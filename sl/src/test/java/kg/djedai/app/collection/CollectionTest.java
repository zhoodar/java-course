package kg.djedai.app.collection;


import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static junit.framework.Assert.*;


public class CollectionTest {

    @Test
    public void arrayListTest(){
        ArrayList<User> user = new ArrayList<User>();
        user.add(new User("Name",2));
        assertEquals(true, user.contains(new User("Name", 2)));
    }

    @Test
    public void hashSetTest(){
        HashSet<User> user= new HashSet<User>();
        user.add(new User("Name",3));
        user.add(new User("Na",5));
        user.add(new User("Nam",2));
        assertEquals(true,user.contains(new User("Nam",2)));
    }

    @Test
    public void hasMapTest(){
        HashMap<String,User> user = new HashMap<String, User>();
        user.put("Key",new User("Name",5));
        user.put("Kel",new User("Nam",4));

        assertEquals(true, user.containsKey("Key"));
    }
}
