package dao.jpa;

import dao.TerapeutaDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import Modelo.Terapeuta;

public class TerapeutaJPA implements TerapeutaDAO, Serializable {

    @Override
    public void salvar(Terapeuta terapeuta) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(terapeuta);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Terapeuta porLogineSenha(String email, String senha) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Terapeuta> tq = em.createNamedQuery(Terapeuta.POR_LOGIN_E_SENHA,
                    Terapeuta.class);
            tq.setParameter("login", email);
            tq.setParameter("senha", senha);
            List<Terapeuta> lu = tq.getResultList();
            if (lu == null || lu.isEmpty()) {
                return null;
            }
            return lu.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeLogin(String login) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        try {
            TypedQuery<Number> tq = em.createNamedQuery(Terapeuta.EXISTE_LOGIN, Number.class);
            tq.setParameter("login", login);
            int count = tq.getSingleResult().intValue();
            return (count != 0);
        } finally {
            em.close();
        }
    }

}
