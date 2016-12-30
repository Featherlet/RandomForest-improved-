import java.util.ArrayList;
import java.util.Set;

public class SplitPattern {
	private ArrayList<ArrayList<Double>> data = null;
	private int[] cpattern = null;
	private int[] pattern = null;
	private int attrIndex = 0;
	private double threshold = 0;
	private double threshold1 = 0;
	private double threshold2 = 0;
	private double cthreshold = 0;
	
	public SplitPattern(ArrayList<ArrayList<Double>> data){
		this.data = data;
	}
	
	
	//get current entropy value which is H(Y)
    public double getCurrentH(ArrayList<ArrayList<Double>> data){
    	int size = data.size();
    	int[] counter = {0, 0, 0, 0};
    	for(int i = 0; i < size; i++){
    		int label = data.get(i).get(0).intValue();
    		counter[label]++;
    	}
    	double p0 = counter[0] * 1.0/ size;
    	double p1 = counter[1] * 1.0/ size;
    	double p2 = counter[2] * 1.0/ size;
    	double p3 = counter[3] * 1.0/ size;
    	double H = 0;
    	if(p0 != 0)
    		H = H - (p0) * (Math.log(p0)*1.0 / Math.log(2));
    	if(p1 != 0)
    		H = H - (p1) * (Math.log(p1)*1.0 / Math.log(2));
    	if(p2 != 0)
    		H = H - (p2) * (Math.log(p2)*1.0 / Math.log(2));
    	if(p3 != 0)
        	H = H - (p3) * (Math.log(p3)*1.0 / Math.log(2));
    	return H;
    }
    
    //get current entropy value which is H(Y)
    public double getConditionalH(ArrayList<ArrayList<Double>> data, int[] pattern){
    	ArrayList<ArrayList<Double>> left = new ArrayList<ArrayList<Double>>();
    	ArrayList<ArrayList<Double>> right = new ArrayList<ArrayList<Double>>();
    	for(int i = 0; i < data.size(); i++){
    		if(pattern[i] == 0)
    			left.add(data.get(i));
    		else
    			right.add(data.get(i));
    	}
    	double HL = getCurrentH(left);
    	double HR = getCurrentH(right);
    	return (left.size() * 1.0 / data.size()) * HL + (right.size() * 1.0 / data.size()) * HR;
    }
    
    public double splitByAttr1(int attrIndex){
    	double currentH = getCurrentH(data);   	
    	int size = data.size();
    	double minIG = 0;
    	//extracting the attribute's value of each data row
    	double[][] values = new double[size][3]; //values[i] = {index of sample, value of attribute}
    	for(int i = 0; i < size; i++){
    		values[i][0] = i;
    		values[i][1] = data.get(i).get(attrIndex);
    		values[i][2] = data.get(i).get(0);
    	}
    	//bubble sorting of attribute values without losing index
    	for(int i = 0; i < size - 1; i++)
    		for(int j = 0; j < size - i - 1; j++){
    			if(values[j][1] < values[j + 1][1]){
    				double t1 = values[j][0];
    				double t2 = values[j][1];
    				double t3 = values[j][2];
    				values[j][0] = values[j + 1][0];
    				values[j][1] = values[j + 1][1];
    				values[j][2] = values[j + 1][2];
    				values[j + 1][0] = t1;
    				values[j + 1][1] = t2;
    				values[j + 1][2] = t3;
    		}
    	}    	
    	//find the splitting location of attribute values   	
    	for(int i = 0; i < size - 1; i++){
    		int[] newpattern = new int[size];
    		//index1 is the index of biggest left doc, index2 is the index of least right doc
    		int index1 = (int)values[i][0];
    		int index2 = (int)values[i + 1][0];
    		if(data.get(index1).get(0).intValue() != data.get(index2).get(0).intValue() && values[i][1] != 0){    			
    			for(int j = 0; j < i + 1; j++){
    				//get the primary index of the value
    				int index = (int)values[j][0];
    				newpattern[index] = 0;
    			}
    			for(int j = i + 1; j < size; j++){
    				int index = (int)values[j][0];
    				newpattern[index] = 1;
    			}
    		}else
    			continue;
    		
    		//judge if pattern has not been changed
    		int k = 0;
    		for(int j = 0; j < newpattern.length; j++)
    			if(newpattern[j] == 1){
    				k = 1;
    				break;
    			}
     		if(k == 0)
    			break;
    		else{
    			double H = getConditionalH(data, newpattern);
        		double IG = currentH - H;
        		if(IG > minIG){
        			minIG = IG;
        			cpattern = newpattern.clone();
        			cthreshold = (values[i][1] + values[i + 1][1]) / 2.0;
        		}
    		}
    		
    	}
    	if(minIG == 0)
    		return 1000;
    	return minIG;
    }
    
 
    public double getIV(int[] pattern){
    	int left = 0, right = 0;
    	for(int i = 0; i < pattern.length; i++)
    		if(pattern[i] == 0)
    			left++;
    		else
    			right++;
    	double IV = -(left * 1.0 / pattern.length) * (Math.log(left * 1.0 / pattern.length) / Math.log(2))
    			- (right * 1.0 / pattern.length) * (Math.log(right * 1.0 / pattern.length) / Math.log(2));
    	return IV;
    }
	
	
  //return the best attribute
    public double bestSplit(ArrayList<Integer> subDic, ArrayList<Integer> usedDict){
    	double maxIGR = 0;
    	int attrib = 0;
    	for(int i = 0; i < subDic.size(); i++){
    		//should not use those used words
    		if(usedDict.contains(subDic.get(i)))
    			continue;    		
    		double IG = splitByAttr1(subDic.get(i) + 1);
    		if(IG == 1000)
    			continue;
    		double IV = getIV(cpattern);
    		double IGR = IG * 1.0 / (IV + 1);
    		if(IGR > maxIGR){
    			maxIGR = IG;
    			attrib = subDic.get(i);
    			pattern = cpattern.clone();
    			threshold = cthreshold;
    		}   		
    	}
    	attrIndex = attrib;
    	return maxIGR;
    }


	public double getThreshold1() {
		return threshold1;
	}


	public double getThreshold2() {
		return threshold2;
	}


	public int[] getPattern() {
		return pattern;
	}


	public int getAttrIndex() {
		return attrIndex;
	}


	public double getThreshold() {
		return threshold;
	}
    
    
}
