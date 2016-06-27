package kg.djedai.models;

/**
 * @author Zhoodar
 * @since 25.06.2016.
 */
public abstract class Pet {

    private int id;
    private String name;
    private ClientModel clientModel;

    public Pet(){
    }

    public Pet(final String name){
        this.name = name;
    }

    public ClientModel getClientModel() {
        return clientModel;
    }

    public void setClientModel(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  abstract int getType();

    public  abstract void setType(int type);
}
