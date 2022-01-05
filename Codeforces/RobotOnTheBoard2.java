package Codeforces;

import java.io.*;
import java.util.*;

public class RobotOnTheBoard2 {

    static long bfs_BD = 0, bfs_CYCLE = 0, findcycle = 0;

    static class Cell {
        int x;
        int y;
        int bd_val;
        int cycle_val;
        Cell parent;
        int cycleLength;
        ArrayList<Cell> adlist = new ArrayList<>();
        boolean isVisited_cycle;
        boolean isVisited_bfs;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
            this.parent = null;
            this.bd_val = 0;
            this.cycleLength = 0;
            this.isVisited_cycle = false;
            this.isVisited_bfs = false;
        }
    }

    static boolean isValid(int n, int m, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static void hasCycle(Cell source) {
        Stack<Cell> stk = new Stack<>();
        Map<Cell, Integer> inStack = new HashMap<>();

        stk.push(source);
        source.isVisited_cycle = true;
        inStack.put(source, 1);

        while (!stk.isEmpty()) {
            Cell cur = stk.peek();

            if (cur.parent != null) {
                Cell next = cur.parent;
                if (!next.isVisited_cycle) {
                    stk.push(next);
                    next.isVisited_cycle = true;
                    inStack.put(next, inStack.get(cur) + 1);
                } else {
                    if (inStack.containsKey(next)) {
                        int cycleLength = inStack.get(cur) - inStack.get(next) + 1;
                        while (stk.peek() != next) {
                            Cell top = stk.pop();
                            top.cycleLength = cycleLength;
                        }
                        Cell top = stk.pop();
                        top.cycleLength = cycleLength;
                        return;
                    } else {
                        stk.pop();
                        inStack.remove(cur);
                    }
                }
            } else {
                stk.pop();
                inStack.remove(cur);
            }
        }
    }

    static void findCycles(Cell[][] grid) {
        int m = grid[0].length;

        for (Cell[] cells : grid) {
            for (int j = 0; j < m; j++) {
                if (!cells[j].isVisited_cycle) {
                    hasCycle(cells[j]);
                }
            }
        }
    }

    static void bfs_BD(Cell[][] grid) {
        int m = grid[0].length;

        Queue<Cell> q = new LinkedList<>();
        for (Cell[] cells : grid) {
            for (int j = 0; j < m; j++) {
                if (cells[j].parent == null) {
                    q.add(cells[j]);
                    cells[j].bd_val = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            Cell cur = q.remove();
            for (Cell adcell : cur.adlist) {
                q.add(adcell);
                adcell.bd_val = cur.bd_val + 1;
            }
        }
    }

    static void bfs_CYCLE(Cell[][] grid) {
        int m = grid[0].length;

        Queue<Cell> q = new LinkedList<>();
        for (Cell[] cells : grid) {
            for (int j = 0; j < m; j++) {
                if (cells[j].cycleLength > 0) {
                    if (cells[j].adlist.size() > 1)
                        q.add(cells[j]);
                    cells[j].cycle_val = cells[j].cycleLength;
                    cells[j].isVisited_bfs = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Cell cur = q.remove();
            for (Cell adcell : cur.adlist) {
                if (!adcell.isVisited_bfs) {
                    q.add(adcell);
                    adcell.cycle_val = cur.cycle_val + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        Scanner sc = new Scanner(new File("Input.txt"));

        //Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Cell[][] grid = new Cell[n][m];
            for (int i = 0; i < n; i++) {
                for(int j=0;j<m;j++){
                    grid[i][j] = new Cell(i, j);
                }
            }

            for (int i = 0; i < n; i++) {
                String s = sc.next();
                for (int j = 0; j < m; j++) {
                    if (s.charAt(j) == 'U') {
                        if (isValid(n, m, i - 1, j)) {
                            grid[i][j].parent = grid[i - 1][j];
                            grid[i - 1][j].adlist.add(grid[i][j]);
                        }
                    }
                    else if (s.charAt(j) == 'D') {
                        if (isValid(n, m, i + 1, j)) {
                            grid[i][j].parent = grid[i + 1][j];
                            grid[i + 1][j].adlist.add(grid[i][j]);
                        }
                    }
                    else if (s.charAt(j) == 'L') {
                        if (isValid(n, m, i, j - 1)) {
                            grid[i][j].parent = grid[i][j - 1];
                            grid[i][j - 1].adlist.add(grid[i][j]);
                        }
                    }
                    else {
                        if (isValid(n, m, i, j + 1)) {
                            grid[i][j].parent = grid[i][j + 1];
                            grid[i][j + 1].adlist.add(grid[i][j]);
                        }
                    }
                }
            }

            long s1 = System.currentTimeMillis();

            bfs_BD(grid);
            findCycles(grid);
            bfs_CYCLE(grid);

            bfs_BD += (System.currentTimeMillis() - s1);

            int max = -1;
            Cell best = grid[0][0];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (Math.max(grid[i][j].bd_val + 1, grid[i][j].cycle_val) > max) {
                        best = grid[i][j];
                        max = Math.max(grid[i][j].bd_val + 1, grid[i][j].cycle_val);
                    }
                }
            }

            sb.append(best.x + 1).append(" ").append(best.y + 1)
                    .append(" ").append(max).append("\n");
        }

        System.out.println(sb);

        System.out.println(bfs_BD/1000.0);
        /*System.out.println(findcycle/1000.0);
        System.out.println(bfs_CYCLE/1000.0);*/

        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000.0);
    }
}