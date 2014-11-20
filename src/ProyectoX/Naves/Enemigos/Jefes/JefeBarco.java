package ProyectoX.Naves.Enemigos.Jefes;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Frames.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasDobles;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasGrandes;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasInvisibles;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasSimples;

/**
 * JefeBArco es la clase que representa al 2do Jefe del juego,
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
*/

public class JefeBarco extends Jefe{

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/JefeBarco/JefeBarco.png"));
	protected static final String boundsDouble = "/ProyectoX/img/Enemigo/JefeBarco/posicionesTorretasDobles.txt";
	protected static final String boundsSimple = "/ProyectoX/img/Enemigo/JefeBarco/posicionesTorretasSimples.txt";
	protected static final String boundsGrande = "/ProyectoX/img/Enemigo/JefeBarco/posicionesTorretasGrandes.txt";
	protected static final String boundsInvisible = "/ProyectoX/img/Enemigo/JefeBarco/posicionesTorretaInvisibles.txt";
	
	
	private static final int defaultWidth = 385;
	private static final int defaultHeight = 2494;
	private static final int defaultVel = 2;
	
	private long init;
	
	
	/**
	 * Constructor de la clase JefeBarco
	 */
	
	public JefeBarco() {
		super(0, defaultVel, new ImageIcon(url), defaultWidth, defaultHeight);
		
		x = 400 - defaultWidth/2;
		y = - defaultHeight;
		
		int cantTorretasDobles = 10;
		int cantTorretasSimples = 5;
		int cantTorretasGrandes = 4;
		int cantTorretasInvisibles = 15;
		
		cargarArchivoTorretas(boundsDouble, new FabricaTorretasDobles(), cantTorretasDobles);
		cargarArchivoTorretas(boundsSimple, new FabricaTorretasSimples(), cantTorretasSimples);
		cargarArchivoTorretas(boundsGrande, new FabricaTorretasGrandes(), cantTorretasGrandes);
		cargarArchivoTorretas(boundsInvisible,new FabricaTorretasInvisibles(), cantTorretasInvisibles);
		
		puntaje = 600;
			
		
	}

	/**
	 * define agregarExplosiones() de la clase Jefe
	 * Agrega al mapa una serie de Explosiones al morir el Jefe
	 */

	protected void agregarExplosiones() {
		if(!control[0]){
			init = System.currentTimeMillis();
			control[0] = true;
			int[] array = {77, 275, 184, 874, 87, 1063, 256, 641};
			int expY = 117;
			int i = 0;
			URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
			while(i<array.length){
				Explosion ex = new Explosion(x+array[i],y+expY, new ImageIcon(exp), 32,32);
				ex.setDelay(5000);
				mapa.addExposion(ex);
				i++;
			}
		}
		else{ 
			if(System.currentTimeMillis() - init > 5000){
				jugador.setPuntaje(puntaje);
				mapa.addPower(400, 200, true);
				setVisible();
				width = defaultWidth;
				height = defaultHeight;
			}
			if(System.currentTimeMillis() - init > 1000 && !control[1]){
				control[1] = true;
				addSonidoExplosion();
				URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/player.gif"));
				URL exp2 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/ExplodeNave.gif"));
				mapa.addExposion(new Explosion(x+ 45, y + 421, new ImageIcon(exp2), 180, 180));
				mapa.addExposion(new Explosion(x+ 95, y + 1634, new ImageIcon(exp), 265, 265));
				mapa.addExposion(new Explosion(x+ 205, y + 2001, new ImageIcon(exp), 180, 180));
			}
			
			if(System.currentTimeMillis() - init > 1500 && !control[2]){
				control[2] = true;
				addSonidoExplosion();
				URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
				URL exp2 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/ExplodeNave.gif"));
				mapa.addExposion(new Explosion(x+ 210, y + 1503, new ImageIcon(exp2), 140, 140));
				mapa.addExposion(new Explosion(x+ 34, y + 289, new ImageIcon(exp), 140, 140));
				
			}
			
			if(System.currentTimeMillis() - init > 3000 && !control[3]){
				control[3] = true;
				addSonidoExplosion();
				URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
				URL exp2 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/ExplodeNave.gif"));
				
				mapa.addExposion(new Explosion(x+ 350, y + 1100, new ImageIcon(exp), 140, 140));
				mapa.addExposion(new Explosion(x+ 230, y + 324, new ImageIcon(exp2), 190, 140));
				mapa.addExposion(new Explosion(x+ 101, y + 1723, new ImageIcon(exp2), 190, 140));
				
			}
			
			if(System.currentTimeMillis() - init > 4000 && !control[4]){
				control[4] = true;
				addSonidoExplosion();
				URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
				URL exp2 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/ExplodeNave.gif"));
				URL exp3 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/player.gif"));

				mapa.addExposion(new Explosion(x+ 120, y + 1100, new ImageIcon(exp3), 140, 140));
				mapa.addExposion(new Explosion(x+ 240, y + 2005, new ImageIcon(exp2), 190, 140));
				mapa.addExposion(new Explosion(x+ 310, y + 195, new ImageIcon(exp2), 190, 140));
				mapa.addExposion(new Explosion(x+ 43, y + 678, new ImageIcon(exp), 180, 180));
				mapa.addExposion(new Explosion(x+ 195, y + 987, new ImageIcon(exp3), 265, 265));
				mapa.addExposion(new Explosion(x+ 345, y + 320, new ImageIcon(exp), 180, 180));
			
			}
		}
		
	}
}