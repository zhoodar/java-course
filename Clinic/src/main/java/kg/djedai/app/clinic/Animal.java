package kg.djedai.app.clinic;
/**
 * Class Dog 
 */
public  class Animal implements Pet{
	/**
	 * Name 
	 */
	private final String name;
	
	/**
	 * Конструктор
	 * @param name имя животного
	 */
	public Animal(final String name){
		this.name = name;
	}

    /**
     *{@inheritDoc}
     *@return
     */
    @Override
    public String getName() {
        return this.name;
    }

}
