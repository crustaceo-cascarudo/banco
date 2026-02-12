package com.fpmislata.banco.web.filter;

import com.fpmislata.banco.domain.repository.UserRepository;
import com.fpmislata.banco.domain.repository.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class TokenFilter extends OncePerRequestFilter {

  private final UserRepository userRepository;

  private static final List<String> PUBLIC_PATHS = Arrays.asList(
      "/api/users/register",
      "/api/users/login");

  public TokenFilter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private boolean isPublicPath(String path) {
    return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    String requestPath = request.getRequestURI();
    String requestMethod = request.getMethod();

    System.out.println("[TokenFilter] " + requestMethod + " " + requestPath);

    if ("OPTIONS".equalsIgnoreCase(requestMethod)) {
      System.out.println("OPTIONS request (CORS preflight), permitir sin validación");
      filterChain.doFilter(request, response);
      return;
    }

    // Permitir rutas públicas sin token
    if (isPublicPath(requestPath)) {
      System.out.println("Ruta pública, permitir sin token");
      filterChain.doFilter(request, response);
      return;
    }

    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);
      System.out.println("Token extraído: " + token.substring(0, Math.min(token.length(), 20)) + "...");

      try {
        UserEntity user = userRepository.findByToken(token);
        if (user != null) {
          System.out.println("Usuario autenticado: " + user.name() + " (DNI: " + user.dni() + ")");

          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(
                  user.dni(),
                  null,
                  List.of(new SimpleGrantedAuthority("ROLE_USER"))
              );

          SecurityContextHolder.getContext().setAuthentication(authentication);

          request.setAttribute("authenticatedUser", user);
          request.setAttribute("authenticatedUserId", user.id());
          request.setAttribute("authenticatedUserName", user.name());
          request.setAttribute("authenticatedUserSurname1", user.surname1());
          request.setAttribute("authenticatedUserSurname2", user.surname2());
          request.setAttribute("authenticatedUserDni", user.dni());
        } else {
          System.out.println("Token inválido o expirado");
        }
      } catch (Exception e) {
        System.out.println("Error validando token: " + e.getMessage());
      }
    }

    filterChain.doFilter(request, response);
  }
}

