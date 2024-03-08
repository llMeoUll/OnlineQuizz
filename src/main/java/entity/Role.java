package entity;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    private int rId;
    private String name;
    private ArrayList<Feature> features = new ArrayList<>();
}
