package com.flush.FlushVideo.Controller;

import com.flush.FlushVideo.model.Room;
import com.flush.FlushVideo.model.User;
import com.flush.FlushVideo.repository.RoomRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class RoomController {
/** The JPA repository */
@Autowired
private RoomRepository roomRepository;

@GetMapping("/rooms")
public ResponseEntity<List<Room>> getAllRooms(@RequestParam(required = false)String nome){
    try {
		List<Room> rooms = new ArrayList<Room>();

		if (nome == null)
			roomRepository.findAll().forEach(rooms::add);
		else
			roomRepository.findByNome(nome).forEach(rooms::add);

		if (rooms.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@PostMapping("/rooms")
public ResponseEntity<Room> createRoom(@RequestBody Room room) {
	try {
		Room _room = roomRepository
				.save(new Room(0, room.getNome(), room.getStatus(), room.getFlagstatus(), room.getIdutente() , room.getDataagg(), room.getDatainizio(), room.getDatafine()));
		return new ResponseEntity<>(_room, HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@DeleteMapping("/rooms/{id}")
public ResponseEntity<HttpStatus> deleteRoom(@PathVariable("id") long id) {
	try {
		roomRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@DeleteMapping("/rooms")
public ResponseEntity<HttpStatus> deleteAllRooms() {
	try {
		roomRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

@PutMapping("/rooms/{id}")
public ResponseEntity<Room> updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
	Optional<Room> roomDate = roomRepository.findById(id);

	if (roomDate.isPresent()) {
		//  room.getDatainizio(), room.getDatafine()
		Room _room = roomDate.get();
		_room.setNome(room.getNome());
		_room.setStatus(room.getStatus());
		_room.setFlagstatus(room.getFlagstatus());
		_room.setIdutente(room.getIdutente());
		_room.setDataagg( room.getDataagg());
        _room.setDatainizio(room.getDatainizio());
        _room.setDatafine(room.getDatafine());
		return new ResponseEntity<>(roomRepository.save(_room), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
}