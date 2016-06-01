package kg.djedai.store;

import kg.djedai.app.clinic.Client;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализация паттерна Сингелтон для webapp
 * @author Zhoodar
 * @since 01.06.2016.
 */
public class ClientCashe {
    private static final ClientCashe INSTANCE = new ClientCashe();

    private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<Integer, Client>();

    public static ClientCashe getIstance(){
        return INSTANCE;
    }

    public Collection<Client> values(){
        return this.clients.values();
    }
    public void addClient(final int id, final Client client){
        this.clients.put(id,client);
    }
    public void deleteClient(final int id){
        this.clients.remove(id);
    }
    public void editClient(final int id,final Client client){
        this.clients.replace(id, client);
    }

    public Client get(final int id){
        return this.clients.get(id);
    }
}
