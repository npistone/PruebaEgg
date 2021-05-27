package libreria.interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.servicios.AutorService;
import libreria.servicios.ClienteService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;
import libreria.servicios.PrestamoService;

public class Libreria {

    public static void main(String[] args) throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        Integer opcion = 0;
        try {

            System.out.println(" ******* LIBRERIA EGG ******* ");
            opcion = 0;
            do {
                do {
                    System.out.println("SELECCIONE UNA OPCION");
                    System.out.println("1- CREAR LIBRO");
                    System.out.println("2- CREAR CLIENTE");
                    System.out.println("3- PRESTAMOS DE LIBRO");
                    System.out.println("4- DEVOLUCION DE LIBROS");
                    System.out.println("5- BUSQUEDA DE LIBROS");
                    System.out.println("6- SALIR");
                    opcion = leer.nextInt();

                } while (opcion > 6 || opcion < 1);
                switch (opcion) {
                    case 1:
                        LibroService l1 = new LibroService();
                        l1.crearLibro();

                        break;
                    case 2:
                        ClienteService cServive = new ClienteService();
                        cServive.crearCliente();
                        break;
                    case 3:
                        PrestamoService prestamo = new PrestamoService();
                        prestamo.crearPrestamo();

                        break;
                    case 4:
                        Scanner leP = new Scanner(System.in).useDelimiter("\n");
                        Scanner le2 = new Scanner(System.in).useDelimiter("\n");

                        System.out.println("***** DEVOLUCION DE LIBROS *****");
                        System.out.println("Ingrese el documento del cliente");
                        Integer doc = leP.nextInt();
                        List<Prestamo> prestamosActivos;
                        prestamosActivos = PrestamoService.listarPrestamos();
                        if (prestamosActivos.isEmpty()) {
                            System.out.println("No hay prestamos para eliminar");
                        } else {
                            System.out.println("Prestamos activosgis");
                            for (Prestamo prestamosActivo : prestamosActivos) {
                                System.out.println(prestamosActivo);
                            }
                            System.out.println("Seleccione el Id del prestamos a eliminar");

                            Integer id = le2.nextInt();

                            PrestamoService.eliminarPrestamo(id);
                        }

                        break;

                    case 5:
                        int busqueda;
                        do {
                            System.out.println("1- BUSQUEDA POR ISBN");
                            System.out.println("2- BUSQUEDA POR TITULO");
                            busqueda = leer.nextInt();
                        } while (busqueda > 2 || busqueda < 1);

                        if (busqueda == 1) {
                            Scanner l2 = new Scanner(System.in);
                            System.out.println("Ingrese el ISBN");
                            Long isbn = l2.nextLong();
                            List<Libro> libros = new ArrayList();
                            LibroService librito = new LibroService();
                            libros = librito.buscaISBN(isbn);

                            System.out.println("Libros disponibles");
                            for (Libro libro : libros) {
                                System.out.println(libro);
                            }
                        } else {
                            Scanner l3 = new Scanner(System.in);
                            System.out.println("Ingrese el Titulo");
                            String titulo = l3.nextLine();
                            List<Libro> libros = new ArrayList();
                            LibroService librito = new LibroService();
                            libros = librito.buscaLibro(titulo);
                            System.out.println("Libros disponibles");
                            for (Libro libro : libros) {
                                System.out.println(libro);
                            }

                        }
                        break;
                    case 6:
                        System.out.println("PROGRAMA TERMINADO");
                        break;
                    default:
                        System.out.println("S");
                }
            } while (opcion != 6);
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Error de sistema1");

        }

    }

}
