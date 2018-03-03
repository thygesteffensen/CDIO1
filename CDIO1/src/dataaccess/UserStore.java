package dataaccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dto.UserDTO;

public class UserStore implements Serializable {
	private List<UserDTO> users = new ArrayList<>();

	public UserStore () { }
	
	public UserStore (List<UserDTO> users) {		
		this.users = users;
		
		Collections.sort(users);
	}
	
	public void addUser(UserDTO user) {
		users.add(user);
		
		Collections.sort(users);
	} 
	public List<UserDTO> getUsers(){
		return users;
	}

}
