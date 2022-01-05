package Codeforces.Round660Div2;

import java.util.*;

public class C_UncleBogdanAndCountryHappiness {
    static class City
    {
        int id;
        long pop;
        long hi;
        long happy;
        long unhappy;
        long totalpop;
        boolean isVisited;
        City parent;
        ArrayList<City> adjacentcity = new ArrayList<>();
        City(int id)
        {
            this.id = id;
            this.isVisited = false;
            this.parent = null;
        }
    }
    static class Country
    {
        ArrayList<City> citylist;
        Country(int n)
        {
            this.citylist = new ArrayList<>(n);
            for(int i=0;i<n;i++)
            {
                citylist.add(new City(i));
            }
        }
        public void addRoad(int xi, int yi)
        {
            City u = citylist.get(xi);
            City v = citylist.get(yi);
            u.adjacentcity.add(v);
            v.adjacentcity.add(u);
        }
        public String dfs(int m)
        {
            City source = citylist.get(0);

            Stack<City> stk = new Stack<>();
            Stack<Integer> ptrstk = new Stack<>();
            stk.push(source);
            ptrstk.push(-1);
            source.isVisited = true;

            while (!stk.isEmpty())
            {
                City cur = stk.pop();
                int ptr = ptrstk.pop();
                if(ptr<cur.adjacentcity.size()-1)
                {
                    ptr++;
                    stk.push(cur);
                    ptrstk.push(ptr);

                    City next = cur.adjacentcity.get(ptr);
                    if(!next.isVisited)
                    {
                        next.parent = cur;
                        stk.push(next);
                        ptrstk.push(-1);
                        next.isVisited = true;
                        next.totalpop = next.pop;
                        if(next.adjacentcity.size()==1)
                        {
                            long happy, unhappy;
                            if(Math.abs(next.hi%2)==0 && next.totalpop%2==0)
                            {
                                happy = (next.totalpop/2+next.hi/2);
                                unhappy = (next.totalpop-happy);
                            }
                            else if(Math.abs(next.hi%2)==1 && next.totalpop%2==1)
                            {
                                if(next.hi<0)
                                {
                                    happy = (next.totalpop/2)-(next.hi/2);
                                }
                                else
                                {
                                    happy = (next.totalpop/2)+(next.hi/2+1);
                                }
                                unhappy = (next.totalpop-happy);
                            }
                            else
                            {
                                return "NO";
                            }
                            next.happy = happy;
                            next.unhappy = unhappy;
                        }
                    }
                }
                else
                {
                    long totalpop = cur.pop;
                    long totalhappy = 0;
                    long totalunhappy = 0;
                    for(City c: cur.adjacentcity)
                        if(c!=cur.parent) {
                            totalpop += c.totalpop;
                            totalhappy += c.happy;
                            totalunhappy += c.unhappy;
                        }

                    long happy, unhappy;
                    if(Math.abs(cur.hi%2)==0 && totalpop%2==0)
                    {
                        happy = (totalpop/2+cur.hi/2);
                        unhappy = (totalpop-happy);
                    }
                    else if(Math.abs(cur.hi%2)==1 && totalpop%2==1)
                    {
                        if(cur.hi<0)
                        {
                            happy = (totalpop/2)-Math.abs(cur.hi/2);
                        }
                        else
                        {
                            happy = (totalpop/2)+(cur.hi/2+1);
                        }
                        unhappy = (totalpop-happy);
                    }
                    else
                    {
                        return "NO";
                    }

                    long happyhere = happy-totalhappy;
                    long unhappyhere = unhappy-totalunhappy;

                    if(happy<totalhappy)
                    {
                        return "NO";
                    }
                    else if(happy+unhappy!=cur.pop+totalhappy+totalunhappy)
                    {
                        return "NO";
                    }
                    else if(happy+unhappy!=totalpop)
                    {
                        return "NO";
                    }
                    else if(happyhere<0 || happyhere+unhappyhere!=cur.pop)
                    {
                        return "NO";
                    }
                    else if(Math.abs(cur.hi)>totalpop)
                    {
                        return "NO";
                    }

                    cur.totalpop = totalpop;
                    cur.happy = happy;
                    cur.unhappy = unhappy;
                }
            }

            if(m!=source.totalpop)
                return "NO";
            return "YES";
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-->0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();

            Country country = new Country(n);

            for(int i=0;i<n;i++)
            {
                country.citylist.get(i).pop = sc.nextLong();
            }
            for(int i=0;i<n;i++)
            {
                country.citylist.get(i).hi = sc.nextLong();
            }

            for(int i=0;i<n-1;i++)
            {
                int xi = sc.nextInt()-1;
                int yi = sc.nextInt()-1;
                country.addRoad(xi, yi);
            }

            System.out.println(country.dfs(m));
        }
    }
}
