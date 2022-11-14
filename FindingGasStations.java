import java.util.*;

public class FindingGasStations {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of miles the car can go with full tank: ");
        int n = sc.nextInt();

        System.out.print("Enter the number of gas stations between .... ");
        int ng = sc.nextInt();              //Number of gas stations between source and destination

        int[] gasStation = new int[ng];
        for(int i=0;i<ng;i++)
        {
            System.out.print("Enter the distance at which "+(i+1)+"th gas station is present: ");
            gasStation[i] = sc.nextInt();
        }

        int distTravelled = n;
        ArrayList<Integer> gasStationsUsed = new ArrayList<>();
        for(int i=0;i<ng;i++)
        {
            if(distTravelled<gasStation[i]) {
                distTravelled += n;
                gasStationsUsed.add(i);
            }
        }

        System.out.print("The Gas Stations that should be used in the journey is : ");
        for(int i: gasStationsUsed)
            System.out.print(i+" ");
        System.out.println();
    }
}
