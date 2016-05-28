package kg.djedai.app.collection;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MyLinkedListTest extends TestCase {

    public void testAdd() throws Exception {
        MyLinkedList list= new MyLinkedList();
        list.add("a");
        list.add("b");
        list.add(2,"c");
        list.add("b");
        list.add("b");
        list.toPrintOut();

    }
}