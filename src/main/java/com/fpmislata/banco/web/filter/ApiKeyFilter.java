package com.fpmislata.banco.web.filter;

import com.fpmislata.banco.domain.model.ApiClient;
import com.fpmislata.banco.domain.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "X-API-Key";

    private final ApiKeyService apiKeyService;

    public ApiKeyFilter(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String requestMethod = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(requestMethod)) {
            System.out.println("[ApiKeyFilter] OPTIONS request (CORS preflight), permitir sin validación");
            filterChain.doFilter(request, response);
            return;
        }

        String apiKey = request.getHeader(API_KEY_HEADER);

        if (apiKey != null && !apiKey.isBlank()) {
            Optional<ApiClient> apiClient = apiKeyService.validateApiKey(apiKey);

            if (apiClient.isPresent()) {
                ApiClient client = apiClient.get();
                System.out.println("[ApiKeyFilter] API Client autenticado: " + client.getClientName());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                client.getClientName(),
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_API_CLIENT"))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                request.setAttribute("authenticatedApiClient", client);
                request.setAttribute("authenticatedApiClientId", client.getId());
                request.setAttribute("authenticatedApiClientName", client.getClientName());
            }
        }

        filterChain.doFilter(request, response);
    }
}
