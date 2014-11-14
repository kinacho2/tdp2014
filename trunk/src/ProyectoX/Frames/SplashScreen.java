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

public class SplashScreen extends JPanel {
	private int duration;
	private JLabel jLabelTitle;
	private URL path;

	public SplashScreen(int d, URL path) {
		duration = d;
		this.path = path;
		showSplash();
	}

	// A simple little method to show a title screen in the center
	// of the screen for the amount of time given in the constructor
	public void showSplash() {
		//this.setLocationByPlatform(true);
		
		 //
		 //JPanel content = (JPanel) getContentPane();
		setFocusable(false);
		 setBackground(new java.awt.Color(0,0,0));
		 setLayout(null);
		
		 JLabel copyrt = new JLabel("", JLabel.CENTER);
		 copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		 copyrt.setForeground(new java.awt.Color(255,42,00));
		 copyrt.setText("Chaos Wind");
		 copyrt.setBounds(18, 405, 464, 43);
		 add(copyrt);

		 
		 jLabelTitle = new JLabel();
		 jLabelTitle.setText("Chaos Wind");
		 jLabelTitle.setFont(new java.awt.Font("Ubuntu",0,72));
		 jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		 jLabelTitle.setForeground(new java.awt.Color(255,45,0));
		 jLabelTitle.setBounds(0, 298, 437, 157);
		 add(jLabelTitle);
		
		 JLabel label = new JLabel(new ImageIcon(path));
		 add(label);
		 label.setBounds(0, 0, 800, 600);
		 
		 int width = 800;
		 int height = 600;
		 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		 int x = (screen.width - width)/2;
		 int y = (screen.height - height)/2;
		 this.setBounds(x, y, width, height);
		 

		 // Display it
		 setVisible(true);
		
		 // Wait a little while, maybe while loading resources
		 
		
		 //setVisible(false);
		 setBackground(new java.awt.Color(0,0,0));
		 //this.setOpacity(0.0f);
	}

}