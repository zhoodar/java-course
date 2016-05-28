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
     * @param args Аргументы
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
        System.out.println("\tДобро пожаловат!");
        while(!stop){
            callMenu();
            inputMenu();
        }
    }

    /**
     * Меню выборки функциональности
     */
    public void callMenu(){
        System.out.println("****************************************************************************");
        System.out.println("* 1.Добавить*2.Удалить*3.Найти*4.Переименовать*5.Показат*6.Выйти*7.Пробный * ");
        System.out.println("****************************************************************************");
    }

    /**
     * Меню выборки
     */
    public void inputMenu(){
        String inputS =this.reader.next();
        int input=0;
        try{
            input =Integer.valueOf(inputS);
        }catch (NumberFormatException e){System.out.println("\tВведите только цифры!");}
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
                System.out.print("\t До скорой встречи!");
                break;
            case (7):
                addTestButton();
                break;
            default:
                System.out.println("\tНапример: для поиска клиента введите: 3 ");
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
     * Для тестировки
     */
    public void addTestButton(){
        String answer;
        System.out.println(" Если будуте добавить пробные данные, все вашы данные будет удалены.\n Хотите добавить пробные даннные? y/n ");
        while(true) {
            answer = this.reader.next();
            if (answer.equals("y")) {
                TestDataInClinic();
                System.out.println("\tПробная База данных дабавлена!\n");
                break;
            } else if (answer.equals("n")) {
                System.out.println(" \n");
                break;
            } else {
                System.out.println("\tВведите правльное выражение! ");
            }
        }
    }

    /**
     * Создать ноый клиет
     */
    public void addNewClientButton(){
        String inputClient;
        String classPet;
        String namePet;
        int number=clinic.getNumberOfClients();
        number++;
        if(number==MAX_CLIENTS) number--;
        System.out.println("Введите Имя клиента: ");
        inputClient = this.reader.next();
        while(true){
            System.out.println("У клиента кошка или собака: cat/dog ");
            classPet=this.reader.next();
            if(classPet.equals("cat") || classPet.equals("dog"))
                break;
            else
                System.out.println("Введите правильно!");
        }
        if(classPet.equals("cat")){
            System.out.println("Введите имя кошки: ");
            namePet = this.reader.next();
            Cat cat = new Cat(namePet);
            clinic.addClient(number,new Client(inputClient,cat));
        } else if(classPet.equals("dog")){
            System.out.println("Введите имя собаки: ");
            namePet = this.reader.next();
            Dog dog = new Dog(new Animal(namePet));
            clinic.addClient(number, new Client(inputClient, dog));
        }

        System.out.println("\tКлиент успешно добавлен! ");
    }

    /**
     * Показать всех клиентов
     */
    public void showAllButton(){
        if(clinic.getNumberOfClients()==-1) {
            System.out.println("Нет данных для покза! Сначала добавьте Клиента или Попробуйте 'пробные данные'!");
        } else {
            System.out.println("В Базе есть эти данные!\n");
            clinic.toPrint(clinic.getClients());
        }
    }

    /**
     * Переименовать имя клиента
     */
    public void reNameButton() {
        if(clinic.getNumberOfClients()==-1) {
            System.out.println("Нет данных для изминения! Сначала добавьте Клиента или Попробуйте 'пробные данные'!");
        } else {
            System.out.println("Введите имя клиента которого хотите переименовать!");
            String oldName = this.reader.next();
            System.out.println("Введите новую Имю клиента:");
            String newName = this.reader.next();
            if (clinic.reNameClientName(oldName, newName)) {
                System.out.println("\tУспешно переименован !");
            } else {
                System.out.println("\tТокаго имени нету");
            }
        }
    }

    /**
     * Удалить клиента
     */
    public void removeButton(){
        if(clinic.getNumberOfClients()==-1) {
            System.out.println("Нет данных для удаления! Сначала добавьте Клиента или Попробуйте 'пробные данные'!");
        } else {
            System.out.println("Введите имя клиента которого хотите удалить!");
            String name = this.reader.next();
            if(clinic.removeClientById(name)) {
                System.out.println("\tУспешно удалено!");
            } else {System.out.println("\t Клиент Не удален! \nПричины: база пуст или Клиент с таким именим нет!");}
        }
    }
    //Найти клиента
    public void findClientButton(){
        if(clinic.getNumberOfClients()==-1) {
            System.out.println("Нет данных ! Сначала добавьте Клиента или Попробуйте 'пробные данные'!");
        } else {
            System.out.println("Введите имя клиента которого хотите найти!");
            String name = this.reader.next();
            if(clinic.findClientById(name)!=null) {
                System.out.println("Найденно!");
                clinic.toPrint(clinic.findClientById(name));
            } else {System.out.println("Не Найденно! Введите правильную Имю!");}
        }
    }

}