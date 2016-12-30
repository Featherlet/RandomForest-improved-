import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Boostrap {


	private ArrayList<ArrayList<Double>> subData = null; 	
	
	public ArrayList<ArrayList<Double>> getSubData(ArrayList<ArrayList<Double>> Data, int bootSize){
		subData = new ArrayList<ArrayList<Double>>();
		Set<Integer> set = new HashSet<Integer>(); 
		int len = Data.size();
		int i = 0;
		while(true){
			Random random = new Random();  
			int randomNumber =  random.nextInt(len - 1)%(len); 
			if(set.contains(randomNumber)){
				continue;
			}else{
				set.add(randomNumber);
				subData.add(Data.get(randomNumber));
				i++;
			}
			if(i == bootSize)
				break;
		}
		return subData;
	}
	
	public ArrayList<Integer> getSubAttributes(ArrayList<wordUnit> library, int bootSize){
		ArrayList<wordUnit> subDictionary = new ArrayList<wordUnit>();
		ArrayList<Integer> setDic = new ArrayList<Integer>();
		int len = library.size();
		int i = 0;
		while(true){
			Random random = new Random();  
			int randomNumber =  random.nextInt(len - 1)%(len); 
			if(setDic.contains(randomNumber)){
				continue;
			}else{
				setDic.add(randomNumber);
				subDictionary.add(library.get(randomNumber));
				i++;
			}
			if(i == bootSize)
				break;
		}
		return setDic;
	}
}

