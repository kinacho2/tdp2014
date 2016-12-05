package ProyectoX.Paneles;

import java.awt.BorderLayout;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	private static final URL check = (PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/check.png"));
	private static final URL uncheck = (PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/uncheck.png"));
	
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
	private ImageIcon bombaORocket;
	private boolean pauseTime = false;
	private JLabel labelBuff;
	private JLabel timeBuff;
	private JLabel buffVida;
	private JLabel buffVidaboton;
	private JLabel buffFantasmaboton;
	private JLabel buffFantasma;
	private JLabel buffTimeboton;
	private JLabel buffTime;
	private JLabel defensa;
	private Jugador def;
	private JLabel disparo;
	private JLabel disparoLVL;
	private JPanel defensaVida;
	private JButton chk;
	
	private int[] vidaDef = new int[2];
	private boolean checkVida = false;
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
		puntaje.setBounds(270+15+30, -2, 100, 35);
		puntaje.setFont(new java.awt.Font("Segoe UI",0,20));
		puntaje.setForeground(new java.awt.Color(255,255,255));
		puntaje.setBorder(new LineBorder(new java.awt.Color(255,255,255), 1, false));
		puntaje.setHorizontalAlignment(SwingConstants.RIGHT);
		bar.add(puntaje);
		
		
		//label enemigos y contador de enemigos
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/Enemigo/basico.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		labelEnemigo = new JLabel(ii);
		labelEnemigo.setBounds(180-45-30, -2, 30, 35);
		labelEnemigo.setBorder(new LineBorder(new java.awt.Color(255,255,255), 1, false));
		bar.add(labelEnemigo);
		
		
		contadorEnemigos = new JLabel("" + mapa.cantEnemies());
		contadorEnemigos.setBounds(180-45, -2, 45, 35);
		contadorEnemigos.setFont(new java.awt.Font("Segoe UI",0,20));
		contadorEnemigos.setForeground(new java.awt.Color(215,56,7));
		contadorEnemigos.setBorder(new LineBorder(new java.awt.Color(255,255,255), 1, false));
		contadorEnemigos.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(contadorEnemigos);
		
		//label vida y cantidad de vida
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/vida.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		labelVida = new JLabel(ii);
		labelVida.setBounds(180, -2, 30, 35);
		labelVida.setBorder(new LineBorder(new java.awt.Color(255,255,255), 1, false));
		bar.add(labelVida);
		
		cantVida = new JLabel(""+ jugador.getVida());
		cantVida.setBounds(210, -2, 45, 35);
		cantVida.setFont(new java.awt.Font("Segoe UI",0,20));
		cantVida.setForeground(new java.awt.Color(0,0,255));
		cantVida.setBorder(new LineBorder(new java.awt.Color(255,255,255), 1, false));
		cantVida.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(cantVida);
		
		//icono de corazones y cantidad de corazones
		ii = new ImageIcon(PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/heart.png"));
		ii = new ImageIcon(ii.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		heart = new JLabel(ii);
		heart.setBounds(216+550, -2, 30, 35);
		heart.setBorder(new LineBorder(new java.awt.Color(255,0,0), 1, false));
		bar.add(heart);
		
		
		cantHearts = new JLabel("     "+jugador.getHearts());
		cantHearts.setBounds(216+550, 20, 30, 10);
		cantHearts.setFont(new java.awt.Font("Segoe UI",0,11));
		cantHearts.setForeground(new java.awt.Color(255,255,255));
		cantHearts.setOpaque(false);
		cantHearts.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(cantHearts);
		
		//icono de buffs y tiempo de buff
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/fantasma.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		labelBuff = new JLabel(ii);
		labelBuff.setBounds(240+15, -2, 30, 35);
		labelBuff.setBackground(new java.awt.Color(0,0,255));
		labelBuff.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(labelBuff);
		
		timeBuff = new JLabel("");
		timeBuff.setBounds(270+15, -2, 30, 35);
		timeBuff.setFont(new java.awt.Font("Segoe UI",0,20));
		timeBuff.setForeground(new java.awt.Color(0,255,0));
		timeBuff.setHorizontalAlignment(SwingConstants.CENTER);
		timeBuff.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(timeBuff);
		
		
		//Icono de bomba y cantidad de bombas
		cantBombas = new JLabel("X    "+jugador.getCantBombas());
		cantBombas.setBounds(186+490, 20, 30, 10);
		cantBombas.setFont(new java.awt.Font("Segoe UI",0,11));
		cantBombas.setOpaque(false);
		cantBombas.setForeground(new java.awt.Color(255,255,255));
		cantBombas.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(cantBombas);
		
		
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/bomba.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		labelBomba = new JLabel(ii);
		labelBomba.setBounds(186+490, -2, 30, 35);
		labelBomba.setBackground(new java.awt.Color(0,0,255));
		labelBomba.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(labelBomba);
		
		
		
		//buffvida
		
		chk = new JButton();
		//TODO
		chk.setIcon(new ImageIcon(new ImageIcon(uncheck).getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT)));
		chk.setBounds(186+400, 0, 10, 10);
		chk.addActionListener(new OyenteChk());
		chk.setFocusable(false);

		chk.setForeground(new java.awt.Color(0,255,0));
		chk.setBackground(new java.awt.Color(0,0,0));
		chk.setBorder(BorderFactory.createCompoundBorder(null,null));
		chk.setFont(new java.awt.Font("Segoe UI",0,20));
		bar.add(chk);
		
		buffVidaboton = new JLabel("V    "+jugador.getPotas());
		buffVidaboton.setBounds(186+400, 20, 30, 10);
		buffVidaboton.setFont(new java.awt.Font("Segoe UI",0,11));
		buffVidaboton.setOpaque(false);
		buffVidaboton.setForeground(new java.awt.Color(255,255,255));
		buffVidaboton.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(buffVidaboton);
		
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/vida.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		buffVida = new JLabel(ii);
		buffVida.setBounds(186+400, -2, 30, 35);
		buffVida.setBorder(new LineBorder(new java.awt.Color(255,0,0), 1, false));
		bar.add(buffVida);
		
		//bufffantasma
		buffFantasmaboton = new JLabel("F    "+jugador.getFantasma());
		buffFantasmaboton.setBounds(186+430, 20, 30, 10);
		buffFantasmaboton.setFont(new java.awt.Font("Segoe UI",0,11));
		buffFantasmaboton.setOpaque(false);
		buffFantasmaboton.setForeground(new java.awt.Color(255,255,255));
		buffFantasmaboton.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(buffFantasmaboton);
		
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/fantasma.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		buffFantasma = new JLabel(ii);
		buffFantasma.setBounds(186+430, -2, 30, 35);
		buffFantasma.setBorder(new LineBorder(new java.awt.Color(255,0,0), 1, false));
		bar.add(buffFantasma);
		
		//bufffantasma
		buffTimeboton = new JLabel("R    "+jugador.getTime());
		buffTimeboton.setBounds(186+460, 20, 30, 10);
		buffTimeboton.setFont(new java.awt.Font("Segoe UI",0,11));
		buffTimeboton.setOpaque(false);
		buffTimeboton.setForeground(new java.awt.Color(255,255,255));
		buffTimeboton.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(buffTimeboton);
		
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/tiempo.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		buffTime = new JLabel(ii);
		buffTime.setBounds(186+460, -2, 30, 35);
		buffTime.setBorder(new LineBorder(new java.awt.Color(255,0,0), 1, false));
		bar.add(buffTime);
		
		//Defensa
		defensaVida = new JPanel();//new JLabel("");
		defensaVida.setBounds(186+520, 20, 30, 10);
		defensaVida.setFont(new java.awt.Font("Segoe UI",0,11));
		defensaVida.setOpaque(true);
		defensaVida.setForeground(new java.awt.Color(0,255,0));
		defensaVida.setBackground(new java.awt.Color(255,0,0));
		//defensaVida.setHorizontalAlignment(SwingConstants.CENTER);
		defensaVida.setLayout(null);
		bar.add(defensaVida);
		
		ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/invisible.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		defensa = new JLabel(ii);
		defensa.setBounds(186+520, -2, 30, 35);
		defensa.setBackground(new java.awt.Color(0,0,255));
		defensa.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(defensa);
		
		//Disparo
		disparoLVL = new JLabel("LVL  "+jugador.getDisparo().getLevel());
		disparoLVL.setBounds(186+550, 20, 30, 10);
		disparoLVL.setFont(new java.awt.Font("Segoe UI",0,11));
		disparoLVL.setOpaque(false);
		disparoLVL.setForeground(new java.awt.Color(255,255,255));
		disparoLVL.setHorizontalAlignment(SwingConstants.CENTER);
		bar.add(disparoLVL);
		
		
		ii = new ImageIcon(jugador.getDisparo().getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT));
		disparo = new JLabel(ii);
		disparo.setBounds(186+550, -2, 30, 35);
		disparo.setBackground(new java.awt.Color(0,0,255));
		disparo.setBorder(new LineBorder(new java.awt.Color(0,0,255), 1, false));
		bar.add(disparo);
		
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
		{
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
	            	m.move();
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
	        Jugador[] aux = jugador.getDefensa();
	        if(aux!=null)
	        for (int i = 0; i < aux.length; i++ ) {
		        
		        
		        if (aux[i] != null){
		        	if(aux[i].getVisible()){
		        		g2d.drawImage(aux[i].getImage(), aux[i].getX(), aux[i].getY(), this);
		        	}
		        	else
		        		jugador.dropDefensa(i);
		        }
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
	                if(!pause && !pauseTime)
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
	      	
	      	if(checkVida && jugador.getVida()<40 && jugador.getVida()>0){
	      		jugador.curar();  
	      	}
	      	
			contadorEnemigos.setText("" + mapa.cantEnemies());
			cantHearts.setText("     "+jugador.getHearts());
			int cantB = jugador.getCantBombas();
			int cantR = jugador.getCantRockets();
			int invulnerable = (int)jugador.getInvulnerableTime()/1000;
			int timeP = (int)jugador.getPauseTime()/1000;

			if(cantR==0 && cantB ==0)
				cantBombas.setText("X    "+0);
			else{
				if(cantR>0 && cantB==0){
					cantBombas.setText("X    "+cantR);
					bombaORocket = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/rocket.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

				}
				if(cantR==0 && cantB>0){
					cantBombas.setText("X    "+cantB);
					bombaORocket = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/bomba.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
				}
				labelBomba.setIcon(bombaORocket);
			}
			
			if(timeP>=0){	
				ImageIcon ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/tiempo.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
				timeBuff.setText(""+timeP);
				labelBuff.setIcon(ii);
			}else{
				if(invulnerable>=0){
					ImageIcon ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/PUP/fantasma.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
					timeBuff.setText(""+invulnerable);
					labelBuff.setIcon(ii);
				}
				else{
					//Enemigo/Torreta/invisible.png
					ImageIcon ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/invisible.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
					timeBuff.setText("");
					labelBuff.setIcon(ii);
				}
			}
			
			
			buffTimeboton.setText("R    "+jugador.getTime());
			buffVidaboton.setText("V    "+jugador.getPotas());
			buffFantasmaboton.setText("F    "+jugador.getFantasma());
			
			Jugador[] nv = jugador.getDefensa();
			if(nv==null || nv[0]==null){
				//no hay defensa se setea invisible
				ImageIcon ii = new ImageIcon(new ImageIcon(PanelJugador.class.getClassLoader().getResource("ProyectoX/img/Enemigo/Torreta/invisible.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
				defensa.setIcon(ii);
				defensaVida.removeAll();
				defensaVida.setOpaque(false);
				//defensaVida.setText("");
			}
			else{
				Jugador auxiliar = nv[0];
				
				defensaVida.setOpaque(true);

				if(auxiliar!=def){
					def=auxiliar;
					ImageIcon ii = new ImageIcon(def.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
					defensa.setIcon(ii);
				}
				
				String ss = ""+def.getVida();
				boolean segundo = false;
				if(nv.length>1 && nv[1]!=null){
					ss=ss+"-"+nv[1].getVida();
					segundo = true;
				}
				
				int bound = 600/def.getMaxVida();
				if(bound>6){
					bound = bound/2;
				}
				//defensaVida.setText(ss);
				if(vidaDef[0]!=def.getVida() || (segundo && vidaDef[1]!=nv[1].getVida()) || (!segundo && vidaDef[1]<20)){
					vidaDef[0]=def.getVida();
					defensaVida.removeAll();
					for(int i = 0; i<def.getVida();i=i+20){
						
						JLabel label = new JLabel();
						label.setBackground(new java.awt.Color(0,255,0));
						label.setOpaque(true);
						label.setBounds(i/20*bound,0,bound,20);
						defensaVida.add(label);
					}
					
					
					if(segundo){
						vidaDef[1]=nv[1].getVida();
						for(int i = 600; i>600-vidaDef[1];i=i-20){
							
							JLabel label = new JLabel();
							label.setBackground(new java.awt.Color(0,255,0));
							label.setOpaque(true);
							label.setBounds(600/20-bound*((600-i)/20+1),0,bound,20);
							defensaVida.add(label);
						}
					}
					
				}
			}
			disparoLVL.setText("LVL  "+jugador.getDisparo().getLevel());
			disparo.setIcon(new ImageIcon(jugador.getDisparo().getImage()));
			
			
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
            	 mind.pause(pause,false);
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

	public void pauseTime(boolean arg) {
		pauseTime = arg;
		
	}
	

	
	private class OyenteChk implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!checkVida){
				chk.setIcon(new ImageIcon(new ImageIcon(check).getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT)));
				checkVida = true;
			}
			else{
				chk.setIcon(new ImageIcon(new ImageIcon(uncheck).getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT)));
				checkVida = false;
			}
			
		}
	}
	
}
