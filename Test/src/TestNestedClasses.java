class NestedStaticClasses {

    static int staticMember = 1;

    int nonStaticMember = 2;
    static class NestedStaticClass{

        static int nestedStaticMember = 10;
        int nestedNonStaticMember = 100;

        static void display() {
            System.out.println("Hello from nested static class");
        }

        void displayStaticMemberValue() {
            System.out.println(nestedStaticMember);
        }
    }
}

public class TestNestedClasses{

    public static void main(String[] args) {
        NestedStaticClasses obj = new NestedStaticClasses();
        System.out.println("Static member of Parent class: "+NestedStaticClasses.staticMember);
        System.out.println("Non Static member of Parent class: "+obj.nonStaticMember);

        System.out.println("Static member of Nested static class: "+NestedStaticClasses.NestedStaticClass.nestedStaticMember);

        NestedStaticClasses.NestedStaticClass obj2 = new NestedStaticClasses.NestedStaticClass();

        System.out.println("Non Static member of Nested static class: "+obj2.nestedNonStaticMember);

        NestedStaticClasses.NestedStaticClass.display();

        //If we change the value of a static variable, every instance of the class will also have its value of the static variable be changed.

        NestedStaticClasses.NestedStaticClass.nestedStaticMember = 1000;

        NestedStaticClasses.NestedStaticClass obj3 = new NestedStaticClasses.NestedStaticClass();
        obj3.displayStaticMemberValue();
        obj2.displayStaticMemberValue();
    }
}

