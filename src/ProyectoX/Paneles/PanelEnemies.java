package ProyectoX.Paneles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;
import ProyectoX.Aplication;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Frames.MenteSplash;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Minds.MindEnemies;
import ProyectoX.Naves.Enemigos.Enemigo;

/**
 * clase Encargada de pintar a los enemigos, el fondo y los disparos del jugador
 * implementa la interface AbstractPanel
  * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class PanelEnemies  extends JPanel implements AbstractPanel {

	protected MindEnemies mind;
	protected Mapa mapa;
	private Aplication api;

	/**
	 * Constructor de la clase PanelEnemies
	 * se encarga de crear la instancia de MindEnemies
	 * @param map
	 * @param api
	 */
	public PanelEnemies(Mapa map, Aplication api) {
		
		this.api = api;
		mind = new MindEnemies(this);
		map.setMindEnemies(mind);
		mapa = map;
		setLayout(null);
        setOpaque(false);
        setBounds(0,0,800, 600);
	}
	
	/**
	 * redefine paint(Graphics g) de la clase JComponent
	 * pinta en el panel el fondo, los enemigos y los disparos del jugador
	 * @param g de tipo Graphics 
	 */
	public synchronized void paint(Graphics g) {
		 
	        super.paint(g);

	        Graphics2D g2d = (Graphics2D) g;
	        
	        g2d.drawImage(mapa.getImage(), mapa.getX(), mapa.getY(), this);
	        
	        ArrayList enemigos = mind.getEnemies();
	        
	        //repinta los enemigos
	        for (int i = 0; i < enemigos.size(); i++ ) {
	            Enemigo m = (Enemigo) enemigos.get(i);
	            if(m!=null && m.getVisible()){
	            	AffineTransform tx = m.getRotacion();
	            	g2d.setTransform(tx);
	            	g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	            }
	        }
	        
	        ArrayList ms = mapa.getMisilesJugador();
	        
	        //mueve, repinta y elimina los disparos en caso de que ya no sean visibles
	        for (int j = 0; j < ms.size(); j++ ) {
	            Disparo misil = (Disparo) ms.get(j);
	            if (misil != null && misil.isVisible()){
		            	AffineTransform tx = AffineTransform.getRotateInstance(0, misil.getX() , misil.getY());
			            g2d.setTransform(tx);
			            g2d.drawImage(misil.getImage(), misil.getX(), misil.getY(), this);
		            }
	            
	        }
	 

	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
	        
    }
	
	/**
	 * retorna la instancia actual de MindEnemies del panel 
	 * @return instancia de MindEnemies
	 */
	public MindEnemies getMindEnemies() {
		 return mind;
	}
	
	/**
	 * setea un nuevo mapa al panel
	 * @param map nueva instancia de mapa
	 */

	public void setMapa(Mapa map) {
		mapa = map;
		
	}

	/**
	 * define function() de la interface AbstractPanel
	 * esta funcion pide el siguiente nivel y setea en visible el JFrame
	 */
	public void function() {
		mapa.nextMapa();
		api.setVisible(true);
	}
	
	/**
	 * le indica al panel que el jefe fue destruido
	 * para que este llame al SplashScreen
	 */

	public void jefeMuerto() {
		
		api.setVisible(false);
		MenteSplash mente = new MenteSplash(5000,mapa.getImagenSplash(),this);
		mente.start();
		
	}

}
