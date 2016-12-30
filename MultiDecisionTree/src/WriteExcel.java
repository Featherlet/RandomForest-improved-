import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class WriteExcel {
	
	public void writeData(ArrayList<ArrayList<Double>> data, ArrayList<wordUnit> dict, String name){
		try{
            FileOutputStream out=new FileOutputStream("E:/javaWorkspace/MyMulDecisionTree(new)/" + name);
            PrintStream p=new PrintStream(out);
            String line = "0, ";
            for(int i = 0; i < dict.size(); i++){
            	line += "" + dict.get(i).word + ", ";
            }
            p.println(line);            
            for(int i = 0; i < data.size(); i++){
            	line = "";
            	for(int j = 0; j < data.get(i).size(); j++)
            		line += data.get(i).get(j).toString() + ", ";
            	p.println(line);  
            }
            p.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
		System.out.println("finish writing " + name);
	}
	
	public void writeData2(ArrayList<ArrayList<Double>> data, String name){
		try{
            FileOutputStream out=new FileOutputStream("E:/javaWorkspace/MyMulDecisionTree(new)/" + name);
            PrintStream p=new PrintStream(out);          
            for(int i = 0; i < data.size(); i++){
            	String line = "";
            	int j;
            	for(j = 0; j < data.get(i).size() - 1; j++){
            		line += data.get(i).get(j).toString() + ", ";
            		System.out.println(i + " " + j);
            	}
            	line += data.get(i).get(j).toString();
            	System.out.println(i + " " + j);
            	p.println(line);  
            }
            p.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
		System.out.println("finish writing " + name);
	}

}
