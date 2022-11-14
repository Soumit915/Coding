package BombTracingVisualizer;

import java.awt.*;
import java.util.*;
import java.io.*;

class Point
{
    int x;
    int y;
    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

class City
{
    char[][] map;
    int nr;
    int nc;
    boolean[][] isVisited;
    ArrayList<Point> policecoordinates = new ArrayList<>();
    ArrayList<Point> bombcoordinates = new ArrayList<>();
    ArrayList<Point> impcoordinates = new ArrayList<>();
    HashMap<Integer, HashMap<Integer, Boolean>> imphasher = new HashMap<>();
    HashMap<Integer, HashMap<Integer, Boolean>> impexploded = new HashMap<>();
    ArrayList<ArrayList<Point>> bomberpath = new ArrayList<>();
    ArrayList<Integer> nextindex = new ArrayList<>();
    Point[][] previous;
    HashSet<Integer> bombexploded;
    HashMap<Integer, Point> bomb_imp = new HashMap<>();
    private int globalcounter;
    private PrintWriter pw1;
    private int timer;

    City(char[][] map)
    {
        this.map = map;
        this.nr = map.length;
        this.nc = map[0].length;
        this.isVisited = new boolean[nr][nc];
        this.previous = new Point[nr][nc];
        this.bombexploded = new HashSet<>();
    }

