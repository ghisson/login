package com.flush.FlushVideo.help;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.flush.FlushVideo.model.Token;
import com.flush.FlushVideo.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {
    private String secret = "atlantica";
    private Instant now;
    private Instant after;
    private  Date dateAfter;
    private Long scadenza;
    static KeyPairGenerator keyPairGenerator;

    static {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    static KeyPair keyPair = keyPairGenerator.generateKeyPair();

    static RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    static RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

    // Get an instance of Algorithm using public, private key pair
    static Algorithm algorithm = Algorithm.RSA512(rsaPublicKey, privateKey);

    public JWTService()  throws NoSuchAlgorithmException{





    }

    public String create(User user) {
    	
    	Duration tempo_scadenza=Duration.ofDays(1);
    	
    	now = Instant.now(); //current date
    	after= now.plus(tempo_scadenza);
    	dateAfter = Date.from(after);
    	scadenza=dateAfter.toInstant().toEpochMilli();
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	String token_json="";
    	
    	Token token=new Token(user,scadenza);
    	try {
    		token_json = ow.writeValueAsString(token);
		} catch (JsonProcessingException e) {
			return null;
		}
        return JWT.create()
                .withClaim("token", token_json)
                .sign(algorithm);
    }

    public Token verify(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .build();

            DecodedJWT jwt = verifier.verify(token);
            Map<String,Object> ret=jwt.getClaims().entrySet()
                    .stream()
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().as(Object.class)));
            
            Date date = new Date();
            Long ora = date.getTime();
            Token tk=null;
            
            
            ObjectMapper objectMapper = new ObjectMapper();
            try {
            	
				tk=objectMapper.readValue((String)ret.get("token"), Token.class);
				
			} catch (JsonMappingException e1) {
				return null;
			} catch (JsonProcessingException e1) {
				return null;
			}	
            
            
           /* if(ora.compareTo(tk.getScadenza())>0) {
            	
            	tk=new Token();
            	tk.setError("Token scaduto");
            }*/
            return tk;   
    }
}
