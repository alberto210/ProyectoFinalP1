package logico;

public class Plan {

	private String tipo;
	private String nombre;
	private String id;
	private float precio;
	
	public Plan(String tipo, String nombre, String id, float precio) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.id = id;
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
