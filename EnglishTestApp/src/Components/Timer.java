package Components;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Timer extends JLabel implements Runnable{
	int min, sec;

	public Timer() {
		sec = 00;
		min =  60;
		this.setText(min + " : " + sec);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			this.repaint();
			try {
				Thread.sleep(1000);
				if(sec == 0)
				{
					if(min == 0)
						break;
					else
					{
						min--;
						sec = 59;
					}
				
				}
				else
				{	
					sec--;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setText(min + " : " + sec);
		}
		JOptionPane timeover = new JOptionPane();
		timeover.showMessageDialog(null,"시간이 다되었습니다.");
		
	}

}
