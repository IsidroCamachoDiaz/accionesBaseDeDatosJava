package accionesBasicasBaseDeDatos.servicios;

import java.sql.Connection;
import java.util.List;

import accionesBasicasBaseDeDatos.dtos.Libro;

public interface interfazAccionesBaseDeDatos {
	public Connection conexionBaseDeDatos();
	public List <Libro> insertarQuery(String query,Connection conexion);
	public void cerrarBaseDeDatos(Connection conexion);
	public void insertarDatos(List <Libro> libros ,Connection conexion);
	public void ActualizarDatos(List <Libro> libros ,Connection conexion);
}
