package com.pskwiercz.demojpa.services;

import com.pskwiercz.demojpa.entities.Message;
import com.pskwiercz.demojpa.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class HibernateMessageService {

    public void saveMessageHibernate(String msgText) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Message message = new Message(msgText);
        session.save(message);

        session.getTransaction().commit();
        session.close();
    }

    public Message findMessageHibernate(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Message message = session.find(Message.class, id);

        session.getTransaction().commit();
        session.close();

        return message;
    }
}
