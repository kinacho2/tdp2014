package ProyectoX.Naves.Enemigos.Torretas;

public class FabricaTorretasSimples extends FabricaTorretas{
	
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaSimple(xx,yy);
	}

}
