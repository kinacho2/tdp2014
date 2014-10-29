package Proyecto2.Naves.Enemigos.Torretas;

public class FabricaTorretasDobles extends FabricaTorretas{
	
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaDoble(xx,yy);
	}
}
