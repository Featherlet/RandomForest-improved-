import java.util.ArrayList;

public class TfIdfE {

	private ArrayList<ArrayList<Double>> trainingData = null;
	private ArrayList<ArrayList<Double>> testingData = null;
	private ArrayList<Document> formatTrainDoc = null;
	private ArrayList<Document> formatTestDoc = null;
	ArrayList<wordUnit> allWords = null;
	private ArrayList<wordUnit> dictionary = null;	
	
	public TfIdfE(ArrayList<Document> formatTrainDoc, ArrayList<Document> formatTestDoc, ArrayList<wordUnit> allWords){
		this.formatTrainDoc = formatTrainDoc;
		this.formatTestDoc = formatTestDoc;
		this.allWords = allWords;
	}
	
	public ArrayList<wordUnit> setDictionary(int attrNum){
		calculateWeight();
		dictionary = new ArrayList<wordUnit>();
		int[] record = new int[attrNum + 1];
		int lenth = 1;
		record[0] = 0;
		for(int i = 1; i < allWords.size(); i++){
			if(lenth < attrNum){
				record[lenth] = i;
				lenth++;
				int index = lenth - 1;
				while(index >= 1 && allWords.get(record[index]).weight > allWords.get(record[index - 1]).weight){
					int t = record[index];
					record[index] = record[index - 1];
					record[index - 1] = t;
					index--;
				}
			}else{
				record[lenth] = i;
				int index = lenth;
				while(index >= 1 && allWords.get(record[index]).weight > allWords.get(record[index - 1]).weight){
					int t = record[index];
					record[index] = record[index - 1];
					record[index - 1] = t;
					index--;
				}
			}
		}
		
		for(int i = 0; i < record.length - 1; i++){
			wordUnit wu = allWords.get(record[i]).copy();
			dictionary.add(wu);
		}
		return dictionary;
	}

	public double infoEntropy(int a, int b ,int c, int d){
		int total = a + b + c + d;
		double E = 0;
		if(a != 0)
			E = E + (a * 1.0/ total) * Math.log(a * 1.0/ total) / Math.log(2);
		if(b != 0)
			E = E + (b * 1.0/ total) * Math.log(b * 1.0/ total) / Math.log(2);
		if(c != 0)
			E = E + (c * 1.0/ total) * Math.log(c * 1.0/ total) / Math.log(2);
		if(d != 0)
			E = E + (d * 1.0/ total) * Math.log(d * 1.0/ total) / Math.log(2);
		return E;
	}

	public void calculateWeight(){
		for(int i = 0; i < allWords.size(); i++){
			/*int classCounter = 0;
			for(int j = 0; j < 4; j++)
				if(allWords.get(i).tfc[j] != 0)
					classCounter++;
			allWords.get(i).classCounter = classCounter;	*/	
			double classEntropy = 2.001 + infoEntropy(allWords.get(i).dfc[0], allWords.get(i).dfc[1], allWords.get(i).dfc[2], allWords.get(i).dfc[3]);
			//double cd = 4 / classCounter * 1.0;
			allWords.get(i).train_idf = Math.log(formatTrainDoc.size() * 1.0/ allWords.get(i).idfCounter) / Math.log(2);
			allWords.get(i).weight = allWords.get(i).frequency * 1.0 * allWords.get(i).train_idf * classEntropy/ allWords.size();
		}
	}	

	public void setTrainData(){
		trainingData = new ArrayList<ArrayList<Double>>();
		for(int i = 0; i < formatTrainDoc.size(); i++){
			ArrayList<Double> data = new ArrayList<Double>();
			Document doc = formatTrainDoc.get(i);
			int label = doc.label;
			data.add((double)label);
			for(int j = 0; j < dictionary.size(); j++){		
				String word = dictionary.get(j).word;
				double tf = 0;
				if(doc.words.contains(word)){
					int index = doc.words.indexOf(word);
					int freq = doc.frequency[index];
					tf = freq * 1.0 / doc.doclenth;
				}
				double weightk = tf;
				data.add(weightk);
			}
			trainingData.add(data);
		}
	}


	public void setTestData(){
		testingData = new ArrayList<ArrayList<Double>>();
		for(int i = 0; i < formatTestDoc.size(); i++){
			ArrayList<Double> data = new ArrayList<Double>();
			Document doc = formatTestDoc.get(i);
			int label = doc.label;
			data.add((double)label);
			for(int j = 0; j < dictionary.size(); j++){	
				String word = dictionary.get(j).word;
				double tf = 0;
				if(doc.words.contains(word)){
					int index = doc.words.indexOf(word);
					int freq = doc.frequency[index];
					tf = freq * 1.0 / doc.doclenth;
				}
				double weightk = tf;
				data.add(weightk);
			}
			testingData.add(data);
		}
	}

	public ArrayList<ArrayList<Double>> getTrainingData() {
		return trainingData;
	}

	public ArrayList<ArrayList<Double>> getTestingData() {
		return testingData;
	}

	public ArrayList<wordUnit> getDictionary() {
		return dictionary;
	}
	
	
	
}
