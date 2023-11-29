package ru.ac.uniyar.testingcourse.conference;

import java.util.HashMap;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Conference implements IConference {
    private SessionFactory sessionFactory;

    private Session openSession() {
        if (sessionFactory == null) {
            final Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Conference.class);
            configuration.addAnnotatedClass(Fee.class);
            configuration.addAnnotatedClass(Participant.class);
            sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
        }
        return sessionFactory.openSession();
    }
    public void register(Participant participant, Integer feeAmount) {
        Session session = openSession();
        session.getTransaction().begin();
        Fee fee = new Fee(feeAmount);
        participant.setFee(fee);
        session.persist(fee);
        session.persist(participant);
        session.getTransaction().commit();
    }
    @Override
    public void markFeePaid(Participant participant) {
        Session session = openSession();
        session.beginTransaction();
        Fee fee = session.get(Fee.class, participant.getFee().getId());
        fee.pay();
        session.getTransaction().commit();
    }
    @Override
    public Integer budget() {
        return null;
    }
    @Override
    public void addToBlacklist(String email) {
        //обработка на несущ имейл
        Session session = openSession();
        session.beginTransaction();
        Query<Participant> emailQuery = session.createQuery("FROM Participant WHERE email = :email",
                Participant.class);
        Participant result = emailQuery.setParameter("email", email).list().get(0);
        result.setBlackListed(true);
        session.getTransaction().commit();
    }

    @Override
    public void removeFromBlacklist(String email) {
        //обработка на несущ имейл
        Session session = openSession();
        session.beginTransaction();
        Query<Participant> emailQuery = session.createQuery("FROM Participant WHERE email = :email",
                Participant.class);
        Participant result = emailQuery.setParameter("email", email).list().get(0);
        result.setBlackListed(false);
        session.getTransaction().commit();
    }


}
