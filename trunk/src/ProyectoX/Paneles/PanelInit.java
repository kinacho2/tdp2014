package ProyectoX.Paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProyectoX.Aplication;

public class PanelInit extends JPanel{

	private Aplication api;
	private JButton quit;
	private JButton newGame;
	
	public PanelInit(Aplication api){
		super();
		this.api = api;
		
		setLayout(null);
		setBackground(new java.awt.Color(0,0,0));
		
		{
			newGame = new JButton();
			add(newGame);
			newGame.setText("JUEGO NUEVO");
			newGame.setBounds(400 - 288/2, 90, 288, 50);
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
			quit.setBounds(400 - 288/2, 177, 288, 50);
			quit.setBackground(new java.awt.Color(0,0,0));
			quit.setForeground(new java.awt.Color(0,255,0));
			quit.setPreferredSize(new java.awt.Dimension(14, 7));
			quit.setFont(new java.awt.Font("Segoe UI",0,20));
			quit.setBorder(BorderFactory.createCompoundBorder(null, null));
			quit.addActionListener(new OyenteQuit());
		}
	}
	
	private class OyenteQuit implements ActionListener{

		@Override
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
            //api.initMain();
            
		}
	}
}
