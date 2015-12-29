package ua.alex.source.webtester.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_result", schema = "public", catalog = "webtester")
public class TestResult extends ManagerEntity {

    @Id
    @SequenceGenerator(name = "TEST_RESULT_IDTEST_RESULT_GENERATOR", sequenceName = "test_result_seq", allocationSize = 1)
    @GeneratedValue(generator = "TEST_RESULT_IDTEST_RESULT_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_test_result", nullable = false, unique = true)
    private long idTestResult;

    @Column(name = "correct_count", nullable = false)
    private int correctCount;

    @Column(name = "all_count", nullable = false)
    private int allCount;

    @Column(name = "test_name", nullable = false, length = 255)
    private String testName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;


    public long getIdTestResult() {
        return idTestResult;
    }

    public void setIdTestResult(long idTestResult) {
        this.idTestResult = idTestResult;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdTestResult();
    }
}
