package entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    private int id;
    private String email;
    private String givenName;
    private String familyName;
    private String avatar;
    private String username;
    private String password;
    private boolean isVerified;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ArrayList<Role> roles;
    private ArrayList<StarRate> ratedStar;
    private ArrayList<Test> doneTest;
    private ArrayList<Set> ownedSets;
    private ArrayList<SelfTest> selfTests;
    private ArrayList<Room> ownedRooms;
    private ArrayList<Room> joinedRooms;
    private ArrayList<Comment> comments;
}
