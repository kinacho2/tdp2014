package ProyectoX.Mapas;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import ProyectoX.Aplication;
import ProyectoX.Frames.MenteSplash;
import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Jefes.JefeAvion;
import ProyectoX.Paneles.PanelGame;
import ProyectoX.PowerUps.PUPBuilder;

/**
 * Tercer y ultimo nivel del juego
 * Se encarga de crear la pantalla final del juego
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class Nivel_III extends Mapa{
	
	protected static final URL fondo = (Mapa.class.getClassLoader().getResource("ProyectoX/img/Fondos/fondoNivel1.png"));
	protected static final int initY = -8072 + 600;
	protected static final String soundBoss = "/ProyectoX/sounds/music/level3boss.mp3";
	protected static final String sound = "/ProyectoX/sounds/music/level3.mp3";

	

	public Nivel_III(Aplication api, PanelGame game){
		super(api,game);
		rn = new Random(13);
		cantEnemies = 201;
		power = new PUPBuilder(16);
		enBuilder = new EnemiesBuilder(19);
		ImageIcon ii = new ImageIcon(fondo);
		imagenFondo = ii.getImage();
		x = -1200;
		y = initY;
		jefe = new JefeAvion(); 
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
			if(y  >= 0 ){
				y = initY;
			}
		}
		
		
		delay = (delay + 1) % delayVel;
		return y;
	}

	/**
	 * crea una SplashScreen para el final del juego y redirige el hilo al primer panel del juego
	 */
	
	public void nextMapa() {
		game.setVisible(false);
		MenteSplash spl = new MenteSplash(5000, fondo, game);
    	spl.start();
 
	}

	/**
	 * redefine reset() de la clase Mapa
	 * hace un reset del nivel y llama a la funcion reset de Mapa
	 */
	
	public void reset() {
		reproductor.stop(0);
		Mapa map = new Nivel_III(api,game);
		map.setMind(mind);
		map.setMindEnemies(mindEnemies);
		mind.setMapa(map);
		mindEnemies.setMapa(map);
		map.setJugador(jugador);
		super.reset();
	}

}
