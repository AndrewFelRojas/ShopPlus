package Model;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

/**
 * Clase utilitaria para la gestión de archivos CSV relacionados con usuarios, productos, pedidos y envíos.
 * Proporciona métodos para cargar y guardar datos en archivos de texto plano.
 * @author andres
 */
public class GestorCSV {

    // === USUARIOS ===

    /**
     * Carga una lista de usuarios desde un archivo CSV.
     *
     * @param rutaArchivo Ruta del archivo que contiene los usuarios
     * @return Lista de objetos Usuario cargados
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public static List<Usuario> cargarUsuarios(String rutaArchivo) throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 4) continue;
                String tipo = partes[0].trim().toLowerCase();
                String nombre = partes[1];
                String email = partes[2];
                String contraseña = partes[3];

                Usuario u = switch (tipo) {
                    case "cliente" -> new Cliente(nombre, email, contraseña);
                    case "administrador" -> new Administrador(nombre, email, contraseña);
                    case "proveedor" -> new Proveedor(nombre, email, contraseña);
                    default -> null;
                };
                if (u != null) usuarios.add(u);
            }
        }
        return usuarios;
    }

    /**
     * Guarda una lista de usuarios en un archivo CSV.
     *
     * @param usuarios Lista de usuarios a guardar
     * @param rutaArchivo Ruta del archivo destino
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    public static void guardarUsuarios(List<Usuario> usuarios, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Usuario u : usuarios) {
                String tipo = u.getClass().getSimpleName();
                writer.write(tipo + "," + u.getNombre() + "," + u.getEmail() + "," + u.getContraseña());
                writer.newLine();
            }
        }
    }

    // === PRODUCTOS ===

    /**
     * Carga una lista de productos desde un archivo CSV.
     *
     * @param rutaArchivo Ruta del archivo que contiene los productos
     * @return Lista de objetos Producto cargados
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public static List<Producto> cargarProductos(String rutaArchivo) throws IOException {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 4) continue;
                String id = partes[0];
                String nombre = partes[1];
                double precio = Double.parseDouble(partes[2]);
                int cantidad = Integer.parseInt(partes[3]);
                productos.add(new Producto(id, nombre, precio, cantidad));
            }
        }
        return productos;
    }

    /**
     * Guarda una lista de productos en un archivo CSV.
     *
     * @param productos Lista de productos a guardar
     * @param rutaArchivo Ruta del archivo destino
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    public static void guardarProductos(List<Producto> productos, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Producto p : productos) {
                writer.write(p.getId() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getCantidad());
                writer.newLine();
            }
        }
    }

    // === PEDIDOS ===

    /**
     * Guarda un pedido individual en un archivo CSV.
     *
     * @param pedido Pedido a guardar
     * @param rutaArchivo Ruta del archivo destino
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    public static void guardarPedido(Pedido pedido, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(pedido.getEmailCliente() + "," + pedido.getIdProducto() + "," + pedido.getFecha());
            writer.newLine();
        }
    }

    /**
     * Carga una lista de pedidos desde un archivo CSV.
     *
     * @param rutaArchivo Ruta del archivo que contiene los pedidos
     * @return Lista de objetos Pedido cargados
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public static List<Pedido> cargarPedidos(String rutaArchivo) throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 3) continue;
                String email = partes[0];
                String idProducto = partes[1];
                LocalDateTime fecha = LocalDateTime.parse(partes[2]);
                pedidos.add(new Pedido(email, idProducto, fecha));
            }
        }
        return pedidos;
    }

    /**
     * Guarda una lista completa de pedidos en un archivo CSV, sobrescribiendo el contenido anterior.
     *
     * @param pedidos Lista de pedidos a guardar
     * @param rutaArchivo Ruta del archivo destino
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    public static void guardarPedidos(List<Pedido> pedidos, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Pedido p : pedidos) {
                writer.write(p.getEmailCliente() + "," + p.getIdProducto() + "," + p.getFecha());
                writer.newLine();
            }
        }
    }

    // === ENVIOS ===

    /**
     * Guarda un envío individual en un archivo CSV.
     *
     * @param envio Envío a guardar
     * @param rutaArchivo Ruta del archivo destino
     * @throws IOException Si ocurre un error al escribir el archivo
     */
    public static void guardarEnvio(Envio envio, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(envio.getEmailProveedor() + "," + envio.getIdProducto() + "," + envio.getCantidad() + "," + envio.getFecha());
            writer.newLine();
        }
    }

    /**
     * Carga una lista de envíos desde un archivo CSV.
     *
     * @param rutaArchivo Ruta del archivo que contiene los envíos
     * @return Lista de objetos Envio cargados
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public static List<Envio> cargarEnvios(String rutaArchivo) throws IOException {
        List<Envio> envios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 4) continue;
                String email = partes[0];
                String idProducto = partes[1];
                int cantidad = Integer.parseInt(partes[2]);
                LocalDateTime fecha = LocalDateTime.parse(partes[3]);
                envios.add(new Envio(email, idProducto, cantidad, fecha));
            }
        }
        return envios;
    }
}