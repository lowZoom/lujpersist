package luj.persist.internal.data.object;

@FunctionalInterface
public interface DataFactory<T> {

  T create();
}
