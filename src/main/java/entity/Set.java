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
    private int sid;
    private String sname;
    private String description;
    private boolean is_private;
    private Date created_at;
    private Date updated_at;
    private User user;
    private ArrayList<StarRate> starRates;
    private ArrayList<Comment> comments;
    private ArrayList<Question> questions;
}
