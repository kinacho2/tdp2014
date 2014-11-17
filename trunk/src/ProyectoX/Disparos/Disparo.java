package ProyectoX.Disparos;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Frames.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Sound.Reproductor;
import ProyectoX.Sound.Sonido;


public class Disparo {

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Basico/Basico.png");
	private static final URL explode = Disparo.class.getClassLoader().getResource("ProyectoX/img/Explosiones/pequena.gif");
	protected String sonido;
	protected String golpe = "/ProyectoX/sounds/bala_golpeando.mp3";
	
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
	protected Reproductor rep;
    
    
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
        sonido = "/ProyectoX/sounds/fire.mp3";
        
    }

    /**
     * retorna la imagen del Disparo
     * @return instancia de Image
     */

    public Image getImage() {
        return image;
    }
    
    /**
     * retorna la posicion en x del Disparo
     * @return entero x
     */

    public int getX() {
        return x;
    }
    
    /**
     * retorna la posicion en y del Disparo
     * @return entero y
     */

    public int getY() {
        return y;
    }

    /**
     * indica si el Disparo esta visible en pantalla
     * @return boolean visible
     */
    public boolean isVisible() {
        return visible ;
    }
    
    /**
     * modifica los valores de x e y dependiendo de los valores de dx y dy 
     * que funcionan como diferenciales
     */

    public void move() {
        y -= dy * velocidad;
        x += dx * velocidad;
        verificarColisionBorde();
    }
    
    /**
     * verifica si el disparo salio de la pantalla y setea la visivilidad en false
     */
    
    protected void verificarColisionBorde(){
    	if (y < maxHeight || y >  minHeight || x < minWidth || x > maxWidth)
            setVisible();
    }
    
    /**
     *  Determina si el disparo colisiono con la nave pasada por parametro
     */
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
		fColision = (A && B || E && F) && (C && D || G && H) ||  !A && !F && ( !H && D || G && H) ||  !C &&  !H && (B &&  !F ||  !A && E) && !nave.isInvulnerable() && isVisible() && nave.getVisible();
		if(fColision){
			if(rep!=null)
				rep.addSound(golpe,false) ;
		}
		
		return fColision;
	}
    
    /**
     *  Establece que el disparo no esta visible en la pantalla
     */
    public void setVisible() {
    	visible = false;
    }

    /**
     *  devuelve el damage del disparo
     * @return entero damage
     */
	public int getDamage() {
		return damage;
	}
	
	/**
	 *  clona el tipo disparo y lo devuelve en un arreglo de disparos
	 * @return Disparo[] con 1 elemento
	 */
	public Disparo[] cloneNivel() {
		
		Disparo[] d = new Disparo[1];
		d[0] = new Disparo(x, y, dx, dy, velocidad);
		d[0].setReproductor(rep);
		return d ;
	}
	
	/**
	 * devuelve una instancia de la clase Explosion que depende del Disparo
	 * @param altura la altura a la que se produce la explosion, en esta clase queda sin uso
	 * @return instancia de Explosion
	 */
	
	public Explosion newExplosion(int altura) {
		return new Explosion(x + width/2, y + height / 2, explosion, width, height);
	}
	
	/**
	 * setea la posicion del disapro en las coordenadas determinadas
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	public void setPosicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Retorna el siguiente nivel del disparo 
	 * en esta clase base es el mismo disparo
	 * @return instancia de Disparo
	 */
	
	public Disparo nextLevel(){
		return new Disparo(x,y,dx,dy,velocidad);
	}


	/**
	 * agrega un sonido nuevo al reproductor
	 */
	public void getSound() {
		rep.addSound(sonido,false) ;
	}
	
	/**
	 * Setea un nuevo reproductor
	 * @param rep de tipo Reproductor
	 */
	
	public void setReproductor(Reproductor rep){
		this.rep = rep;
	}
	
	/**
	 * setea una instancia de Reproductor a cada Disparo del arreglo d
	 * @param d arreglo de disparos
	 */
	
	protected void setearReproductor(Disparo[] d){
		for(int i = 0; i<d.length; i++){
			d[i].setReproductor(rep);
		}
	}
}

