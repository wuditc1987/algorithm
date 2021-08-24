package print;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/14
 * @description TODO
 */
public class ClassLoadTest {
    Person person = new Person("ClassLoadTest");

    static {
        System.out.println("ClassLoadTest static");
    }

    public ClassLoadTest(){
        System.out.println("ClassLoadTest constructor");
    }

    public static void main(String[] args) {
        new MyClass();
    }
}

class Person{
    static {
        System.out.println("person static");
    }

    public Person(String s){
        System.out.println("person " + s);
    }
}

class MyClass extends ClassLoadTest {
    Person person = new Person("MyClass");
    static {
        System.out.println("MyClass static");
    }

    public MyClass(){
        System.out.println("MyClass constructor");
    }
}