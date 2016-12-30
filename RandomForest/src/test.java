import java.util.ArrayList;

public class test {
	
	public static void main(String[] args){
		Newsgroups np = new Newsgroups();
		np.readTrainingData(100, 849);
		np.readTestingData(0, 99);
		ArrayList<Document> formatTrainDoc = np.getFormatTrainDoc();
		ArrayList<Document> formatTestDoc = np.getFormatTestDoc();
		ArrayList<wordUnit> allWords = np.getAllWords();
		
		System.out.println("dimension is 100");
		long startMili=System.currentTimeMillis();
		tfidf ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti.setDictionary(100);
		ti.setTrainData();
		ti.setTestData();
		ArrayList<ArrayList<Double>> trainingData = ti.getTrainingData();
		ArrayList<ArrayList<Double>> testingData = ti.getTestingData();
		ArrayList<wordUnit> dictionary = ti.getDictionary();
		DecisionForest df = new DecisionForest(trainingData, dictionary, 100, 400, 50);
		df.buildForest();
		df.testData(testingData);
		ti.Clear();
		df.Clear();
		long endMili=System.currentTimeMillis();
		System.out.println("running time is " + (endMili - startMili));
		
		
		System.out.println("dimension is 500");
		startMili=System.currentTimeMillis();
		ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti.setDictionary(500);
		ti.setTrainData();
		ti.setTestData();
		trainingData = ti.getTrainingData();
		testingData = ti.getTestingData();
		dictionary = ti.getDictionary();
		df = new DecisionForest(trainingData, dictionary, 100, 400, 200);
		df.buildForest();
		df.testData(testingData);
		ti.Clear();
		df.Clear();
		endMili=System.currentTimeMillis();
		System.out.println("running time is " + (endMili - startMili));
		
		System.out.println("dimension is 1000");
		startMili=System.currentTimeMillis();
		ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti.setDictionary(1000);
		ti.setTrainData();
		ti.setTestData();
		trainingData = ti.getTrainingData();
		testingData = ti.getTestingData();
		dictionary = ti.getDictionary();
		df = new DecisionForest(trainingData, dictionary, 100, 400, 200);
		df.buildForest();
		df.testData(testingData);
		ti.Clear();
		df.Clear();
		endMili=System.currentTimeMillis();
		System.out.println("running time is " + (endMili - startMili));
		
		System.out.println("dimension is 2000");
		startMili=System.currentTimeMillis();
		ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti.setDictionary(2000);
		ti.setTrainData();
		ti.setTestData();
		trainingData = ti.getTrainingData();
		testingData = ti.getTestingData();
		dictionary = ti.getDictionary();
		df = new DecisionForest(trainingData, dictionary, 100, 400, 200);
		df.buildForest();
		df.testData(testingData);
		ti.Clear();
		df.Clear();
		endMili=System.currentTimeMillis();
		System.out.println("running time is " + (endMili - startMili));
		
		System.out.println("dimension is 5000");
		startMili=System.currentTimeMillis();
		ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti.setDictionary(5000);
		ti.setTrainData();
		ti.setTestData();
		trainingData = ti.getTrainingData();
		testingData = ti.getTestingData();
		dictionary = ti.getDictionary();
		df = new DecisionForest(trainingData, dictionary, 100, 400, 200);
		df.buildForest();
		df.testData(testingData);
		ti.Clear();
		df.Clear();
		endMili=System.currentTimeMillis();
		System.out.println("running time is " + (endMili - startMili));
		
		System.out.println("dimension is 10000");
		startMili=System.currentTimeMillis();
		ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti.setDictionary(10000);
		ti.setTrainData();
		ti.setTestData();
		trainingData = ti.getTrainingData();
		testingData = ti.getTestingData();
		dictionary = ti.getDictionary();
		df = new DecisionForest(trainingData, dictionary, 100, 400, 200);
		df.buildForest();
		df.testData(testingData);
		ti.Clear();
		df.Clear();
		endMili=System.currentTimeMillis();
		System.out.println("running time is " + (endMili - startMili));
		
		System.out.println("dimension is all");
		startMili=System.currentTimeMillis();
		ti = new tfidf(formatTrainDoc, formatTestDoc, allWords);
		ti.setDictionary(-1);
		ti.setTrainData();
		ti.setTestData();
		trainingData = ti.getTrainingData();
		testingData = ti.getTestingData();
		dictionary = ti.getDictionary();
		df = new DecisionForest(trainingData, dictionary, 100, 400, 200);
		df.buildForest();
		df.testData(testingData);
		ti.Clear();
		df.Clear();
		endMili=System.currentTimeMillis();
		System.out.println("running time is " + (endMili - startMili));
	}

}
