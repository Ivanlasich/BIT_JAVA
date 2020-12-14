package main.java.store;
import java.util.List;

public interface BankEntitiesStorage <K,V>{
    <V1 extends V> void save(V1 entity); //producer <? extends V>

    void saveAll(List<? extends V> entities); //producer

    <V1 extends V> V1 findByKey(K key);

    List<? super V> findAll(); //consumer

    void deleteByKey(K key);

    void deleteAll(List<? extends V> entities);
}
