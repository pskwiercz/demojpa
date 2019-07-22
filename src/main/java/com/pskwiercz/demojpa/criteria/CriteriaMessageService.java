package com.pskwiercz.demojpa.criteria;

import com.pskwiercz.demojpa.entities.Message;
import com.pskwiercz.demojpa.util.HibernateUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class CriteriaMessageService {

    public void saveMessages(String messageText) {
        EntityManager em = HibernateUtil.getSessionFactory().openSession();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();

            Message msg = new Message(messageText);
            em.persist(msg);

            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Message> findMessages() {
        EntityManager em = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        Root<Message> root = cq.from(Message.class);
        TypedQuery<Message> query = em.createQuery(cq.select(root));
        List<Message> result = query.getResultList();
        return result;
    }

    public Message findMessage(Long id) {
        EntityManager em = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        Root<Message> root = cq.from(Message.class);
        Predicate predicate = cb.equal(root.get("id"), id);
        cq.where(predicate);
//        cq.where(cb.equal(root.get("id"), id));
        TypedQuery<Message> query = em.createQuery(cq.select(root));
//        Message result = query.getSingleResult();
        Message result = query.getResultList().stream().findFirst().orElse(null);
        return result;
    }
}
