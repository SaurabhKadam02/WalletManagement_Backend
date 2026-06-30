//package com.technodrome.walletmanagement.util;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//   
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//
//            String token = authHeader.substring(7);
//            String email = jwtUtil.extractEmail(token);
//
//            UsernamePasswordAuthenticationToken auth =
//                    new UsernamePasswordAuthenticationToken(
//                            email,
//                            null,
//                            new ArrayList<>());
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//        } catch (ExpiredJwtException e) {
//
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().write(
//                    "{\"message\":\"Token expired. Please login again\"}");
//            return;
//
//        } catch (Exception e) {
//
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().write(
//                    "{\"message\":\"Invalid token\"}");
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
package com.technodrome.walletmanagement.util;

import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Skip JWT validation for login and register APIs
        String path = request.getServletPath();

        if (path.equals("/auth/login") || path.equals("/auth/register")) {

            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            String token = authHeader.substring(7);

            String email = jwtUtil.extractEmail(token);
            System.out.println("JWT Email = " + email);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            new ArrayList<>());

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (ExpiredJwtException e) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            response.getWriter().write(
                    "{\"message\":\"Token expired. Please login again\"}");

            return;

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            response.getWriter().write(
                    "{\"message\":\"Invalid token\"}");

            return;
        }

        filterChain.doFilter(request, response);
    }
}