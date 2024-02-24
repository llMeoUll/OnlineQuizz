package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private int roomId;
    private String roomName;
    private String code;
    private String password;
    private String description;
    private User user;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ArrayList<User> joinedUsers;
    private ArrayList<Test> tests;
}
