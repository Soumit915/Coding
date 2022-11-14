package CUrBrain_Questions;

import java.io.*;
import java.util.*;

public class PyramidScheme {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        File file = new File("Input.txt");
        Scanner sc = new Scanner(file);

        Soumit output = new Soumit();
        output.streamOutput("Output.txt");

        int testcases = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (testcases-->0){
            User temp;
            int n, t, amount, myIncome = 0, depth, income, rejected = 0;
            StringBuilder name;
            String my_code;
            String referral_code;
            String input;
            String[] inputs;
            n = sc.nextInt();
            if(n==0)
                System.out.println("Alert");
            t = sc.nextInt();
            ArrayList<User> users = new ArrayList<>();
            HashMap<String, User> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                input = sc.next()+sc.nextLine();
                inputs = input.split(" ");

                //System.out.println(Arrays.toString(inputs));

                amount = Integer.parseInt(inputs[inputs.length - 3]);
                if (amount == 600)
                    depth = 10;
                else if (amount == 500)
                    depth = 6;
                else if (amount == 400)
                    depth = 3;
                else
                    depth = 1;
                referral_code = inputs[inputs.length - 1];

                //System.out.println(testcases+" "+referral_code);
                income = getReturn(amount,  map, referral_code, false);
                if (myIncome + income < t) {
                    myIncome += getReturn(amount, map, referral_code, true);
                    name = new StringBuilder(inputs[0]);
                    for (int j = 1; j < inputs.length - 3; j++)
                        name.append(" ").append(inputs[j]);
                    my_code = inputs[inputs.length - 2];
                    temp = new User(name.toString(), my_code, referral_code, amount, depth);
                    users.add(temp);
                    map.put(my_code, temp);
                } else {
                    rejected++;
                }
            }

            Collections.sort(users);
            sb.append(myIncome).append(" ").append(rejected).append("\n");

            for(int ui = 0;ui<Math.min(10, users.size());ui++) {
                User i = users.get(ui);
                sb.append(i.name).append(" ").append(i.income).append("\n");
            }
        }

        output.println(sb.toString());

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);

        sc.close();
        output.close();
    }

    static int getReturn(int amount, HashMap<String, User> map,
                         String referral_code, boolean change){
        User t;
        int depth = 1, x;
//        System.out.println(referral_code);
        while(!referral_code.equals("NULL") && depth <= 10){

            /*System.out.println(map);
            System.out.println(referral_code+" "+depth);*/

            t = map.getOrDefault(referral_code, null);

            if(t==null)
                break;

            if(t.depth>=depth){
                x = depth==1?100:10;
                amount-=x;
                if(change)
                    t.income+=x;
            }
            referral_code = t.referral_code;
            depth++;
//            System.out.println(referral_code+" "+depth);
        }
        return amount;
    }

    static class User implements Comparable<User>{
        String name, my_code, referral_code;
        int income, depth;
        User(String name, String my_code, String referral_code, int amount, int depth){
            this.name = name;
            this.my_code = my_code;
            this.referral_code = referral_code;
            income = -amount;
            this.depth = depth;

        }
        public int compareTo(User user){
            return user.income - this.income;
        }
        public String toString(){
            return this.name + " "+ this.my_code;
        }
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
