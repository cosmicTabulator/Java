import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen implements KeyListener{

	JFrame frame;
	static JLabel screen;
	static BufferedImage image;
	
	public Screen(int width, int hieght){
		
		frame = new JFrame();
		frame.setSize(width, hieght);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		image = new BufferedImage(width, hieght, BufferedImage.TYPE_INT_RGB);
		screen = new JLabel(new ImageIcon(image));
		
		Graphics g = image.getGraphics();
		
//		g.drawString("Terrus", 200, 100);
//		g.drawString("v 0.0", 200, 200);
		
		g.dispose();
		
		frame.setContentPane(screen);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}