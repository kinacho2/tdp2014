package ProyectoX.Frames_Minds;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.PowerUps.PowerUp;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Explosiones.Explosion;
import ProyectoX.Mapas.Mapa;

public class PanelJugador extends JPanel implements ActionListener{

	private Mind mind;
	private Mapa mapa;
	private Jugador jugador;
	
	private JPanel bar;
	    
	private JLabel puntaje;
	private JLabel labelVida;
	private JLabel contadorEnemigos;
	
	public PanelJugador(Mapa map, int select){
		mind = new Mind(map, this, select);
		jugador = mind.getJugador();
		mapa = map;
		setOpaque(false);
        
        setLayout(null);
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
        
        
	}
	
	public void setBar(JPanel bar) {
		this.bar = bar;
        puntaje = new JLabel("Puntaje: "+ 0);
		puntaje.setBounds(800-150, 0, 350, 50);
		bar.add(puntaje);
		
		labelVida = new JLabel("Vida: " + jugador.getVida());
		labelVida.setBounds(800-250, 0, 100, 50);
		bar.add(labelVida);
		
		contadorEnemigos = new JLabel("Enemigos restantes: " + mapa.cantEnemies());
		contadorEnemigos.setBounds(800-500, 0, 250, 50);
		bar.add(contadorEnemigos);
		
		//quit.setBackground(new java.awt.Color(0,0,0));
		//labelVida.setPreferredSize(new java.awt.Dimension(14, 7));
		labelVida.setFont(new java.awt.Font("Segoe UI",0,20));
		labelVida.setForeground(new java.awt.Color(255,0,0));
		puntaje.setFont(new java.awt.Font("Segoe UI",0,20));
		puntaje.setForeground(new java.awt.Color(0,0,255));
		contadorEnemigos.setFont(new java.awt.Font("Segoe UI",0,20));
		contadorEnemigos.setForeground(new java.awt.Color(0,255,0));
    
    }
	
	public synchronized void paint(Graphics g) {
        super.paint(g);
        

        // Pinta el jugador
        Graphics2D g2d = (Graphics2D)g;
        
        if (jugador.getVisible())
        	g2d.drawImage(jugador.getImage(), jugador.getX(), jugador.getY(), this);
        
        Jugador aux = jugador.getDefensa();
        
        if (aux != null){
        	if(aux.getVisible())
        		g2d.drawImage(aux.getImage(), aux.getX(), aux.getY(), this);
        	else
        		jugador.dropDefensa();
        }
        
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
            if(!m.getVisible()) {
            	ms.remove(i);
            }
        }
        
        //repinta los powerUps
        ms = mapa.getPowers();
        
        for (int j = 0; j < ms.size(); j++ ) {
            PowerUp pw = (PowerUp) ms.get(j);
            g2d.drawImage(pw.getImage(), pw.getX(), pw.getY(), this);
        }
        
      	puntaje.setText("Puntaje: " + jugador.getPuntaje());
        labelVida.setText("Vida: " + jugador.getVida());
		contadorEnemigos.setText("Enemigos restantes: " + mapa.cantEnemies());
         
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
       
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

	public void actualizarPuntaje(){
        puntaje.setText("Puntaje: " + jugador.getPuntaje());

	}
	
	public void actionPerformed(ActionEvent event) {
		
		mind.actionPerformed(event);
	}

	public Mind getMind() {
		return mind;
	}
	
}
