package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Carrera;
import entities.Corredor;
import gestorBBDD.GestorDB;

public class LecturaCSV {


	//LecturaCSV.leerFichero("buenosTiempos.csv", carreras.get(0));
	
	
	public static void leerFichero(String ruta,Carrera carrera) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;

			int dorsal = -1;
			int tInicio = -1;
			String tFin = null;
			while ((linea = br.readLine()) != null) {
				String[] l = linea.split(",");
				if (l.length == 3) {
					if (!linea.split(",")[0].matches("[a-zA-Z].*")) {
						if (Integer.parseInt(linea.split(",")[0]) > 0) {
							dorsal = Integer.parseInt(linea.split(",")[0]);
						}
					}
					if (!linea.split(",")[0].matches("[a-zA-Z].*")) {
						if (Integer.parseInt(linea.split(",")[1]) == 0) {
							tInicio = Integer.parseInt(linea.split(",")[1]);
						}else{
							tInicio = -1;
						}
					}
					if (!linea.split(",")[0].matches("[a-zA-Z].*")) {
						String[] crono = l[2].split(":");
						if(crono.length==3){
							int horas=-1;
							int minutos=-1;
							int segundos=-1;
							if(!crono[0].matches(".*[a-zA-Z].*")){
								if(Integer.parseInt(crono[0])>=0){
									horas = Integer.parseInt(crono[0]);
								}
							}
							
							if(!crono[1].matches(".*[a-zA-Z].*")){
								if(Integer.parseInt(crono[1])>0 && Integer.parseInt(crono[1])< 60 ){
									minutos = Integer.parseInt(crono[1]);
								}
							}
							
							if(!crono[2].matches(".*[a-zA-Z].*")){
								if(Integer.parseInt(crono[2])>0 && Integer.parseInt(crono[2])< 60){
									segundos = Integer.parseInt(crono[2]);
								}
							}
							
							if(horas!=-1 && minutos!=-1 && segundos !=-1){
								tFin = horas+":"+minutos+":"+segundos;
							}
												
						}
					}
					
					if(dorsal!=-1 && tInicio !=-1 && tFin!=null){
						System.out.println("Buen tiempo si señor");
						asignaTiempo(carrera,dorsal,tFin);						
					}else{
						System.out.println("Mal tiempo si señor");
					}
					

					

				} else if (l.length == 2) {
					if (!linea.split(",")[0].matches(".*[a-zA-Z].*")) {
						if (Integer.parseInt(linea.split(",")[0]) > 0) {
							dorsal = Integer.parseInt(linea.split(",")[0]);
						}
					}
					if (!linea.split(",")[1].matches(".*[a-zA-Z].*")) {
						if (Integer.parseInt(linea.split(",")[1]) == 0) {
							tInicio = Integer.parseInt(linea.split(",")[1]);
						}else{
							tInicio = -1;
						}
					}
					
					if(dorsal!=-1 && tInicio==0){
						System.out.println("DNF");
						asignaTiempo(carrera,dorsal,tFin);						
					}else{
						System.out.println("DNF MAL");
					}

					
				} else if (l.length == 1) {
					if (!linea.split(",")[0].matches(".*[a-zA-Z].*")) {
						if (Integer.parseInt(linea.split(",")[0]) > 0) {
							dorsal = Integer.parseInt(linea.split(",")[0]);
						}else{
							dorsal =-1;
						}
					}
					
					if(dorsal!=-1){
						System.out.println("DNS");
						asignaTiempo(carrera,dorsal,tFin);							
					}else{
						System.out.println("DNS MAL");
					}

					
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	private static void asignaTiempo(Carrera carrera,int dorsal,String tiempo) throws SQLException {
		List<Corredor> corredores = GestorApp.getTodosLosCorredores(carrera);
		for(Corredor c:corredores){
			if(c.getDorsal()==dorsal){
				GestorDB.updateTiempo(c,tiempo);
			}
		}
		
	}
	
	
	
}
