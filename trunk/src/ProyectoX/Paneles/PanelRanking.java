package ProyectoX.Paneles;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import ProyectoX.Aplication;
import ProyectoX.Minds.Ranking;
import ProyectoX.Sound.Reproductor;

/**
 * Panel que muestra el ranking
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
 */
public class PanelRanking extends JPanel{

	private Aplication api;
	private String ranking;
	private JButton atras;
	private JButton borrar;
	private JTextArea text ;
	/**
	 * 
	 * @param api Aplicacion actual
	 * @param ranking String que contiene el ranking completo de jugadores
	 */
	public PanelRanking(Aplication api,String ranking){
		this.api = api;
		this.ranking = ranking;
		api.setVisible(false);
		setBackground(new java.awt.Color(0,0,0));
		setLayout(null);
		
		{

			text = new JTextArea();
			text.setBackground(new java.awt.Color(0,0,0));
			text.setForeground(new java.awt.Color(0,255,0));
			text.setFont(new java.awt.Font("Segoe UI",0,20));
			text.setBounds(75, 55, 600, 411);

			add(text);
			text.setText(ranking);
		}
		{
			borrar = new JButton();
			add(borrar);
			borrar.setText("BORRAR RANKING");
			borrar.setBounds(531, 500, 188, 30);
			borrar.setForeground(new java.awt.Color(0,255,0));
			borrar.setBackground(new java.awt.Color(0,0,0));
			borrar.setBorder(BorderFactory.createCompoundBorder(null,null));
			borrar.setFont(new java.awt.Font("Segoe UI",0,20));
			borrar.addActionListener(new OyenteBorrar());

		}
		{
			atras = new JButton();
			add(atras);
			atras.setText("VOLVER");
			atras.setBounds(70, 500, 75, 30);
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
	
	/**
	 * Implementa ActionListener
	 * Retorna al primer panel
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteAtras implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			
			setVisible(false);
			api.initPrimerPanel();
		}
	}
	
	/**
	 * Implementa ActionListener
	 * Borra el Ranking actual
	 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
	 */
	
	private class OyenteBorrar implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			int verd;
            
			JOptionPane dialogo = new JOptionPane();
	            
			verd = dialogo.showConfirmDialog(null, "Desea borrar las puntuaciones\nEsta operacion es irreversible?", "Atencion",JOptionPane.YES_NO_OPTION);
				
			if(verd == 0){
				Ranking ran = new Ranking();
				ran.borrarArchivo();
				text.setText("");
			}
			
		}
	}
}
