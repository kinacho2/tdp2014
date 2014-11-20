package ProyectoX.Naves.Enemigos.Torretas;

/**
 * Esta clase es la encargada de crear Torretas 
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */
public abstract class FabricaTorretas {
	
	/**
	 * Funcion para crear torretas, implementa el patron Abstract Factory
	 * @param xx coordenada y
	 * @param yy coordenada x
	 * @return nueva Torreta
	 */
	public abstract Torreta nuevaTorreta(int xx, int yy);
}
