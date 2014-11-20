package ProyectoX.Naves.Enemigos.Torretas;

/**
 * Esta clase es la encargada de crear instancias de TorretaSimple
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class FabricaTorretasSimples extends FabricaTorretas{
	
	/**
	 * Funcion para crear torretas simples, implementa el patron Abstract Factory
	 * @param xx coordenada y
	 * @param yy coordenada x
	 * @return nueva Torreta
	 */
	
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaSimple(xx,yy);
	}

}
