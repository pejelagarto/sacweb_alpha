package ar.com.divisionturbos.sac.core.enums;

/**
 * Created by mzanetti on 09/06/17.
 */
public enum EstadoEnum {

    ACTIVO("AC" ,"Activo"),
    INACTIVO ("IN","Inactivo");

    private String codigo;
    private String descripcion;

    private EstadoEnum(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static String getDescripcion(String codigo) {
        for (EstadoEnum v : values()) {
            if (v.codigo == codigo) return v.descripcion;
        }
        return "";
    }

}
