package ProyectoX.Paneles;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProyectoX.Aplication;
import ProyectoX.Minds.Ranking;
import ProyectoX.Sound.OyenteSonido;

/**
 * Primer panel del juego, presenta las opciones de iniciar juego o salir
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */
public class PanelInit   extends JPanel {

	
	private static final URL urlEn = (PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/sonido-enabled.png"));
	private static final URL urlDis = (PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/sonido-disabled.png"));
	private JButton rank;
	private JButton sonido;
	private ImageIcon en,dis;
	private Aplication api;
	private JButton quit;
	private JButton newGame;
	private JLabel fondo;
	/**
	 * Constructor de la clase PanelInit
	 * @param api Aplicacion principal del juego
	 */
	public PanelInit(Aplication api){
		super();
		this.api = api;
		
		setLayout(null);
		setBackground(new java.awt.Color(0,0,0));
		
		
		{
			newGame = new JButton();
			add(newGame);
			newGame.setText("JUEGO NUEVO");
			newGame.setBounds(400 - 288/2, 170, 288, 50);
			newGame.setForeground(new java.awt.Color(0,255,0));
			newGame.setBackground(new java.awt.Color(0,0,0));
			newGame.setBorder(BorderFactory.createCompoundBorder(null,null));
			newGame.setFont(new java.awt.Font("Segoe UI",0,20));
			newGame.addActionListener(new OyenteNuevoJuego());
		}
		{
			quit = new JButton();
			add(quit);
			quit.setText("SALIR");
			quit.setBounds(400 - 288/2, 257*2-170, 288, 50);
			quit.setBackground(new java.awt.Color(0,0,0));
			quit.setForeground(new java.awt.Color(0,255,0));
			quit.setPreferredSize(new java.awt.Dimension(14, 7));
			quit.setFont(new java.awt.Font("Segoe UI",0,20));
			quit.setBorder(BorderFactory.createCompoundBorder(null, null));
			quit.addActionListener(new OyenteQuit());
		}
		{
			sonido = new JButton("");
			dis = new ImageIcon(urlDis);
			dis = new ImageIcon(dis.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
			en = new ImageIcon(urlEn);
			en = new ImageIcon(en.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
			
			sonido.setBounds(750, 10, 25, 25);
			sonido.setForeground(new java.awt.Color(0,255,0));
			sonido.setBackground(new java.awt.Color(0,0,0));
			sonido.setBorder(BorderFactory.createCompoundBorder(null,null));
			sonido.setFont(new java.awt.Font("Segoe UI",0,20));
			sonido.setFocusable(false);
			sonido.addActionListener(new OyenteSonido(sonido,api.getReproductor(),en,dis));
			add(sonido);
		}
		{
			rank = new JButton();
			add(rank);
			rank.setText("MOSTRAR RANKING");
			rank.setBounds(400 - 288/2, 257, 288, 50);
			rank.setForeground(new java.awt.Color(0,255,0));
			rank.setBackground(new java.awt.Color(0,0,0));
			rank.setBorder(BorderFactory.createCompoundBorder(null,null));
			rank.setFont(new java.awt.Font("Segoe UI",0,20));
			rank.addActionListener(new OyenteRanking());
		}
		{
			
			ImageIcon icon = new ImageIcon(PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/menu.gif"));
			fondo = new JLabel(icon);
			fondo.setBounds(0, 0, 800, 600);
			add(fondo);
		}
		
	}
	
	/**
	 * Clase que implementa la interface ActionListener
	 * se encarga de cerrar la aplicacion en caso de salir
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	private class OyenteQuit implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
            int verd;
            
            JOptionPane dialogo = new JOptionPane();
            
            verd = dialogo.showConfirmDialog(null, "Desea salir del juego?", "Atencion",JOptionPane.YES_NO_OPTION);
			
            if(verd == 0){
            	System.exit(0);
            }
            
		}
	}
	/**
	 * Clase que implementa ActionListener
	 * inicia el panel de seleccion de Jugador
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
		
	private class OyenteNuevoJuego implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane pane = new JOptionPane();
			String nom = pane.showInputDialog("ingrese su Nombre", "");
            nom = nom.replaceAll(" ", "_");
            nom = nom.replaceAll("@", ".");
            if(nom==""){
            	nom = "Player";
            }
            api.setNombre(nom);
			setVisible(false);
           
            api.initMain(api.getReproductor());
            
		}
	}
	
	/**
	 * Clase que implementa ActionListener
	 * inicia el panel de seleccion de Jugador
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
		
	private class OyenteRanking implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			Ranking ran = new Ranking();
            String linea = ran.mostrarPuntaje();
            api.initPanelRanking(linea);
		}
	}

}
