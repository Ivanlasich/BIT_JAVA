package main.java.store;

public interface KeyExtractor<K, V> {
    <K1 extends K,V1 extends V> K1 extract(V1 entity); //producer
}
