package com.flush.FlushVideo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flush.FlushVideo.model.Friendship;
import com.flush.FlushVideo.model.SubClasses.CompositeId;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, CompositeId>{
	Optional<Friendship> findById(CompositeId id);
}