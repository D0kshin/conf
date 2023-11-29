package ru.ac.uniyar.testingcourse.conference;

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

public class Main {
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

    private void fillDB() {
        Session session = openSession();
        session.getTransaction().begin();

        session.getTransaction().commit();
    }

    private void gettingParticipants(){
        Session session = openSession();
        String participantName = "name1";
        Query<Participant> q1 = session.createQuery("FROM Participant",
                Participant.class);
        List<Participant> result = q1.list();

        result.forEach((participant -> {
            System.out.println(participant.getName());
        }));

        session.close();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.fillDB();

        Conference conference = new Conference();
        Participant p1 = new Participant("TestName", "TestSurname", "test.email");
        Participant p2 = new Participant("TestName2", "TestSurname2", "test.email2");
        Participant p3 = new Participant("TestName3", "TestSurname3", "test.email3");
        Participant p4 = new Participant("TestName4", "TestSurname4", "test.email4");
        conference.register(p1, 230);
        conference.register(p2, 150);
        conference.register(p3, 140);

        conference.markFeePaid(p1);
        conference.markFeePaid(p3);
        conference.addToBlacklist(p2.getEmail());
        conference.addToBlacklist(p1.getEmail());
    }
}
