package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SelfTestQuestion {
    private SelfTest selfTest;
    private Question question;
    private String answer;
}
