package proyecto0.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ProcesadorDeArchivos {
	private String[] arreglo;
	private LinkedList<String> lista;
	
	public ProcesadorDeArchivos() {
		lista= new LinkedList<String>();
	}
	
	public String[] procesar(File[] archivos) throws IOException {
		//creo el arreglo de strings que voy a retornar.
		String[] ret= new String[5];
		String stringAux=new String();
		
		//leo cada archivo y lo almaceno en un string todo concatenado.
		for(int i=0;i<archivos.length;i++)
			stringAux+=leerArchivo(archivos[i].getPath());
		
		//creo un StringTokenizer para eliminar todas las apariciones de '.', ';', ':', '(', ')' y de ','.
		StringTokenizer st = new StringTokenizer(stringAux,".;;(),");
		while (st.hasMoreTokens()) {
	        stringAux+=st.nextToken();
	    }
		
		//Creo otro StringTokenizer para buscar cada palabra y meterlas en la lista sin el caracter espacio.
		StringTokenizer stEspacio = new StringTokenizer(stringAux," ");
		while (stEspacio.hasMoreTokens()) {
	        lista.add(st.nextToken());
	    }
		//ret= claseDeAndrea.metodoDeAndrea(lista);
		//Retorna un arreglo de strings con las 5 palabras que mas veces aparecen entre todos los archivos.
		return ret;
	}
	
	//codigo obtenido con ayuda del siguiente link:
	//http://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
	private String leerArchivo( String file ) throws IOException {
		String s=new String();
		
		//Creo un BufferedReader para leer de un archivo.
		BufferedReader lector = new BufferedReader(new FileReader(file));
		
		//Leo cada linea mientras tenga lineas por leer en el archivo.
		String linea=lector.readLine();
		while (linea!=null) {
			s+=linea+" ";
			linea=lector.readLine();
		}
		//cierro el BufferedReader.
		lector.close();
		return s;
	}
}
