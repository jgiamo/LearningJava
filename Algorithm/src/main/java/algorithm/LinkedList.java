package algorithm;

/**
 * 包含链表的几种常规操作
 */
public class LinkedList {

    //头部哨兵节点
    Node head = null;

    //链表最后一个节点
    Node tail = null;

    LinkedList() {
        head = new Node("");
    }

    public boolean add(String val) {
        Node node = new Node(val);
        if (tail == null) {
            tail = node;
            head.next = tail;
        } else {
            tail.next = node;
            tail = node;
        }
        return true;
    }

    public LinkedList fluentAdd(String val) {
        this.add(val);
        return this;
    }


    //反转链表
    public void reverseList(){
    }

    public String traverse() {
        StringBuilder stringBuilder = new StringBuilder();
        Node traverseNode = head;
        while (traverseNode.next != null) {
            stringBuilder.append(traverseNode.next.val).append(" ");
            traverseNode = traverseNode.next;
        }
        return stringBuilder.toString();
    }

    public static class Node {
        Node(String val) {
            this.val = val;
        }
        protected String val;
        protected Node next;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.fluentAdd("123").fluentAdd("345").fluentAdd("456");
        System.out.println(list.traverse());
    }
}
