package kg.djedai.store;


import kg.djedai.app.clinic.Pet;
import kg.djedai.models.ClientModel;

import java.util.Collection;
import java.util.List;


/**
 * Реализация паттерна Синглтон для webapp
 * @author Zhoodar
 * @since 01.06.2016.
 */
public class ClientCache implements Storage{

    private static final ClientCache INSTANCE = new ClientCache();

    private final Storage storage = new StorageCache();

    public static ClientCache getInstance(){
        return INSTANCE;
    }

    @Override
    public Collection<ClientModel> getClients(){
        return this.storage.getClients();
    }

    @Override
    public void addClient(final ClientModel client){
        this.storage.addClient(client);
    }

    @Override
    public void deleteClient(final String id){
        this.storage.deleteClient(id);
    }

    @Override
    public void editClient(final ClientModel client){
        this.storage.editClient(client);
    }

    @Override
    public ClientModel getClientById(final String id){
        return this.storage.getClientById(id);
    }

    @Override
    public ClientModel getLastClient(){
        return this.storage.getLastClient();
    }

    @Override
    public List<ClientModel> findByFullName(final String clientName){
       return this.storage.findByFullName(clientName);
    }

    @Override
    public String generateId(){
      return this.storage.generateId();
    }

    @Override
    public List<ClientModel> findByContain(final String partName) {
        return this.storage.findByContain(partName);
    }

    public void close() {
    }

    @Override
    public void addPetToClient(int type, String namePet , String idClient) {
        this.storage.addPetToClient(type,namePet, idClient);
    }

    @Override
    public List<Pet> getPetCurrentClient(String idCurrentClient) {
        return this.storage.getPetCurrentClient(idCurrentClient);
    }

    @Override
    public void deletePetCurrentClient(String idCurrentClient, String petName) {
        this.storage.deletePetCurrentClient(idCurrentClient, petName);
    }

}
