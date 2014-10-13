package Proyecto2;

import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;

public class Jugador extends Nave {
	protected ImageIcon icon;
	protected ImageIcon iconDer;
	protected ImageIcon iconIzq;
	protected int power;
	protected static URL explode = (Nave.class.getClassLoader().getResource("img/Explosiones/player.gif"));
	
	
	public Jugador(int vida, int vel,int x, int y, ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq){
		super(vida, vel, x, y, icon, new ImageIcon(explode), icon.getIconWidth(), icon.getIconHeight());
		this.icon = icon;
		this.iconDer = iconDer;
		this.iconIzq = iconIzq;
		velocidad=vel;
		setJugador(this);
		velocidadMisil = 20;
		power=3;
	}
	
	public void keyPressed(KeyEvent e) {
		
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_SPACE) {
            if(puedeDisparar() && getVisible()){
            	disparar();
            }
        }
       
        if (key == KeyEvent.VK_A) {
        	dx = -velocidad;
            image = iconIzq.getImage();
        }
        
        if (key == KeyEvent.VK_D) {
        	dx = velocidad;
            image = iconDer.getImage();
        }
       
        if (key == KeyEvent.VK_W) {
        	dy = -velocidad;
        }
        
        if (key == KeyEvent.VK_S) {
        	dy = velocidad;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
            image = icon.getImage();
        }
      
        if (key == KeyEvent.VK_D) {
            dx = 0;
            image = icon.getImage();
        }
        
        if (key == KeyEvent.VK_W) {
            dy = 0;
        }
       
        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
    
    public void disparar() {
    	
    	Disparo d = new Disparo(x + width/2 , y, 0, 1, velocidadMisil);
    	Disparo[] array = d.cloneNivel(power);
    	if(puedeDisparar()){
    		for(int i = 0;i<array.length;i++){
    			mapa.addDisparoJugador(array[i]);
    		}
    	}
    }

	 public void move() {
		 
		 
		 if(x >= minWidth)
			x += dx;
		 else
			x = 1;
		 
		 if( x <= maxWidth)
			x += dx;
		 else
			 x = maxWidth-1;
		 
		 if(y >= 0 )
	        y += dy;
		 else
			y = 1;
		 
		 if(y <= minHeight)
			y += dy;
		 else
			y = minHeight-1;
   }

	@Override
	public Explosion getExplosion() {
		return new ExplosionMediana(x + width/2, y + height/2, new ImageIcon(explode), width, height);
	}
}