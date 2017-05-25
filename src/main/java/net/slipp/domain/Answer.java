package net.slipp.domain;

import javax.persistence.*;

/**
 * Created by woowahan on 2017. 5. 25..
 */

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private long id;

    private String contents;

    @ManyToOne
    private Question question;

    public Answer() {
    }

}
