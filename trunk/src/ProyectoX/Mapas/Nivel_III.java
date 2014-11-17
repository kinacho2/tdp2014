package ProyectoX.Mapas;

import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import ProyectoX.Aplication;
import ProyectoX.Frames.MenteSplash;
import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Jefes.JefeAvion;
import ProyectoX.Naves.Enemigos.Jefes.JefeBarco;
import ProyectoX.Paneles.PanelGame;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.Sound.Sonido;

public class Nivel_III extends Mapa{
	
	protected static final URL fondo = (Mapa.class.getClassLoader().getResource("ProyectoX/img/Fondos/fondoNivel1.png"));
	protected static final int initY = -8072 + 600;
	protected static final String soundBoss = "/ProyectoX/sounds/music/level2boss.mp3";
	protected static final String sound = "/ProyectoX/sounds/music/level2.mp3";

	

	public Nivel_III(Aplication api, PanelGame game){
		super(api,game);
		rn = new Random(13);
		cantEnemies = 50;
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
	
	public int getY(){
		if(delay % delayVel  == 0){
			y++;
			if(y  >= 0 ){
				y = initY;
			}
		}
		
		
		delay = (delay + 1) % delayVel;
		return y;
	}

	
	public void nextMapa() {
		MenteSplash spl = new MenteSplash(5000, fondo, game, 1);
    	spl.start();
 
	}

	@Override
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
