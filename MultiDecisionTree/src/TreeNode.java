import java.util.ArrayList;
//The TreeNode record not only the tree relation but also the attribute it use and the data set of the node.
public class TreeNode {  
	private int nodeName; //name of the node, which is also the splitting attribute 
	private TreeNode parent;
	private ArrayList<TreeNode> children; //records all the children  
    private ArrayList<ArrayList<Double>> data; //the training data for this node
    private double threshold;
    private double threshold1;
    private double threshold2;
    
    public double getThreshold1() {
		return threshold1;
	}
	public void setThreshold1(double threshold1) {
		this.threshold1 = threshold1;
	}
	public double getThreshold2() {
		return threshold2;
	}
	public void setThreshold2(double threshold2) {
		this.threshold2 = threshold2;
	}
	public TreeNode() {  
    	nodeName = -1; 
    	parent = null;
    	children = new ArrayList<TreeNode>();  
	    data = null;  
	    threshold = 0;
	    threshold1 = 0;
	    threshold2 = 0;
	}    
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public void setParent(TreeNode p) {  
	    parent = p;  
	}  
	public TreeNode getParent() {  
	    return parent;  
	}  
	public ArrayList<TreeNode> getChildren() {  
	    return children;  
	}
	public void setChildren(TreeNode tn) { 
		if(tn != null)
			children.add(tn);  
	}  
    public int getName() {  
	    return nodeName;  
	}  
    public void setName(int name) {  
	    nodeName = name;  
    }  
    public ArrayList<ArrayList<Double>> getDatas() {  
	    return data;  
    }  
    public void setData(ArrayList<ArrayList<Double>> d) {  
        data = d;  
    }
}  


