package kg.djedai.store;

import kg.djedai.app.clinic.Pet;
import kg.djedai.models.ClientModel;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс для удобного изменение место обработки данных
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
    public ClientModel getClientById(final String id);

    /**
     *  Получить послендый добпаленого клиента
     * @return Обеък клиент
     */
    public ClientModel getLastClient();

    /**
     *  Найти клиента веденной
     * @param clientName полная имя
     * @return список найденных клиентов
     */
    public List findByFullName(final String clientName) ;

    /**
     * Найти клиента по части
     * @param partName часть искаемого слово
     * @return список найденных клиентов
     */
    public List<ClientModel> findByContain(final String partName);

    /**
     * Генерирует уникалный ID для всех клиентов
     * @return
     */
    public String generateId();

    /**
     * Закрывет сессию connection
     */
    public void close();


    /**
     * добавить нового животного к клиенту
     * @param type животного
     * @param namePet имя животного
     * @param idClient клиента
     */
    public void addPetToClient(int type, String namePet, String idClient);

    /**
     * получить всех животных клиента с введеный id
     * @param idCurrentClient
     */
    public List<Pet> getPetCurrentClient(String idCurrentClient);

    /**
     * удоляет животного из клиента
     * @param idCurrentClient клиента
     * @param petName имя удоляемого животного
     */
    public void deletePetCurrentClient(String idCurrentClient, String petName);
}