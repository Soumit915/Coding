import java.util.*;

public class ClosestPairOfPoints {
    static class Point
    {
        int x;
        int y;
        Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    static class xSorter implements Comparator<Point>
    {
        public int compare(Point o1, Point o2) {
            return Double.compare(o1.x,o2.x);
        }
    }

    static class ySorter implements Comparator<Point>
    {
        public int compare(Point a, Point b) {
            return Double.compare(a.y,b.y);
        }
    }

    public static double calcDist(Point a, Point b)
    {
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }
    public static Point[] findClosestStrip(Point[] points, int ll, int mid, int ul, double dm)
    {
        Point[] stripPoints;
        Point[] closestPair = new Point[2];
        double lr = points[mid].x-dm;
        double rr = points[mid].x+dm;
        int lindex = ll;
        int rindex = ul;
        for(int i=mid;i>=ll;i--)
        {
            if(points[i].x<lr)
            {
                lindex = i+1;
                break;
            }
        }
        for(int i=mid+1;i<ul;i++)
        {
            if(points[i].x>rr)
            {
                rindex = i-1;
                break;
            }
        }

        if(rindex-lindex+1<=2)
        {
            closestPair[0] = points[lindex];
            closestPair[1] = points[lindex+1];
            return closestPair;
        }
        stripPoints = new Point[rindex-lindex+1];
        System.arraycopy(points, lindex, stripPoints, 0, rindex + 1 - lindex);
        Arrays.sort(stripPoints, new ySorter());

        double mind = dm;
        for(int i=0;i<stripPoints.length;i++)
        {
            Point pPoint = stripPoints[i];
            double ry = pPoint.y+dm;
            for(int j=i+1;j<Math.min(i+6, stripPoints.length);j++)
            {
                Point cP = stripPoints[j];
                if(cP.y<ry)
                {
                    double d = calcDist(cP,pPoint);
                    mind = Math.min(d,mind);
                    if(Double.compare(mind,d)==0)
                    {
                        closestPair[0] = pPoint;
                        closestPair[1] = cP;
                    }
                }
                else
                    break;
            }
        }

        if(mind==dm)
        {
            return null;
        }

        return closestPair;
    }
    public static Point[] bruteForce(Point[] points, int ll)
    {
        Point[] closestPair = new Point[2];
        double d1 = calcDist(points[ll],points[ll+1]);
        double d2 = calcDist(points[ll],points[ll+2]);
        double d3 = calcDist(points[ll+1],points[ll+2]);
        if(d1<d2)
        {
            closestPair[1] = points[ll+1];
            if(d1<d3)
            {
                closestPair[0] = points[ll];
            }
            else
            {
                closestPair[0] = points[ll+2];
            }
        }
        else
        {
            closestPair[1] = points[ll+2];
            if(d2<d3)
            {
                closestPair[0] = points[ll];
            }
            else
            {
                closestPair[0] = points[ll+1];
            }
        }

        return closestPair;
    }
    public static Point[] findClosest(Point[] points, int ll, int ul)
    {
        if(ul-ll == 2)
        {
            return bruteForce(points, ll);
        }
        else if(ul-ll==1)
        {
            Point[] closestPair = new Point[2];
            closestPair[0] = points[ll];
            closestPair[1] = points[ll+1];
            return closestPair;
        }
        int mid = (ll+ul)/2;
        Point[] lP = findClosest(points,ll,mid);
        Point[] rP = findClosest(points,mid+1,ul);
        double lm = calcDist(lP[0],lP[1]);
        double rm = calcDist(rP[0],rP[1]);
        double dm = Math.min(lm, rm);
        Point[] dP;
        if(Double.compare(dm,lm) == 0)
            dP = lP;
        else
            dP = rP;
        Point[] minstrip = findClosestStrip(points, ll, mid, ul, dm);
        double minstripm = Double.MAX_VALUE;
        if(minstrip!=null)
            minstripm = calcDist(minstrip[0],minstrip[1]);
        dm = Math.min(dm,minstripm);
        if(Double.compare(dm,minstripm)==0)
            return minstrip;
        else
            return dP;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of points : ");
        int n = sc.nextInt();

        Point[] Points = new Point[n];
        for(int i=0;i<n;i++)
        {
            System.out.print("Enter the x-y coordinates of the point : ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            Points[i] = new Point(x,y);
        }

        Arrays.sort(Points, new xSorter());

        Point[] closestPoints = findClosest(Points,0,n-1);
        System.out.println("The closest point in the plane is : ("+closestPoints[0].x+", "+closestPoints[0].y+") and ("+closestPoints[1].x+", "+closestPoints[1].y+")");
        System.out.println("The distance between them is : "+calcDist(closestPoints[0],closestPoints[1]));
    }
}
