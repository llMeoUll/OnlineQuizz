package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    private int testId;
    private Room room;
    private String testName;
    private String testDescription;
    private Date startTime;
    private Date endTime;
    private ArrayList<Question> questions;
    private ArrayList<User> usersDoTest;
}
