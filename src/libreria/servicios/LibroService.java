package libreria.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Libro;
import libreria.entidades.Editorial;
import libreria.entidades.Autor;

public class LibroService {

    

    public void crearLibro() throws Exception {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        System.out.println("*******CREACION DE LIBROS ******");
        /**
         * Atributo titulo, verificacion de datos
         */
        try {
            String titulo;
            do {
                System.out.println("Ingrese el titulo del libro");
                titulo = leer.nextLine();
                if (titulo.isEmpty() || titulo.equals("")) {
                    System.out.println("El titulo del libro no puede estar vacio");
                }
            } while (titulo.isEmpty() || titulo.equals(""));
            
            List<Libro> traeLibro = buscaLibro(titulo);

            if (traeLibro.size() == 0) {

                int isbn;
                do {
                    System.out.println("Ingrese el ISBN");
                    isbn = leer.nextInt();
                } while (isbn == 0);

                System.out.println("Ingrese año de creacion");
                Integer anio = leer.nextInt();
                Integer ejemplares;
                do {
                    System.out.println("Ingrese la cantidad de ejemplares disponibles");
                    ejemplares = leer.nextInt();
                } while (ejemplares <= 0);
                Integer prestados;
                do {
                    System.out.println("Ingrese los ejemplares prestados");
                    prestados = leer.nextInt();
                    if (prestados > ejemplares) {
                        System.out.println("Los ejemplares prestados no pueden ser mayores"
                                + "a los ejemplares disponibles");
                    }
                } while (prestados > ejemplares);

                Autor autor = AutorService.crearAutor();

                Editorial edi = EditorialService.crearEditorial();

                Libro libro1 = new Libro(isbn, titulo, anio, ejemplares, prestados, autor, edi);
                EntityManager em = Persistence.createEntityManagerFactory("Libreria_PU").createEntityManager();
                em.getTransaction().begin();
                em.persist(libro1);
                em.getTransaction().commit();

            } else {
                System.out.println("El libro con ese titulo ya se encuentra creado");

                for (Libro libro : traeLibro) {

                    System.out.println(libro);
                }
            }
        } catch (Exception e) {
            //em.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Error en la creacion del libro");

        }
    }

    public  List<Libro> buscaLibro(String valor) throws Exception {

        List<Libro> libros = null;
        try {

            EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

            EntityManager em = ef.createEntityManager();

            libros = em.createQuery("SELECT l FROM Libro l  WHERE l.titulo= :valor")
                    .setParameter("valor", valor)
                    .getResultList();
        
    }
    catch (Exception e

    
        ) {
            
            e.printStackTrace();
        System.out.println("Ups, error  en la Busqueda de libros");

    }
    return libros ;
}

public static void listarLibros() throws Exception {
        System.out.println("**** LISTA DE LIBRO *****");

        List<Libro> libros = new ArrayList();
        try {
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

            EntityManager em = ef.createEntityManager();

            libros = em.createQuery("SELECT l FROM Libro l ")
                    .getResultList();

            for (Libro libro : libros) {
                System.out.println(libro);
            }

        } catch (Exception e) {
           
            e.printStackTrace();
            System.out.println("Error al listar los libros disponibles");

        
            
        } 
        

    }
     public  List<Libro> buscaISBN(Long isbn) throws Exception {

       List<Libro> libros = null;
        try {
            
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

            EntityManager em = ef.createEntityManager();

            libros = em.createQuery("SELECT l FROM Libro l  WHERE l.isbn = :isbn")
                    .setParameter("isbn", isbn).getResultList();
            return libros;
        } catch (Exception e) {
           
            e.printStackTrace();
            System.out.println("Ups, error  en la Busqueda de libro");

        
        }
        if(libros.isEmpty()){
            System.out.println("no se encontro libros");
        }
        return libros;
     }  
     
}
