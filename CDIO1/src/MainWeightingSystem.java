import dal.IUserDAO.DALException;
import dal.UserDAO;
import userinterface.ITextUserInterface;
import userinterface.TextUserInterface;

public class MainWeightingSystem {
	
	public static void main(String[] args) {

		ITextUserInterface tui = new TextUserInterface(new UserDAO());
		
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
