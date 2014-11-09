package ProyectoX.Disparos;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Explosiones.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Sound.Sonido;


public class Disparo {

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Basico/Basico.png");
	private static final URL explode = Disparo.class.getClassLoader().getResource("ProyectoX/img/Explosiones/pequena.gif");
	protected String sonido;
	
	protected int x, y;
    protected Image image;
    protected ImageIcon explosion;
    private boolean visible;

    protected int velocidad;
    
    //largo y ancho de la image
  	protected int height;
  	protected int width;
	protected int damage;
	protected double dx;
	protected double dy;

    private int maxHeight = -height*2;
    private int minHeight = 600 + height;
	private int maxWidth = 800 + width;
	private int minWidth = -width*2;
    
    
    public Disparo(int x, int y, double dx, double dy, int missileSpeed) {

    	height = 10;
		width = 10;
    	this.dx = dx;
    	this.dy = dy;
    	
        ImageIcon ii = new ImageIcon(url);
        image = ii.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
       
        
        ii = new ImageIcon(explode);
        explosion = new ImageIcon(ii.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        
        visible = true;
        this.x = x - width/2;
        this.y = y - height/2;
        damage = 10;
        velocidad = missileSpeed;
        sonido = "/ProyectoX/sounds/mul.mp3";
        
    }


    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible ;
    }

    public void move() {
        y -= dy * velocidad;
        x += dx * velocidad;
        verificarColisionBorde();
    }
    
    protected void verificarColisionBorde(){
    	if (y < maxHeight || y >  minHeight || x < minWidth || x > maxWidth)
            setVisible();
    }
    
    // Determina si el disparo colision� con una nave
    public synchronized boolean colision(Nave nave) {
    	boolean A,B,C,D,E,F,G,H, fColision; 
		
		A = x >= nave.getX();
		B = x <= (nave.getX() + nave.getWidth());
		C = y >= nave.getY();
		D = y <= (nave.getY() + nave.getHeight());
		E = (x + width) >= nave.getX();
		F = (x + width) <= (nave.getX() + nave.getWidth());
		G = (y + height) >= nave.getY();
		H = (y + height) <= (nave.getY() + nave.getHeight());
		
		// funcion de colicion que verifica si alguno de los 4 puntos del borde del objeto disparo intersectan con area del objeto pasado por parametro
		fColision = (A && B || E && F) && (C && D || G && H) ||  !A && !F && ( !H && D || G && H) ||  !C &&  !H && (B &&  !F ||  !A && E);
		return  nave.getVisible() && fColision;
	}
    
    // Establece que el disparo no est� visible en la pantalla
    public void setVisible() {
    	visible = false;
    }

    // devuelve el damage del disparo
	public int getDamage() {
		return damage;
	}
	
	// clona el tipo disparo para poder disparar en serie
	public Disparo[] cloneNivel() {
		
		// establece la cantidad de disapros por nivel
		Disparo[] d = new Disparo[1];
		d[0] = new Disparo(x, y, dx, dy, velocidad);
		return d ;
	}
	
	public Explosion newExplosion(int altura) {
		return new Explosion(x + width/2, y + height / 2, explosion, width, height);
	}
	
	public void setPosicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Disparo nextLevel(){
		return new Disparo(x,y,dx,dy,velocidad);
	}


	public String getSound() {
		//return new Sonido(sonido,false);
		return sonido;
	}
	
}

