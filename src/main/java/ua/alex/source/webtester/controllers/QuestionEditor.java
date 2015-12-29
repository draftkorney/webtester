package ua.alex.source.webtester.controllers;


import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import ua.alex.source.webtester.entities.Answer;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class QuestionEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        String value = (String) getValue();
        return value;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String value = text;
        Answer answer = new Answer();
        answer.setIdAnswer(Long.valueOf(text));
        setValue(answer);
    }
}
