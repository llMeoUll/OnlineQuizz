package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HashTag {
    private int id;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ArrayList<Set> sets = new ArrayList<>();
}
