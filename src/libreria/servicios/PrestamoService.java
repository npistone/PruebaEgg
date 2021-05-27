package libreria.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.servicios.LibroService;
import libreria.entidades.Cliente;

/**
 *
 * @author NPist
 */
public class PrestamoService {

    public void crearPrestamo() throws Exception {
        Scanner lec1 = new Scanner(System.in).useDelimiter("\n");

        String titulo;
        System.out.println("****** PRESTAMO ****");
        System.out.println("SELECCION DE LIBRO");
        int opcion;
        do {
            System.out.println("1- BUSQUEDA POR ISBN");
            System.out.println("2- BUSQUEDA POR TITULO");
            opcion = lec1.nextInt();
        } while (opcion > 2 || opcion < 1);
        List<Libro> libros;
            if(opcion==1){
                System.out.println("Ingrese el ISBN");
                        Long isbn = lec1.nextLong();
                        List<Libro> libris= new ArrayList();
                        LibroService librito = new LibroService();
                        libros = librito.buscaISBN(isbn);
            }else{
                
              System.out.println("Ingrese el Titulo");
                        String titul = lec1.nextLine();
                        List<Libro> libris= new ArrayList();
                        LibroService librito = new LibroService();
                        libros = librito.buscaLibro(titul);
                
           
            }
                if (libros.isEmpty()) {
                    System.out.println("No se ha encontrado ningun libro con ese ISBN");
                } else {

                    System.out.println("**** LIBROS DISPONIBLES ****");
                    for (Libro libro : libros) {
                        System.out.println(libro);
                    }
                    System.out.println("Seleccione el ID del libro");
                    int id = lec1.nextInt();

                    EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

                    EntityManager em = ef.createEntityManager();
                    Libro libro = new Libro();
                    libro = em.find(Libro.class, id);
                    
                    boolean disponible = disponibilidad(libro);
                   
                    if (disponible) {
                          Cliente cliente=null;
                        do{ 
                        try{
                        String respuesta = "a" ;
                        do{
                        System.out.println("Hay disponibilidad de libro");
                        System.out.println("Ingrese el documento del cliente");
                        long documento = lec1.nextLong();
                            
                       
                     
                                
                        cliente = ClienteService.buscaCliente(documento);
                        
                        if(cliente==null){
                            System.out.println("No se a encontrado un usuario con ese documento");
                            System.out.println("Ingrese nuevamente el documento");
                            System.out.println("O presione S para salir");
                            respuesta = lec1.nextLine();
                        }
                        }while(cliente==null);
                        
                        } catch (Exception e) {
                                   e.printStackTrace();
                                   System.out.println("Error en la carga del cliente");
                            }
                        }while(cliente==null);
                       
                        Prestamo prestamo = new Prestamo();
                        /**
                         * Seteo de libro para prestamo
                         */
                        prestamo.setLibro(libro);
                        
                        /**
                         * Seteo de cliente para prestamo
                         */
                        prestamo.setCliente(cliente);
                        
                        Date fechaInicio = new Date();
                        System.out.println("Fecha de inicio de prestamo: "+fechaInicio);
                        prestamo.setFecha(fechaInicio);
                        /**
                         * parametro Devolucion para prestamo
                         */
                        Date fechaDevolucion = diasDevolucion(fechaInicio);
                        prestamo.setDevolucion(fechaDevolucion);
                        
                        System.out.println("Fecha de devolucion : "+fechaDevolucion);
                        /**
                         * Resta y suma de ejemplares del  libro
                         */
                        libro.setEjemplares(libro.getEjemplares()-1);
                        libro.setPrestados(libro.getPrestados()+1);
                    } else {
                        
                        System.out.println("No hay disponibilidad para el libro buscado");
                    }

                    System.out.println("Libros");

                 
                
        
    


        
    } 
}
    
    public boolean disponibilidad(Libro libro){
        
        boolean disponibilidad ;
        if(libro.getEjemplares()> libro.getPrestados()){
            disponibilidad= true;
        }else{
            disponibilidad= false;
        }
            return disponibilidad;
    }
    
    public Date diasDevolucion(Date fechaIn){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaIn);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        
        return calendar.getTime();
        
        
    }
    
     public static List<Prestamo> buscaPrestamoId(Integer id) throws Exception {

        List<Prestamo> prestamos = null;
        try {

            EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

            EntityManager em = ef.createEntityManager();

            prestamos = em.createQuery("SELECT p FROM Prestamo p  WHERE p.id= : id")
                    .setParameter("id", id)
                    .getResultList();
        
    }
    catch (Exception e

    
        ) {
            
            e.printStackTrace();
        System.out.println("Ups, error  en la Busqueda de Prestamos");

    }
    return prestamos ;
}
    public static void eliminarPrestamo(Integer id){
    
        EntityManager em = Persistence.createEntityManagerFactory("Libreria_PU").createEntityManager();
        Prestamo prestamo = new Prestamo();
        try{
        em.getTransaction().begin();
        prestamo=em.find(Prestamo.class, id);
        em.remove(prestamo);
        em.getTransaction().commit();
            System.out.println("Prestamos Eliminado correctamente");
        }catch(Exception e ){
            e.printStackTrace();
            em.getTransaction().rollback();
            System.out.println("Error al eliminar el prestamo");
        }
        
        
                      
    }
    
    public static List<Prestamo> listarPrestamos(){
        System.out.println("Esto deberia modificar");
        List<Prestamo> prestamos = null;
        try {
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

            EntityManager em = ef.createEntityManager();

            prestamos = em.createQuery("SELECT p FROM Prestamo p").getResultList();
            if (prestamos.isEmpty()) {
                
                System.out.println("No hay libros disponibles");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la busqueda de prestamos");
            
        }
        
        return prestamos;
    }
    
    
}
