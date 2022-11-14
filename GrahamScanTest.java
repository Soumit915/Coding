import java.util.*;

public class GrahamScanTest
{
    static class Point implements Comparable<Point>
    {
        int x;
        int y;
        float cosine;
        int dx;
        public int compareTo(Point a)
        {
            int k = Float.compare(this.cosine,a.cosine)*-1;
            if(k==0)
            {
                return Double.compare(this.dx,a.dx);
            }
            else
                return k;
        }
    }

    static class Sorter implements Comparator<Point>
    {
        public int compare(Point a, Point b)
        {
            if(Double.compare(a.y,b.y)==0)
            {
                return Double.compare(a.x,b.x);
            }
            else
                return Double.compare(a.y,b.y);
        }
    }

    static class ConvexHull
    {
        Stack<Point> hull = new Stack<>();
        public void getHull(Point[] points)
        {
            int n = points.length;
            hull.push(points[0]);
            hull.push(points[1]);
            for(int i=2;i<n;i++)
            {
                Point p3 = points[i];
                while(hull.size()>1)
                {
                    Point p2 = hull.pop();
                    int x1 = hull.peek().x;
                    int y1 = hull.peek().y;
                    int x2 = p2.x;
                    int y2 = p2.y;
                    int x3 = p3.x;
                    int y3 = p3.y;
                    int d = (x1-x2)*(y2-y3)-(x2-x3)*(y1-y2);

                    if(d>=0)
                    {
                        hull.push(p2);
                        break;
                    }
                }
                hull.push(p3);
            }

            Point p3 = points[0];
            while(hull.size()>1)
            {
                Point p2 = hull.pop();
                int x1 = hull.peek().x;
                int y1 = hull.peek().y;
                int x2 = p2.x;
                int y2 = p2.y;
                int x3 = p3.x;
                int y3 = p3.y;
                int d = (x1-x2)*(y2-y3)-(x2-x3)*(y1-y2);

                if(d>=0)
                {
                    hull.push(p2);
                    break;
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of points in the graph : ");
        int n = sc.nextInt();

        Point[] Points = new Point[n];
        for(int i=0;i<n;i++)
        {
            System.out.print("Enter the x and y coordinates of the " + (i+1) + "th point : ");
            Point p = new Point();
            p.x = sc.nextInt();
            p.y = sc.nextInt();
            Points[i] = p;
        }

        Point pivotPoint = Collections.min(Arrays.asList(Points), new Sorter());
        for(int i=0;i<n;i++)
        {
            if(Points[i].x == pivotPoint.x && Points[i].y == pivotPoint.y)
            {
                Points[i].cosine = 1;
                Points[i].dx = 0;
                continue;
            }
            Points[i].cosine = (float) ((Points[i].x-pivotPoint.x)/Math.sqrt(Math.pow((Points[i].x-pivotPoint.x),2)+Math.pow((Points[i].y-pivotPoint.y),2)));
            Points[i].dx = Math.abs(Points[i].x-pivotPoint.x);
        }
        Arrays.sort(Points);

        ConvexHull ch = new ConvexHull();
        ch.getHull(Points);

        System.out.println("\nThe vertices of the convex polygon in the anticlockwise direction is : ");
        for(Point p: ch.hull)
        {
            System.out.println(p.x+" "+p.y);
        }
    }
}
