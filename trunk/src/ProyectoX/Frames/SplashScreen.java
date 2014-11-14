package ProyectoX.Frames;

//SplashScreen.java
//A simple application to show a title screen in the center of the screen
//for the amount of time given in the constructor.  This class includes
//a sample main() method to test the splash screen, but it's meant for use
//with other applications.
//

import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class SplashScreen extends JWindow {
	private int duration;
	private JLabel jLabelTitle;
	private URL path;
	private Image image;

	public SplashScreen(int d, URL path) {
		duration = d;
		this.path = path;
		
	}

	// A simple little method to show a title screen in the center
	// of the screen for the amount of time given in the constructor
	public void showSplash() {
		this.setLocationByPlatform(true);
		

		
		int width = 800;
		int height = 600;
		setBackground(new java.awt.Color(0,0,0));
		setLayout(null);
		
		JLabel copyrt = new JLabel("", JLabel.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		copyrt.setForeground(new java.awt.Color(255,42,00));
		copyrt.setText("Chaos Wind");
		copyrt.setBounds(18, 405, 464, 43);
		//add(copyrt);

		 
		jLabelTitle = new JLabel();
		jLabelTitle.setText("Chaos Wind");
		jLabelTitle.setFont(new java.awt.Font("Ubuntu",0,72));
		jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTitle.setForeground(new java.awt.Color(255,45,0));
		jLabelTitle.setBounds(0, 298, 437, 157);
		//add(jLabelTitle);
		
		image = (new ImageIcon(path).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		 
		 
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width)/2;
		int y = (screen.height - height)/2;
		this.setBounds(x, y, width, height);
		
		setVisible(true);
		long init = System.currentTimeMillis();
		while(System.currentTimeMillis() - init < duration){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			
		}
		
		setVisible(false);

	}
	
	public synchronized void paint(Graphics g) {
		 

		  super.paint(g);

	      Graphics2D g2d = (Graphics2D) g;
	        
	      g2d.drawImage(image, 0, 0, this);

	      Toolkit.getDefaultToolkit().sync();
	      g.dispose();
	}

}