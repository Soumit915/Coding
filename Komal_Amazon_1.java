import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

public class Komal_Amazon_1 {

    static class User{
        String username, password;
        boolean isLoggedIn;

        User(String username, String password){
            this.username = username;
            this.password = password;
            this.isLoggedIn = false;
        }
    }

    public static List<String> implementAPI(List<String> logs){

        List<String> ans = new ArrayList<>();

        HashMap<String, User> hash = new HashMap<>();

        for (String s : logs) {
            String[] api = s.split(" ");

            if (api[0].equals("register")) {
                String username = api[1];
                String password = api[2];

                if (hash.containsKey(username)) {
                    ans.add("Username already exists");
                } else {
                    User user = new User(username, password);
                    hash.put(username, user);

                    ans.add("Registered Successfully");
                }
            } else if (api[0].equals("login")) {
                String username = api[1];
                String password = api[2];

                if (hash.containsKey(username)) {
                    User user = hash.get(username);

                    if (!user.isLoggedIn && user.password.equals(password)) {
                        user.isLoggedIn = true;
                        ans.add("Logged In Successfully");
                    } else {
                        ans.add("Login Unsuccessful");
                    }
                } else {
                    ans.add("Login Unsuccessful");
                }
            } else {
                String username = api[1];

                if (hash.containsKey(username)) {
                    User user = hash.get(username);

                    if (user.isLoggedIn) {
                        user.isLoggedIn = false;
                        ans.add("Logged Out Successfully");
                    } else {
                        ans.add("Logout Unsuccessful");
                    }
                } else {
                    ans.add("Logout Unsuccessful");
                }
            }
        }

        return ans;
    }

    public static int minimumKeypadClickCount(String text){
        int n = text.length();

        Integer[] hash = new Integer[26];
        for(int i=0;i<26;i++){
            hash[i] = 0;
        }
        for(int i=0;i<n;i++){
            int ch = text.charAt(i) - 'a';

            hash[ch]++;
        }

        Arrays.sort(hash, Collections.reverseOrder());

        int ans = 0;
        for(int i=0;i<26;i++){
            int typecount = i/9 + 1;
            ans += (typecount * hash[i]);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        String s = "abacadefghibjjjjj";
        System.out.println(minimumKeypadClickCount(s));

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
