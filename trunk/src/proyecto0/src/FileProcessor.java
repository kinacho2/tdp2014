package proyecto0.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import TDAMapa.Entry;
import TDAMapa.InvalidKeyException;
import TDAMapa.MapeoHashAbierto;
import TDAMapa.Map;
 
public class FileProcessor {
	
        private String[] arreglo;
        private Map<String,Integer> mapa;
       
        
        
        public FileProcessor() {
        	mapa = new MapeoHashAbierto<String,Integer>();
        }
        
       
        
        public void procesar(File[] archivos) throws IOException {
                //crea el arreglo de strings que va a retornar.
                String[] ret= new String[5];
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
                
               
                //Creo un StringTokenizer para buscar cada palabra y meterlas en un diccionario sin el caracter espacio.
                StringTokenizer stEspacio = new StringTokenizer(stringAux," ");
                Integer frecuencia;
                String palabra;
                while (stEspacio.hasMoreTokens()) {
                	palabra = stEspacio.nextToken().toUpperCase();
                	try {
						frecuencia = mapa.get(palabra);
	                	if (frecuencia != null) {
	                		frecuencia++;
	            		}
	                	else
	                		frecuencia = 1;
	                	mapa.put(palabra, frecuencia);
	                	
                	} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            }
        }
       
        //codigo obtenido con ayuda del siguiente link:
        //http://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
        private String leerArchivo( File file ) throws IOException {
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
        
        //Devuelve las 5 palabras con másfrecuencia en una lista
        public List<String> calcularPalabrasMasUsadas(){
        	List<String> lista = new LinkedList<String>();
        	
        	for (Entry<String,Integer> entrada : mapa.entries()) {
        		
        		//Si la lista lista no tiene 5 elementos los inserta ya que son los menores hasta ese momento      
        		if (lista.size() < 5) {
        			lista.add(entrada.getKey());        			
        		} else {
        			
        			//busca la palabra con menor frecuencia y luego se compara con la entrada actual
        			String menorFrecuencia = buscarPalabraMinima(lista, mapa);
        		
        			try {
						if (mapa.get(menorFrecuencia) < entrada.getValue()) {
							lista.remove(menorFrecuencia);
							lista.add(entrada.getKey());
						}
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					}
        		
        		}
        	}
        	modificarLista(lista);
        	return lista;
        }
        
        private void modificarLista(List<String> lista){
        	try {
	        	List<String> aux = new LinkedList<String>();
	        	for (String palabra : lista) {
					aux.add(palabra+": "+mapa.get(palabra));
		        }
	        	lista.removeAll(lista);
	        	for(String palabra : aux){
	        		lista.add(palabra);
	        	}
        	} 
        	catch (InvalidKeyException e) {
				e.printStackTrace();
			}
        }
        
        // busca la palabra con menor frecuencia dentro de la lista
        private String buscarPalabraMinima(List<String> lista, Map<String,Integer> mapa) {
        	
        	String menor = lista.get(0);
        	
        	try {
            	//Recorre los 5 elementos de la lista y busca el menor
        		for (String palabra : lista) {
						if (mapa.get(palabra) < mapa.get(menor))
							menor = palabra;
	        	}
        		
        	} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
        	return menor;
        }
}