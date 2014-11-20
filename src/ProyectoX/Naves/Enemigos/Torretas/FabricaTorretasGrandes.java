package ProyectoX.Naves.Enemigos.Torretas;

/**
 * Esta clase es la encargada de crear instancias de TorretaGrande
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class FabricaTorretasGrandes extends FabricaTorretas{
	
	/**
	 * Funcion para crear torretas grandes, implementa el patron Abstract Factory
	 * @param xx coordenada y
	 * @param yy coordenada x
	 * @return nueva Torreta
	 */
	
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaGrande(xx,yy);
	}

}
