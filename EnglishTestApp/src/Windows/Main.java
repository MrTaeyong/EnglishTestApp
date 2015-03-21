package Windows;

import Control.Controller;

public class Main {
	public static void main(String[] args) throws Exception {
//		LoginWindow l_window = new LoginWindow();
		MainWindow window = new MainWindow();
		Controller controller = new Controller(window);
		window.setController(controller);
	}
}
