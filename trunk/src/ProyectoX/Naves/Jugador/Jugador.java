package ProyectoX.Naves.Jugador;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Explosiones.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Defensa.Defensa;

public class Jugador extends Nave {
	protected ImageIcon icon;
	protected ImageIcon iconDer;
	protected ImageIcon iconIzq;
	protected String power;
	protected static URL explode = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/player.gif"));
	protected Disparo arma;
	private Defensa defensa;
	
	public Jugador(int vida, int vel, ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq){
		super(vida, vel, icon, new ImageIcon(explode), icon.getIconWidth(), icon.getIconHeight());
		this.icon = icon;
		this.iconDer = iconDer;
		this.iconIzq = iconIzq;
		velocidad=vel;
		setJugador(this);
		velocidadMisil = 20;
		power = "";
		puntaje = 0;
		arma = new Disparo(x + width/2 , y, 0, 1, velocidadMisil);
    	
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
        if(defensa!=null){
        	defensa.keyPressed(e);
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
        
        if(defensa!=null){
        	defensa.keyReleased(e);
        }
        
    }
    
    public void disparar() {
    	arma.setPosicion(x + width/2, y + height/2);
    	Disparo[] array = arma.cloneNivel();
    	if(puedeDisparar()){
    		for(int i = 0;i<array.length;i++){
    			mapa.addDisparoJugador(array[i]);
    		}
    	}
    }

	 public void move() {
		 
		 if(x >= minWidth + width*2)
			x += dx;
		 else
			x = 1;
		 
		 if( x <= maxWidth - width*2)
			x += dx;
		 else
			 x = maxWidth  - width*2 - 1;
		 
		 if(y >= maxHeight + height*2 )
	        y += dy;
		 else
			y = 1;
		 
		 if(y <= minHeight - height)
			y += dy;
		 else
			y = minHeight - height - 1;
		 
		 if(defensa!=null){
			 defensa.move();
		 }
   }

		public void setVida(int vd) {
			
			if(vida <= 0) 
				setVisible();
			else
				vida-=vd;
		}
	 
	@Override
	public Explosion getExplosion() {
		return new Explosion(x + width/2, y + height/2, new ImageIcon(explode), width, height);
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje += puntaje;
	}
	
	public String getPower(){
		return power;
	}
	
	public void setPower(String power){
		this.power = power;
	}
	
	public void setNewDisparo(Disparo d){
		arma = d;
	}
	
	public Disparo getDisparo(){
		return arma;
	}
	
	public void setDefensa(Defensa def){
		defensa = def;
	}
	
	public Defensa getDefensa(){
		return defensa;
	}

	public void dropDefensa() {
		defensa = null;
		
	}
}
