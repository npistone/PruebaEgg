

package libreria.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;




    @Entity
public class Cliente implements Serializable {
    @Id    
    private Long documento;
    private String nombre;
    private String apellido;
    private String domicilo;
    private String telefono;

    public Cliente() {
    }

    public Cliente(Long documento, String nombre, String apellido, String domicilo, String telefono) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilo = domicilo;
        this.telefono = telefono;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilo() {
        return domicilo;
    }

    public void setDomicilo(String domicilo) {
        this.domicilo = domicilo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente " + "documento=" + documento + ", nombre=" + nombre + 
                ", apellido=" + apellido + ", domicilo=" + domicilo +
                ", telefono=" + telefono + '}';
    }

    

}
