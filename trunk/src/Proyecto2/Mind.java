package Proyecto2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Timer;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Mind extends JPanel implements ActionListener {
	
    private Timer timer;
    private Jugador player;
    

    public Mind() {

    	setOpaque(true);
        setSize(400, 300);
        setLayout(null);
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        player = new Jugador();

        timer = new Timer(5, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        
        ArrayList ms = player.getMissiles();
        
        
        //repinta los disparos
        for (int i = 0; i < ms.size(); i++ ) {
            Disparo m = (Disparo) ms.get(i);
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void actionPerformed(ActionEvent e) {
    	ArrayList ms = player.getMissiles();

    	
    	//verifica que esten visibles los disparos
        for (int i = 0; i < ms.size(); i++) {
        	Disparo m = (Disparo) ms.get(i);
            if (m.isVisible()) 
                m.move();
            else ms.remove(i);
        }
        
    	player.move();
        repaint();  
    }


    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
        	player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
        	player.keyPressed(e);
        }
    }

}
