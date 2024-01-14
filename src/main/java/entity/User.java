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
    private Role role;
    private ArrayList<StarRate> ratedStar;
    private ArrayList<Test> doneTest;
    private ArrayList<Set> ownedSets;
    private ArrayList<SelfTest> selfTests;
    private ArrayList<Room> ownedRooms;
    private ArrayList<Room> joinedRooms;
    private String avatar;
}
