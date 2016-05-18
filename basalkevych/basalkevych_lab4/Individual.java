public class Individual {
	private byte[] genes;
	
	public Individual(byte[] genes){
		this.genes = genes;
	}
	
	public byte[] getGenes(){
		return genes;
	}

	public void mutate(int i) {
		if(i < genes.length)
		{
			genes[i] = (byte) (1 - genes[i]);
		}
	}
	
	public float getFloatValue(){
		return FloatConverter.byteArrayToFloat(genes);
	}
}