	//This function checks if the next location is valid and if so it does the corresponding changes.
    public void setPos(Queue<Point> q, int x, int y, Point prev, boolean[][] isVisited,
                       Point[][] previous, int query)
    {
        int nr = isVisited.length;
        int nc = isVisited[0].length;
        if(x>=1 && x<=nr-2 && y>=1 && y<=nc-2 && !isVisited[x][y] &&
                (map[x][y]==' ' || ((map[x][y]=='B' || (map[x][y]=='I' &&
                        impexploded.get(x).get(y))) && query==1) || (map[x][y]=='I' && query==2)))
        {
            isVisited[x][y] = true;
            Point p = new Point(x,y);
            if(map[x][y] == ' ') {
                q.add(p);
                if(query==1)
                    globalcounter++;
            }
            previous[x][y] = prev;
            if((map[x][y]=='B' || (map[x][y]=='I' && impexploded.get(x).get(y))) && query==1)
            {
                Point ptr = previous[x][y];
                while(map[ptr.x][ptr.y]!='P')
                {
                    map[ptr.x][ptr.y] = '*';
                    ptr = previous[ptr.x][ptr.y];
                }

                for(int ind=0;ind<bombcoordinates.size();ind++)
                {
                    Point bp = bombcoordinates.get(ind);
                    if(bp.x==x && bp.y==y && !bombexploded.contains(ind))
                    {
                        pw1.println("At +"+timer+" unit time a police caught a bomber at coordinate ("+x+", "+y+")");
                    }
                }
            }
            else if(map[x][y]=='I' && query==2 && !imphasher.get(x).get(y))
            {
                Stack<Point> pathstk = new Stack<>();
                Point ptr = previous[x][y];
                while(map[ptr.x][ptr.y]!='B')
                {
                    pathstk.push(ptr);
                    ptr = previous[ptr.x][ptr.y];
                }

                ArrayList<Point> path = new ArrayList<>();
                while(!pathstk.isEmpty())
                {
                    path.add(pathstk.pop());
                }
                bomberpath.add(path);
                nextindex.add(0);
                imphasher.get(x).put(y,true);
                int bi;
                for(bi=0;bi<bombcoordinates.size();bi++)
                {
                    Point bc = bombcoordinates.get(bi);
                    if(bc.x==ptr.x && bc.y==ptr.y)
                        break;
                }
                for(Point impc: impcoordinates)
                    if(impc.x==x && impc.y==y)
                        bomb_imp.put(bi,impc);
            }
        }
    }
    //This function gets a new Bombers path
    public void getBomberPath(int i)
    {
        //int i = bombcoordinates.size()-1;
        Point p1 = bombcoordinates.get(i);
        Queue<Point> q = new LinkedList<>();
        boolean[][] bombVisited = new boolean[nr][nc];
        Point[][] previous = new Point[nr][nc];
        q.add(p1);
        previous[p1.x][p1.y] = null;
        bombVisited[p1.x][p1.y] = true;

        while(!q.isEmpty())
        {
            Point p = q.remove();
            setPos(q, p.x-1, p.y,p,bombVisited, previous,2);         //Checking for top cell
            if(bomberpath.size()==i+1)
                break;
            setPos(q, p.x, p.y-1,p,bombVisited, previous,2);         //Checking for left cell
            if(bomberpath.size()==i+1)
                break;
            setPos(q, p.x, p.y+1,p,bombVisited, previous,2);         //Checking for right cell
            if(bomberpath.size()==i+1)
                break;
            setPos(q, p.x+1, p.y,p,bombVisited, previous,2);         //Checking for bottom cell
            if(bomberpath.size()==i+1)
                break;
            setPos(q, p.x-1, p.y-1,p,bombVisited, previous,2);    //Checking for top-left cell
            if(bomberpath.size()==i+1)
                break;
            setPos(q, p.x-1, p.y+1,p,bombVisited, previous,2);    //Checking for top-right cell
            if(bomberpath.size()==i+1)
                break;
            setPos(q, p.x+1, p.y-1,p,bombVisited, previous,2);    //Checking for bottom-left cell
            if(bomberpath.size()==i+1)
                break;
            setPos(q, p.x+1, p.y+1,p,bombVisited, previous,2);    //Checking for bottom-right cell
            if(bomberpath.size()==i+1)
                break;
        }
        if(bomberpath.size()==i)
        {
            bomberpath.add(null);
            nextindex.add(-1);
        }
    }
    //This function gets a new Bomber
    public void getNewBomber(Point nextp, int nexti, int i)
    {
        int divprob = (int) (Math.random()*(16));
        boolean flag = false;
        int initx = -1;
        int inity = -1;
        if(divprob==0)
        {
            if((nexti+1<bomberpath.get(i).size()) && bomberpath.get(i).get(nexti+1).x!=nextp.x-1
                    && bomberpath.get(i).get(nexti+1).y!=nextp.y-1 && map[nextp.x-1][nextp.y-1]==' ')
            {
                flag = true;initx = nextp.x-1;inity = nextp.y-1;
            }
            else if((nexti+1<bomberpath.get(i).size()) && bomberpath.get(i).get(nexti+1).x!=nextp.x-1
                    && bomberpath.get(i).get(nexti+1).y!=nextp.y && map[nextp.x-1][nextp.y]==' ')
            {
                flag = true;initx = nextp.x-1;inity = nextp.y;
            }
            else if((nexti+1<bomberpath.get(i).size()) && bomberpath.get(i).get(nexti+1).x!=nextp.x-1
                    && bomberpath.get(i).get(nexti+1).y!=nextp.y+1 && map[nextp.x-1][nextp.y+1]==' ')
            {
                flag = true;initx = nextp.x-1;inity = nextp.y+1;
            }
            else if((nexti+1<bomberpath.get(i).size()) && bomberpath.get(i).get(nexti+1).x!=nextp.x
                    && bomberpath.get(i).get(nexti+1).y!=nextp.y-1 && map[nextp.x][nextp.y-1]==' ')
            {
                flag = true;initx = nextp.x;inity = nextp.y-1;
            }
            else if((nexti+1<bomberpath.get(i).size()) && bomberpath.get(i).get(nexti+1).x!=nextp.x
                    && bomberpath.get(i).get(nexti+1).y!=nextp.y+1 && map[nextp.x][nextp.y+1]==' ')
            {
                flag = true;initx = nextp.x;inity = nextp.y+1;
            }
            else if((nexti+1<bomberpath.get(i).size()) && bomberpath.get(i).get(nexti+1).x!=nextp.x+1
                    && bomberpath.get(i).get(nexti+1).y!=nextp.y-1 && map[nextp.x+1][nextp.y-1]==' ')
            {
                flag = true;initx = nextp.x+1;inity = nextp.y-1;
            }
            else if((nexti+1<bomberpath.get(i).size()) && bomberpath.get(i).get(nexti+1).x!=nextp.x+1
                    && bomberpath.get(i).get(nexti+1).y!=nextp.y && map[nextp.x+1][nextp.y]==' ')
            {
                flag = true;initx = nextp.x+1;inity = nextp.y;
            }
            else if((nexti+1<bomberpath.get(i).size()) && bomberpath.get(i).get(nexti+1).x!=nextp.x+1
                    && bomberpath.get(i).get(nexti+1).y!=nextp.y+1 && map[nextp.x+1][nextp.y+1]==' ')
            {
                flag = true;initx = nextp.x+1;inity = nextp.y+1;
            }
        }

        if(flag)
        {
            Point newbc = new Point(initx, inity);
            bombcoordinates.add(newbc);
            map[initx][inity] = 'B';
            getBomberPath(bombcoordinates.size()-1);
        }
    }

