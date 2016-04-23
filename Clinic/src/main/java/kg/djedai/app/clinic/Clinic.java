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
            if (this.clients[j] != null && this.clients[j].getPet() != null && this.clients[j].getPet().getName().equals(name))
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
        int position=0;
        for(int j=0; j<this.clients.length; j++){
            if( this.clients[j] != null && this.clients[j].getId().equals(id) ){
                position=j;
            }
        }
        return new Client[]{this.clients[position]};
    }

    /**
     * Удалить клиента если есть такой id клиет
     * @param id Имя клиента
     * @return boolean  true or false
     */
    public boolean removeClientById(final String id){
        boolean isRemoved = false;
        for (int j =0; j<this.clients.length;j++ ){
            if(this.clients[j]!=null&& this.clients[j].getId().equals(id)){
                this.clients[j]=null;
                isRemoved=true;
            }
        }
        return isRemoved;
    }



 }