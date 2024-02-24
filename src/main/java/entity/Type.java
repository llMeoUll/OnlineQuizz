package entity;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Type {
    private int typeId;
    private String typeName;
    private ArrayList<Question> questions;
}
