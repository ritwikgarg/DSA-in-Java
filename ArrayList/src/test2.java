import javax.sound.midi.Soundbank;

public class test2 {
    void doSomething() {
        System.out.println("Parent class");
    }
}

class test1 extends test2 {
    @Override
    void doSomething() {
        super.doSomething();
        System.out.println("Child class");
    }
}

class OverridingTest {
    public static void main(String[] args) {
        test1 obj = new test1();
        obj.doSomething();
    }
}