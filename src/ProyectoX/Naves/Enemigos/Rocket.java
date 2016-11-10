package ProyectoX.Naves.Enemigos;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ProyectoX.Disparos.Disparo;
import ProyectoX.Frames.Explosion;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Jugador.Jugador;

public class Rocket extends Kamikaze{
	
	private Nave objetivo;
	private static final URL url = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Bomba/rocket.png");
	private static final URL explode = Disparo.class.getClassLoader().getResource("ProyectoX/img/Disparos/Bomba/explosion.gif");

	
	public Rocket(int vd,int vel){
		super(false);
		vida=50;
		width = 10;
		height = 23;
		velocidad = vel;
		jugador= null;
		image = new ImageIcon(url).getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
		vida = vd;
	}
	
	/*cuando lo crea un enemigo le setea el jugador
	 * 
	 * */
	public void setJugador(Jugador jugador) {
		if(mapa==null){
			super.setJugador(jugador);
			objetivo = jugador;
		}
	}
	
	public void setMapa(Mapa map) {
		mapa = map;
		ArrayList array = mapa.getEnemies();
		if(array.size()>0){
			objetivo = (Enemigo) array.get(0);
		}
		
		Nave aux;
		for (int j = 1; j < array.size(); j++ ) {
			aux = (Enemigo) array.get(j);
			if(distancia(aux)<distancia(objetivo) && aux.getHeight()>1 || objetivo.getHeight()<5)
				objetivo=aux;
		}
	}
	
	private double distancia(Nave n){
		int nx = n.getX() +n.getWidth()/2;
		int ny = n.getY() + n.getHeight()/2;
		int tx = this.x + this.width/2;
		int ty = this.y + this.height/2;
		double dis = Math.sqrt((nx-tx)*(nx-tx)+(ny-ty)*(ny-ty));
		
		return dis;
	}
	
	public synchronized void move() {
		
			if(objetivo!=null && objetivo.getVisible()){
				dx = objetivo.getX() - x;
				dy = objetivo.getY() - y;
				double mod = Math.sqrt(dy*dy+dx*dx);
				setRotacion();
				
				//centra la direccion del Enemigo hacia el jugador
				dx += objetivo.getWidth() / 2;
				dy += objetivo.getHeight() / 2;
				
				if (dx != 0 || dy != 0) {
					dx = dx / mod;
					dy = dy / mod;
				} else {
					dy = 0.1d;
					dx = 0.1d;
				}
				y += dy * velocidad;
				x += dx * velocidad;
			
			}
			else{
				y -= velocidad;
			}
		 
		setMove();
		verificarColision();
	}

	public void verificarColision() {
		if(objetivo!=null)
		if (colision(objetivo) || verificarDistancia()) {
			objetivo.setVida(vida);
			
			setVisible();
			
		}
		//verifica la colision con la Defensa del Jugador
		if(jugador!=null){
			Jugador[] aux = jugador.getDefensa();
			if(aux!=null)
			for (int i = 0; i < aux.length; i++ ) {
			if(aux[i] != null)
				if (colision(aux[i])) {
					int vd = aux[i].getDamageColision();
					aux[i].setVida(vida);
					setVisible();	
				}
			}
		}
		
		// establece que el eneimgo no está en la pantalla, por lo que no está visible
		if(fueraDePantalla() ) {
			setVisible();
		}
		
	}
	private boolean verificarDistancia() {
		int dis = objetivo.getHeight()/2;
				
		return distancia(objetivo)<dis;
	}
	
	public void disparar() {
		
	}
	
	public boolean isInvulnerable(){
		boolean verd = (mapa!=null);
		return verd;
		
	}
	
	public void setVisible(){
		super.setVisible();
		if(mapa!=null)
			mapa.getJugador().misilLanzado();
	}
	
	public void setVida(int vd){
		if(!isInvulnerable()){
			super.setVisible();
		}
	}
	
	public Explosion getExplosion() {
		addSonidoExplosion();
	
		return new Explosion(x + width/2, y + height/2, new ImageIcon(explode), height*2, height*2);
	}

	public void setVelocidad(int vel) {
		velocidad = vel;
		
	}
	
	
}
