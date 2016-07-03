package kg.djedai.models;

import java.util.Set;

/**
 * @author Zhoodar
 * @since 02.07.2016.
 */
public class User extends Base {
    private String login;
    private String email;
    private Role role;
    private Set<Message> messages;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
