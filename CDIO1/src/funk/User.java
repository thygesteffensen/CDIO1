package funk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import codegen.GenPassword;
import codegen.IGenPassword;
import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dto.UserDTO;

public class User implements IUser {
	private List<UserDTO> users = new ArrayList<>();
	private IGenPassword genPassword = new GenPassword();
	private IUserDAO dataAccessObject;
	
	public User(IUserDAO dataAccess) {
		this.dataAccessObject = dataAccess;
	}
	
	@Override
	public String showUsers() {
		String str = "";
		try {
			users = dataAccessObject.getUserList();
			// Could be rewritten into an for each loop
			for (int i = 0; i < users.size(); i++)
				str += users.get(i).toString() + "\n";
		} catch (DALException e) {
			System.err.println("A mistake happend while trying to show users. \n");
			e.printStackTrace();
		}	
		return str;
	}

	@Override
	public String createUser(String name, String cpr, String role) {
		// Password gets auto generated in genPassword                          Måske man kunne have en nested klasse her?
		String password = genPassword.genPassword();
		
		// Contains roles	
		List<String> roles = new ArrayList<>();		
		roles.add(role);
		
		// Initials gets generated from the given name
		String initials = generateInitials(name);
		
		// gets all users and then I have the right size.
		// this method is slow and should be improved.
		try {
			users = dataAccessObject.getUserList();			
		} catch (DALException e) {
			System.err.println("A mistake happend while creating a user. The user is not created.\n");
			e.printStackTrace();
		}
		int id = users.size() + 1;
		
		// Creates user
		UserDTO user = new UserDTO(id, name, initials, cpr, password, roles);
		// "puts" user in database...
		try {
			this.dataAccessObject.createUser(user);
		}
		catch (DALException e) {
			System.err.println("A mistake happend while creating a user. The user is not created.\n");
			e.printStackTrace();
		}
		
		return user.toString();
	}
	

	private String addToString(String input, String str) {
		return str + input;
	}

	@Override
	public String updateUser(int userID, int type, String change) throws DALException {
		if(type == 1) {
			dataAccessObject.getUser(userID).setName(change);
			dataAccessObject.getUser(userID).setInitials(generateInitials(change));
		}
		else if (type == 2) {
			List<String> roles = new ArrayList<>();
			roles.add(change);
			dataAccessObject.getUser(userID).setRoles(roles);
		}
		
		return "Change have happend";
	}

	@Override
	public void deleteUser(int userID) throws DALException {
		dataAccessObject.deleteUser(userID);

	}
	
	/* ***********************************************************************************************
	 * 									HELPER FUNCTIONS
	 * ***********************************************************************************************
	 */	
	private String getString() {
		Scanner in = new Scanner(System.in);
		String name = in.nextLine().trim();
		in.close();
		return name;
	}
	
	private int getInt() {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		in.close();
		return num;
	}
	
	private String generateInitials(String name) {
		String[] nameArr = name.split(" ");
		String initials = "";
		for (int i = 0; i < nameArr.length; i++)
			initials += nameArr[i].charAt(0);
		return initials.toLowerCase();
	}

}
