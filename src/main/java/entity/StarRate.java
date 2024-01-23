package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter

public class StarRate {
    private User user;
    private Set set;
    private int rate;
    private Date createdAt;
    private Date updatedAt;
}
