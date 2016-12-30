
public class wordUnit {
	public String word = "";
	public int frequency = 0;
	public double train_idf = 0;
	public int idfCounter = 0;
	public int[] dfc = {0, 0, 0, 0};
	public double weight = 0;
	public int classCounter = 0;
	public double c = 0;
	public double e = 0;
	
	public wordUnit copy(){
		wordUnit wu = new wordUnit();
		wu.word = this.word;
		wu.frequency = this.frequency;
		wu.idfCounter = this.idfCounter;
		wu.train_idf = this.train_idf;
		wu.dfc = new int[4];
		wu.dfc[0] = this.dfc[0];
		wu.dfc[1] = this.dfc[1];
		wu.dfc[2] = this.dfc[2];
		wu.dfc[3] = this.dfc[3];
		wu.weight = this.weight;
		wu.classCounter = this.classCounter;
		wu.c = this.c;
		wu.e = this.e;
		return wu;
	}
}