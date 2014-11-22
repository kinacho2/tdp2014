package ProyectoX.Paneles;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoX.Aplication;
import ProyectoX.Sound.Reproductor;

/**
 * Panel que muestra el ranking
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */
public class PanelRanking extends JPanel{

	private Aplication api;
	private String ranking;
	private JButton atras;
	public PanelRanking(Aplication api,String ranking){
		this.api = api;
		this.ranking = ranking;
		api.setVisible(false);
		
		setLayout(null);
		{
			atras = new JButton();
			add(atras);
			atras.setText("VOLVER");
			atras.setBounds(800/2 - 50 - 41, 472, 41, 29);
			atras.setForeground(new java.awt.Color(0,255,0));
			atras.setBackground(new java.awt.Color(0,0,0));
			atras.setBorder(BorderFactory.createCompoundBorder(null,null));
			atras.setFont(new java.awt.Font("Segoe UI",0,20));
			atras.addActionListener(new OyenteAtras());

		}
		
		setBounds(0,0,800,600);
		api.add(this);
		api.setVisible(true);
	}
	
	private class OyenteAtras implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			setVisible(false);
			api.initPrimerPanel();
		}
	}
}
