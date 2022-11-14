package Hackerrank.Algorithms.Recursion;

import java.io.*;
import java.util.*;

public class CrosswordPuzzle {
    static class Cell{
        int r;
        int c;
        char dir;
        int length;
        String word;
        Cell(int r, int c, char dir, int length){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.length = length;
        }
        public String toString(){
            return this.r+" "+this.c+" "+this.dir+" "+this.length+" "+this.word;
        }
    }
    static HashMap<Integer, ArrayList<Cell>> processCrossWord(char[][] crossword){
        int r = crossword.length;
        int c = crossword[0].length;
        HashMap<Integer, ArrayList<Cell>> hash_cell = new HashMap<>();

        //column-wise
        for(int i=0;i<r;i++){
            int s = 0;
            for(int j=0;j<c;j++){
                if(crossword[i][j]=='-'){
                    if(j==0 || crossword[i][j-1]=='+') {
                        s = 0;
                    }
                    s++;
                }

                if(crossword[i][j]=='+' && (j!=0 && crossword[i][j-1]=='-') && s!=1){
                    ArrayList<Cell> cell_list = hash_cell.getOrDefault(s, new ArrayList<>());
                    cell_list.add(new Cell(i, j-s, 'c', s));
                    hash_cell.put(s, cell_list);
                    s = 0;
                }
            }
        }

        //row-wise
        for(int i=0;i<c;i++){
            int s = 0;
            for(int j=0;j<r;j++){
                if(crossword[j][i]=='-'){
                    if(j==0 || crossword[j-1][i]=='+') {
                        s = 0;
                    }
                    s++;
                }

                if(crossword[j][i]=='+' && (j!=0 && crossword[j-1][i]=='-' && s!=1)){
                    ArrayList<Cell> cell_list = hash_cell.getOrDefault(s, new ArrayList<>());
                    cell_list.add(new Cell(j-s, i, 'r', s));
                    hash_cell.put(s, cell_list);
                    s = 0;
                }
            }
        }

        return hash_cell;
    }
    static void swap(ArrayList<String> wordlist, int i, int j){
        String s = wordlist.get(i);
        wordlist.set(i, wordlist.get(j));
        wordlist.set(j, s);
    }
    static void setCombinations(ArrayList<Cell> cell_list, ArrayList<String> wordlist){
        for(int i=0;i<wordlist.size();i++){
            Cell cell = cell_list.get(i);
            cell.word = wordlist.get(i);
        }
    }
    static boolean isValidCombination(char[][] crossWord,
                                      HashMap<Integer, ArrayList<Cell>> cell_hash){
        int r = crossWord.length;
        int c = crossWord[0].length;
        char[][] duplicate_crossword = new char[r][c];
        for(int i=0;i<r;i++){
            System.arraycopy(crossWord[i], 0, duplicate_crossword[i], 0, c);
        }

        for(int i: cell_hash.keySet()){
            ArrayList<Cell> list = cell_hash.get(i);
            for (Cell cell : list) {
                String word = cell.word;
                int startrow = cell.r;
                int startcol = cell.c;
                for (int k = 0; k < word.length(); k++) {
                    char ch = word.charAt(k);
                    if (cell.dir == 'c') {
                        char present_ch = duplicate_crossword[startrow][startcol + k];
                        if (present_ch == '-') {
                            duplicate_crossword[startrow][startcol + k] = ch;
                        } else {
                            if (present_ch != ch) {
                                return false;
                            }
                        }
                    } else {
                        char present_ch = duplicate_crossword[startrow + k][startcol];
                        if (present_ch == '-') {
                            duplicate_crossword[startrow + k][startcol] = ch;
                        } else {
                            if (present_ch != ch) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        ans_hash = cell_hash;
        answer = duplicate_crossword;
        return true;
    }
    static HashMap<Integer, ArrayList<Cell>> ans_hash;
    static char[][] answer;
    static boolean recurse(char[][] crossWord,
                        ArrayList<Integer> keyList,
                        HashMap<Integer, ArrayList<String>> hashlist,
                        HashMap<Integer, ArrayList<Cell>> hashcell,
                        int keyList_index, int k, int n){
        if(keyList_index>=keyList.size()) {
            return false;
        }

        if(k==n-1) {
            setCombinations(hashcell.get(keyList.get(keyList_index)),
                    hashlist.get(keyList.get(keyList_index)));
            boolean status;
            if(keyList_index<keyList.size()-1)
                status = recurse(crossWord, keyList, hashlist, hashcell,
                    keyList_index+1, 0,
                        hashlist.get(keyList.get(keyList_index+1)).size());
            else {
                status = isValidCombination(crossWord, hashcell);
            }
            return status;
        }

        for(int i=k;i<n;i++){
            ArrayList<String> wordlist = hashlist.get(keyList.get(keyList_index));
            swap(wordlist, i, k);
            boolean status = recurse(crossWord, keyList, hashlist, hashcell, keyList_index, k+1, n);
            if(status)
                return status;
            swap(wordlist, i, k);
        }

        return false;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int r = 10;
        int c = 10;
        char[][] crossword = new char[r+1][c+1];
        for(int i=0;i<r;i++){
            crossword[i] = (sc.next()+"+").toCharArray();
        }
        crossword[10] = "+++++++++++".toCharArray();

        String lastline = sc.next();
        sc = new Scanner(lastline);
        sc.useDelimiter(";");

        ArrayList<String> words = new ArrayList<>();
        while (sc.hasNext()){
            words.add(sc.next());
        }

        HashMap<Integer, ArrayList<String>> hash_list = new HashMap<>();
        for(String s: words){
            int l = s.length();
            ArrayList<String> list = hash_list.getOrDefault(l, new ArrayList<>());
            list.add(s);
            hash_list.put(l, list);
        }

        HashMap<Integer, ArrayList<Cell>> hash_cell = processCrossWord(crossword);
        ArrayList<Integer> keyList = new ArrayList<>(hash_cell.keySet());
        recurse(crossword, keyList, hash_list, hash_cell,
                0, 0,  hash_list.get(keyList.get(0)).size());

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                System.out.print(answer[i][j]);
            }
            System.out.println();
        }
    }
}
