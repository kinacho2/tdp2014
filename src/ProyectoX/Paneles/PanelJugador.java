package ProyectoX.Paneles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.PowerUps.PowerUp;
import ProyectoX.Sound.OyenteSonido;
import ProyectoX.Disparos.Disparo;
import ProyectoX.Frames.Explosion;
import ProyectoX.Frames.Objeto;
import ProyectoX.Mapas.Mapa;
import ProyectoX.Minds.Mind;

/**
 * Panel que se encarga de pintar al Jugador y se Defensa, los disparos enemigos, los PowerUP y las nubes
 * implementa a la interface ActionListener
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */

public class PanelJugador  extends JPanel implements ActionListener{

	private static final URL urlEn = (PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/sonido-enabled.png"));
	private static final URL urlDis = (PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/sonido-disabled.png"));
	
	private Mind mind;
	private Mapa mapa;
	private Jugador jugador;
	private OyenteSonido oyente;
	private JButton sonido;
	private ImageIcon en, dis;
	private JLabel labelBomba;
	private JLabel cantBombas;
	private JLabel heart;
	private JLabel cantHearts;
	private JLabel puntaje;
	private JLabel labelVida;
	private JLabel cantVida;
	private JLabel labelEnemigo;
	private JLabel contadorEnemigos;
	private boolean pause = false;
	
	/**
	 * Constructor de la clase PanelJugador
	 * crea una instancia de Mind
	 * @param map
	 * @param select
	 */
	public PanelJugador(Mapa map, int select, String nombre){
		mind = new Mind( this, select,nombre);
		map.setMind(mind);
		jugador = mind.getJugador();
		mapa = map;
		setOpaque(false);
        
        setLayout(null);
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
        
        
	}
	
	/**
	 * @param bar de tipo JPanel, panel con estadisticas y opciones que aparece abajo del panel de juego
	 */
	
	public void setBar(JPanel bar) {
		
		ImageIcon ii;
		
        puntaje = new JLabel(""+ 0);
		puntaje.setBounds(369, -2, 200, 35);
		puntaje.setFont(new java.awt.Font("Segoe UI",0,20));
		puntaje.setForeground(new java.awt.Color(255,255,255));
		puntaje.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		puntaje.setHorizontalAlignment(SwingConstants.RIGHT);
		bar.add(puntaje);
		
		
		//label enemigos y contador de enemigos
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/Enemigo/basico.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		labelEnemigo = new JLabel(ii);
		labelEnemigo.setBounds(333, -2, 30, 35);
		labelEnemigo.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(labelEnemigo);
		
		
		contadorEnemigos = new JLabel("" + mapa.cantEnemies());
		contadorEnemigos.setBounds(363, -2, 45, 35);
		contadorEnemigos.setFont(new java.awt.Font("Segoe UI",0,20));
		contadorEnemigos.setForeground(new java.awt.Color(0,255,0));
		contadorEnemigos.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		contadorEnemigos.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(contadorEnemigos);
		
		//label vida y cantidad de vida
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/vida.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		labelVida = new JLabel(ii);
		labelVida.setBounds(252, -2, 30, 35);
		labelVida.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(labelVida);
		
		cantVida = new JLabel(""+ jugador.getVida());
		cantVida.setBounds(282, -2, 45, 35);
		cantVida.setFont(new java.awt.Font("Segoe UI",0,20));
		cantVida.setForeground(new java.awt.Color(0,0,255));
		cantVida.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		cantVida.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(cantVida);
		
		//icono de corazones y cantidad de corazones
		ii = new ImageIcon(PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/heart.png"));
		ii = new ImageIcon(ii.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		heart = new JLabel(ii);
		heart.setBounds(186, -2, 30, 35);
		bar.add(heart);
		heart.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		
		cantHearts = new JLabel("3");
		cantHearts.setBounds(216, -2, 30, 35);
		cantHearts.setFont(new java.awt.Font("Segoe UI",0,20));
		cantHearts.setForeground(new java.awt.Color(255,0,0));
		cantHearts.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		cantHearts.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(cantHearts);
		
		//Icono de bomba y cantidad de bombas
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/bomba.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		labelBomba = new JLabel(ii);
		labelBomba.setBounds(120, -2, 30, 35);
		labelBomba.setBackground(new java.awt.Color(0,0,255));
		labelBomba.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(labelBomba);
		
		cantBombas = new JLabel(""+jugador.getCantBombas());
		cantBombas.setBounds(150, -2, 30, 35);
		cantBombas.setFont(new java.awt.Font("Segoe UI",0,20));
		cantBombas.setForeground(new java.awt.Color(0,255,0));
		cantBombas.setHorizontalAlignment(SwingConstants.CENTER);
		cantBombas.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(cantBombas);
		
		
		
		//icono de sonido y boton de silencio
		sonido = new JButton("");
		dis = new ImageIcon(urlDis);
		dis = new ImageIcon(dis.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		en = new ImageIcon(urlEn);
		en = new ImageIcon(en.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		oyente = new OyenteSonido(sonido,mind.getReproductor(),en,dis);
		sonido.setBounds(70, 0, 25, 25);
		sonido.setForeground(new java.awt.Color(0,255,0));
		sonido.setBackground(new java.awt.Color(0,0,0));
		sonido.setBorder(BorderFactory.createCompoundBorder(null,null));
		sonido.setFont(new java.awt.Font("Segoe UI",0,20));
		sonido.setFocusable(false);
		sonido.addActionListener(oyente);
		bar.add(sonido);
		
		
		
    }
	
	/**
	 * redefine paint(Graphic g) de JComponent
	 * pinta en el panel los disparos enemigos, el jugador y su defensa, los powerup y las nubes
	 */
	
	public void paint(Graphics g) {
		if(!pause){
	        super.paint(g);
	        
	        Graphics2D g2d = (Graphics2D) g;
	        
	        ArrayList ms = mapa.getMisilesEnemigos();
	        
	        //mueve, y repinta los disparos enemigos
	        for (int j = 0; j < ms.size(); j++ ) {
	            Disparo misil = (Disparo) ms.get(j);
		        if(misil!=null && misil.isVisible()) {
			        g2d.drawImage(misil.getImage(), misil.getX(), misil.getY(), this);
		        } 
	        }
	        
	        //pinta las explosiones	   
	        
	        ms = mapa.explosiones();
	        for (int i = 0; i < ms.size(); i++ ) {
	            Explosion m = (Explosion) ms.get(i);
	            if(m!=null && m.getVisible())
	            	g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
	            
	        }
	        
	        //elimina las explosiones que no estan en pantalla
	        
	        for (int i = 0; i < ms.size(); i++ ) {
	        	Explosion m = (Explosion) ms.get(i);
	        	if(m!=null){
	        		if( !m.getVisible()) {
	        			ms.remove(i);
	        		}
	            }
	        }
	        
	        // Pinta el jugador
	        if (jugador.getVisible()){
	        	g2d.drawImage(jugador.getImage(), jugador.getX(), jugador.getY(), this);
	        }
	        
	        Jugador aux = jugador.getDefensa();
	        
	        if (aux != null){
	        	if(aux.getVisible()){
	        		g2d.drawImage(aux.getImage(), aux.getX(), aux.getY(), this);
	        	}
	        	else
	        		jugador.dropDefensa();
	        }
	        
	        //repinta los powerUps
	        ms = mapa.getPowers();
	        
	        for (int j = 0; j < ms.size(); j++ ) {
	            PowerUp pw = (PowerUp) ms.get(j);
	            g2d.drawImage(pw.getImage(), pw.getX(), pw.getY(), this);
	        }
	        
	        ms = mapa.getObjeto();
	        
	        // mueve y repinta las nubes
	        
	        for(int i = 0; i<ms.size(); i++){
	        	Objeto o = (Objeto) ms.get(i);
	        	if(o!= null && o.getVisible()){
	                g2d.drawImage(o.getImage(), o.getX(), o.getY(), this);
	                o.move();
	        	}
	        	
	        }
	        
	        // remueve las nubes
	        
	        for(int i = 0; i<ms.size(); i++){
	        	Objeto o = (Objeto) ms.get(i);
	        	if(o!= null){
	        		if(!o.getVisible()){
	        			ms.remove(i);
	        		}
	        	}
	        	else{
	        		ms.remove(i);
	        	}
	        }
	        
	      	puntaje.setText("" + jugador.getPuntaje());
	      	cantVida.setText("" + jugador.getVida());
			contadorEnemigos.setText("" + mapa.cantEnemies());
			cantHearts.setText(""+jugador.getHearts());
	        cantBombas.setText(""+jugador.getCantBombas());
	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
		}
       
	}
	
	/**
	 * clase que implementa KeyListener, se encarga de captar los KeyEvent y de derivarle la accion al jugador
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class TAdapter implements KeyListener {
		
        public void keyReleased(KeyEvent e) {
        	jugador.keyReleased(e);
        	
        }

        public void keyPressed(KeyEvent e) {
        	jugador.keyPressed(e);
        	
        	 int key = e.getKeyCode();
             
             if (key == KeyEvent.VK_P) {
            	 if(!pause)
            		 pause = true;
            	 else
            		 pause = false;
            	 mind.pause(pause);
             }
        	
        }

		public void keyTyped(KeyEvent arg0) {
			// Es necesaria poner esta funcion porque TAdapter implementa a una interfaz, no realiza nada
		}
		
		
    }
	
	/**
	 * metodo derivado hacia la instancia de Mind
	 * @param event
	 */
	
	public void actionPerformed(ActionEvent event) {
		
		mind.actionPerformed(event);
	}
	
	/**
	 * le setea al panel el Mapa actual
	 * @param map instancia actual de Mapa
	 */

	public void setMapa(Mapa map) {
		mapa = map;
		
	}
	
	
	
}
