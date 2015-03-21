package Control;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.ref.WeakReference;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
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
		setExample();
	}
	public void setExample()
	{
		for(int i =0; i < probList.get(problemNumber).getExampleCount(); i++)
		{
			this.mainWindow.radioButton[i].setText(probList.get(problemNumber).getExamples(i));;
			this.mainWindow.radioButton[i].setVisible(true);;
		}
		for(int i = probList.get(problemNumber).getExampleCount(); i < 5; i++)
		{
			this.mainWindow.radioButton[i].setVisible(false);;
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Next")){
			
			problemNumber++;
			//앞에 문제를 이전에 풀지 않은 경우
			if(probList.get(problemNumber).getSelected_number() == -1)
			{
			//문제 출력
				mainWindow.textAreaLeft.setText(probList.get(problemNumber).getProblem());
				mainWindow.buttonGroup.clearSelection();
				setExample();
			
				mainWindow.textAreaRight.setText(null);
		
				mainWindow.repaint();
			}
			//푼 경우
			else
			{
				mainWindow.textAreaLeft.setText(probList.get(problemNumber).getProblem());
				mainWindow.buttonGroup.clearSelection();
				setExample();
				mainWindow.radioButton[probList.get(problemNumber).getSelected_number()].setSelected(true);
				
				
				
		
				mainWindow.repaint();
			}
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
				setExample();
				mainWindow.radioButton[probList.get(problemNumber).getSelected_number()].setSelected(true);
				
			}
			mainWindow.repaint();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < 5; i++)
		{
			if(mainWindow.radioButton[i].isSelected())
			{
				mainWindow.textAreaRight.setText(probList.get(problemNumber).getResult(i));
				probList.get(problemNumber).setSelected_number(i);
				break;	
			}
		}
		
	}

}
