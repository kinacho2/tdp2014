package ProyectoX.Mapas;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import ProyectoX.Aplication;
import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Jefes.JefeBarco;
import ProyectoX.Paneles.PanelGame;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.Sound.Reproductor;

/**
 * Segundo nivel del juego
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Nivel_II extends Mapa{
	
	protected static final URL fondo = (Mapa.class.getClassLoader().getResource("ProyectoX/img/Fondos/fondoNivel2.png"));
	protected static final int initY = -2647 + 600;
	protected static final String soundBoss = "/ProyectoX/sounds/music/level2boss.mp3";
	protected static final String sound = "/ProyectoX/sounds/music/level2.mp3";

	/**
	 * Constructor de la clase NivelII
	 * se encarga de setear los atributos por defecto para su estado
	 * @param api la aplicacion que ejecutara el siguiente metodo al terminarse el SplashScreen
	 * @param game el panel donde se dibuja el juego
	 * @param rep el Reproductor actual del juego
	 */
	
	public Nivel_II(Aplication api, PanelGame game, Reproductor rep){
		super(api,game,rep);
		rn = new Random(13);
		cantEnemies = 151;//151;
		power = new PUPBuilder(6);
		enBuilder = new EnemiesBuilder(9);
		ImageIcon ii = new ImageIcon(fondo);
		imagenFondo = ii.getImage();
		x = -50;
		y = initY;
		
		jefe = new JefeBarco(); 
		jefe.addReproductor(reproductor);
		sonido = sound;
		sonidoJefe = soundBoss;
		
		
		reproductor.addSound(sonido,true);
		
	}
	
	/**
	 * redefine getY() de la clase Mapa
	 * retorna la coordenada y del fondo y la modifica si el fondo llego a su fin vuelve a empezar
	 * @return coordenada y
	 */
	
	public int getY(){
		if(delay % delayVel  == 0){
			y += dy;
			if(y  >= 0 && dy>0){
				y = initY;
			}
			if(y<=initY && dy<0){
				y=0;
			}
		}
		
		
		delay = (delay + 1) % delayVel;
		return y;
	}
	
	/**
	 * crea y setea a todos los entes del juego el nuevo mapa que es el siguiente nivel
	 */

	public void nextMapa() {
		reproductor.stop(0);
		Mapa map = new Nivel_III(api,game,reproductor);
		map.setMind(mind);
		map.setMindEnemies(mindEnemies);
		mind.setMapa(map);
		mindEnemies.setMapa(map);
		map.setJugador(jugador);
	}

	/**
	 * redefine reset() de la clase Mapa
	 * hace un reset del nivel y llama a la funcion reset de Mapa
	 */
	
	public void reset() {
		reproductor.stop(0);
		Mapa map = new Nivel_II(api,game,reproductor);
		map.setMind(mind);
		map.setMindEnemies(mindEnemies);
		mind.setMapa(map);
		mindEnemies.setMapa(map);
		map.setJugador(jugador);
		super.reset();
	}

	/**
	 * retorna la URL asociada a la imagen del SplashScreen que seguira al terminar el nivel
	 * @return URL de imagen
	 */
	public URL getImagenSplash() {
		return (Aplication.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/historia/Imagen4.png"));
	}
}
