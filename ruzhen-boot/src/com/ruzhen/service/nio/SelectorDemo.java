package com.ruzhen.service.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {
    public void selector() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel sschannel = ServerSocketChannel.open();
        sschannel.configureBlocking(false); //设置为非阻塞方式
        sschannel.socket().bind(new InetSocketAddress(8080));
        sschannel.register(selector, SelectionKey.OP_ACCEPT); //注册监听的事件
        while(true){
            Set selectedKeys = selector.selectedKeys(); //获取所有key集合
            Iterator it = selectedKeys.iterator();
            while(it.hasNext()){
                SelectionKey key = (SelectionKey) it.next();
                if((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
                    ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = socketChannel.accept(); //接收到的服务端的请求
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                }else if((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
                    SocketChannel sc = (SocketChannel) key.channel();
                    while(true){
                        buffer.clear();
                        int n =sc.read(buffer); //读取数据
                        if(n <= 0){
                            break;
                        }
                        buffer.flip();
                        it.remove();
                    }
                }
            }
        }
    }
}
