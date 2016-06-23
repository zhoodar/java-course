package kg.djedai.store;

import kg.djedai.app.clinic.Animal;
import kg.djedai.app.clinic.Cat;
import kg.djedai.app.clinic.Dog;
import kg.djedai.app.clinic.Pet;
import kg.djedai.models.ClientModel;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Реализация интерфейса Storage изпользуя структуру данных HashMap
 * @author Zhoodar
 * @since 10.06.2016.
 */
public class StorageCache implements Storage {

    private final ConcurrentHashMap<String, ClientModel> clients = new ConcurrentHashMap<>();

    private final List<ClientModel> foundClient = new CopyOnWriteArrayList<ClientModel>();

    @Override
    public Collection<ClientModel> getClients() {
        return this.clients.values();
    }

    @Override
    public void addClient(ClientModel client) {
        this.clients.put(client.getId(),client);
    }

    @Override
    public void editClient(ClientModel client) {
        this.clients.replace(client.getId(),client);
    }

    @Override
    public void deleteClient(String id) {
        this.clients.remove(id);
    }

    @Override
    public ClientModel getClientById(String id) {
        return this.clients.get(id);
    }

    @Override
    public ClientModel getLastClient() {
        LinkedList<ClientModel> list = new LinkedList<>();
        list.addAll(this.getClients());
        return list.getLast();
    }

    @Override
    public List<ClientModel> findByFullName(String clientName) {
        this.foundClient.clear();
        for (ClientModel client : getClients()) {
            if (client.getNameClient().toLowerCase().equals(clientName.toLowerCase()) )
                this.foundClient.add(client);
            for(Pet pet: client.getPets()){
                if(pet.getName().toLowerCase().equals(clientName.toLowerCase()))
                    this.foundClient.add(client);

            }
        }
        return this.foundClient;
    }


    @Override
    public List<ClientModel> findByContain(String partName) {
        this.foundClient.clear();
        for (ClientModel client : getClients()) {
            for(Pet pet: client.getPets()){
                if(pet.getName().toLowerCase().indexOf(partName.toLowerCase())!=-1)
                    this.foundClient.add(client);

            }
            if(client.getNameClient().toLowerCase().indexOf(partName.toLowerCase())!= -1)
                this.foundClient.add(client);
        }
        return this.foundClient;
    }

    @Override
    public String generateId() {
       return RandomIdGenerator.getBase36(6);
    }

    @Override
    public void close() {
    }

    @Override
    public void addPetToClient(int type, String petName,String idClient) {
        Pet pet;
        if(type == 2 )
            pet = new Cat(petName);
        else
            pet = new Dog(new Animal(petName));
        getClientById(idClient).setPets(pet);
    }

    @Override
    public List<Pet> getPetCurrentClient(String idCurrentClient) {
        return getClientById(idCurrentClient).getPets();
    }

    @Override
    public void deletePetCurrentClient(String idCurrentClient, String petName) {
        List<Pet> pets = getClientById(idCurrentClient).getPets();
        for(Pet pet : pets ){
            if(pet.getName().equals(petName))
                pets.remove(pet);
        }
    }

    private static class RandomIdGenerator {
        private static char[] _base62chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        private static Random _random = new Random();

        public static String getBase36(int length){
            StringBuilder sb = new StringBuilder(length);
            for(int i=0; i<length;i++){
                sb.append(_base62chars[_random.nextInt(36)]);
            }
            return sb.toString();
        }
    }
}
