package Windows;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Components.ProblemNumber;
import Components.Timer;
import Control.Controller;

public class MainWindow extends JFrame {
	private final static int NUMBER_OF_EXAMPLES = 5; 
	
	public JEditorPane textAreaLeft;
	public JEditorPane textAreaRight;
	public JPanel panelLeftInner;
	public Button btnPrev;
	public Button btnNext;
	public List<JLabel> numList;
	public Controller controller;
	public JRadioButton[] radioButton;
	public MainWindow() {
		setBounds(0, 0, 500, 500);
		setMinimumSize(new Dimension(1500, 1000));
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// End of Main Window
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		// End of Menubar setting.
		
		numList = new ArrayList<JLabel>();
		
		JPanel panelNorth = new JPanel();
		Timer timer = new Timer();
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		btnPrev = new Button("Pre");
		btnPrev.setPreferredSize(new Dimension(100, 20));
		btnNext = new Button("Next");
		btnNext.setPreferredSize(new Dimension(100, 20));
		

		// End of North Panel
		
		JPanel panelSouth = new JPanel();
		panelSouth.add("West", btnPrev);
		{
			JLabel a = new ProblemNumber(1);
			a.setBackground(Color.GREEN);
			numList.add(a);
			for(int i = 2; i <= 20; i++)
				numList.add(new ProblemNumber(i));
			
			for(JLabel temp : numList) {
				panelSouth.add(temp);
			}
		}
		panelSouth.add("East", btnNext);
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		// End of South Panel
		
		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(0, 2, 0, 0));
		
		JScrollPane jScrollLeft = new JScrollPane();
		JPanel panelLeft = new JPanel();
		panelLeftInner = new JPanel();
		textAreaLeft = new JEditorPane("text/html", "");
		//textarea.setText("Here is some <b>bold text</b>");

		textAreaLeft.setEditable(false);
		panelLeft.setLayout(new GridLayout(2, 1));
		panelLeft.add(jScrollLeft);
		panelLeftInner.setLayout(new GridLayout(NUMBER_OF_EXAMPLES, 1));
		panelLeft.add(panelLeftInner);
		jScrollLeft.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollLeft.setViewportView(textAreaLeft);
		panelCenter.add(panelLeft);
		
		
		radioButton = new JRadioButton[NUMBER_OF_EXAMPLES];
		ButtonGroup buttonGroup = new ButtonGroup();
		for(int i = 0; i < NUMBER_OF_EXAMPLES; i++) {
			radioButton[i] = new JRadioButton();
			radioButton[i].setHorizontalAlignment(JRadioButton.CENTER);
			radioButton[i].setText(String.valueOf(i));
			radioButton[i].setName(Integer.toString(i));
			buttonGroup.add(radioButton[i]);
			panelLeftInner.add(radioButton[i]);
			
		}
		
		JScrollPane jScrollRight = new JScrollPane();
		textAreaRight = new JEditorPane("text/html", "");
		textAreaRight.setEditable(false);
		jScrollRight.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollRight.setViewportView(textAreaRight);
		panelCenter.add(jScrollRight);
		
		setVisible(true);
	}
	public void setController(Controller controller){
		this.controller = controller;
		btnNext.addActionListener(controller);
		btnPrev.addActionListener(controller);
		for(int i = 0; i < NUMBER_OF_EXAMPLES; i++)
		{
			radioButton[i].addItemListener(controller);
		}
	}
	
}
