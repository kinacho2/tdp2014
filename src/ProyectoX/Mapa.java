 package ProyectoX;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Explosiones.Explosion;
import ProyectoX.Frames_Minds.Mind;
import ProyectoX.Frames_Minds.MindEnemies;
import ProyectoX.Naves.Enemigos.Artillero;
import ProyectoX.Naves.Enemigos.Basico;
import ProyectoX.Naves.Enemigos.Bombardero;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Kamikaze;
import ProyectoX.Naves.Enemigos.Jefes.*;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.PowerUps.Bomba;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.PowerUps.PowerUp;

public class Mapa {
	
	protected ArrayList misilesEnemigos;
	protected ArrayList misilesJugador;
	protected ArrayList explosiones;
	protected Jugador jugador;
	protected ArrayList jugadores;
	protected ArrayList enemiesInWindow;
	protected int indiceEnemigos;
	protected int indiceExplosiones;
	protected Mind mind;
	protected MindEnemies mindEnemies;
	protected PUPBuilder power;
	protected ArrayList powerUps;
	protected int cantEnemies;
	protected Random rn;
	protected boolean jefe =false;
	
	
	public Mapa() {
		
		// Arreglo de disparos
		misilesEnemigos = new ArrayList();
		misilesJugador = new ArrayList();
		
		jugadores = new ArrayList();

		rn = new Random(7);
		cantEnemies = 101;
     	
		enemiesInWindow = new ArrayList();
		indiceEnemigos = 0;
		
		explosiones = new ArrayList();
		
		power = new PUPBuilder(7);
		powerUps = new ArrayList();
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

	public ArrayList getJugador() {
		return jugadores;
	}

	public void setNewJugador(Jugador jugador) {
		jugadores.add(jugador);
	}
	
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}
	
	public void removeJugador(Jugador jugador){
		jugadores.remove(jugador);
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
	
	public void removerDisparoJugador(int i) {
		misilesJugador.remove(i);
	}

	public ArrayList getMisilesEnemigos() {
		return misilesEnemigos;
	}

	public ArrayList getMisilesJugador() {
		return misilesJugador;
	}
	/*
	 * Crea un nuevo enemigo aleatoriamente
	 */
	public Enemigo nextEnemigo() {
		Enemigo m;
		if(indiceEnemigos < cantEnemies) {	
			int probabilidad = rn.nextInt(cantEnemies);
			
			if(probabilidad % 5 == 0)
				m = new Kamikaze(probabilidad % 15 == 0);
			else if(probabilidad % 13 == 0)
				m = new Bombardero();
			else if(probabilidad % 5 == 1) 
				m = new Artillero((probabilidad % 30) == 1);
			else
				m = new Basico(true);
			
			
			m.setJugador(jugador);
            m.setMapa(this);
            indiceEnemigos++;
            
            
		} else {
			if(!jefe){
				m = new JefeTanque();
				mindEnemies.addBoss((Jefe)m);
				jefe = true;
				m.setJugador(jugador);
				m.setMapa(this);
				m = null;
				
			}
			else{
				m = null;
			}
		}
		
		return m;
	}
	
	/*
	 * Agrega el explosion ex a la lista de explosivos 
	 */
	public void addExposion(Explosion ex) {
		explosiones.add(ex);
	}
	
	public ArrayList explosiones() {
		return explosiones;
	}
	
	public ArrayList getPowers(){
		return powerUps;
	}

	public void addPower(int x, int y, boolean bomba){
		PowerUp up;
		if(bomba){
			up = new Bomba(x,y);
		}else{
			up = power.getPowerUpRandom(x, y);
		}
		powerUps.add(up);
	}
	
	public void stop() {
		mind.stop();
		mindEnemies.setStop();
		
	}

	public synchronized void bomba() {
		for(int i = 0; i < enemiesInWindow.size(); i++){
			Enemigo m = (Enemigo) enemiesInWindow.get(i);
			m.bomba();
		}
		
	}
	
	
}

