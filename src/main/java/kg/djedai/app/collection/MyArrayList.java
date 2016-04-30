package kg.djedai.app.collection;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Zhoodar on 26.04.2016.
 * Implementation of ArrayList
 */
public class MyArrayList<E> implements MyList<E> {
    /**
     * Размер массив-списка
     */
    private int size;

    /**
     * Хранилище елементов
     */
    private Object[] elementData={};

    /**
     * Конструктор пустой список с размерю поумолчанию 10
     */
    public MyArrayList(){
        this.elementData = (E[]) new Object[10];
    }

    /**
     * Конструктор пустого списка с конкретной минимум емкости
     * @param capacity конкретный размер списка
     */
    public MyArrayList(int capacity){
        if(capacity>0)
            this.elementData = (E[]) new Object[capacity];
    }
    /**
     * Добавляет конкретного элемента в конце этого списка
     * @param value элемент который должен добалятся в этот список
     */
    public void add(E value){
        ensureCapacity(size+1);
        this.elementData[size++]=value;
    }

    /**
     * Вставляет конкретного значения в конкретную позицию в этого списка.
     * @param index канкетрная позиция где должно всталятся конкертный элемент
     * @param value элемент которой должно добавится
     */
    public void add(int index, E value){
        ensureCapacity(size+1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    /**
     * Увеличивает емкость списка, если необходима, чтобы
     * уверять что этот список будет хранит минимум чисел
     * элементов утачненый в аргументе minCapacity
     * @param minCapaсity желанный минимум емкость
     */
    public void ensureCapacity(int minCapaсity){
        Object[] oldData={};
        int oldCapacity=elementData.length;
        int newCapacity = (oldCapacity * 3) / 2 + 1;
        if(oldCapacity<minCapaсity) {
            this.elementData=oldData;
            this.elementData= (E[]) new Object[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, size);
        }
    }

    /**
     * Удаляет елемент в конкретном позиции в листе
     * @param index позция элемента которго должен удалится
     */
    public void remove(int index){
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
    }

    /**
     * Удаляет первый найденный элемент  из этого списка
     * @param value элемент который должно удалится из списка
     * @return true если эсть такой элемент в списке
     */
    public boolean remove(E value){
        for (int index = 0; index < size; index++)
            if (value.equals(elementData[index])) {
                remove(index);
                return true;
            }
        return false;
    }

    /**
     * Возрврашет текуший размер листа
     * @return размер листа
     */
    public int size(){
        return this.size;
    }

    /**
     * Возврашает элемент из листа с конкретной позиции
     * @param index позиция
     * @return элемент из листа с конкретной позиции
     */
    public E get(int index){
        return (E)this.elementData[index];
    }

    /**
     * Заменяеть элемент в конкретной позиции в листе
     * @param index индекс заменяюшего элемента
     * @param element элемент заменител
     * @return
     */
    public boolean set(int index, E element) {
        if(index<size) {
            elementData[index] = element;
            return true;
        }
        return false;
    }

    /**
     * возраешеть элемент в позиции
     * @param index позиция элемета
     * @return элемент
     */
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * Возврашает iterator над данным листом
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }

            @Override
            public void remove() {

            }

            @Override
            public void forEachRemaining(Consumer<? super E> action) {

            }
        };
    }


}
