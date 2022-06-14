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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flush.FlushVideo.model.Friendship;
import com.flush.FlushVideo.model.RoomInvitations;
import com.flush.FlushVideo.model.SubClasses.CompositeId;
import com.flush.FlushVideo.model.SubClasses.CompositeIdInvitations;
import com.flush.FlushVideo.repository.RoomInvitationsRepository;


	@CrossOrigin(origins = "http://localhost:4200")
	@RestController
	@RequestMapping("/api")

	public class RoomInvitationsController{
	/** The JPA repository */
	@Autowired
	private RoomInvitationsRepository roomInvitationsRepository;

	@GetMapping("/roomInvitations")
	public ResponseEntity<List<RoomInvitations>> getAllRoomInvitations(){
	    try {
			List<RoomInvitations> roomInvitations = new ArrayList<RoomInvitations>();
				roomInvitationsRepository.findAll().forEach(roomInvitations::add);

			if (roomInvitations.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(roomInvitations, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/roomInvitations")
	public ResponseEntity<RoomInvitations> createRoomInvitations(@RequestBody  RoomInvitations roomInvitations) {
		try {
			 RoomInvitations _roomInvitations = roomInvitationsRepository
					.save(new RoomInvitations(roomInvitations.getId(), roomInvitations.getFlag()));
			return new ResponseEntity<>(_roomInvitations, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	 @GetMapping("/roomInvitations/{id1}/{id2}")
		public ResponseEntity<RoomInvitations> getRoomInvitationsShipById(@PathVariable("id1") int id1,@PathVariable("id2") int id2) {
		 CompositeIdInvitations id = new CompositeIdInvitations(id1,id2);
			Optional<RoomInvitations> documentiData = roomInvitationsRepository.findById(id);

			if (documentiData.isPresent()) {
				return new ResponseEntity<>(documentiData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

		@DeleteMapping("/roomInvitations/{id1}/{id2}")
		public ResponseEntity<HttpStatus> deleteRoomInvitations(@PathVariable("id1") int id1,
				@PathVariable("id2") int id2) {
			CompositeIdInvitations id=new CompositeIdInvitations(id1,id2);
			try {
				roomInvitationsRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

