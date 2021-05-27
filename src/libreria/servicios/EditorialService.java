package libreria.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;

public class EditorialService {

    /*    private String id;
    private String nombre;*/
    private static EntityManager em = Persistence.createEntityManagerFactory("Libreria_PU").createEntityManager();

    ;

    public static Editorial crearEditorial() throws Exception {
        //Integer.parseInt(scanner.nextLine());
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        Editorial ai = new Editorial();
        List<Editorial> editoriales = new ArrayList();
        try {
            String nombre;
            do {

                System.out.println("Nombre de la Editorial :");
                nombre = leer.nextLine().toUpperCase();
            } while (nombre.isEmpty());

            editoriales = buscaEditorial(nombre);
            if (editoriales.size() != 0) {

                System.out.println("Autor ya existente");
                for (Editorial editorial : editoriales) {
                    System.out.println(editoriales);
                }

                System.out.println("Seleccione  el id de la Editorial");
                EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

                EntityManager em = ef.createEntityManager();
                int id = leer.nextInt();
                ai = em.find(Editorial.class, id);

            } else {
                ai.setNombre(nombre);
                EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

                EntityManager em = ef.createEntityManager();

                em.getTransaction().begin();
                em.persist(ai);
                em.getTransaction().commit();
                em.close();
                System.out.println("Editorial creada exitosamente");
            }

        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Error de carga");

        }
        return ai;
    }

    public static List<Editorial> buscaEditorial(String nombre) throws Exception {

        List<Editorial> editoriales = null;
        try {
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

            EntityManager em = ef.createEntityManager();

            editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
                    .setParameter("nombre", nombre).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ups, error  en la Busqueda de la Editorial");

        }
        return editoriales;
    }

}
