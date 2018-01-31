package serpis.ad;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PedidoLinea generated by hbm2java
 */
@Entity
@Table(name="pedidolinea"
    ,catalog="dbprueba"
)
public class PedidoLinea  {

     private long id;
     @ManyToOne
     @JoinColumn(name="articulo")
     private Articulo articulo;
     @ManyToOne
     @JoinColumn(name="pedido")
     private Pedido pedido;
     private BigDecimal precio;
     private BigDecimal unidades;
     private BigDecimal importe;
    
    public PedidoLinea(Articulo articulo, Pedido pedido, BigDecimal precio, BigDecimal unidades, BigDecimal importe) {
       this.articulo = articulo;
       this.pedido = pedido;
       this.precio = precio;
       this.unidades = unidades;
       this.importe = importe;
    }
   
    public PedidoLinea() {
		// TODO Auto-generated constructor stub
	}

	@Id 
    @GeneratedValue(strategy=IDENTITY)

    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="articulo", nullable=false)
    public Articulo getArticulo() {
        return this.articulo;
    }
    
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pedido", nullable=false)
    public Pedido getPedido() {
        return this.pedido;
    }
    
    //visibilidad a nivel package
    void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    
    @Column(name="precio", precision=10)
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    @Column(name="unidades", precision=10)
    public BigDecimal getUnidades() {
        return this.unidades;
    }
    
    public void setUnidades(BigDecimal unidades) {
        this.unidades = unidades;
    }
    
    @Column(name="importe", precision=10)
    public BigDecimal getImporte() {
        return this.importe;
    }
    
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    @Override
    public String toString(){
   
    	return String.format("[%s] (%s)", id, articulo.getNombre(), pedido);
    	
    }

}

