package Model;

/**
 * Representa un usuario de tipo Cliente en el sistema ShopPlus.
 * El cliente puede realizar compras y consultar sus pedidos.
 * @author andres
 */
public class Cliente extends Usuario {

    /**
     * Crea un nuevo cliente con los datos especificados.
     * @param nombre Nombre del cliente.
     * @param email Correo electrónico del cliente.
     * @param contraseña Contraseña de acceso.
     */
    public Cliente(String nombre, String email, String contraseña) {
        super(nombre, email, contraseña);
    }

    /**
     * Muestra las opciones disponibles para el cliente.
     * Implementación específica del método abstracto de Usuario.
     */
    @Override
    public void mostrarOpciones() {
        System.out.println("1. Realizar compras\n2. Consultar pedidos");
    }
}