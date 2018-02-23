package userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dal.IUserDAO.DALException;
import funk.IUser;

public class TUI implements ITextUserInterfaceExtended {
	private List<IExitable> exitables = new ArrayList<>();
	private IUser user;
	
	public TUI(IUser user) {
		this.user = user;
	}
	
	@Override
	public void ShowUsers() {
		System.out.println(user.showUsers());
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
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		in.close();
		return num;
	}
	
	private void NotifyExitableListeners() {
		for(IExitable ex : exitables) 
			ex.Exit();
	}

	@Override
	public void CreateUser() throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void UpdateUser() throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void DeleteUser() throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void AttachExitable(IExitable exitable) {
		// TODO Auto-generated method stub

	}

}
