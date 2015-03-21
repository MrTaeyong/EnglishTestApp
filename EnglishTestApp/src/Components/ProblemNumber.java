package Components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

public class ProblemNumber extends JLabel {
	int num;
		
	public ProblemNumber(int num) {
		this(num, 20, 20, Color.WHITE);
	}
	
	public ProblemNumber(int num, int width, int height, Color color) {
		this.num = num;
		this.setText(String.valueOf(num));
		this.setPreferredSize(new Dimension(width, height));
		this.setOpaque(true);
		this.setBackground(color);
		this.setHorizontalAlignment(JLabel.CENTER);
	}
}
