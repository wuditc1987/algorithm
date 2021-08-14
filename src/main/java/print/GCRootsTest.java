package print;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/7
 * @description TODO
 */
public class GCRootsTest {
    //GCRoots 方法区中的静态变量
    public static Integer staticVar;

    //GCRoots 方法区中的常量
    public final Integer finalVar = 1000;

    //GCRoots JNI
    public native void gcTest();

    public static void main(String[] args) {
        //GCRoots  虚拟机栈中本地变量表的值
        GCRootsTest t = new GCRootsTest();//① Yes
        Integer b = t.finalVar;//② No
        GCRootsTest.staticVar = 1;//③ No
        Integer c = GCRootsTest.staticVar;//④ No
        //GC时哪些对象会被回收?
        t = null;
        try {
            System.gc();
            Thread.sleep(10000);
        }catch (Exception e){

        }

        System.out.println(b + " : " + c);
    }

}
