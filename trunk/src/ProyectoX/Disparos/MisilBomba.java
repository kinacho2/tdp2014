package ProyectoX.Disparos;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Frames.Explosion;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Sound.Reproductor;
import ProyectoX.Sound.Sonido;

public class MisilBomba extends Disparo{

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Bomba/misil.gif");
	private static final URL url2 = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Bomba/explosion.gif");
	private static final URL explode = Disparo.class.getClassLoader().getResource("ProyectoX/img/Explosiones/bomba.gif");
	private static final URL destroy = Disparo.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif");
	
	private static final String sonidoExplosion = "/ProyectoX/sounds/bombExplode.mp3";
	private static final String thunder = "/ProyectoX/sounds/thunder.mp3";

	
	private static final int defaultWidth = 10;
	private static final int defaultHeight = 30;
	private static final int defaultWidth2 = 300;
	private static final int defaultHeight2 = 300;
	
	
	protected Image misil;
	private long init;
	
	private int distancia;
	private int maxDistancia;
	
	//delay entre el disparo y la explosion
	private int minDelay;
	
	//delay en el que se destruyen los enemigos
	private int maxDelay;
	
	private boolean control = true;
	private boolean control2 = true;
	
	private Mapa mapa;
	
	public MisilBomba(int x, int y, Mapa mapa, Reproductor rep) {
		super(x, y, 0, 0, 1);
		ImageIcon ii =  new ImageIcon(url);
		misil = ii.getImage().getScaledInstance(defaultWidth, defaultHeight, Image.SCALE_DEFAULT);
		
		
		this.x = x - defaultWidth/2;
		this.y = y - defaultHeight;
		
		init = System.currentTimeMillis();
		
		minDelay = 5 * 100 - 1;
		maxDelay = minDelay + 9 * 100;
		
		maxDistancia = 200;
		
		width = 13;
		height = 30;
		
		this.mapa = mapa;
		
		damage = 2000;
		
		this.rep = rep;
		
	}
	
	public Image getImage(){
		return misil;
	}
	
	
	public void move(){
		long now = System.currentTimeMillis();
		
		
		if(isVisible()){
			y = y - velocidad;
			distancia ++;
			
			//cuando el misil recorre la distancia debe explotar, antes puede ser colisionado por un enemigo y destruirse
			if(control2 && distancia>=maxDistancia){
				control2 = false;
				
				//el misil explota y cambia de imagen, los enemigos que colisionen con la explosion seran destruidos
				ImageIcon ii =  new ImageIcon(url2);
				misil = ii.getImage().getScaledInstance(defaultWidth2, defaultHeight2, Image.SCALE_DEFAULT);
				
				//la imagen pasa a tener otro ancho y alto
				width = defaultWidth2;
				height = defaultHeight2;
				rep.addSound(new Sonido(sonidoExplosion,false));
				//se ajusta el x e y a la nueva imagen
				x = x - defaultWidth2/2 + defaultWidth /2;
				y = y - defaultHeight2/2 + defaultHeight/2;
				
				//se inisializa la variable en el tiempo determinado
				init = System.currentTimeMillis();
				velocidad = 0;
			}
			
			if(!control2 && now - init > minDelay){
				//se añade la explosion una sola vez al cumplirse el tiempo determinado
				if(control){
					control = false;
					Explosion aux = new Explosion(400, 300, new ImageIcon(explode), 800, 600);
					aux.setDelay(2000);
					mapa.addExposion(aux);
					rep.addSound(new Sonido(thunder,false));
				}
				//al llegar este tiempo se le indica al mapa que destruya los enemigos en pantalla a excepsion del Jefe
				if(now - init > maxDelay){
					super.setVisible();
					mapa.bomba();
				}
			}
		}
	}
	
	//al impactar no debe desaparecer por lo que el setVisible es redefinido
	
	public void setVisible(){
		if(control2){
			super.setVisible();
		}
	}

	public Explosion newExplosion(int altura){
		if(control2)
			return new Explosion(x + width/2, y + height / 2, new ImageIcon(destroy), width, height);
		else
			return new Explosion(x + width/2 - defaultWidth/2, y + height/2 - defaultHeight/2, new ImageIcon(destroy), defaultWidth, defaultHeight);
	}
	
}
