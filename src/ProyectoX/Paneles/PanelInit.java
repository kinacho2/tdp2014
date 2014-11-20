package ProyectoX.Paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProyectoX.Aplication;
import ProyectoX.Naves.Nave;

public class PanelInit   extends JPanel {

	private Aplication api;
	private JButton quit;
	private JButton newGame;
	private JLabel fondo;
	
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
			quit.setBounds(400 - 288/2, 257, 288, 50);
			quit.setBackground(new java.awt.Color(0,0,0));
			quit.setForeground(new java.awt.Color(0,255,0));
			quit.setPreferredSize(new java.awt.Dimension(14, 7));
			quit.setFont(new java.awt.Font("Segoe UI",0,20));
			quit.setBorder(BorderFactory.createCompoundBorder(null, null));
			quit.addActionListener(new OyenteQuit());
		}
		{
			
			ImageIcon icon = new ImageIcon(PanelInit.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/menu.gif"));
			fondo = new JLabel(icon);
			fondo.setBounds(0, 0, 800, 600);
			add(fondo);
		}
		
	}
	
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
		
	private class OyenteNuevoJuego implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
            setVisible(false);
            api.initMain(api.getReproductor());
            
		}
	}

}
