package com.reb.nio;

import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * http://blog.csdn.net/shimiso/article/details/24990499
 * http://www.infoq.com/cn/news/2014/12/external-memory-heap-memory
 * 使用 select( )  多个通道
 * Created by rebby on 2017/5/8.
 */
@SuppressWarnings("all")
public class SelectSockets {

    public static int PORT_NUMBER = 1234 ;

    public static void main(String[] args) throws IOException {
        new SelectSockets().go(args);
    }


    public void go(String[] argv) throws IOException {

        int port = PORT_NUMBER;

        if(argv.length > 0){ // Override default listen port
            port = Integer.parseInt(argv[0]);
        }

        System.out.println("Listening on port " + port);
        //分配未绑定的服务器套接字通道
        // Allocate an unbound server socket channel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        //获取关联的ServerSocket与其绑定
        // Get the associated ServerSocket to bind it with
        ServerSocket serverSocket = serverChannel.socket();

        //在下面创建一个新的选择器
        // Create a new Selector for use below
        Selector selector = Selector.open();

        //设置服务器通道 并且监听其端口
        // Set the port the server channel will listen to
        serverSocket.bind(new InetSocketAddress(port));

        //设置socket监听 为非阻塞模式
        // Set nonblocking mode for the listening socket
        serverChannel.configureBlocking(false);

        //使用 Selector 来注册 ServerSocketChannel
        // Register the ServerSocketChannel with the Selector
        serverChannel.register(selector,SelectionKey.OP_ACCEPT);

        for (;;){
            //这可能会阻塞很长时间。 返回时，所选集合包含 就绪 通道的键。
            // This may block for a long time. Upon returning, the
           // selected set contains keys of the ready channels.
            int n = selector.select();

            if(n == 0 ){
                continue; // nothing to do
            }
            //在所选择的 键集 上获取迭代器
            // Get an iterator over the set of selected keys
            Iterator it = selector.selectedKeys().iterator();

            //查看所选集中的每个键
            // Look at each key in the selected set
            while (it.hasNext()){
                SelectionKey key = (SelectionKey) it.next();

                //一个新的连接进来了吗？
                // Is a new connection coming in?
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector,channel,SelectionKey.OP_READ);
                    sayHello(channel);
                }
                //查看  是否有数据
                // Is there data to read on this channel?
                if(key.isReadable()){
                    readDataFromSocket(key);
                }
                // 从选定的集合中删除密钥; 它已被处理
                // Remove key from selected set; it's been handled
                it.remove();
            }
        }
    }

    protected void registerChannel(Selector selector, SelectableChannel channel ,int opt ) throws IOException {

        if (channel == null ){
            return ; //could happen
        }
        //Set the new channel nonblocking
        channel.configureBlocking(false);

        //Register it with the selector
        channel.register(selector, opt);
    }

    // ----------------------------------------------------------
    // Use the same byte buffer for all channels. A single thread is
    // servicing all the channels, so no danger of concurrent acccess.
    private ByteBuffer buffer = ByteBuffer.allocate(1024) ;

    /**
     * Sample data handler method for a channel with data ready to read.
     *
     * @param key
     *          A SelectionKey object associated with a channel determined by
     *          the selector to be ready for reading. If the channel returns
    141
    } }
    buffer.put("Hi there!\r\n".getBytes());
    buffer.flip();
    channel.write(buffer);
     *          an EOF condition, it is closed here, which automatically
     *          invalidates the associated key. The selector will then
     *          de-register the channel on the next select call.
     */
    protected void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count ;


        //Clear()函数将缓冲区重置 为空状态。它并不改变缓冲区中的任何数据元素，而是仅仅将上界设为容量的值，并把位置(position)设置为：0
        //那么旧数据会不会占用缓冲位置？答案是 会！但是不会影响新数据写入 。当有新数写入缓冲区 就会清除 旧 数据。直到数据到达容量位置(capacity)。
        buffer.clear();

        // Loop while data is available; channel is nonblocking
        while((count = socketChannel.read(buffer)) > 0){
            buffer.flip(); // Make buffer readable
            // Send the data; don't assume it goes all at once
            while (buffer.hasRemaining()){
                socketChannel.write(buffer);
            }
            // WARNING: the above loop is evil. Because
            // it's writing back to the same nonblocking
            // channel it read the data from, this code can
            // potentially spin in a busy loop. In real life
            // you'd do something more useful than this.

            buffer.clear(); // Empty buffer

            if(count < 0){
                //Close channel on EOF, invalidates the key
                socketChannel.close();
            }
        }

    }
    /**
     * Spew a greeting to the incoming client connection.
     *
     * @param channel
     *          The newly connected SocketChannel to say hello to.
     */
    private void sayHello (SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }
}
