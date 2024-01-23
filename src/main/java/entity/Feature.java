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

public class Feature {
    private int fId;
    private String fName;
    private String description;
    private String url;
    private ArrayList<Role> roles;
}
