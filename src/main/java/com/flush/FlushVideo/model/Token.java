package com.flush.FlushVideo.model;

public class Token {
	
	private Long scadenza;
	private User user;
	private String error;
    


	
	public Token(User user,Long scadenza) {
		this.user=user;
		this.scadenza=scadenza;
		error="";
	}

	
	public Token() {}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user=user;
	}

	public Long getScadenza() {
		return scadenza;
	}


	public void setScadenza(Long scadenza) {
		this.scadenza = scadenza;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}
	


}
