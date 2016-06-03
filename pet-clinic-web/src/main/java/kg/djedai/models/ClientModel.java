package kg.djedai.models;

import kg.djedai.app.clinic.Pet;

/**
 * класс реализует Клиент
 * @author Zhoodar
 * @since 01.06.2016
 */
public class ClientModel {

    private final String nameClient;
    private final int id;
    private final Pet pet;

    public ClientModel( int id, String nameClient, Pet pet) {
        this.id = id;
        this.nameClient = nameClient;
        this.pet = pet;
    }
    /**
     * метод чтобы узнат конкретного питомца
     * @return Питомца
     */
    public Pet getPet(){
        return this.pet;
    }
    /**
     * Для нахождения имени питомца
     * @return Имя питомца
     */

    public String getPetName(){
        return String.format("%s", this.pet.getName());
    }

    /**
     * Для нахождение id клиента
     * @return id
     */
    public int getId(){
        return this.id;
    }

    public  String getNameClient(){
        return this.nameClient;
    }

    public String toString(){
        return String.format("%s%s",this.getId(), this.getPet());
    }

}
