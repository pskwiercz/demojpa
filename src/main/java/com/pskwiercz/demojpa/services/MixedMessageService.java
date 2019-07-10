package com.pskwiercz.demojpa.services;

import com.pskwiercz.demojpa.entities.Message;
import com.pskwiercz.demojpa.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MixedMessageService {

    public void saveMixedMessage(String messageText) {
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

    public Message findMixedMessage(Long id) {
        Message msg = null;

        EntityManager em = getEntityManager();
        EntityTransaction txn = em.getTransaction();
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
        EntityManager em = HibernateUtil.getSessionFactory().openSession();
        return em;
    }
}
