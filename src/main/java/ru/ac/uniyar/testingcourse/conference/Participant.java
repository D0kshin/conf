package ru.ac.uniyar.testingcourse.conference;


import jakarta.persistence.*;

import java.util.Set;

/**
 * Scientist participating in a conference
 */
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fee_id", referencedColumnName = "id")
    private Fee fee;
    private Boolean blackListed = false;

    public Participant() {}
    public Participant(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Integer getId() {
        return id;
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
    public void setFee(Fee fee) {this.fee = fee;}
    public Fee getFee(){return this.fee;}
    public void setBlackListed(Boolean bool) {
        this.blackListed = bool;
    }
    public Boolean getBlackListed() {
        return this.blackListed;
    }

    /**
     * @return full name: [name] [surname]
     */
    public String getFullName() {
        return String.format("%s %s", getName(), getSurname());
    }
}

