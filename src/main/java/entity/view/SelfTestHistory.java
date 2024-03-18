package entity.view;

import entity.Set;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SelfTestHistory {
    private int selfTestId;
    private User user;
    private Set set;
    private Timestamp createdAt;
    private int result;
    private int numberOfQuestion;
}
