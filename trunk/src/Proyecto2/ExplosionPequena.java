package Proyecto2;

import java.net.URL;
import javax.swing.ImageIcon;

public class ExplosionPequena  extends Explosion{
	
	protected static URL url = (Nave.class.getClassLoader().getResource("img/Explosiones/pequena.gif"));
	
	public ExplosionPequena(int x, int y){
		super(x,y,new ImageIcon(url),20,20);
	}
	
	
}
