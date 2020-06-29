package exercise421;

public class MagicClassInstantiatorTest {
    public static void main(String... args) throws Exception {
        MagicClassInstantiator
                .main(
                        "exercise421.MagicTestClass",
                        "howdie", "print");
        MagicClassInstantiator
                .main(
                        "exercise421.MagicTestClass",
                        "howdie", "there", "print");
        MagicClassInstantiator
                .main(
                        "exercise421.MagicTestClass",
                        "howdie", "there", "partner", "print");
        MagicClassInstantiator.main("java.lang.String", "pankaj agarwal",
                "toUpperCase");
        MagicClassInstantiator.main("java.util.ArrayList", "size");
        MagicClassInstantiator.main("java.util.ArrayList", "clear");
    }
}
