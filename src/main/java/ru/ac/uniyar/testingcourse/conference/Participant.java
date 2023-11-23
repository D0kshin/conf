package ru.ac.uniyar.testingcourse.conference;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Scientist participating in a conference
 */
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private final String name;
    private final String surname;
    private final String email;

    public Participant(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Integer getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    /**
     * @return full name: [name] [surname]
     */
    public String getFullName() {
        return String.format("%s %s", getName(), getSurname());
    }
}
