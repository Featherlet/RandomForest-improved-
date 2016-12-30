import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class PrintData {
	
	public void printDict(ArrayList<wordUnit> dict, String name){
		try{
            FileOutputStream out=new FileOutputStream("E:/javaWorkspace/MyMulDecisionTree(new)/" + name);
            PrintStream p=new PrintStream(out);
            p.println("0, 1, 2, 3.");
            p.println();
            for(int i = 0; i < dict.size(); i++){
            	String line = "'" + dict.get(i).word + "', ";
                p.print(line);
            }
            p.print("......");
            p.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
		System.out.println("finish writing " + name);
	}

	public void writeDictionary(ArrayList<wordUnit> dict, String name){
		try{
            FileOutputStream out=new FileOutputStream("E:/javaWorkspace/MyMulDecisionTree(new)/" + name);
            PrintStream p=new PrintStream(out);
            for(int i = 0; i < dict.size(); i++){
            	String line = "";
            	line = line + dict.get(i).word + " " + dict.get(i).frequency + " " +  dict.get(i).idfCounter + " " 
            			+ dict.get(i).train_idf + " " + dict.get(i).dfc[0] + " "+ dict.get(i).dfc[1] + " "
            			+ dict.get(i).dfc[2] + " "+ dict.get(i).dfc[3] + " "+ dict.get(i).weight; 
                p.println(line);
            }
            p.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
		System.out.println("finish writing dict");
	}
}
