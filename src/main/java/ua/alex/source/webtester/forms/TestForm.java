package ua.alex.source.webtester.forms;


import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import ua.alex.source.webtester.entities.Account;


public class TestForm implements IForm {
    private static final long serialVersionUID = -3633827335080843887L;

    @NotNull(errorCode = "form.test.name.empty")
    @NotBlank(errorCode = "form.test.name.empty")
    @Length(min = 6, max = 60, errorCode = "form.test.name.length")
    private String name;

    @NotNull(errorCode = "form.test.name.empty")
    @NotBlank(errorCode = "from.test.description")
    @Length(min = 6, max = 60, errorCode = "form.test.description.length")
    private String description;

    private Integer timePerQuestion;

    @NotNull
    private Account account;

    private Long idTest;

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
