package ProyectoX.Naves.Enemigos;

import javax.swing.ImageIcon;

public class Rafaga extends Enemigo {

	private static final int defaultWidth = 60;
	private static final int defaultHeight = 48;
	private static final int defaultVel = 5;
	private static final int defaultVida = 1;
	
	
	public Rafaga(){
		super(defaultVida,defaultVel,new ImageIcon(),defaultWidth,defaultHeight);	}


	@Override
	public boolean isEspecial() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void disparar() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
}
