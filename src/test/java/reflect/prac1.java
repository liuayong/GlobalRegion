package reflect;

import java.lang.reflect.*;

class Office {
    public String OfficeLocation() {
        return location;
    }
    
    public String getEmpName(Integer eid) {
        return "Sergio";
    }
    
    String location = "Bangalore";
}

public class prac1 {
    public static void main(String[] args) {
        Office ofc = new Office();
        Class cObj = ofc.getClass();
        Class[] carr = new Class[1];
        carr[0] = Integer.class;
        try {
            Method meth = cObj.getMethod("OfficeLocation", null);
            System.out.println("Method with specified name is = " + meth.toString());
        } catch (NoSuchMethodException e) {
            System.out.println(e.toString());
        }
        try {
            Method meth = cObj.getMethod("getEmpName", carr);
            System.out.println("Method with specified name is = " + meth.toString());
        } catch (NoSuchMethodException e) {
            System.out.println(e.toString());
        }
        
        try {
            Method meth = cObj.getMethod(null, carr);
            System.out.println("Method found" + meth.toString());
        } catch (NoSuchMethodException e) {
            System.out.println(e.toString());
        } catch (NullPointerException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}