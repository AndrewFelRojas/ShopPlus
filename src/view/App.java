package view;

import Controller.*;
import Exception.*;
import Model.*;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

/**
 * Vista principal en consola del sistema ShopPlus.
 * Permite registrar usuarios, iniciar sesión y realizar acciones según el tipo de usuario.
 * Esta clase maneja la interfaz de usuario del sistema de compras ShopPlus,roporcionando funcionalidades diferenciadas para Clientes, Administradores y Proveedores.
 * @author andres
 */
public class App {
    
    /**
     * Método principal que inicia la aplicación ShopPlus.
     * 
     * <p>Carga datos desde archivos CSV, inicializa los controladores necesarios
     * y presenta el menú principal para que los usuarios puedan:</p>
     * <ul>
     *   <li>Iniciar sesión con credenciales existentes</li>
     *   <li>Registrarse como nuevo usuario (Cliente, Administrador o Proveedor)</li>
     *   <li>Salir del sistema</li>
     * </ul>
     * 
     * <p>Dependiendo del tipo de usuario autenticado, se mostrarán diferentes opciones:</p>
     * <ul>
     *   <li><b>Cliente:</b> Comprar productos y ver sus pedidos</li>
     *   <li><b>Administrador:</b> Ver inventario y gestionar usuarios</li>
     *   <li><b>Proveedor:</b> Actualizar stock y procesar envíos</li>
     * </ul>
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cargar usuarios y productos desde archivos
        List<Usuario> usuarios = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        try {
            usuarios = GestorCSV.cargarUsuarios("Usuarios.txt");
            productos = GestorCSV.cargarProductos("Productos.txt");
        } catch (IOException e) {
            System.out.println("Error al cargar archivos: " + e.getMessage());
        }
        
        ControladorUsuario controladorUsuario = new ControladorUsuario(usuarios);
        ControladorProducto controladorProducto = new ControladorProducto(productos);

        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Bienvenido a ShopPlus ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    // Autenticación de usuario
                    System.out.print("\nEmail: ");
                    String email = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String contraseña = scanner.nextLine();

                    try {
                        Usuario usuario = controladorUsuario.autenticar(email, contraseña);
                        System.out.println("\n¡Acceso exitoso como " + usuario.getClass().getSimpleName() + "!");
                        controladorUsuario.mostrarOpcionesUsuario(usuario);

                        // Menú específico para Cliente
                        if (usuario instanceof Cliente) {
                            boolean volverCliente = false;
                            while (!volverCliente) {
                                System.out.println("3. Volver al menú principal");
                                System.out.print("Seleccione una opción: ");
                                String opcionCliente = scanner.nextLine();

                                switch (opcionCliente) {
                                case "1":
                                    // Comprar producto
                                    System.out.print("Ingrese ID de producto para comprar: ");
                                    String idCompra = scanner.nextLine();
                                    try {
                                        Producto p = controladorProducto.buscarProducto(idCompra);
                                        System.out.println("Producto encontrado: " + p.getNombre() + " - $" + p.getPrecio());

                                        Pedido nuevoPedido = new Pedido(usuario.getEmail(), p.getId(), LocalDateTime.now());
                                        GestorCSV.guardarPedido(nuevoPedido, "Pedidos.txt");

                                        System.out.println("Pedido registrado. Esperando envío del proveedor.");
                                    } catch (ProductoNoEncontradoException e) {
                                        System.out.println(e.getMessage());
                                    } catch (IOException e) {
                                        System.out.println("Error al guardar el pedido: " + e.getMessage());
                                    }
                                    break;
                                    
                                    case "2":
                                        // Ver pedidos del cliente
                                    	try {
                                    	    List<Pedido> pedidosCliente = GestorCSV.cargarPedidos("Pedidos.txt");
                                    	    System.out.println("\nTus pedidos:");
                                    	    boolean tienePedidos = false;
                                    	    for (Pedido pedido : pedidosCliente) {
                                    	        if (pedido.getEmailCliente().equals(usuario.getEmail())) {
                                    	            System.out.println("- Producto ID: " + pedido.getIdProducto() + " | Fecha: " + pedido.getFecha());
                                    	            tienePedidos = true;
                                    	        }
                                    	    }
                                    	    if (!tienePedidos) {
                                    	        System.out.println("No tienes pedidos registrados.");
                                    	    }
                                    	} catch (IOException e) {
                                    	    System.out.println("Error al cargar pedidos: " + e.getMessage());
                                    	}
                                    case "3":
                                        volverCliente = true;
                                        break;
                                    default:
                                        System.out.println("Opción inválida.");
                                }
                            }

                        // Menú específico para Administrador
                        } else if (usuario instanceof Administrador) {
                            boolean volverAdmin = false;
                            while (!volverAdmin) {
                                System.out.println("3. Volver al menú principal");
                                System.out.print("Seleccione una opción: ");
                                String opcionAdmin = scanner.nextLine();

                                switch (opcionAdmin) {
                                    case "1":
                                        // Ver inventario
                                        System.out.println("\nInventario actual:");
                                        controladorProducto.mostrarInventario();
                                        break;
                                        
                                    case "2":
                                        // Gestión de usuarios
                                        boolean volverGestionUsuarios = false;
                                        while (!volverGestionUsuarios) {
                                            System.out.println("\nGestión de usuarios:");
                                            System.out.println("1. Ver todos los usuarios");
                                            System.out.println("2. Buscar usuario por email");
                                            System.out.println("3. Eliminar usuario por email");
                                            System.out.println("4. Volver");
                                            System.out.print("Seleccione una opción: ");
                                            String opcionGestion = scanner.nextLine();

                                            switch (opcionGestion) {
                                                case "1":
                                                    // Listar todos los usuarios
                                                    System.out.println("\nUsuarios registrados:");
                                                    for (Usuario u : usuarios) {
                                                        System.out.println("- " + u.getClass().getSimpleName() + ": " + u.getNombre() + " | " + u.getEmail());
                                                    }
                                                    break;

                                                case "2":
                                                    // Buscar usuario por email
                                                    System.out.print("Ingrese el email del usuario a buscar: ");
                                                    String emailBuscar = scanner.nextLine();
                                                    boolean encontrado = false;
                                                    for (Usuario u : usuarios) {
                                                        if (u.getEmail().equalsIgnoreCase(emailBuscar)) {
                                                            System.out.println("Usuario encontrado:");
                                                            System.out.println("- Tipo: " + u.getClass().getSimpleName());
                                                            System.out.println("- Nombre: " + u.getNombre());
                                                            System.out.println("- Email: " + u.getEmail());
                                                            encontrado = true;
                                                            break;
                                                        }
                                                    }
                                                    if (!encontrado) {
                                                        System.out.println("No se encontró ningún usuario con ese email.");
                                                    }
                                                    break;

                                                case "3":
                                                    // Eliminar usuario
                                                    System.out.print("Ingrese el email del usuario a eliminar: ");
                                                    String emailEliminar = scanner.nextLine();
                                                    Usuario usuarioAEliminar = null;
                                                    for (Usuario u : usuarios) {
                                                        if (u.getEmail().equalsIgnoreCase(emailEliminar)) {
                                                            usuarioAEliminar = u;
                                                            break;
                                                        }
                                                    }
                                                    if (usuarioAEliminar != null) {
                                                        usuarios.remove(usuarioAEliminar);
                                                        try {
                                                            GestorCSV.guardarUsuarios(usuarios, "Usuarios.txt");
                                                            System.out.println("Usuario eliminado correctamente.");
                                                        } catch (IOException e) {
                                                            System.out.println("Error al guardar cambios: " + e.getMessage());
                                                        }
                                                    } else {
                                                        System.out.println("Usuario no encontrado.");
                                                    }
                                                    break;

                                                case "4":
                                                    volverGestionUsuarios = true;
                                                    break;

                                                default:
                                                    System.out.println("Opción inválida.");
                                            }
                                        }
                                        break;
                                        
                                    case "3":
                                        volverAdmin = true;
                                        break;
                                        
                                    default:
                                        System.out.println("Opción inválida.");
                                }
                            }

                        // Menú específico para Proveedor
                        } else if (usuario instanceof Proveedor) {
                        	boolean volverProv = false;
                        	while (!volverProv) {
                        	    System.out.println("3. Volver al menú principal");
                        	    System.out.print("Seleccione una opción: ");
                        	    String opcionProv = scanner.nextLine();

                        	    switch (opcionProv) {
                        	        case "1":
                        	            // Actualizar cantidad de producto
                        	            System.out.print("Ingrese ID de producto: ");
                        	            String id = scanner.nextLine();
                        	            System.out.print("Nueva cantidad: ");
                        	            int cantidad = Integer.parseInt(scanner.nextLine());
                        	            try {
                        	                controladorProducto.actualizarCantidad(id, cantidad);
                        	                System.out.println("Cantidad actualizada correctamente.");
                        	            } catch (ProductoNoEncontradoException e) {
                        	                System.out.println(e.getMessage());
                        	            }
                        	            break;
                        	            
                        	        case "2":
                        	            // Procesar y enviar pedidos
                        	            try {
                        	                List<Pedido> pedidos = GestorCSV.cargarPedidos("Pedidos.txt");
                        	                if (pedidos.isEmpty()) {
                        	                    System.out.println("No hay pedidos pendientes.");
                        	                    break;
                        	                }

                        	                System.out.println("\nPedidos pendientes:");
                        	                for (int i = 0; i < pedidos.size(); i++) {
                        	                    Pedido pedido = pedidos.get(i);
                        	                    System.out.println((i + 1) + ". Cliente: " + pedido.getEmailCliente() +
                        	                                       " | Producto ID: " + pedido.getIdProducto() +
                        	                                       " | Fecha: " + pedido.getFecha());
                        	                }

                        	                System.out.print("Seleccione el número del pedido a enviar: ");
                        	                int seleccion = Integer.parseInt(scanner.nextLine()) - 1;

                        	                if (seleccion < 0 || seleccion >= pedidos.size()) {
                        	                    System.out.println("Selección inválida.");
                        	                    break;
                        	                }

                        	                Pedido pedidoAEnviar = pedidos.get(seleccion);
                        	                Producto producto = controladorProducto.buscarProducto(pedidoAEnviar.getIdProducto());

                        	                if (producto.getCantidad() > 0) {
                        	                    // Procesar envío
                        	                    producto.setCantidad(producto.getCantidad() - 1);
                        	                    GestorCSV.guardarProductos(productos, "Productos.txt");

                        	                    Envio envio = new Envio(usuario.getEmail(), producto.getId(), 1, LocalDateTime.now());
                        	                    GestorCSV.guardarEnvio(envio, "Envios.txt");

                        	                    pedidos.remove(seleccion);
                        	                    GestorCSV.guardarPedidos(pedidos, "Pedidos.txt");

                        	                    System.out.println("✅ Envío realizado. Stock actualizado.");
                        	                } else {
                        	                    System.out.println("❌ No hay stock suficiente para este producto.");
                        	                }

                        	            } catch (ProductoNoEncontradoException e) {
                        	                System.out.println("Producto no encontrado: " + e.getMessage());
                        	            } catch (IOException e) {
                        	                System.out.println("Error al guardar archivos: " + e.getMessage());
                        	            } catch (NumberFormatException e) {
                        	                System.out.println("Entrada inválida. Debe ingresar un número.");
                        	            } catch (Exception e) {
                        	                System.out.println("Error inesperado: " + e.getMessage());
                        	            }
                        	            break;
                        	            
                        	        case "3":
                        	            volverProv = true;
                        	            break;
                        	            
                        	        default:
                        	            System.out.println("Opción inválida.");
                        	    }
                        	}
                        }

                        // Guardar cambios en productos
                        GestorCSV.guardarProductos(productos, "Productos.txt");

                    } catch (UsuarioInvalidoException e) {
                        System.out.println("Error de autenticación: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error general: " + e.getMessage());
                    }
                    break;

                case "2":
                    // Registro de nuevo usuario
                    System.out.println("\n=== Registro de nuevo usuario ===");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailNuevo = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String contraseñaNueva = scanner.nextLine();
                    System.out.print("Tipo de usuario (Cliente / Administrador / Proveedor): ");
                    String tipo = scanner.nextLine().trim().toLowerCase();

                    Usuario nuevoUsuario = switch (tipo) {
                        case "cliente" -> new Cliente(nombre, emailNuevo, contraseñaNueva);
                        case "administrador" -> new Administrador(nombre, emailNuevo, contraseñaNueva);
                        case "proveedor" -> new Proveedor(nombre, emailNuevo, contraseñaNueva);
                        default -> null;
                    };

                    if (nuevoUsuario == null) {
                        System.out.println("Tipo de usuario inválido.");
                        break;
                    }

                    usuarios.add(nuevoUsuario);
                    try {
                        GestorCSV.guardarUsuarios(usuarios, "Usuarios.txt");
                        System.out.println("Usuario registrado exitosamente.");
                    } catch (IOException e) {
                        System.out.println("Error al guardar el usuario: " + e.getMessage());
                    }
                    break;

                case "3":
                    // Salir del sistema
                    salir = true;
                    System.out.println("Gracias por usar ShopPlus.");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}