package ProyectoX.Naves.Enemigos.Jefes;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasDobles;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasSimples;
import ProyectoX.Naves.Enemigos.Torretas.Torreta;

public class JefeTanque extends Jefe{

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/JefeTanque/JefeTanque.png"));
	protected static final String boundsDouble = "/ProyectoX/img/Enemigo/JefeTanque/posicionesTorretasDobles.txt";
	protected static final String boundsSimple = "/ProyectoX/img/Enemigo/JefeTanque/posicionesTorretasSimples.txt";
	
	
	private static final int defaultWidth = 320;
	private static final int defaultHeight = 1376;
	private static final int defaultVel = 2;
	
	private boolean primero = true;
	
	
	public JefeTanque() {
		super(0, defaultVel, new ImageIcon(url), defaultWidth, defaultHeight);
		
		x = 400 - defaultWidth/2;
		y = - defaultHeight;
		
		int cantTorretasDobles = 9;
		int cantTorretasSimples = 5;
		
		cargarArchivoTorretas(boundsDouble, new FabricaTorretasDobles(), cantTorretasDobles);
		cargarArchivoTorretas(boundsSimple, new FabricaTorretasSimples(), cantTorretasSimples);
		
		puntaje = 500;
			
		
	}

}
