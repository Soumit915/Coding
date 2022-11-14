package Quora;

import java.io.*;
import java.util.*;

public class DigestRecommendation {

    static class Story{
        int id;
        User creator;
        Set<User> followers = new HashSet<>();
        Story(int id, User creator){
            this.id = id;
            this.creator = creator;
        }
        public void addFollower(User follower){
            followers.add(follower);
        }
    }

    static class User{
        int id;
        Set<Story> created = new HashSet<>();
        Set<Story> followedStories = new HashSet<>();
        Set<User> followedUsers = new HashSet<>();
        Set<User> followers = new HashSet<>();

        ArrayList<Recommended> storyPoint = new ArrayList<>();
        User(int id){
            this.id = id;
        }
        public void addStory(Story story){
            created.add(story);
        }
        public void addFollowedStory(Story story){
            followedStories.add(story);
        }
        public void addFollowedUser(User followed){
            followedUsers.add(followed);
        }
        public void addFollower(User follower){
            followers.add(follower);
        }
        public boolean followsStoriesCreatedBy(User user){
            for(Story story: user.created){
                if(this.followedStories.contains(story))
                    return true;
            }

            return false;
        }
        public boolean followsStoriesFollowedBy(User user){
            for(Story story: user.followedStories){
                if(this.followedStories.contains(story))
                    return true;
            }

            return false;
        }
    }

    static class Recommended implements Comparable<Recommended>{
        Story story;
        int points;
        Recommended(Story story, int points){
            this.story = story;
            this.points = points;
        }
        public int compareTo(Recommended r){
            int c = Integer.compare(this.points, r.points)*-1;
            if(c==0){
                return Integer.compare(this.story.id, r.story.id);
            }
            else return c;
        }
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Soumit sc = new Soumit("Input.txt");

        int storycount = sc.nextInt();
        int usercount = sc.nextInt();

        Story[] stories = new Story[storycount];
        User[] users = new User[usercount];
        for(int i=0;i<usercount;i++){
            users[i] = new User(i);
        }

        for(int i=0;i<storycount;i++){
            int creator = sc.nextInt() - 1;
            stories[i] = new Story(i, users[creator]);
            users[creator].addStory(stories[i]);
        }

        int userConnectionCount = sc.nextInt();
        int storyFollowerCount = sc.nextInt();

        for(int i=0;i<userConnectionCount;i++){
            int follower = sc.nextInt()-1;
            int follows = sc.nextInt()-1;

            users[follower].addFollowedUser(users[follows]);
            users[follows].addFollower(users[follower]);
        }

        for(int i=0;i<storyFollowerCount;i++){
            int follower = sc.nextInt()-1;
            int followsStory = sc.nextInt()-1;

            users[follower].addFollowedStory(stories[followsStory]);
            stories[followsStory].addFollower(users[follower]);
        }

        for(int i=0;i<usercount;i++){
            for(int j=0;j<storycount;j++){
                if(stories[j].creator == users[i] || stories[j].followers.contains(users[i])){
                    users[i].storyPoint.add(new Recommended(stories[j], -1));
                    continue;
                }

                int points = 0;
                for(int k=0;k<usercount;k++){
                    if(i==k)
                        points += 0;
                    else{
                        int c = 0;
                        if(users[k].followers.contains(users[i])){
                            c = 3;
                        }
                        else if(users[i].followsStoriesCreatedBy(users[k])){
                            c = 2;
                        }
                        else if(users[i].followsStoriesFollowedBy(users[k])){
                            c = 1;
                        }

                        if(stories[j].creator == users[k]){
                            c *= 2;
                        }
                        else if(users[k].followedStories.contains(stories[j])){
                            c *= 1;
                        }
                        else{
                            c *= 0;
                        }

                        points += c;
                    }
                }

                users[i].storyPoint.add(new Recommended(stories[j], points));
            }

            Collections.sort(users[i].storyPoint);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<usercount;i++){
            for(int j=0;j<3;j++){
                sb.append(users[i].storyPoint.get(j).story.id+1).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

        long end = System.currentTimeMillis();
        System.out.println((end - start)/1000.0);

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
