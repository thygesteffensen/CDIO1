import userinterface.IExitable;

public class ExitListener implements IExitable{
	private boolean exit = false;
	
	@Override
	public void Exit() {
		exit = true;
	}
	
	public boolean ToExit() {
		return exit;
	}
}
