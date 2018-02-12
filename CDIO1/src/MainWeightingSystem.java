import dal.IUserDAO.DALException;
import dal.UserDAO;
import userinterface.ITextUserInterfaceExtended;
import userinterface.TextUserInterface;

public class MainWeightingSystem {
	
	public static void main(String[] args) {
		UserDAO dataAccess = null;
		
		try {
			dataAccess = new UserDAO();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		ITextUserInterfaceExtended tui = new TextUserInterface(dataAccess);
		
		boolean exit = false;
		
		while(!exit) {
			try {
				tui.ShowMenu();
			} catch (DALException e) {
				e.printStackTrace();
			}finally {
				exit = tui.ToExit();				
			}
		}
		
		System.out.printf("\n%s\n", "Thank you and goodbye.");
		
	}

}
