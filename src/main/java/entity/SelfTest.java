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
    private int selfTestId;
    private User user;
    private int numbOfQues;
    private ArrayList<Question> questions;
}
