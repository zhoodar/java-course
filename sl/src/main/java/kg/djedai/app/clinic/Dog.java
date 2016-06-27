package kg.djedai.app.clinic;

/**
 * Реализация  собаки. 
 */
public class Dog implements Pet {
	
	/**
	 * Базовая реализация питомца.
	 */
	private final Pet pet;
	
	public Dog(final Pet pet){
		this.pet = pet;
	}
	
	/**
	 * {@inheritDoc}
	 * @return
	 */

	public String getName() {
		return  this.pet.getName();
	}
}