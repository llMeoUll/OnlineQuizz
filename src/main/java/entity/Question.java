package entity;

import lombok.*;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString

public class Question {
    private int qId;
    private String question;
    private String answer;
    private Set set;
    private Type type;
    private ArrayList<QuestionOption> questionOptions;
    private ArrayList<SelfTest> selfTests;
    private ArrayList<Test> tests;
}

