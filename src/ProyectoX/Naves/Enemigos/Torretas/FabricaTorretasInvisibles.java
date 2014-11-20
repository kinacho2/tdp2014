package ProyectoX.Naves.Enemigos.Torretas;

/**
 * Esta clase es la encargada de crear instancias de TorretaInvisible
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class FabricaTorretasInvisibles extends FabricaTorretas{
	
	/**
	 * Funcion para crear torretas invisibles, implementa el patron Abstract Factory
	 * @param xx coordenada y
	 * @param yy coordenada x
	 * @return nueva Torreta
	 */
	
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaInvisible(xx,yy);
	}

}
