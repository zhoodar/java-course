package kg.djedai.models;

/**
 * @author Zhoodar
 * @since 25.06.2016.
 */
public class Dog extends Pet {
    private int type = 1;

    public Dog(String name) {
        super(name);
    }

    public Dog() {
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public void setType(int type) {
        this.type=type;
    }
}
