package test;

@FunctionalInterface
public interface Test08 {

    String aaaa = "aaaa";

    default String getMyName(String name) {
        return "fang" + name;
    }

    String addAandB(String string);

    boolean equals(Object string);
}
