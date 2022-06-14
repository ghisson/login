package com.flush.FlushVideo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flush.FlushVideo.model.Friendship;
import com.flush.FlushVideo.model.User;
import com.flush.FlushVideo.model.SubClasses.CompositeId;
import com.flush.FlushVideo.repository.FriendshipRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class FriendshipController {
	/** The JPA repository */
    @Autowired
    private FriendshipRepository FriendshipRepository;
    
    @GetMapping("/friendship")
    public ResponseEntity<List<Friendship>> getAllFriendship(){
        try {
			List<Friendship> friends = new ArrayList<Friendship>();
				FriendshipRepository.findAll().forEach(friends::add);

			if (friends.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(friends, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    @PostMapping("/friendship")
	public ResponseEntity<Friendship> createFriendship(@RequestBody Friendship friend) {
		try {
			Friendship _friend = FriendshipRepository
					.save(friend);
			return new ResponseEntity<>(_friend, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    @GetMapping("/friendship/{id1}/{id2}")
	public ResponseEntity<Friendship> getFriendShipById(@PathVariable("id1") int id1,@PathVariable("id2") int id2) {
    	CompositeId id=new CompositeId(id1,id2);
		Optional<Friendship> documentiData = FriendshipRepository.findById(id);

		if (documentiData.isPresent()) {
			return new ResponseEntity<>(documentiData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/friendship/{id1}/{id2}")
	public ResponseEntity<HttpStatus> deleteFriendship(@PathVariable("id1") int id1,
			@PathVariable("id2") int id2) {
		CompositeId id=new CompositeId(id1,id2);
		try {
			FriendshipRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
