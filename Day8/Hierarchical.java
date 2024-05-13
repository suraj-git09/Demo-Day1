
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}


class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}


class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Hierarchical {
    public static void main(String[] args) {
        Animal animal1 = new Dog(); 
        Animal animal2 = new Cat(); 

        animal1.sound(); 
        animal2.sound(); 
    }
}
