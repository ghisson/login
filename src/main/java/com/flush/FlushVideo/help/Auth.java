package com.flush.FlushVideo.help;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import com.flush.FlushVideo.model.Token;
import com.flush.FlushVideo.model.User;
import com.flush.FlushVideo.repository.UserRepository;

public class Auth {





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
