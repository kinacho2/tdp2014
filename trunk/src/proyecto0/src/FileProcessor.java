package proyecto0.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;



import TDAEntry.Entry;
import TDAEntry.Entrada;


/**
 * Clase FileProcessor
 * @author Nestor Fligiuolo, Joaquin Gaviot Y Andrea Borek
 *
 */
public class FileProcessor {
	
        private HashMap<String,Integer> mapa;
        Entry<String,Integer>[] array;
        
       
        public FileProcessor() {
        	mapa = new HashMap<String,Integer>();
            array = (Entry<String,Integer>[]) new Entrada[6];

        }
        
        /**
         * Lee cada uno de los archivos del arreglo, inserta cada una de las palabras en un mapa y determina su frecuencia.
         * Además establece las 5 palabras más usadas en los archivos
         * @param arreglo de archivos
         * @return arreglo con las entradas de palabras y frecuancia
         * @throws IOException
         */
        
        public Entry<String, Integer>[] procesar(File[] archivos) throws IOException {
        	
                String stringAux=new String();
               
                //lee cada archivo y lo almacena en un string todo concatenado.
                for(int i=0;i<archivos.length;i++){
                        stringAux+=leerArchivo(archivos[i]);
                }
                //eliminar todas las apariciones de simbolos reemplazandolos por un vacio ("")
                String tokens= ".,;:()*&%$-_=+[{]}|<>?/!@";
                for(int i=0;i<tokens.length();i++){
                	stringAux = stringAux.replace(""+tokens.charAt(i), "");
                }
                
               
                //Se crea un StringTokenizer para buscar cada palabra y meterlas en un diccionario sin el caracter espacio.
                StringTokenizer stEspacio = new StringTokenizer(stringAux," ");
                Integer frecuencia;
                String palabra;
                //Se crea un arreglo para almacenar las 5 palabras mas repetidas
                int i=0;
                while (stEspacio.hasMoreTokens()) {
                	palabra = stEspacio.nextToken().toUpperCase();
                	
                		frecuencia = mapa.get(palabra);
	                	if (frecuencia != null) {
	                		frecuencia++;
	                		System.out.print("");
	            		}
	                	else
	                		frecuencia = 1;
	                	mapa.put(palabra, frecuencia);
	                	Entry<String,Integer> nuevaEntrada = new Entrada<String,Integer>(palabra, frecuencia);
	                	if(i<array.length-1 && array[i]==null){
	                		array[i] = nuevaEntrada;
	                		i++;
	                	} else {
	                		if(frecuencia != null && frecuencia > array[4].getValue()){
	                			insertar(nuevaEntrada);
	                		}
	                	
	                	}
	                	
            }
                
            return array;
        }
        
        /**
         * Inserta la palabra en el lugar correspondiente
         * @param entrada 
         */
        
        private void insertar(Entry<String, Integer> en){
        	boolean inserte = false;
        	boolean inserte2=false;
        	int i = 4;
        	
        	/*
        	 * busca la posicion para insertar e inserta ordenadamente
        	 * si se inserto de la primer forma (insertar = true) es porque 
        	 * se incremento el contador de la palabra correspondiente a la entrada pasada por parametro
        	 * y es posible que deba realizarse un ordenamiento 
        	 * si se inserto de la otra forma (insertar2 = true) quiere decir que se realizo un corrimiento hacia abajo
        	 * antes de insertar y el arreglo queda ordenado
        	 */
        	while(i>=0 && !inserte && !inserte2){
        		if(array[i].getKey().equals(en.getKey())){
        			inserte = true;
        			array[i] = en;
        		}
        		else{
        			if(array[i].getValue() < en.getValue()){
        				i--;
        			}
        			else{
        				correrPosiciones(i);
        				inserte2 = true;
        				array[i] = en;
        			}
        		}
        		
        	}
        	//si se inserto de esta manera significa que es posible que deba efectuarse un corrimiento
        	if(inserte){
    			while(i>0 && array[i].getValue() >= array[i-1].getValue()){
    				Entry<String, Integer> aux = array[i];
    				array[i] = array[i-1];
    				array[i-1] = aux;
    				i--;
    			}
        	}
        	
        }
        
        /**
         * Desplaza una posición los elementos desde el índice indicada 
         * @param posición
         */
        private void correrPosiciones(int pos) {
        	for (int i = 4; i >= pos; i--){
        		array[i+1] = array[i];
        	}
        	array[5] = null;
        }
       
        /**
         * Abre el archivo he inserta el texto del mismo en un string, luego devuelve este último
         * @param file
         * @return String del texto del archivo
         * @throws IOException
         */
        private String leerArchivo( File file ) throws IOException {
        	//codigo obtenido con ayuda del siguiente link:
            //http://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
        	String s=new String();
              
            //Crea un BufferedReader para leer de un archivo.
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