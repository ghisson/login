package com.flush.FlushVideo.model.SubClasses;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class CompositeId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "AMI_ID_UTENTE1_FK")
	private int amiIdUtente1Fk;
	@Column(name = "AMI_ID_UTENTE2_FK")
	private int amiIdUtente2Fk;
	
	public CompositeId() {
		
	}
	public CompositeId(int id1, int id2) {
		// TODO Auto-generated constructor stub
		amiIdUtente1Fk=id1;
		amiIdUtente2Fk=id2;
	}

	public int getAmiIdUtente1Fk() {
		return amiIdUtente1Fk;
	}

	public void setAmiIdUtente1Fk(int amiIdUtente1Fk) {
		this.amiIdUtente1Fk = amiIdUtente1Fk;
	}

	public int getAmiIdUtente2Fk() {
		return amiIdUtente2Fk;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amiIdUtente1Fk, amiIdUtente2Fk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeId other = (CompositeId) obj;
		return amiIdUtente1Fk == other.amiIdUtente1Fk && amiIdUtente2Fk == other.amiIdUtente2Fk;
	}

	public void setAmiIdUtente2Fk(int amiIdUtente2Fk) {
		this.amiIdUtente2Fk = amiIdUtente2Fk;
	}

}