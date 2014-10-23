package Proyecto2.Explosiones_Disparos;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import Proyecto2.Naves.Nave;


public class Disparo {

	private static final URL url = Disparo.class.getClassLoader().getResource("Proyecto2/img/Disparos/Basico/Basico.png");
	private static final URL explode = Disparo.class.getClassLoader().getResource("Proyecto2/img/Explosiones/pequena.gif");
	
	private int x, y;
    private Image image;
    private ImageIcon explosion;
    private boolean visible;

    private int velocidad;
    
    //largo y ancho de la image
  	protected int height;
  	protected int width;
	private int damage;
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
        ii = new ImageIcon(ii.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        image = ii.getImage();
        
        ii = new ImageIcon(explode);
        explosion = new ImageIcon(ii.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        
        visible = true;
        this.x = x - width/2;
        this.y = y - height;
        damage = 1;
        velocidad = missileSpeed;
        
        
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
       if (y < maxHeight || y >  minHeight || x < minWidth || x > maxWidth)
            setVisible();
    }
    
    // Determina si el disparo colisionó con una nave
    public boolean colision(Nave nave) {
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
		fColision = (A && B || E && F) && (C && D || G && H);
		return  nave.getVisible() && fColision;
	}
    
    // Establece que el disparo no está visible en la pantalla
    public void setVisible() {
    	visible = false;
    }

    // devuelve el damage del disparo
	public int getDamage() {
		return damage;
	}
	
	// clona el tipo disparo para poder disparar en serie
	public Disparo[] cloneNivel(int power) {
		
		// establece la cantidad de disapros por nivel
		Disparo[] d = new Disparo[ power * 2-1 ];
		double n = 0;
		int direccion = 1;
		
		for (int i = 0; i < power * 2-1 ;i++) {
			
			// n * power determina la dirección del disparo
			//  direccion * dy determina si es para arriba o para abajo
			d[i] = new Disparo(x, y, n*power, direccion * dy, velocidad);
			
			// si es de nivel mayor a uno, establece el ángulo del disaparo
			if (n == 0) {
				n = -0.3d;
			}

			// cuando la iteración es 2 etablece el nivel 3
			if (n + 0.6 == -n && i == 2) {
				n = 0.3d;
				direccion = -1;
				y += 30;
			}
			// establece el simétrico del disparo
			n = -n;
		}
		
		return d;
	}
	
	public Explosion newExplosion() {
		return new Explosion(x + width/2, y + height / 2, explosion, width, height);
	}
	
	public void setPosicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}

