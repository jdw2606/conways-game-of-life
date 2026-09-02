
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Canvas extends JFrame{
	MyCanvas canvas;
	JButton nextGen;
	JButton automaticGen;
	
	Canvas() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		nextGen = new JButton("Next Generation");
		automaticGen = new JButton("Auto");
		canvas = new MyCanvas(nextGen, automaticGen);
		
		this.add(nextGen, BorderLayout.SOUTH);
		this.add(automaticGen, BorderLayout.NORTH);
		this.add(canvas, BorderLayout.CENTER);
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


}
