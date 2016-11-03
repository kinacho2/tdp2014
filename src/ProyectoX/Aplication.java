package ProyectoX;


import javax.swing.JFrame;
import javax.swing.JPanel;

import ProyectoX.Naves.Jugador.Jugador;
import ProyectoX.Paneles.PanelFinal;
import ProyectoX.Paneles.PanelGame;
import ProyectoX.Paneles.PanelInit;
import ProyectoX.Paneles.PanelSelect;
import ProyectoX.Frames.MenteSplash;
import ProyectoX.Frames.SplashScreen;
import ProyectoX.Sound.Reproductor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Random;


/**
 * Clase que contiene el metodo main 
 * Inicializa paneles
 * @author Borek Andrea, Figliuolo Nestor, Gaviot Joaquin
*/
public class Aplication extends javax.swing.JFrame {
	

	private JPanel principal;
	private JPanel main;
	private static final String sound = "/ProyectoX/sounds/music/menu_";
	private Reproductor rep;
	private Random rn;
	private boolean enabled = false;
	private URL url = (Aplication.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/historia/Imagen1.png"));
	private URL url2 = (Aplication.class.getClassLoader().getResource("ProyectoX/img/Menu_barras/historia/Imagen2.png"));
	private String nombre;

	/**
	 * Constructor de la clase Aplication 
	 * inicia el reproductor y la GUI
	 */
	 public Aplication() {
		rep = new Reproductor();
		rn = new Random();
		initGUI();
    }

    public static void main(String[] args) {
        new Aplication();
        
    }
	    
    /**
     * Metodo que crea una SplashScreen antes de iniciar el juego
     * y procede a iniciar el juego con el parametro select
     * @param select indica la nave que usara el jugador
     */
    
    public void showSplashBeforeGame(int select){
    	setVisible(false);
    	MenteSplash spl = new MenteSplash(5000, url2, this, select);
    	spl.start();
    }
    
    /**
     * Metodo que inicializa el panel de juego,
     * es llamado por la SplashScrren anterior
     * @param select indica la nave que usara el jugador
     */
    
    public void initGame(int select){
    	rep.stop(100);
    	enabled = false;
    	setVisible(false);
    	
    	new PanelGame(this,select,nombre,rep);    
        
        setVisible(true);
        
	}
	    
    /**
     * inicializa el Frame y crea el primer panel del juego
     */
  
    private void initGUI() {
    	//#MODIFY
    	SplashScreen spl = new SplashScreen(100, url);
    	spl.showSplash();
    	initPrimerPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Chaos Wind");
        setResizable(false);
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				cerrarJuego();
			}
		});
        
	}
    
    /**
     * inicializa el primer panel para iniciar o salir del juego
     */
    
	public void initPrimerPanel(){
		if(!enabled){
			rep.addSound(sound+rn.nextInt(4)+".mp3",true);
			enabled = true;
		}
		setVisible(false);
		principal = new PanelInit(this);
		add(principal);
		setVisible(true);
	}
	
	/**
	 * inicializa el segundo panel para seleccionar tipo de nave
	 * @param rep es de tipo Reproductor, como el metodo se llamara desde afuera
	 * de la clase Aplication se debe setear el nuevo reproductor para controlar los hilos 
	 * del sonido
	 */
	
	public void initMain(Reproductor rep){
		if(!enabled){
			rep.addSound(sound+rn.nextInt(4)+".mp3",true);
			enabled = true;
		}
		this.rep = rep;
		setVisible(false);
		main = new PanelSelect(this);
		add(main);
		setVisible(true);
	}
	
	
	/**
	 * Inicia el panel Final donde se muestra la puntuacion final y la opsion de guardar
	 * @param jugador jugador actual
	 */
	public void initPanelFinal(Jugador jugador){
		setVisible(false);
		add(new PanelFinal(this,rep,jugador));
		setVisible(true);
	}
	
	/**
	 * cierra el juego en forma segura
	 */
	
	private void cerrarJuego() {
		this.dispose();
		System.exit(0);
		
	}
	
	/**
	 * retorna el atributo rep de tipo Reproductor
	 * @return instancia de Reproductor
	 */
	public Reproductor getReproductor(){
		return rep;
	}
	
	/**
	 * setea el nombre del Jugador
	 * @param arg nombre del Jugador
	 */
	
	public void setNombre(String arg){
		nombre = arg;
	}
}
