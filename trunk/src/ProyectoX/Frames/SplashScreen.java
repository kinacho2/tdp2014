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
/**
 * Codigo construido en parte utilizando la clase SplashScreen mostrada en la practica de la materia
 * contiene una imagen creada a partir de un URL pasado por parametro en su constructor
 * su funcion es dibujar la imagen en la pantalla y permanecer durante un tiempo determinado
 */
public class SplashScreen extends JWindow {
	private int duration;
	private URL path;
	private Image image;
	
	/**
	 * Constructor de la clase SplashScreen
	 * @param d entero que indica la duracion del SplashScreen
	 * @param path la imagen que se mostrara en el Splash
	 */
	public SplashScreen(int d, URL path) {
		duration = d;
		this.path = path;
		
	}

	/**
	 * Inicializa la Screen y permanece en pantalla el tiempo indicado
	 */
	public void showSplash() {
		this.setLocationByPlatform(true);
		
		int width = 800;
		int height = 600;
		getContentPane().setBackground(new java.awt.Color(0,0,0));
		setLayout(null);
		
		image = (new ImageIcon(path).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width)/2;
		int y = (screen.height - height)/2;
		this.setBounds(x, y, width, height);
		
		setVisible(true);
		
			
		repaint();
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
		
		
		setVisible(false);

	}
	
	/**
	 * redefine  paint(Graphics g) de la clase Window
	 * dibuja en el JWindows la imagen que este contiene
	 * @param g 
	 */
	
	public synchronized void paint(Graphics g) {
		  super.paint(g);
	      Graphics2D g2d = (Graphics2D) g;
	      g2d.drawImage(image, 0, 0, this);
	      Toolkit.getDefaultToolkit().sync();
	      g.dispose();
	}

}