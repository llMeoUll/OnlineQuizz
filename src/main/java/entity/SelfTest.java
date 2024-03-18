package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SelfTest {
    private int selfTestId;
    private User user;
    private Set set;
    private int result;
    private Timestamp createdAt;
}
