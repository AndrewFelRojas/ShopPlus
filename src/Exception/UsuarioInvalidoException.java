package Exception;

/**
 * clase que crea mensaje para la excepcion personalizada UsuarioInvalidoException.
 * @author andres
 */
public class UsuarioInvalidoException extends Exception {
    public UsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}