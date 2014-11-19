package ProyectoX.Mapas;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import ProyectoX.Aplication;
import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Jefes.JefeBarco;
import ProyectoX.Naves.Enemigos.Jefes.JefeTanque;
import ProyectoX.Paneles.PanelGame;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.Sound.Sonido;

public class Nivel_II extends Mapa{
	
	protected static final URL fondo = (Mapa.class.getClassLoader().getResource("ProyectoX/img/Fondos/fondoNivel2.png"));
	protected static final int initY = -2647 + 600;
	protected static final String soundBoss = "/ProyectoX/sounds/music/level2boss.mp3";
	protected static final String sound = "/ProyectoX/sounds/music/level2.mp3";

	
	
	public Nivel_II(Aplication api, PanelGame game){
		super(api,game);
		rn = new Random(13);
		cantEnemies = 1;
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
			if(y  >= 0 ){
				y = initY;
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
		Mapa map = new Nivel_III(api,game);
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
		Mapa map = new Nivel_II(api,game);
		map.setMind(mind);
		map.setMindEnemies(mindEnemies);
		mind.setMapa(map);
		mindEnemies.setMapa(map);
		map.setJugador(jugador);
		super.reset();
	}
}