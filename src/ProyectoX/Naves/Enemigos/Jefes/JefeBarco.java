package ProyectoX.Naves.Enemigos.Jefes;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasDobles;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasSimples;
import ProyectoX.Naves.Enemigos.Torretas.Torreta;

public class JefeBarco extends Jefe{

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/JefeBarco/JefeBarco.png"));
	protected static final String boundsDouble = "/ProyectoX/img/Enemigo/JefeBarco/posicionesTorretasDobles.txt";
	protected static final String boundsSimple = "/ProyectoX/img/Enemigo/JefeBarco/posicionesTorretasSimples.txt";
	
	
	private static final int defaultWidth = 385;
	private static final int defaultHeight = 2494;
	private static final int defaultVel = 10;
	
	
	public JefeBarco() {
		super(0, defaultVel, new ImageIcon(url), defaultWidth, defaultHeight);
		
		x = 400 - defaultWidth/2;
		y = - defaultHeight;
		
		int cantTorretasDobles = 9;
		int cantTorretasSimples = 6;
		
		cargarArchivoTorretas(boundsDouble, new FabricaTorretasDobles(), cantTorretasDobles);
		cargarArchivoTorretas(boundsSimple, new FabricaTorretasSimples(), cantTorretasSimples);
		puntaje = 600;
			
		
	}
	
	
}