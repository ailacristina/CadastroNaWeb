package dao.jpa;

import dao.ConsultaDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import Modelo.Consulta;

public class ConsultaJPA implements ConsultaDAO, Serializable {

    @Override
    public void salvar(Consulta consulta) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        em.getTransaction().begin();
        em.persist(consulta);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Consulta> todos(Long terapeutaId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        TypedQuery<Consulta> tq = em.createNamedQuery(Consulta.TODOS,
                Consulta.class);
        tq.setParameter("terapeutaId", terapeutaId);
        List<Consulta> listaConsultas = tq.getResultList();
        em.close();
        return listaConsultas;
    }

    @Override
    public void concluir(Long consultaId) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        Consulta consulta = em.find(Consulta.class, consultaId);
        if (consulta != null) {
            em.getTransaction().begin();
            em.remove(consulta);
            em.getTransaction().commit();
        }        
        em.close();
    }

}
