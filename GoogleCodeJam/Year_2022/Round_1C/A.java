package GoogleCodeJam.Year_2022.Round_1C;

import java.io.*;
import java.util.*;

public class A {

    static boolean allSame(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != s.charAt(0))
                return false;
        }

        return true;
    }

    static int getNext(String[] strings, List<Integer> list){
        int count = 0;
        int index = list.get(0);
        for(int i : list){
            if(allSame(strings[i])){
                count++;
            }
            else{
                index = i;
            }
        }

        if(count < list.size() - 1){
            return -1;
        }
        else return index;
    }

    static String getAns(String[] strings, boolean[] isVisited, int start,
                         Map<Character, List<Integer>> startsWith){
        int n = strings.length;

        int[] letters = new int[26];
        for(String s: strings){
            for(int i=0;i<s.length();i++){
                letters[s.charAt(i) - 'A']++;
            }
        }

        StringBuilder sb = new StringBuilder();

        int count = 0, i = start;
        while(count < n && !isVisited[i]){
            sb.append(strings[i]);
            isVisited[i] = true;

            for(int j=0;j<strings[i].length();j++){
                letters[strings[i].charAt(j) - 'A']--;

                if(j!=strings[i].length() - 1) {
                    if(strings[i].charAt(j) != strings[i].charAt(j+1)
                            && letters[strings[i].charAt(j) - 'A'] != 0){
                        return null;
                    }
                }
            }

            List<Integer> list = startsWith.getOrDefault(strings[i].charAt(strings[i].length() - 1),
                    new ArrayList<>());
            if(list.size() == 0){
                if(letters[strings[i].charAt(strings[i].length() - 1) - 'A'] != 0){
                    return null;
                }

                return sb.toString();
            }

            i = getNext(strings, list);
            for(int li: list){
                if(i != li && !isVisited[li]){
                    sb.append(strings[li]);
                    for(int j=0;j<strings[li].length();j++){
                        letters[strings[li].charAt(j) - 'A']--;
                    }
                    isVisited[li] = true;
                }
            }

            if(i == -1)
                return null;

            count++;
        }

        if(letters[strings[i].charAt(strings[i].length() - 1) - 'A'] != 0){
            return null;
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int testi = 1;testi<=t;testi++){
            sb.append("Case #").append(testi).append(": ");

            int n = sc.nextInt();
            String[] s = new String[n];
            Map<Character, List<Integer>> map = new HashMap<>();
            for(int i=0;i<n;i++){
                s[i] = sc.next();
                List<Integer> list = map.getOrDefault(s[i].charAt(0), new ArrayList<>());
                list.add(i);

                map.put(s[i].charAt(0), list);
            }

            boolean[] isVisited = new boolean[n];
            StringBuilder ans = new StringBuilder();
            for(int i=0;i<n;i++){
                if(isVisited[i])
                    continue;

                boolean[] local = new boolean[n];
                String cur = getAns(s, local, i, map);

                if(cur != null){
                    for(int j=0;j<n;j++){
                        isVisited[j] |= local[j];
                        local[j] = isVisited[j];
                    }
                    ans.append(cur);
                }
            }

            boolean flag = true;
            for(int i=0;i<n;i++){
                flag = flag & isVisited[i];
            }

            if(flag)
                sb.append(ans).append("\n");
            else sb.append("IMPOSSIBLE\n");
        }

        System.out.println(sb);

        sc.close();
    }

    static class Soumit {
        final private int BUFFER_SIZE = 1 << 18;
        final private DataInputStream din;
        final private byte[] buffer;
        private PrintWriter pw;
        private int bufferPointer, bytesRead;
        StringTokenizer st;

        public Soumit() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Soumit(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public void streamOutput(String file) throws IOException {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
        }

        public void println(String a) {
            pw.println(a);
        }

        public void print(String a) {
            pw.print(a);
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[3000064]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public void sort(int[] arr) {
            ArrayList<Integer> arlist = new ArrayList<>();
            for (int i : arr)
                arlist.add(i);

            Collections.sort(arlist);
            for (int i = 0; i < arr.length; i++)
                arr[i] = arlist.get(i);
        }

        public void sort(long[] arr) {
            ArrayList<Long> arlist = new ArrayList<>();
            for (long i : arr)
                arlist.add(i);

            Collections.sort(arlist);
            for (int i = 0; i < arr.length; i++)
                arr[i] = arlist.get(i);
        }

        public int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }

            return arr;
        }

        public long[] nextLongArray(int n) throws IOException {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }

            return arr;
        }

        public double[] nextDoubleArray(int n) throws IOException {
            double[] arr = new double[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextDouble();
            }

            return arr;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
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

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
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

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            /*if (din == null)
                return;*/
            if (din != null) din.close();
            if (pw != null) pw.close();
        }
    }
}
