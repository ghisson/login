package com.flush.FlushVideo.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.flush.FlushVideo.model.SubClasses.CompositeId;
import com.flush.FlushVideo.model.SubClasses.CompositeIdInvitations;

@Entity
@Table(name = "tb_ist_invitostanza_cl")
public class RoomInvitations {
	
	@EmbeddedId
	private CompositeIdInvitations id;

	public CompositeIdInvitations getId() {
		return id;
	}
	
	public void setId(CompositeIdInvitations id) {
		this.id = id;
	}
	
	@Column(name = "IST_FLAG_ACCET")
	private String flag;

	public RoomInvitations() {

	}

	public RoomInvitations(CompositeIdInvitations id, String flag) {
		super();
		this.id = id;
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomInvitations other = (RoomInvitations) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RoomInvitations [id=" + id + ", flag=" + flag + ", getId()=" + getId() + ", getFlag()=" + getFlag()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}

}
