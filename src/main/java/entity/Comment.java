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
    private int commentId;
    private Set set;
    private User user;
    private String content;
    private int likes;
    private int unlikes;
    private Date createdAt;
    private Date updatedAt;
    private ArrayList<Comment> repliedComments;
}
