package Model;
import java.util.List;

import Exception.ProductoNoEncontradoException;

/**
 * Representa un producto disponible en el sistema ShopPlus.
 * Contiene información como ID, nombre, precio y cantidad en inventario.
 * @author andres
 */
public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int cantidad;

    /**
     * Crea un nuevo producto con los datos especificados.
     * @param id Identificador único del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio unitario del producto.
     * @param cantidad Cantidad disponible en inventario.
     */
    public Producto(String id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    /** @return ID del producto. */
    public String getId() { return id; }

    /** @return Nombre del producto. */
    public String getNombre() { return nombre; }

    /** @return Precio del producto. */
    public double getPrecio() { return precio; }

    /** @return Cantidad disponible. */
    public int getCantidad() { return cantidad; }

    /**
     * Actualiza la cantidad disponible del producto.
     * @param cantidad Nueva cantidad en inventario.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Busca un producto por su ID dentro de una lista.
     * @param inventario Lista de productos disponibles.
     * @param idBuscado ID del producto a buscar.
     * @return Producto encontrado.
     * @throws ProductoNoEncontradoException Si no se encuentra el producto.
     */
    public static Producto buscarPorId(List<Producto> inventario, String idBuscado) throws ProductoNoEncontradoException {
        for (Producto p : inventario) {
            if (p.getId().equals(idBuscado)) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException("Producto con ID '" + idBuscado + "' no encontrado.");
    }
}