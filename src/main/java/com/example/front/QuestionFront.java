package com.example.front;

import javafx.beans.property.SimpleStringProperty;

public class QuestionFront {
    private final SimpleStringProperty questions;
    private final SimpleStringProperty types;
    private final SimpleStringProperty responses;
    private final SimpleStringProperty operations;

    public QuestionFront(String questions, String types, String responses, String operations) {
        this.questions = new SimpleStringProperty(questions);
        this.types = new SimpleStringProperty(types);
        this.responses = new SimpleStringProperty(responses);
        this.operations = new SimpleStringProperty(operations);
    }



    public String getQuestions() {
        return questions.get();
    }

    public String getTypes() {
        return types.get();
    }

    public String getResponses() {
        return responses.get();
    }

    public String getOperations() {
        return operations.get();
    }


    public void setQuestions(String value) {
        questions.set(value);
    }

    public void setTypes(String value) {
        types.set(value);
    }

    public void setResponses(String value) {
        responses.set(value);
    }

    public void setOperations(String value) {
        operations.set(value);
    }
}
