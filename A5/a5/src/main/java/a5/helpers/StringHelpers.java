package a5.helpers;

import a5.model.ADT.interfaces.IIterable;
import a5.model.ADT.interfaces.IIterator;

public final class StringHelpers 
{
    private StringHelpers() { }
     
    public static String Repeat(char c, int times) { return new String(new char[times]).replace('\0', c); }

    public static boolean IsNullOrEmpty(String s) { return s == null || s.equals(""); }

    public static String ToHex(int x) { return "0x" + String.format("%08X", x).replaceFirst("^(00)+(?=[0-9A-F])", ""); }

    public static <T extends Object> String Join(String separator, IIterable<T> collection)
    {
        String result = "";
        IIterator<T> i = collection.GetIterator();

        i.First();
        while (i.Valid())
        {
            result += i.Current().toString();
            i.Next();
            
            if (i.Valid()) result += separator;
        }

        return result;
    }
}