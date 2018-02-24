import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.UserDAO;
import dto.UserDTO;
import funk.IUser;
import funk.User;
import userinterface.IExitable;
import userinterface.ITextUserInterfaceExtended;
import userinterface.TUI;
import userinterface.TextUserInterface;

public class MainWeightingSystem {
	ExitListener listener = new ExitListener();	
	ITextUserInterfaceExtended textUserInterface = null;
	UserDTO dataAccess = null;
	IUserDAO userDAO;
	IUser user;
	
	public void run() {
		
		dataAccess = new UserDTO();		
		
	try {
		userDAO = new UserDAO();
		user = new User(userDAO);
	} catch (Exception e) {
		System.out.println("Did not load database");
		System.exit(0);
	}
		
//		textUserInterface = new TextUserInterface(dataAccess);
		textUserInterface = new TUI(user);
		
		textUserInterface.AttachExitable(listener);
		
		while(!listener.ToExit()) {
			try {
				textUserInterface.ShowMenu();
			} catch (DALException e) {
				e.printStackTrace();
				for(Throwable t : e.getSuppressed()) {
					System.out.printf("\nSuppressed:  %s ", t.getMessage());
				}
			}finally {

			}
		}
		
		System.out.printf("\n%s\n", "Thank you and goodbye.");
		
	}

}
