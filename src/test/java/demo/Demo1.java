package demo;

public class Demo1 {

    public static void main(String[] args) {
        String name = "刘阿勇";
        String s1 = String.format("%-10s", name).replace(' ', '_');
        System.out.println(s1);
        System.out.println(s1.length());
        System.out.println(String.format("%-10s", name).length());


    }
}
