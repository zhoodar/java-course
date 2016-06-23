package kg.djedai.models;

import kg.djedai.app.clinic.Pet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * класс реализует Клиент
 * @author Zhoodar
 * @since 01.06.2016
 */
public class ClientModel {

    private String nameClient;
    private String id ;
    private final List<Pet> pets = new CopyOnWriteArrayList<>();

    public ClientModel( String id, String nameClient) {
        this.id = id;
        this.nameClient = nameClient;
    }

    public ClientModel(){
    }

    /**
     * метод чтобы узнат конкретных  питомцев
     * @return спсисок питомцев
     */
    public List<Pet> getPets(){
        return this.pets;
    }

    /**
     * Для нахождение id клиента
     * @return id
     */
    public String getId(){
        return this.id;
    }

    public  String getNameClient(){
        return this.nameClient;
    }


    public void setPets(Pet pet) {
        this.pets.add(pet);
    }

    public void setNameClient(String nameClient){
        this.nameClient = nameClient;
    }
    public void setId(String id){
        this.id = id;
    }


}
