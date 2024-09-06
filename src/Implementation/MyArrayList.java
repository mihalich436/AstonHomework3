package Implementation;

import Interface.MyList;

import java.util.*;

public class MyArrayList<E> implements MyList<E> {
    private static final int defaultInitialCapacity = 10;
    private static final int enlargeScale = 2;
    private static final Object[] empty = {};

    private Object[] elements;
    private int capacity;
    private int size;

    public MyArrayList() {
        elements = empty;
    }

    public MyArrayList(int initialCapacity) {
        if(initialCapacity > 0){
            this.capacity = initialCapacity;
            elements = new Object[capacity];
        }
        else if (initialCapacity == 0) elements = empty;
        else throw new IllegalArgumentException("Negative capacity: " + initialCapacity);
    }

    public MyArrayList(Collection<? extends E> collection){
        if(collection.isEmpty()) elements = empty;
        else{
            elements = Arrays.copyOf(collection.toArray(), collection.size(), Object[].class);
            capacity = collection.size();
            size = collection.size();
        }
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size());
        return (E) elements[index];
    }

    private void enlarge(int minCapacity){
        if(capacity == 0 && minCapacity < defaultInitialCapacity) {
            elements = new Object[defaultInitialCapacity];
            capacity = defaultInitialCapacity;
            return;
        }
        int newCapacity = Math.max(minCapacity, capacity * enlargeScale);
        elements = Arrays.copyOf(elements, newCapacity);
        capacity = newCapacity;
    }

    private void prepareToAdd(int index, int addSize){
        Objects.checkIndex(index, size() + addSize);
        if(size() + addSize >= capacity) enlarge(size() + addSize);
        if(index < size()) System.arraycopy(elements, index, elements, index + addSize, size() - index);
        size += addSize;
    }

    @Override
    public void add(E element, int index) {
        prepareToAdd(index, 1);
        elements[index] = element;
    }

    @Override
    public void add(E element) {
        add(element, size());
    }

    @Override
    public void addAll(Collection<E> collection) {
        addAll(collection, size());
    }

    @Override
    public void addAll(Collection<E> collection, int index) {
        if(collection.isEmpty()) return;
        prepareToAdd(index, collection.size());
        Object[] a = collection.toArray();
        System.arraycopy(a, 0, elements, index, collection.size());
    }

    private void remove(int index, int removeSize){
        System.arraycopy(elements, index + removeSize, elements, index, size() - removeSize-index);
        size -= removeSize;
        elements[size()] = null;
    }

    @Override
    public boolean remove(Object element) {
        if(element == null) return false;
        return removeAll(List.of(element));
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size());
        E element = (E) elements[index];
        remove(index, 1);
        return element;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) return false;
        int deletedNum = 0;
        for (int i=0; i<size(); i++){
            elements[i - deletedNum] = elements[i];
            if (collection.contains(elements[i])) deletedNum++;
        }
        size -= deletedNum;
        for (int i=size(); i<size()+deletedNum; i++){
            elements[i] = null;
        }
        return deletedNum != 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        E buf;
        boolean sorted;
        for (int i=size()-1; i>0; i--){
            sorted = true;
            for (int j=0; j<i; j++){
                if (comparator.compare(get(j), get(j+1)) > 0){
                    sorted = false;
                    buf = get(j);
                    elements[j] = elements[j+1];
                    elements[j+1] = buf;
                }
            }
            if (sorted) break;
        }
    }

    @Override
    public String toString() {
        if (size == 0) return "MyArrayList{}";
        StringBuilder stringBuilder = new StringBuilder("MyArrayList{");
        for (int i = 0; i < size - 1; i++){
            stringBuilder.append(elements[i].toString());
            stringBuilder.append(", ");
        }
        stringBuilder.append(elements[size - 1]);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
