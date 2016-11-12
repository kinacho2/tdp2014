package ProyectoX.Naves.Enemigos.Jefes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ProyectoX.Frames.Explosion;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretas;
import ProyectoX.Naves.Enemigos.Torretas.Torreta;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Sound.Reproductor;

/**
  * Jefe es la clase que representa a los Jefes del juego, hay uno por cada nivel,
  * tienen patrones de movimiento determinados y un arreglo de Enemigos de tipo Torreta
  * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public abstract class Jefe extends Enemigo{
	
	protected static final URL explode = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/grande.gif"));
	protected boolean[] control = {false,false,false,false,false};
	private boolean primero = true;
	protected ArrayList torretas;
	protected long init;
	/**
	 * Constructor de la clase Jefe
	 * @param vida no se utiliza porque la vida del Jefe son sus Torretas
	 * @param vel la cantidad de pixeles que se mueve por iteracion
	 * @param ii ImageIcon que guarda la Image
	 * @param w ancho
	 * @param h altura
	 */
	
	public Jefe(int vida, int vel, ImageIcon ii, int w, int h) {
		super(vida, vel, ii, w, h);
		torretas = new ArrayList();
		width = 0;
		height = 0;
	}

	/**
	 * A partir de una fabrica, una ubicacion relativa y una cantidad determinada 
	 * lee de un fichero las coordenadas x e y de cada Torreta, las crea y las agrega al arreglo de Torretas
	 * @param bounds path relativo del archivo de texto que contiene las coordenadas de cada Torreta, ordenadas como "x y " 
	 * @param fabrica de tipo FabricaTorretas es quien genera las torretas a partir de las coordenadas x, y
	 * @param cantTorretas la cantidad de Torretas que se van a crear asi como tambien la cantidad de numeros que tiene que leer la funcion
	 */
	
	protected void cargarArchivoTorretas(String bounds, FabricaTorretas fabrica, int cantTorretas){
		try {
			
			InputStream arch = this.getClass().getResourceAsStream(bounds);
            DataInputStream lector = new DataInputStream(arch);
			
			
			for(int i =0; i<cantTorretas; i++){
				String ss = "";
				char c = (char) lector.read();
				
				while(c!=' '){
					ss+=c;
					c = (char) lector.read();
					
				}
				
				int dx =  new Integer(ss).intValue();
				ss = "";
				c = (char) lector.read();
				while(c!=' '){
					ss+=c;
					c = (char) lector.read();
					
				}
				int dy =  new Integer(ss).intValue();
				
				torretas.add(fabrica.nuevaTorreta(x + dx , y + dy));
			}
			
			lector.close();
			arch.close();
		} catch ( IOException e) {
			
		}
	}
	
	/**
	 * no se realiza la accion de disparar por los tanto esta funcion solo verifica las colisiones
	 */
	public void disparar() {
		verificarColision();
	}
	
	/**
	 * esta funcion retorna siempre false
	 * @return false
	 */

	public boolean isEspecial() {
		return false;
	}
	
	/**
	 * retorna las torretas que tiene la instancia
	 * @return ArrayList de Torreta
	 */
	
	public ArrayList getTorretas(){
		return torretas;
	}
	
	/**
	 * redefine setJugador de la clase Nave
	 * setea el Jugador a la instancia y a cada una de sus torretas
	 * @param jugador nueva instancia de Jugador 
	 */
	
	public void setJugador(Jugador jugador){
		super.setJugador(jugador);
		for(int i=0; i<torretas.size(); i++){
			Torreta t =(Torreta)torretas.get(i);
			t.setJugador(jugador);
		}
	}
	
	/**
	 * esta funcion esta vacia debido a que la vida del Jefe son sus Torretas
	 */
	
	public void setVida(int vd){
		
	}
	
	/**
	 * esta funcion retorna siempre false
	 * el Jugador no debe colisionar con el Jefe a menos que se lo especifique
	 * @param n de tipo Nave
	 * @return false
	 */
	
	public boolean colision(Nave n){
		return false;
	}
	
	/**
	 * redefine setMapa de la clase Nave
	 * setea el Mapa a la instancia y a cada una de sus torretas
	 * @param map nueva instancia de Mapa
	 */
	
	public void setMapa(Mapa map){
		super.setMapa(map);
		for(int i=0; i<torretas.size(); i++){
			Torreta t =(Torreta)torretas.get(i);
			t.setMapa(map);
		}
	}
	
	/**
	 * redefine bomba() de la clase Enemigo
	 * la bomba no afecta al fefe por lo que esta funcion solo retorna 0
	 * @return 0
	 */
	
	public int bomba(){
		return 0;
	}
	
	/**
	 * redefine addReproductor de la clase Nave
	 * setea el Reproductor a la instancia y a cada una de sus torretas
	 * @param rep nueva instancia de Reproductor 
	 */
	
	public void addReproductor(Reproductor rep){
		reproductor = rep;
		for(int i =0; i<torretas.size(); i++){
			Torreta torr = (Torreta) torretas.get(i);
			torr.addReproductor(rep);
		}
	}
	
	/**
	 * devuelve la posicion en y de la torreta mas baja o mas alta dependiendo del booleano pos
	 * @param pos booleano, si es true se retorna la coordenada y de la Torreta que esta mas abajo de todas, en caso contrario devuelve la coordenada y de la Torreta que esta mas arriba
	 * @return coordenada y de la Torreta
	 */
	
	protected int torretaMasBaja(boolean pos){
		int res = 0;
		if(torretas.size() > 0){
			int i = 0;
			Torreta aux = (Torreta) torretas.get(i);
			res = aux.getY();
			while(i+1 < torretas.size()){
				aux = (Torreta) torretas.get(i);
				if(pos){
					if(res < aux.getY()){
						res = aux.getY();
					}
				}
				else{
					if(res > aux.getY()){
						res = aux.getY();
					}
				}
				i++;
			}
		}
		return res;
		
		
	}
	
	/**
	 * realiza un movimiento de arriba hacia abajo, 
	 * y luego de abajo hacia arriba dependiendo de 
	 * la ubicacion de las Torretas visibles,
	 * mueve las Torretas que contiene y
	 * tambien comprueba que queden Torretas en el arreglo,
	 * de no ser asi el Jefe es destruido
	 */
	
	public void move() {
		if(puedeMoverse()){
			if(torretas.size() == 0){
				agregarExplosiones();
				mapa.setdy(0);
			}
			else
				if(torretaMasBaja(false) < 200 && primero){
					y +=velocidad;
					mapa.setdy(1);
					for(int i=0; i<torretas.size(); i++){
						Torreta t =(Torreta)torretas.get(i);
						t.setPosition(0, +velocidad);
						if(!t.getVisible()){
							torretas.remove(i);
						}
					}
				}
				else{
					primero = false;
					if(torretaMasBaja(true) > -100){
						y-=velocidad;
						mapa.setdy(-1);
						for(int i=0; i<torretas.size(); i++){
							Torreta t =(Torreta)torretas.get(i);
							t.setPosition(0, -velocidad);
							if(!t.getVisible()){
								torretas.remove(i);
							}
						}
					}
					else
						primero = true;
				}
		}
		setMove();
		
	}
	
	/**
	 * agrega explosiones en determinados lugares del jefe cuando este fue destruido
	 * ademas le da la puntuacion correspondiente al jugador
	 */
	
	protected abstract void agregarExplosiones();
	
	
	/**
	 * redefine getExplosion() de la clase Enemigo
	 * retorna la Explosion tipica de los Jefes
	 * @return instancia de Explosion
	 */
	
	public Explosion getExplosion(){
		
		addSonidoExplosion();
		Explosion aux = new Explosion(x + width/2, y + height/2, new ImageIcon(explode), width, height);
		aux.setDelay(3000);
		
		return aux;
	}
	
	public void setVisible(){
		
	}

	public void destroy() {
		super.setVisible();
	}
	
	public boolean isInvulnerable(){
		return true;
	}
}
