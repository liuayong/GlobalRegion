package reflect;

public class Test {
    
    public void func() {
    }
    
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException {
        
        // returns the Class object for this class
        Class myClass = Class.forName("reflect.Test");
        System.out.println(myClass == Test.class);
        System.out.println(Test.class.hashCode() + ", " + myClass.hashCode());
        
        System.out.println("Class represented by myClass: " + myClass.toString());
        
        String methodName = "func";
        Class[] parameterType = null;
        
        // Get the method of myClass
        // using getMethod() method
        System.out.println(methodName + " Method of myClass: " + myClass.getMethod(methodName));
        System.out.println(methodName + " Method of myClass: " + myClass.getMethod(methodName, parameterType));
    }
}
