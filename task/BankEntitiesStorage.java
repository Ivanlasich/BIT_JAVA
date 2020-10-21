package task;

import java.util.List;

public interface BankEntitiesStorage {
    void save(Object entity);

    void saveAll(List entities);

    Object findByKey(Object key);

    List findAll();

    void deleteByKey(Object key);

    void deleteAll(List entities);
}
