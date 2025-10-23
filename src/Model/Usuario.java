package Model;

import Exception.UsuarioInvalidoException;

/**
 * Clase abstracta que representa un usuario genérico del sistema ShopPlus.
 * Contiene atributos comunes como nombre, email y contraseña.
 * @author andres
 * @version 1.0
 */
public abstract class Usuario {
    protected String nombre;
    protected String email;
    protected String contraseña;

    /**
     * Crea un nuevo usuario con los datos básicos.
     * @param nombre Nombre del usuario.
     * @param email Correo electrónico del usuario.
     * @param contraseña Contraseña de acceso.
     */
    public Usuario(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }

    /** @return Nombre del usuario. */
    public String getNombre() { return nombre; }

    /** @return Email del usuario. */
    public String getEmail() { return email; }

    /** @return Contraseña del usuario. */
    public String getContraseña() { return contraseña; }

    /**
     * Valida las credenciales ingresadas contra las del usuario.
     * @param emailIngresado Email ingresado.
     * @param contraseñaIngresada Contraseña ingresada.
     * @throws UsuarioInvalidoException Si las credenciales no coinciden.
     */
    public void validarAcceso(String emailIngresado, String contraseñaIngresada) throws UsuarioInvalidoException {
        if (!this.email.equals(emailIngresado) || !this.contraseña.equals(contraseñaIngresada)) {
            throw new UsuarioInvalidoException("Credenciales inválidas para el usuario: " + nombre);
        }
    }

    /**
     * Muestra las opciones disponibles para el tipo de usuario.
     * Este método debe ser implementado por las subclases.
     */
    public abstract void mostrarOpciones();
}