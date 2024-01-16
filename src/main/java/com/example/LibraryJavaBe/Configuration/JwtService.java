package com.example.LibraryJavaBe.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String sceretKey;
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;
    @Value("${security.jwt.expiration-time}")

    private long refreshExpiration;
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);

    }
    public String generateToken(UserDetails userDetails){

        return generateToken((new HashMap<>()),userDetails);
    }
    public boolean isTokenValid(String token ,UserDetails userDetails){

        final String username = extractUsername(token);
        return  (username.equals(userDetails.getUsername())&& !isTokenExpired(token));

    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(
            Map<String, Objects> extractClaims,
            UserDetails userDetails
    ){
        return  buildToken(extractClaims,userDetails,jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ){
        return  buildToken(new HashMap<>(),userDetails,refreshExpiration);
    }

    private  String buildToken( Map<String, Objects> extractClaims,
                                UserDetails userDetails,
                                long expiration
    ){
        return Jwts.builder().setClaims(extractClaims)
                .setSubject(userDetails.getUsername())

                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis()+jwtExpiration)))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public <T>T extractClaim(String token, Function<Claims,T> claimsResolver){
        final  Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);

    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(sceretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
