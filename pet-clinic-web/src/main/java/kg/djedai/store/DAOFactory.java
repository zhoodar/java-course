package kg.djedai.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhoodar
 * @since 29.06.2016.
 */
@Service
public class DAOFactory {

    public final Storage hibernateStorage;

    public final UserDAO userStorage;

    public final RoleDAO roleStorage;

    public final DAO messageStorage;

    @Autowired
    public DAOFactory(final Storage hibernateStorage, final UserDAO userStorage, final RoleDAO roleStorage,final DAO messageStorage) {
        this.hibernateStorage = hibernateStorage;
        this.userStorage = userStorage;
        this.roleStorage = roleStorage;
        this.messageStorage = messageStorage;
    }
}
