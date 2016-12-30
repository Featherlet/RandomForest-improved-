import java.util.ArrayList;

public class DecisionForest {

	private ArrayList<ArrayList<Double>> formatTrainData = null;
	private ArrayList<wordUnit> dictionary = null;
	private int numOfTree;
	private int bootSizeData;
	private int bootSizeDic;
	private ArrayList<DecisionTree> forest = null;
	
	public DecisionForest(ArrayList<ArrayList<Double>> formatTrainData, ArrayList<wordUnit> dict, int numOfTree, int bootSizeData, int bootSizeDic){
		this.formatTrainData = formatTrainData;
		this.dictionary = dict;
		this.numOfTree = numOfTree;
		this.bootSizeData = bootSizeData;
		this.bootSizeDic = bootSizeDic;
		
	}
	
	public ArrayList<DecisionTree> buildForest(){
		forest = new ArrayList<DecisionTree>();
		Boostrap boot = new Boostrap();
		for(int i = 0; i < numOfTree; i++){
			ArrayList<ArrayList<Double>> subData = boot.getSubData(formatTrainData, bootSizeData);
			ArrayList<Integer> subDic = boot.getSubAttributes(dictionary, bootSizeDic);
			DecisionTree dt = new DecisionTree(subDic);
			dt.buildTree(null, subData);
			forest.add(dt);
		}
		return forest;
	}
	
	public void testData(ArrayList<ArrayList<Double>> formatTestData){
		int num = forest.size();
		int size = formatTestData.size();
		int[] result = new int[size];
		for(int i = 0; i < size; i++){
			ArrayList<Double> data = formatTestData.get(i);
			int maxLabel = 0;
			int max = 0;
			int counter[] = {0, 0, 0, 0};
			for(int j = 0; j < num; j++){
				DecisionTree dt = forest.get(j);
				int label = dt.testSingleData(data);
				counter[label]++;
			}
			
			for(int j = 0; j < 4; j++){
				if(max < counter[j]){
					max = counter[j];
					maxLabel = j;
				}
			}
			result[i] = maxLabel;
		}
		int right = 0;
		for(int i = 0; i < size; i++)
			if(result[i] == formatTestData.get(i).get(0).intValue())
				right++;
		System.out.println(right * 1.0 / size);
	}
	
	public void Clear(){
		forest.clear();
	}
	
}


