import java.util.*;
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

abstract class C1
{
    public C1()
    {
        System.out.print(1);
    }
}

class C2 extends C1  
{
    public C2()
    {
        System.out.print(2);
    }
}

class C3 extends C2
{
    public C3()
    {
        System.out.print(3);
    }
}

public class Test
{
    public static void main(String args[])
    {
        new C3();
    }
}
