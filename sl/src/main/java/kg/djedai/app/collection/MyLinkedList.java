package kg.djedai.app.collection;

/**
 * Implementation of LinkedList
 * @author Zhoodar on 26.04.2016.
 */
public class MyLinkedList<E> {

    private int size;

    Node<E> head;
    Node<E> tail;

    public MyLinkedList(){}

    public void add(E value) {
        Node<E> node = new Node<E>(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.nextNode = node;
            tail = node;
        }
        size++;
    }

    public void add(int index , E value){

        Node<E> newNode = new Node<E>(value);
        Node<E> current = head;
        if(index==0) {
            newNode.setNextNode(current.nextNode);
            head=newNode;
        }
        for (int i = 1; i < index ; ++i) {
            current = current.nextNode;
        }
        newNode.setNextNode(current.nextNode);
        current.nextNode = newNode;
        size++;
    }

    public void delete(E value) {
        if (size == 0)
            return;
        Node<E> current = head;
        if (value.equals(current.data)){
            unlinkFirst(current);
        } else {
            for (Node<E> x = head; x != null; x = x.nextNode) {
                if (value.equals(x.data)) {
                   current=current.nextNode;
                }
            }
            current.setNextNode(current.nextNode.nextNode);
        }
        size--;
    }

    private void unlinkFirst(Node<E> f) {
        final Node<E> next = f.nextNode;
        f.data = null;
        f.nextNode = null;
        head = next;
        if (next == null)
            tail = null;
        else
            next.prevNode = null;
    }

    public int size(){
        return this.size;
    }

    public E get(int index){
        if(index<0)
            return null;
        Node<E> current = head;
        for(int i=1; i<=index; i++){
            if(current.nextNode==null)
                return null;
            current=current.nextNode;
        }
        return current.data;
    }

    public void toPrintOut(){
        if(head!=null){
            Node<E> node = head;
            System.out.println(node);
            while(node.nextNode != null){
                node = node.nextNode;
                System.out.println(node);
            }
        }
    }


    private static class Node<E> {

        E data;
        Node<E> nextNode;
        Node<E> prevNode;

        public Node(E data){
            this.data=data;

        }
        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }


        @Override
        public String toString() {
            return "" + data;
        }
    }

}
