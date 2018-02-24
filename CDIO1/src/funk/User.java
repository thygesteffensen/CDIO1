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
	private IUserDAO dataAccess;
	
	public User(IUserDAO dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public String showUsers() {
		String str = "";
		try {
			users = dataAccess.getUserList();
			System.out.println(users.size() + "\t" + str);
			users.stream().forEach(u -> addToString(u.toString(),str));
		} catch (DALException e) {
			e.printStackTrace();
		}	
		System.out.println(str);
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
		int id = 1; // Will be auto generated later
		
		// Creates user
		UserDTO user = new UserDTO(id, name, initials, cpr, password, roles);
		// "puts" user in database...
		try {
			this.dataAccess.createUser(user);
		}
		catch (DALException e) {
			return "Failure";
		}
		
		return user.toString();
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
		return initials;
	}

}
