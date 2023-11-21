package com.example.blog.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthfilter extends OncePerRequestFilter {
	
	private JwtTokenProvider jwtTokenProvider;
	
	private UserDetailsService userDetailsService;
	

	public JwtAuthfilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
		this.userDetailsService = userDetailsService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//get header with auth
		String authHeader = request.getHeader("Authorization");
		
		String jwtToken = null;
		String userNameFromToken = null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			
			jwtToken = authHeader.substring(7);
			
			if(jwtTokenProvider.validateToken(jwtToken)) {
				userNameFromToken = jwtTokenProvider.getUserNameFromToken(jwtToken);
				
				 UserDetails userDetails =  userDetailsService.loadUserByUsername(userNameFromToken);
				
				 UsernamePasswordAuthenticationToken usernameAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				 usernameAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 
				 SecurityContextHolder.getContext().setAuthentication(usernameAuthenticationToken);
			}
			
			
		}
		doFilter(request, response, filterChain);
		
	}

}
