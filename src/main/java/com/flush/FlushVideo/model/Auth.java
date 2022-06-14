package com.flush.FlushVideo.model;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Auth {
	
	private static String[] role= {"Merda", "Admin"};;

	
	
	public static boolean checkToken(String token) {
		Token tk;
		JWTService jwtservice;
		try {
			jwtservice = new JWTService();
		} catch (NoSuchAlgorithmException e1) {
			return false;
		} 
		try {
			tk = jwtservice.verify(token);
		}catch(Exception e) {
			return false;
		}
		
		return tk!=null;
	}
	
	public static Token getToken(String token) {
		Token tk;
		JWTService jwtservice;
		try {
			jwtservice = new JWTService();
		} catch (NoSuchAlgorithmException e1) {
			return null;
		} 
		try {
			tk = jwtservice.verify(token);
		}catch(Exception e) {
			tk=null;
		}
		
		return tk;
	}
	
	
	public static String createToken(User user) {
		JWTService jwtservice;
		try {
			jwtservice = new JWTService();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		String token=jwtservice.create(user);
		return token;
	}
	
	
	
	
	

}
