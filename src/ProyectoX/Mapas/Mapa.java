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
import ProyectoX.Paneles.PanelGame;
import ProyectoX.Paneles.PanelGameOver;
import ProyectoX.PowerUps.Bomba;
import ProyectoX.PowerUps.PUPBuilder;
import ProyectoX.PowerUps.PowerUp;
import ProyectoX.Sound.Reproductor;

/**
 * Clase que contiene los objetos principales del Juego, Enemigos, PowerUPs, Jugador, Disparos, Mentes, etc
 * Se encarga de controlar  las pausas, los stop y reset del juego
 * Contiene ademas el fondo del nivel, la musica y un arreglo de Objetos que son nubes
 * Junto con sus clases hijas implementa el patron State
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class Mapa{
	
	protected static final String nube = "ProyectoX/img/Fondos/nubes/nubes";
	
	protected Aplication api;
	protected JPanel panel;
	protected Reproductor reproductor;
	protected ArrayList misilesEnemigos;
	protected ArrayList misilesJugador;
	protected ArrayList explosiones;
	protected Jugador jugador;
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

	private boolean pauseTime = false;
	
	/**
	 * Constructor de la clase Mapa
	 * @param api la aplicacion que ejecutara el siguiente metodo al terminarse el SplashScreen
	 * @param game el panel donde se dibuja el juego
	 * @param rep Reproductor actual
	 */
	
	public Mapa(Aplication api, PanelGame  game, Reproductor rep) {
		this.game = game;
		this.api = api;
		
		misilesEnemigos = new ArrayList();
		misilesJugador = new ArrayList();
		
     	
		enemiesInWindow = new ArrayList();
		indiceEnemigos = 0;
		
		explosiones = new ArrayList();
		
		powerUps = new ArrayList();
		
		reproductor = rep;
		
		objetos = new ArrayList();
		objetosEnPantalla = new ArrayList();
		rn = new Random();
		for(int i = 1; i<= 5; i++){
			ImageIcon ii = new ImageIcon((Mapa.class.getClassLoader().getResource(nube+i+".png")));
			Objeto o = new Objeto(ii, rn.nextInt(700),-800,0,1,1);
			objetos.add(o);
		}
		
		
	}
	
	/**
	 * retorna la cantidad de enemigos que resta por eliminar
	 * @return entero que simboliza la cantidad de enemigos
	 */
	
	public int cantEnemies(){
		return cantEnemies - indiceEnemigos + enemiesInWindow.size();
	}
	
	/**
	 * retorna un ArrayList con los enemigos en pantalla
	 * @return ArrayList de Enemigo
	 */
	
	public ArrayList getEnemies() {
		return enemiesInWindow;
	}
	
	/**
	 * @return instancia actual de Mind
	 */
	
	public void setEnemies(Enemigo e) {
		enemiesInWindow.add(e);
	}
	
	/**
	 * @return instancia actual de Mind
	 */
	
	public Mind getMind(){
		return mind;
	}
	

	/**
	 * retorna al jugador principal, que en caso de tener escudo es el mismo escudo
	 * @return instancia de Jugador que contiene el Mapa
	 */

	
	public Jugador getJugador() {
		return jugador;
	}
	
	/**
	 * se setea un nuevo jugador al mapa
	 * @param jugador el nuevo jugador del Mapa
	 */
	
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}
	
	/**
	 * setea la nueva Mind al mapa y setea el mapa y el reproductor a la Mind
	 * @param mind controla el hilo de juego principal y al jugador
	 */
	
	public void setMind(Mind mind) {
		this.mind = mind;
		mind.setMapa(this);
		mind.addReproductor(reproductor);

	}
	
	/**
	 * setea la nueva MindEnemies al mapa y setea el mapa y el reproductor a la MindEnemies
	 * @param mindEnemies controla el hilo de los enemigos
	 */

	public void setMindEnemies(MindEnemies mindEnemies) {
		this.mindEnemies = mindEnemies;
		mindEnemies.setMapa(this);
		mindEnemies.setReproductor(reproductor);
	}
	
	/**
	 * agrega un nuevo Disparo enemigo al Mapa
	 * @param d nuevo Disparo del Enemigo
	 */
	
	public void addDisparoEnemigo(Disparo d) {
		misilesEnemigos.add(d);
	}
	
	/**
	 * agrega un nuevo Disparo jugador al Mapa
	 * @param d nuevo Disparo del Jugador
	 */
	
	public void addDisparoJugador(Disparo d) {
		misilesJugador.add(d);
		
	}
	
	/**
	 * remueve el Disparo del Jugador que se encuentra en el indice i
	 * @param i indice del arreglo
	 */
	
	public void removerDisparoJugador(int i) {
		misilesJugador.remove(i);
	}
	
	/**
	 * retorna una ArrayList con los Disparos enemigos
	 * @return ArrayList de Disparo
	 */

	public ArrayList getMisilesEnemigos() {
		return misilesEnemigos;
	}
	
	/**
	 * retorna una ArrayList con los Disparos del jugador
	 * @return ArrayList de Disparo
	 */

	public ArrayList getMisilesJugador() {
		return misilesJugador;
		
	}
	/**
	 * Crea un nuevo enemigo aleatoriamente
	 * en caso de no haber mas enemigos se setea el jefe a la MindEnemies
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
					
					m = jefe;
					mindEnemies.addBoss(jefe);
					estaJefe = mindEnemies.estaJefe();
					reproductor.stop(2000);
					if(estaJefe){
						reproductor.addSound(sonidoJefe,true);
						m.setJugador(jugador);
						m.setMapa(this);
						m.addReproductor(reproductor);
					}
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
	
	/**
	 * Agrega el explosion ex a la lista de explosiones
	 * @param ex nueva Explosion que se agrega al mapa
	 */
	public void addExposion(Explosion ex) {
		explosiones.add(ex);
	}
	
	/**
	 * devuelve las Explosiones que estan en el mapa
	 * @return ArrayList de Explosiones
	 */
	
	public ArrayList explosiones() {
		return explosiones;
	}
	
	/**
	 * devuelve los PowerUP que estan en el mapa
	 * @return ArrayList de PowerUP
	 */
	
	public ArrayList getPowers(){
		return powerUps;
	}
	
	/**
	 * genera un PowerUP aleatoriamente y lo agrega al mapa
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param bomba en caso de ser el jefe el que entrega PowerUP este sera una Bomba
	 */

	public void addPower(int x, int y, boolean bomba){
		PowerUp up;
		if(bomba){
			up = new Bomba(x,y);
		}else{
			up = power.getPowerUpRandom(x, y);
		}
		powerUps.add(up);
	}
	
	/**
	 * le indica a los entes del programa que se detengan
	 */
	public void stop() {
		mind.stop();
		mindEnemies.setStop();
		reproductor.stop(0);
		
	}
	
	/**
	 * Le indica al mapa que se deben eliminar todos los enemigos de pantalla
	 * en caso de ser un jefe solo eliminara las torretas que estan en la pantalla
	*/

	public synchronized void bomba() {
		int puntaje = 0;
		for(int i = 0; i < enemiesInWindow.size(); i++){
			Enemigo m = (Enemigo) enemiesInWindow.get(i);
			puntaje += m.bomba();
		}
		jugador.setPuntaje(puntaje);
		for(int i = 0; i < misilesEnemigos.size(); i++){
			((Disparo)misilesEnemigos.get(i)).setVisible();
			
		}
	}
	
	/**
	 * retorna la imagen del fondo del mapa
	 * @return instancia de Image
	 */
	
	public Image getImage(){
		return imagenFondo;
	}
	
	/**
	 * retorna la coordenada x del fondo
	 * @return coordenada x
	 */
	
	public int getX(){
		return x;
	}
	/**
	 * retorna la coordenada y del fondo y la modifica
	 * @return coordenada y
	 */
	
	public int getY(){
		if(delay % delayVel  == 0){
			y += dy;
		}
		delay = (delay + 1) % delayVel;
		return y;
	}
	
	/**
	 * indica un cambio de nivel 
	 */
	
	public abstract void nextMapa();
	
	/**
	 * agrega un objeto al azar a la lista de objetos de pantalla
	 * @return ArrayList de Objeto
	 */
	public ArrayList getObjeto(){
		
		if(rn.nextInt()%500 == 2){
			Objeto o = (Objeto)objetos.get(rn.nextInt(5));
			o.setX(rn.nextInt(700));
			objetosEnPantalla.add(o.clone());
		}
		return objetosEnPantalla;
	}
	
	/**
	 * le indica a los entes del juego que hay una pausa
	 * @param arg de tipo booleano si es true se setea una pausa si es false se saca la pausa
	 */

	public void pause(boolean arg) {
		mindEnemies.pause(arg);
		
		if(arg){
			dy = 0;
			reproductor.stop(1);
		}
		else{
			if(!pauseTime)
				dy = 1;
			reproductor.addSound(sonido, true);
		}
	}
	

	public void pauseTime(boolean arg) {
		mindEnemies.pauseTime(arg);
		mind.pauseTime(arg);
		pauseTime  = arg;
		if(arg){
			dy = 0;
		}
		else{
			dy = 1;
		}
	}
	
	/**
	 * hace un reset del nivel se encarga de sacar la pausa a los entes del juego
	 */

	public void reset(){
		jugador.setHearts();
		jugador.setHearts();
		jugador.setHearts();
		jugador.setHearts();
		panel.setVisible(false);
		
		api.setVisible(false);
		game.setVisible(true);
		mind.pause(false);
		mindEnemies.setEstaJefe();
		pause(false);
		api.setVisible(true);
	}
	
	/**
	 * indica que se finalizo el juego
	 */
	
	public void gameOver(){
		pause(true);
		mind.pause(true);
		game.setVisible(false);
		reproductor.stop(0);
		panel = new PanelGameOver(api,this,reproductor,mind.getJugador());
	}
	
	/**
	 * retorna la URL asociada a la imagen del SplashScreen que seguira al terminar el nivel
	 * @return URL de imagen
	 */
	public abstract URL getImagenSplash();

	public void bombarderoFuera() {
		enBuilder.bombarderoFuera();
	}

}

