package funk;

import java.util.ArrayList;
import java.util.List;

import dal.IUserDAO.DALException;
import dto.UserDTO;

public class User implements IUser {
	private List<UserDTO> users = new ArrayList<>();
	
	@Override
	public String showUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createUser(String name, String cpr, String role) {
		
		try {
			users = dataAccess.getUserList();
			users.stream().forEach(u -> System.out.printf("%s\n", u));			
		} catch (DALException e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public String updateUser(String type, String change) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub

	}

}
