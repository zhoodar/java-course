package kg.djedai.store;


import kg.djedai.app.clinic.Cat;
import kg.djedai.models.ClientModel;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Реализация паттерна Синглтон для webapp
 * @author Zhoodar
 * @since 01.06.2016.
 */
public class ClientCache {
    private static final ClientCache INSTANCE = new ClientCache();

    private final ConcurrentHashMap<Integer, ClientModel> clients = new ConcurrentHashMap<Integer, ClientModel>();

    private final List<ClientModel> foundClient = new CopyOnWriteArrayList<ClientModel>();

    public static ClientCache getInstance(){
        return INSTANCE;
    }

    public Collection<ClientModel> values(){
        return this.clients.values();
    }

    public void addClient(final ClientModel client){
        this.clients.put(client.getId(),client);
    }

    public void deleteClient(final int id){
        this.clients.remove(id);
    }

    public void editClient(final ClientModel client){
        this.clients.replace(client.getId(),client);
    }

    public ClientModel get(final int id){
        return this.clients.get(id);
    }

    public List<ClientModel> findByFullName(final String client){
        if(client !=  null) {
            for (Map.Entry<Integer, ClientModel> entry : this.clients.entrySet()) {
                if (entry.getValue().getNameClient().toLowerCase().equals(client.toLowerCase()) ||
                        entry.getValue().getPetName().toLowerCase().equals(client.toLowerCase()))
                    this.foundClient.add(entry.getValue());
            }
        }
        return this.foundClient;
    }

    public int generateId(){
        int count=0;
        for(Map.Entry<Integer, ClientModel> entry: clients.entrySet()){
           count++;
        }
        return count;
    }

    public List<ClientModel> findByContain(final String client) {
        if(client !=  null) {
            for (Map.Entry<Integer, ClientModel> entry : clients.entrySet()) {
                if (entry.getValue().getNameClient().toLowerCase().contains(client.toLowerCase())
                        || entry.getValue().getPetName().toLowerCase().contains(client.toLowerCase()))
                    this.foundClient.add(entry.getValue());
            }
        }
        return this.foundClient;
    }
}
