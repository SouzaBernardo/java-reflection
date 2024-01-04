package reflection;

public class Dog {

    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    public Dog() {
        name = "Big Dog";
    }

    public String sayHi() {
        return "Hi, my name is " + name;
    }

    public String sayHiTo(String toSay) {
        return String.format("Hi %s, my name is %s", toSay, name);
    }

    @Override
    public String toString() {
        return this.sayHi();
    }
}
