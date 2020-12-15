package main.java.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


public class SimpleEntitiesStorage<K,V> implements BankEntitiesStorage<K, V> {
    private final Map<K,V> storage = new HashMap<K,V>();
    private final KeyExtractor<? super K, ? super V>  keyExtractor;

    public SimpleEntitiesStorage(KeyExtractor<? super K, ? super V> keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    @Override
    public <V1 extends V>  void save(V1 entity) {
        K key = keyExtractor.extract(entity);
        storage.put(key, entity);
    }

    @Override
    public void saveAll(List<? extends V> entities) {
        for(V entity : entities) {
            K key = keyExtractor.extract(entity);
            storage.put(key, entity);
        }
    }

    @Override
    public List<? super V> findAll() {
        List<? super V> list = new ArrayList();

        for(V elem : storage.values()){
            list.add(elem);
        }
        return list;
    }

    @Override
    public void deleteByKey(K key) {
        storage.remove(key);
    }

    @Override
    public void deleteAll(List<? extends V> entities) {
        storage.values().removeAll(entities);
    }

    @Override
        public <V1 extends V> V1 findByKey(K key) {
        return (V1) storage.get(key);
    }
}
