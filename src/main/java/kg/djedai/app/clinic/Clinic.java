package kg.djedai.app.clinic;

/**
 * Описания клиника.
 * @author Zhoodar Djedai
 * @since 22.04.2016
 */
 public class Clinic {
	 
	/**
	 * Список клиентов
	 */
	private final Client[] clients;
	
	public Clinic(final int size) {
		this.clients = new Client[size];
	}

    /**
     * Получить всех клиентов
     * @return clients
     */
    public Client[] getClients(){
        return this.clients;
    }

	/**
	 * Добавить клиента.
	 * @param position Позиция в массиве 
	 * @param client добавляемый объект Клиент
	 */
	public void addClient(final int position, final Client client){
		this.clients[position] = client;
	}
	/**
	 * Найти клиента по именни питомца
	 * @param name 
	 * @return массив найденного объекта
	 */
	public Client[] findClientByPetName(final String name){
		int position=0;
		for(int j = 0; j < this.clients.length; j++) {
            if (this.clients[j] != null && this.clients[j].getPet() != null && this.clients[j].getPetName().equals(name))
                position=j;
        }
        return new Client[]{this.clients[position]};
	}

    /**
     * Найти клиента по id
     * @param id Имя клиента
     * @return массив найденного объекта
     */
    public Client[] findClientById(final String id){
        int position=-1;
        for(int j=0; j<this.clients.length; j++){
            if( this.clients[j] != null && this.clients[j].getId().equals(id) ){
                position=j;
                break;
            }
        }
        return new Client[]{this.clients[position]};
    }

    /**
     * Удалить клиента если есть клиент c такой id
     * @param id Имя клиента
     * @return boolean  true or false
     */
    public boolean removeClientById(final String id){
        boolean isRemoved = false;
        for (int j =0; j<this.clients.length;j++ ){
            if(this.clients[j]!=null&& this.clients[j].getId().equals(id)){
                this.clients[j]=null;
                isRemoved=true;
                break;
            }
        }
        return isRemoved;
    }

    /**
     * Переименовать имени клиента
     * @param oldName старый имя
     * @param newName новый имя
     * @return boolean true or false
     */
    public boolean reNameClientName(final String oldName, final String newName){
        boolean isRenamed = false;
        for(int j =0; j<this.clients.length; j++) {
            if(this.clients[j] != null && this.clients[j].getId().equals(oldName)) {
                Client reNamed = new Client(newName,this.clients[j].getPet());
                clients[j]=reNamed;
                isRenamed= true;
                break;
            }
        }
        return isRenamed;
    }

    /**
     * Печатать на экран введеного клитна(ов)
     * @param clients массив клиентов
     */
    public void  toPrint(Client[] clients){
        for(Client client : clients){
            if(client !=null) {
                Pet pet = client.getPet();
                System.out.print("Client: " + client.getId());
                if(pet != null){
                    System.out.println(", pet: "+pet.getName());
                }
                else System.out.println();
            }
        }
    }

    /**
     * Переобразование из массива в строку
     * @param clients массив
     * @return строка
     */
    public String toString(Client[] clients){
        String result = "";
        for(Client client : clients){
            if(client !=null) {
                Pet pet = client.getPet();
                if(pet != null){
                    result="Client: " + client.getId()+", pet: "+pet.getName();
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
        if(this.clients.length>0){
            for(int j =0;j<this.clients.length;j++){
                if(this.clients[j]!=null)
                counter++;
            }
        }
        return counter;
    }
 }