package ru.ac.uniyar.testingcourse.conference;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
        Participant pt1 = new Participant("name1", "surname1", "email1");
        session.persist(pt1);
        Participant pt2 = new Participant("name2", "surname2", "email2");
        session.persist(pt2);
        session.getTransaction().commit();
    }




    public static void main(String[] args) {
        Main main = new Main();
        main.fillDB();



    }
}
