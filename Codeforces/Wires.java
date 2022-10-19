package Codeforces;

import java.util.*;
import java.io.*;

public class Wires {

    static class Wire{
        int id;
        int u, v;

        boolean isVisited;

        Wire(int id, int u, int v){
            this.id = id;
            this.u = u;
            this.v = v;
            this.isVisited = false;
        }
    }

    static class Pair{
        Wire wire;
        int point;

        Pair(Wire wire, int point){
            this.wire = wire;
            this.point = point;
        }
    }

    static class Circuit{
        List<Wire> wirelist;

        Map<Integer, List<Wire>> wire_map;
        List<List<Wire>> connected_components;

        Circuit(int n){
            wirelist = new ArrayList<>(n);
            wire_map = new HashMap<>();
            connected_components = new ArrayList<>();
        }

        public void addWire(Wire wire){
            wirelist.add(wire);

            int u = wire.u;
            int v = wire.v;

            List<Wire> terminal_list = wire_map.getOrDefault(u, new ArrayList<>());
            terminal_list.add(wire);
            wire_map.put(u, terminal_list);

            terminal_list = wire_map.getOrDefault(v, new ArrayList<>());
            terminal_list.add(wire);
            wire_map.put(v, terminal_list);
        }

        public void discoverConnectedComponents(){

            for(Wire wire: wirelist){
                if(!wire.isVisited){
                    List<Wire> connected_component = dfs(wire);
                    connected_components.add(connected_component);
                }
            }
        }

        public List<Wire> dfs(Wire wire_source){
            Stack<Integer> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            List<Wire> wire_in_connected_component = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();

            int source = wire_source.u;

            stk.push(source);
            ptrstk.push(-1);
            visited.add(source);

            while(!stk.isEmpty()){
                int cur = stk.pop();
                int ptr = ptrstk.pop();

                List<Wire> wiremap_list = wire_map.get(cur);

                if(ptr < wiremap_list.size() - 1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Wire wire = wiremap_list.get(ptr);

                    if(!wire.isVisited){
                        wire.isVisited = true;
                        wire_in_connected_component.add(wire);

                        if(wire.u == cur){
                            if(!visited.contains(wire.v)){
                                visited.add(wire.v);
                                stk.push(wire.v);
                                ptrstk.push(-1);
                            }
                        }
                        else{
                            if(!visited.contains(wire.u)){
                                visited.add(wire.u);
                                stk.push(wire.u);
                                ptrstk.push(-1);
                            }
                        }
                    }
                }
            }

            return wire_in_connected_component;
        }

        public Pair getPoint(List<Wire> wires){

            for(Wire wire: wires){
                if(wire_map.get(wire.u).size() == 1){
                    return new Pair(wire, wire.u);
                }
                else if(wire_map.get(wire.v).size() == 1){
                    return new Pair(wire, wire.v);
                }

                wire.isVisited = false;
            }

            Stack<Integer> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();

            HashSet<Integer> visited = new HashSet<>();
            HashSet<Integer> inStack = new HashSet<>();

            stk.push(wires.get(0).u);
            ptrstk.push(-1);
            visited.add(wires.get(0).u);
            inStack.add(wires.get(0).u);

            while(!stk.isEmpty()){
                int cur = stk.pop();
                int ptr = ptrstk.pop();

                List<Wire> wiremap_list = wire_map.get(cur);

                if(ptr < wiremap_list.size()-1){
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    Wire wire = wiremap_list.get(ptr);

                    if(!wire.isVisited){
                        wire.isVisited = true;

                        if(wire.u == cur){
                            if(inStack.contains(wire.v)){
                                return new Pair(wire, wire.v);
                            }

                            if(!visited.contains(wire.v)){
                                visited.add(wire.v);
                                inStack.add(wire.v);
                                stk.push(wire.v);
                                ptrstk.push(-1);
                            }
                        }
                        else{
                            if (inStack.contains(wire.u)) {
                                return new Pair(wire, wire.u);
                            }

                            if(!visited.contains(wire.u)){
                                visited.add(wire.u);
                                inStack.add(wire.u);
                                stk.push(wire.u);
                                ptrstk.push(-1);
                            }
                        }
                    }
                }
                else{
                    inStack.remove(cur);
                }
            }

            return new Pair(null, -1);
        }
    }

    public static void main(String[] args) throws IOException {
        Soumit sc = new Soumit();

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();

            Circuit circuit = new Circuit(n);

            for(int i=0;i<n;i++){
                Wire wire = new Wire(i, sc.nextInt(), sc.nextInt());
                circuit.addWire(wire);
            }

            circuit.discoverConnectedComponents();

            int[][] ans = new int[circuit.connected_components.size() - 1][3];
            int point = circuit.connected_components.get(0).get(0).u;
            for(int i=1;i<circuit.connected_components.size();i++){
                Pair p = circuit.getPoint(circuit.connected_components.get(i));

                if(p.wire == null){
                    System.out.println(n);
                    for(Wire wire: circuit.wirelist){
                        System.out.println(wire.u+" "+wire.v);
                    }
                    System.exit(0);
                }

                ans[i-1][0] = p.wire.id;
                ans[i-1][1] = p.point;
                ans[i-1][2] = point;
            }

            sb.append(ans.length).append("\n");
            for(int[] arr: ans){
                sb.append(arr[0] + 1).append(" ").append(arr[1]).append(" ").append(arr[2]).append("\n");
            }
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
