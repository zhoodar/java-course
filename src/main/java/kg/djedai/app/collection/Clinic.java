package kg.djedai.app.collection;


import java.util.Iterator;

/**
 * Описания клиника.
 * @author Zhoodar Djedai
 * @since 22.04.2016
 */
 public class Clinic {
	 
	/**
	 * Список клиентов
	 */
	private MyArrayList<Client> clients;

    /**
     * Коструктор создаст список с размерю size
     * @param size размер списка
     */
	public Clinic(final int size) {
		this.clients = new MyArrayList<Client>(size);
	}

    /**
     * Получить всех клиентов
     * @return clients
     */
    public MyArrayList<Client> getClients(){
       return this.clients;
    }



	/**
	 * Добавить клиента.
	 * @param client добавляемый объект Клиент
	 */
	public void addClient(final Client client){
		  this.clients.add(client);
	}
	/**
	 * Найти клиента по именни питомца
	 * @param name 
	 * @return массив найденного объекта
	 */
	public MyArrayList<Client> findClientByPetName(final String name){
		int position=0;

        return new MyArrayList<Client>(){};
	}

    /**
     * Найти клиента по id
     * @param id Имя клиента
     * @return массив найденного объекта
     */
    public MyArrayList<Client> findClientById(final String id){
        for ( int j =0; j<this.clients.size();j++ ) {
            if (clients.get(j) != null && clients.get(j).getId().equals(id)){
                final int position =j;
                return new MyArrayList<Client>(){Client client=new Client(clients.get(position).getId(),clients.get(position).getPet());};
            }
        }
        return null;
    }

    /**
     * Удалить клиента если есть клиент c такой id
     * @param id Имя клиента
     * @return boolean  true or false
     */
    public boolean removeClientById(final String id){
        boolean isRemoved = false;
        for (int j =0; j<this.clients.size();j++ ){
            if(clients.get(j)!=null && clients.get(j).getId().equals(id))
                isRemoved=clients.remove(clients.get(j));
                break;
        }
        return isRemoved;
    }

    /**
     * Переименовать имени клиента
     * @param oldName старая имя
     * @param newName новая имя
     * @return boolean true or false
     */
    public boolean reNameClientName(final String oldName, final String newName){
        boolean isRenamed = false;
        Client newClient = null;
        for(int j =0; j<this.clients.size(); j++) {
            if (clients.get(j) != null && clients.get(j).getId().equals(oldName)) {
                newClient = new Client(newName, clients.get(j).getPet());

                isRenamed = true;
                break;
            }
        }
        return isRenamed;
    }

    /**
     * Печатать на экран введеного клитна(ов)
     * @param clients массив клиентов
     */
    public void  toPrint(MyArrayList<Client> clients){
        int counter=0;
        for(int j=0;j<clients.size();j++){
            counter++;
            if(clients.get(j) != null) {
                Pet pet = clients.get(j).getPet();
                System.out.print("Client " + counter + ": " + clients.get(j).getId());
                System.out.println(", pet: "+pet.getName());
            }
                else System.out.println("Null pointer");
        }

    }

    /**
     * Переобразование из массива в строку
     * @param client массив
     * @return строка
     */
    public String toString(MyArrayList<Client> client){
        String result = "";
        for(int j=0;j<client.size();j++) {
            if(client.get(j) !=null) {
                Pet pet = client.get(j).getPet();
                if(pet != null){
                    result="Client: " + client.get(j).getId()+", pet: "+pet.getName();
                }
                else result = "";
            }
        }
        return result;
    }

    /**
     * для нахождение позици в массиве
     * @return int
     */
    public int getNumberOfClients(){
        int counter=-1;
        if(this.clients.size()>0){
            for(int j =0;j<this.clients.size();j++){
                if(this.clients.get(j)!=null)
                counter++;
            }
        }
        return counter;
    }
 }