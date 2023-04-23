package com.example.shppyad16.PostOffice;

import com.example.shppyad16.Departure.Departure;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostOfficeService {
    private final SessionFactory sessionFactory;

    @Autowired
    public PostOfficeService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PostOffice> getAllPostOffices() {
        Session session = sessionFactory.getCurrentSession();
        Query<PostOffice> query = session.createQuery("SELECT d FROM PostOffice d", PostOffice.class);
        return query.getResultList();
    }

    public PostOffice getPostOfficeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(PostOffice.class, id);
    }

    public PostOffice createPostOffice(PostOffice postOffice) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(postOffice);
        tx.commit();
        return postOffice;
    }

    public void deletePostOfficeById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        PostOffice postOffice = session.get(PostOffice.class, id);
        if (postOffice != null) {
            session.remove(postOffice);
        }
        tx.commit();
    }

    public List<Departure> getDeparturesByPostOffice(Long id) {
        Session session = sessionFactory.openSession();
        PostOffice postOffice = session.get(PostOffice.class, id);
        return postOffice.getDepartureList();
    }
}
