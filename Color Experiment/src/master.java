import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class master{
	
	static Dimension screenSize;
	
	static int height;
	static int width;
	
	static BufferedImage image;
	static JFrame frame;
	static JLabel space;
	
	public master(){
		
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		space = new JLabel(new ImageIcon(image));
		
		Graphics g = image.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.dispose();
		
		KeyListener key = new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_R){
					 setRed();
				 }
				 if (e.getKeyCode() == KeyEvent.VK_B){
					 setBlue();
				 }
				 if (e.getKeyCode() == KeyEvent.VK_G){
					 setGreen();
				 }
				 if (e.getKeyCode() == KeyEvent.VK_W){
					 setWhite();
				 }
				 if (e.getKeyCode() == KeyEvent.VK_Q){
					 System.exit(0);
				 }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}};
		
		frame.addKeyListener(key);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		gd.setFullScreenWindow(frame);
		
	}
	
	public void setRed(){
		Graphics g = image.getGraphics();
		g.setColor(Color.RED);
		g.fillRect(width/2, height/2, 2, 1);
		g.dispose();
		space.repaint();
	}
	
	public void setBlue(){
		Graphics g = image.getGraphics();
		g.setColor(Color.BLUE);
		g.fillRect(width/2, height/2, 2, 1);
		g.dispose();
		space.repaint();
	}
	
	public void setGreen(){
		Graphics g = image.getGraphics();
		g.setColor(Color.GREEN);
		g.fillRect(width/2, height/2, 2, 1);
		g.dispose();
		space.repaint();
	}
	
	public void setWhite(){
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(width/2, height/2, 2, 1);
		g.dispose();
		space.repaint();
	}
	
	public static void main(String[] args) {

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		height = screenSize.height;
		width = screenSize.width;
		
		frame = new JFrame();
		
		new master();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(master.space);
		frame.pack();
		
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		frame.getContentPane().setCursor(blankCursor);
		
		frame.setVisible(true);
		
	}

}