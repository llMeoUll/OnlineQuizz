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

public class Comment {
    private int comment_id;
    private Set set;
    private User user;
    private String content;
    private int likes;
    private int unlikes;
    private Date created_at;
    private Date updated_at;
    private ArrayList<Comment> repliedComments;
}
