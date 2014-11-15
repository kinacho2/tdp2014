package ProyectoX.Naves.Enemigos.Torretas;

public class FabricaTorretasGrandes extends FabricaTorretas{
	
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaGrande(xx,yy);
	}

}
