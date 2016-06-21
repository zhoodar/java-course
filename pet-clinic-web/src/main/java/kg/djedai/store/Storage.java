package kg.djedai.store;

import kg.djedai.app.clinic.Pet;
import kg.djedai.models.ClientModel;

import java.util.Collection;
import java.util.List;

/**
 * @author Zhoodar
 * @since 10.06.2016.
 */
public interface Storage {

    /**
     * метод создаеть Collection на основе Generics из модела ClientModel
     * @return Collection
     */
    public Collection<ClientModel> getClients();

    /**
     * Добавляет нового объекта client к месту сохранение
     * @param client добавляемый объект
     */
    public void addClient(final ClientModel client);

    /**
     *Редактирует конкретного объекта client
     * @param client рекдактируемый объект
     */
    public void editClient(final ClientModel client);

    /**
     * Удаляет объекта client из место сохранение
     * @param id удаляемого объекта client
     */
    public void deleteClient(final String id);

    /**
     * Получает конкретного совподающим с id объекта из место хранение
     * @param id объекта client
     * @return получаемый ClientModel
     */
    public ClientModel get(final String id);

    /**
     *
     */
    public ClientModel getLastClient();

    /**
     *
     * @param clientName
     * @return
     */
    public List<ClientModel> findByFullName(final String clientName) ;

    /**
     *
     * @param partName
     * @return
     */
    public List<ClientModel> findByContain(final String partName);

    /**
     *
     * @return
     */
    public String generateId();

    /**
     *
     */
    public void close();


    /**
     *
     * @param type
     * @param namePet
     */
    public void addPetToClient(int type, String namePet, String idClient);

    /**
     *
     * @param idCurrentClient
     */
    public List<Pet> getPetCurrentClient(String idCurrentClient);

    /**
     *
     * @param idCurrentClient
     */
    public void deletePetCurrentClient(String idCurrentClient, String petName);
}