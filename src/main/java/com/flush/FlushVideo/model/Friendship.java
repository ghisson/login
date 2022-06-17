package com.flush.FlushVideo.model;

import java.util.Objects;

import javax.persistence.*;

import com.flush.FlushVideo.model.SubClasses.CompositeId;

@Entity
@Table(name = "tb_ami_amicizia_cl")
public class Friendship {

	@EmbeddedId
	private CompositeId id;

	public CompositeId getId() {
		return id;
	}

	public Friendship(CompositeId id) {
		super();
		this.id = id;
	}

	public void setId(CompositeId id) {
		this.id = id;
	}

	public Friendship() {

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
		Friendship other = (Friendship) obj;
		return Objects.equals(id, other.id);
	}
}