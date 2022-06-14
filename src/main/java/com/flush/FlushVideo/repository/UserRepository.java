package com.flush.FlushVideo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flush.FlushVideo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByNome(String nome);
    Optional<User> findById(long id);
	Optional<User> findByEmail(String email);

}
