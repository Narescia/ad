package serpis.ad;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Categoria {
	@Id
	private long id;
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s", id, nombre);
	}
}
