package Interface;

import java.util.Collection;
import java.util.Comparator;

public interface MyList<E> {
    E get(int index);
    void add(E element, int index);
    void add(E element);
    void addAll(Collection<E> collection);
    void addAll(Collection<E> collection, int index);
    boolean remove(Object element);
    E remove(int index);
    boolean removeAll(Collection<?> collection);
    int size();
    void sort( Comparator<? super E> comparator);
    static <T> void sort(MyList<T> list, Comparator<? super T> comparator){
        list.sort(comparator);
    }
}
