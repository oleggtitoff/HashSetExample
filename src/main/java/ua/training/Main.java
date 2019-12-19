package ua.training;

import ua.training.model.entity.SimpleHashSet;
import ua.training.model.entity.SimpleSet;

public class Main {

    public static void main(String[] args) {
        SimpleSet<String> set = new SimpleHashSet<>(128);

        set.add("first");
        set.add("second");
        set.add("third");

        System.out.println(set.size());
        set.iterator().forEachRemaining(System.out::println);

        set.remove("second");

        System.out.println(set.size());
        set.iterator().forEachRemaining(System.out::println);

        System.out.println(set.contains("third"));
        System.out.println(set.contains("second"));
    }

}
