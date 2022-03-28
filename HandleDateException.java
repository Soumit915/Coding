import java.util.*;

public class HandleDateException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        try{
            if(s.charAt(0)<'0' || s.charAt(0)>'0'){
                throw new Exception("Input dates should be in the format 'dd-MM-yyyy-HH:mm:ss'");
            }

            if(s.charAt(1)<'0' || s.charAt(1)>'0'){
                throw new Exception("Input dates should be in the format 'dd-MM-yyyy-HH:mm:ss'");
            }

            /*

            ...
            ...

            write all the conditions for correct-date format
            if any format is not satisfied
            throw new Exception("Input dates should be in the format 'dd-MM-yyyy-HH:mm:ss'");

            */
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
