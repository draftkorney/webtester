package ua.alex.source.webtester.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Test extends ManagerEntity {

    @Id
    @SequenceGenerator(name = "TEST_IDTEST_GENERATOR", sequenceName = "test_seq",allocationSize = 1)
    @GeneratedValue(generator = "TEST_IDTEST_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_test", nullable = false, unique = true)
    private long idTest;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "time_per_question", columnDefinition = "INTEGER NOT NULL DEFAULT 30")
    private int timePerQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account",nullable = false)
    private Account account;

    public long getIdTest() {
        return idTest;
    }

    public void setIdTest(long idTest) {
        this.idTest = idTest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(int timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdTest();
    }
}
