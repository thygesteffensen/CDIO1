package functionality;

import java.util.List;

import dataaccess.IUserDAO;
import dataaccess.UserDAO;
import dataaccess.IUserDAO.DALException;
import dto.UserDTO;

public class FunctionalityImpl implements Functionality{
	
	private IUserDAO dataAccess = null;
	
	public FunctionalityImpl(UserDAO dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Override
	public void CreateUser(UserDTO user) throws DALException{
		this.dataAccess.createUser(user);
	}

	
	@Override
	public void UpdateUser(UserDTO user) throws DALException{
		dataAccess.updateUser(user);
	}
	
	@Override
	public void DeleteUser(int id) throws DALException {
		dataAccess.deleteUser(id);
	}
	
	@Override
	public UserDTO GetUserClone(int id) throws DALException {
		return (UserDTO)dataAccess.getUser(id).clone();
	}

	@Override
	public List<UserDTO> GetUsers() throws DALException {
		return dataAccess.getUserList();
	}

}
