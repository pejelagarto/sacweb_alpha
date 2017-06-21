package ar.com.divisionturbos.sac.core.model;

import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mzanetti on 05/06/17.
 */
@Entity
@Table(name="CLIENTES")
public class ClienteEntity implements Serializable {

    Long clienteId;
    BigDecimal nroOt;
    Long cuit;
    String razonSocial;
    String direccion;
    String telefono;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "clienteSequence")
    @SequenceGenerator(name="clienteSequence", sequenceName="SAC_CLIENTE_SEQUENCE")
    @Field
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getNroOt() {
        return nroOt;
    }

    public void setNroOt(BigDecimal nroOt) {
        this.nroOt = nroOt;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
