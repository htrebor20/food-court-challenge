package com.pragma.food_cout.configuration.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    protected final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = this.jwtTokenUtil.getTokenFromRequest(request);
        String usernameFromToken = null;

        if (token != null) {
            usernameFromToken =  this.jwtTokenUtil.getUsernameFromToken(token);
        }

        try {
            if (usernameFromToken != null && this.jwtTokenUtil.validateToken(token)) {
                List<String> roles = this.jwtTokenUtil.getRolesFromToken(token);
                UserDetails userDetails = User.builder()
                        .username(usernameFromToken)
                        .password("")
                        .roles(roles.toArray(new String[0]))
                        .build();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        token,
                        userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(request);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            request.getSession().setAttribute("messageToken", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
