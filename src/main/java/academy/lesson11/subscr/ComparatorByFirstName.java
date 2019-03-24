package academy.lesson11.subscr;

import java.util.Comparator;

public class ComparatorByFirstName implements Comparator<Subscriber> {


    @Override
    public int compare(Subscriber o1, Subscriber o2) {

        return o1.getFirstName().compareTo(o2.getFirstName());



    }
}
