package reflect;
//import statements?

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassgetMethodExample2 {
    
    public static void main(String... args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<ClassgetMethodExample2> class1 = ClassgetMethodExample2.class;
        
        Method mthd = class1.getMethod("IntCalc", int.class);
        System.out.println(mthd);
        
        mthd = class1.getMethod("Work");
        System.out.println(mthd);
        
        mthd = class1.getMethod("StaticMethod", String.class);
        System.out.println(mthd);
        Object invoke = mthd.invoke(class1.newInstance(), "33222");
        System.out.println(invoke.getClass().getName() + ", " + invoke);
        
        mthd = class1.getMethod("Work");
        System.out.println(mthd);
    }
    
    public int IntCalc(int i) {
        return 0;
    }
    
    public void Work() {
    }
    
    public static int StaticMethod(String s) {
        return 3333;
    }
}