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

    private final String nameClient;
    private final String index ;

    private final List<Pet> pets = new CopyOnWriteArrayList<>();

    public ClientModel( String index, String nameClient) {
        this.index = index;
        this.nameClient = nameClient;
    }

    /**
     * метод чтобы узнат конкретных  питомцев
     * @return спсисок питомцев
     */
    public List<Pet> getPet(){
        return this.pets;
    }

    /**
     * Для нахождение id клиента
     * @return id
     */
    public String getId(){
        return this.index;
    }

    public  String getNameClient(){
        return this.nameClient;
    }


    public void setPets(Pet pet) {
        this.pets.add(pet);
    }
}
