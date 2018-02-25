package userinterface;

import dal.IUserDAO.DALException;

public interface ITextUserInterface {

	void ShowUsers();
	
	void ShowMenu() throws DALException;

	void CreateUser() throws DALException;

	void UpdateUser() throws DALException;
	
	void DeleteUser() throws DALException;

}
