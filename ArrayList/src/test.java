import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test {

    public static void main(String args[]) {
        Integer i=5;
        modifyInteger(i);
        System.out.println("value of i outside function:" + i);

    }

    public static void modifyInteger(Integer i) {
        final List<String> myList = Collections.unmodifiableList(new ArrayList<>());
        i = i+5;
        System.out.println("value of i inside function:" + i);
    }
}
