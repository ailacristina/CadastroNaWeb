package dao.jpa;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil implements Serializable {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("CadastroPU");
        }
        return emf;
    }

   
}
