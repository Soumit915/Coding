package FacebookHackercup.Round1_2020;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class B_Perimetric2 {
    static class Interval implements Comparable<Interval>
    {
        long left;
        long right;
        Interval(long left, long right)
        {
            this.left = left;
            this.right = right;
        }
        public int compareTo(Interval in)
        {
            return Long.compare(this.left, in.left);
        }
    }
    public static void main(String[] args) throws IOException {

        File file = new File("Input.txt");

        FileWriter fw = new FileWriter("Output.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
            int t = sc.nextInt();

            for(int testi=1;testi<=t;testi++)
            {
                int n = sc.nextInt();
                int k = sc.nextInt();

                long[] l = new long[n];
                long[] w = new long[n];
                long[] h = new long[n];
                long mod = (long) 1e9+7;

                for(int i=0;i<k;i++)
                {
                    l[i] = sc.nextLong();
                }
                long Al = sc.nextLong();
                long Bl = sc.nextLong();
                long Cl = sc.nextLong();
                long Dl = sc.nextLong();
                for(int i=k;i<n;i++)
                {
                    l[i] = ((Al*l[i-2]+Bl*l[i-1]+Cl)%Dl)+1;
                }

                for(int i=0;i<k;i++)
                {
                    w[i] = sc.nextLong();
                }
                long Aw = sc.nextLong();
                long Bw = sc.nextLong();
                long Cw = sc.nextLong();
                long Dw = sc.nextLong();
                for(int i=k;i<n;i++)
                {
                    w[i] = ((Aw*w[i-2]+Bw*w[i-1]+Cw)%Dw)+1;
                }

                for(int i=0;i<k;i++)
                {
                    h[i] = sc.nextLong();
                }
                long Ah = sc.nextLong();
                long Bh = sc.nextLong();
                long Ch = sc.nextLong();
                long Dh = sc.nextLong();
                for(int i=k;i<n;i++)
                {
                    h[i] = ((Ah*h[i-2]+Bh*h[i-1]+Ch)%Dh)+1;
                }

                TreeSet<Interval> intervalTree = new TreeSet<>();
                intervalTree.add(new Interval(l[0], l[0]+w[0]));
                long[] p = new long[n];
                p[0] = 2*(w[0]+h[0]);
                for(int i=1;i<n;i++)
                {
                    Interval current = new Interval(l[i], l[i]+w[i]);
                    Interval previous = intervalTree.floor(current);
                    if(previous==null)
                    {
                        previous = intervalTree.ceiling(current);
                    }

                    long start;
                    long extraperimeter=0;
                    final long extraperimeter1 = (2 * (w[i] + h[i]))%mod;
                    if(previous.left<current.left && previous.right<current.left)
                    {
                        start = current.left;
                        Interval next = intervalTree.higher(previous);
                        if(next==null || next.left>current.right)
                        {
                            intervalTree.add(current);
                            extraperimeter = extraperimeter1;
                        }
                        else if(next.left<=current.right)
                        {
                            extraperimeter += ((next.left-current.left)*2)%mod;
                            previous = next;
                            next = intervalTree.higher(next);
                            intervalTree.remove(previous);
                            while(next!=null && next.left<=current.right)
                            {
                                extraperimeter += (2*(next.left-previous.right))%mod;
                                extraperimeter = ((extraperimeter-(2*h[i])%mod)+mod)%mod;
                                previous = next;
                                next = intervalTree.higher(previous);
                                intervalTree.remove(previous);
                            }

                            if(current.right>previous.right)
                            {
                                extraperimeter += (2*(current.right-previous.right))%mod;
                                extraperimeter %= mod;
                            }
                            intervalTree.add(new Interval(start, Math.max(current.right, previous.right)));
                        }
                        extraperimeter %= mod;
                    }
                    else if(previous.left<=current.left && previous.right<=current.right)
                    {
                        start = previous.left;
                        Interval next = intervalTree.higher(previous);
                        intervalTree.remove(previous);
                        if(next==null || next.left>current.right)
                        {
                            extraperimeter += (2*(current.right-previous.right))%mod;
                            extraperimeter %= mod;
                            intervalTree.add(new Interval(start, current.right));
                        }
                        else
                        {
                            while(next!=null && next.left<=current.right)
                            {
                                extraperimeter += (2*(next.left-previous.right))%mod;
                                extraperimeter = (extraperimeter-(2*h[i])%mod)+mod;
                                extraperimeter %= mod;
                                previous = next;
                                next = intervalTree.higher(previous);
                                intervalTree.remove(previous);
                            }

                            if(current.right>previous.right)
                            {
                                extraperimeter += (2*(current.right-previous.right))%mod;
                            }
                            intervalTree.add(new Interval(start, Math.max(current.right, previous.right)));
                        }
                        extraperimeter %= mod;
                    }
                    else if(previous.left<=current.left && previous.right>=current.right)
                    {
                        System.out.print("");
                    }
                    else if(previous.left>=current.left && previous.right<=current.right)
                    {
                        start = current.left;
                        extraperimeter += (2*(previous.left-current.left))%mod;
                        extraperimeter %= mod;
                        intervalTree.remove(previous);

                        Interval next = intervalTree.higher(previous);
                        if(next==null || next.left>current.right)
                        {
                            intervalTree.add(current);
                            extraperimeter += (2*(current.right-previous.right))%mod;
                            extraperimeter %= mod;
                        }
                        else
                        {
                            extraperimeter += (next.left-previous.right)*2;
                            extraperimeter = ((extraperimeter-(2*h[i])%mod)+mod)%mod;
                            previous = next;
                            next = intervalTree.higher(next);
                            intervalTree.remove(previous);
                            while(next!=null && next.left<=current.right)
                            {
                                extraperimeter += (2*(next.left-previous.right))%mod;
                                extraperimeter = (extraperimeter - (2*h[i])%mod)+mod;
                                extraperimeter %= mod;
                                previous = next;
                                next = intervalTree.higher(previous);
                                intervalTree.remove(previous);
                            }

                            if(current.right>previous.right)
                            {
                                extraperimeter += (2*(current.right-previous.right))%mod;
                                extraperimeter %= mod;
                            }
                            intervalTree.add(new Interval(start, Math.max(current.right, previous.right)));
                        }
                        extraperimeter %= mod;
                    }
                    else if(previous.left<=current.right && previous.right>=current.right)
                    {
                        extraperimeter += ((previous.left-current.left)*2)%mod;
                        extraperimeter %= mod;
                        intervalTree.remove(previous);
                        intervalTree.add(new Interval(current.left, previous.right));
                    }
                    else
                    {
                        intervalTree.add(current);
                        extraperimeter = extraperimeter1;
                        extraperimeter %= mod;
                    }

                    p[i] = (p[i-1]+extraperimeter)%mod;
                }

                long product = 1;
                for(int i=0;i<n;i++)
                {
                    product = (product*p[i])%mod;
                }

                pw.println("Case #"+testi+": "+product);
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