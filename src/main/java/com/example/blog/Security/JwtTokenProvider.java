package com.example.blog.Security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.blog.exception.BlogApplicationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	
	@Value("${app.jwt-expiration-milliseconds}")
	private long jwtExpirationTime;

	// current version way of doing it 
	
	public String generateToken(Authentication authentication) {
		
		Date expireDate = new Date(new Date().getTime() + jwtExpirationTime);
		
		return Jwts.builder()
				.subject(authentication.getName())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+jwtExpirationTime))
				.signWith(getKey())
				.compact();
	}
	
	public SecretKey getKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	
	public String getUserNameFromToken(String token) {
		//Claims claims = Jwt.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	     // Claims claims  = Jwts.parser().setSigningKey(getKey()).build().parseClaimsJwt(token).getBody();
	     // Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token);
	      
		Claims claims = Jwts
				.parser()
				.verifyWith(getKey())
	    		.build().parseSignedClaims(token).getPayload();
	      
	      claims.toString();
	      String username = claims.getSubject();
	      return username;
	}
	
	public boolean validateToken(String token)  {
		
		try {
			Jwts
			.parser()
			.verifyWith(getKey())
			.build()
			.parse(token);
			return true;
		}
		catch(MalformedJwtException mlx) {
			
			throw new BlogApplicationException(HttpStatus.BAD_REQUEST, "Invalid JWT token ");
		}
		
		catch(ExpiredJwtException expired) {
			throw new BlogApplicationException(HttpStatus.BAD_REQUEST, "Token is expired");
		}
		catch(UnsupportedJwtException ex) {
			throw new BlogApplicationException(HttpStatus.BAD_REQUEST, "Unsupported jwt token");
			
		}
		catch(IllegalArgumentException ex) {
			throw new BlogApplicationException(HttpStatus.BAD_GATEWAY, "JWT claim string is empty");
		}
	}

}
