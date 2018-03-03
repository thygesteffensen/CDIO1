package controllers;

public class ControllerImpl {
	UserInterface ui = null;
	public ControllerImpl(UserInterface ui) {
		this.ui = ui;		
	}
	
	public void runUI() {
		ui.ShowMenu();
	}
}
