package algorithm;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LRU {

    public LRU(int size) {
        this.maxLength = size;
        this.length = 0;
    }

    Node head = null;
    int maxLength = 0;
    int length = 0;

    public String getCache(String key) {
        if (head == null) {
            head = new Node(key, getValue(key));
            return head.val;
        }
        Node node = head;
        Node preNode = null;
        Node prePreNode = null;

        while (node != null) {
            if (node.match(key)) {//命中缓存
                //不是头节点
                if (preNode != null) {
                    preNode.next = node.next;
                    node.next = head;
                    head = node;
                    return node.val;
                } else {
                    return node.val;
                }
            }
            prePreNode = preNode;
            preNode = node;
            node = node.next;
        }
        length++;


        //如果缓存太多，需要删除尾部节点。
        if (length >= maxLength) {
            prePreNode.next = null;
            length--;
        }
        Node newNode = new Node(key, getValue(key));
        newNode.next = head;
        head = newNode;

        return newNode.val;
    }

    public String getValue(String key) {
        System.out.println("getValue:" + key);
        return key + "value";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node node = head;
        while (node != null) {
            stringBuilder.append(node.key).append(":").append(node.val).append("\n");
            node = node.next;
        }
        return stringBuilder.toString();
    }


    public static class Node {
        Node(String key, String val) {
            this.key = key;
            this.val = val;
        }

        public boolean match(String matchKey) {
            if (key.equals(matchKey)) {
                return true;
            } else {
                return false;
            }
        }

        protected String key;
        protected String val;
        protected Node next;
    }


    public static void main(String[] args) {
        int cacheLenght = 5;

        LRU myLru = new LRU(cacheLenght);

        String val1 = myLru.getCache("key1");

        String val2 = myLru.getCache("key2");

        String val3 = myLru.getCache("key3");

        String val4 = myLru.getCache("key4");

        String val5 = myLru.getCache("key5");

        String val6 = myLru.getCache("key3");

        String val7 = myLru.getCache("key6");

        System.out.println(myLru.toString());
        System.out.println(val1 + val2 + val3 + val4 + val5 + val6 + val7);


    }
}
