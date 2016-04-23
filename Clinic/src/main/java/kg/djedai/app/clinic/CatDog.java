package kg.djedai.app.clinic;

/**
 Реализация двух питомцов
 */
 public class CatDog implements Pet {
	/**
	 * создание имен питомцов
	 */
	private final Pet dog;
	private final Pet cat;
	
	/**
	 Конструктор.
	 */
	public CatDog(Pet cat,Pet dog) {
		this.dog = dog;
		this.cat = cat;
	} 
	
	/**
	 *
	 *{@inheritDoc}
	 *@return
	 */
	@Override
	public String getName() {
		return String.format("%s%s", this.cat.getName(), this.dog.getName());
	}
 }