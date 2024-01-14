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
    private int test_id;
    private Room room;
    private String test_name;
    private String test_description;
    private Date start_time;
    private Date end_time;
    private ArrayList<Question> questions;
    private ArrayList<User> usersDoTest;
}
