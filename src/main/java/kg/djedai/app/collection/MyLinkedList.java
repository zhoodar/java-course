package kg.djedai.app.collection;

/**
 * Implementation of LinkedList
 * @author Zhoodar on 26.04.2016.
 */
public class MyLinkedList<E>{

    private int size=0;

    Entry<E> header = null;
    {
        header.next = header.prev = header;
    }



    public MyLinkedList(){

    }

    public void add(E value){
        Entry newEntry = new Entry(value, header, header.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
    }

    public void addFirst(E value){
        Entry newEntry = new Entry(value, header, header.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
    }

    public void addLast(E value){
        Entry newEntry = new Entry(value, header, header.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
    }

    public void add(int index , E value){
        Entry newEntry = new Entry(value, (index == size ? header : entry(index)), header.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;

    }

    private Entry<E> entry(int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);

        Entry<E> e = header;

        if (index < (size >> 1))
        {
            for (int i = 0; i <= index; i++)
                e = e.next;
        }
        else
        {
            for (int i = size; i > index; i--)
                e = e.prev;
        }

        return e;
    }

    private static class Entry<E>
    {
        E element;
        Entry<E> next;
        Entry<E> prev;

        Entry(E element, Entry<E> next, Entry<E> prev)
        {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public int size(){
        return this.size;
    }
}
