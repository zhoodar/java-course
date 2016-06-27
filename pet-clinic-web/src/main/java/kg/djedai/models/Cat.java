package kg.djedai.models;

/**
 * @author Zhoodar
 * @since 25.06.2016.
 */
public class Cat extends Pet{

    private int type=2;

    public Cat(final String name) {
        super(name);
    }

    public Cat() {
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
