package ProyectoX.Naves.Jugador.Defensa;

import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Jugador.Jugador;

/**
 * Representa a un Escudo que cubre al Jugador de los ataques enemigos
 * Siempre se Encuentra encima del Jugador 
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class EscudoX extends Defensa {

	protected static final URL url = (Jugador.class.getClassLoader().getResource("ProyectoX/img/PUP/Escudo/Escudo.gif"));
	protected static final URL urlWarning = (Jugador.class.getClassLoader().getResource("ProyectoX/img/PUP/Escudo/Escudo_Warning.gif"));

	private boolean warning = false;
	private String warningSound = "/ProyectoX/sounds/warning.mp3";
	
	/**
	 * Constructor de la clase EscudoX
	 * Setea al mapa la instancia propia como nuevo Jugador y a todos los enemigos que estan en pantalla
	 * para obtener como resultado que el Jugador no reciba danio mientras el EscudoX esta visible
	 * @param jugador el Jugador actual
	 */
	public EscudoX(Jugador jugador){
		super(400, jugador.getVelocidad(),  new ImageIcon(url), new ImageIcon(url), new ImageIcon(url));
		setearParametrosDefecto(400, 0, 64, 64);
		x = jugador.getX() + jugador.getWidth()/2 - defaultWidth/2;
		y = jugador.getY() + jugador.getHeight()/2 - defaultHeight/2;
		height = defaultHeight;
		width = defaultWidth;
		
		setJugador(jugador);
		
		bombas = 0;
		
		setMapa(jugador.getMapa());
		
		ArrayList enemigos = mapa.getEnemies();
		
		for(int i = 0; i < enemigos.size(); i++){
			Enemigo m = (Enemigo) enemigos.get(i);
			m.setJugador(this);
		}
		
		mapa.setJugador(this);
		
		
	}
	
	/**
	 * redefine setVisible() de la clase Nave
	 * Le setea al Mapa el Jugador original y tambien a los enemigos en pantalla 
	 * le quita la visibilidad al EscudoX
	 */
	
	public void setVisible(){
		super.setVisible();
		ArrayList enemigos = mapa.getEnemies();
		
		for(int i = 0; i < enemigos.size(); i++){
			Enemigo m = (Enemigo) enemigos.get(i);
			m.setJugador(jugador);
		}
		
		mapa.setJugador(jugador);
		
	}
	
	/**
	 * redefine move() de la clase Jugador
	 * setea las coordenadas x e y sobre las coordenadas del jugador
	 */

	public void move(){
		x = jugador.getX() + jugador.getWidth()/2 - defaultWidth/2;
		y = jugador.getY() + jugador.getHeight()/2 - defaultHeight/2;
	}
	
	/**
	 * redefine disparar() de la clase Jugador
	 * el metodo disparar no ejecuta ninguna accion
	 */
	public void disparar(){
		
	}	
	
	/**
	 * redefine setVida(int vd) de la clase Jugador
	 * le resta a la vida la cantidad pasada por parametro
	 * y ademas en caso de que la vida del escudo sea menor que 50
	 * le setea el warning
	 */
	
	public void setVida(int vd){
		super.setVida(vd);	
		
		if(vida <= 100 && vida>0) {
			setWarning();
			
		}
	}
	
	/**
	 * setea una sola vez los iconos de warning a la instancia
	 */
	
	private void setWarning(){
		if(!warning){
			icon = new ImageIcon(urlWarning);
			iconDer = new ImageIcon(urlWarning);
			iconIzq = new ImageIcon(urlWarning);
			reproductor.addSound(warningSound , false);
			warning = true;
		}
	}
	
	/**
	 * redefine getEscudo() de la clase Defensa
	 * hace un reset y se retorna retorna a si misma
	 * @return instancia propia
	 */
	
	public Defensa getEscudo(){
		reset();
		return this;
	}
	
	/**
	 * redefine la operacion getAyudante() de la clase Ayudante
	 * hace un setVisible y retorna una instancia de NaveAyudante
	 * @return instancia de Ayudante
	 */
	
	public Defensa[] getAyudante(){
		setVisible();
		Defensa[] nueva = {new NaveAyudante(jugador,0)};
		return nueva;
	}
	
	/**
	 * redefine reset de la clase Jugador
	 * quita el warning a la instancia y setea los atributos por defecto
	 */
	public void reset(){
		icon = new ImageIcon(url);
		iconDer = new ImageIcon(url);
		iconIzq = new ImageIcon(url);
		warning = false;
		vida = defaultVida;
	}
	
	/**
	 * redefine setPuntaje(int puntaje) de la clase Jugador
	 * le setea el puntaje pasado por parametro al Jugador
	 * @param puntaje entero que se le suma al puntaje del Jugador
	 */
	
	public void setPuntaje(int puntaje){
		jugador.setPuntaje(puntaje);
	}
	
	public void misilLanzado(){
		jugador.misilLanzado();
	}
}
