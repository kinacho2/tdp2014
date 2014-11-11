package ProyectoX.Naves.Enemigos.Jefes;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasDobles;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasSimples;
import ProyectoX.Naves.Enemigos.Torretas.Torreta;

public class JefeAvion extends Jefe{

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/JefeAvion/JefeAvion.png"));
	protected static final String boundsDouble = "/ProyectoX/img/Enemigo/JefeAvion/posicionesTorretasDobles.txt";
	protected static final String boundsSimple = "/ProyectoX/img/Enemigo/JefeAvion/posicionesTorretasSimples.txt";
	
	
	private static final int defaultWidth = 1275;
	private static final int defaultHeight = 636;
	private static final int defaultVel = 2;
	
	private boolean primero = true;
	
	
	public JefeAvion() {
		super(0, defaultVel, new ImageIcon(url), defaultWidth, defaultHeight);
		
		x = 400 - defaultWidth/2;
		y = - defaultHeight;
		
		int cantTorretasDobles = 9;
		int cantTorretasSimples = 5;
		
		cargarArchivoTorretas(boundsDouble, new FabricaTorretasDobles(), cantTorretasDobles);
		cargarArchivoTorretas(boundsSimple, new FabricaTorretasSimples(), cantTorretasSimples);
		
		puntaje = 500;
			
		
	}


	public void move() {
		if(torretas.size() == 0){
			jugador.setPuntaje(puntaje);
			mapa.addPower(400, 200, true);
			setVisible();
			width = defaultWidth;
			height = defaultHeight;
			
		}
		
		if(y < 100 && primero){
			y +=velocidad;
			for(int i=0; i<torretas.size(); i++){
				Torreta t =(Torreta)torretas.get(i);
				t.setPosition(0, +velocidad);
				if(!t.getVisible()){
					torretas.remove(i);
				}
			}
		}
		else{
			primero = false;
			if(y + defaultHeight > 100){
				y-=velocidad;
				for(int i=0; i<torretas.size(); i++){
					Torreta t =(Torreta)torretas.get(i);
					t.setPosition(0, -velocidad);
					if(!t.getVisible()){
						torretas.remove(i);
					}
				}
			}
			else
				primero = true;
		}
		
	}

	
	
	
	
}
