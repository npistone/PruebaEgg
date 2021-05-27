package libreria.servicios;

import java.awt.PageAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

public class AutorService {

    private static EntityManager em = Persistence.createEntityManagerFactory("Libreria_PU").createEntityManager();;

               
    public static Autor crearAutor()throws Exception{
        //Integer.parseInt(scanner.nextLine());
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        Autor ai = new Autor();
        List<Autor> autores = new ArrayList();
        try {
            String nombre;
            do {
                
                System.out.println("Nombre del autor :");
                nombre = leer.nextLine().toLowerCase();
            } while (nombre.isEmpty());

             autores = buscaAutor(nombre);
            if (autores.size()!=0) {

                System.out.println("Autor ya existente");
                for (Autor autor: autores) {
                    System.out.println(autor);
                }
               
                System.out.println("Seleccione  el id del autor");
                EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

                EntityManager em = ef.createEntityManager();
                int id = leer.nextInt();
                ai=em.find(Autor.class,id);
                

            } else {
                ai.setNombre(nombre);
                EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

                EntityManager em = ef.createEntityManager();

                em.getTransaction().begin();
                em.persist(ai);
                em.getTransaction().commit();
                em.close();
                System.out.println("Autor creado exitosamente");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
            
            em.getTransaction().rollback();
            System.out.println("Error de carga");
            
        }
        return ai;
    }

    public static List<Autor> buscaAutor(String nombre) throws Exception {
        
        List<Autor> Autores = null;
        try {
            
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

            EntityManager em = ef.createEntityManager();

            Autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre")
                    .setParameter("nombre", nombre).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ups, error  en la Busqueda del autor");

        }
        return Autores;
    }

}
/**a1 = (Autor) em.createQuery("Select a from Autor a where a.nombre:'nombre';")
                    .setParameter("nombre", nombre).
                    getSingleResult();*/
