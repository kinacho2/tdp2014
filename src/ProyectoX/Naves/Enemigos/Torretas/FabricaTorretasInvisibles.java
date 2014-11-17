package ProyectoX.Naves.Enemigos.Torretas;

public class FabricaTorretasInvisibles extends FabricaTorretas{

	private boolean grande;
	
	public FabricaTorretasInvisibles(boolean grande){
		this.grande = grande;
	}
	public Torreta nuevaTorreta(int xx, int yy) {
		return new TorretaInvisible(xx,yy,grande);
	}

}
