package Model;
import java.time.LocalDateTime;

/**
 * Representa un pedido realizado por un cliente.
 * Cada pedido contiene el correo del cliente, el ID del producto solicitado y la fecha en que se registró.
 * @author andres
 */
public class Pedido {

    /** Correo electrónico del cliente que realizó el pedido */
    private String emailCliente;

    /** Identificador del producto solicitado */
    private String idProducto;

    /** Fecha y hora en que se registró el pedido */
    private LocalDateTime fecha;

    /**
     * Constructor completo para crear un objeto Pedido.
     *
     * @param emailCliente Correo electrónico del cliente
     * @param idProducto Identificador del producto solicitado
     * @param fecha Fecha y hora del pedido
     */
    public Pedido(String emailCliente, String idProducto, LocalDateTime fecha) {
        this.emailCliente = emailCliente;
        this.idProducto = idProducto;
        this.fecha = fecha;
    }

    /** @return Correo electrónico del cliente */
    public String getEmailCliente() {
        return emailCliente;
    }

    /** @param emailCliente Nuevo correo electrónico del cliente */
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    /** @return Identificador del producto solicitado */
    public String getIdProducto() {
        return idProducto;
    }

    /** @param idProducto Nuevo identificador del producto */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /** @return Fecha y hora del pedido */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /** @param fecha Nueva fecha y hora del pedido */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Representación en texto del pedido, útil para guardar en archivos CSV.
     *
     * @return Cadena con los datos del pedido separados por comas
     */
    @Override
    public String toString() {
        return emailCliente + "," + idProducto + "," + fecha;
    }
}