    //This function move the bombers
    public void moveBombers()
    {
        for(int i=0;i<bombcoordinates.size();i++)
        {
            Point p = bombcoordinates.get(i);
            if(isVisited[p.x][p.y])
                continue;
            int nexti = nextindex.get(i);
            //Condition when bomb is exploded
            if(nexti!=-1 && nexti==bomberpath.get(i).size())
            {
                nextindex.set(i,nexti+1);
                map[p.x][p.y] = ' ';
                bombexploded.add(i);
                bombcoordinates.set(i,bomb_imp.get(i));
                Point coor = bombcoordinates.get(i);
                impexploded.get(coor.x).put(coor.y,true);
                pw1.println("At +"+timer+" unit time bomber at coordinate ("+coor.x+", "+coor.y+")");
            }
            if(nexti==-1 || nexti>=bomberpath.get(i).size())
                continue;
            Point nextp = bomberpath.get(i).get(nexti);
            if(nextp==null || isVisited[nextp.x][nextp.y])
                continue;

            //Check if the movement is possible, i.e., if there is any collision
            if(map[nextp.x][nextp.y]=='B')
                continue;

            //Random Division Generetor
            getNewBomber(nextp, nexti, i);

            //Setting the next coordinates for the bomber
            bombcoordinates.set(i, nextp);
            map[p.x][p.y] = ' ';
            map[nextp.x][nextp.y] = 'B';
            nextindex.set(i,nexti+1);
        }
    }
    public void setPos(Queue<Point> q, int x, int y, Point prev, boolean[][] isVisited,
                       Point[][] previous)
    {
        int nr = isVisited.length;
        int nc = isVisited[0].length;
        if(x>=1 && x<=nr-2 && y>=1 && y<=nc-2 && !isVisited[x][y] &&
                (map[x][y]==' ' || map[x][y]=='B'))
        {
            isVisited[x][y] = true;
            Point p = new Point(x,y);
            if(map[x][y] == ' ') {
                q.add(p);
            }
            previous[x][y] = prev;
            if(map[x][y]=='B')
            {
                ArrayList<Point> path = new ArrayList<>();
                Point ptr = previous[x][y];
                while(map[ptr.x][ptr.y]!='I')
                {
                    path.add(ptr);
                    ptr = previous[ptr.x][ptr.y];
                }

                boolean flag = true;
                for(int i=0;i<bombcoordinates.size();i++)
                {
                    Point bp = bombcoordinates.get(i);
                    if(bp.x==x && bp.y==y)
                    {
                        if(bomberpath.get(i)!=null)
                        {
                            flag = false;
                            break;
                        }
                        bomberpath.set(i,path);
                        nextindex.set(i,0);
                        bomb_imp.put(i,ptr);
                        break;
                    }
                }
                if(flag) {
                    imphasher.get(ptr.x).put(ptr.y, true);
                }
            }
        }
    }
    public void getBombersPath()
    {
        for(int i=0;i<bombcoordinates.size();i++)
        {
            bomberpath.add(null);
            nextindex.add(-1);
        }

        for (Point p1 : impcoordinates) {
            Queue<Point> q = new LinkedList<>();
            boolean[][] impVisited = new boolean[nr][nc];
            Point[][] previous = new Point[nr][nc];
            q.add(p1);
            previous[p1.x][p1.y] = null;
            impVisited[p1.x][p1.y] = true;

            while (!q.isEmpty()) {
                Point p = q.remove();
                setPos(q, p.x - 1, p.y, p, impVisited, previous);         //Checking for top cell
                if (imphasher.get(p1.x).get(p1.y))
                    break;
                setPos(q, p.x, p.y - 1, p, impVisited, previous);         //Checking for left cell
                if (imphasher.get(p1.x).get(p1.y))
                    break;
                setPos(q, p.x, p.y + 1, p, impVisited, previous);         //Checking for right cell
                if (imphasher.get(p1.x).get(p1.y))
                    break;
                setPos(q, p.x + 1, p.y, p, impVisited, previous);         //Checking for bottom cell
                if (imphasher.get(p1.x).get(p1.y))
                    break;
                setPos(q, p.x - 1, p.y - 1, p, impVisited, previous);    //Checking for top-left cell
                if (imphasher.get(p1.x).get(p1.y))
                    break;
                setPos(q, p.x - 1, p.y + 1, p, impVisited, previous);    //Checking for top-right cell
                if (imphasher.get(p1.x).get(p1.y))
                    break;
                setPos(q, p.x + 1, p.y - 1, p, impVisited, previous);    //Checking for bottom-left cell
                if (imphasher.get(p1.x).get(p1.y))
                    break;
                setPos(q, p.x + 1, p.y + 1, p, impVisited, previous);    //Checking for bottom-right cell
                if (imphasher.get(p1.x).get(p1.y))
                    break;
            }
        }
    }

