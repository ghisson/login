package com.flush.FlushVideo.model.SubClasses;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class CompositeIdInvitations implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "IST_ID_UTENTE_FK")
	private int istIdUtenteFk;
	@Column(name = "IST_ID_STANZA_FK")
	private int istIdStanzaFk;
	
	public CompositeIdInvitations() {
		
	}
	public CompositeIdInvitations (int id1, int id2) {
		// TODO Auto-generated constructor stub
		istIdUtenteFk=id1;
		istIdStanzaFk=id2;
	}

	public int getIstIdUtenteFk() {
		return istIdUtenteFk;
	}

	public void setIstIdUtenteFk(int istIdUtenteFk) {
		this.istIdUtenteFk = istIdUtenteFk;
	}

	public int getIstIdStanzaFk() {
		return istIdStanzaFk;
	}
	
	public void setIstIdStanzaFk(int istIdStanzaFk) {
		this.istIdStanzaFk = istIdStanzaFk;
	}

	@Override
	public int hashCode() {
		return Objects.hash(istIdUtenteFk, istIdStanzaFk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeIdInvitations other = (CompositeIdInvitations) obj;
		return istIdUtenteFk == other.istIdUtenteFk && istIdStanzaFk == other.istIdStanzaFk;
	}



}
