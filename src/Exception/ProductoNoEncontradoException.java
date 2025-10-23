package Exception;

/**
 * claseque crea el mensaje para la excepcion personalizada ProductoNoEncontradoException.
 * @author andres
 */
public class ProductoNoEncontradoException extends Exception {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
