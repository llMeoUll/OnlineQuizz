package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class Question {
    private int qid;
    private String question;
    private String answer;
    private Set set;
    private Type type;
    private ArrayList<QuestionOption> questionOptions;
    private ArrayList<SelfTest> selfTests;
    private ArrayList<Test> tests;
}
