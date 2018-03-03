package testdataaccess;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dataaccess.IUserDAO;
import dataaccess.IUserDAO.DALException;
import dataaccess.UserDAO;
import dto.UserDTO;

public class TestingUseCaseUpdateUser {

	
	@Test
	public void TestUpdateUser() {
		//test if UserDTO can be created and compared for equality
		//this is needed for the current use case testing 
		
		//initialisation
		List<String> roles = new ArrayList<>();
		roles.add("operator");		
		int id = 1; 
		String name = "Kaloyan"; 
		String initials = "ka";
		String str = String.format("UserDTO [id= %d, name= %s, initials= %s, roles= %s]" , id, name, initials, roles);

		//execution
		UserDTO user = new UserDTO(id, name, initials, "cpr123", "freePswd", roles);
				
		//assertion 
		assertEquals(user.toString(), str);
		
		//init
		IUserDAO dataAccess = null;
		List<UserDTO> users = new ArrayList<>();
		try {
			dataAccess = new UserDAO();
			users = dataAccess.getUserList();
		} catch (DALException e) {
			// TO DO..
		}		
		
		//execution
    	if ( ! available(user, users) ) {
        	try {
    			dataAccess.createUser(user);			
    		}catch(DALException e) {
    			//TO DO..
    		}
    		
    		UserDTO returnedUser = null;
    		try {
    			returnedUser = dataAccess.getUser(id);
    		}catch(DALException e) {
    			//TO DO..
    		}
    		
    		//assertion
    		assertEquals(user.toString(), returnedUser.toString());
    		
    	}else {//if user already in the store update the user 
    		 IUserDAO dataAccessReload = null;
    		//init
    		UserDTO u = new UserDTO();
    		UserDTO modifiedUser = new UserDTO();
    		try {
    			dataAccessReload = new UserDAO();
    			u = dataAccessReload.getUser(id);
    			modifiedUser = u.clone();
    			modifiedUser.getRoles().add("Admin");
    			modifiedUser.setName(modifiedUser.getName() + " Penov");    			    			
    			dataAccessReload.updateUser(modifiedUser);
    		} catch (DALException e) {
    			e.printStackTrace();
    		}
    		
    		String returnedUserAsStr = "";
			try {
				returnedUserAsStr = dataAccessReload.getUser(u.getId()).toString();
			} catch (DALException e) {
				//TO DO...
			}    		
    		assertEquals(modifiedUser.toString(), returnedUserAsStr);
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
