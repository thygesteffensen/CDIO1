package functionality;

import java.util.List;

import dataaccess.IUserDAO.DALException;
import dto.UserDTO;

public interface Functionality {
	List<UserDTO> GetUsers() throws DALException;
	
	//void ShowMenu() throws DALException;

	void CreateUser(UserDTO user) throws DALException;

	void UpdateUser(UserDTO user) throws DALException;
	
	void DeleteUser(int id) throws DALException;
	
	UserDTO GetUserClone(int id) throws DALException;
}
