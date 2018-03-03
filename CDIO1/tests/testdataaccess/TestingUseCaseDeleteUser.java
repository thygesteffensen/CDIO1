package testdataaccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dataaccess.IUserDAO;
import dataaccess.UserDAO;
import dataaccess.IUserDAO.DALException;
import dto.UserDTO;

public class TestingUseCaseDeleteUser {

	@Test
	public void TestDeleteUser() {
		//test if UserDTO can be created and compared for equality
		//this is needed for the current use case testing 
		
		//initialisation
		List<String> roles = new ArrayList<>();
		roles.add("operator");		
		int id = 4; 
		String name = "John"; 
		String initials = "jo";
		String str = String.format("UserDTO [id= %d, name= %s, initials= %s, roles= %s]" , id, name, initials, roles);
		//execution
		UserDTO user = new UserDTO(id, name, initials, "cpr321", "freeMan", roles);
		//assertion 
		assertEquals(user.toString(), str);
		
		//init
		IUserDAO dataAccess = null;
		List<UserDTO> users = new ArrayList<>();
		try {
			dataAccess = new UserDAO();
			users = dataAccess.getUserList();
			//add user to user list 
			dataAccess.createUser(user);		
			assertTrue(available(user, users));			

			//execution
			dataAccess.deleteUser(user.getId());
    		
			//assertion
			assertFalse(available(user, users));
			
		} catch (DALException e) {
			// TO DO..
		}		
			
	}
	
	private boolean available(UserDTO user, List<UserDTO> users) {
		boolean available = false;
		for(UserDTO u : users) {
			if(u.toString().equals(user.toString())) {
				available = true;
				break;
			}
		}		
		return available;
	}

	
	
}
