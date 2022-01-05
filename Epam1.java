import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

//good =3, excellent =5, great=5, awesome=6, fantastic =6, best=5, super=5
//no=1, not=2, bad=3, worse=5, worst =6, poor=3, hard=4

public class Epam1 {
    static HashMap<String, Integer> sensitive = new HashMap<>();
    static class Company implements Comparable<Company>{
        String name;
        ArrayList<String> messages = new ArrayList<>();
        int rating;
        Company(String name){
            this.name = name;
            this.rating = 0;
        }
        public void computeRating(){
            for(String message: messages){
                Scanner messageInterpret = new Scanner(message);
                while(messageInterpret.hasNext()){
                    String word = messageInterpret.next().trim();
                    this.rating += sensitive.getOrDefault(word, 0);
                }
            }
        }
        public int compareTo(Company company){
            int c = Integer.compare(this.rating, company.rating);
            if(c==0){
                return this.name.compareTo(company.name);
            }
            else return c;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        rateCompanies(sc);
        sc.close();
    }
    public static void rateCompanies(Scanner sc){

        sensitive.put("good", 3);
        sensitive.put("excellent", 5);
        sensitive.put("great", 5);
        sensitive.put("awesome", 6);
        sensitive.put("fantastic", 6);
        sensitive.put("best", 5);
        sensitive.put("super", 5);

        sensitive.put("no", -1);
        sensitive.put("not", -2);
        sensitive.put("bad", -3);
        sensitive.put("worse", -5);
        sensitive.put("worst", -6);
        sensitive.put("poor", -3);
        sensitive.put("hard", -4);

        ArrayList<Company> companies = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            Scanner lineinterpret = new Scanner(line);
            lineinterpret.useDelimiter(",");
            Company nc = new Company(lineinterpret.next().trim().toLowerCase());
            while(lineinterpret.hasNext()){
                String review = lineinterpret.next().trim();
                nc.messages.add(review);
            }

            companies.add(nc);
        }

        for(Company company: companies){
            company.computeRating();
        }

        companies.sort(Collections.reverseOrder());

        for(Company company: companies){
            if(company.rating>0){
                System.out.println(company.name+" : "+company.rating);
            }
        }
    }
}
