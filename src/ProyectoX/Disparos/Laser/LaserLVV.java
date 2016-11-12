package ProyectoX.Disparos.Laser;

import java.net.URL;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Naves.Nave;

public class LaserLVV extends DisparoLaser{
	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser3.gif");
	private static final URL url2 = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser3-180.gif");
	private static final URL url3 = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser3-90.gif");
	private static final URL url4 = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser3-270.gif");
	private static final URL urlCarga = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Laser/laser1_carga.gif");
	
	
	/**
	 * Constructor de la clase LaserLVIII
	 * @param nave Nave que efectua el DisparoLaser
	 * @param dy direccion del laser
	 */
	private double dx;
	
	public LaserLVV(Nave nave, double dy, double dx) {
		super(dy,0,0, new ImageIcon(urlCarga), (dy==1)?new ImageIcon(url):new ImageIcon(url2), nave);
		
		x = nave.getX() + nave.getWidth()/2 - width/2;
		y = nave.getY() - height;
		damage = 8;
		
		if(dx==1){
			second = new ImageIcon(url3);
			
		}
		if(dx==2){
			second = new ImageIcon(url4);
			
		}
		this.dx = dx;
		
		setDelays(500, 2000);
	}

	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia de si mismo
	 * @return instancia de Disparo de tipo dinamico LaserLVIII
	 */
	
	public DisparoJugador nextLevel(){
		return new LaserLVV(nave, dy,dx);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * clona el tipo disparo y lo devuelve en un arreglo de disparos
	 * @return Disparo[] con 1 elemento de tipo dinamico LaserLVIII
	 */
	
	public Disparo[] cloneNivel(){
		long ret = System.currentTimeMillis();
		if(ret - init > totalDuracion || primerDisparo){
			if(primerDisparo){
				primerDisparo = false;
			}
			init = System.currentTimeMillis();
			Disparo[] toRet = new Disparo[4];
			toRet[0] = new LaserLVV(nave, dy,0);
			toRet[1] = new LaserLVV(nave, 0,0);
			toRet[2] = new LaserLVV(nave, dy,1);
			toRet[3] = new LaserLVV(nave, 0,2);
			toRet[0].setReproductor(rep);
			toRet[1].setReproductor(rep);
			toRet[2].setReproductor(rep);
			toRet[3].setReproductor(rep);
			return toRet;
		}
		else return new Disparo[0];
	}
	
	public void move(){
		if(System.currentTimeMillis() - init > minDuracion){
			verificarNuevaImagen();
		}
		
		if(dx==0){
			x = nave.getX() + nave.getWidth()/2 - width/2-3;
			y = nave.getY() - (int)(dy*height) - (int)(dy-1)*nave.getHeight();
		}
		else{
			y = nave.getY() + nave.getHeight()/2 - height/2;
			if(dx==1){
				x = nave.getX() + nave.getWidth()-5;
			}
			else{
				x = nave.getX() - width;
			}
		}
		
		if(System.currentTimeMillis() - init > maxDuracion){
			desarmar();
		}
	}
	
	public int getLevel() {
		return 4;
	}
}
