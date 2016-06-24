package kg.djedai.store;

import kg.djedai.app.clinic.Animal;
import kg.djedai.app.clinic.Cat;
import kg.djedai.app.clinic.Dog;
import kg.djedai.app.clinic.Pet;
import kg.djedai.models.ClientModel;
import kg.djedai.service.Settings;

import java.sql.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Реализация интерфейса Storage изпользуя JDBC
 * @author Zhoodar
 * @since 10.06.2016.
 */
public class JdbcStorage implements Storage {

    private final Connection connection;

    private final List<ClientModel> foundClients = new CopyOnWriteArrayList<ClientModel>();

    public JdbcStorage() {
        final Settings settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Collection<ClientModel> getClients() {
        final List<ClientModel> clients = new CopyOnWriteArrayList<ClientModel>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM client")) {
            while (rs.next()) {
                clients.add(new ClientModel(rs.getString("index"),rs.getString("fullname")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fillClientWithPet(clients);
    }

    @Override
    public void addClient(ClientModel client) {
        try (final PreparedStatement st = this.connection.prepareStatement("INSERT INTO client (fullname , index) VALUES (?, ?)")) {
            st.setString(1, client.getNameClient());
            st.setString(2, client.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editClient(ClientModel client) {
        try(final PreparedStatement statement = this.connection.prepareStatement("UPDATE client SET fullname= ? WHERE index = ?")) {
            statement.setString(1,client.getNameClient());
            statement.setString(2,client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteClient(String id) {
        try(final PreparedStatement statement = this.connection.prepareStatement("DELETE FROM client WHERE index = ?")) {
            statement.setString(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientModel getClientById(String id) {
        try( final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM client WHERE  client.index=(?)")) {
            statement.setString(1,id);
            try(final ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    return new ClientModel(rs.getString("index"),rs.getString("fullname"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ClientModel getLastClient() {
        LinkedList<ClientModel> list = new LinkedList<>();
        list.addAll(getClients());
        return list.getLast();
    }

    @Override
    public List<ClientModel> findByFullName(String clientName) {
        this.foundClients.clear();
        for (ClientModel client : getClients()) {
            if (client.getNameClient().toLowerCase().equals(clientName.toLowerCase())) {
                this.foundClients.add(client);
            }
            for(Pet pet: client.getPets()){
                if(pet.getName().toLowerCase().equals(clientName.toLowerCase())){
                    this.foundClients.add(client);
                }
            }
        }
        return this.foundClients;
    }

    @Override
    public List<ClientModel> findByContain(String partName) {
        this.foundClients.clear();
        for (ClientModel clt : getClients()) {
            for(Pet pet: clt.getPets()){
                if(pet.getName().toLowerCase().indexOf(partName.toLowerCase())!=-1){
                    this.foundClients.add(clt);
                }
            }
            if(clt.getNameClient().toLowerCase().indexOf(partName.toLowerCase())!=-1) {
                this.foundClients.add(clt);
            }
        }
        return this.foundClients;
    }


    @Override
    public String generateId() {
        return RandomIdGenerator.getBase36(6);
    }


    public Collection<ClientModel> fillClientWithPet(List<ClientModel> toFill) {
        String id;
        for(ClientModel client : toFill){
            id = client.getId();
            for(Pet pet : getPetCurrentClient(id)) {
                client.setPets(pet);
            }
        }
        return toFill;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPetToClient(int type, String petName, String idClient) {
        try (final PreparedStatement statement = this.connection.prepareStatement("INSERT INTO pet (client_id , pet_type, nick ) VALUES (?, ?, ?)")) {
            statement.setString(1, idClient);
            statement.setInt(2, type);
            statement.setString(3, petName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pet> getPetCurrentClient(String idCurrentClient) {
        List<Pet> pets = new CopyOnWriteArrayList<>();
      try (final PreparedStatement st = this.connection.prepareStatement("SELECT * FROM pet WHERE client_id = ?")) {
          st.setString(1,idCurrentClient);
          try(final ResultSet rs = st.executeQuery()){
              while (rs.next()){
                  if(rs.getInt("pet_type") == 2){
                      pets.add(new Cat(rs.getString("nick")));
                  } else {
                      pets.add(new Dog(new Animal(rs.getString("nick"))));
                  }
              }
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
        return pets;
    }

    @Override
    public void deletePetCurrentClient(String idCurrentClient, String namePet) {
        try(final PreparedStatement st = this.connection.prepareStatement("DELETE FROM pet WHERE nick= ? AND client_id= ?")) {
            st.setString(1, namePet);
            st.setString(2,idCurrentClient);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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