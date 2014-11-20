package ProyectoX.Naves.Enemigos.Torretas;

/**
 * Esta clase es la encargada de crear instancias de TorretaDoble
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class FabricaTorretasDobles extends FabricaTorretas{
	
	/**
	 * Funcion para crear torretas dobles, implementa el patron Abstract Factory
	 * @param xx coordenada y
	 * @param yy coordenada x
	 * @return nueva Torreta
	 */
	
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaDoble(xx,yy);
	}
}
