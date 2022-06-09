package CUrBrain_Questions;

import java.io.*;
import java.util.*;

public class ShootPolygon {

    static class Point {
        double x, y;

        Point(double x, double y){
            this.x = x;
            this.y = y;
        }

        public double distance(Point p1, Point p2){
            double del_x = p2.x - p1.x;
            double del_y = p2.y - p1.y;

            return Math.sqrt(del_x*del_x + del_y*del_y);
        }
    }

    static class Line {
        Point p1;
        Point p2;

        double a, b, c;
        double slope;

        Line(double a, double b, double c){
            this.a = a;
            this.b = b;
            this.c = c;

            this.slope = (a / b) * -1;
        }

        Line(Point p1, Point p2){
            this.p1 = p1;
            this.p2 = p2;

            double del_x = p2.x - p1.x;
            double del_y = p2.y - p1.y;

            this.a = del_y;
            this.b = -1 * del_x;
            this.c = p1.y * del_x - p1.x * del_y;
            this.slope = (this.a / this.b) * -1;
        }

        Line(Point p1, double angle){
            double radians = Math.toRadians(angle);
            double slope = Math.tan(radians);

            this.slope = slope;
            this.a = slope;
            this.b = -1;
            this.c = (p1.y - slope * p1.x);
        }
    }

    static boolean isParallel(Line line1, Line line2){
        return line1.slope == line2.slope && line1.slope * line2.slope == -1;
    }

    static List<Line> getParallelLines(Line[] lines, Line shooting_line){

        List<Line> parallel_lines = new ArrayList<>();
        for(Line line: lines){
            if(isParallel(line, shooting_line)){
                parallel_lines.add(line);
            }
        }

        return parallel_lines;
    }

    static Point getIntersectingPoint(Line line1, Line line2){
        double x = (line1.b * line2.c - line2.b * line1.c) / (line2.a * line1.b - line2.b * line1.a);
        double y = (line1.a * line2.c - line2.a * line1.c) / (line2.a * line1.b - line2.b * line1.a);

        return new Point(x, y);
    }

    static boolean inBetween(Point p1, Point p2, Point p3){
        double dist12 = p1.distance(p1, p2);
        double dist23 = p2.distance(p2, p3);
        double dist13 = p1.distance(p1, p3);

        return dist12 + dist23 == dist13;
    }

    static List<Point> getIntersectingPoints(List<Line> lines, Line shooting_line){

        List<Point> intersectingPoints = new ArrayList<>();
        for(Line line: lines){
            Point intersectingPoint = getIntersectingPoint(line, shooting_line);

            if(inBetween(line.p1, intersectingPoint, line.p2)){
                intersectingPoints.add(intersectingPoint);
            }
        }

        return intersectingPoints;
    }

    static double getPerpendicularDistance(Line line, Point point){

        double num = Math.abs(line.a * point.x + line.b * point.y + line.c);
        double deno = Math.sqrt(line.a * line.a + line.b * line.b);

        return num / deno;

    }

    static List<Line> filter_parallel_lines(List<Line> parallel_lines, Point shooting_point){

        List<Line> filtered_parallel_lines = new ArrayList<>();
        for(Line line: parallel_lines){
            if(getPerpendicularDistance(line, shooting_point) == 0){
                filtered_parallel_lines.add(line);
            }
        }

        return filtered_parallel_lines;

    }

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("Input.txt"));

        StringBuilder sb = new StringBuilder();
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            double a = sc.nextDouble();
            double s = sc.nextDouble();
            double x = sc.nextDouble();
            double y = sc.nextDouble();

            Point[] points = new Point[n];
            for(int i=0;i<n;i++){
                points[i] = new Point(sc.nextDouble(), sc.nextDouble());
            }

            Line[] lines = new Line[n];
            for(int i=0;i<n;i++){
                lines[i] = new Line(points[i], points[(i+1)%n]);
            }

            Point starting_point = new Point(x, y);
            Line shooting_line = new Line(starting_point, a);

            List<Line> parallel_lines = getParallelLines(lines, shooting_line);
            Set<Line> parallel_lines_set = new HashSet<>(parallel_lines);

            List<Line> non_parallel_lines = new ArrayList<>();
            for(Line line: lines){
                if(!parallel_lines_set.contains(line)){
                    non_parallel_lines.add(line);
                }
            }

            List<Point> intersecting_points =
                    getIntersectingPoints(non_parallel_lines, shooting_line);

            intersecting_points.sort(
                    Comparator.comparingDouble(p -> starting_point.distance(starting_point, p)));

            List<Line> filtered_parallel_lines = filter_parallel_lines(parallel_lines, starting_point);


        }

        System.out.println(sb);

    }

}
