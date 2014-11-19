package ProyectoX.Naves.Jugador;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Disparos.MisilBomba;
import ProyectoX.Frames.Explosion;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Defensa.Defensa;
import ProyectoX.Sound.Reproductor;

public abstract class Jugador extends Nave {
	protected int hearts = 6;
	protected ImageIcon icon;
	protected ImageIcon iconDer;
	protected ImageIcon iconIzq;
	protected static URL explode = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/player.gif"));
	protected static Image invisible = new ImageIcon((Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/invisible.png"))).getImage();
	protected Image aux;
	private boolean cambio = false;
	protected DisparoJugador arma;
	private Defensa defensa;
	protected int bombas;
	private String explodeSound = "/ProyectoX/sounds/explode.mp3";
	private boolean pause;
	private long init;
	private int invulnerable = 5000;
	
	/**
	 * Cosntructor de la clase Jugador
	 * basado en el codigo http://zetcode.com/tutorials/javagamestutorial/movingsprites/ para mover el jugador
	 * @param vida
	 * @param vel
	 * @param icon
	 * @param iconDer
	 * @param iconIzq
	 */
	
	public Jugador(int vida, int vel, ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq){
		super(vida, vel, icon, new ImageIcon(explode), icon.getIconWidth(), icon.getIconHeight());
		
		x = 400;
		y = 450;
		
		init = System.currentTimeMillis();
		
		this.icon = icon;
		this.iconDer = iconDer;
		this.iconIzq = iconIzq;
		velocidad=vel;
		setJugador(this);
		velocidadMisil = 20;
		bombas = 2;
		
		puntaje = 0;
		arma = new DisparoJugador(x + width/2 , y, 0, 1, velocidadMisil,this);
		
    	
	}
	
	public void keyPressed(KeyEvent e) {
		if(!pause){
	        int key = e.getKeyCode();
	        
	        if (key == KeyEvent.VK_SPACE) {
	            if(puedeDisparar() && getVisible()){
	            	disparar();
	            }
	            setDis();
	        }
	       
	        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
	        	dx = -velocidad;
	            image = iconIzq.getImage();
	        }
	        
	        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
	        	dx = velocidad;
	            image = iconDer.getImage();
	        }
	       
	        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
	        	dy = -velocidad;
	        }
	        
	        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
	        	dy = velocidad;
	        }
	        
	        if (key == KeyEvent.VK_X){
	        	tirarBomba();
	        }
	        
	        
	        if(defensa!=null){
	        	defensa.keyPressed(e);
	        }
		}
    }

    private void tirarBomba() {
    	if(bombas > 0){
    		mapa.addDisparoJugador(new MisilBomba(x + width/2, y, mapa, reproductor));
    		bombas--;
    	}
	}

	public void keyReleased(KeyEvent e) {
    	if(!pause){
	        int key = e.getKeyCode();
	
	        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
	            dx = 0;
	            image = icon.getImage();
	        }
	      
	        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
	            dx = 0;
	            image = icon.getImage();
	        }
	        
	        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
	            dy = 0;
	        }
	       
	        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
	            dy = 0;
	        }
	        
	        if (key == KeyEvent.VK_SPACE) {
	            setDisCero();
	        }
	        
	        if(defensa!=null){
	        	defensa.keyReleased(e);
	        }
    	}
        
    }
    
    public void disparar() {
    	arma.setPosicion(x + width/2, y + height/2);
    	
    	Disparo[] array = arma.cloneNivel();
    	for(int i = 0;i<array.length;i++){
    		Disparo dis = array[i];
    		mapa.addDisparoJugador(dis);
    	}
    	arma.getSound();
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
		if(System.currentTimeMillis() - init > invulnerable){
			if(vida > 0)
				vida-=vd;		
			if(vida > 100)
				vida = 100;
			if(vida <= -1) 
				setVisible();
		}
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
	
	public void setNewDisparo(DisparoJugador d){
		arma = d;
		arma.setReproductor(reproductor);
	}
	
	public DisparoJugador getDisparo(){
		return arma;
	}
	
	public void setDefensa(Defensa def){
		defensa = def;
		defensa.addReproductor(reproductor);
	}
	
	public Defensa getDefensa(){
		return defensa;
	}

	public void dropDefensa() {
		defensa = null;
	}
	
	public void setBomba(){
		if (bombas <= 3)
			bombas ++;
	}
	
	public void addReproductor(Reproductor rep){
		super.addReproductor(rep);
		arma.setReproductor(reproductor);
	}
	
	public void reset(){
		hearts--;
		init = System.currentTimeMillis();
		x = 400;
		y = 450;
		dropDefensa();
		bombas = 1;
		visible = true;
	}
	
	public void setMapa(Mapa map){
		super.setMapa(map);
		if(defensa!=null){
			defensa.setMapa(map);
		}
	}
	
	protected String getSonidoExplosion(){
		return explodeSound;
	}
	
	public void pause(boolean arg){
		pause = arg;
	}

	public int getHearts() {
		return hearts;
	}

	public void setHearts() {
		hearts ++;
	}
	public boolean isInvulnerable(){
		boolean toRet = System.currentTimeMillis() - init <= invulnerable;
		if(!toRet){
			cambio = false;
		}else{
			if(!cambio){
				cambio = true;
				aux = image;
				image = invisible;
				
			}
			
			else{
				cambio = false;
				image = aux;
			}
		}
		
		return toRet;
	}
	
	public int getCantBombas(){
		return bombas;
	}
}
