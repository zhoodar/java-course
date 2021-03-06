package kg.djedai.app.clinic;
/**
 * класс реализует Клиент
 * @author Zhoodar Djedai
 * @since 22.04.2016
 */
 public class Client{
	private final String id;
	private final Pet pet;
	
	public Client(String id, Pet pet) {
		this.id = id;
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
    public String getId(){
        return this.id;
    }

    public String toString(){
        return String.format("%s%s",this.getId(), this.getPet());
    }
 
 }