package luj.persist.data.object;

@FunctionalInterface
public interface DataFactory<T> {

  T create();
}
