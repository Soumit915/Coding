import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Anirban_LineTrend {
    static class Node{
        int x, y;
        Node next;
        public String toString(){
            return this.x+" "+this.y;
        }
    }
    static int countTrends(Node head){
        if(head.next==null)
            return 0;
        Node ptr = head.next;
        Node preptr = head;
        ArrayList<Node> list = new ArrayList<>();
        while(ptr!=null){
            int dx = ptr.x - preptr.x;
            int dy = ptr.y - preptr.y;
            Node node = new Node();
            node.x = dx;
            node.y = dy;
            list.add(node);

            preptr = ptr;
            ptr = ptr.next;
        }

        System.out.println(list);

        int count = 0;
        for(int i=1;i<list.size();i++){
            long y1 = list.get(i-1).y;
            long y2 = list.get(i).y;

            long x1 = list.get(i-1).x;
            long x2 = list.get(i).x;

            if(y1 * x2 != x1 * y2)
                count++;
        }

        return count;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Node head = new Node();
        head.x = 1;
        head.y = 2;
        Node ptr = head;
        for (int i=0;i<n;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            ptr.next = new Node();
            ptr = ptr.next;
            ptr.x = x;
            ptr.y = y;
        }

        System.out.println(countTrends(head));
    }
}
