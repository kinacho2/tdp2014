package ProyectoX.Naves.Enemigos.Torretas;

public class FabricaTorretasInvisibles extends FabricaTorretas{
	
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaInvisible(xx,yy);
	}

}
