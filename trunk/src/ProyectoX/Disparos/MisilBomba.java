package ProyectoX.Disparos;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Explosiones.Explosion;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;

public class MisilBomba extends Disparo{

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Bomba/misil.gif");
	private static final URL url2 = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Bomba/explosion.gif");
	private static final URL explode = Disparo.class.getClassLoader().getResource("ProyectoX/img/Explosiones/bomba.gif");
	private static final URL destroy = Disparo.class.getClassLoader().getResource("ProyectoX/img/Explosiones/nave.gif");
	
	
	
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
	
	public MisilBomba(int x, int y, Mapa mapa) {
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
		
		damage = 200;
		
	}
	
	public Image getImage(){
		return misil;
	}
	
	//la bomba es una imagen gif por lo que no se mueve
	
	public void move(){
		long now = System.currentTimeMillis();
		
		
		if(isVisible()){
			y = y - velocidad;
			distancia ++;
			if(control2 && distancia>=maxDistancia){
				control2 = false;
				ImageIcon ii =  new ImageIcon(url2);
				misil = ii.getImage().getScaledInstance(defaultWidth2, defaultHeight2, Image.SCALE_DEFAULT);
				width = defaultWidth2;
				height = defaultHeight2;
				x = x - defaultWidth2/2 + defaultWidth /2;
				y = y - defaultHeight2/2 + defaultHeight/2;
				init = System.currentTimeMillis();
				velocidad = 0;
			}
			
			if(!control2 && now - init > minDelay){
				if(control){
					control = false;
					Explosion aux = new Explosion(400, 300, new ImageIcon(explode), 800, 600);
					aux.setDelay(5000);
					mapa.addExposion(aux);
					
				}
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
