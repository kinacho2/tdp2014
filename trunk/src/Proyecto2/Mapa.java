package Proyecto2;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Proyecto2.Explosiones_Disparos.Disparo;
import Proyecto2.Explosiones_Disparos.Explosion;
import Proyecto2.Frames_Minds.Mind;
import Proyecto2.Frames_Minds.MindEnemies;
import Proyecto2.Naves.Enemigos.Artillero;
import Proyecto2.Naves.Enemigos.Basico;
import Proyecto2.Naves.Enemigos.Bombardero;
import Proyecto2.Naves.Enemigos.Enemigo;
import Proyecto2.Naves.Enemigos.Kamikaze;
import Proyecto2.Naves.Jugador.Jugador;

public class Mapa {
	
	protected ArrayList misilesEnemigos;
	protected ArrayList misilesJugador;
	protected ArrayList explosiones;
	protected Jugador jugador;
	protected ArrayList enemiesInWindow;
	protected int indiceEnemigos;
	protected int indiceExplosiones;
	protected Mind mind;
	protected MindEnemies mindEnemies;
	protected int cantEnemies;
	protected Random rn;
	
	
	public Mapa() {
		// Arreglo de disparos
		misilesEnemigos = new ArrayList();
		misilesJugador = new ArrayList();

		rn = new Random(5);
		cantEnemies = 100;
     		
		enemiesInWindow = new ArrayList();
		indiceEnemigos=0;
		
		explosiones = new ArrayList();
	}
	
	public int cantEnemies(){
		return cantEnemies - indiceEnemigos;
	}
	
	public ArrayList getEnemies() {
		return enemiesInWindow;
	}
	
	public void setEnemies(ArrayList enemies) {
		this.enemiesInWindow = enemies;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public void setMind(Mind mind) {
		this.mind = mind;
	}

	public void setMindEnemies(MindEnemies mindEnemies) {
		this.mindEnemies = mindEnemies;
	}
	
	public int sizeMisilesEnemigos() {
		return misilesEnemigos.size();
	}
	
	public int sizeMisilesJugador() {
		return misilesJugador.size();
	}
	
	public void addDisparoEnemigo(Disparo d) {
		misilesEnemigos.add(d);
	}
	
	public void addDisparoJugador(Disparo d) {
		misilesJugador.add(d);
	}
	
	public void removerDisparoEnemigo(int i) {
		misilesEnemigos.remove(i);
	}
	
	public void removerDisparoJugador(int i) {
		misilesJugador.remove(i);
	}

	public ArrayList getMisilesEnemigos() {
		return misilesEnemigos;
	}

	public ArrayList getMisilesJugador() {
		return misilesJugador;
	}
	
	public Enemigo nextEnemigo() {
		Enemigo m;
		if(indiceEnemigos < cantEnemies) {	
			int probabilidad = rn.nextInt(cantEnemies);
			
			if(probabilidad%5 == 0)
				m = new Kamikaze(probabilidad % 15 == 0);
			else if(probabilidad%13 == 0)
				m = new Bombardero();
			else if(probabilidad % 5 == 1) 
				m = new Artillero((probabilidad % 30) == 1);
			else
				m = new Basico(probabilidad % 11 == 0);
			
			m.setJugador(jugador);
            m.setMapa(this);
			
		} else {
			return null;
		}
		indiceEnemigos++;
		return m;
	}
	
	public void addExposion(Explosion ex) {
		explosiones.add(ex);
	}
	
	public Explosion getExplosion() {
		Explosion toRet=null;
		
		if(indiceExplosiones < explosiones.size()) {
			toRet= (Explosion) explosiones.get(indiceExplosiones);
			indiceExplosiones++;
		}
		
		return toRet;
	}
	
	public ArrayList explosiones() {
		return explosiones;
	}

	
	public void stop() {
		mind.stop();
		mindEnemies.setStop();
		
	}
}

