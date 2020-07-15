package playground;

public class Giorgos {
    public static void main(String[] args) {
        Sea creatan_sea = new Sea();

        for(Octopus octopus:creatan_sea)
        {
            System.out.println(octopus);
        }

        creatan_sea.forEach(System.out::println);// Java 8
    }

}
