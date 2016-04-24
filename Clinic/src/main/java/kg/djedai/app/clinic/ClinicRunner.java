package kg.djedai.app.clinic;
import java.util.Scanner;

/**
 * Запуск Clinic app.
 * @author Zhoodar Djedai
 * @since 22.04.2016
 */
public class ClinicRunner{
    private final int MAX_CLIENTS =10;
    private Clinic clinic ;
    private boolean stop;
    private Scanner reader;

    /**
     * Constructor app
     */
    public ClinicRunner(){
        init();
    }

    /**
     * Main метод
     * @param args
     */
    public static void main(String[] args) {
        ClinicRunner run = new ClinicRunner();
    }

    /**
     * Инициализировать Clinic app
     */
    public void init(){
        this.clinic = new Clinic(MAX_CLIENTS);
        this.reader = new Scanner(System.in);
        this.stop = false;
        this.start();
    }
    /**
     * Зупускает приложению
     */
    public void start(){
        String answer;
        System.out.println("База данных пуст, Хотите добавить пробную базу? y/n ");
        while(true){
            answer=this.reader.next();
            if(answer.equals("y")) {
                TestDataInClinic();
                System.out.println("\tПробная База дабавлена!");
                break;
            } else if(answer.equals("n")){
                System.out.println("Если не хотите добавить пробную базу, можете сами дабавить новых, вызвавь функцию - добавить");
                break;
            }else{
                System.out.println("\tВведите правльное выражение! ");
            }

        }
        while(!stop){
            callMenu();
            inputMenu();
        }
    }

    /**
     * Меню выборки функциональности
     */
    public void callMenu(){
        System.out.print("\t1.Добавить || 2.Удалить || 3.Найти || 4.Переименовать || 5.Показат польностью || 6.Выйти \n" );
    }

    /**
     * Меню выборки
     */
    public void inputMenu(){
        int input =this.reader.nextInt();
        switch (input){
            case (1):
                addNewClientButton();
                break;
            case (2):
                removeButton();
                break;
            case (3):
                findClientButton();
                break;
            case (4):
                reNameButton();
                break;
            case (5):
                showAllButton();
                break;
            case (6):
                this.stop=true;
                break;
            default:
                System.out.print("\n\t Введите правилную команду");
                break;

        }
    }

    /**
     * Тестовые данные
     */
	public void TestDataInClinic(){
        clinic.addClient(0, new Client("Rocky", new Dog(new Animal("Bobik"))));
        clinic.addClient(1, new Client("Nina", new Cat("Rizhik")));
        clinic.addClient(2, new Client("Monika", new Dog(new Animal("Sharik"))));
        clinic.addClient(3, new Client("Richard", new Cat("Tom")));
    }

    /**
     * Создать ноый клиет
     */
    public void addNewClientButton(){
        String inputClient;
        String classPet;
        String namePet;
        int number=clinic.getNumberOfClients();
        if(number==MAX_CLIENTS) number--;
        System.out.println("Введите Имя клиента: ");
        inputClient = this.reader.next();
        System.out.println("Введите Имя питомца: ");
        namePet = this.reader.next();
        System.out.println("Введите класс питомца: cat/dog ");
        classPet=this.reader.next();
        while(true){
            if(classPet.equals("cat")){
                Cat cat = new Cat(namePet);
                clinic.addClient(number,new Client(inputClient,cat));
                break;
            } else if(classPet.equals("dog")){
                Dog dog = new Dog(new Animal(namePet));
                clinic.addClient(number, new Client(inputClient, dog));
                break;
            } else {
                System.out.println("Введите  правильно класс питомца: cat/dog ");
            }
        }
        System.out.println("\tКлиент успешно добавлен! ");
    }

    /**
     * Показать всех клиентов
     */
    public void showAllButton(){
        clinic.toPrint(clinic.getClients());
    }

    /**
     * Переименовать имя клиента
     */
    public void reNameButton() {
        System.out.println("Введите имя клиента которого хотите переименовать!");
        String oldName = this.reader.next();
        System.out.println("Введите новую Имю клиента:");
        String newName = this.reader.next();
        if(clinic.reNameClientName(oldName,newName)) {
            System.out.println("\tУспешно переименована !");
        } else {
            System.out.println("\tТокаго имени нету");
        }
    }

    /**
     * Удалить клиента
     */
    public void removeButton(){
        System.out.println("Введите имя клиента которого хотите удалить!");
        String name = this.reader.next();
        if(clinic.removeClientById(name)) {
            System.out.println("\tУспешно удалено!");
        } else {System.out.println("\tНе удалено! ( ");}

    }
    //Найти клиента
    public void findClientButton(){
        System.out.println("Введите имя клиента которого хотите найти!");
        String name =this.reader.next();
        clinic.toPrint(clinic.findClientById(name));
    }

}