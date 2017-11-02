/**
 * 
 */
package gestorBBDD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Usuario;

/**
 * @author Antonio Paya Gonzalez
 *
 */
public class LectorCSV {
	
	public static final String SEPARADOR = "\t";
	
	public static List<Usuario> leerUsuarios(String fichero){
		BufferedReader bufferLectura = null;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			bufferLectura = new BufferedReader(new FileReader(fichero));
			String linea = bufferLectura.readLine();
			while (linea != null) {
				String[] campos = linea.split(SEPARADOR); 
				linea = bufferLectura.readLine();
				usuarios.add(new Usuario(campos[0],campos[1],campos[2],Integer.parseInt(campos[3]),campos[4],campos[5]));
		  }
		 } 
		 catch (IOException e) {
		  e.printStackTrace();
		 }
		 finally {
			 if (bufferLectura != null) {
				 try {
					 bufferLectura.close();
				 } 
				 catch (IOException e) {
				    e.printStackTrace();
				 }
			 }
		 }
		return usuarios;
	}
}
