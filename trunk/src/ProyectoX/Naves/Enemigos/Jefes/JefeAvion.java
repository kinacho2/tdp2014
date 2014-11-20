package ProyectoX.Naves.Enemigos.Jefes;

import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import ProyectoX.Frames.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasDobles;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasInvisibles;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasSimples;
import ProyectoX.Naves.Enemigos.Torretas.Torreta;

/**
 * JefeAvion es la clase que representa al 3er Jefe del juego,
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
*/

public class JefeAvion extends Jefe{

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/JefeAvion/JefeAvion.png"));
	protected static final String boundsDouble = "/ProyectoX/img/Enemigo/JefeAvion/posicionesTorretasDobles.txt";
	protected static final String boundsSimple = "/ProyectoX/img/Enemigo/JefeAvion/posicionesTorretasSimples.txt";
	protected static final String boundsInvisible = "/ProyectoX/img/Enemigo/JefeAvion/posicionesTorretasInvisibles.txt";
	private static final String alarm = "/ProyectoX/sounds/alarm.mp3";

	
	private static final int defaultWidth = 1275;
	private static final int defaultHeight = 636;
	private static final int defaultVel = 2;
	
	//alturas y anchos de los distintos cuadros de colision las hi son las alturas las wi son los anchos
	private final int hm = 55;
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
	private final int h5w1 = 133;
	private final int h5w2 = 1142;
	
	private boolean primero = false;
	private boolean segundo = false;
	private boolean tercero =  false;
	private boolean cuarto = false;
	private boolean quinto = false;
	private boolean sexto = false;
	private boolean septimo = false;
	private boolean octavo = false;
	private boolean random = true;
	private Random rn;
	private long init;
	private int delay = 5000;
	
	
	/**
	 * Constructor de la clase JefeAvion
	 */
	public JefeAvion() {
		super(2000, defaultVel, new ImageIcon(url), defaultWidth, defaultHeight);
		rn = new Random();
		x = 400 - defaultWidth/2;
		y = - defaultHeight;
		delay = 2;
		int cantTorretasDobles = 10;
		int cantTorretasSimples = 7;
		int cantTorretasInvisibles = 12;
		init = System.currentTimeMillis();
		cargarArchivoTorretas(boundsDouble, new FabricaTorretasDobles(), cantTorretasDobles);
		cargarArchivoTorretas(boundsSimple, new FabricaTorretasSimples(), cantTorretasSimples);
		cargarArchivoTorretas(boundsInvisible,new FabricaTorretasInvisibles(), cantTorretasInvisibles);
		puntaje = 500;
			
	}

	/**
	 * Genera 8 patrones de movimiento distintos y verifica si el jefe tiene torretas
	 * Ademas verifica las colisiones con el jugador
	 */
	public void move() {
		if(torretas.size() == 0){
			agregarExplosiones();
		}
		if(System.currentTimeMillis() - init > delay && !control[0])
			if(puedeMoverse()){
				if(random){
					int select = -1;
					boolean move = false;
					while(!move){
						select = rn.nextInt(4);
						if(select == 0 || select == 3){
							move = true;
						}
						if(select == 2 && hayTorretas(x + defaultWidth/2 + 190, x+defaultWidth)){
							move = true;
						}
						if(select == 1 && hayTorretas(x, x+defaultWidth/2 - 190)){
							move = true;
						}
					} 
	
					int auxX = x;
					int auxY = y;
					
					if(select == 0){
						primero = segundo = true;
						x = -237;
						y = -defaultHeight;
						velocidad = 4;
					}
					if(select == 1){
						tercero = cuarto = true;
						x = 800;
						y = 0;
						velocidad = 3;
					}
					if(select == 2){
						quinto = sexto = true;
						x = - defaultWidth;
						y = 0;
						velocidad = 3;
					}
					if(select == 3){
						septimo = octavo = true;
						x = -237;
						y = 800;
						velocidad = 5;
						reproductor.addSound(alarm, false);
					}
					actualizarTorretas(x-auxX, y-auxY);
					
					random = false;
				}
				
				
				//movimiento de arriba a abajo
				if(!tercero && !cuarto && !quinto && !sexto && !septimo && !octavo)
				if(primero && y + defaultHeight < 500){
					y +=velocidad;
					actualizarTorretas(0, +velocidad);
				}
				else{
					primero = false;
					if(y + defaultHeight > -100 && segundo){
						y-=velocidad;
						actualizarTorretas(0, -velocidad);
					}
					else {
						segundo = false;
						random = true;
						init = System.currentTimeMillis();
					}
				}
				
				//movimiento de derecha a izquierda
				if(!primero && !segundo && !quinto && !sexto && !septimo && !octavo)
				if(tercero && x > 250){	
					x-=velocidad;
					actualizarTorretas(-velocidad,0);
				}
				else{
					tercero = false;
					if(x < 900 && cuarto){
						x+=velocidad;
						actualizarTorretas(velocidad,0);
					}
					else{
						cuarto = false;
						random = true;
						init = System.currentTimeMillis();
					}
				
				}
				
				//movimiento de izquierda a derecha
				if(!primero && !segundo && !tercero && !cuarto && !septimo && !octavo)
				if(quinto && x + defaultWidth < 550){
					
					x+=velocidad;
					actualizarTorretas(velocidad,0);
				}
				else{
					quinto = false;
					if(x + defaultWidth > -100 && sexto){
						x-=velocidad;
						actualizarTorretas(-velocidad,0);
					}
					else{
						sexto = false;
						random = true;
						init = System.currentTimeMillis();
					}
				}
				
				//movimiento de abajo a arriba
				if(!primero && !segundo && !tercero && !cuarto && !quinto && !sexto)
				if(septimo && y > 250){
					y-=velocidad;
					actualizarTorretas(0,-velocidad);
				}
				else{
					septimo = false;
					if(y < 700 && octavo){
						y+=velocidad;
						actualizarTorretas(0,velocidad);
					}
					else{
						octavo = false;
						random = true;
						init = System.currentTimeMillis();
					}
				}
			}
		setMove();
	}
	
