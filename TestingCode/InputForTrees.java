package TestingCode;

public class InputForTrees {
    static class Set
    {
        int id;
        int count;
        Set parent;
        Set(int id)
        {
            this.id = id;
            this.count = 1;
            this.parent = null;
        }
        public void union(Set repb)
        {
            if(this.count >= repb.count)
            {
                repb.parent = this;
                this.count = this.count+repb.count;
            }
            else
            {
                this.parent = repb;
                repb.count = this.count+repb.count;
            }
        }
        public Set compress()
        {
            if(this.parent!=null)
            {
                this.parent = this.parent.find();
            }
            return this;
        }
        public void findUnion(Set repb)
        {
            Set k,k1;
            if(this.parent == null)
                k = this;
            else
                k = this.parent;
            if(repb.parent == null)
                k1 = repb;
            else
                k1 = repb.parent;

            k.union(k1);
        }
        public Set find()
        {
            if(this.parent == null)
            {
                return this;
            }
            this.compress();
            return this.parent;
        }
    }
    public static void main(String[] args)
    {
        int n = 10;
        Set[] arr = new Set[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = new Set(i);
        }
        int edges = 1;
        while(edges<n)
        {
            int u = (int) (Math.random()*n+1);
            int v = (int) (Math.random()*n+1);

            if(u!=v && arr[u-1].find()!=arr[v-1].find())
            {
                System.out.println(u+" "+v);
                arr[u-1].findUnion(arr[v-1]);
                edges++;
            }
        }
    }
}
