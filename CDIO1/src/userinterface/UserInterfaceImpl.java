package userinterface;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import codegen.GenPassword;
import codegen.IGenPassword;
import controllers.UserInterface;
import dataaccess.IUserDAO.DALException;
import dto.UserDTO;
import functionality.Functionality;

public class UserInterfaceImpl implements UserInterface{
	private Functionality functionality = null;
	private CustomInputStream inputStream = new CustomInputStream(System.in);
	private List<IExitable> exitables = new ArrayList<>();
	private IGenPassword genPassword = new GenPassword();
	
	private int choice = -1;
	private int id = -1;
	
	public UserInterfaceImpl(Functionality functionality){
		this.functionality = functionality;		
	}
	
	public UserInterfaceImpl(Functionality functionality, IExitable exitable){
		this.functionality = functionality;		
		AttachExitable(exitable);
	}
	
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

	public void AttachExitable(IExitable exitable) {
		exitables.add(exitable);
	}
	
	private void NotifyExitableListeners() {
		for(IExitable ex : exitables) {
			ex.Exit();
		}
	}
	
	@Override
	public void ShowMenu() {
		printMenu();		  
		int choice = getInt();
		switch(choice) {
			case 1:
				try {
					ShowUsers();
					//functionality.ShowUsers();
				} catch (DALException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					CreateUser();
				} catch (DALException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					UpdateUser();
				} catch (DALException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					DeleteUser();
				} catch (DALException e) {
					e.printStackTrace();
				}
				break;
			case 0:
			default:				
				NotifyExitableListeners();				
				CloseInputStream();
				break;
		}

	}
	
	public void ShowUsers() throws DALException {		
		functionality.GetUsers().stream().forEach(UserDTO::Print);
	}
	
	public void DeleteUser() throws DALException {
		
		 	askForUserId();
			getUserId();
		 
			functionality.DeleteUser(id);
	}
	
	private void printMenu() {
		System.out.format("%s%n%s%n%s%n%s%n%s%n", 
				"Enter 1 to Show all users.",
				"Enter 2 to Create an user.",
				"Enter 3 to Update an user.",
				"Enter 4 to Delete an user.",
				"Enter 0 to Exit."
				);		
	}
	
	private int getInt() {
		Scanner in = new Scanner(this.inputStream);
		while(!in.hasNextInt()) {
			System.out.printf("%n%s%n", "Not a number, try again!");
			in.next();
		}
		int num = in.nextInt();
		in.close();
		return num;
	}

	private void CloseInputStream() {
		new Scanner(System.in).close();
	}
	
	private void CreateUser() throws DALException {
		System.out.printf("%n%s%n","Enther new user");
		
		System.out.printf("%n%s","Enther id: ");
		int id = getInt();
		
		System.out.printf("%n%s","Enther name: ");
		String name = getString();
		
		System.out.printf("%n%s","Enther initials: ");
		String initials = getString();
		
		System.out.printf("%n%s","Enther cpr: ");
		String cpr = getString();
		
		String password = genPassword.genPassword();
		
		System.out.printf("%n%s","Enther role: ");
		String role = getString();		
		List<String> roles = new ArrayList<>();		
		roles.add(role);
		
		UserDTO user = new UserDTO(id, name, initials, cpr, password, roles);
		
		System.out.printf("Current user info: %n\t%s %n %n", user.toString());
		
		//the final call to functionality
		functionality.CreateUser(user);
	}
	
	public void UpdateUser() throws DALException{
		
		askForUserId();
		getUserId();
		
		askForChoice();
		getChoice();		 
		
		UserDTO user = functionality.GetUserClone(id);
		
		if(choice == 1) {
			String name = getString();
			user.setName(name);
		}else if(choice == 2) {
			String role = getString();
			user.addRole(role);;
		}else {
			//to do ..
		}

		functionality.UpdateUser(user);
	}
	
	private void getChoice() {
		choice = getInt();		 
	}
	
	private void askForChoice() {
		System.out.format("%s%n%s", 
						   "Enter 1 to change user name or",
						   "enter 2 to add a new role: ");
	}

	private void getUserId() {		
		id = getInt();
	}
	
	private void askForUserId() {
		System.out.printf("%s","Plase enter user id: ");
	}
	
	private String getString() {
		Scanner in = new Scanner(this.inputStream);
		String str = in.nextLine().trim();
		in.close();
		return str;
	}
}
