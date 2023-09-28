package accionesBasicasBaseDeDatos.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import accionesBasicasBaseDeDatos.util.Util;
import accionesBasicasBaseDeDatos.servicios.interfazAccionesPrincipal;
import accionesBasicasBaseDeDatos.servicios.implementacionAccionesPrincipales;

public class Inicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creamos lo necesario
				Scanner leer = new Scanner (System.in);
				int opcion=0;
				interfazAccionesPrincipal inter = new implementacionAccionesPrincipales();
				//Entra en el bucle 
				do {
					Util.menu();
					opcion=Util.CapturaEntero("Introduzca una opcion", 0, 4);
					//Comprueba la opcion depediendo de lo que necesite
					switch(opcion) {
					case 1:
						inter.CrearDatos();
						break;
					case 2:
						inter.LeerDatos();
						break;
					case 3:
						break;
					case 4:
						break;
					}
					
				}while(opcion!=0);
				//Cierra el scanner
				leer.close();
	}

}
