import dataaccess.UserDAO;
import functionality.FunctionalityImpl;
import controllers.ControllerImpl;
import dataaccess.IUserDAO.DALException;
import userinterface.ITextUserInterfaceExtended;
import userinterface.TextUserInterface;
import userinterface.UserInterfaceImpl;

public class MainWeightingSystem {
	private static ExitListener listener = new ExitListener();	
	private static ITextUserInterfaceExtended textUserInterface = null;
	private static UserDAO dataAccess = null;
	
	public static void main(String[] args) {
		
		try {
			dataAccess = new UserDAO();
		} catch (DALException e) {
			e.printStackTrace();
			for(Throwable t : e.getSuppressed()) {
				System.out.printf("%nSuppressed:  %s ", t.getMessage());
			}
		}		
		
		textUserInterface = new TextUserInterface(dataAccess);
		
		textUserInterface.AttachExitable(listener);
		
		while(!listener.ToExit()) {
			try {
				
				new ControllerImpl(
						new UserInterfaceImpl(
								new FunctionalityImpl(new UserDAO("data.bin")), 
								listener)
						).runUI();
				
				//textUserInterface.ShowMenu();
			} catch (DALException e) {
				e.printStackTrace();
				for(Throwable t : e.getSuppressed()) {
					System.out.printf("%nSuppressed:  %s ", t.getMessage());
				}
			}finally {

			}
		}
		
		System.out.printf("%n%s%n", "Thank you and goodbye.");
		
	}

}
