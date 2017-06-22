package ar.com.divisionturbos.sac.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.*;
import org.hibernate.search.annotations.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DETALLEOTS database table.
 */
@Entity
@Table(name = "ITEMS")
@NamedQuery(name = "Detalleot.findAll", query = "SELECT d FROM ItemsEntity d")
public class ItemsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "itemsSequence")
    @SequenceGenerator(name = "itemsSequence", sequenceName = "SAC_ITEM_SEQUENCE")
    private Long itemsId;

/*
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "clienteId", nullable = false)
    private ClienteEntity cliente;
*/
    private Long otId;

    private String descripcion;

    private String elemento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "F_CAL")
    private Date fCal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "F_EGCERT")
    private Date fEgcert;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "F_EGINST")
    private Date fEginst;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "F_IN")
    private Date fIn;

    @Field
    private BigDecimal item;

    private String labterc;

    private String marca;

    private String modelo;

    private String nrcertif;
    @Field
    private Long nrot;

    private String rango;

    private String sn;


    @Transient
    private Long itm;


    @Type(type = "true_false")
    private Boolean tercerizado;

    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long itemsId) {
        this.itemsId = itemsId;
    }

    public Long getOtId() {
        return otId;
    }

    public void setOtId(Long otId) {
        this.otId = otId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Date getfCal() {
        return fCal;
    }

    public void setfCal(Date fCal) {
        this.fCal = fCal;
    }

    public Date getfEgcert() {
        return fEgcert;
    }

    public void setfEgcert(Date fEgcert) {
        this.fEgcert = fEgcert;
    }

    public Date getfEginst() {
        return fEginst;
    }

    public void setfEginst(Date fEginst) {
        this.fEginst = fEginst;
    }

    public Date getfIn() {
        return fIn;
    }

    public void setfIn(Date fIn) {
        this.fIn = fIn;
    }

    public BigDecimal getItem() {
        return item;
    }

    public void setItem(BigDecimal item) {
        this.item = item;
    }

    public String getLabterc() {
        return labterc;
    }

    public void setLabterc(String labterc) {
        this.labterc = labterc;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNrcertif() {
        return nrcertif;
    }

    public void setNrcertif(String nrcertif) {
        this.nrcertif = nrcertif;
    }

    public Long getNrot() {
        return nrot;
    }

    public void setNrot(Long nrot) {
        this.nrot = nrot;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getItm() {
        return itm;
    }

    public void setItm(Long itm) {
        this.itm = itm;
    }

    public Boolean getTercerizado() {
        return tercerizado;
    }

    public void setTercerizado(Boolean tercerizado) {
        this.tercerizado = tercerizado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemsEntity)) return false;

        ItemsEntity that = (ItemsEntity) o;

        return itm != null ? itm.equals(that.itm) : that.itm == null;
    }

    @Override
    public int hashCode() {
        return itm != null ? itm.hashCode() : 0;
    }
}