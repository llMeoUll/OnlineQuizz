package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SelfTest {
    private int self_test_id;
    private User user;
    private int numb_of_ques;
    private ArrayList<Question> questions;
}
