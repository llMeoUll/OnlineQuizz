package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private int room_id;
    private String room_name;
    private String code;
    private String password;
    private String description;
    private User user;
    private Date created_at;
    private Date updated_at;
    private ArrayList<User> joinedUsers;
    private ArrayList<Test> tests;
    private String code_to_join;
}
