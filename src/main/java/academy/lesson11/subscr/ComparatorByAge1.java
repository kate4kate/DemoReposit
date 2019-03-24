package academy.lesson11.subscr;

import java.util.Comparator;

public class ComparatorByAge1 implements Comparator<Subscriber> {

    @Override
    public int compare(Subscriber o1, Subscriber o2) {
       if (o1.getAge() < o2.getAge())
           return -1;
        if (o2.getAge() < o1.getAge())
            return 1;

        // if (o2.getAge() == o1.getAge())
        return 0;
    }


}
