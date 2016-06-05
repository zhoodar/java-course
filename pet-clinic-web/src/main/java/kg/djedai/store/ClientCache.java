package kg.djedai.store;



import kg.djedai.models.ClientModel;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Реализация паттерна Синглтон для webapp
 * @author Zhoodar
 * @since 01.06.2016.
 */
public class ClientCache {
    private static final ClientCache INSTANCE = new ClientCache();

    private final ConcurrentHashMap<Integer, ClientModel> clients = new ConcurrentHashMap<Integer, ClientModel>();

    private final List<ClientModel> foundClient = new CopyOnWriteArrayList<ClientModel>();

    private AtomicInteger id = new AtomicInteger();

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

    public List<ClientModel> findByFullName(final String clientName){
        this.foundClient.clear();
        for (ClientModel client : this.clients.values()) {
            if (client.getNameClient().toLowerCase().equals(clientName.toLowerCase()) ||
                    client.getPetName().toLowerCase().equals(clientName.toLowerCase()))
                this.foundClient.add(client);
        }
        return this.foundClient;
    }

    public int generateId(){
      return this.id.getAndIncrement();
    }

    public List<ClientModel> findByContain(final String partName) {
        this.foundClient.clear();
        for (ClientModel client : this.clients.values()) {
            for(int i=0; i< client.getPetName().length()-partName.length(); i++) {
                if(partName.length()< client.getPetName().length()) {
                    if (client.getPetName().substring(i, partName.length()).toLowerCase().equals(partName.toLowerCase()))
                        this.foundClient.add(client);
                }
            }
            for(int i=0; i< client.getPetName().length()-partName.length(); i++) {
                if(partName.length()< client.getNameClient().length()) {
                    if (client.getNameClient().substring(i, partName.length()).toLowerCase().equals(partName.toLowerCase()))
                        this.foundClient.add(client);
                }
            }

        }
        return this.foundClient;
    }
}
