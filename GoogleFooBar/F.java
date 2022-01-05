package GoogleFooBar;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class F {
    static long gcd(long a, long b){
        if(a%b==0){
            return b;
        }
        else return gcd(b, a%b);
    }
    static long lcm(long a, long b){
        return (a*b)/gcd(a, b);
    }
    static class Probability{
        long num;
        long deno;
        Probability(long num, long deno){
            if(deno==0){
                this.num = 1;
                this.deno = 0;
            }
            else{
                long gcd = gcd(num , deno);
                num /= gcd;
                deno /= gcd;

                this.num = num;
                if(num==0)
                    this.deno = 1;
                else this.deno = deno;

                if(this.deno<0){
                    this.deno = Math.abs(this.deno);
                    this.num *= -1;
                }

                if(this.deno==0){
                    this.num = 0;
                    this.deno = 1;
                }
            }
        }
        public String toString(){
            return this.num+"/"+this.deno;
        }
        public Probability add(Probability p){
            if(this.deno<0){
                this.deno = Math.abs(this.deno);
                this.num *= -1;
            }
            if(p.deno<0){
                p.deno = Math.abs(p.deno);
                p.num *= -1;
            }

            long lcm = lcm(this.deno, p.deno);
            long num = ((lcm/this.deno)*this.num) + ((lcm/p.deno)*p.num);
            long common = gcd(num, lcm);
            lcm /= common;
            num /= common;

            if(lcm<0){
                lcm = Math.abs(lcm);
                num *= -1;
            }
            return new Probability(num, lcm);
        }
        public Probability subtract(Probability p){
            if(this.deno<0){
                this.deno = Math.abs(this.deno);
                this.num *= -1;
            }
            if(p.deno<0){
                p.deno = Math.abs(p.deno);
                p.num *= -1;
            }

            long lcm = lcm(this.deno, p.deno);
            long num = ((lcm/this.deno)*this.num) - ((lcm/p.deno)*p.num);
            long common = gcd(num, lcm);
            lcm /= common;
            num /= common;

            if(lcm<0){
                lcm = Math.abs(lcm);
                num *= -1;
            }
            return new Probability(num, lcm);
        }
        public Probability multiply(Probability p){
            if(this.deno<0){
                this.deno = Math.abs(this.deno);
                this.num *= -1;
            }
            if(p.deno<0){
                p.deno = Math.abs(p.deno);
                p.num *= -1;
            }

            long num = this.num * p.num;
            long deno = this.deno * p.deno;
            long gcd = gcd(num, deno);
            num /= gcd;
            deno /= gcd;

            if(deno<0){
                deno = Math.abs(deno);
                num *= -1;
            }
            return new Probability(num, deno);
        }
        public Probability divide(Probability p){
            if(this.deno<0){
                this.deno = Math.abs(this.deno);
                this.num *= -1;
            }
            if(p.deno<0){
                p.deno = Math.abs(p.deno);
                p.num *= -1;
            }
            if(p.num==0){
                return new Probability(0, 1);
            }

            long num = this.num * p.deno;
            long deno = this.deno * p.num;
            long gcd = gcd(num , deno);
            num /= gcd;
            deno /= gcd;

            if(deno<0){
                deno = Math.abs(deno);
                num *= -1;
            }

            return new Probability(num, deno);
        }
    }
    static void subtractFromIdentity(Probability[][] Q){
        for(int i=0;i<Q.length;i++){
            for(int j=0;j<Q.length;j++){
                if(i==j){
                    Q[i][j] = (new Probability(1, 1)).subtract(Q[i][j]);
                }
                else{
                    Q[i][j] = (new Probability(0, 1)).subtract(Q[i][j]);
                }
            }
        }
    }
    static Probability determinant(Probability[][] M){
        int n = M.length;
        if(n==1)
            return M[0][0];

        if(n==2){
            return M[0][0].multiply(M[1][1])
                    .subtract(M[0][1].multiply(M[1][0]));
        }

        Probability[][] adjugate = new Probability[n][n];
        int i = 0;
        for(int j=0;j<n;j++){
            Probability[][] matrix = new Probability[n-1][n-1];
            for(int i1=0;i1<n-1;i1++){
                for(int j1=0;j1<n-1;j1++){
                    if(i1>=i){
                        if(j1>=j){
                            matrix[i1][j1] = M[i1+1][j1+1];
                        }
                        else{
                            matrix[i1][j1] = M[i1+1][j1];
                        }
                    }
                    else{
                        if(j1>=j){
                            matrix[i1][j1] = M[i1][j1+1];
                        }
                        else{
                            matrix[i1][j1] = M[i1][j1];
                        }
                    }
                }
            }

            adjugate[i][j] = determinant(matrix);
            adjugate[i][j].num *= Math.pow(-1, i+j);
        }

        Probability det = new Probability(0, 1);
        for(i=0;i<n;i++){
            det = det.add(M[0][i].multiply(adjugate[0][i]));
        }

        return det;
    }
    static void transpose(Probability[][] Q){
        int n = Q.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                Probability temp = Q[i][j];
                Q[i][j] = Q[j][i];
                Q[j][i] = temp;
            }
        }
    }
    static Probability[][] invert(Probability[][] Q){
        int n = Q.length;

        if(n==1){
            Probability[][] adjugate = new Probability[n][n];
            adjugate[0][0] = new Probability(Q[0][0].deno, Q[0][0].num);
            return adjugate;
        }

        Probability[][] adjugate = new Probability[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Probability[][] matrix = new Probability[n-1][n-1];
                for(int i1=0;i1<n-1;i1++){
                    for(int j1=0;j1<n-1;j1++){
                        if(i1>=i){
                            if(j1>=j){
                                matrix[i1][j1] = Q[i1+1][j1+1];
                            }
                            else{
                                matrix[i1][j1] = Q[i1+1][j1];
                            }
                        }
                        else{
                            if(j1>=j){
                                matrix[i1][j1] = Q[i1][j1+1];
                            }
                            else{
                                matrix[i1][j1] = Q[i1][j1];
                            }
                        }
                    }
                }

                adjugate[i][j] = determinant(matrix);
                adjugate[i][j].num *= Math.pow(-1, i+j);
            }
        }

        transpose(adjugate);
        Probability detQ = determinant(Q);

        Probability[][] inverse = new Probability[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                inverse[i][j] = adjugate[i][j].divide(detQ);
            }
        }

        return inverse;
    }
    public static int[] solution(int[][] input) {
        int n = input.length;
        Probability[][] mat = new Probability[n][n];
        ArrayList<Integer> absorbing_states = new ArrayList<>();
        for(int i=0;i<n;i++){
            long sum = 0;
            int count = 0;
            for(int j=0;j<n;j++){
                sum += input[i][j];
                if(input[i][j]>0)
                    count++;
            }
            for(int j=0;j<n;j++){
                mat[i][j] = new Probability(input[i][j], sum);
            }
            if(count<=0)
                absorbing_states.add(i);
        }

        HashMap<Integer, Integer> indexmap = new HashMap<>();
        Probability[][] absorbing = new Probability[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                absorbing[i][j] = new Probability(0, 1);
            }
        }
        for(int i=0;i<absorbing_states.size();i++){
            absorbing[i][i] = new Probability(1, 1);
            indexmap.put(absorbing_states.get(i), i);
        }

        for(int i=0;i<n;i++){
            if(!indexmap.containsKey(i)){
                indexmap.put(i, indexmap.keySet().size());
            }
        }

        for(int i=0;i<n;i++){
            int primeindex = indexmap.get(i);
            if(primeindex<absorbing_states.size())
                continue;
            for(int j=0;j<n;j++){
                absorbing[primeindex][indexmap.get(j)] = mat[i][j];
            }
        }

        Probability[][] Q = new Probability[n-absorbing_states.size()][n-absorbing_states.size()];
        for(int i=absorbing_states.size();i<n;i++){
            if (n - absorbing_states.size() >= 0)
                System.arraycopy(absorbing[i], absorbing_states.size(), Q[i - absorbing_states.size()], 0, n - absorbing_states.size());
        }

        Probability[][] R = new Probability[n-absorbing_states.size()][absorbing_states.size()];
        for(int i=absorbing_states.size();i<n;i++){
            System.arraycopy(absorbing[i], 0, R[i - absorbing_states.size()], 0, absorbing_states.size());
        }

        if(absorbing_states.size()==1){
            return new int[]{1, 1};
        }

        subtractFromIdentity(Q);
        Probability[][] F = invert(Q);

        Probability[] ans = new Probability[R[0].length];
        for(int i=0;i<R[0].length;i++){
            Probability cur = new Probability(0, 1);
            for(int j=0;j<F.length;j++){
                Probability mul = F[0][j].multiply(R[j][i]);
                cur = cur.add(mul);
            }
            ans[i] = cur;
        }

        int[] probability = new int[R[0].length+1];
        long lcm = 1;
        for(int i=0;i<R[0].length;i++){
            lcm = lcm(lcm, ans[i].deno);
        }

        long gcd = lcm;
        for(int i=0;i<R[0].length;i++){
            probability[i] = (int) ((lcm / ans[i].deno) * ans[i].num);
            gcd = gcd(probability[i], gcd);
        }
        probability[R[0].length] = (int) lcm;

        for(int i=0;i<probability.length;i++){
            probability[i] = (int) (probability[i] / gcd);
        }

        return probability;
    }
    public static void main(String[] args) {
        int[][] arr = {
                {0, 5, 1, 2, 3, 2, 5, 5, 4, 5},{3, 2, 4, 5, 4, 4, 0, 1, 3, 1},
                {3, 5, 2, 4, 3, 2, 2, 2, 1, 5},{3, 5, 4, 1, 0, 3, 3, 2, 2, 1},
                {2, 0, 2, 1, 2, 4, 5, 4, 4, 5},{1, 5, 4, 2, 0, 2, 5, 2, 5, 5},
                {3, 5, 2, 3, 0, 3, 3, 3, 0, 1},{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 0, 5, 1, 2, 3, 0, 1, 4, 5},{0, 1, 4, 0, 3, 3, 5, 1, 1, 1}
        };
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println(Arrays.toString(solution(arr)));
    }
}
/*
[0, 4, 3, 5, 5, 3, 4, 0, 0, 4]
[0, 5, 3, 0, 0, 3, 0, 3, 2, 0]
[3, 5, 4, 3, 4, 4, 3, 1, 1, 2]
[4, 4, 3, 2, 5, 0, 0, 0, 0, 5]
[0, 1, 4, 1, 0, 1, 1, 3, 2, 4]
[0, 4, 1, 0, 1, 0, 1, 4, 1, 1]
[3, 4, 3, 4, 1, 3, 4, 5, 2, 4]
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[4, 4, 0, 1, 2, 0, 1, 0, 5, 3]
[4, 0, 2, 2, 1, 0, 4, 5, 4, 4]
 */