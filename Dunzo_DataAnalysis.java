import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Dunzo_DataAnalysis {
    static class User{
        long id;
        String city;
        String phonetype;
        ArrayList<Transaction> self_transactions = new ArrayList<>();
        User(String s){
            Scanner sc = new Scanner(s);
            sc.useDelimiter(",");

            this.id = sc.nextLong();
            this.city = sc.next();
            this.phonetype = sc.next();
        }
    }
    static class Transaction{
        long id;
        long val;
        long userId;
        String paymentMethod;
        Transaction(String s){
            Scanner sc = new Scanner(s);
            sc.useDelimiter(",");

            this.id = sc.nextLong();
            this.val = sc.nextLong();
            this.userId = sc.nextLong();
            this.paymentMethod = sc.next();
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner usc = new Scanner(new File("User.csv"));
        Scanner tsc = new Scanner(new File("Transaction.csv"));

        HashMap<Long, User> users = new HashMap<>();
        while(usc.hasNextLine()){
            String s = usc.nextLine();
            User user = new User(s);
            users.put(user.id, user);
        }

        HashMap<Long, Transaction> transactions = new HashMap<>();
        while(tsc.hasNextLine()){
            String s = tsc.nextLine();
            Transaction transaction = new Transaction(s);
            transactions.put(transaction.id, transaction);

            User user = users.get(transaction.userId);
            user.self_transactions.add(transaction);
        }

        HashMap<String, Long> paymentmethod_ordervalue = new HashMap<>();
        HashMap<String, Long> paymentmethod_count = new HashMap<>();
        for(Transaction transaction: transactions.values()){
            if(!transaction.paymentMethod.equals("GPAY"))
                continue;
            User user = users.get(transaction.userId);
            String city = user.city;
            paymentmethod_count.put(city,
                    paymentmethod_count.getOrDefault(city, 0L)+1);
            paymentmethod_ordervalue.put(city,
                    paymentmethod_ordervalue.getOrDefault(city,
                            0L)+transaction.val);
        }

        for(String paymentname: paymentmethod_count.keySet()){
            double d = paymentmethod_ordervalue.get(paymentname);
            d = d/paymentmethod_count.get(paymentname);
            System.out.println(paymentname+" "+d+" "+paymentmethod_ordervalue.get(paymentname));
        }

        usc.close();
        tsc.close();
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
