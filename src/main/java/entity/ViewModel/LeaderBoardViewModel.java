package entity.ViewModel;

import entity.Test;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeaderBoardViewModel {
    private int udtId;
    private User user;
    private Test test; // Information of test
    private int orderAttempt;
    private Timestamp createdAt;
    private float score;
    private float totalScore;
}
