package FacebookHackercup.QualificationRound_2020;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class C_Timber {
    public static void main(String[] args) throws IOException {

        File file = new File("Input.txt");

        FileWriter fw = new FileWriter("Output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            int t = sc.nextInt();

            for(int testi=1;testi<=t;testi++) {
                int n = sc.nextInt();

                long[][] arr = new long[n][2];
                for (int i = 0; i < n; i++) {
                    arr[i][0] = sc.nextLong();
                    arr[i][1] = sc.nextLong();
                }

                HashMap<Long, Long> pointleft = new HashMap<>();
                HashMap<Long, Long> pointright = new HashMap<>();
                HashMap<Long, Long> pointlength = new HashMap<>();
                for (int i = 0; i < n; i++) {
                    pointlength.put(arr[i][0], arr[i][1]);
                    pointleft.put(arr[i][0], arr[i][0] - arr[i][1]);
                    pointright.put(arr[i][0], arr[i][0] + arr[i][1]);
                }

                ArrayList<Long> points = new ArrayList<>(pointleft.keySet());
                Collections.sort(points);

                HashMap<Long, Long> pointmap = new HashMap<>();
                for (long i = 0; i < n; i++) {
                    pointmap.put(points.get((int) i), i);
                }

                long[] maxIntervalleft = new long[n];
                maxIntervalleft[n - 1] = pointlength.get(points.get(n - 1));
                long[] maxIntervalright = new long[n];
                maxIntervalright[n - 1] = pointlength.get(points.get(n - 1));

                //leftcutending represents the maximum interval length upto a certain point such that the tree is cut left
                HashMap<Long, Long> leftcutending = new HashMap<>();
                for (long i = n - 1; i >= 0; i--) {
                    if (!leftcutending.containsKey(pointleft.get(points.get((int) i)))) {
                        leftcutending.put(pointleft.get(points.get((int) i)), i);
                    }
                }

                for (int i = n - 2; i >= 0; i--) {
                    //At first update the maxIntegervalright
                    //Get the ith point
                    long pi = points.get(i);
                    //Find the end of the right-interval of the ith tree
                    long rpi = pointright.get(pi);
                    long maxval = Long.MIN_VALUE;
                    //Check if there is a tree present at rpi
                    if (pointlength.containsKey(rpi)) {
                        //If a tree is present there then find the right-interval from that point
                        long pointi = pointmap.get(rpi);
                        maxval = maxIntervalright[(int) pointi];
                    }
                    if (leftcutending.containsKey(rpi)) {
                        long lcerpi = leftcutending.get(rpi);
                        maxval = Math.max(maxval, maxIntervalleft[(int) lcerpi]);
                    }
                    maxIntervalright[i] = Math.max(maxval + pointlength.get(points.get(i)), pointlength.get(points.get(i)));

                    if (leftcutending.containsKey(points.get(i))) {
                        long lcei = leftcutending.get(points.get(i));
                        maxIntervalleft[i] = maxIntervalleft[(int) lcei] + pointlength.get(points.get(i));
                    } else {
                        maxIntervalleft[i] = pointlength.get(points.get(i));
                    }

                    if (leftcutending.containsKey(pointleft.get(points.get(i)))) {
                        long lcepi = leftcutending.get(pointleft.get(points.get(i)));
                        long prev = maxIntervalleft[(int) lcepi];
                        long cur = maxIntervalleft[i];
                        if (cur > prev) {
                            leftcutending.put(pointleft.get(points.get(i)), (long) i);
                        }
                    } else {
                        leftcutending.put(pointleft.get(points.get(i)), (long) i);
                    }
                }

                long max = Integer.MIN_VALUE;
                for (int i = 0; i < n; i++) {
                    max = Math.max(max, maxIntervalleft[i]);
                    max = Math.max(max, maxIntervalright[i]);
                }

                pw.print("Case #"+testi+": ");
                pw.println(max);
            }

            pw.close();
            bw.close();
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}