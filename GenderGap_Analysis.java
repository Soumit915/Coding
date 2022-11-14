import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class GenderGap_Analysis {
    static class Response{
        String timestamp;
        int age;
        String country;
        String gender;
        boolean isEmployed;
        String organisation;
        String ratio;
        boolean hasOrganisedDrives;
        boolean hasPayDist;
        boolean gotGenderSpecificBenefits_fromOrganisation;
        boolean getGenderSpecificBenefits_fromGovt;
        boolean wasDiscriminated_atOrganisation;
        boolean wasDiscriminated_atPublic;
        boolean wasDiscriminated_atHome;
        boolean wasHarassed;
        boolean hasSeenOthersHarassed;
        boolean hasTakenInitiativeToPromoteEquality;
        Response(String s){
            Scanner sc = new Scanner(s);
            sc.useDelimiter(",");

            timestamp = sc.next();
            age = sc.nextInt();
            country = sc.next();
            gender = sc.next();
            isEmployed = sc.next().equals("Yes");
            organisation = sc.next();
            ratio = sc.next();
            hasOrganisedDrives = sc.next().charAt(1)=='Y';
            hasPayDist = sc.next().equals("Yes");
            gotGenderSpecificBenefits_fromOrganisation = sc.next().equals("Yes");
            getGenderSpecificBenefits_fromGovt = sc.next().equals("Yes");
            wasDiscriminated_atOrganisation = sc.next().equals("Yes");
            wasDiscriminated_atPublic = sc.next().equals("Yes");
            wasDiscriminated_atHome = sc.next().equals("Yes");
            wasHarassed = sc.next().equals("Yes");
            hasSeenOthersHarassed = sc.next().equals("Yes");
            hasTakenInitiativeToPromoteEquality = sc.next().equals("Yes");
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("Response.txt"));

        ArrayList<Response> responses = new ArrayList<>();
        while(sc.hasNextLine()){
            String s = sc.nextLine();

            responses.add(new Response(s));
        }

        Map<String, Integer> employed_gendercount = new HashMap<>();
        employed_gendercount.put("Male", 0);
        employed_gendercount.put("Female", 0);
        employed_gendercount.put("LGBTQ", 0);
        for(Response response: responses){
            if(response.getGenderSpecificBenefits_fromGovt){
                employed_gendercount.put(response.gender,
                        employed_gendercount.get(response.gender)+1);
            }
        }
        System.out.println(employed_gendercount);

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
