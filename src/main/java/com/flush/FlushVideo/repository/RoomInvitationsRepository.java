package com.flush.FlushVideo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flush.FlushVideo.model.RoomInvitations;
import com.flush.FlushVideo.model.SubClasses.CompositeId;
import com.flush.FlushVideo.model.SubClasses.CompositeIdInvitations;

public interface RoomInvitationsRepository extends JpaRepository<RoomInvitations, CompositeIdInvitations>{
	Optional<RoomInvitations> findById(CompositeIdInvitations id);
}
