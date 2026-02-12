package pe.com.todobaratito.util;

import java.io.UnsupportedEncodingException;

public class StringManager {
    //creamos un metodo para poder controlar el UTF-8
    public static String convertUTF8(String str) throws UnsupportedEncodingException{
        return new String(str.getBytes("ISO-8859-1"),"UTF-8");
    }

}

