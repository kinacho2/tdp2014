 package ProyectoX.Mapas;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;










import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ProyectoX.Aplication;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Frames.Explosion;
import ProyectoX.Frames.Objeto;
import ProyectoX.Minds.Mind;
import ProyectoX.Minds.MindEnemies;
import ProyectoX.Naves.Enemigos.EnemiesBuilder;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Jefes.*;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Paneles.AbstractPanel;
import ProyectoX.Paneles.PanelGame;
import ProyectoX.Paneles.PanelGameOver;
import ProyectoX.PowerUps.Bomba;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.PowerUps.PowerUp;
import ProyectoX.Sound.Reproductor;
import ProyectoX.Sound.Sonido;

public abstract class Mapa{
	
	protected static final String nube = "ProyectoX/img/Fondos/nubes/nubes";
	
	protected Aplication api;
	protected JPanel panel;
	protected Reproductor reproductor;
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
	protected boolean estaJefe =false;
	protected EnemiesBuilder enBuilder;
	protected Jefe jefe;
	
	protected Image imagenFondo;
	protected ArrayList objetos;
	protected ArrayList objetosEnPantalla;
	protected int x;
	protected int y;
	protected int dy = 1;
	protected int delay;
	protected int delayVel = 7;
	protected String sonido;
	protected String sonidoJefe;


	protected PanelGame game;
	
	public Mapa(Aplication api, PanelGame  game) {
		this.game = game;
		this.api = api;
		// Arreglo de disparos
		misilesEnemigos = new ArrayList();
		misilesJugador = new ArrayList();
		
		jugadores = new ArrayList();
     	
		enemiesInWindow = new ArrayList();
		indiceEnemigos = 0;
		
		explosiones = new ArrayList();
		
		powerUps = new ArrayList();
		
		reproductor = new Reproductor();
		
		objetos = new ArrayList();
		objetosEnPantalla = new ArrayList();
		rn = new Random();
		for(int i = 1; i<= 5; i++){
			ImageIcon ii = new ImageIcon((Mapa.class.getClassLoader().getResource(nube+i+".png")));
			Objeto o = new Objeto(ii, rn.nextInt(700),-800,0,1,1);
			objetos.add(o);
		}
		
		
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

	//retorna al jugador principal, que en caso de tener escudo es el mismo escudo

	
	public Jugador getJugador() {
		return jugador;
	}
	
	//se setea un nuevo jugador al mapa
	
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}
	

	
	
	public void setMind(Mind mind) {
		this.mind = mind;
		mind.setMapa(this);
		mind.addReproductor(reproductor);

	}

	public void setMindEnemies(MindEnemies mindEnemies) {
		this.mindEnemies = mindEnemies;
		mindEnemies.setMapa(this);
		mindEnemies.setReproductor(reproductor);
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
		Enemigo m;
		if(indiceEnemigos < cantEnemies) {	
			m = enBuilder.getNextEnemigo();
			
			m.setJugador(jugador);
	        m.setMapa(this);
	        m.addReproductor(reproductor);
	        indiceEnemigos++;  
		} else {
			if(!estaJefe){
				if(enemiesInWindow.size() == 0){
					reproductor.stop(1000);
					reproductor.addSound(sonidoJefe,true);
					m = jefe;
					mindEnemies.addBoss(jefe);
					estaJefe = true;
					m.setJugador(jugador);
					m.setMapa(this);
					m.addReproductor(reproductor);
					m = null;
				}
				else{
					m = null;
				}
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
		reproductor.stop(0);
		
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
	
	public Image getImage(){
		return imagenFondo;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		if(delay % delayVel  == 0){
			y++;
		}
		delay = (delay + 1) % delayVel;
		return y;
	}
	
	public abstract void nextMapa();
	
	public ArrayList getObjeto(){
		
		if(rn.nextInt()%500 == 2){
			Objeto o = (Objeto)objetos.get(rn.nextInt(5));
			o.setX(rn.nextInt(700));
			objetosEnPantalla.add(o.clone());
		}
		return objetosEnPantalla;
	}

	public void pause(boolean arg) {
		mindEnemies.pause(arg);
		if(arg)
			dy = 0;
		else
			dy = 1;
	}

	public void reset(){
		jugador.setHearts();
		jugador.setHearts();
		jugador.setHearts();
		panel.setVisible(false);
		api.setVisible(false);
		game.setVisible(true);
		mind.pause(false);
		pause(false);
		api.setVisible(true);
	}
	
	public void gameOver(){
		pause(true);
		mind.pause(true);
		game.setVisible(false);
		reproductor.stop(0);
		panel = new PanelGameOver(api,this);
	}
	
}

