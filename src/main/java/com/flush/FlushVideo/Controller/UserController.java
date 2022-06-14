package com.flush.FlushVideo.Controller;
import com.flush.FlushVideo.model.Auth;
import com.flush.FlushVideo.model.Token;
import com.flush.FlushVideo.model.User;
import com.flush.FlushVideo.model.UserModel;
import com.flush.FlushVideo.repository.UserRepository;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class UserController {
	/** The JPA repository */
    @Autowired
    private UserRepository userRepository;
       
    @GetMapping("/manual")
    void manual(HttpServletResponse response) throws IOException {
        response.setHeader("Custom-Header", "foo");
        response.setStatus(200);
        response.getWriter().println("Hello World!");
    }
    
    /*@GetMapping("/token")
    void getToken(HttpServletResponse response,@RequestParam(required = false)String token)throws IOException{
    	
    	Token tk=Auth.getToken(token);
    	UserModel userModel=new UserModel();
		
		
    	Date date = new Date();
        Long ora = date.getTime();
    	//return new ResponseEntity<>(new UserModel(Auth.getToken(token).getUser()), HttpStatus.OK);   
        
        response.setHeader("Custom-Header", "foo");
        response.setStatus(200);
        response.getWriter().print(Auth.getToken(token).getUser());
    }
    */
   
     @GetMapping("/token")
    public ResponseEntity<UserModel> getToken(@RequestParam(required = false)String token){
    	
    	Token tk=Auth.getToken(token);
    	UserModel userModel=new UserModel();
		
		if(tk==null){
			userModel.setError("Token invalido");
			//token sbagliato
			return new ResponseEntity<>(userModel,HttpStatus.UNAUTHORIZED );
		}
		if(tk.getError().equals("Token scaduto")) {
			//token scaduto
			userModel.setError("Token scaduto");
			return new ResponseEntity<>(userModel,HttpStatus.UNAUTHORIZED );
		}
    	Date date = new Date();
        Long ora = date.getTime();
    	return new ResponseEntity<>(new UserModel(Auth.getToken(token).getUser()), HttpStatus.OK);       
    }
     
    
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false)String nome,
    		@RequestHeader("token") String token){
        try {
        	Token tk=Auth.getToken(token);
    		
    		if(tk==null){
    			//token sbagliato
    			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED );
    		}
    		if(tk.getError().equals("Token scaduto")) {
    			//token scaduto
    			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED );
    		
        	
        	
    		}
    		
			List<User> users = new ArrayList<User>();

			if (nome == null)
				userRepository.findAll().forEach(users::add);
			else
				userRepository.findByNome(nome).forEach(users::add);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
     
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			
			Optional<User> getUser=userRepository.findByEmail(user.getEmail());
			if(getUser.isPresent()) {
				return new ResponseEntity<>(HttpStatus.IM_USED);
			}
			User _user = userRepository
					.save(new User(0, user.getNome(), user.getCognome(), user.getEmail(), user.getPassword(), user.getRuolo()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @PostMapping("/login")
    public ResponseEntity<String> checkUser(@RequestParam(required = true)String email,String password){
    	try {
    		
			Optional<User> user = userRepository.findByEmail(email) ;
			
		
			if (user.isPresent()) {
				if(user.get().getPassword().equals(password)) {
					
					
					String token=Auth.createToken(user.get());
					
					if(token==null) {
						return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					return new ResponseEntity<>( token, HttpStatus.OK);
				}
				else{
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED );
				}
				 
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			
			
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
  
    }
    

    
    
    @GetMapping("/users/{idutente}")
	public ResponseEntity<User> getUserById(@PathVariable("idutente") long id) {
		Optional<User> documentiData = userRepository.findById(id);

		if (documentiData.isPresent()) {
			return new ResponseEntity<>(documentiData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
   


}
