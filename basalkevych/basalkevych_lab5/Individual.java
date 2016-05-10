public class Individual {
	private byte[] genes;
	private float path;
	
	public Individual(byte[] genes){
		this.genes = genes;
		this.path = PathCalculator.findPathLength(this);
	}
	
	public float getPath(){
		return path;
	}
	
	public byte[] getGenes(){
		return genes;
	}

	public void setValue(int position, byte value){
		if(position<genes.length){
			genes[position] = value;
		}
	}
	
}
