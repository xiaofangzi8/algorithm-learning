import util.ListNode;

import java.util.List;

public class RotateRight {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
//        ListNode l4 = new ListNode(4);
//        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;

//        ListNode result = rotateRight(l1,2);
          ListNode result = rotateRight(l1,5);
        while(result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    private static ListNode rotateRight(ListNode head, int k){
       //空或者一个节点直接返回
       if(head == null || k==0 || head.next == null){
           return head;
       }
       //指向头节点
       ListNode pre = head;
       //尾节点
       ListNode end = null;
       int n = 0;
       //先统计链表的长度
       while(pre != null){
           end = pre;
           pre = pre.next;
           n++;
       }

       //k和列表长度n取模
       k %=n;
       ListNode start = head;
       for(int i=0; i< n-k-1;i++){
           start = start.next;
       }
       end.next = head;
       head = start.next;
       start.next = null;
        return head;
    }
}
