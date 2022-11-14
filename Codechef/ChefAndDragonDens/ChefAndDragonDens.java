package Codechef.ChefAndDragonDens;

import java.util.*;
import java.io.*;

public class ChefAndDragonDens {
    static int[] leftHighestIndex;
    static int[] rightHighestIndex;
    static class Deque
    {
        int[] deque;
        int front;
        int rear ;
        Deque()
        {
            deque = new int[400000];
            front = -1;
            rear = -1;
        }
        public boolean isEmpty()
        {
            return (this.front==-1);
        }
        public void addLast(int n)
        {
            if(rear==-1)
            {
                front = 200000;
                rear = front;
            }
            else
            {
                rear++;
            }
            deque[rear] = n;
        }
        public int removeLast()
        {
            if(rear==front)
            {
                int k = rear;
                rear = -1;
                front = -1;
                return deque[k];
            }
            else
            {
                rear--;
                return deque[rear+1];
            }
        }
        public void addFirst(int n)
        {
            if(front==-1)
            {
                front = 200000;
                rear = front;
            }
            else
            {
                front--;
            }
            deque[front] = n;
        }
        public int removeFirst()
        {
            if(rear==front)
            {
                int k = rear;
                rear = -1;
                front = -1;
                return deque[k];
            }
            else
            {
                front++;
                return deque[front-1];
            }
        }
        public int getFirst()
        {
            return deque[front];
        }
        public int getLast()
        {
            return deque[rear];
        }
    }

    static class Stack
    {
        long[] stk;
        int top;
        Stack()
        {
            stk = new long[200005];
            top = -1;
        }
        public void push(long n)
        {
            top++;
            stk[top] = n;
        }
        public long pop()
        {
            top--;
            return stk[top+1];
        }
        public boolean isEmpty()
        {
            return (top==-1);
        }
        public long peek()
        {
            return stk[top];
        }
    }

    static class Query implements Comparable<Query>
    {
        int id;
        int type;
        int start;
        int end;
        int leftblockno;
        int rightblockno;
        int timestamp;
        Query(int id, int type, int start, int end)
        {
            this.id = id;
            this.type = type;
            this.start = start-1;
            this.end = end;
            if(type==2)
                this.end-=1;
        }
        public int compareTo(Query q)
        {
            int c = Integer.compare(this.leftblockno, q.leftblockno);
            if(c==0)
            {
                //c = Integer.compare(this.rightblockno, q.rightblockno);
                c = Integer.compare(this.timestamp, q.timestamp);
                if(c==0)
                {
                    c = Integer.compare(this.end, q.end);
                    //c = Integer.compare(this.timestamp, q.timestamp);
                    //if(c==0)
                    //{
                    //  c = Integer.compare(this.end, q.end);
                    if(c==0)
                    {
                        return Integer.compare(this.start, q.start);
                    }
                    else
                        return c;
                    //}
                    //else
                    //  return c;
                }
                else
                    return c;
            }
            else
                return c;
        }
    }

    public static void preComputeLeftRightHighestIndex(long[] height)
    {
        int n = height.length;
        leftHighestIndex = new int[n];
        rightHighestIndex = new int[n];

        Stack stk = new Stack();
        Stack stkind = new Stack();
        for(int i=0;i<n;i++)
        {
            while(!stk.isEmpty() && stk.peek()<height[i])
            {
                rightHighestIndex[(int) stkind.pop()] = i;
                stk.pop();
            }
            stk.push(height[i]);
            stkind.push(i);
        }
        while(!stk.isEmpty())
        {
            rightHighestIndex[(int) stkind.pop()] = n;
            stk.pop();
        }

        stk = new Stack();
        stkind = new Stack();
        for(int i=n-1;i>=0;i--)
        {
            while(!stk.isEmpty() && stk.peek()<height[i])
            {
                leftHighestIndex[(int) stkind.pop()] = i;
                stk.pop();
            }
            stk.push(height[i]);
            stkind.push(i);
        }
        while (!stk.isEmpty())
        {
            leftHighestIndex[(int) stkind.pop()] = -1;
            stk.pop();
        }
    }

