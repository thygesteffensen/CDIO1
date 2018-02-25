package funk;

import dal.IUserDAO.DALException;

public interface IUser {
	String showUsers();
	String createUser(String name, String cpr, String role) throws DALException; // returns info, of the newly added user 
	String updateUser(int userID, int type, String change) throws DALException; // returns info, of the recently changed user
	void deleteUser(int userID) throws DALException;
}
