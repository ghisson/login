package com.flush.FlushVideo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flush.FlushVideo.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
    List<Room> findByNome(String nome);
    Optional<Room> findById(long id);
}
