import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DecisionTree {

	private TreeNode root = null;
	private ArrayList<Integer> usedDict = null;
	int nodenum = 0;
	
	public DecisionTree(){
		usedDict = new ArrayList<Integer>();
	}
	
	
	//build the decision tree and return the root of the tree
	public void buildTree(TreeNode theRoot, ArrayList<ArrayList<Double>> data){  
        TreeNode node = new TreeNode();  
        nodenum++;
        //System.out.println("add node No." + nodenum +", data size is: " + data.size());
        if(theRoot == null){ //theRoot == null means this is the root of the tree
        	theRoot = node;   //set the fist node as root
            node.setData(data);  //put the whole data set to the node
            root = node; //set the root
        }else if(data.isEmpty()){ 
        	//System.out.println("Error! You should provide data!");
        	return;
        }else if(data.size() == 1){
        	int label = data.get(0).get(0).intValue();
        	//System.out.println("this is leaf and labels are :" + label);
        	node.setParent(theRoot);
        	theRoot.setChildren(node);
        	node.setChildren(null);
        	node.setName(label);
        	return;
        }else if(data.size() < 10){
        	//deal with leaves and add labels.
        	String label = "";
        	for(int j = 0; j < data.size(); j++)
        		label = label + " " + data.get(j).get(0).toString();
        	//System.out.println("this is leaf and labels are :" + label);
        	int[] p = {0, 0, 0, 0};
        	for(int i = 0; i < data.size(); i++){
        		if(data.get(i).get(0).intValue() == 0)// || data.get(i).get(0).intValue() == 1)
        			p[0]++;
        		else if(data.get(i).get(0).intValue() == 1)
        			p[1]++;
        		else if(data.get(i).get(0).intValue() == 2)
        			p[2]++;
        		else
        			p[3]++;
        	}
        	int max = 0;
        	int maxlabel = 0;
        	for(int i = 0; i < 4; i++){
        		if(p[i] > max){
        			max = p[i];
        			maxlabel = i;
        		}
        	}
        	node.setParent(theRoot);
        	theRoot.setChildren(node);
        	node.setName(maxlabel);
        	node.setChildren(null);
        	return;
        }else if(usedDict.size() == data.get(0).size()){
        	//deal with leaves and add labels.
        	//calculate the number of positive  and negative documents
        	String label = "";
        	for(int j = 0; j < data.size(); j++)
        		label = label + " " + data.get(j).get(0).toString();
        	//System.out.println("this is leaf and labels are :" + label);
        	int[] p = {0, 0, 0, 0};
        	for(int i = 0; i < data.size(); i++){
        		if(data.get(i).get(0).intValue() == 0)// || data.get(i).get(0).intValue() == 1)
        			p[0]++;
        		else if(data.get(i).get(0).intValue() == 1)
        			p[1]++;
        		else if(data.get(i).get(0).intValue() == 2)
        			p[2]++;
        		else
        			p[3]++;
        	}
        	int max = 0;
        	int maxlabel = 0;
        	for(int i = 0; i < 4; i++){
        		if(p[i] > max){
        			max = p[i];
        			maxlabel = i;
        		}
        	}
        	node.setParent(theRoot);
        	theRoot.setChildren(node);
        	node.setName(maxlabel);
        	node.setChildren(null);
        	return;
        }else{ //then this is the child node
        	node.setParent(theRoot);
        	theRoot.setChildren(node);
            node.setData(data);
        }
    	
        //if all the data belong to one class, then this is a leaf
        int k = 0;
        for(int i = 0; i < data.size() - 1; i++){
        	double a = data.get(i).get(0);
        	double b = data.get(i + 1).get(0);
        	if(a != b){
        		k = 1;
        		break;
        	}
        }
        if(k == 0){
        	int label = data.get(0).get(0).intValue();
        	//System.out.println("this is leaf and labels are :" + label);
        	node.setChildren(null);
        	node.setName(label);
        	return;
        }
        
        //else keep going
        int[] pattern = null;
        int attrIndex = 0;
        double threshold = 0;
        SplitPattern sp = new SplitPattern(data);       
        double IGR = sp.bestSplit(usedDict);
        if(IGR == 0){            	
        	String label = "";
        	for(int j = 0; j < data.size(); j++)
        		label = label + " " + data.get(j).get(0).toString();
        	//System.out.println("this is leaf and labels are :" + label);
        	int[] p = {0, 0, 0, 0};
        	for(int i = 0; i < data.size(); i++){
        		if(data.get(i).get(0).intValue() == 0)// || data.get(i).get(0).intValue() == 1)
        			p[0]++;
        		else if(data.get(i).get(0).intValue() == 1)
        			p[1]++;
        		else if(data.get(i).get(0).intValue() == 2)
        			p[2]++;
        		else
        			p[3]++;
        	}
        	int max = 0;
        	int maxlabel = 0;
        	for(int i = 0; i < 4; i++){
        		if(p[i] > max){
        			max = p[i];
        			maxlabel = i;
        		}
        	}
        	node.setParent(theRoot);
        	theRoot.setChildren(node);
        	node.setName(maxlabel);
        	node.setChildren(null);
        	return;
        }
        pattern = sp.getPattern();
        attrIndex = sp.getAttrIndex();
        threshold = sp.getThreshold();
        node.setThreshold(threshold);
        usedDict.add(attrIndex);
        //System.out.println("split attribute is " + attrIndex);
        
        
        if(IGR > 0.01){
        	node.setName(attrIndex);
        	//System.out.println("IG is " + IGR);
        	//split the data into left node data and right node data and use two ArrayList to collect them
        	ArrayList<ArrayList<Double>> dataleft = new ArrayList<ArrayList<Double>>();
        	ArrayList<ArrayList<Double>> dataright = new ArrayList<ArrayList<Double>>();
            for(int i = 0; i < data.size(); i++){
            	//see the value of the word(index) in each document
            	if(pattern[i] == 0)
        			dataleft.add(data.get(i));
        		else 
            		dataright.add(data.get(i));
        	}        
        	//build the recurse tree
        	buildTree(node, dataleft);
        	buildTree(node, dataright);
        }else{
        	//deal with leaves and add labels.
        	//calculate the number of positive and negative documents
        	String label = "";
        	for(int j = 0; j < data.size(); j++)
        		label = label + " " + data.get(j).get(0).toString();
        	//System.out.println("this is leaf and labels are :" + label);
        	int[] p = {0, 0, 0, 0};
        	for(int i = 0; i < data.size(); i++){
        		if(data.get(i).get(0).intValue() == 0)// || data.get(i).get(0).intValue() == 1)
        			p[0]++;
        		else if(data.get(i).get(0).intValue() == 1)
        			p[1]++;
        		else if(data.get(i).get(0).intValue() == 2)
        			p[2]++;
        		else
        			p[3]++;
        	}
        	int max = 0;
        	int maxlabel = 0;
        	for(int i = 0; i < 4; i++){
        		if(p[i] > max){
        			max = p[i];
        			maxlabel = i;
        		}
        	}
        	node.setName(maxlabel);
        	node.setChildren(null);
        	return;
        }
      }
	
	
	//print the tree
		public void LevelOrderTraverse(){
			//System.out.println("Decision Tree");
		    Queue<TreeNode> Q = new LinkedList<TreeNode>();
			if(root!=null) 
				Q.add(root);
			while(!Q.isEmpty())
			{
				TreeNode node = Q.remove();				
				ArrayList<TreeNode> children = node.getChildren();
				if(!children.isEmpty()){
					//System.out.print(node.getName() + " ");
					//System.out.print("left:" + children.get(0).getName() + " ");
					Q.add(children.get(0));
					//System.out.print("right:" + children.get(1).getName() + " ");
					Q.add(children.get(1));
				}else{
					//System.out.print("leaf: " + node.getName() + " ");
				}
			}
			//System.out.println("\nfinished travelling");
		}
	
		
		//test the decision tree
		public double testTree(ArrayList<ArrayList<Double>> data){
			//prepareData prepare = new prepareData(Data);
			//ArrayList<Document> testData = prepare.getFormatTrainData();
			int rightNum = 0;
			for(int i = 0; i < data.size(); i++){
				TreeNode tn = root;
				int label = data.get(i).get(0).intValue();
				while(!tn.getChildren().isEmpty()){
					int attrib = tn.getName();
					double threshold = tn.getThreshold();
					if(data.get(i).get(attrib) <= threshold){
						tn = tn.getChildren().get(1);
					}else{
						tn = tn.getChildren().get(0);
					}
				}
				if(tn.getName() == label)
					rightNum++;
			}
			return rightNum*1.0/data.size();			
		}
		
		public int testSingleData(ArrayList<Double> data){
			TreeNode tn = root;
			while(!tn.getChildren().isEmpty()){
				int attrib = tn.getName();
				double threshold = tn.getThreshold();
				if(data.get(attrib) <= threshold){
					tn = tn.getChildren().get(1);
				}else{
					tn = tn.getChildren().get(0);
				}
			}
			return tn.getName();
		}
}
