package dal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;

public class UserStore implements Serializable {
	private List<UserDTO> users = new ArrayList<>();

	public UserStore () { }
	
	public UserStore (List<UserDTO> users) {
		this.users = users;
	}
	
	public void addUser(UserDTO user) {
		users.add(user);
	} 
	public List<UserDTO> getUsers(){
		return users;
	}
	
}
