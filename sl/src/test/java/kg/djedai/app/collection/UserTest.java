package kg.djedai.app.collection;

import static org.junit.Assert.*;

public class UserTest {
    public static void main(String[] args) {

        int i = 0;
        do {
            if (i > 5) i--;
            if (i < 4) i++;
            if (i == 5) i+=2;
            i++;
            System.out.print(i);
        } while (i <= 5);

              }
}