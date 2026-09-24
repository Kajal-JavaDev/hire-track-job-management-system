package com.example.demo.config;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Component;

import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;




@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
		String authHeader = request.getHeader("Authorization");
		
		String token = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
		 token = authHeader.substring(7);
			
			String username = jwtService.extractUsername(token);
			
			if(username != null) {
				User user = userService.findByUsername(username);
				
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
				System.out.println("USER ROLE = " + user.getRole());//TEMPRORY
				
				List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));//add new
				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username ,null, authorities);
				
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request,response);
	}

}
