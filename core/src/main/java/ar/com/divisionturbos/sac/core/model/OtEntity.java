package ar.com.divisionturbos.sac.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the OTS database table.
 * 
 */
@Entity
@Table(name="OTS")
@NamedQuery(name="Ot.findAll", query="SELECT o FROM OtEntity o")
@Indexed
public class OtEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator = "otSequence")
	@SequenceGenerator(name="otSequence", sequenceName="SAC_OT_SEQUENCE")
	private long otId;

	@IndexedEmbedded
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "clienteId", nullable = false)
	private ClienteEntity cliente;

	private BigDecimal diashabiles;

	@Temporal(TemporalType.DATE)
	@Column(name="F_ALTA")
	private Date fAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="F_CIERRRE")
	private Date fCierre;

	private Long nrot;

	private BigDecimal qinst;

	private BigDecimal qinstfaltan;

	@Field
	private String estado;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaCreacion;

	private String usuarioCreacion;

	@IndexedEmbedded
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "otId")
	private List<ItemsEntity> detalleOtEntityList = new ArrayList<ItemsEntity>();

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getOtId() {
		return otId;
	}

	public void setOtId(long otId) {
		this.otId = otId;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getDiashabiles() {
		return diashabiles;
	}

	public void setDiashabiles(BigDecimal diashabiles) {
		this.diashabiles = diashabiles;
	}

	public Date getfAlta() {
		return fAlta;
	}

	public void setfAlta(Date fAlta) {
		this.fAlta = fAlta;
	}

	public Date getfCierre() {
		return fCierre;
	}

	public void setfCierre(Date fCierre) {
		this.fCierre = fCierre;
	}

	public Long getNrot() {
		return nrot;
	}

	public void setNrot(Long nrot) {
		this.nrot = nrot;
	}

	public BigDecimal getQinst() {
		return qinst;
	}

	public void setQinst(BigDecimal qinst) {
		this.qinst = qinst;
	}

	public BigDecimal getQinstfaltan() {
		return qinstfaltan;
	}

	public void setQinstfaltan(BigDecimal qinstfaltan) {
		this.qinstfaltan = qinstfaltan;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public List<ItemsEntity> getDetalleOtEntityList() {
		return detalleOtEntityList;
	}

	public void setDetalleOtEntityList(List<ItemsEntity> detalleOtEntityList) {
		this.detalleOtEntityList = detalleOtEntityList;
	}
}