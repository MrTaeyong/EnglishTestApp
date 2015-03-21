package Control;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;

public class LoginController implements ActionListener {


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Component c = (Component) e.getSource();
		if(c instanceof Button){
			Button temp = (Button) c;
			//if(c == del){
			//	id.setText("");
			//	pwd.setText("");
			//}
		}
	}

}
