package userinterface;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.UserDAO;
import dto.UserDTO;
import funk.IUser;
import funk.User;

public class TUI implements ITextUserInterface {
	private List<IExitable> exitables = new ArrayList<>();
	private IUser user;
	private CustomInputStream inputStream = new CustomInputStream(System.in);
	

	public TUI(IUser user) {
		this.user = user;
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
			System.exit(0);
		default:				
			NotifyExitableListeners();
			break;
		}
	}
	
	@Override
	public void ShowUsers() {
		System.out.println(user.showUsers());
	}

	@Override
	public void CreateUser() throws DALException {
		System.out.println("Please enter the following:");
		
		System.out.print("Name:");
		String name = getString();
		
		System.out.print("Social sercurity number:");
		String cpr = getString();

		System.out.print("Role: ");
		String role = getString();
		
		System.out.printf("\n" + "This is the data on the added user: %s \n\n", 
				user.createUser(name, cpr, role));
		
	}

	@Override
	public void UpdateUser() throws DALException {
		System.out.printf("Which user do you wish to update? ");
		int userID = getInt();
		
		// Printing 
		System.out.printf("%s", type.toString());
		int type = getInt();
		
		System.out.print("Enter change: ");
		String change = getString();
		
		System.out.printf("The following user is changed %d. There was a change in %d which were %s \n", userID, type, change);
		
		try {
			user.updateUser(userID, type, change);
		} catch (DALException e) {
			System.err.println("An error happend while updating user. The user have not been updated.");
			e.printStackTrace();
		}
	}

	@Override
	public void DeleteUser() throws DALException {
		System.out.print("Enter the user id, on the user you want to delete: ");

		try {
			user.deleteUser(getInt());			
		} catch (DALException e) {
			System.err.println("The following user could not be deleted");
			e.printStackTrace();
		}
	}
	
	/* ****************************************************************
	 *                        HELPER FUNCTIONS
	 * ****************************************************************
	 */
	
	private StringBuilder menu = new StringBuilder(200)
			.append("Enter 1 to Show all users.\n")
			.append("Enter 2 to Create an user.\n")
			.append("Enter 3 to Update an user.\n")
			.append("Enter 4 to Delete an user.\n")
			.append("Enter 0 to Exit.\n");	
	
	private StringBuilder type = new StringBuilder(200)
			.append("Enter 1 to update name.\n")
			.append("Enter 2 to update roles.\n");
	
	private int getInt() {
		Scanner in = new Scanner(this.inputStream);
		int num = in.nextInt();
		in.close();
		return num;
	}
	
	private String getString() {
		Scanner in = new Scanner(this.inputStream);
		String str = in.nextLine().trim();
		in.close();
		return str;
	}
	
	private void NotifyExitableListeners() {
		for(IExitable ex : exitables) 
			ex.Exit();
	}
	
	/* ****************************************************************
	 *                        READING INPUT
	 * ****************************************************************
	 */
	
		//work around to keep System.in open
		public class CustomInputStream extends FilterInputStream{
			public CustomInputStream(InputStream inputStream) {
				super(inputStream);
			}		
			@Override 
			public void close() throws IOException{
				//System.in won't be closed 
			}
		}
}
