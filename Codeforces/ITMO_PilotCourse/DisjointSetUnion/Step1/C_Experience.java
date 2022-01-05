package Codeforces.ITMO_PilotCourse.DisjointSetUnion.Step1;

import java.util.*;
public class C_Experience {
    static class Set{
        int id;
        int count;
        int value;
        Set parent;
        int index;
        ArrayList<Integer> valuelist;
        Set(int id)
        {
            this.id = id;
            this.parent = this;
            this.value = 0;
            this.valuelist = new ArrayList<>();
            this.index = -1;
            this.count = 1;
        }
        public Set getParent()
        {
            //If the player is the head of the set itself then return the parent
            if(this.parent==this)
            {
                return this;
            }

            //If the parent of the player is the head of the set
            if(this.parent.parent == this.parent)
            {
                //Add the due value of this player at the last of its valuelist as per prefixSum
                if(this.valuelist.size()==0)
                    this.valuelist.add(this.parent.getSum(this.index));
                else this.valuelist.add(this.parent.getSum(this.index) + this.valuelist.get(this.valuelist.size()-1));

                //Also update the value of this player
                this.value += this.parent.getSum(this.index);

                //Add a new value which will be the new index value
                if(this.parent.valuelist.size()==0)
                    this.parent.valuelist.add(0);
                else this.parent.valuelist.add(this.parent.valuelist.get(this.parent.valuelist.size()-1));

                //So update the this players index
                this.index = this.parent.valuelist.size()-1;

                return this.parent;
            }


            Set parent = this.parent.getParent();

            //Add the due value of this player
            if(this.valuelist.size()==0)
                this.valuelist.add(this.parent.getSum(this.index));
            else this.valuelist.add(this.parent.getSum(this.index) + this.valuelist.get(this.valuelist.size()-1));

            //Update the due value
            this.value += this.parent.getSum(this.index);
            //Update the index
            this.index = this.parent.index;
            //Update the parent
            this.parent = parent;
            return parent;
        }
        public int getSum(int index)
        {
            if(this.valuelist.size()==0)
                return 0;
            else if(index==0)
                return this.valuelist.get(this.valuelist.size()-1);
            else return this.valuelist.get(this.valuelist.size()-1) - this.valuelist.get(index-1);
        }
        public void addValue(int value)
        {
            if(this.valuelist.size()==0)
                this.valuelist.add(value);
            else this.valuelist.set(this.valuelist.size()-1,
                    value+this.valuelist.get(this.valuelist.size()-1));

            this.value += value;
        }
        public void union(Set parent2)
        {
            if(this.count>parent2.count)
            {
                parent2.parent = this;
                //Add a new value which will be the new index value
                if(this.valuelist.size()==0)
                    this.valuelist.add(0);
                else this.valuelist.add(this.valuelist.get(this.valuelist.size()-1));

                parent2.index = this.valuelist.size()-1;
                this.count += parent2.count;
            }
            else
            {
                this.parent = parent2;

                if(parent2.valuelist.size()==0)
                    parent2.valuelist.add(0);
                else parent2.valuelist.add(parent2.valuelist.get(parent2.valuelist.size()-1));

                this.index = parent2.valuelist.size()-1;
                parent2.count += this.count;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Set[] players = new Set[n];
        for(int i=0;i<n;i++)
        {
            players[i] = new Set(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++)
        {
            String type = sc.next();
            if(type.equals("join"))
            {
                int player1 = sc.nextInt()-1;
                int player2 = sc.nextInt()-1;

                Set parent1 = players[player1].getParent();
                Set parent2 = players[player2].getParent();
                if(parent1!=parent2)
                {
                    parent1.union(parent2);
                }
            }
            else if(type.equals("add"))
            {
                int player1 = sc.nextInt()-1;
                int value = sc.nextInt();
                Set parent1 = players[player1].getParent();

                parent1.addValue(value);
            }
            else {
                int player1 = sc.nextInt()-1;
                players[player1].getParent();
                sb.append(players[player1].value).append("\n");
            }
        }

        System.out.println(sb);
    }
}