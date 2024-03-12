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
public class Notification {
    private int notificationId;
    private User from;
    private ArrayList<User> tos;
    private String url;
    private NotificationType type;
    private boolean isRead;
    private String content;
}
