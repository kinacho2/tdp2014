package ProyectoX.Disparos.Wave;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Disparos.DisparoJugador;
import ProyectoX.Disparos.Laser.LaserLVI;
import ProyectoX.Disparos.Multiplicador.MultiplicadorLVI;
import ProyectoX.Naves.Nave;

/**
 * Tercer nivel de DisparoWave
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */



public class WaveLVIV extends DisparoWave {

	private static boolean estado=true;
	private int contador=0;
	private long max = 100;
	private long init;
	/**
	 * Constructor de la clase WaveLVIII
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param dx diferencial x
	 * @param dy diferencial y
	 */
	
	public WaveLVIV(int x, int y, double dx, double dy,Nave nave,int contador) {
		super(x, y, dx, dy,nave);
		this.contador = contador;
		estado=true;
		damage = 1;
	}
	
	/**
	 * redefine nextLevel() de la clase DisparoJugador
	 * retorna una instancia si mismo
	 * @return instancia de Disparo de tipo dinamico WaveLVIII
	 */
	
	public DisparoJugador nextLevel(){
		return new WaveLVIV(x,y,dx,dy,nave,contador);
	}
	
	/**
	 * redefine cloneNivel() de la clase Disparo
	 * @return Disparo[] con 2 elementos de tipo dinamico DisparoWave y 4 elementos de tipo dinamico WaveLVIII
	 */
	
	public Disparo[] cloneNivel(){
		int max = 5;
		Disparo[] d = new Disparo[(contador<max)?14:10];
		estado=true;
		d[0] = new WaveLVIII(x  - 10 , y, 3, -1, nave);
		d[1] = new WaveLVIII(x  + 10 , y, -3, -1, nave);
		d[2] = new DisparoWave(x  - 10 , y, 2, 1, nave);
		d[3] = new DisparoWave(x  + 10 , y, -2, 1, nave);
		d[4] = new WaveLVIII(x  - 10 , y, 2, 1, nave);
		d[5] = new WaveLVIII(x  + 10 , y, -2, 1, nave);
		d[6] = new WaveLVI(x  + 10 , y, -1.5, 4, nave);
		d[7] = new WaveLVI(x  + 10 , y, 1.5, 4, nave);
		d[8] = new WaveLVI(x  + 10 , y, -1.5, -4, nave);
		d[9] = new WaveLVI(x  + 10 , y, 1.5, -4, nave);
		if(contador<max){
			d[10] = new WaveLVIV(x  - 10 , y, 1, 1, nave,0);
			d[11] = new WaveLVIV(x  + 10 , y, -1, -1, nave,0);
			d[12] = new WaveLVIV(x  - 10 , y, 1, -1, nave,0);
			d[13] = new WaveLVIV(x  + 10 , y, -1, 1, nave,0);
			contador++;
		}
		setearReproductor(d);
		return d;
	}

	/**
	 * redefine move() de la clase DisparoWave
	 * genera un movimiento sinuidal diferente
	 */
	
	public void move(){
		x=nave.getX() + nave.getWidth()/2 - width/2 + (int)(dx*200*Math.cos(3*variacion)*Math.cos(1*variacion)*Math.sin(variacion));
		y=nave.getY() + nave.getHeight()/2 - height/2 + (int)(dy*200*Math.sin(3*variacion)*Math.sin(1*variacion)*Math.cos(variacion));
		//System.out.println(x+"  "+100*(Math.cos(2*variacion)*Math.cos(variacion)));
		variacion = variacion + 0.02;
		//verificarColisionBorde();
		if(!nave.getVisible() || !estado){
			 super.setVisible();
		 }
	}
	
	public void setVisible(){
		 
	}
	
	/**
	 * retorna una instancia de tipo dinamico LaserLVI
	 * @return instancia de LaserLVI
	 */
	
	public DisparoJugador getLaser(){
		estado=false;
		return new LaserLVI(nave,1);
	}
	
	/**
	 * retorna una instancia de tipo dinamico MultiplicadorLVI
	 * @return instancia de MultiplicadorLVI
	 */
	
	public DisparoJugador getMultiplicador(){
		estado=false;
		return new MultiplicadorLVI(x,y,dx,dy,nave);
	}
	/*
	public synchronized boolean colision(Nave nave) {
		boolean verd = false;
		long aux = System.currentTimeMillis();
		if(aux  - init > max){
			
			init=System.currentTimeMillis();
			verd = super.colision(nave);
		}
		
		
		return verd;
	}
	*/
	
	public void reset() {
		contador=0;
	}
	
	
	public int getLevel() {
		return 4;
	}
}
