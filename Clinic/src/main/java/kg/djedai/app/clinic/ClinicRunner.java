package kg.djedai.app.clinic;
/**
 * Запуск Clinic app.
 * @author Zhoodar Djedai
 * @since 22.04.2016
 */
public class ClinicRunner{
	
	public static void main(String[] args) {
		final Clinic clinic = new Clinic(5);
		
		clinic.addClient(0, new Client("David",new Cat("Tom")));
		clinic.addClient(1, new Client("Ross",new Cat("Laky")));
		
		System.out.println(clinic.findClientByPetName("Tom"));
	}
}