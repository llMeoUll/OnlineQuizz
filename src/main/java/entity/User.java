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

public class User {
    private int uid;
    private String username;
    private String password;
    private String email;
    private String displayName;
    private String avatar;
    private int rid;
    private ArrayList<Test> doneTests;
    private ArrayList<StarRate> listOfRated;
    private ArrayList<Room> rooms;
    private ArrayList<Set> sets;
    private ArrayList<SelfTest> selfTests;
}
