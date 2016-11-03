package ProyectoX.Naves.Jugador;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Disparos.MisilBomba;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Frames.Explosion;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Rocket;
import ProyectoX.Naves.Jugador.Defensa.Defensa;
import ProyectoX.Sound.Reproductor;

/**
 * Clase que representa a la nave del Jugador
 * contiene el puntaje y los metodos de movimiento
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class Jugador extends Nave {
	protected int hearts;
	protected ImageIcon icon;
	protected ImageIcon iconDer;
	protected ImageIcon iconIzq;
	protected static URL explode = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/player.gif"));
	protected static Image invisible = new ImageIcon((Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/invisible.png"))).getImage();
	protected Image aux;
	private boolean cambio = false;
	protected DisparoJugador arma;
	private Defensa[] defensa;
	protected int bombas;
	private String explodeSound = "/ProyectoX/sounds/explode.mp3";
	private boolean pause;
	private long init;
	protected int invulnerable = 5000;
	protected int contPuntaje;
	//cada 3000 puntos se seteara un nuevo corazon al jugador
	protected int maxContPuntaje = 3000;
	protected String winHeart = "/ProyectoX/sounds/power.mp3";
	private String nombre;
	protected int rocket;
	private boolean misilLanzado=false;
	//arreglo booleano de keyCodes
	/**
	 * Cosntructor de la clase Jugador
	 * basado en el codigo http://zetcode.com/tutorials/javagamestutorial/movingsprites/ para mover el jugador
	 * @param vida cantidad de vida del Jugador
	 * @param vel cantidad de pixeles que se mueve por iteracion
	 * @param icon Imagen estatica vertical
	 * @param iconDer Imagen inclinada hacia la derecha
	 * @param iconIzq Imagen inclinada hacia la izquierda
	 */
	
	public Jugador(int vida, int vel, ImageIcon icon, ImageIcon iconDer, ImageIcon iconIzq, String nombre){
		super(vida, vel, icon, new ImageIcon(explode), icon.getIconWidth(), icon.getIconHeight());
		this.nombre = nombre;
		setearParametrosDefecto(vida, vel, icon.getIconWidth(), icon.getIconHeight());
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
		hearts = 3;
		puntaje = contPuntaje = 0;
		arma = new MultiplicadorLVI(x, y , 0, 1, this);
    	
	}
	
	/**
	 * Si no esta en pausa setea los diferenciales (dx, dy) dependiendo de la tecla presionada
	 * ademas setea las imagenes de desplazamiento y ordena disparar
	 * @param e KeyEvent de la tecla que se encuentra presionada
	 */
	
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
	            aux = image = iconIzq.getImage();
	        }
	        
	        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
	        	dx = velocidad;
	            aux = image = iconDer.getImage();
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
	        if(defensa!=null)
		        for(int i=0; i<defensa.length;i++){
		        	if(defensa[i]!=null)
		        		defensa[i].keyPressed(e);
			        
		        }
	        
		}
    }
	
	/**
	 * Le indica al mapa que se arrojo una bomba y le resta una bomba a la cantidad de bombas
	 */
    private void tirarBomba() {
    	if(bombas > 0){
    		if(!misilLanzado){
    			misilLanzado = true; 
    			
    			mapa.addDisparoJugador(new MisilBomba(x + width/2, y, mapa, reproductor));
	    		
	    		bombas--;
    		}
    	}
    	if(rocket>0){
    		if(!misilLanzado){
		    	Rocket r = new Rocket(50,12);
		    	misilLanzado = true;
		    	r.addReproductor(reproductor);
		    	r.setPosition(x+width/2,y+3);
		    	r.setMapa(mapa);
		    	mapa.setEnemies(r);
		    	
		    	rocket--;
    		}
    	}
	}
    
    /**
     * Setea los diferenciales (dx, dy) en 0 dependiendo de la tecla que se solto
     * @param e KeyEvent de la tecla soltada
     */

	public void keyReleased(KeyEvent e) {
    	if(!pause){
	        int key = e.getKeyCode();
	       
	        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
	            dx = 0;
	            aux = image = icon.getImage();
	        }
	      
	        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
	            dx = 0;
	            aux = image = icon.getImage();
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
	        
	        if(defensa!=null)
		        for(int i=0; i<defensa.length;i++){
		        	if(defensa[i]!=null)
		        		defensa[i].keyReleased(e);
			        
		        }
	        
    	}
        
    }
    
	/**
	 * Ejecuta la accion de disparar y agrega al mapa los disparos del Jugador resultantes
	 * Ademas agrega un sonido de Disparo al Reproductor
	 */
	
    public void disparar() {
    	arma.setPosicion(x + width/2, y + height/2);
    	
    	Disparo[] array = arma.cloneNivel();
    	for(int i = 0;i<array.length;i++){
    		Disparo dis = array[i];
    		mapa.addDisparoJugador(dis);
    	}
    	if(array.length>0)
    		arma.getSound();
    }
    
    /**
     * Modifica los valores actuales de las coordenadas x,y adicionando los respectivos valores de sus diferenciales
     */

    public void move() {
	 
	 if(x >= 0)
		x += dx;
	 else
		x = 1;
	 
	 if( x <= maxWidth - width)
		x += dx;
	 else
		 x = maxWidth  - width - 1;
	 
	 if(y >= 0)
	    y += dy;
	 else
		y = 1;
	 
	 if(y <= minHeight - height)
		y += dy;
	 else
		y = minHeight - height - 1;
	 
	 
	 if(defensa!=null)
	        for(int i=0; i<defensa.length;i++){
	        	if(defensa[i]!=null)
	        		defensa[i].move();
		        
	        }
 }

    /**
     * define el metodo setVida(int vd) de la clase Nave
     * Le resta a la vida del Jugador el valor pasado por parametro
     * Si el valor del parametro es negativo se aumenta la vida
     * ademas setea la visibilidad en falso cuando su vida baja de 0
     * @param vd entro que sera restado a la vida del Jugador
     */
	public void setVida(int vd) {
		if(System.currentTimeMillis() - init > invulnerable){
			if(vida-vd > 0)
				vida-=vd;	
			else
				vida = 0;
			if(vida > 150)
				vida = 150;
			if(vida <= 0) 
				setVisible();
		}
	}
	
	/**
	 * retorna una instancia de Explosion dependiente del Jugador
	 * @return instancia de Explosion
	 */
	 
	public Explosion getExplosion() {
		return new Explosion(x + width/2, y + height/2, new ImageIcon(explode), width, height);
	}
	
	/**
	 * retorna el puntaje asociado al jugador
	 * @return entero que representa el puntaje
	 */

	public int getPuntaje() {
		return puntaje;
	}
	
	/**
	 * Suma al puntaje la cantidad pasada por parametro, ademas se encarga de sumarle un corazon al Jugador cada 3000 puntos 
	 * @param puntaje entero que sera sumado al puntaje del Jugador
	 */

	public void setPuntaje(int puntaje) {
		this.puntaje += puntaje;
		contPuntaje += puntaje;
		if(contPuntaje > maxContPuntaje){
			contPuntaje -= maxContPuntaje;
			setHearts();
			reproductor.addSound(winHeart, false);
		}
	}
	
	/**
	 * le setea al Jugador un nuevo DisparoJugador
	 * @param d nueva instancia de DisparoJugador
	 */
	public void setNewDisparo(DisparoJugador d){
		arma = d;
		arma.setReproductor(reproductor);
	}
	
	/**
	 * retorna el Disparo actual del Jugador
	 * @return instancia de DisparoJugador
	 */
	
	public DisparoJugador getDisparo(){
		return arma;
	}
	
	/**
	 * Setea al Jugador una nueva Defensa
	 * y le setea a la Defensa su Reproductor
	 * @param def nueva instancia de Defensa
	 */
	
	public void setDefensa(Defensa[] def){
		defensa = def;
		for(int i=0;i<defensa.length;i++)
			defensa[i].addReproductor(reproductor);
	}
	
	/**
	 * retorna la instancia de Defensa actual del Jugador
	 * @return instancia de Defensa
	 */
	public Defensa[] getDefensa(){
		return defensa;
	}
	
	/**
	 * Le indica al Jugador que perdio la Defensa
	 */

	public void dropDefensa(int i) {
		if(i<0)
			defensa = null;
		else
			defensa[i] = null;
		if(i==0 && defensa.length>1 &&defensa[1]!=null){
			defensa[0] = defensa[1];
		}
	}
	
	/**
	 * agrega una bomba a la cantidad de bombas del jugador siempre que no se exceda de 5
	 */
	
	public void setBomba(){
		if (bombas <= 6)
			bombas +=2;
		else
			bombas = 8;
		
		rocket=0;
	}
	
	/**
	 * Redefine addReproductor(Reproductor rep) de la clase Nave
	 * setea una nueva instancia de Reproductor al Jugador y a su Disparo
	 * @param rep nueva instancia de Reproductor
	 */
	
	public void addReproductor(Reproductor rep){
		super.addReproductor(rep);
		arma.setReproductor(reproductor);
	}
	
	/**
	 * Le setea al Jugador los atributos por defecto y resta un corazon
	 */
	
	public void reset(){
		hearts--;
		init = System.currentTimeMillis();
		x = 400;
		y = 450;
		dropDefensa(-1);
		bombas = 2;
		rocket = 0;
		visible = true;
	}
	
	/**
	 * Redefine setMapa(Mapa map) de la clase Nave
	 * setea al jugador y a su defensa el nuevo Mapa
	 * @param map nueva instancia de Mapa
	 */
	
	public void setMapa(Mapa map){
		super.setMapa(map);
		if(defensa!=null)
	        for(int i=0; i<defensa.length;i++){
	        	if(defensa[i]!=null)
	        		defensa[i].setMapa(map);
		        
	        }
		arma.reset();
	}
	
	/**
	 * retorna un String que contiene el path del Sonido de Explosion del Jugador
	 * @return path del Archivo de Sonido 
	 */
	
	protected String getSonidoExplosion(){
		return explodeSound;
	}
	
	/**
	 * le indica al Jugador una pausa o una reanudacion
	 * @param arg booleano si es true se indica pausa, si es false se reanuda
	 */
	
	public void pause(boolean arg){
		pause = arg;
	}
	
	/**
	 * @return cantidad de corazones del Jugador
	 */

	public int getHearts() {
		return hearts;
	}
	
	/**
	 * Aumenta en 1 la cantidad de corazones del Jugador
	 */

	public void setHearts() {
		hearts ++;
	}
	
	/**
	 * Define la operacion isInvulnerable() de la clase Nave
	 * durante 5 segundos el Jugador es invulnerable a los Disparos y no colisiona con los Enemigos
	 * y genera un pestaneo en la imagen
	 * @return booleano si es true el jugador no puede recibir danio
	 */
	
	public boolean isInvulnerable(){
		boolean toRet = System.currentTimeMillis() - init <= invulnerable;
		if(!toRet){
			if(!cambio){
				image = aux;
			}
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
	
	/**
	 * @return cantidad actual de bombas que tiene el Jugador
	 */
	
	public int getCantBombas(){
		return bombas;
	}
	
	/**
	 * Agrega un nuevo Sonido al Reproductor cuando se recibe un PowerUP o un corazon
	 * @param path del Archivo de Sonido
	 */
	
	public void addSonidoDePremio(String path){
		reproductor.addSound(path, false);
	}
	
	/**
	 * retorna el nombre asociado al jugador
	 * @return nombre del jugador
	 */
	
	public String getNombre(){
		return nombre;
	}

	public void setRocket() {
		if(rocket<=40)
			rocket+=10;
		else
			rocket = 50;
		bombas = 0;
		
	}
	
	public void misilLanzado(){
		misilLanzado = false;
	}

	public int getCantRockets() {
		return rocket;
	}
	
}
