 package ProyectoX.Mapas;

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
import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Kamikaze;
import ProyectoX.Naves.Enemigos.Jefes.*;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.PowerUps.Bomba;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.PowerUps.PowerUp;

public abstract class Mapa {
	
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
	protected EnemiesBuilder enBuilder;
	
	
	public Mapa() {
		
		// Arreglo de disparos
		misilesEnemigos = new ArrayList();
		misilesJugador = new ArrayList();
		
		jugadores = new ArrayList();
     	
		enemiesInWindow = new ArrayList();
		indiceEnemigos = 0;
		
		explosiones = new ArrayList();
		
		
		powerUps = new ArrayList();
	}
	
	public int cantEnemies(){
		return cantEnemies - indiceEnemigos + enemiesInWindow.size();
	}
	
	public ArrayList getEnemies() {
		return enemiesInWindow;
	}
	
	public void setEnemies(ArrayList enemies) {
		this.enemiesInWindow = enemies;
	}

	//retorna el arreglo que contiene al jugador y a su defensa
	
	public ArrayList getJugador() {
		return jugadores;
	}
	
	//Las defensas se comportan como un jugador por lo que se guarda un arreglo de jugadores
	
	public void setNewJugador(Jugador jugador) {
		jugadores.add(jugador);
	}
	
	//retorna al jugador principal, que en caso de tener escudo es el mismo escudo
	
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}
	
	//remueve la defensa o el jugador eliminado
	
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
	public Enemigo nextEnemigo(){
		Enemigo m = enBuilder.getNextEnemigo();
		
		m.setJugador(jugador);
        m.setMapa(this);
        indiceEnemigos++;
        
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
	
	/*
	 * Le indica al mapa que se deben eliminar todos los enemigos de pantalla
	*/

	public synchronized void bomba() {
		int puntaje = 0;
		for(int i = 0; i < enemiesInWindow.size(); i++){
			Enemigo m = (Enemigo) enemiesInWindow.get(i);
			puntaje += m.bomba();
		}
		jugador.setPuntaje(puntaje);
		
	}
	
	
}

