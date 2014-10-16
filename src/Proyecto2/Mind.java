package Proyecto2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Mind extends JPanel implements ActionListener {
	
    private Timer timer;
    private Jugador jugador;
    private final int delay = 15;
    private Mapa mapa;
    private boolean stop;
    private int delayFinal;
    private int contDelay;
    private JPanel bar;
    
   

	private JLabel puntaje;
	private JLabel labelVida;
	private JLabel contadorEnemigos;

    public Mind(Mapa map, int select) {
    	
    	setOpaque(true);
        
        setLayout(null);
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        mapa = map;
        crearJugador(select);
        stop = false;
        delayFinal = jugador.getExplosion().getDelay();
        contDelay = 0;
        
        timer = new Timer(delay, this);
        timer.start();
        
    }
    
    public void setBar(JPanel bar) {
		this.bar = bar;
        puntaje = new JLabel("Puntaje: "+ 0);
		puntaje.setBounds(800-150, 0, 150, 50);
		bar.add(puntaje);
		
		labelVida = new JLabel("Vida: " + jugador.getVida());
		labelVida.setBounds(800-200, 0, 50, 50);
		bar.add(labelVida);
		
		contadorEnemigos = new JLabel("Enemigos restantes: " + mapa.cantEnemies());
		contadorEnemigos.setBounds(800-400, 0, 150, 50);
		bar.add(contadorEnemigos);
    
    }
    
    public void paint(Graphics g) {
	        super.paint(g);

	        // Pinta el jugador
	        Graphics2D g2d = (Graphics2D)g;
	        if (jugador.getVisible())
	        	g2d.drawImage(jugador.getImage(), jugador.getX(), jugador.getY(), this);
	        
	        // arreglo de disparos
	        ArrayList ms = mapa.getMisilesJugador();
	
	        //repinta los disparos
	        for (int i = 0; i < ms.size(); i++ ) {
	            Disparo m = (Disparo) ms.get(i);
	            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	        }
	        
	        //pinta las explosiones	        
	        ms = mapa.explosiones();
	        for (int i = 0; i < ms.size(); i++ ) {
	            Explosion m = (Explosion) ms.get(i);
	            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	            m.setTime(delay);
	            if(!m.getVisible()) {
	            	ms.remove(i);
	            }
	        }
	
	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
    	
    	
    	disparosJugador();
    	
    	disparosEnemigos();
        
        if(jugador.getVisible()) {
        	jugador.move(); 
        } else {
        	if(!stop) {
        		mapa.addExposion(jugador.getExplosion());
        		stop = true;
        	} else {
        		contDelay += delay;
        		if(contDelay >= delayFinal){
        			mapa.stop();
        			JOptionPane dialog = new JOptionPane();
        			dialog.showMessageDialog(null, puntaje.getText() + "\nVuelva al menu de seleccion para comenzar nuevamente", "FIN DEL JUEGO", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        }
        
       	repaint();  
    }
    
    // Mueve los disparos visibles de jugador y los que no son removidos; además verifica si algún disparo colisionó
    // con algún enemigo
    private void disparosJugador() {
    	// arreglo de disparos de jugador y de enemigos que se encuentran en el mapa
        ArrayList ms = mapa.getMisilesJugador();
        ArrayList enemigos = mapa.getEnemies();
        
        // mueve los misiles del jugador y remueve los que no estan visibles  
        for (int i = 0; i < ms.size(); i++) {
        	Disparo m = (Disparo) ms.get(i);
            if (m.isVisible())
                m.move();
            else 
            	mapa.removerDisparoJugador(i);
            
            // verifica si algun misil del jugador colisiono a un enemigo
            for (int j = 0; j < enemigos.size(); j++) {
            	Enemigo enemigo = (Enemigo) enemigos.get(j);
                if (m.colision(enemigo)) {
                	if (m.isVisible()){
                		enemigo.setVida(m.getDamage());
                	}
                	puntaje.setText("Puntaje: " + jugador.getPuntaje());
                	m.setVisible();
                	mapa.addExposion(m.newExplosion());
                }
            }
        }
    	puntaje.setText("Puntaje: " + jugador.getPuntaje());
    	labelVida.setText("Vida: " + jugador.getVida());
		contadorEnemigos.setText("Enemigos restantes: " + mapa.cantEnemies());
    	
    }
    
    // Mueve los disparos visibles de los enemigos y los que no son removidos; además verifica si algún disparo colisionó
    // con algún enemigo
    private void disparosEnemigos() {
    	
    	// arreglo de disparos de los enemigos que se encuentran en el mapa
    	ArrayList ms = mapa.getMisilesEnemigos();
        
        // verifica si algun misil del enemigo colisiono con el jugador
        for (int j = 0; j < ms.size(); j++ ) {
            Disparo misil = (Disparo) ms.get(j);
            if (misil.colision(jugador)) {
            	if (misil.isVisible()) {
            		jugador.setVida(misil.getDamage());
            	}
            	misil.setVisible();
            	mapa.addExposion(misil.newExplosion());
            }
        }
    	
    }

    private class TAdapter implements KeyListener {

        public void keyReleased(KeyEvent e) {
        	jugador.keyReleased(e);
        	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        		jugador.setDisCero();
            }
        }

        public void keyPressed(KeyEvent e) {
        	jugador.keyPressed(e);
        	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        		jugador.setDis();
            }
        	
        }

		public void keyTyped(KeyEvent arg0) {
			// Es necesaria poner esta función porque implementa a una interfaz TAdapter, no realiza nada
		}
    }
    
    public Jugador getJugador() {
    	return jugador;
    }
    
    // Selección del tipo de nave
    public void crearJugador(int select) {
    	if (select == 1) {
        	jugador = new Veloz();
    	} else if (select == 2) {
        	jugador = new Normal();
    	} else if (select == 3) {
        	jugador = new Resistente();
    	}
        jugador.setMapa(mapa);
        mapa.setJugador(jugador);
        jugador.setMinHeight(530);
    }
    
    public void stop() {
    	timer.stop();
    }

}
