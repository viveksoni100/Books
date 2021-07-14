package modernjavainaction.chap03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author viveksoni100
 */
public class MethodRef {

    public static void main(String[] args) {
        MyName myName = MethodRef::printMyName;
        System.out.println(myName.say());

        System.out.println("***************************");
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort(String::compareToIgnoreCase);
        System.out.println(str.toString());

        System.out.println("**************************************");
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        System.out.println(c);

    }

    public static String printMyName() {
        return "Hello Vivek!";
    }

    interface MyName {
        String say();
    }
}
