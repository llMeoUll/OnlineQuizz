package entity;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feature {
    private int fId;
    private String fName;
    private String description;
    private String url;
    private ArrayList<Role> roles;
}
