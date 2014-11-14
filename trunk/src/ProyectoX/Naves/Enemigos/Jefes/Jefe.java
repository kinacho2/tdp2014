package ProyectoX.Naves.Enemigos.Jefes;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ProyectoX.Mapas.Mapa;
import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Enemigo;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretas;
import ProyectoX.Naves.Enemigos.Torretas.Torreta;
import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Sound.Reproductor;



public abstract class Jefe extends Enemigo{
	
	private boolean primero = true;
	protected ArrayList torretas;
	protected int defaultWidth;
	protected int defaultHeight;

	public Jefe(int vida, int vel, ImageIcon ii, int w, int h) {
		super(vida, vel, ii, w, h);
		defaultWidth = w;
		defaultHeight = h;
		torretas = new ArrayList();
		width = 0;
		height = 0;
	}

	protected void cargarArchivoTorretas(String bounds, FabricaTorretas fabrica, int cantTorretas){
		try {
			
			InputStream arch = this.getClass().getResourceAsStream(bounds);
            DataInputStream lector = new DataInputStream(arch);
			
			
			for(int i =0; i<cantTorretas; i++){
				String ss = "";
				char c = (char) lector.read();
				
				while(c!=' '){
					ss+=c;
					//System.out.println(c);
					c = (char) lector.read();
					
				}
				
				
				int dx =  new Integer(ss).intValue();
				ss = "";
				c = (char) lector.read();
				while(c!=' '){
					ss+=c;
					c = (char) lector.read();
					
				}
				int dy =  new Integer(ss).intValue();
				
				torretas.add(fabrica.nuevaTorreta(x + dx , y + dy));
			}
		} catch ( IOException e) {
			
		}
	}
	public void disparar() {
		
	}

	public boolean isEspecial() {
		return false;
	}
	
	
	public ArrayList getTorretas(){
		return torretas;
	}
	
	public void setJugador(Jugador jugador){
		super.setJugador(jugador);
		for(int i=0; i<torretas.size(); i++){
			Torreta t =(Torreta)torretas.get(i);
			t.setJugador(jugador);
		}
	}
	
	public void setVida(int vd){
		
	}
	
	public boolean colision(Nave n){
		return false;
	}
	
	public void setMapa(Mapa map){
		super.setMapa(map);
		for(int i=0; i<torretas.size(); i++){
			Torreta t =(Torreta)torretas.get(i);
			t.setMapa(map);
		}
	}
	
	public synchronized int bomba(){
		if(torretas.size() > 2){
			Random rn = new Random(4);
			Enemigo m = (Enemigo) torretas.get(rn.nextInt(torretas.size()));
			m.setVisible();
			m = (Enemigo) torretas.get(rn.nextInt(torretas.size()));
			m.setVisible();
		}
		else{
			for(int i = 0; i < torretas.size(); i++){
				Enemigo m = (Enemigo) torretas.get(i);
				m.setVisible();
			}
		}
		//modificar a gusto
		return 0;
	}
	
	public void addReproductor(Reproductor rep){
		reproductor = rep;
		for(int i =0; i<torretas.size(); i++){
			Torreta torr = (Torreta) torretas.get(i);
			torr.addReproductor(rep);
		}
	}

	public int getMovimiento() {
		
		return primero?1:-1;
	}
	
	protected int torretaMasBaja(boolean pos){
		int res = 0;
		if(torretas.size() > 0){
			int i = 0;
			Torreta aux = (Torreta) torretas.get(i);
			res = aux.getY();
			while(i+1 < torretas.size()){
				aux = (Torreta) torretas.get(i);
				if(pos){
					if(res < aux.getY()){
						res = aux.getY();
					}
				}
				else{
					if(res > aux.getY()){
						res = aux.getY();
					}
				}
				i++;
			}
		}
		//System.out.println(pos);
		return res;
		
		
	}
	
	public void move() {
		if(puedeMoverse()){
			if(torretas.size() == 0){
				jugador.setPuntaje(puntaje);
				mapa.addPower(400, 200, true);
				setVisible();
				width = defaultWidth;
				height = defaultHeight;
				
			}
			else
				if(torretaMasBaja(false) < 200 && primero){
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
					if(torretaMasBaja(true) > -100){
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
		setMove();
		
	}
	
	
}
