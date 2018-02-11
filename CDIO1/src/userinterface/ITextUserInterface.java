package userinterface;

import dal.IUserDAO.DALException;

public interface ITextUserInterface {

	void ShowMenu() throws DALException;

	boolean ToExit();

	void ShowUsers() ;

	void CreateUser() throws DALException;

	void UpdateUser() throws DALException;
	
	void DeleteUser();

}
