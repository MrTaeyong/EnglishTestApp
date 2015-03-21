package Control;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.ref.WeakReference;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Windows.MainWindow;
import DataType.InsertionProb;
import DataType.Problem;

public class Controller implements ActionListener,ItemListener {
	List<Problem> probList ;
	int problemNumber = 0;
	MainWindow mainWindow;
	public Controller(MainWindow mainWindow) throws Exception {
		// TODO Auto-generated constructor stub
		this.mainWindow = mainWindow;
		ProblemParser pp = new ProblemParser();
		probList = pp.parse();
		this.mainWindow.textAreaLeft.setText(probList.get(problemNumber).getProblem());
		for(int i =0; i < 5; i++)
		{
			this.mainWindow.radioButton[i].setText(probList.get(problemNumber).getExamples(i));;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Next")){
			System.out.println("Next");
			problemNumber++;
			mainWindow.textAreaLeft.setText(probList.get(problemNumber).getProblem());
		}
		else if(e.getActionCommand().equals("Pre")){
			System.out.println("pre");
			if(problemNumber == 0)
			{
				JOptionPane underzero = new JOptionPane();
				underzero.showMessageDialog(null, "첫문제입니다.");
			}
			else
			{
				problemNumber--;
				mainWindow.textAreaLeft.setText(probList.get(problemNumber).getProblem());
			}
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String result;
		for(int i = 0; i < 5; i++)
		{
			if(mainWindow.radioButton[i].isSelected())
			{
				
				result = probList.get(problemNumber).getResult(i);
				mainWindow.textAreaRight.setText(result);
				break;
				
			}
		}
		
	}
	
}
