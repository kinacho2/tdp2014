package Proyecto2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Nave{
	
	
	 private double rotacion = 0.0;
	   
	protected int vida, x, y, dx, dy;
	//imagen de la nave
	protected Image image;
	//url de la image
	
	private ArrayList missiles;
	
	//largo y ancho de la image
	
	protected int height;
	
	protected int width;
	private int dis;
	private int longDis;
	
	protected int velocidad;
	protected Jugador jugador;
	
	
	// basado en el codigo http://zetcode.com/tutorials/javagamestutorial/movingsprites/ para mover el jugador
	public Nave(int vel, int xx, int yy, ImageIcon icon, int w, int h){
		height = h;
		width = w;
		
		//ajusta la imagen al tamaño de los parametros w = ancho y h = alto
		ImageIcon ii = new ImageIcon(icon.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		image = ii.getImage();
		missiles = new ArrayList();
		x = xx;
		y = yy;
		dis=0;
		longDis=7;
		velocidad=vel;
	}
	public void setJugador(Jugador jugador){
		this.jugador=jugador;
	}
	
	 public void move() {
	        x += dx;
	        y += dy;
    }
	 
	public int getX() {
	        return x;
    }

    public int getY() {
        return y;
    }
     
    
    public Image getImage() {
		return image;
	}
    
    /*
     * aumenta en 1 el delay de los disparos
     */
    
    public void setDis(){
    	dis= (dis+1) % longDis;
    }
    /*
     * setea el delay en 0
     */
    public void setDisCero(){
    	dis=0;
    }
    
    protected boolean puedeDisparar(){
    	return dis%longDis==0;
    }
    
    public ArrayList getMissiles() {
        return missiles;
    }
    
    
    
    public void disparar() {
    	if(puedeDisparar())
    		missiles.add(new Disparo(x + width/2 , y ));
    }

    
    
    public Dimension getPreferredSize() 
    {
        return new Dimension(width, height);
    }
 
 
    public void paint(Graphics g) 
    {
        
    }
     
    public double getRotacion() 
    {
        return this.rotacion;
    }
     
    public void setRotacion(double rotacion) 
    {
        this.rotacion = rotacion;
    }
    public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}


}
