package Controller;

import java.util.List;

import Exception.ProductoNoEncontradoException;
import Model.Producto;

/**
 * Controlador encargado de gestionar la lógica relacionada con los productos del sistema ShopPlus.
 * Permite buscar productos, actualizar inventario y mostrar el listado.
 * @author andres
 */
public class ControladorProducto {
    private List<Producto> inventario;

    /**
     * Crea un controlador de productos con una lista inicial.
     * @param inventario Lista de productos disponibles.
     */
    public ControladorProducto(List<Producto> inventario) {
        this.inventario = inventario;
    }

    /**
     * Busca un producto por su ID.
     * @param id ID del producto a buscar.
     * @return Producto encontrado.
     * @throws ProductoNoEncontradoException Si no se encuentra el producto.
     */
    public Producto buscarProducto(String id) throws ProductoNoEncontradoException {
        return Producto.buscarPorId(inventario, id);
    }

    /**
     * Actualiza la cantidad disponible de un producto.
     * @param id ID del producto.
     * @param nuevaCantidad Nueva cantidad en inventario.
     * @throws ProductoNoEncontradoException Si el producto no existe.
     */
    public void actualizarCantidad(String id, int nuevaCantidad) throws ProductoNoEncontradoException {
        Producto p = buscarProducto(id);
        p.setCantidad(nuevaCantidad);
    }

    /**
     * Muestra el inventario completo en consola.
     */
    public void mostrarInventario() {
        for (Producto p : inventario) {
            System.out.println(p.getId() + " - " + p.getNombre() + " - $" + p.getPrecio() + " - Stock: " + p.getCantidad());
        }
    }
}