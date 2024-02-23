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

public class Set {
    private int sId;
    private String sName;
    private String description;
    private boolean isPrivate;
    private Date createdAt;
    private Date updatedAt;
    private User user;
    private ArrayList<StarRate> starRates;
    private ArrayList<Comment> comments;
    private ArrayList<Question> questions;
    private ArrayList<HashTag> hashTags = new ArrayList<>();
}
