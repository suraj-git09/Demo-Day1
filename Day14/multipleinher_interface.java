package Day14;

interface Animal {
    default void sound() {
        System.out.println("Animal makes a sound.");
    }
}

interface Pet {
    default void sound() {
        System.out.println("Pet makes a sound.");
    }
}

class Dog implements Animal, Pet {
    @Override
    public void sound() {
        // Resolving conflict by providing own implementation
        System.out.println("The dog barks.");
    }
}

public class multipleinher_interface {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound();
    }
}
