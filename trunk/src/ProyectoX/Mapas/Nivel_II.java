package ProyectoX.Mapas;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Jefes.JefeBarco;
import ProyectoX.Naves.Enemigos.Jefes.JefeTanque;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.Sound.Sonido;

public class Nivel_II extends Mapa{
	
	protected static final URL fondo = (Mapa.class.getClassLoader().getResource("ProyectoX/img/Fondos/fondoNivel2.png"));
	protected static final int initY = -2647 + 600;
	protected static final String soundBoss = "/ProyectoX/sounds/music/level2boss.mp3";
	protected static final String sound = "/ProyectoX/sounds/music/level2.mp3";

	
	
	public Nivel_II(){
		super();
		rn = new Random(13);
		cantEnemies = 101;
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

	public Mapa nextMapa() {
		reproductor.stop(0);
		Mapa map = new Nivel_III();
		map.setMind(mind);
		map.setMindEnemies(mindEnemies);
		mind.setMapa(map);
		mindEnemies.setMapa(map);
		map.setJugador(jugador);
		return map;
	}
	
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
}
