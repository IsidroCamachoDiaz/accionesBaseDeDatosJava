package accionesBasicasBaseDeDatos.servicios;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import accionesBasicasBaseDeDatos.dtos.Libro;
import accionesBasicasBaseDeDatos.util.Util;

public class implementacionAccionesPrincipales implements interfazAccionesPrincipal {

	@Override
	public void CrearDatos() {
		int creaciones = Util.CapturaEntero("Cuantos entidades quiere crear:", 1, 100);
		List <Libro> librosCrear= new ArrayList <Libro> ();
		interfazAccionesBaseDeDatos inter = new implementacionAccionesBaseDeDatos();
		Connection c=inter.conexionBaseDeDatos();
		Scanner leer= new Scanner(System.in);
		for(int i=0;i<creaciones;i++) {
			System.out.println("Titulo del libro");
			String titulo=leer.next();
			System.out.println("Nombre del autor del libro");
			String autor=leer.next();
			System.out.println("ISBN del libro");
			String sdbn=leer.next();
			System.out.println("Ediccion del libro");
			int ediccion=leer.nextInt();
			librosCrear.add(new Libro(0,titulo,autor,sdbn,ediccion));
		}
		inter.insertarDatos(librosCrear, c);
		System.out.println("Se han creado los "+librosCrear.size()+" libros");
		inter.cerrarBaseDeDatos(c);
	}

	@Override
	public void LeerDatos() {
		int opcion = Util.CapturaEntero("Que sea ver:\n1-Todos los datos\n2-Filtar\n", 1, 2);
		interfazAccionesBaseDeDatos inter = new implementacionAccionesBaseDeDatos();
		Scanner leer = new Scanner (System.in);
		try {
		Connection c=inter.conexionBaseDeDatos();
		List <Libro> libros;
		switch(opcion) {
		case 1:
			libros=inter.insertarQuery("SELECT * FROM gbp_almacen.gbp_alm_cat_libros", c);
			MostrarDatos(libros);
			break;
		case 2:
			int opcion2 = Util.CapturaEntero("Por que desea Filtar:\n1-ID\n2-Autor\n", 1, 2);
			switch(opcion2) {
			case 1:
				System.out.println("Por que id desea filtrar");
				long id=leer.nextLong();
				libros=inter.insertarQuery("SELECT * FROM gbp_almacen.gbp_alm_cat_libros WHERE	id_libro="+id, c);
				if(libros.isEmpty())
					System.out.println("No se enontro esa id en la base de datos");
				else
					MostrarDatos(libros);
				break;
			case 2:
				System.out.println("Introduzca el nombre del autor que desea filtar");
				String autor = leer.next();
				libros=inter.insertarQuery("SELECT * FROM gbp_almacen.gbp_alm_cat_libros WHERE	autor='"+autor+"'", c);
				if(libros.isEmpty())
					System.out.println("No se encontro el autor en la base de datos");
				else
					MostrarDatos(libros);
				break;
			}
			break;
		}
		}catch(Exception e) {
			System.out.println("Error en la implementacion de Acciones Principales "+e.getMessage());
		}

	}

	@Override
	public void ActualizarDatos() {
		interfazAccionesBaseDeDatos inter = new implementacionAccionesBaseDeDatos();

	}

	@Override
	public void BorrarDatos() {
		// TODO Auto-generated method stub

	}
	
	private void MostrarDatos(List <Libro> libros) {
		for(int i=0;i<libros.size();i++) {
			System.out.println(libros.get(i).toString());
		}
	}

}
