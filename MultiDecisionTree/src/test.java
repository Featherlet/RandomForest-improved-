import java.util.ArrayList;

public class test {
	
	public static void main(String[] args){
		Newsgroups np = new Newsgroups();
		np.readTrainingData(100, 499);
		np.readTestingData(0, 99);
		ArrayList<Document> formatTrainDoc = np.getFormatTrainDoc();
		ArrayList<Document> formatTestDoc = np.getFormatTestDoc();
		ArrayList<wordUnit> allWords = np.getAllWords();
		tfidf ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti.setDictionary(500);
		ti.setTrainData();
		ti.setTestData();
		ArrayList<ArrayList<Double>> trainingData = ti.getTrainingData();
		ArrayList<ArrayList<Double>> testingData = ti.getTestingData();
		ArrayList<wordUnit> dictionary = ti.getDictionary();
		WriteExcel we = new WriteExcel();
		we.writeData2(trainingData, "trainData500.csv");
		we.writeData2(testingData, "testData500.csv");
		
		/*System.out.println("for tf idf");
		tfidf ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		int dicsize = 100;
		for(int i = 0; i < 10; i++){
			long startMili=System.currentTimeMillis();
			ti.setDictionary(dicsize);
			ti.setTrainData();
			ti.setTestData();
			long endMili=System.currentTimeMillis();
			System.out.println("dicsize is " + dicsize + "time is " + (endMili - startMili));
			/*ArrayList<ArrayList<Double>> trainingData = ti2.getTrainingData();
			ArrayList<ArrayList<Double>> testingData = ti2.getTestingData();
			DecisionTree dt = new DecisionTree();
			dt.buildTree(null, trainingData);
			double result = dt.testTree(testingData);
			System.out.println("dicsize is " + dicsize2 + "; result is " + result);
			dicsize += 100;
		}
		
		System.out.println("for tf idf cf");
		TfIdfCF tif = new TfIdfCF(formatTrainDoc, formatTestDoc, allWords);
		int dicsizef = 100;
		for(int i = 0; i < 10; i++){
			long startMili=System.currentTimeMillis();
			tif.setDictionary(dicsizef);
			tif.setTrainData();
			tif.setTestData();
			long endMili=System.currentTimeMillis();
			System.out.println("dicsize is " + dicsizef + "time is " + (endMili - startMili));
			dicsizef += 100;
		}
		
		System.out.println("for tf idf h");
		TfIdfH tih = new TfIdfH(formatTrainDoc, formatTestDoc, allWords);
		int dicsizeh = 100;
		for(int i = 0; i < 10; i++){
			long startMili=System.currentTimeMillis();
			tih.setDictionary(dicsizeh);
			tih.setTrainData();
			tih.setTestData();
			long endMili=System.currentTimeMillis();
			System.out.println("dicsize is " + dicsizeh + "time is " + (endMili - startMili));
			dicsizeh += 100;
		}
		
		System.out.println("for tf idf e");
		TfIdfE tie = new TfIdfE(formatTrainDoc, formatTestDoc, allWords);
		int dicsizee = 100;
		for(int i = 0; i < 10; i++){
			long startMili=System.currentTimeMillis();
			tie.setDictionary(dicsizee);
			tie.setTrainData();
			tie.setTestData();
			long endMili=System.currentTimeMillis();
			System.out.println("dicsize is " + dicsizee + "time is " + (endMili - startMili));
			dicsizee += 100;
		}*/
		
		
		/*PrintData pd = new PrintData();		
		tfidf ti1 = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti1.setDictionary(200);
		ArrayList<wordUnit> dict1 = ti1.getDictionary();
		pd.printDict(dict1, "TfIdf_wordList");
		
		TfIdfCF ti2 = new TfIdfCF(formatTrainDoc, formatTestDoc, allWords);
		ti2.setDictionary(200);
		ArrayList<wordUnit> dict2 = ti2.getDictionary();
		pd.printDict(dict2, "TfIdfCF_wordList");
		
		TfIdfE ti3 = new TfIdfE(formatTrainDoc, formatTestDoc, allWords);
		ti3.setDictionary(200);
		ArrayList<wordUnit> dict3 = ti3.getDictionary();
		pd.printDict(dict3, "TfIdfE_wordList");*/
	}
}
