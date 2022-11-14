import java.io.*;
import java.util.*;

class Transport{
    String type;
    private final HashMap<String, Integer> code_mapper = new HashMap<>();
    Transport(String type){
        this.type = type;
    }
    public void addCode(String code, int price){
        this.code_mapper.put(code, price);
    }
    public int getCode(String code){
        return this.code_mapper.get(code);
    }
    public HashMap<String, Integer> getAllCodes(){
        return (HashMap<String, Integer>) this.code_mapper.clone();
    }
}

public class ManishaThoughtWorks {

    static HashMap<String, Transport> DB;

    static void populateDB(){
        DB = new HashMap<>();

        Transport metro = new Transport("Metro");
        metro.addCode("IM01", 350);
        metro.addCode("OM01", 950);
        metro.addCode("AP01", 600);
        DB.put("Metro", metro);

        Transport bus = new Transport("Bus");
        bus.addCode("SB01", 550);
        bus.addCode("BB01", 800);
        DB.put("Bus", bus);
    }

    static ArrayList<String> getTransports(String transportLine){
        ArrayList<String> transports = new ArrayList<>();
        Scanner sc = new Scanner(transportLine);
        sc.useDelimiter(",");

        while(sc.hasNext()){
            transports.add(sc.next());
        }

        return transports;
    }
    static boolean isValidTransport(String transport){
        return DB.containsKey(transport);
    }

    static ArrayList<String> getCodes(String codeline){
        ArrayList<String> codes = new ArrayList<>();
        Scanner sc = new Scanner(codeline);
        sc.useDelimiter(",");

        while(sc.hasNext()){
            codes.add(sc.next());
        }

        return codes;
    }
    static ArrayList<String> getInValidCodes(ArrayList<String> codes){
        return new ArrayList<>();
    }

    static int getDays(String days){
        Scanner sc = new Scanner(days);
        return sc.nextInt();
    }
    static boolean isValidDays(String transport, int days){
         if(transport.equals("Metro"))
             return 20<=days;
         else return 15<=days;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        populateDB();

        System.out.print("Where to put add: ");
        String transportLine = sc.nextLine();

        System.out.print("Location code: ");
        String codeline = sc.nextLine();

        System.out.print("Duration: ");
        String days = sc.nextLine();

        ArrayList<String> transports = getTransports(transportLine);
        for(String transport: transports){
            if(!isValidTransport(transportLine)){
                System.out.println(transport+" is an invalid transport.");
                System.exit(0);
            }
        }

        ArrayList<String> codes = getCodes(codeline);
        ArrayList<String> invalidCodes = getInValidCodes(codes);
        if(invalidCodes.size()>0){
            //Show error code
            System.exit(1);
        }

        int nodays = getDays(days);
        String transport = null;
        if(!isValidDays(transport, nodays)){
            //Show error code
            System.exit(2);
        }

        Transport ad_transport = DB.get(transport);
        int price = 0;
        for(String code: codes){
            HashMap<String, Integer> code_mapper = ad_transport.getAllCodes();
            price += (nodays * code_mapper.get(code));
        }

        System.out.println("Bill Amount : "+price);
    }
}
