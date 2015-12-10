package ua.alex.source.webtester.forms;


import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import ua.alex.source.webtester.entities.Account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestForm implements IForm {
    private static final long serialVersionUID = -3633827335080843887L;

    private Long idTest;

    @NotEmpty
    @Size(min = 6, max = 60)
    private String name;

    @NotEmpty
    @Size(min = 6, max = 60)
    private String description;

    @NotNull
    @Range(min = 30)
    private Integer timePerQuestion;

    @NotNull
    private Account account;

    public Long getIdTest() {
        return idTest;
    }

    public void setIdTest(Long idTest) {
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

    public Integer getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(Integer timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
