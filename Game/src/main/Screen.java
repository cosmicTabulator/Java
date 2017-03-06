package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen{

	JFrame frame;
	JLabel pane;
	static BufferedImage image;
	public static Set<Integer> keys = new HashSet<Integer>();
	
	public Screen(int width, int hieght){
		
		frame = new JFrame();
		frame.setSize(width, hieght);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		image = new BufferedImage(width, hieght, BufferedImage.TYPE_INT_RGB);
		pane = new JLabel(new ImageIcon(image));
		
		Graphics g = image.getGraphics();
		
		g.dispose();
		
		KeyListener keylistener = new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				int keyCode = e.getKeyCode();
				if(!keys.contains(keyCode)){
					keys.add(keyCode);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
				int keyCode = e.getKeyCode();
				if(keys.contains(keyCode)){
					keys.remove(keyCode);
				}	
			}
		};
		
		frame.addKeyListener(keylistener);
		frame.setContentPane(pane);
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
