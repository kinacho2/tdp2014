package Proyecto2.Frames_Minds;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import Proyecto2.Mapa;
import Proyecto2.Explosiones_Disparos.Disparo;
import Proyecto2.Naves.Enemigos.Enemigo;

public class PanelEnemies extends JPanel {

	protected MindEnemies mind;
	protected Mapa mapa;
	

	public PanelEnemies(Mapa map ) {
		mind = new MindEnemies(this,map);
		mapa = map;
		setLayout(null);
        setOpaque(false);
        setBounds(0,0,800, 600);
	}
	
	public synchronized void paint(Graphics g) {
	        super.paint(g);

	        Graphics2D g2d = (Graphics2D) g;
	        
	        ArrayList enemigos = mind.getEnemies();
	        
	        //repinta los enemigos
	        for (int i = 0; i < enemigos.size(); i++ ) {
	            Enemigo m = (Enemigo) enemigos.get(i);
	            AffineTransform tx = m.getRotacion();
	            g2d.setTransform(tx);
	            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	            
	        }
	        
	        ArrayList ms = mapa.getMisilesEnemigos();
	        
	        //mueve, repinta y elimina los disparos en caso de que ya no sean visibles
	        for (int j = 0; j < ms.size(); j++ ) {
	            Disparo misil = (Disparo) ms.get(j);
	            if (misil != null)
		            if(misil.isVisible()) {
		            	misil.move();
		            	AffineTransform tx = AffineTransform.getRotateInstance(0, misil.getX() , misil.getY());
			            g2d.setTransform(tx);
			            g2d.drawImage(misil.getImage(), misil.getX(), misil.getY(), this);
		            } else {
		            	ms.remove(j);
		            }
	            
	        }
	 

	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
	        
    }
	 
	public MindEnemies getMindEnemies() {
		 return mind;
	}

}
