package Model;

/**
 * Representa un usuario de tipo Administrador en el sistema ShopPlus.
 * El administrador puede gestionar productos y usuarios.
 * @author andres
 */
public class Administrador extends Usuario {

    /**
     * Crea un nuevo administrador con los datos especificados.
     * @param nombre Nombre del administrador.
     * @param email Correo electrónico del administrador.
     * @param contraseña Contraseña de acceso.
     */
    public Administrador(String nombre, String email, String contraseña) {
        super(nombre, email, contraseña);
    }

    /**
     * Muestra las opciones disponibles para el administrador.
     * Implementación específica del método abstracto de Usuario.
     */
    @Override
    public void mostrarOpciones() {
        System.out.println("1. Gestionar productos\n2. Gestionar usuarios");
    }
}