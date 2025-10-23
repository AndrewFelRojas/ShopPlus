package Model;

import java.time.LocalDateTime;

/**
 * Representa un envío realizado por un proveedor a un cliente.
 * Cada envío está asociado a un producto, una cantidad enviada y una fecha de registro.
 * @author andres
 */
public class Envio {
    private String emailProveedor;
    private String idProducto;
    private int cantidad;
    private LocalDateTime fecha;

    /**
     * Constructor completo para crear un objeto Envio.
     *
     * @param emailProveedor Correo electrónico del proveedor
     * @param idProducto Identificador del producto enviado
     * @param cantidad Cantidad de unidades enviadas
     * @param fecha Fecha y hora del envío
     */
    public Envio(String emailProveedor, String idProducto, int cantidad, LocalDateTime fecha) {
        this.emailProveedor = emailProveedor;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    /** @return Correo electrónico del proveedor */
    public String getEmailProveedor() {
        return emailProveedor;
    }

    /** @param emailProveedor Nuevo correo electrónico del proveedor */
    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    /** @return Identificador del producto enviado */
    public String getIdProducto() {
        return idProducto;
    }

    /** @param idProducto Nuevo identificador del producto */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /** @return Cantidad de unidades enviadas */
    public int getCantidad() {
        return cantidad;
    }

    /** @param cantidad Nueva cantidad de unidades enviadas */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /** @return Fecha y hora del envío */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /** @param fecha Nueva fecha y hora del envío */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Representación en texto del envío.
     *
     * @return Cadena con los datos del envío separados por comas
     */
    @Override
    public String toString() {
        return emailProveedor + "," + idProducto + "," + cantidad + "," + fecha;
    }
}