import java.util.ArrayList;
	
public class Document {
	
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

	public ArrayList<String> words;
	public int[] frequency;
	public int label;
	public int doclenth = 0;
	
	public Document(String doc){
		words = new ArrayList<String>();
		doc = doc.toLowerCase();
		int thelable = doc.charAt(0) - '0';
		this.label = thelable;
		//then turn doc the non-letter characters into space.
		for(int i = 0; i < doc.length(); i++){ 
			if(doc.charAt(i) < 97 || doc.charAt(i) > 122){
				char c = doc.charAt(i);
				doc = doc.replace(c, ' ');
			}
		}
        String[] docwords = doc.split(" ");
        
        frequency = new int[docwords.length];
        Stemmer stemmer = new Stemmer();
		for(int i = 0; i < docwords.length; i++){
			String s = docwords[i];
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
				if(words.contains(s)){
					int index = words.indexOf(s);
					frequency[index]++;
				}else{
					int length = words.size();
					words.add(s);
					frequency[length] = 1;
				}
			}
		}
		
		for(int i = 0; i < frequency.length; i++)
			doclenth = doclenth + frequency[i];
	}
	
}
