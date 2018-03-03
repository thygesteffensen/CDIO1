package dataaccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dataaccess.UserStore;
import dataaccess.IUserDAO;
import dto.UserDTO;

public class UserDAO implements IUserDAO {
	private final String FILE_NAME; 
	private UserStore store = new UserStore();
	private List<UserDTO> users  = new ArrayList<>(); 
	
	public UserDAO() throws DALException {
		this("data.bin");		
	}
	
	public UserDAO(String FILE_NAME) throws DALException {		
		this.FILE_NAME = FILE_NAME;
		
		reloadStore();
	}
	
	private void reloadStore() throws DALException {
		setStore( loadStore() );
		setUsers( store.getUsers() );
	}

	private void setStore(UserStore store) {
		this.store = store;
	}	
	private void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	
	private UserStore loadStore() throws DALException {
        UserStore userStore = new UserStore();
        ObjectInputStream oIS = null;
        try {
            FileInputStream fIS = new FileInputStream(FILE_NAME);
            oIS = new ObjectInputStream(fIS);
            Object inObj = oIS.readObject();
            if (inObj instanceof UserStore){
                userStore = (UserStore) inObj;
            } else {
                throw new DALException("Wrong object in file");
            }
        } catch (FileNotFoundException e) {
            //No problem - just returning empty userstore
        } catch (IOException e) {
            throw new DALException("Error while reading disk!", e);
        } catch (ClassNotFoundException e) {
            throw new DALException("Error while reading file - Class not found!", e);
        } finally {
            if (oIS!=null){
                try {
                    oIS.close();
                } catch (IOException e) {
                    throw new DALException("Error closing pObjectStream!", e);
                }
            }
        }
        return userStore;
    }

	@Override
	public UserDTO getUser(int id) throws DALException { //I might want to throw UserNotFoundException if user not found
		UserDTO user = new UserDTO();
		for(UserDTO u : users) {
			if(u.getId() == id) {
				user = u;				
			}
		}
		return user;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		return users;
	}

	@Override
	public void createUser(UserDTO user) throws DALException { //I might want to throw UserNotCreatedException if error
		store.addUser(user);
		saveUsers(store);
	}
	
	private void saveUsers(UserStore users) throws DALException {
        ObjectOutputStream oOS =null;
        try{
            FileOutputStream fOS = new FileOutputStream(FILE_NAME);
            oOS = new ObjectOutputStream(fOS);
            oOS.writeObject(users);
        }catch(FileNotFoundException e){
            throw new DALException("Error locating file", e);
        }catch (IOException e){
            throw new DALException("Error writing to disk", e);
        }finally{
            if (oOS!=null) {
                try {
                    oOS.close();
                } catch (IOException e) {
                    throw new DALException("Unable to close ObjectStream", e);
                }
            }
        }    
     }


	@Override
	public void updateUser(UserDTO user) throws DALException {
		List<UserDTO> users = store.getUsers();
		for(UserDTO u : users) {
			if(u.getId() == user.getId()) {
				u.Update(user);
				break;
			}
		}
		
		saveUsers(store);
		
		reloadStore();
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		List<UserDTO> users = store.getUsers();
		
		Iterator<UserDTO> it = users.iterator();
		while(it.hasNext()) {
			if(it.next().hasId(userId)) {
				it.remove();
				break;
			}
		}
		
		saveUsers(store);
		
		reloadStore();
	}
	
}
