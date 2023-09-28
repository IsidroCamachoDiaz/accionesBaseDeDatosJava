package accionesBasicasBaseDeDatos.dtos;

public class Libro {
	long idLibro;
	String titulo;
	String autor;
	String isbn;
	int edicion;
		
	public Libro(long idLibro, String titulo, String autor, String isbn, int edicion) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.edicion = edicion;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getEdicion() {
		return edicion;
	}
	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}
	public long getIdLibro() {
		return idLibro;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn + ", edicion="
				+ edicion + "]";
	}
	

}
