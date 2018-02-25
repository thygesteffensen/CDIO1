import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.UserDAO;
import dto.UserDTO;
import funk.IUser;
import funk.User;
import userinterface.ITextUserInterface;
import userinterface.TUI;

public class WeightingSystem {
	IUserDAO userDAO;
	IUser user;
	ITextUserInterface tui;
	
	public void Run() throws DALException {
		userDAO = new UserDAO();
		user = new User(userDAO);
		tui = new TUI(user);
		
		System.out.println("Welcome... \n");
		
		while(true) {
			try {
				tui.ShowMenu();
			} catch (DALException e) {
				System.out.println("Something went wrong.");
				e.printStackTrace();
			}
		}
	}	
}
