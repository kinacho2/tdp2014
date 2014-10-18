package Proyecto2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelEnemies extends JPanel {

	protected MindEnemies mind;
	protected Mapa mapa;
	

	public PanelEnemies(Mind m,Mapa map ) {
		mind = new MindEnemies(this,map);
		mapa = map;
		setLayout(null);
        setOpaque(false);
        setBounds(0,0,800, 600);
	}
	
	public synchronized void paint(Graphics g) {
	        super.paint(g);

	        Graphics2D g2d = (Graphics2D) g;
	        
	        ArrayList ms = mapa.getMisilesEnemigos();
	        
	        //mueve, repinta y elimina los disparos en caso de que ya no sean visibles
	        for (int j = 0; j < ms.size(); j++ ) {
	            Disparo misil = (Disparo) ms.get(j);
	            if (misil != null)
		            if(misil.isVisible()) {
		            	misil.move();
		            	g2d.drawImage(misil.getImage(), misil.getX(), misil.getY(), this);
		            } else {
		            	ms.remove(j);
		            }
	            
	        }
	        
	        ArrayList enemigos = mind.getEnemies();
	        
	        //repinta los enemigos
	        for (int i = 0; i < enemigos.size(); i++ ) {
	            Enemigo m = (Enemigo) enemigos.get(i);
	            double rot = m.getRotacion();
	            AffineTransform tx = AffineTransform.getRotateInstance(rot, m.getX()+m.getWidth()/2, m.getY()+m.getHeight()/2);
	            g2d.setTransform(tx);
	            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	            
	        }

	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
	        
	        
    }
	 
	public MindEnemies getMindEnemies() {
		 return mind;
	}

}
