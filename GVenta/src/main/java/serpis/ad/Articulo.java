package serpis.ad;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="articulo"
    ,catalog="dbprueba"
    , uniqueConstraints = @UniqueConstraint(columnNames="nombre") 
)
public class Articulo {
     private Long id;
     private Categoria categoria;
     private String nombre;
     private BigDecimal precio;
     private Set<PedidoLinea> pedidolineas = new HashSet<PedidoLinea>(0);

    public Articulo() {
    }

    public Articulo(String nombre, BigDecimal precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public Articulo(Categoria categoria, String nombre, BigDecimal precio, Set<PedidoLinea> pedidolineas) {
       this.categoria = categoria;
       this.nombre = nombre;
       this.precio = precio;
       this.pedidolineas = pedidolineas;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoria")
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    @Column(name="nombre", unique=true, nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="precio", nullable=false, precision=10)
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="articulo")
    public Set<PedidoLinea> getPedidolineas() {
        return this.pedidolineas;
    }
    
    public void setPedidolineas(Set<PedidoLinea> pedidolineas) {
        this.pedidolineas = pedidolineas;
    }
    
    public String toString() {
    	if(this.getCategoria()!= null) {
		return String.format("%-5s%-15s%-15s%-15s", String.valueOf(this.getId()),
							this.getCategoria().getNombre(),
							this.getNombre(),
							String.valueOf(this.getPrecio()));
		
    	}else {
    		return String.format("%-5s%-15s%-15s%-15s", String.valueOf(this.getId()),
    				"",
    				this.getNombre(),
					String.valueOf(this.getPrecio()));
    		
    	}
    	
    }
   

}
