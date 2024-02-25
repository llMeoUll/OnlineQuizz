package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class QuestionOption {
    private int optId;
    private Question question;
    private String optContent;
}
