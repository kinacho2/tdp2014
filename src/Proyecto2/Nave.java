package Proyecto2;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Nave {
	
	protected Jugador jugador;
	protected int vida;
	protected Mapa mapa;

	//variables para la imagen
	protected int height;
	protected int width;
	private double rotacion = 0.0;
	protected Image image;
	protected Image explosion;
	protected boolean visible;


	// variables para disparar
	private int dis;
	private int longDis;
	protected int velocidadMisil;

	// Para la Posicion
	protected int velocidad;
	protected int x, y;
	protected double dx, dy;
	protected int posInicialX;

    protected int maxHeight;
    protected int minHeight;
    protected int maxWidth;
    protected int minWidth;
	
	
	// basado en el codigo http://zetcode.com/tutorials/javagamestutorial/movingsprites/ para mover el jugador
	public Nave(int vida, int vel, int xx, int yy, ImageIcon icon, ImageIcon explosion, int w, int h){
		height = h;
		width = w;
		this.x = xx;
		this.y = yy;
		this.posInicialX = x;
		//ajusta la imagen al tamaņo de los parametros w = ancho y h = alto
		ImageIcon ii = new ImageIcon(icon.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		image = ii.getImage();
		this.explosion = explosion.getImage();
		
		
		this.dis=0;
		this.longDis=7;
		
		this.velocidad=vel;
		this.vida=vida;
		visible = true;
		
		maxHeight = -height*2;
		minHeight = 600 + height;
		maxWidth = 800 - width;
		minWidth = -width*2;
		
	}
	public void setJugador(Jugador jugador){
		this.jugador=jugador;
	}
	
	 public abstract void move();
	 
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
    
    public abstract void disparar();

    public Dimension getPreferredSize() 
    {
        return new Dimension(width, height);
    }
 
    public double getRotacion() 
    {
        return this.rotacion;
    }
     
    public void setRotacion() 
    {
    	double mod = Math.sqrt(dx*dx+dy*dy);
		double cos = Math.abs(Math.acos(dx/mod));
		double sin = Math.abs(Math.asin(dx/mod));
		double pi = Math.PI;
		
		//funcion de rotacion de imagen
		if(dx==0)
			if(dy<0)
				rotacion = 0;
			else
				rotacion = (pi);
		else if(dx>0)
			if (dy==0)
				rotacion = (pi/2);
			else if(dy<0)
				rotacion = (sin);
			else
				rotacion = (pi/2+cos);	
		else if (dy==0)
			rotacion = ((3/2)*pi);
				
		else if(dy>0)
			rotacion = (pi+sin);
		else
			rotacion = (pi/2-cos);
    }
    
    public int getHeight() {
		return height;
	}
    
	public int getWidth() {
		return width;
	}

	 public boolean colision(Nave nave) {
		 return (x >= nave.getX() &&  x <= (nave.getX() + nave.getWidth()) && y >= nave.getY()  && y <= (nave.getY() + nave.getHeight())) ||
				((x + width) >= nave.getX() &&  (x + width) <= (nave.getX() + nave.getWidth()) && y >= nave.getY()  && y <= (nave.getY() + nave.getHeight())) ||
				(x >= nave.getX() &&  x <= (nave.getX() + nave.getWidth()) && (y + height) >= nave.getY()  && (y + height) <= (nave.getY() + nave.getHeight())) ||
				((x + width) >= nave.getX() &&  (x + width) <= (nave.getX() + nave.getWidth()) && (y + height) >= nave.getY()  && (y + height) <= (nave.getY() + nave.getHeight()));
	}
	
	public void setVisible(){
		visible = false;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void setMapa(Mapa map){
		mapa = map;
	}
	
	public void setVida(int vd){
		vida -= vd;
		if(vida <= 0){
			setVisible();
		}
	}
	
	public int getDaņoColision() {
		return vida;
	}
	
	public abstract Explosion getExplosion();
	
	protected boolean fueraDePantalla(){
		return (x < minWidth || x > maxWidth || y > minHeight || y < maxHeight);
	}
	
	protected void setFrecuenciaDeDisparo(int init, int longitud){
		dis = init;
		longDis = longitud;
	}
	

	public int getVelocidad() {
		return velocidad;
	}
}

