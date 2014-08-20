package proyecto0.src;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PanelAPI extends javax.swing.JFrame {
	private JPanel principal;
	private JTextArea console;
	private JButton comenzar;
	private JButton load;
	private JComboBox<String> menuIdiomas;
	private File[] files;
	private JFileChooser chooser;
	private String chooseTitle;
	private String spanish;
	private String english;
	boolean idiom;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PanelAPI inst = new PanelAPI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public PanelAPI() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				principal = new JPanel();
				getContentPane().add(principal, BorderLayout.CENTER);
				principal.setLayout(null);
				principal.setBackground(new java.awt.Color(128,128,192));
				{
					load = new JButton();
					principal.add(load);
					load.setText("Cargar Directorio");
					load.setBounds(35, 66, 148, 23);
					load.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							    chooser = new JFileChooser(); 
							    //chooser.setCurrentDirectory(new java.io.File("."));
							    if(idiom){
							    	chooser.setDefaultLocale(Locale.getDefault());
							    }
							    else{
							    	chooser.setDefaultLocale(Locale.ENGLISH);
							    }
							    chooser.setDialogTitle(chooseTitle);
							    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							    //
							    // disable the "All files" option.
							    //
							    chooser.setAcceptAllFileFilterUsed(false);
							    //    
							    if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) { 
							    	files=chooser.getSelectedFile().listFiles();
									comenzar.setEnabled(true);
							    }
						}
					});
				}
				{
					comenzar = new JButton();
					principal.add(comenzar);
					comenzar.setText("Comenzar");
					comenzar.setBounds(202, 66, 148, 23);
					comenzar.setEnabled(false);
					comenzar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
						    /*
						     * IMPLEMENTAR 
						     * METODO
						     * AQUI
						     * 
						     * 
						     */
				
					}
				});
				}
				{
					menuIdiomas = new JComboBox<String>();
					principal.add(menuIdiomas);
					menuIdiomas.setBounds(0, 0, 82, 18);
					spanish = "Espa�ol";
					english = "Ingles";
					chooseTitle = "Seleccionar Directorio";
					menuIdiomas.addItem(spanish);
					menuIdiomas.addItem(english);
					idiom = true;
					menuIdiomas.addActionListener(new OyenteMenu());
					
				}
				{
					console = new JTextArea();
					principal.add(console);
					console.setBounds(12, 108, 360, 142);
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	private void crearMenu(){
		menuIdiomas.setVisible(false);
		  menuIdiomas = new JComboBox<String>();
		  principal.add(menuIdiomas);
		  menuIdiomas.setBounds(0, 0, 82, 18);
		  menuIdiomas.addItem(spanish);
		  menuIdiomas.addItem(english);
		  if(idiom){
			  menuIdiomas.setSelectedIndex(0);
		  }
		  else{
			  menuIdiomas.setSelectedIndex(1);
		  }
		  menuIdiomas.addActionListener(new OyenteMenu());
	}
	private class OyenteMenu implements ActionListener{

		public void actionPerformed(ActionEvent evt) {
			  
			  if (idiom){
				  if(menuIdiomas.getSelectedItem().toString().equals("Ingles")){
					  idiom = false;
					  load.setText("Load Directory");
					  comenzar.setText("Start");
					  spanish = "Spanish";
					  english = "English";
					  chooseTitle = "Select Directory";
					  crearMenu();
				  }
			  }
			  else{
				  if(menuIdiomas.getSelectedItem().toString().equals("Spanish")){
					  idiom = true;
					  load.setText("Cargar Directorio");
					  comenzar.setText("Comenzar");
					  spanish = "Espa�ol";
					  english = "Ingles";
					  chooseTitle = "Seleccionar Directorio";
					  crearMenu();
				  }
			  }
		}
		
	}
}
