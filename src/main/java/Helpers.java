import java.util.List;
import java.util.ArrayList;

public class Helpers {
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T> List<T> difference(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>(list1.size());
        list.addAll(list1);
        list.removeAll(list2);
        return list;
    }
}
