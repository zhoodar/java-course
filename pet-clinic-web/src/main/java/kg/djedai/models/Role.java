package kg.djedai.models;

/**
 * @author Zhoodar
 * @since 02.07.2016.
 */
public class Role extends Base{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
