import dal.IUserDAO.DALException;
import dal.UserDAO;
import userinterface.IExitable;
import userinterface.ITextUserInterfaceExtended;
import userinterface.TextUserInterface;

public class MainWeightingSystem {
	
	public static void main(String[] args) {
		UserDAO dataAccess = null;
		ITextUserInterfaceExtended tui = new TextUserInterface(dataAccess);
		ExitListener listener = new ExitListener();
		
		try {
			dataAccess = new UserDAO();
		} catch (DALException e) {
			e.printStackTrace();
			for(Throwable t : e.getSuppressed()) {
				System.out.printf("\nSuppressed:  %s ", t.getMessage());
			}
		}		
		
		
		tui.AttachExitable(listener);
		
		//boolean exit = false;
		
		while(!listener.ToExit()) {//exit) {
			try {
				tui.ShowMenu();
			} catch (DALException e) {
				e.printStackTrace();
				for(Throwable t : e.getSuppressed()) {
					System.out.printf("\nSuppressed:  %s ", t.getMessage());
				}
			}finally {
				//exit = tui.ToExit();				
			}
		}
		
		System.out.printf("\n%s\n", "Thank you and goodbye.");
		
	}

}
