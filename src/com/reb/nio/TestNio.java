package com.reb.nio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by rebby on 2017/5/2.
 */
@SuppressWarnings("all")
public class TestNio {

    private static int index = 0;
    private static String [] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly", // Sorry Jimi ;-) "Help Me! Help Me!",
    };
    public static void main(String[] args) throws UnsupportedEncodingException {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte)'A').put((byte)'B').put((byte)'C');
        buffer.clear();
        buffer.put((byte)'1').put((byte)'2').put((byte)'3').put((byte)'4');
        buffer.put((byte)'5').put((byte)'6').put((byte)'7');
        buffer.put((byte)'5');
        buffer.put((byte)'5');
        buffer.put((byte)'5');
        System.out.println(new String(new byte[]{buffer.get(0)}));
       // System.out.println(new String(new byte[]{buffer.get(1)}));
       //buffer.flip();
        //容量 10 上界 3 mark 1 position 1
        System.out.println("limit: " + buffer.limit());
        System.out.println("mark:" + buffer.mark());
        System.out.println("position:" + buffer.position());
        System.out.println("capacity:" + buffer.capacity());

    }

    private static void drainBuffer(CharBuffer buffer){
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
        System.out.println(" ");
    }
    private static boolean fillBuffer (CharBuffer buffer)
    {
        if (index >= strings.length) {
            return (false);
        }
        String string = strings [index++];
        for (int i = 0; i < string.length( ); i++) {
            buffer.put (string.charAt (i));
        }
        return (true);
    }

}
