
import java.util.*;

class Cricketer{
    private int cricketerId;
    private String cricketerName;
    private int jercyNo;
    private String group;
    private String type;

    Cricketer(int cricketerId, String cricketerName, int jercyNo, String group, String type){
        this.cricketerId = cricketerId;
        this.cricketerName = cricketerName;
        this.jercyNo = jercyNo;
        this.group = group;
        this.type = type;
    }

    public void setCricketerId(int cricketerId){
        this.cricketerId = cricketerId;
    }

    public void setCricketerName(String cricketerName){
        this.cricketerName = cricketerName;
    }

    public void setJercyNo(int jercyNo){
        this.jercyNo = jercyNo;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public void setType(String type){
        this.type = type;
    }

    public int getCricketerId(){
        return this.cricketerId;
    }

    public String getCricketerName(){
        return this.cricketerName;
    }

    public int getJercyNo(){
        return this.jercyNo;
    }

    public String getGroup(){
        return this.group;
    }

    public String getType(){
        return this.type;
    }
}

public class Priyonkar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 4;

        List<Cricketer> list = new ArrayList<>();
        for(int i=0;i<n;i++){

            int id = sc.nextInt();
            String name = sc.next() + sc.nextLine();
            int jercyNo = sc.nextInt();
            String group = sc.next() + sc.nextLine();
            String type = sc.next() + sc.nextLine();

            Cricketer cricketer = new Cricketer(id, name, jercyNo, group, type);
            list.add(cricketer);
        }

        String group = sc.nextLine();
        String type = sc.nextLine();

        Cricketer min = findCricketerWithMinJercyNo(list, group, type);
        if(min == null){
            System.out.println("There is no such cricketer");
        }
        else{
            System.out.println(min.getCricketerId());
        }
    }

    public static Cricketer findCricketerWithMinJercyNo(List<Cricketer> list, String group, String type){

        int min = Integer.MAX_VALUE;
        Cricketer ans = null;

        for(Cricketer cricketer: list){
            if(cricketer.getGroup().equalsIgnoreCase(group) && cricketer.getType().equalsIgnoreCase(type)){
                min = Math.min(min, cricketer.getJercyNo());
                ans = cricketer;
            }
        }

        return ans;
    }
}
