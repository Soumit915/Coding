import java.util.*;
public class Point {
    int x,y;
    Point()
    {
        x = 0;
        y = 0;
    }
    void readpoint()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the x-coordinate: ");
        this.x = sc.nextInt();

        System.out.print("Enter the y-coordinate: ");
        this.y = sc.nextInt();
    }
    Point midpoint(Point B)
    {
        Point midpoint = new Point();
        midpoint.x = (this.x + B.x)/2;
        midpoint.y = (this.y + B.y)/2;
        return midpoint;
    }
    void displayPoint()
    {
        System.out.println("The midpoint is: ("+x+", "+y+")");
    }
    public static void main(String args[])
    {
        Point p1 = new Point();
        Point p2 = new Point();

        p1.readpoint();
        p2.readpoint();

        Point p3 = p1.midpoint(p2);
        p3.displayPoint();
    }
}

