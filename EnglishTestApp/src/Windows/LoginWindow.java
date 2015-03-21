package Windows;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {
	private JLabel l_label, i_label, p_label;
	private JTextField id;
	private JPasswordField pwd;
	public Button login;
	
	public LoginWindow(){
		super("로그인 화면");
		
		setLayout(new BorderLayout());
		JPanel tPanel = new JPanel();
		l_label = new JLabel("로그인");
		tPanel.add(l_label);
	        
		JPanel mainPanel = new JPanel();
	        
		JPanel idPanel = new JPanel();
		i_label = new JLabel("아 이 디: ");
		id = new JTextField(15);
		idPanel.add(i_label);
		idPanel.add(id);
	        
		JPanel pwPanel = new JPanel();
		p_label = new JLabel("비밀번호: ");
		pwd = new JPasswordField(15);
		pwPanel.add(p_label);
		pwPanel.add(pwd);
		mainPanel.add(idPanel);
		mainPanel.add(pwPanel);
	        
		JPanel btPanel = new JPanel();
		login = new Button("login");
		btPanel.add(login);
	        
		add("North", tPanel);
		add("Center", mainPanel);
		add("South", btPanel);
	               
		setSize(300,200);
		setVisible(true);        
	}    
}