    //This function returns the index of the bomb at position (x,y)
    public int getBombIndex(int x, int y)
    {
        for(int i=0;i<bombcoordinates.size();i++)
        {
            Point p = bombcoordinates.get(i);
            if(p.x==x && p.y==y)
                return i;
        }
        return -1;
    }

    //This is the core function that solves the entire tracing of the bomb-visualizer.
    public void solve(int timeLimit, DrawingPanel panel, PrintWriter pw1) throws InterruptedException
    {
        Queue<Point> q = new LinkedList<>();
        this.timer = 0;
        this.pw1 = pw1;
        for(int i=0;i<nr;i++)
        {
            for(int j=0;j<nc;j++)
            {
                if(map[i][j]=='P')
                {
                    Point p = new Point(i,j);
                    q.add(p);
                    previous[i][j] = null;
                    policecoordinates.add(p);
                }
                else if(map[i][j]=='B')
                {
                    Point p = new Point(i,j);
                    bombcoordinates.add(p);
                }
                else if(map[i][j]=='I')
                {
                    Point p = new Point(i,j);
                    impcoordinates.add(p);
                    if(imphasher.containsKey(i))
                    {
                        HashMap<Integer, Boolean> hash = imphasher.get(i);
                        HashMap<Integer, Boolean> explodehash = impexploded.get(i);
                        hash.put(j,false);
                        explodehash.put(j,false);
                    }
                    else
                    {
                        HashMap<Integer, Boolean> hash = new HashMap<>();
                        HashMap<Integer, Boolean> explodehash = new HashMap<>();
                        hash.put(j,false);
                        imphasher.put(i,hash);
                        explodehash.put(j,false);
                        impexploded.put(i,explodehash);
                    }
                }
            }
        }

        getBombersPath();
        Graphics pen = panel.getGraphics();
        pen.setFont(new Font("Consolas",Font.BOLD,14));
        pen.setColor(new Color(0,0,0));

        int localcounter = policecoordinates.size();
        while(!q.isEmpty())
        {
            Point p = q.remove();
            localcounter--;
            setPos(q, p.x-1, p.y,p,isVisited, previous,1);         //Checking for top cell
            setPos(q, p.x, p.y-1,p,isVisited, previous,1);         //Checking for left cell
            setPos(q, p.x, p.y+1,p,isVisited, previous,1);         //Checking for right cell
            setPos(q, p.x+1, p.y,p,isVisited, previous,1);         //Checking for bottom cell
            setPos(q, p.x-1, p.y-1,p,isVisited, previous,1);    //Checking for top-left cell
            setPos(q, p.x-1, p.y+1,p,isVisited, previous,1);    //Checking for top-right cell
            setPos(q, p.x+1, p.y-1,p,isVisited, previous,1);    //Checking for bottom-left cell
            setPos(q, p.x+1, p.y+1,p,isVisited, previous,1);    //Checking for bottom-right cell
            if(localcounter == 0)
            {
                timer++;
                if(timer%timeLimit==0)
                {
                    //Print in file that all static bombs have exploded.
                    for(int i=0;i<bomberpath.size();i++) {
                        Point bc = bombcoordinates.get(i);
                        if (bomberpath.get(i) == null && !bombexploded.contains(i) && !isVisited[bc.x][bc.y])
                        {
                            bombexploded.add(i);
                            Point ep = bombcoordinates.get(i);
                            pw1.println("At +"+timer+" unit time static bomber at coordinate ("+ep.x+", "+ep.y+")");
                        }
                    }
                }
                panel.clear();
                localcounter = globalcounter;
                globalcounter = 0;

                moveBombers();
                for(int i=0;i<nr;i++)
                {
                    for(int j=0;j<nc;j++) {
                        if((map[i][j]=='I' && impexploded.get(i).get(j)) ||
                                (map[i][j]=='B' && bombexploded.contains(getBombIndex(i,j))))
                        {
                            pen.setColor(new Color(255,0,0));
                            pen.drawString("X", 10 + (j * 8), 10 + (i * 16));
                            pen.setColor(new Color(106, 99, 99, 60));
                            pen.fillOval(10 + (j * 8)-45,10 + (i * 16)-55,100,100);
                        }
                        else if (map[i][j] == ' ' && isVisited[i][j]) {
                            pen.setColor(new Color(201,201,201));
                            pen.drawString(".", 10 + (j * 8), 10 + (i * 16));
                        }
                        else if(map[i][j]=='*')
                        {
                            pen.setColor(new Color(231,214,5));
                            pen.drawString("*", 10 + (j * 8), 10 + (i * 16));
                        }
                        else if(map[i][j]=='B')
                        {
                            pen.setColor(new Color(255,0,0));
                            pen.drawString("B", 10 + (j * 8), 10 + (i * 16));
                        }
                        else if(map[i][j]=='P')
                        {
                            pen.setColor(new Color(0,255,0));
                            pen.drawString("P", 10 + (j * 8), 10 + (i * 16));
                        }
                        else if(map[i][j]=='I')
                        {
                            pen.setColor(new Color(0,0,255));
                            pen.drawString("I", 10 + (j * 8), 10 + (i * 16));
                        }
                        else {
                            pen.setColor(new Color(0,0,0));
                            pen.drawString(String.valueOf(map[i][j]), 10 + (j * 8), 10 + (i * 16));
                        }
                    }
                    //sb.append("\n");
                    //pen.drawString(String.valueOf(sb),20,20+(i*16));
                }
                //Provide output to file with a time halt
                Thread.sleep(1000);
            }
        }
    }
}

public class MainClass
{
	public static void loading(DrawingPanel panel) throws InterruptedException {
	    Graphics pen = panel.getGraphics();
        pen.setFont(new Font("Algerian",Font.BOLD,25));
        pen.setColor(new Color(0,0,255));
        pen.drawString("Loading Map",500,215);
        Thread.sleep(750);
        pen.drawString(".",750,215);
        Thread.sleep(750);
        pen.drawString(".",758,215);
        Thread.sleep(750);
        pen.drawString(".",766,215);
        Thread.sleep(750);
        panel.clear();
        pen.drawString("Loading Map",500,215);
        Thread.sleep(750);
        pen.drawString(".",750,215);
        Thread.sleep(750);
        pen.drawString(".",758,215);
        Thread.sleep(750);
        pen.drawString(".",766,215);
        Thread.sleep(750);
    }
    public static void printWelcomePage(DrawingPanel panel) throws InterruptedException {
        String sb = "Welcome to the Bomb-Tracing Visualizer";
        Graphics pen = panel.getGraphics();
        pen.setFont(new Font("Algerian",Font.BOLD,25));
        pen.setColor(new Color(0,0,255));
        pen.drawString(sb,350,200);
        Thread.sleep(1000);
        pen.drawString("Starting in ",530,230);
        pen.drawString(String.valueOf(5),725,230);
        Thread.sleep(1000);
        for(int i=4;i>=1;i--)
        {
            panel.clear();
            pen.drawString(sb,350,200);
            pen.drawString("Starting in ",530,230);
            pen.drawString(String.valueOf(i),725,230);
            Thread.sleep(1000);
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
	    sQuadRa s4 = new sQuadRa();

	    FileReader fr;
	    BufferedReader br;
	    boolean flag = true;
        String path;
	    while(true)
        {
            if(flag)
            {
                System.out.print("Enter the correct path and name of the map, " +
                        "if nothing is entered, default path and name (Map.txt) will get loaded.\n" +
                        "You can skip by simply pressing the enter key once : ");
                path = s4.readLine();
                if(path.equals(""))
                    path = "Map.txt";
            }
            else
            {
                System.out.println("Enter the correct path and name of the map: ");
                path = s4.readLine();
            }

            if(!path.endsWith(".txt"))
            {
                System.out.println("Sorry! Suitable extension not found. Try to provide a (.txt) file format.");
                continue;
            }
            try
            {
                fr = new FileReader(path);
                br = new BufferedReader(fr);
                System.out.println("Map successfully loaded.");
                break;
            }
            catch (FileNotFoundException e)
            {
                if(path.equals("Map.txt"))
                {
                    System.out.print("Sorry, unable to load map from default path. Make sure Map.txt is present " +
                            "in the same folder but not in the same package. If problem still persists try to " +
                            "specify the full path of the map. " +
                            "For ex. F:\\SOUMIT SAHA\\Programe Career\\Java\\IntellIJ Idea\\Map.txt");
                    flag = false;
                }
                else
                {
                    System.out.print("Sorry, unable to load map from specified path. Make sure specified is valid. " +
                            "Try Again. If problem still persists try to " +
                            "specify the full path of the map. " +
                            "For ex. F:\\SOUMIT SAHA\\Programe Career\\Java\\IntellIJ Idea\\Map.txt");
                }
            }
        }

        FileWriter fw = new FileWriter("OutputPath.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        FileWriter fw1 = new FileWriter("Report.txt");
        BufferedWriter bw1 = new BufferedWriter(fw1);
        PrintWriter pw1 = new PrintWriter(bw1);

        String a;
        ArrayList<String> cityOutlay = new ArrayList<>();
        while((a = br.readLine()) != null)
        {
            cityOutlay.add(a);
        }

        char[][] map = new char[cityOutlay.size()][cityOutlay.get(0).length()];
        for(int i=0;i<cityOutlay.size();i++)
        {
            char[] row = cityOutlay.get(i).toCharArray();
            map[i] = row;
        }

        System.out.print("Enter the time-limit of the bomb explosions: ");
        int timeLimit = s4.nextInt();
        if(timeLimit<=0)
        {
            timeLimit = Integer.MAX_VALUE;
        }

        DrawingPanel panel = new DrawingPanel(1600,1600);
        panel.setAlwaysOnTop(true);
        printWelcomePage(panel);
        panel.clear();loading(panel);
        panel.clear();

        panel.toFront();

        City city = new City(map);
        city.solve(timeLimit, panel, pw1);

        for(int i=0;i<cityOutlay.size();i++)
        {
            for(int j=0;j<cityOutlay.get(0).length();j++)
            {
                pw.print(map[i][j]);
            }
            pw.println();
        }

        HashMap<Integer, HashMap<Integer, Boolean>> impexploded = city.impexploded;
        Graphics pen = panel.getGraphics();
        panel.clear();
        pen.setFont(new Font("Consolas",Font.BOLD,14));
        pen.setColor(new Color(0,0,0));
        for(int i=0;i<cityOutlay.size();i++)
        {
            for(int j=0;j<cityOutlay.get(0).length();j++) {
                if((map[i][j]=='I' && impexploded.get(i).get(j)) ||
                        (map[i][j]=='B' && city.bombexploded.contains(city.getBombIndex(i,j))))
                {
                    pen.setColor(new Color(255,0,0));
                    pen.drawString("X", 10 + (j * 8), 10 + (i * 16));
                    pen.setColor(new Color(106, 99, 99, 60));
                    pen.fillOval(10 + (j * 8)-45,10 + (i * 16)-55,100,100);
                }
                else if(map[i][j]=='*')
                {
                    pen.setColor(new Color(231,214,5));
                    pen.drawString("*", 10 + (j * 8), 10 + (i * 16));
                }
                else if(map[i][j]=='B')
                {
                    pen.setColor(new Color(255,0,0));
                    pen.drawString("B", 10 + (j * 8), 10 + (i * 16));
                }
                else if(map[i][j]=='P')
                {
                    pen.setColor(new Color(0,255,0));
                    pen.drawString("P", 10 + (j * 8), 10 + (i * 16));
                }
                else if(map[i][j]=='I')
                {
                    pen.setColor(new Color(0,0,255));
                    pen.drawString("I", 10 + (j * 8), 10 + (i * 16));
                }
                else {
                    pen.setColor(new Color(0,0,0));
                    pen.drawString(String.valueOf(map[i][j]), 10 + (j * 8), 10 + (i * 16));
                }
            }
        }

        pw1.close();
        bw1.close();
        fw1.close();

        pw.close();
        bw.close();
        fw.close();

        br.close();
        fr.close();
    }
    static class sQuadRa
    {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public sQuadRa()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

    }
}
