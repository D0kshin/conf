package ru.ac.uniyar.testingcourse.conference;

import jakarta.persistence.*;


/**
 * Registration fee that must be paid by the participant.
 * Not paid when created, should be marked as paid when paid ({@link #pay()})
 */
@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer amount;
    private Boolean paid = false;

    @OneToOne(mappedBy = "fee")
    private Participant participant;

    public Fee() {}
    public Fee(Integer amount) {
        this.amount = amount;
    }

    /**
     * Mark fee as paid
     */
    public void pay() {
        paid = true;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public Boolean isPaid() {
        return paid;
    }

}

