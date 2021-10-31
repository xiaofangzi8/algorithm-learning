import javafx.beans.binding.MapExpression;
import util.ListNode;
import util.Node;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

    private static Map<Node, Node> map = new HashMap<Node,Node>();
    public static void main(String[] args) {
        Node l1 = new Node(1);
        Node l2 = new Node(2);
        Node l3 = new Node(3);
        Node l4 = new Node(4);
        Node l5 = new Node(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        Node result = copyRandomList(l1);
        while(result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }

//        Node result2 = copyRandomList2(l1);
//        while(result2 != null){
//            System.out.print(result2.val + " ");
//           result2 = result2.next;
//        }


    }

    public static Node copyRandomList(Node head) {

        if(head == null){
            return null;
        }

        Node p = head;

        while(p != null){
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }
        p=head;

        while(p!=null){
            if(p.random != null){
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        Node dummy = new Node(-1);
        p = head;
        Node cur = dummy;
        while(p != null){
            cur.next = p.next;
            cur = cur.next;
            p.next = cur.next;
            p = p.next;
        }
        return dummy.next;
    }


   //方法二：递归
    public static Node copyRandomList2(Node head){
        if(head == null){
            return null;
        }

        if(!map.containsKey(head)){
            Node newNode = new Node(head.val);
            map.put(head,newNode);
            newNode.next = copyRandomList2(head.next);
            newNode.random = copyRandomList2(head.random);
        }
        return (Node) map.get(head);
    }
}