    //static Deque<Integer> globaldeque;
    static Deque globaldeque;
    static boolean[] globalset;
    static long globalsum;
    public static void getBlockQuery(int start, int end, long[] tastiness)
    {
        Stack stk = new Stack();
        if(globaldeque.isEmpty() || end<globaldeque.getLast())
        {
            globaldeque = new Deque();
            globalset = new boolean[tastiness.length];
            globalsum = tastiness[start];
            globaldeque.addLast(start);
            globalset[start] = true;
        }
        int ptr = end;
        while (ptr>Math.max(globaldeque.getLast(), start-1))
        {
            stk.push(ptr);
            ptr = leftHighestIndex[ptr];
        }

        //If the ptr value is greater than or equal to the last value of deque then empty the stack
        if(ptr>=globaldeque.getLast())
        {
            if(ptr!=globaldeque.getLast())
            {
                globaldeque.addLast(ptr);
                globalset[ptr] = true;
                globalsum += tastiness[ptr];
            }
            while(!stk.isEmpty())
            {
                int den = (int) stk.pop();
                globaldeque.addLast(den);
                globalset[den] = true;
                globalsum += tastiness[den];
            }
        }
        //Else find all the points that smaller and should be removed
        else if(ptr<globaldeque.getLast())
        {
            //Remove the points that are smaller than the ptr-height
            while(!globaldeque.isEmpty() && ptr<globaldeque.getLast())
            {
                int removed = globaldeque.removeLast();
                globalset[removed] = false;
                globalsum -= tastiness[removed];
            }

            if(globaldeque.isEmpty() || ptr!=globaldeque.getLast())
            {
                if(ptr>=0)
                {
                    globaldeque.addLast(ptr);
                    globalset[ptr] = true;
                    globalsum += tastiness[ptr];
                }
            }
            while(!stk.isEmpty())
            {
                int den = (int) stk.pop();
                globaldeque.addLast(den);
                globalset[den] = true;
                globalsum += tastiness[den];
            }
        }

        int globalstart = globaldeque.getFirst();

        if(globalstart<start)
        {
            while(globaldeque.getFirst()<start)
            {
                int removed = globaldeque.removeFirst();
                globalset[removed] = false;
                globalsum -= tastiness[removed];
            }
            if(globaldeque.getFirst()!=start)
            {
                int ind = globaldeque.getFirst();
                while(leftHighestIndex[ind]>=start)
                {
                    ind = leftHighestIndex[ind];
                    globaldeque.addFirst(ind);
                    globalset[ind] = true;
                    globalsum += tastiness[ind];
                }

            }
        }
        else if(globalstart>start)
        {
            int ind = globalstart;
            while(leftHighestIndex[ind]>=start)
            {
                ind = leftHighestIndex[ind];
                globaldeque.addFirst(ind);
                globalset[ind] = true;
                globalsum += tastiness[ind];
            }

        }
    }
    public static void getReverseBlockQuery(int start, int end, long[] tastiness)
    {
        Stack stk = new Stack();
        if(globaldeque.isEmpty() || end>globaldeque.getFirst())
        {
            globaldeque = new Deque();
            globalset = new boolean[tastiness.length];
            globalsum = tastiness[start];
            globaldeque.addLast(start);
            globalset[start] = true;
        }
        int ptr = end;
        while (ptr<Math.min(globaldeque.getFirst(), start+1))
        {
            stk.push(ptr);
            ptr = rightHighestIndex[ptr];
        }

        //If the ptr value is less than or equal to the first value of deque then empty the stack
        if(ptr<=globaldeque.getFirst())
        {
            if(ptr!=globaldeque.getFirst())
            {
                globaldeque.addFirst(ptr);
                globalset[ptr] = true;
                globalsum += tastiness[ptr];
            }
            while(!stk.isEmpty())
            {
                int den = (int) stk.pop();
                globaldeque.addFirst(den);
                globalset[den] = true;
                globalsum += tastiness[den];
            }
        }
        //Else find all the points that are greater and should be removed
        else if(ptr>globaldeque.getFirst())
        {
            //Remove the points that are smaller than the ptr-height
            while(!globaldeque.isEmpty() && ptr>globaldeque.getFirst())
            {
                int removed = globaldeque.removeFirst();
                globalset[removed] = false;
                globalsum -= tastiness[removed];
            }

            if(globaldeque.isEmpty() || ptr!=globaldeque.getFirst())
            {
                if(ptr<tastiness.length) {
                    globaldeque.addFirst(ptr);
                    globalset[ptr] = true;
                    globalsum += tastiness[ptr];
                }
            }
            while(!stk.isEmpty())
            {
                int den = (int) stk.pop();
                globaldeque.addFirst(den);
                globalset[den] = true;
                globalsum += tastiness[den];
            }
        }

        int globalstart = globaldeque.getLast();

        if(globalstart>start)
        {
            while(globaldeque.getLast()>start)
            {
                int removed = globaldeque.removeLast();
                globalset[removed] = false;
                globalsum -= tastiness[removed];
            }
            if(globaldeque.getLast()!=start)
            {
                int ind = globaldeque.getLast();
                while(rightHighestIndex[ind]<=start)
                {
                    ind = rightHighestIndex[ind];
                    globaldeque.addLast(ind);
                    globalset[ind] = true;
                    globalsum += tastiness[ind];
                }

            }
        }
        else if(globalstart<start)
        {
            int ind = globalstart;
            while(rightHighestIndex[ind]<=start)
            {
                ind = rightHighestIndex[ind];
                globaldeque.addLast(ind);
                globalset[ind] = true;
                globalsum += tastiness[ind];
            }

        }
    }
    public static void main(String[] args) throws IOException
    {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int q = sc.nextInt();

        long[] height = new long[n];
        long[] tastiness = new long[n];
        long[] duptasty = new long[n];
        for(int i=0;i<n;i++)
        {
            height[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++)
        {
            tastiness[i] = sc.nextInt();
            duptasty[i] = tastiness[i];
        }

        preComputeLeftRightHighestIndex(height);

        ArrayList<Query> querylist = new ArrayList<>();

        int blocksize = (int) Math.pow(n, 0.67);
        int tscount=0;
        for(int i=0;i<q;i++)
        {
            int qtype = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            Query query = new Query(i, qtype, b, c);
            query.leftblockno = ((b-1)/blocksize);
            query.rightblockno = ((c-1)/blocksize);
            if(qtype==2)
            {
                query.timestamp = tscount;
            }
            else
            {
                tscount++;
                query.timestamp = -1;
            }
            querylist.add(query);
        }

        StringBuilder sb = new StringBuilder();
        long[] ans = new long[q];

        ArrayList<Query> molist = new ArrayList<>();
        ArrayList<Query> molistReverse = new ArrayList<>();
        ArrayList<Query> updatelist = new ArrayList<>();
        for(Query qi: querylist)
        {
            if(qi.type==1)
            {
                updatelist.add(qi);
            }
            else
            {
                if(qi.start<=qi.end)
                {
                    molist.add(qi);
                }
                else
                {
                    molistReverse.add(qi);
                }
            }
        }

        long[][] updatemap = new long[updatelist.size()][3];
        for(int i=0;i<updatelist.size();i++)
        {
            Query uqi = updatelist.get(i);
            updatemap[i][0] = uqi.start;
            updatemap[i][1] = duptasty[uqi.start];
            updatemap[i][2] = uqi.end;
            duptasty[uqi.start] = uqi.end;
        }

        Collections.sort(molist);
        Collections.sort(molistReverse);

        int tsptr=0;
        Deque deque = new Deque();
        globaldeque = new Deque();
        boolean[] set = new boolean[n];
        globalset = new boolean[n];
        int globalstart;
        long sum=tastiness[0];
        globalsum = 0;
        deque.addLast(0);
        set[0] = true;
        for(Query qi: molist)
        {
            while(tsptr != qi.timestamp)
            {
                if(tsptr<qi.timestamp)
                {
                    tastiness[((int)updatemap[tsptr][0])] = updatemap[tsptr][2];
                    if(set[(int)updatemap[tsptr][0]])
                    {
                        sum -= updatemap[tsptr][1];
                        sum += updatemap[tsptr][2];
                    }
                    if(globalset[(int)updatemap[tsptr][0]])
                    {
                        globalsum -= updatemap[tsptr][1];
                        globalsum += updatemap[tsptr][2];
                    }
                    tsptr++;
                }
                else {
                    tsptr--;
                    tastiness[((int)updatemap[tsptr][0])] = updatemap[tsptr][1];
                    if(set[(int)updatemap[tsptr][0]])
                    {
                        sum -= updatemap[tsptr][2];
                        sum += updatemap[tsptr][1];
                    }
                    if(globalset[(int)updatemap[tsptr][0]])
                    {
                        globalsum -= updatemap[tsptr][2];
                        globalsum += updatemap[tsptr][1];
                    }
                }
            }
            //Find all the points that should be appended after the deque
            Stack stk = new Stack();
            if(qi.end<deque.getLast())
            {
                if(qi.rightblockno-qi.leftblockno<=1)
                {
                    deque = new Deque();
                    set = new boolean[n];
                    int init = (qi.rightblockno*blocksize);
                    sum = tastiness[init];
                    deque.addLast(init);
                    set[init] = true;
                }
                else
                {
                    getBlockQuery((qi.leftblockno+1)*blocksize,
                            (qi.rightblockno)*blocksize-1, tastiness);
                    deque = new Deque();
                    set = new boolean[n];
                    for(int dqi=globaldeque.front;dqi<=globaldeque.rear;dqi++)
                    {
                        int dvals = globaldeque.deque[dqi];
                        deque.addLast(dvals);
                        set[dvals] = true;
                    }
                    sum = globalsum;
                }
              /*deque = new LinkedList<>();
              set = new HashSet<>();
              int init = (qi.blockno*blocksize);
              sum = tastiness[init];
              deque.addLast(init);
              set.add(init);*/
            }
            int ptr = qi.end;
            while (ptr>Math.max(deque.getLast(), qi.start-1))
            {
                stk.push(ptr);
                ptr = leftHighestIndex[ptr];
            }

            //If the ptr value is greater than or equal to the last value of deque then empty the stack
            if(ptr>=deque.getLast())
            {
                if(ptr!=deque.getLast())
                {
                    deque.addLast(ptr);
                    set[ptr] = true;
                    sum += tastiness[ptr];
                }
                while(!stk.isEmpty())
                {
                    int den = (int) stk.pop();
                    deque.addLast(den);
                    set[den] = true;
                    sum += tastiness[den];
                }
            }
            //Else find all the points that smaller and should be removed
            else if(ptr<deque.getLast())
            {
                //Remove the points that are smaller than the ptr-height
                while(!deque.isEmpty() && ptr<deque.getLast())
                {
                    int removed = deque.removeLast();
                    set[removed] = false;
                    sum -= tastiness[removed];
                }

                if(deque.isEmpty() || ptr!=deque.getLast())
                {
                    if(ptr>=0)
                    {
                        deque.addLast(ptr);
                        set[ptr] = true;
                        sum += tastiness[ptr];
                    }
                }
                while(!stk.isEmpty())
                {
                    int den = (int) stk.pop();
                    deque.addLast(den);
                    set[den] = true;
                    sum += tastiness[den];
                }
            }

            globalstart = deque.getFirst();

            if(globalstart == qi.start)
            {
                if(deque.getFirst()==globalstart)
                {
                    ans[qi.id] = sum;
                }
                else
                {
                    ans[qi.id] = -1;
                }
            }
            else if(globalstart<qi.start)
            {
                while(deque.getFirst()<qi.start)
                {
                    int removed = deque.removeFirst();
                    set[removed] = false;
                    sum -= tastiness[removed];
                }
                if(deque.getFirst()==qi.start)
                {
                    ans[qi.id] = sum;
                }
                else
                {
                    int ind = deque.getFirst();
                    while(leftHighestIndex[ind]>=qi.start)
                    {
                        ind = leftHighestIndex[ind];
                        deque.addFirst(ind);
                        set[ind] = true;
                        sum += tastiness[ind];
                    }

                    if(ind == qi.start)
                    {
                        ans[qi.id] = sum;
                    }
                    else
                    {
                        ans[qi.id] = -1;
                    }
                }
            }
            else
            {
                int ind = globalstart;
                while(leftHighestIndex[ind]>=qi.start)
                {
                    ind = leftHighestIndex[ind];
                    deque.addFirst(ind);
                    set[ind] = true;
                    sum += tastiness[ind];
                }

                if(ind == qi.start)
                {
                    ans[qi.id] = sum;
                }
                else
                {
                    ans[qi.id] = -1;
                }
            }
        }

        while(tsptr!=0)
        {
            tsptr--;
            tastiness[((int)updatemap[tsptr][0])] = updatemap[tsptr][1];
        }

        tsptr=0;
        deque = new Deque();
        globaldeque = new Deque();
        set = new boolean[n];
        globalset = new boolean[n];
        sum=tastiness[n-1];
        globalsum = 0;
        deque.addLast(n-1);
        set[n-1] = true;
        for(Query qi: molistReverse)
        {
            while(tsptr != qi.timestamp)
            {
                if(tsptr<qi.timestamp)
                {
                    tastiness[((int)updatemap[tsptr][0])] = updatemap[tsptr][2];
                    if(set[(int)updatemap[tsptr][0]])
                    {
                        sum -= updatemap[tsptr][1];
                        sum += updatemap[tsptr][2];
                    }
                    if(globalset[(int)updatemap[tsptr][0]])
                    {
                        globalsum -= updatemap[tsptr][1];
                        globalsum += updatemap[tsptr][2];
                    }
                    tsptr++;
                }
                else {
                    tsptr--;
                    tastiness[((int)updatemap[tsptr][0])] = updatemap[tsptr][1];
                    if(set[(int)updatemap[tsptr][0]])
                    {
                        sum -= updatemap[tsptr][2];
                        sum += updatemap[tsptr][1];
                    }
                    if(globalset[(int)updatemap[tsptr][0]])
                    {
                        globalsum -= updatemap[tsptr][2];
                        globalsum += updatemap[tsptr][1];
                    }
                }
            }
            //Find all the points that should be appended after the deque
            //System.out.println("Test: "+qi.id+" "+sum);
            if(qi.end>deque.getFirst())
            {
                if(qi.leftblockno-qi.rightblockno<=1)
                {
                    deque = new Deque();
                    set = new boolean[n];
                    int init = Math.min((qi.rightblockno+1)*blocksize-1, n-1);
                    sum = tastiness[init];
                    deque.addLast(init);
                    set[init] = true;
                }
                else
                {
                    getReverseBlockQuery((qi.leftblockno)*blocksize-1,
                            (qi.rightblockno+1)*blocksize-1, tastiness);
                    deque = new Deque();
                    set = new boolean[n];
                    for(int dqi=globaldeque.front;dqi<=globaldeque.rear;dqi++)
                    {
                        int dvals = globaldeque.deque[dqi];
                        deque.addLast(dvals);
                        set[dvals] = true;
                    }
                    sum = globalsum;
                }

              /*deque = new LinkedList<>();
              set = new HashSet<>();
              int init = Math.min((qi.blockno+1)*blocksize-1, n-1);
              sum = tastiness[init];
              deque.addLast(init);
              set.add(init);*/
            }
            Stack stk = new Stack();
            int ptr = qi.end;
            while (ptr<Math.min(deque.getFirst(), qi.start+1))
            {
                stk.push(ptr);
                ptr = rightHighestIndex[ptr];
            }

            //If the ptr value is less than or equal to the first value of deque then empty the stack
            if(ptr<=deque.getFirst())
            {
                if(ptr!=deque.getFirst())
                {
                    deque.addFirst(ptr);
                    set[ptr] = true;
                    sum += tastiness[ptr];
                }
                while(!stk.isEmpty())
                {
                    int den = (int) stk.pop();
                    deque.addFirst(den);
                    set[den] = true;
                    sum += tastiness[den];
                }
            }
            //Else find all the points that are greater and should be removed
            else if(ptr>deque.getFirst())
            {
                //Remove the points that are smaller than the ptr-height
                while(!deque.isEmpty() && ptr>deque.getFirst())
                {
                    int removed = deque.removeFirst();
                    set[removed] = false;
                    sum -= tastiness[removed];
                }

                if(deque.isEmpty() || ptr!=deque.getFirst())
                {
                    if(ptr<n) {
                        deque.addFirst(ptr);
                        set[ptr] = true;
                        sum += tastiness[ptr];
                    }
                }
                while(!stk.isEmpty())
                {
                    int den = (int) stk.pop();
                    deque.addFirst(den);
                    set[den] = true;
                    sum += tastiness[den];
                }
            }

            globalstart = deque.getLast();

            if(globalstart == qi.start)
            {
                if(deque.getLast()==globalstart)
                {
                    ans[qi.id] = sum;
                }
                else
                {
                    ans[qi.id] = -1;
                }
            }
            else if(globalstart>qi.start)
            {
                while(deque.getLast()>qi.start)
                {
                    int removed = deque.removeLast();
                    set[removed] = false;
                    sum -= tastiness[removed];
                }
                if(deque.getLast()==qi.start)
                {
                    ans[qi.id] = sum;
                }
                else
                {
                    int ind = deque.getLast();
                    while(rightHighestIndex[ind]<=qi.start)
                    {
                        ind = rightHighestIndex[ind];
                        deque.addLast(ind);
                        set[ind] = true;
                        sum += tastiness[ind];
                    }

                    if(ind == qi.start)
                    {
                        ans[qi.id] = sum;
                    }
                    else
                    {
                        ans[qi.id] = -1;
                    }
                }
            }
            else
            {
                int ind = globalstart;
                while(rightHighestIndex[ind]<=qi.start)
                {
                    ind = rightHighestIndex[ind];
                    deque.addLast(ind);
                    set[ind] = true;
                    sum += tastiness[ind];
                }

                if(ind == qi.start)
                {
                    ans[qi.id] = sum;
                }
                else
                {
                    ans[qi.id] = -1;
                }
            }
        }

        for(int i=0;i<q;i++)
        {
            if(querylist.get(i).type==2)
            {
                sb.append(ans[i]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            din.close();
        }
    }
}