	/**
	 * actualiza la posicion de las torretas dependiendo del movimiento que siga el JefeAvion
	 * al salir de la pantalla y volver a entrar
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */

	private void actualizarTorretas(int dx, int dy){
		for(int i=0; i<torretas.size(); i++){
			Torreta t =(Torreta)torretas.get(i);
			t.setPosition(dx, dy);
			if(!t.getVisible()){
				torretas.remove(i);
			}
		}
	}
	
	/**
	 * Verifica que hay Torretas entre los dos valores de x pasados por parametro
	 * @param x1 offset menor
	 * @param x2 offset mayor
	 * @return true si hay Torretas entre ambos offset
	 */
	private boolean hayTorretas(int x1,int x2){
		boolean ret = false;
		int i = 0;
		while(!ret && i < torretas.size()){
			Torreta aux = (Torreta) torretas.get(i);
			ret = aux.getX() > x1 && aux.getX() < x2;
			i++;
		}
		return ret;
	}
	
	/**
	 * define agregarExplosiones() de la clase Jefe
	 * Agrega al mapa una serie de Explosiones al morir el Jefe
	 */
	
	protected void agregarExplosiones(){
		if(!control[0]){
			init = System.currentTimeMillis();
			control[0] = true;
			int[] array = {169, 256, 343, 432, 817, 907, 993, 1083};
			int expY = 117;
			int i = 0;
			URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/fuego.gif"));
			while(i<array.length){
				Explosion ex = new Explosion(x+array[i],y+expY, new ImageIcon(exp), 32,56);
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
				mapa.addExposion(new Explosion(x+ 430, y + 250, new ImageIcon(exp2), 180, 180));
				mapa.addExposion(new Explosion(x+ 850, y + 186, new ImageIcon(exp), 265, 265));
				mapa.addExposion(new Explosion(x+ 660, y + 320, new ImageIcon(exp), 180, 180));
			}
			
			if(System.currentTimeMillis() - init > 1500 && !control[2]){
				control[2] = true;
				addSonidoExplosion();
				URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
				URL exp2 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/ExplodeNave.gif"));
				mapa.addExposion(new Explosion(x+ 310, y + 150, new ImageIcon(exp2), 140, 140));
				mapa.addExposion(new Explosion(x+ 912, y + 289, new ImageIcon(exp), 140, 140));
				
			}
			
			if(System.currentTimeMillis() - init > 3000 && !control[3]){
				control[3] = true;
				addSonidoExplosion();
				URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
				URL exp2 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/ExplodeNave.gif"));
				
				mapa.addExposion(new Explosion(x+ 120, y + 1100, new ImageIcon(exp), 140, 140));
				mapa.addExposion(new Explosion(x+ 800, y + 324, new ImageIcon(exp2), 190, 140));
				mapa.addExposion(new Explosion(x+ 650, y + 424, new ImageIcon(exp2), 190, 140));
				
			}
			
			if(System.currentTimeMillis() - init > 4000 && !control[4]){
				control[4] = true;
				addSonidoExplosion();
				URL exp = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif"));
				URL exp2 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/ExplodeNave.gif"));
				URL exp3 = (Nave.class.getClassLoader().getResource("ProyectoX/img/Explosiones/player.gif"));

				mapa.addExposion(new Explosion(x+ 120, y + 1100, new ImageIcon(exp3), 140, 140));
				mapa.addExposion(new Explosion(x+ 800, y + 324, new ImageIcon(exp2), 190, 140));
				mapa.addExposion(new Explosion(x+ 650, y + 424, new ImageIcon(exp2), 190, 140));
				mapa.addExposion(new Explosion(x+ 430, y + 250, new ImageIcon(exp), 180, 180));
				mapa.addExposion(new Explosion(x+ 850, y + 186, new ImageIcon(exp3), 265, 265));
				mapa.addExposion(new Explosion(x+ 660, y + 320, new ImageIcon(exp), 180, 180));
			
			}
		}
		
	}

	/**
	 * redefine la funcion colision(Nave nave) de la clase Jefe
	 * la colision con esta instancia se da con las alas el cuerpo y la cola de la imagen
	 */

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
