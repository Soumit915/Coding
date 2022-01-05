import java.util.*;
import java.io.*;

public class NiceTeams
{
    public static int maxPairs(List<Integer> skillLevel, int minDiff) {

        Collections.sort(skillLevel);

        Map<Integer, Integer> hash = new HashMap<>();
        for(int i: skillLevel)
        {
            if(hash.containsKey(i))
            {
                hash.put(i, hash.get(i)+1);
            }
            else hash.put(i, 1);
        }

        ArrayList<Integer> sortedkeyset = new ArrayList<>(hash.keySet());
        TreeSet<Integer> treeset = new TreeSet<>(sortedkeyset);
        Collections.sort(sortedkeyset);

        Map<Integer, Integer> suffixsum = new HashMap<>();
        int lastvalue = sortedkeyset.get(sortedkeyset.size()-1);
        suffixsum.put(lastvalue, hash.get(lastvalue));
        for(int i=sortedkeyset.size()-2;i>=0;i--)
        {
            int val = sortedkeyset.get(i);
            suffixsum.put(val, suffixsum.get(sortedkeyset.get(i+1))+hash.get(val));
        }

        int count = 0;
        for(int i: sortedkeyset)
        {
            int nextindex = i+minDiff;
            if(treeset.ceiling(nextindex)==null)
            {
                break;
            }

            int nextvalue = treeset.ceiling(i+minDiff);
            int nom = Math.min(suffixsum.get(nextvalue), hash.get(i));
            count += nom;

            if(nom<hash.get(i))
                break;
            Stack<Integer> stk = new Stack<>();
            if(nom==suffixsum.get(nextvalue))
            {
                break;
            }
            else
            {
                while(true)
                {
                    int vtdfeind = Math.min(hash.get(nextvalue), nom);
                    hash.put(nextvalue, hash.get(nextvalue)-vtdfeind);
                    nom -= vtdfeind;
                    stk.push(vtdfeind);
                    if(nom==0)
                        break;
                    nextvalue = treeset.higher(nextvalue);
                }
            }

            int prevalue = nextvalue;
            int sumvalue = 0;
            while(!stk.isEmpty())
            {
                sumvalue += stk.pop();
                suffixsum.put(prevalue, suffixsum.get(prevalue)-sumvalue);
                prevalue = treeset.lower(prevalue);
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int n = sc.nextInt();

        List<Integer> skillLevel = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            skillLevel.add(sc.nextInt());
        }

        int maxDiff = sc.nextInt();
        System.out.println(maxPairs(skillLevel, maxDiff));
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 18;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[100064]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
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
            if (din == null)
                return;
            din.close();
        }
    }
}