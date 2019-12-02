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
    public void reverseList() {
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

    public static int hasCircleAndGetCricleLength(Node head) {
        if (head == null) {
            return 0;
        }
        if (head.next == null) {
            return 0;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != null) {
            if (slow == fast) {
                break;
            }
            slow = slow.next;
            if(fast.next == null){
                return 0;
            }
            fast = fast.next.next;
        }
        if(fast == null){
            return 0;
        }
        int len = 1;
        fast = fast.next;
        while(slow != fast) {
            fast = fast.next;
            len ++;
        }
        return len;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Node a = new Node("1");
        Node b = new Node("2");
        Node c = new Node("3");
        Node d = new Node("4");
        Node e = new Node("5");
        Node f = new Node("6");
        Node g = new Node("7");
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = c;
        System.out.println(hasCircleAndGetCricleLength(a));
        list.fluentAdd("123").fluentAdd("345").fluentAdd("456");
        System.out.println(list.traverse());
    }
}
