package accionesBasicasBaseDeDatos.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import accionesBasicasBaseDeDatos.dtos.Libro;

public class implementacionAccionesBaseDeDatos implements interfazAccionesBaseDeDatos {

	@Override
	public Connection conexionBaseDeDatos() {
		Connection BaseDatos = null;
		String [] parametros = pasaParametros("C:\\\\Users\\\\Puesto3\\\\git\\\\pruebaBaseDeDatos\\\\src\\\\modelo\\\\claves.properties");
		try {
			
		 //Se pasa los datos de conexion
	    BaseDatos = DriverManager.getConnection(parametros[0], parametros[1], parametros[2]);
	    //Comprobamos la conexion
	    boolean esValidad=BaseDatos.isValid(5000);
	    
	    if(!esValidad)
	    	BaseDatos=null;
	    
		}catch(Exception e) {
			System.out.println("Se produjo un fallo en la conexion de datos en la implementacion"+e.getMessage());
		}
		return BaseDatos;
	}

	@Override
	public List<Libro> insertarQuery(String query, Connection conexion) {
		Statement st = null;
		ResultSet rs= null;
		List <Libro> libros=null;
		try {
			//Se relaciona la base de datos
		    st = conexion.createStatement();
		    //Se mete la query
		    rs = st.executeQuery(query);
		    libros=pasaDto(rs);
		    st.close();
		    rs.close();
		}catch(SQLException eq) {
			System.out.println("Se produjo un fallo en la query en la implementacion de accion de base de datos "+eq.getMessage());
		}catch(Exception e) {
			System.out.println("Se produjo un fallo en leer datos en la implementacion"+e.getMessage());
		}
		return libros;
	}

	@Override
	public void cerrarBaseDeDatos(Connection conexion) {
		try {
		conexion.close();
		}catch(SQLException e) {
			System.out.println("no se pudo desconectar la  base de datos en la implementacion"+e.getMessage());	
		}
	}

	@Override
	public void insertarDatos(List<Libro> libros, Connection conexion) {
		Statement st = null;
		int e=0;
		try {
			//Se relaciona la base de datos
		    st = conexion.createStatement();
		    
		    for(int i=0;i<libros.size();i++) {
		    	String sql = "INSERT INTO gbp_almacen.gbp_alm_cat_libros (titulo,autor,isbn,edicion) "
		    + "VALUES ('"+libros.get(i).getTitulo()+"','"+libros.get(i).getAutor()+"', '"+libros.get(i).getIsbn()+"', '"+libros.get(i).getEdicion()+"');";
		          st.executeUpdate(sql);
		          e=i;
		    }
		    st.close();
		}catch(Exception ed) {
			System.out.println("no se insertar los datos en la implementcion"+ed.getMessage()+"Se ha insertado hasta "+e);	
		}

	}

	@Override
	public void ActualizarDatos(List<Libro> libros, Connection conexion) {
		// TODO Auto-generated method stub

	}
	private String[] pasaParametros(String ruta) {
		Properties properties= new Properties();
		String [] parametros = new String [3];
		try {
			properties.load(new FileInputStream(new File(ruta)));
			parametros[0]=properties.getProperty("jdbc");
			parametros[1]=properties.getProperty("USUARIO");
			parametros[2]=properties.getProperty("CLAVE");
			
		}catch(Exception e) {
			System.out.println("Se produjo un error en PasaParemetros de la implementacion "+e.getMessage());
		}
		return parametros;
	}
	
	private List<Libro> pasaDto(ResultSet rs){
		List <Libro> libros = new ArrayList <Libro>();
		try {
		 while(rs.next() ) {
			    libros.add( new Libro(rs.getInt("id_libro"),rs.getString("titulo"),rs.getString("autor"),rs.getString("isbn"),rs.getInt("edicion")));
			    }
		}catch(SQLException sq) {
			System.out.println("Se produjo un error de lectura en la implementacion "+sq.getMessage());
		}
		return libros;
		
	}

}
