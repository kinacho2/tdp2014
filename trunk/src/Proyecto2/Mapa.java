package Proyecto2;

import java.util.ArrayList;
import java.util.Random;

public class Mapa {
	protected ArrayList misilesEnemigos;
	protected ArrayList misilesJugador;
	protected Jugador jugador;
	protected ArrayList enemies;
	protected ArrayList enemiesInWindow;
	protected int indiceEnemigos;
	
	
	public Mapa(){
		misilesEnemigos = new ArrayList();
		misilesJugador = new ArrayList();

		enemies = new ArrayList();
		Random rn = new Random(17);
		
		
		for (int i = 0; i < 100; i++ ) {
			int probabilidad = rn.nextInt(100);
			Enemigo m;
			
			if(probabilidad%5 == 0)
				m = new Kamikaze();
			else if(probabilidad%13 == 0)
				m = new Bombardero();
			else
				m = new Basico();
			
            m.setMapa(this);
            
            enemies.add(m);
		}
		enemiesInWindow = new ArrayList();
		indiceEnemigos=0;
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
		for(int i = 0; i < enemies.size(); i++){
			Enemigo m = (Enemigo) enemies.get(i);
			m.setJugador(jugador);
		}
	}
	
	public int sizeMisilesEnemigos(){
		return misilesEnemigos.size();
	}
	
	public int sizeMisilesJugador(){
		return misilesJugador.size();
	}
	
	public Disparo getMisilEnemigos(int i){
		return (Disparo) misilesEnemigos.get(i);
	}
	
	public Disparo getMisilJugador(int i){
		return (Disparo) misilesJugador.get(i);
	}
	
	public void addDisparoEnemigo(Disparo d){
		misilesEnemigos.add(d);
	}
	
	public void addDisparoJugador(Disparo d){
		misilesJugador.add(d);
	}
	
	public void removerDisparoEnemigo(int i){
		misilesEnemigos.remove(i);
	}
	
	public void removerDisparoJugador(int i){
		misilesJugador.remove(i);
	}

	public ArrayList getMisilesEnemigos() {
		return misilesEnemigos;
	}

	public ArrayList getMisilesJugador() {
		return misilesJugador;
	}
	
	public Enemigo nextEnemigo(){
		Enemigo m;
		if(indiceEnemigos < enemies.size()){
			m= (Enemigo) enemies.get(indiceEnemigos);
		}
		else{
			return null;
		}
		indiceEnemigos++;
		return m;
	}
	
}
