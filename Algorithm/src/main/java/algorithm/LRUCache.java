package algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private Map<Integer, Node> cache = new HashMap<>();
    private int size;
    private int capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public String get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }

        moveToHead(node);

        return node.val;
    }

    public void put(int key, String value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;

            cache.put(key, newNode);
            addNode(newNode);
            ++size;
            if (size > capacity) {
                Node rmNode = popTail();
                cache.remove(rmNode.key);
                --size;
            }
        }
        else{
            node.val = value;
            moveToHead(node);
        }

    }

    private void addNode(Node node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        Node rem = tail.pre;
        removeNode(rem);
        return rem;
    }


    public static class Node {
        Node() {
        }

        Node(int key, String val) {
            this.key = key;
            this.val = val;
        }

        protected String val;
        protected Node next;
        protected Node pre;
        protected int key;
    }


    public static void main(String[] args) {
        int cacheLenght = 5;

        LRUCache myLru = new LRUCache(cacheLenght);


        System.out.println(myLru.toString());

    }
}
