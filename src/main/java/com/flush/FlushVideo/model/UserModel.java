package com.flush.FlushVideo.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserModel {
	
	

    private long id;

    private String nome;

    private String cognome;
    
    private String email;

    private String password;

    private int ruolo;
    

    public enum Day { 

        ERRORE(0), UTENTE(1), ADMIN(2),
        SUPERADMIN(3);

        private final int abbreviation;

        // Reverse-lookup map for getting a day from an abbreviation
        private static final Map<Integer, Day> lookup = new HashMap<Integer, Day>();

        static {
            for (Day d : Day.values()) {
                lookup.put(d.getAbbreviation(), d);
            }
        }

        private Day(int abbreviation) {
            this.abbreviation = abbreviation;
        }

        public int getAbbreviation() {
            return abbreviation;
        }

        public static Day get(int abbreviation) {
            return lookup.get(abbreviation);
        }
    }
    
    
    public UserModel() {};
    public UserModel(User user) {
    	this.id = user.getId();
		this.nome = user.getNome();
		this.cognome = user.getCognome();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.ruolo = user.getRuolo();	

    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Day getRuolo() {


		return Day.get(ruolo);
	}
	
	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}


}
