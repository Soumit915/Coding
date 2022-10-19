package Codeforces;

import java.util.*;
import java.io.*;

public class BinaryLiterature {

    static class Node{
        int val;
        Node prev;
        Node next;

        Node(int val){
            this.val = val;
        }
    }

    static String getValidNovel(String a, String b){
        int a0 = 0, a1 = 0, b0 = 0, b1 = 0;
        int n = a.length() / 2;

        for(int i=0;i<2*n;i++){
            if(a.charAt(i) == '0')
                a0++;
            else a1++;

            if(b.charAt(i) == '0')
                b0++;
            else b1++;
        }

        Node head;
        if(a0>=n && b0>=n){
            int maxzeros = Math.max(a0, b0);

            Node[] zeros = new Node[maxzeros];
            for(int i=0;i<maxzeros;i++){
                zeros[i] = new Node(0);

                if(i>0){
                    zeros[i].prev = zeros[i-1];
                    zeros[i-1].next = zeros[i];
                }
            }

            head = zeros[0];

            int zc1 = 0, oc1 = 0;
            for(int i=0;i<2*n;i++){
                if(a.charAt(i) == '0'){

                    if(oc1 > 0){
                        Node curhead = null, curptr = null;
                        for(int j=0;j<oc1;j++){
                            Node node = new Node(1);

                            if(curhead == null){
                                curhead = node;
                            }
                            else{
                                curptr.next = node;
                                node.prev = curptr;
                            }
                            curptr = node;
                        }

                        if (zc1 == 0) {
                            curptr.next = head;
                            head.prev = curptr;
                            head = curhead;
                        }
                        else{
                            curptr.next = zeros[zc1-1].next;
                            if(zeros[zc1-1].next != null)
                                zeros[zc1-1].next.prev = curptr;
                            zeros[zc1-1].next = curhead;
                            curhead.prev = zeros[zc1 - 1];
                        }
                    }

                    zc1++;
                    oc1 = 0;
                }
                else{
                    oc1++;
                }
            }
            if(oc1 > 0){
                Node curhead = null, curptr = null;
                for(int j=0;j<oc1;j++){
                    Node node = new Node(1);

                    if(curhead == null){
                        curhead = node;
                    }
                    else{
                        curptr.next = node;
                        node.prev = curptr;
                    }
                    curptr = node;
                }

                if (zc1 == 0) {
                    curptr.next = head;
                    head.prev = curptr;
                    head = curhead;
                }
                else{
                    curptr.next = zeros[zc1-1].next;
                    if(zeros[zc1-1].next != null)
                        zeros[zc1-1].next.prev = curptr;
                    zeros[zc1-1].next = curhead;
                    curhead.prev = zeros[zc1 - 1];
                }
            }

            zc1 = 0; oc1 = 0;
            for(int i=0;i<2*n;i++){
                if(b.charAt(i) == '0'){

                    if(oc1 > 0){
                        Node curhead = null, curptr = null;
                        for(int j=0;j<oc1;j++){
                            Node node = new Node(1);

                            if(curhead == null){
                                curhead = node;
                            }
                            else{
                                curptr.next = node;
                                node.prev = curptr;
                            }
                            curptr = node;
                        }

                        if (zc1 == 0) {
                            curptr.next = head;
                            head.prev = curptr;
                            head = curhead;
                        }
                        else{
                            curptr.next = zeros[zc1-1].next;
                            if(zeros[zc1-1].next != null)
                                zeros[zc1-1].next.prev = curptr;
                            zeros[zc1-1].next = curhead;
                            curhead.prev = zeros[zc1 - 1];
                        }
                    }

                    zc1++;
                    oc1 = 0;
                }
                else{
                    oc1++;
                }
            }
            if(oc1 > 0){
                Node curhead = null, curptr = null;
                for(int j=0;j<oc1;j++){
                    Node node = new Node(1);

                    if(curhead == null){
                        curhead = node;
                    }
                    else{
                        curptr.next = node;
                        node.prev = curptr;
                    }
                    curptr = node;
                }

                if (zc1 == 0) {
                    curptr.next = head;
                    head.prev = curptr;
                    head = curhead;
                }
                else{
                    curptr.next = zeros[zc1-1].next;
                    if(zeros[zc1-1].next != null)
                        zeros[zc1-1].next.prev = curptr;
                    zeros[zc1-1].next = curhead;
                    curhead.prev = zeros[zc1 - 1];
                }
            }

        }
        else{

            int maxones = Math.max(a1, b1);

            Node[] ones = new Node[maxones];
            for(int i=0;i<maxones;i++){
                ones[i] = new Node(1);

                if(i>0){
                    ones[i].prev = ones[i-1];
                    ones[i-1].next = ones[i];
                }
            }

            head = ones[0];

            int zc1 = 0, oc1 = 0;
            for(int i=0;i<2*n;i++){
                if(a.charAt(i) == '1'){

                    if(oc1 > 0){
                        Node curhead = null, curptr = null;
                        for(int j=0;j<oc1;j++){
                            Node node = new Node(0);

                            if(curhead == null){
                                curhead = node;
                            }
                            else{
                                curptr.next = node;
                                node.prev = curptr;
                            }
                            curptr = node;
                        }

                        if (zc1 == 0) {
                            curptr.next = head;
                            head.prev = curptr;
                            head = curhead;
                        }
                        else{
                            curptr.next = ones[zc1-1].next;
                            if(ones[zc1-1].next != null)
                                ones[zc1-1].next.prev = curptr;
                            ones[zc1-1].next = curhead;
                            curhead.prev = ones[zc1 - 1];
                        }
                    }

                    zc1++;
                    oc1 = 0;
                }
                else{
                    oc1++;
                }
            }
            if(oc1 > 0){
                Node curhead = null, curptr = null;
                for(int j=0;j<oc1;j++){
                    Node node = new Node(0);

                    if(curhead == null){
                        curhead = node;
                    }
                    else{
                        curptr.next = node;
                        node.prev = curptr;
                    }
                    curptr = node;
                }

                if (zc1 == 0) {
                    curptr.next = head;
                    head.prev = curptr;
                    head = curhead;
                }
                else{
                    curptr.next = ones[zc1-1].next;
                    if(ones[zc1-1].next != null)
                        ones[zc1-1].next.prev = curptr;
                    ones[zc1-1].next = curhead;
                    curhead.prev = ones[zc1 - 1];
                }
            }

            zc1 = 0; oc1 = 0;
            for(int i=0;i<2*n;i++){
                if(b.charAt(i) == '1'){

                    if(oc1 > 0){
                        Node curhead = null, curptr = null;
                        for(int j=0;j<oc1;j++){
                            Node node = new Node(0);

                            if(curhead == null){
                                curhead = node;
                            }
                            else{
                                curptr.next = node;
                                node.prev = curptr;
                            }
                            curptr = node;
                        }

                        if (zc1 == 0) {
                            curptr.next = head;
                            head.prev = curptr;
                            head = curhead;
                        }
                        else{
                            curptr.next = ones[zc1-1].next;
                            if(ones[zc1-1].next != null)
                                ones[zc1-1].next.prev = curptr;
                            ones[zc1-1].next = curhead;
                            curhead.prev = ones[zc1 - 1];
                        }
                    }

                    zc1++;
                    oc1 = 0;
                }
                else{
                    oc1++;
                }
            }
            if(oc1 > 0){
                Node curhead = null, curptr = null;
                for(int j=0;j<oc1;j++){
                    Node node = new Node(0);

                    if(curhead == null){
                        curhead = node;
                    }
                    else{
                        curptr.next = node;
                        node.prev = curptr;
                    }
                    curptr = node;
                }

                if (zc1 == 0) {
                    curptr.next = head;
                    head.prev = curptr;
                    head = curhead;
                }
                else{
                    curptr.next = ones[zc1-1].next;
                    if(ones[zc1-1].next != null)
                        ones[zc1-1].next.prev = curptr;
                    ones[zc1-1].next = curhead;
                    curhead.prev = ones[zc1 - 1];
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        while(head != null){
            sb.append(head.val);
            head = head.next;
        }

        return sb.toString();
    }

    public static String pad(String s, int n){
        return "0".repeat(Math.max(0, n - s.length())) + s;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-->0){
            int n = sc.nextInt();

            String a = sc.next();
            String b = sc.next();
            String c = sc.next();

            int a0 = 0, a1 = 0, b0 = 0, b1 = 0, c0 = 0, c1 = 0;
            for(int i=0;i<2*n;i++){
                if(a.charAt(i) == '0'){
                    a0++;
                }
                else{
                    a1++;
                }

                if(b.charAt(i) == '0'){
                    b0++;
                }
                else{
                    b1++;
                }

                if(c.charAt(i) == '0'){
                    c0++;
                }
                else{
                    c1++;
                }
            }

            String ans;
            if(a0>=n && b0>=n){
                ans = getValidNovel(a, b);
            }
            else if(a0>=n && c0>=n){
                ans = getValidNovel(a, c);
            }
            else if(b0>=n && c0>=n){
                ans = getValidNovel(b, c);
            }
            else if(a1>=n && b1>=n){
                ans = getValidNovel(a, b);
            }
            else if(a1>=n && c1>=n){
                ans = getValidNovel(a, c);
            }
            else{
                ans = getValidNovel(b, c);
            }

            ans = pad(ans, 3*n);

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
