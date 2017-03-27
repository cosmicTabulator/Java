
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
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

	public JFrame frame;
	public JLabel pane;
	public BufferedImage image;
	public Set<Integer> keys = new HashSet<Integer>();
	
	public Screen(int width, int hieght){
		
		
		//On instantiation, create a new window of given size
		frame = new JFrame();
		frame.setSize(width, hieght);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		//Create a dynamic image and attach it to the window
		image = new BufferedImage(width, hieght, BufferedImage.TYPE_INT_RGB);
		pane = new JLabel(new ImageIcon(image));
		
		image = toCompatibleImage(image);
		
		Graphics2D g = image.createGraphics();
		
		g.dispose();
		
		//Create a key listener, attach it to the window
		KeyListener keylistener = new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				//When a key is pressed, add it to our set of pressed keys
				int keyCode = e.getKeyCode();
				if(!keys.contains(keyCode)){
					keys.add(keyCode);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
				//When a key is released, remove it from our set of pressed keys
				int keyCode = e.getKeyCode();
				if(keys.contains(keyCode)){
					keys.remove(keyCode);
				}	
			}
		};
		
		//Finalizes our window
		frame.addKeyListener(keylistener);
		frame.setContentPane(pane);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	//Optimizes our attached image to the system's best format
	private BufferedImage toCompatibleImage(BufferedImage image)
	{
	    // obtain the current system graphical settings
	    GraphicsConfiguration gfx_config = GraphicsEnvironment.
	        getLocalGraphicsEnvironment().getDefaultScreenDevice().
	        getDefaultConfiguration();

	    /*
	     * if image is already compatible and optimized for current system 
	     * settings, simply return it
	     */
	    if (image.getColorModel().equals(gfx_config.getColorModel()))
	        return image;

	    // image is not optimized, so create a new image that is
	    BufferedImage new_image = gfx_config.createCompatibleImage(
	            image.getWidth(), image.getHeight(), image.getTransparency());

	    // get the graphics context of the new image to draw the old image on
	    Graphics2D g2d = (Graphics2D) new_image.getGraphics();

	    // actually draw the image and dispose of context no longer needed
	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();

	    // return the new optimized image
	    return new_image; 
	}
	
}
