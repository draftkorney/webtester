package ua.alex.source.webtester.components.impl;

import org.springframework.stereotype.Component;
import ua.alex.source.webtester.components.EntityBuilder;
import ua.alex.source.webtester.entities.*;

import java.sql.Timestamp;
import java.util.UUID;

@Component("entityBuilder")
public class EntityBuilderImpl implements EntityBuilder {

    @Override
    public Account buildAccount() {
        Account a = new Account();
        a.setCreated(new Timestamp(System.currentTimeMillis()));
        a.setActive(Boolean.FALSE);
        return a;
    }

    @Override
    public AccountRole buildAccountRole(Account account, Role role) {
        return new AccountRole(account, role);
    }

    @Override
    public AccountRegistration buildAccountRegistration(Account account) {
        return new AccountRegistration(account, UUID.randomUUID().toString());
    }

    @Override
    public Test buildTest() {
        Test test = new Test();
        test.setCreated(new Timestamp(System.currentTimeMillis()));
        return test;
    }

    @Override
    public Question buildQuestion() {
        Question question = new Question();
        question.setCreated(new Timestamp(System.currentTimeMillis()));
        return question;
    }

    @Override
    public Answer buildAnswer() {
        Answer answer = new Answer();
        answer.setCreated(new Timestamp(System.currentTimeMillis()));
        return answer;
    }
}
