
public class KDtree {
    // class KDnode inside the KDtree class
    // in order to create a binary search tree we will use node
    public class KDnode{
        public Point3D point;
        public int axis; // axis is a int variable that represent the division of the KDtree
        // it can take the values 0,1,2 it represents respectfully the division of the KDtree by x,y,z respectfully
        public double value;
        public KDnode left,right;

        public KDnode(Point3D pt, int axis){
            this.point=pt;
            this.axis=axis;
            this.value=pt.get(axis);
            left=right=null;
        }
    }
    // root is an instance variable of KDtree
    private KDnode root;
    // constructor of the KDtree
    public KDtree() {
        root = null;// we first start with an empty tree
    }

    // insert method of the KDtree
    public KDnode insert(Point3D p,KDnode node, int axis){
        if(node==null){
            node=new KDnode(p,axis);
        }
        else if(p.get(axis)<=node.value){
            node.left=insert(p,node.left,(axis+1)%3);
        }
        else{
            node.right=insert(p,node.right,(axis+1)%3);
        }
        return node;
    }
    public void add(Point3D p) {
        this.root=insert(p,this.root,0);//we put the axis 0 by default
        // don't need to increment the axis because in the insert method we use recursive, and we do increment the axis as a parameter
    }
    public KDnode root(){
        return this.root;
    }

}


