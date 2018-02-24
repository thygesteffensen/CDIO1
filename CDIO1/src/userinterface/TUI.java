package userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dto.UserDTO;
import funk.IUser;
import funk.User;

public class TUI implements ITextUserInterface {
	private List<IExitable> exitables = new ArrayList<>();
	private Scanner scan = new Scanner(System.in);
	private UserDTO userDTO;
	private IUserDAO userDAO;
	private IUser user;
	Scanner in = new Scanner(System.in);
	

	public TUI(UserDTO userDTO, IUserDAO userDAO, IUser user) {
		this.userDTO = userDTO;
		this.userDAO = userDAO;
		this.user = user;
	}
	
	@Override
	public void ShowUsers() {
//		System.out.println(1);
		String str = user.showUsers();
		System.out.println(str);
	}

	@Override
	public void ShowMenu() throws DALException {
		System.out.printf("%s", menu.toString());		  
		int choice = getInt();
		switch(choice) {
			case 1:
				ShowUsers();
				break;
			case 2:
				CreateUser();
				break;
			case 3:
				UpdateUser();
				break;
			case 4:
				DeleteUser();
				break;
			case 0:
			default:				
				NotifyExitableListeners();				
				break;
		}
	}
	
	private StringBuilder menu = new StringBuilder(200)
			.append("Enter 1 to Show all users.\n")
	        .append("Enter 2 to Create an user.\n")
	        .append("Enter 3 to Update an user.\n")
	        .append("Enter 4 to Delete an user.\n")
	        .append("Enter 0 to Exit.\n");	
	
	private int getInt() {
		int num = in.nextInt();
		return num;
	}
	
	private void NotifyExitableListeners() {
		for(IExitable ex : exitables) 
			ex.Exit();
	}

	@Override
	public void CreateUser() throws DALException {
		System.out.println("Please enter the following:");
		
		System.out.print("Name:");
		String name = scan.next();
		
		System.out.print("Social sercurity number:");
		String cpr = scan.next();
		
		System.out.print("Role: ");
		String role = scan.next();
		
		System.out.println("this is the data on the added user:");
		System.out.println(user.createUser(name, cpr, role));
	}

	@Override
	public void UpdateUser() throws DALException {
		// TODO Auto-generated method stub
		System.out.println("Not yet implemented");
	}

	@Override
	public void DeleteUser() throws DALException {
		// TODO Auto-generated method stub
		System.out.println("Not yet implemented");
	}
	
	public void Close() {
		in.close();
	}
}
