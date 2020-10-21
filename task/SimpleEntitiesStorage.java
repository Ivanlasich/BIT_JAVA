package task;

import java.util.*;


public class SimpleEntitiesStorage<K, T> implements BankEntitiesStorage {
    private final Map<K, T> storage = new HashMap<K, T>();
    private final KeyExtractor keyExtractor;

    public SimpleEntitiesStorage(KeyExtractor keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    @Override
    public void save(Object entity) {
        K key = (K) keyExtractor.extract(entity);
        storage.put(key, (T) entity);
    }

    @Override
    public void saveAll(List entities) {
        for(T entity : (List<T>) entities) {
            K key = (K) keyExtractor.extract(entity);
            storage.put(key, (T) entity);
        }
    }

    @Override
    public Object findByKey(Object key) {
        return storage.get(key);
    }

    @Override
    public List findAll() {
        List list = new ArrayList();
        for(T elem : storage.values()){
            list.add(elem);
        }
        return list;
    }

    @Override
    public void deleteByKey(Object key) {
        storage.remove(key);
    }

    @Override
    public void deleteAll(List entities) {
        for(Object key : entities){
            deleteByKey(key);
        }
    }

    public Set uniqueKeysOf(){
        return storage.keySet();
    }
}
