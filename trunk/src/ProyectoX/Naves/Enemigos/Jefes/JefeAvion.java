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
	
	private final int hm = 46;
	private final int h1 = 584;
	private final int h1a = 522;
	private final int h2 = 500;
	private final int h3 = 384;
	private final int h4 = 312;
	private final int h5 = 246;
	private final int h1w1 = 410;
	private final int h1w2 = 860;
	private final int h2w1 = 553;
	private final int h2w2 = 720;
	private final int h3w1 = 410;
	private final int h3w2 = 860;
	private final int h4w1 = 298;
	private final int h4w2 = 975;
	private final int h5w1 = 0;
	private final int h5w2 = 1275;
	
	private boolean primero = true;
	private boolean segundo = true;
	private boolean tercero = true;
	private boolean cuarto = true;
	private boolean quinto = true;
	
	
	public JefeAvion() {
		super(2000, defaultVel, new ImageIcon(url), defaultWidth, defaultHeight);
		
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
		
		if(y + defaultHeight < 400 && primero){
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
			if(y + defaultHeight > -100){
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


	public int getMovimiento() {
		return 0;
	}

	public boolean colision(Nave nave) {
		
		int[] arrayY = {h1a,h1,hm,h2,hm,h3,hm,h4,hm,h5};
		int[] arrayX = {h1w1,h1w2,h2w1,h2w2,h3w1,h3w2,h4w1,h4w2,h5w1,h5w2};
		boolean A,B,C,D,E,F,G,H;
		boolean fColision = false;
		int i = 0;
		
		while(i + 1 < arrayY.length && !fColision){
		
			A = x + arrayX[i] >= nave.getX();
			B = x + arrayX[i] <= (nave.getX() + nave.getWidth());
			C = y + arrayY[i] >= nave.getY();
			D = y + arrayY[i] <= (nave.getY() + nave.getHeight());
			E = (x + arrayX[i+1]) >= nave.getX();
			F = (x + arrayX[i+1]) <= (nave.getX() + nave.getWidth());
			G = (y + arrayY[i+1]) >= nave.getY();
			H = (y + arrayY[i+1]) <= (nave.getY() + nave.getHeight());
		
			// funcion de colicion que verifica si uno o mas puntos del borde del objeto nave intersectan con el borde del objeto que ejecuta la funcion
			fColision = fColision || (A && B || E && F) && (C && D || G && H) ||  !A && !F && ( !H && D || G && H) ||  !C &&  !H && (B &&  !F ||  !A && E);
			i = i+2;
		}
		
		return getVisible() && nave.getVisible() && fColision;
			
				
	}
	
	
	
	
}
