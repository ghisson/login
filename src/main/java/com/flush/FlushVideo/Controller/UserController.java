package com.flush.FlushVideo.Controller;
import com.flush.FlushVideo.help.Auth;
import com.flush.FlushVideo.model.Token;
import com.flush.FlushVideo.model.User;
import com.flush.FlushVideo.model.UserModel;
import com.flush.FlushVideo.repository.UserRepository;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class UserController {
	/** The JPA repository */
    @Autowired
    private UserRepository userRepository;

     @GetMapping("/token")

    public ResponseEntity getToken(@RequestHeader("token") String token){
    	
    	Token tk= Auth.getToken(token);


		 if(tk==null){
			 //token sbagliato
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token invalido");
			 //return new ResponseEntity<>(userModel,HttpStatus.UNAUTHORIZED );
		 }
		 if(tk.getError().equals("Token scaduto")) {
			 //token scaduto
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token scaduto");
			 //return new ResponseEntity<>(userModel,HttpStatus.UNAUTHORIZED );
		 }


		Optional<User> _user=userRepository.findById(tk.getUser().getId());


		if(!_user.isPresent() || !tk.getUser().equals(_user.get())){
			//token valido ma che non contiene un utente nel db
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User dentro token invalido");
		}




    	//return new ResponseEntity<>(new UserModel(Auth.getToken(token).getUser()), HttpStatus.OK);
		 return ResponseEntity.ok(new UserModel(Auth.getToken(token).getUser()));
    }
     
    
    
    @GetMapping("/users")
    public ResponseEntity getAllUsers(@RequestHeader("token") String token){
        try {
        	Token tk=Auth.getToken(token);


			if(tk==null){
				//token sbagliato
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token invalido");
				//return new ResponseEntity<>(HttpStatus.UNAUTHORIZED );
			}
			if(tk.getError().equals("Token scaduto")) {
				//token scaduto
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token scaduto");
				//return new ResponseEntity<>(HttpStatus.UNAUTHORIZED );
			}

			Optional<User> _user=userRepository.findById(tk.getUser().getId());
			
			if(!_user.isPresent() || !tk.getUser().equals(_user.get())){
				//token valido ma che non contiene un utente nel db
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User dentro token invalido");
			}
    		

    		
			List<User> users = new ArrayList<User>();
			List <UserModel>ret = new ArrayList<UserModel>();

			userRepository.findAll().forEach(users::add);

			users.forEach(user -> {ret.add(new UserModel(user));});

			if (users.isEmpty()) {
				//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				return ResponseEntity.noContent().build();
			}

			//return new ResponseEntity<>(users, HttpStatus.OK);
			return ResponseEntity.ok(ret);
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
    }
     
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
		try {
			
			Optional<User> getUser=userRepository.findByEmail(user.getEmail());
			if(getUser.isPresent()) {
				//return new ResponseEntity<>(HttpStatus.IM_USED);
				return ResponseEntity.status(HttpStatus.IM_USED).body("email già usata");
			}
			User _user = userRepository
					.save(new User(0, user.getNome(), user.getCognome(), user.getEmail(), user.getPassword(), 1));
			return ResponseEntity.ok(_user);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
    
    @PostMapping("/login")
    public ResponseEntity checkUser(@RequestParam(required = true)String email,String password){
    	try {
    		
			Optional<User> user = userRepository.findByEmail(email) ;
			
		
			if (user.isPresent()) {
				if(user.get().getPassword().equals(password)) {
					
					
					String token=Auth.createToken(user.get());
					
					if(token==null) {
						return ResponseEntity.internalServerError().build();
					}
					return ResponseEntity.ok(token);
				}
				else{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password sbagliata");
				}
				 
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email sbagliata");
			}
			
			
			
		}catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
  
    }
    



	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return ResponseEntity.ok("ok");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}


	
   


}
