package Proyecto2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelEnemies extends JPanel {

	protected MindEnemies mind;
	
	public PanelEnemies( ){
		mind = new MindEnemies(this);
		
		setLayout(null);
        setOpaque(false);
        setBounds(0,0,400, 300);
	}
	
	 public void paint(Graphics g) {
	        super.paint(g);

	        Graphics2D g2d = (Graphics2D)g;
	        
	        ArrayList ms = mind.getEnemies();
	        
	        //repinta los enemigos
	        for (int i = 0; i < ms.size(); i++ ) {
	            Enemigo m = (Enemigo) ms.get(i);
	            double rot = m.getRotacion();
	            double calculo = 3.14*3.14*Math.sin(rot);
	            AffineTransform tx = AffineTransform.getRotateInstance(rot, m.getX(), m.getY());
	            g2d.setTransform(tx);
	            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	            
	        }

	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
    }
	 
	 public MindEnemies getMindEnemies(){
		 return mind;
	 }
	 public void setJugador(Jugador jugador){
		 mind.setJugador(jugador);
	 }
}
