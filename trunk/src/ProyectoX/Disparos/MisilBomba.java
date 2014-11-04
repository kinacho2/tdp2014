package ProyectoX.Disparos;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Mapa;
import ProyectoX.Explosiones.Explosion;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;

public class MisilBomba extends Disparo{

	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Bomba/misil.gif");
	private static final URL explode = Disparo.class.getClassLoader().getResource("ProyectoX/img/Explosiones/bomba.gif");
	
	private static final int defaultWidth = 100;
	private static final int defaultHeight = 204;
	
	
	protected Image misil;
	private int delay = 0;
	//delay entre la carga y el disparo
	private int minDelay;
	//delay que indica el tiempo que permanece el disparo en la pantalla
	private int maxDelay;
	
	boolean control = true;
	
	private Mapa mapa;
	
	public MisilBomba(int x, int y, Mapa mapa) {
		super(x, y, 0, 0, 0);
		ImageIcon ii =  new ImageIcon(url);
		misil = ii.getImage().getScaledInstance(defaultWidth, defaultHeight, Image.SCALE_DEFAULT);
		
		
		this.x = x - defaultWidth/2 + 65;
		this.y = y - defaultHeight;
		
		minDelay = 150;
		maxDelay = 220;
		
		width = 13;
		height = 30;
		
		this.mapa = mapa;
		
	}
	
	public Image getImage(){
		return misil;
	}
	
	//la bomba es una imagen gif por lo que no se mueve
	
	public void move(){
		delay++;
		if(delay > minDelay){
			if(control){
				Explosion aux = new Explosion(400, 300, new ImageIcon(explode), 800, 600);
				aux.setDelay(5000);
				mapa.addExposion(aux);
				control = false;
			}
			if(delay > maxDelay){
				super.setVisible();
				mapa.bomba();
			}
		}
	}
	
	//al impactar no debe desaparecer por lo que el setVisible es redefinido
	
	public void setVisible(){
		
	}

}
