package Controller;

import java.util.List;

import Exception.UsuarioInvalidoException;
import Model.Usuario;

/**
 * Controlador encargado de gestionar la lógica relacionada con los usuarios del sistema ShopPlus.
 * Permite autenticar usuarios y mostrar sus opciones disponibles según el tipo.
 * @author andres
 */
public class ControladorUsuario {
    private List<Usuario> usuarios;

    /**
     * Crea un controlador de usuarios con una lista inicial.
     * @param usuarios Lista de usuarios registrados.
     */
    public ControladorUsuario(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Autentica un usuario según su email y contraseña.
     * @param email Email ingresado.
     * @param contraseña Contraseña ingresada.
     * @return Usuario autenticado.
     * @throws UsuarioInvalidoException Si no se encuentra un usuario válido.
     */
    public Usuario autenticar(String email, String contraseña) throws UsuarioInvalidoException {
        for (Usuario u : usuarios) {
            try {
                u.validarAcceso(email, contraseña);
                return u;
            } catch (UsuarioInvalidoException e) {
                // Ignorar y seguir buscando
            }
        }
        throw new UsuarioInvalidoException("No se encontró un usuario válido con esas credenciales.");
    }

    /**
     * Muestra las opciones disponibles para el usuario autenticado.
     * @param usuario Usuario autenticado.
     */
    public void mostrarOpcionesUsuario(Usuario usuario) {
        usuario.mostrarOpciones();
    }
}