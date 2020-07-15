package playground.concurrentmodificationexception;

public class Cat {
    private final int catNumber;

    public Cat(int catNumber) {
        this.catNumber = catNumber;
    }

    @Override
    public String toString() {
        return "Cat is numbered " + catNumber;
    }

    public void meow() {
        System.out.println("Cat with number " + catNumber + " is making meow sound");
    }
}
