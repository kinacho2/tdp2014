package ProyectoX.Minds;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Ranking {
	private PriorityQueue<Integer> ranking;
	private Map<Integer, String> mapeo;
	
	public Ranking(){
		ranking = new PriorityQueue<Integer>();
		
		//leerArchivo();
		
		
	}
	
	private void leerArchivo(){
		try{
			mapeo = new HashMap<Integer, String>();
			InputStream lector = (Ranking.class.getClassLoader().getResourceAsStream("ProyectoX/Minds/ranking.txt"));
			char c = (char) lector.read();
			String ss ;
			while(c!='@'){
				
				ss = "";
				
				while(c!=' '){
					ss+=c;
					c = (char) lector.read();
				}
				Integer punt =  new Integer(ss).intValue();
				ranking.add(punt);
				c = (char) lector.read();
				ss = "";
				while(c!=' '){
					ss+=c;
					c = (char) lector.read();
					
				}
				mapeo.put(punt, ss);
				c = (char) lector.read();
				
			}
		}
		catch(IOException ex){
			
		}
	}
	
	private void writeRanking(){
		try {
			OutputStream fileOutput;
		
			fileOutput = new FileOutputStream ((Ranking.class.getClassLoader().getResource("ProyectoX/Minds/ranking.txt")).getPath());
		
			BufferedOutputStream fichero = new BufferedOutputStream(fileOutput);
			String linea = "";
			while(!ranking.isEmpty()){
				Integer h = ranking.poll();
				linea = ""+h+" "+mapeo.get(h)+" "+linea;
				
			}
			linea += "@";
			fichero.write(linea.getBytes());
			
			fichero.close();
			fileOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void insertNewPuntaje(String nom, Integer punt){
		leerArchivo();
		mapeo.put(punt, nom);
		ranking.add(punt);
		writeRanking();
		
	}
	
	/**
	 * retorna un string con los puntajes
	 * @return puntuaciones
	 */
	
	public String mostrarPuntaje(){
		String linea = "";
		leerArchivo();
		int cont = 1;
		while(!ranking.isEmpty()){
			Integer h = ranking.poll();
			linea = ""+"@"+"º	"+mapeo.get(h)+"	"+h+"\n"+linea;
			cont ++;
		}
		int i = 1;
		int j = 0;
		String aux = "";
		while(i < cont){
			
			char c = linea.charAt(j);
			 
			while(c!='@' && j < linea.length()){
				aux += c;
				j++;
				c = linea.charAt(j);
			}
			j++;
			aux+=""+i;
			i++;
		}
		
		return aux;
	}
	
	/**
	 * Borra el archivo de puntajes del juego
	 */
	
	public void borrarArchivo(){
		try {
			OutputStream fileOutput;
		
			fileOutput = new FileOutputStream ((Ranking.class.getClassLoader().getResource("ProyectoX/Minds/ranking.txt")).getPath());
		
			BufferedOutputStream fichero = new BufferedOutputStream(fileOutput);
			String linea = "@";

			fichero.write(linea.getBytes());
			
			fichero.close();
			fileOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
