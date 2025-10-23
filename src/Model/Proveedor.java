package Model;

/**
 * Representa un usuario de tipo Proveedor en el sistema ShopPlus.
 * El proveedor puede registrar inventario y enviar productos.
 * @author andres
 */
public class Proveedor extends Usuario {

    /**
     * Crea un nuevo proveedor con los datos especificados.
     * @param nombre Nombre del proveedor.
     * @param email Correo electrónico del proveedor.
     * @param contraseña Contraseña de acceso.
     */
    public Proveedor(String nombre, String email, String contraseña) {
        super(nombre, email, contraseña);
    }

    /**
     * Muestra las opciones disponibles para el proveedor.
     * Implementación específica del método abstracto de Usuario.
     */
    @Override
    public void mostrarOpciones() {
        System.out.println("1. Registrar inventario\n2. Enviar productos");
    }
}