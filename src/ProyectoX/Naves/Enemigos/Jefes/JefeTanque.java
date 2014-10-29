package ProyectoX.Naves.Enemigos.Jefes;

import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ProyectoX.Naves.Nave;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasDobles;
import ProyectoX.Naves.Enemigos.Torretas.FabricaTorretasSimples;
import ProyectoX.Naves.Enemigos.Torretas.Torreta;
import ProyectoX.Naves.Jugador.Jugador;

import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;

public class JefeTanque extends Jefe{

	protected static final URL url = (Nave.class.getClassLoader().getResource("ProyectoX/img/Enemigo/JefeTanque/JefeTanque.png"));
	protected static final String boundsDouble = "/ProyectoX/img/Enemigo/JefeTanque/posicionesTorretasDobles.txt";
	protected static final String boundsSimple = "/ProyectoX/img/Enemigo/JefeTanque/posicionesTorretasSimples.txt";
	
	
	private static final int defaultWidth = 320;
	private static final int defaultHeight = 1376;
	private static final int defaultVel = 3;
	
	private boolean primero = true;
	private boolean segundo = true;
	
	
	public JefeTanque() {
		super(0, defaultVel, new ImageIcon(url), defaultWidth, defaultHeight);
		
		x = 400 - defaultWidth/2;
		y = - defaultHeight;
		
		int cantTorretasDobles = 9;
		int cantTorretasSimples = 5;
		
		cargarArchivoTorretas(boundsDouble, new FabricaTorretasDobles(), cantTorretasDobles);
		cargarArchivoTorretas(boundsSimple, new FabricaTorretasSimples(), cantTorretasSimples);
		
		
			
		
	}

	

	public void move() {
		if(torretas.size() == 0){
			setVisible();
			width = defaultWidth;
			height = defaultHeight;
		}
		int[] toEliminate = new int[torretas.size()];
		int j=0;
		if(y < 100 && primero){
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
			if(y + defaultHeight > 100){
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

	
	
}
