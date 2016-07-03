package kg.djedai.models;

/**
 * @author Zhoodar
 * @since 02.07.2016.
 */
public class Message extends Base {

    private User user;
    private String text;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
