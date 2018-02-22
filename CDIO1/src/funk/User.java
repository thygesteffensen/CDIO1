package funk;

import java.util.ArrayList;
import java.util.List;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dto.UserDTO;

public class User implements IUser {
	private List<UserDTO> users = new ArrayList<>();
	private IUserDAO dataAccess;
	
	public User(IUserDAO dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public String showUsers() {
		String str = "";
		try {
			users = dataAccess.getUserList();
//			users.stream().forEach(u -> System.out.printf("%s\n", u));	
			users.stream().forEach(u -> addToString(u.toString(),str));
		} catch (DALException e) {
			e.printStackTrace();
		}	
		System.out.println(str);
		return str;
	}

	@Override
	public String createUser(String name, String cpr, String role) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String addToString(String input, String str) {
		return str + input;
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
