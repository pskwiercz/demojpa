package com.pskwiercz.demojpa.services;

import com.pskwiercz.demojpa.entities.Message;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Service
public class MessageService {

    public void saveMessage(String messageText) {
        EntityManager em = getEntityManager();
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

    public Message findMessage(Long id) {
        Message msg = null;

        EntityManager em = getEntityManager();
        EntityTransaction txn = getEntityManager().getTransaction();
        try {
            txn.begin();

            msg = em.find(Message.class, id);

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
        return msg;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa");
        return emf.createEntityManager();
    }
}
