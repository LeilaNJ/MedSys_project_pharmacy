/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Pharmacist;

/**
 *
 * @author PC
 */
public class PharmacistJpaController implements Serializable {

    public PharmacistJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pharmacist pharmacist) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pharmacist);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pharmacist pharmacist) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pharmacist = em.merge(pharmacist);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pharmacist.getIdpharmacist();
                if (findPharmacist(id) == null) {
                    throw new NonexistentEntityException("The pharmacist with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pharmacist pharmacist;
            try {
                pharmacist = em.getReference(Pharmacist.class, id);
                pharmacist.getIdpharmacist();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pharmacist with id " + id + " no longer exists.", enfe);
            }
            em.remove(pharmacist);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pharmacist> findPharmacistEntities() {
        return findPharmacistEntities(true, -1, -1);
    }

    public List<Pharmacist> findPharmacistEntities(int maxResults, int firstResult) {
        return findPharmacistEntities(false, maxResults, firstResult);
    }

    private List<Pharmacist> findPharmacistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pharmacist.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pharmacist findPharmacist(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pharmacist.class, id);
        } finally {
            em.close();
        }
    }

    public int getPharmacistCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pharmacist> rt = cq.from(Pharmacist.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
