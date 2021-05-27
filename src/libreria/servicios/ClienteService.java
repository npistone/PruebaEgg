package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;

/**
 *
 * @author NPist
 */
public class ClienteService {
//    @Id    
//    private Long documento;
//    private String nombre;
//    private String apellido;
//    private String domicilo;
//    private String telefono;

    public void crearCliente() {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        Long documento;
        String nombre;
        String apellido;
        String domicilo;
        String telefono;
        
        System.out.println("****** CREADOR DE CLIENTES ******");
        do{
        System.out.println("Ingrese el documento del cliente");
        documento = leer.nextLong();
        }while(documento ==0);
        Cliente clientito = buscaCliente(documento);
        
        if(clientito ==null){
            do{
            System.out.println("Ingerese el  nombre");
            nombre=leer.nextLine();
        }while(nombre.isEmpty());
        do{
            System.out.println("Ingerese el apellido");
            apellido=leer.nextLine();
        }while(apellido.isEmpty());
        
        do{
            System.out.println("Ingerese el domicilio");
            domicilo=leer.nextLine();
        }while(domicilo.isEmpty());
        
        do{
            System.out.println("Ingerese el Telefono");
            telefono=leer.nextLine();
        }while(telefono.isEmpty());
        
        Cliente cliente = new Cliente(documento,nombre, apellido, domicilo,telefono);
        
        EntityManager em = Persistence.createEntityManagerFactory("Libreria_PU").createEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        System.out.println("Cliente creado exitosamente");
        }else{
            System.out.println("El usuario ya se encuentra en el sistema");
            System.out.println(clientito);
            
            
        }
        
        
        

    }
    
    public static Cliente buscaCliente(Long id){
        
        Cliente cliente = new Cliente() ;
        try {
           EntityManagerFactory ef = Persistence.createEntityManagerFactory("Libreria_PU");

            EntityManager em = ef.createEntityManager();

            cliente =em.find(Cliente.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error, en la busqueda de Cliente");
        }
        
       return cliente;
       
    }
}
