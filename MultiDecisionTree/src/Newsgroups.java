import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Newsgroups {
	//a set of stop words that are useless.
	private static String[] stopWords = {"a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", 
				"almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount", 
				"an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at",
				"back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being",
				"below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", 
				"cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during",
				"each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every",
				"everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five",
				"for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", 
				"had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers",
				"herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest",
				"into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many",
				"may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must",
				"my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none",
				"noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto",
				"or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps",
				"please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", 
				"she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", 
				"something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that",
				"the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein",
				"thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through",
				"throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two",
				"un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever",
				"when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", 
				"whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with",
				"within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the", "br", "b", "c", "d",
				"e", "f", "g","h","i","j", "k", "l", "m","n","o","p", "q", "r", "s","t","u","v", "w", "x", "y","z"};

private ArrayList<Document> formatTrainDoc = null;
private ArrayList<Document> formatTestDoc = null;
private ArrayList<wordUnit> allWords = null;
private String[] filenames0 = null;
private String[] filenames1 = null;
private String[] filenames2 = null;
private String[] filenames3 = null;
String root0 = "E:/javaWorkspace/PrepareData/4_newsgroup/comp.graphics";
String root1 = "E:/javaWorkspace/PrepareData/4_newsgroup/comp.windows.x";
String root2 = "E:/javaWorkspace/PrepareData/4_newsgroup/rec.sport.baseball";
String root3 = "E:/javaWorkspace/PrepareData/4_newsgroup/soc.religion.christian";

public Newsgroups(){
	File file0 = new File(root0);
	filenames0 = file0.list();
	File file1 = new File(root1);
	filenames1 = file1.list();
	File file2 = new File(root2);
	filenames2 = file2.list();
	File file3 = new File(root3);
	filenames3 = file3.list();
}

public void readTrainingData(int st, int en){
	ArrayList<String> rowTrainingDoc = new ArrayList<String>();
	String line = null;
	String all = "";
	//read class 0
	BufferedReader fileReader0 = null;
	for(int i = st ; i <= en; i++){
		String filename = filenames0[i];
		String text = null;
		int flag = 0;
		try{
	    	fileReader0=new BufferedReader(new FileReader(new File(root0 + "/" + filename)));	    	
	    	while((line=fileReader0.readLine())!=null){	    		
	    		if(flag == 1){
	    			text = text + line;
	    		}else{
	    			if(line.equals("")){
		    			flag = 1;
		    			continue;
		    		}
	    		}	    		
	    	}
	    }catch(FileNotFoundException e){
	    	e.printStackTrace();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }		
		all = all + " " + text;
		String doc = "0 " + text;
		rowTrainingDoc.add(doc);
	}
	//read class 1
	BufferedReader fileReader1 = null;
	for(int i = st ; i <= en; i++){
		String filename = filenames1[i];
		String text = null;
		int flag = 0;
		try{
	    	fileReader1=new BufferedReader(new FileReader(new File(root1 + "/" + filename)));	    	
	    	while((line=fileReader1.readLine())!=null){
	    		if(flag == 1){
	    			text = text + line;
	    		}else{
	    			if(line.equals("")){
		    			flag = 1;
		    			continue;
		    		}
	    		}	    		
	    	}
	    }catch(FileNotFoundException e){
	    	e.printStackTrace();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }		
		all = all + " " + text;
		String doc = "1 " + text;
		rowTrainingDoc.add(doc);
	}
	//read class 2
	BufferedReader fileReader2 = null;
	for(int i = st ; i <= en; i++){
		String filename = filenames2[i];
		String text = null;
		int flag = 0;
		try{
	    	fileReader2=new BufferedReader(new FileReader(new File(root2 + "/" + filename)));	    	
	    	while((line=fileReader2.readLine())!=null){
	    		if(flag == 1){
	    			text = text + line;
	    		}else{
	    			if(line.equals("")){
		    			flag = 1;
		    			continue;
		    		}
	    		}	    		
	    	}
	    }catch(FileNotFoundException e){
	    	e.printStackTrace();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }		
		all = all + " " + text;
		String doc = "2 " + text;
		rowTrainingDoc.add(doc);
	}
	//read class 3
	BufferedReader fileReader3 = null;
	for(int i = st ; i <= en; i++){
		String filename = filenames3[i];
		String text = null;
		int flag = 0;
		try{
	    	fileReader3=new BufferedReader(new FileReader(new File(root3 + "/" + filename)));	    	
	    	while((line=fileReader3.readLine())!=null){
	    		if(flag == 1){
	    			text = text + line;
	    		}else{
	    			if(line.equals("")){
		    			flag = 1;
		    			continue;
		    		}
	    		}	    		
	    	}
	    }catch(FileNotFoundException e){
	    	e.printStackTrace();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }		
		all = all + " " + text;
		String doc = "3 " + text;
		rowTrainingDoc.add(doc);
	}
	
    /*//class 0
    ArrayList<Integer> set0 = new ArrayList<Integer>(); 
	int counter0 = 0;
	while(true){
		Random random = new Random();  
		int randomNumber =  random.nextInt(1000 - 1)%(1000); 
		if(set0.contains(randomNumber)){
			continue;
		}else{
			set0.add(randomNumber);
			counter0++;
		}
		if(counter0 == num0)
			break;
	}
    //read files from class 0
	BufferedReader fileReader0 = null;
	for(int i = 0 ; i < set0.size(); i++){
		String filename = filenames0[i];
		String text = null;
		try{
	    	fileReader0=new BufferedReader(new FileReader(new File(filename)));	    	
	    	while((line=fileReader0.readLine())!=null){
	    		text = text + line;
	    	}
	    }catch(FileNotFoundException e){
	    	e.printStackTrace();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }		
		all = all + " " + text;
		String doc = "0 " + text;
		rowTrainingDoc.add(doc);
	}*/

    
    //initial all words
    allWords = new ArrayList<wordUnit>();
    all = all.toLowerCase();
	//then turn all the non-letter characters into space.
	for(int i = 0; i < all.length(); i++){ 
		if(all.charAt(i) < 97 || all.charAt(i) > 122){
			char c = all.charAt(i);
			all = all.replace(c, ' ');
		}
	}
    
    String[] words = all.split(" ");
    ArrayList<String> record = new ArrayList<String>(); //keep the same index of each word with allWords
    int maxf = 0;
    Stemmer stemmer = new Stemmer();
	for(int i = 0; i < words.length; i++){
		String s = words[i];
		while(s.startsWith(" ")){
			s = s.substring(1);
		}
		while(s.endsWith(" ")){
			s = s.substring(0, s.length()-2);
		}
		if(s.length() == 0) continue; 
		s = stemmer.getResult(s);	
		int k = 0;
		for(int j = 0; j < stopWords.length; j++){ // if c is a stop word, k = 1.
			String sword = stopWords[j];
			if(s.equals(sword)){
				k = 1;
				break;
			}
		}
		if(k == 0){ // s isn't a stop word
			if(record.contains(s)){
				int index = record.indexOf(s);
				allWords.get(index).frequency++;
				if(allWords.get(index).frequency > maxf)
					maxf = allWords.get(index).frequency;
			}else{
				record.add(s);
				wordUnit newword = new wordUnit();
				newword.word = newword.word + s;
				newword.frequency = 1;
				allWords.add(newword);
			}
		}
	}
	
	formatTrainDoc = new ArrayList<Document>();		
	for(int i = 0; i < rowTrainingDoc.size(); i++){
		String doc = rowTrainingDoc.get(i);
		Document ndoc = new Document(doc);
		formatTrainDoc.add(ndoc);
	}
	
	//calculate all the initial values 
	for(int i = 0; i < allWords.size(); i++){
		String word = allWords.get(i).word;
		for(int j = 0; j < formatTrainDoc.size(); j++){
			if(formatTrainDoc.get(j).words.contains(word)){
				allWords.get(i).idfCounter++;
				int label = formatTrainDoc.get(j).label;
				allWords.get(i).dfc[label]++;
			}
		}
	}
	
    System.out.println("read training data successfully! The data size is " + formatTrainDoc.size());
}

public void readTestingData(int st, int en){
	ArrayList<String> rowTestingDoc = new ArrayList<String>();
	String line = null;
	//read class 0
		BufferedReader fileReader0 = null;
		for(int i = st ; i <= en; i++){
			String filename = filenames0[i];
			String text = null;
			int flag = 0;
			try{
		    	fileReader0=new BufferedReader(new FileReader(new File(root0 + "/" + filename)));	    	
		    	while((line=fileReader0.readLine())!=null){
		    		if(flag == 1){
		    			text = text + line;
		    		}else{
		    			if(line.equals("")){
			    			flag = 1;
			    			continue;
			    		}
		    		}	    		
		    	}
		    }catch(FileNotFoundException e){
		    	e.printStackTrace();
		    }catch(IOException e){
		    	e.printStackTrace();
		    }		
			String doc = "0 " + text;
			rowTestingDoc.add(doc);
		}
		//read class 1
		BufferedReader fileReader1 = null;
		for(int i = st ; i <= en; i++){
			String filename = filenames1[i];
			String text = null;
			int flag = 0;
			try{
		    	fileReader1=new BufferedReader(new FileReader(new File(root1 + "/" + filename)));	    	
		    	while((line=fileReader1.readLine())!=null){
		    		if(flag == 1){
		    			text = text + line;
		    		}else{
		    			if(line.equals("")){
			    			flag = 1;
			    			continue;
			    		}
		    		}	    		
		    	}
		    }catch(FileNotFoundException e){
		    	e.printStackTrace();
		    }catch(IOException e){
		    	e.printStackTrace();
		    }		
			String doc = "1 " + text;
			rowTestingDoc.add(doc);
		}
		//read class 2
		BufferedReader fileReader2 = null;
		for(int i = st ; i <= en; i++){
			String filename = filenames2[i];
			String text = null;
			int flag = 0;
			try{
		    	fileReader2=new BufferedReader(new FileReader(new File(root2 + "/" + filename)));	    	
		    	while((line=fileReader2.readLine())!=null){
		    		if(flag == 1){
		    			text = text + line;
		    		}else{
		    			if(line.equals("")){
			    			flag = 1;
			    			continue;
			    		}
		    		}	    		
		    	}
		    }catch(FileNotFoundException e){
		    	e.printStackTrace();
		    }catch(IOException e){
		    	e.printStackTrace();
		    }		
			String doc = "2 " + text;
			rowTestingDoc.add(doc);
		}
		//read class 3
		BufferedReader fileReader3 = null;
		for(int i = st ; i <= en; i++){
			String filename = filenames3[i];
			String text = null;
			int flag = 0;
			try{
		    	fileReader3=new BufferedReader(new FileReader(new File(root3 + "/" + filename)));	    	
		    	while((line=fileReader3.readLine())!=null){
		    		if(flag == 1){
		    			text = text + line;
		    		}else{
		    			if(line.equals("")){
			    			flag = 1;
			    			continue;
			    		}
		    		}	    		
		    	}
		    }catch(FileNotFoundException e){
		    	e.printStackTrace();
		    }catch(IOException e){
		    	e.printStackTrace();
		    }		
			String doc = "3 " + text;
			rowTestingDoc.add(doc);
		}
    
    formatTestDoc = new ArrayList<Document>();	
	for(int i = 0; i < rowTestingDoc.size(); i++){
		String doc = rowTestingDoc.get(i);
		Document ndoc = new Document(doc);
		formatTestDoc.add(ndoc);
	}
	
    System.out.println("read testing data successfully! The data size is " + formatTestDoc.size());
}

public ArrayList<Document> getFormatTrainDoc() {
	return formatTrainDoc;
}

public ArrayList<Document> getFormatTestDoc() {
	return formatTestDoc;
}

public ArrayList<wordUnit> getAllWords() {
	return allWords;
}

}
