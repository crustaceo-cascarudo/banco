package com.fpmislata.banco.web.filter;

import com.fpmislata.banco.domain.repository.UserRepository;
import com.fpmislata.banco.domain.repository.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class TokenFilter extends OncePerRequestFilter {

  private final UserRepository userRepository;

  public TokenFilter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);

      try {
        UserEntity user = userRepository.findByToken(token);
        if (user != null) {
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
        }
      } catch (Exception e) {
        // Token inválido — no autenticar, dejar que SecurityConfig decida
      }
    }

    filterChain.doFilter(request, response);
  }
}

