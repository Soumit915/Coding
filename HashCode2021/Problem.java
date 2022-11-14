package HashCode2021;

import java.io.*;
import java.util.*;

public class Problem {
    static class Intersection {
        int id;

        Intersection() {
            this.id = 0;
        }

        Intersection(int id) {
            this.id = id;
        }
    }

    static class Road {
        int id;
        Intersection start;
        Intersection end;
        String name;
        long time_length;

        Road(int id, Intersection start, Intersection end, String name, long l) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.name = name;
            this.time_length = l;
        }
    }

    static class Car {
        int id;
        int number_of_paths;
        ArrayList<Road> roads;
        long totalTime;
        long currentTime;

        Car(int id, int number_of_paths, ArrayList<Road> roads) {
            this.id = id;
            this.number_of_paths = number_of_paths;
            this.roads = roads;
        }
    }

    //HashMap<Integer, ArrayList<Road>> getMapping(Road[] roads){}
    static ArrayList<ArrayList<Road>> getMapping(Road[] roads, int number_of_intersections) {
        ArrayList<ArrayList<Road>> map=new ArrayList<>();
        for(int i=0;i<number_of_intersections;i++)
            map.add(new ArrayList<>());
        for(Road road: roads) {
            map.get(road.start.id).add(road);
        }
        return map;
    }

    static HashSet<String> findUsedPaths(long simulation_time, Car[] cars){

        HashSet<String> sets = new HashSet<>();
        for (Car car : cars) {
            long sum = 0;
            for (int j = 0; j < car.number_of_paths; j++) {
                Road road = car.roads.get(j);
                sum += road.time_length;
                sets.add(road.name);

                if (sum > simulation_time)
                    break;
            }
        }
        return sets;
    }

    public static void main(String[] args) throws IOException {

        char[] ar = {'b','c','d','e','f'};
        for (char c : ar) {

            String Inputfile = c + ".txt";
            String Outputfile = "Output" + c + ".txt";

            System.out.println(Inputfile + " " + Outputfile);

            File file = new File(Inputfile);
            Scanner sc = new Scanner(file);

            Soumit output = new Soumit("b.txt");
            output.streamOutput(Outputfile);

            long simulation_time = sc.nextLong();
            int number_of_intersections = sc.nextInt();
            int number_of_roads = sc.nextInt();
            int number_of_cars = sc.nextInt();
            sc.nextInt();

            ArrayList<Intersection> intersections = new ArrayList<>();
            for (int i = 0; i < number_of_intersections; i++) {
                intersections.add(new Intersection(i));
            }

            //Array of roads
            Road[] roads = new Road[number_of_roads];
            HashMap<String, Road> name_road_map = new HashMap<>();
            for (int i = 0; i < number_of_roads; i++) {
                int starting = sc.nextInt();
                int ending = sc.nextInt();
                String name = sc.next();
                long timeL = sc.nextLong();
                roads[i] =
                        new Road(i, intersections.get(starting), intersections.get(ending), name, timeL);

                name_road_map.put(name, roads[i]);
            }

            //Array of cars
            Car[] cars = new Car[number_of_cars];
            for (int i = 0; i < number_of_cars; i++) {
                int number_of_paths_for_this_car = sc.nextInt();
                ArrayList<Road> paths = new ArrayList<>();
                long sum = 0;
                for (int j = 0; j < number_of_paths_for_this_car; j++) {
                    String road_name = sc.next();
                    paths.add(name_road_map.get(road_name));
                    //finding the sum
                    sum += name_road_map.get(road_name).time_length;
                }
                cars[i] = new Car(i, number_of_paths_for_this_car, paths);
                cars[i].totalTime = sum;
                cars[i].currentTime = sum;
            }

            HashSet<String> road_name_sets = findUsedPaths(simulation_time, cars);

            ArrayList<ArrayList<Road>> intersection_roads_map = new ArrayList<>();
            for (int i = 0; i < number_of_intersections; i++)
                intersection_roads_map.add(new ArrayList<>());

            //name_road_map maps name to a road
            for (String s : road_name_sets) {
                Road road = name_road_map.get(s);
                ArrayList<Road> roadlist = intersection_roads_map.get(road.end.id);
                roadlist.add(road);
            }

            int total_used_intersections = 0;
            for (int i = 0; i < number_of_intersections; i++) {
                if (!intersection_roads_map.get(i).isEmpty())
                    total_used_intersections++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(total_used_intersections).append("\n");
            for (int i = 0; i < number_of_intersections; i++) {
                if (!intersection_roads_map.get(i).isEmpty()) {
                    sb.append(i).append("\n");
                    ArrayList<Road> roadlist = intersection_roads_map.get(i);
                    sb.append(roadlist.size()).append("\n");
                    int random = 2;
                    for (Road road : roadlist) {
                        sb.append(road.name).append(" ").append(random).append("\n");
                        random = random % 2 + 1;
                    }
                }
            }

            output.println(sb.toString());
            output.close();

            sc.close();
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
