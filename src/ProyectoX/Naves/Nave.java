package ProyectoX.Naves;

import java.awt.Image;

import javax.swing.ImageIcon;

import ProyectoX.Frames.Explosion;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Sound.Reproductor;

/**
 * Clase que simboliza una nave,
 * Contiene su imagen, su posicion en pantalla, su ancho y alto,
 * y sus propiedades
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class Nave {
	
	//valores por defecto
	
	protected int defaultWidth;
	protected int defaultHeight ;
	protected int defaultVel;
	protected int defaultVida;
	
	
	protected Reproductor reproductor;
	protected Jugador jugador;
	protected int vida;
	protected Mapa mapa;
	protected int puntaje;

   //variables para la imagen
	protected int height;
	protected int width;
	
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
	// bordes de la pantalla
    protected int maxHeight;
    protected int minHeight;
    protected int maxWidth;
    protected int minWidth;
	/**
	 * Constructor de la clase Nave
	 * @param vida cantidad de vida de la Nave
	 * @param vel cantidad de pixeles que se mueve la Nave por cada iteracion
	 * @param icon Imagen de la Nave
	 * @param explosion Imagen de la explosion de la Nave
	 * @param w ancho de la Nave
	 * @param h altura de la Nave
	 */
	
	public Nave(int vida, int vel, ImageIcon icon, ImageIcon explosion, int w, int h) {
		height = h;
		width = w;
		this.posInicialX = x;
		//ajusta la imagen al tamanio de los parametros w = ancho y h = alto
		ImageIcon ii = new ImageIcon(icon.getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
		image = ii.getImage();
		this.explosion = explosion.getImage();
		
		
		this.dis = 0;
		this.longDis = 7;
		
		this.velocidad = vel;
		this.vida =vida;
		visible = true;
		
		maxHeight = -height*2;
		minHeight = 600 + height;
		maxWidth = 800 + width;
		minWidth = -width*2;
		
	}
	
	/**
	 * setea el jugador a la Nave, cada Nave debe conocer al jugador
	 * @param jugador instancia de Jugador
	 */
	
	public void setJugador(Jugador jugador) {
		this.jugador=jugador;
	}
	
	/**
	 * la operacion move debe ser redefinida
	 */
	
	public abstract void move();
	 
	/**
	 * retorna la coordenada en x
	 * @return coordenada x
	 */
	
	public int getX() {
	        return x;
    }
	
	/**
	 * retorna la coordenada en y
	 * @return coordenada y
	 */

    public int getY() {
        return y;
    }
    
    /**
     * retorna la imagen asociada a la instancia de Nave
     * @return instancia de Image
     */
     
    public Image getImage() {
		return image;
	}
    
    /**
     * aumenta en 1 el delay de los disparos
     */
    
    public void setDis() {
    	dis = (dis+1) % longDis;
    }
    
    /**
     * setea el delay en 0
     */
    
    public void setDisCero() {
    	dis = 0;
    }
    
    /**
     * funcion que genera un delay entre disparos
     * @return true si el puntero circlico esta en 0 false en caso contrario
     */
    
    protected boolean puedeDisparar() {
    	return !fueraDePantalla() && dis % longDis == 0;
    	
    }
    
    /**
     * la funcion que crea Disparos, debe ser definida por quien la utilice
     */
    
    public abstract void disparar();
    
    /**
     * retorna la altura de la Nave
     * @return entero que representa la altura
     */
    public int getHeight() {
		return height;
	}
    
    /**
     * retorna el ancho de la Nave
     * @return entero que representa el ancho
     */
    
	public int getWidth() {
		return width;
	}
	
	/**
	 * verifica si la Nave intersecta con otra instancia de Nave
	 * @param nave Nave que se pretende conocer su colision con la propia instancia
	 * @return true si si uno o mas puntos del borde del objeto nave intersectan con el borde del objeto que ejecuta la funcion y ademas ambos objetos estan visibles en pantalla
	 */

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
		
		// funcion de colicion que verifica si uno o mas puntos del borde del objeto nave intersectan con el borde del objeto que ejecuta la funcion
		fColision = (A && B || E && F) && (C && D || G && H) ||  !A && !F && ( !H && D || G && H) ||  !C &&  !H && (B &&  !F ||  !A && E);
		return !nave.isInvulnerable() && getVisible() && nave.getVisible() && fColision;
				
				
	}
	
	 /**
	  * setea la visibilidad de la nave en false
	  */
	
	public void setVisible() {
		visible = false;
	}
	
	 /**
	  * indica si la Nave esta visible en pantalla
	  * @return true si la NAve esta visible en pantalla, false en caso contrari
	  */
	
	public boolean getVisible() {
		return visible;
	}
	
	/**
	 * setea el nuevo Mapa a la Nave
	 * @param map Mapa que contiene los elementos interactivos del juego
	 */
	
	public void setMapa(Mapa map) {
		mapa = map;
	}
	/**
	 * retorna el mapa actual de la instancia
	 * @return instancia de Mapa
	 */
	public Mapa getMapa(){
		return mapa;
	}
	
	/**
	 * setea la vida de la Nave
	 * @param vd entero que representa la vida
	 */
	
	public abstract void setVida(int vd) ;
	
	/**
	 * retorna la cantidad de vida que tiene la nave 
	 * @return entero que representa a la vida
	 */
	public int getVida(){
		return vida;
	}
	
	/**
	 * retorna el damage que hace la instancia propia al colisionar
	 * @return la vida actual de la Nave que representa al damage real que realiza
	 */
	
	public int getDamageColision() {
		return vida;
	}
	/**
	 * retorna una nueva Explosion, debe ser definida
	 * @return instancia de Explosion dependiente de cada tipo de Nave
	 */
	public abstract Explosion getExplosion();
	
	/**
	 * indica si la instancia de Nave se encuentra dentro de los limites de la pantalla
	 * @return true si la Nave se encuentra fuera de los limites de la pantalla, false en caso contrario
	 */
	protected boolean fueraDePantalla() {
		return (x < minWidth || x > maxWidth || y > minHeight || y < maxHeight);
	}
	
	/**
	 * setea un delay entre los disparos para crear distintas frecuencias
	 * @param init indica donde inicia el puntero circlico
	 * @param longitud es la distancia que tiene que recorrer el puntero para completar una vuelta
	 */
	protected void setFrecuenciaDeDisparo(int init, int longitud) {
		dis = init;
		longDis = longitud;
	}
	
	/**
	 * retorna un entero que representa la cantidad de pixeles que se mueve por cada iteracion
	 * @return entero que representa la velocidad
	 */

	public int getVelocidad() {
		return velocidad;
	}
	
	/**
	 * setea la altura minima, que visto desde el punto de vista seria el maximo valor que puede alcanzarse en pantalla
	 * @param h entero que representa una altura
	 */
	public void setMinHeight(int h){
		minHeight = h;
	}
	
	/**
	 * setea un nuevo Reproductor a la Nave
	 * @param rep instancia de Reproductor
	 */
	
	public void addReproductor(Reproductor rep){
		reproductor = rep;
		
	}
	
	/**
	 * retorna un String que contiene la direccion relativa del Sonido mp3 de la Explosion, depende de cada Nave
	 * debe ser definida
	 * @return String que representa una direccion relativa
	 */
	
	protected abstract String getSonidoExplosion();
	
	/**
	 * agrega un nuevo sonido al Reproductor con una repeticion en false
	 */
	protected void addSonidoExplosion(){
		if(!fueraDePantalla())
			reproductor.addSound(getSonidoExplosion(),false);
	}
	
	/**
	 * @return true si la Nave es invulnerable y no puede ser colisionada o no se le resta vida, false en caso contrario
	 */
	
	public abstract boolean isInvulnerable();
	
	protected void setearParametrosDefecto(int vida, int whidt, int height, int velocidad){
		defaultWidth = width;
	    defaultHeight = height;
		defaultVel = velocidad;
		defaultVida = vida;
	}
}

