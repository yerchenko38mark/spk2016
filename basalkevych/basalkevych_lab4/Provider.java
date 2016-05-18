import java.util.Random;

public class Provider {
	private static Random rand = new Random();
	
	public static Individual[] provide(Individual[] parents){
		
		Individual[] res = new Individual[Constants.SELECTION_SIZE];
		
		int current = 0;
		
		for(int i = 0; i < parents.length-1; i++){
			for(int j = i+1; j < parents.length; j++){
				Individual[] currentGenes = makeChildren(parents[i], parents[j]);
				for(int k = 0; k < currentGenes.length; k++){
					individualMutate(currentGenes[k]);
					res[current+k] = currentGenes[k];
				}
				current+=currentGenes.length;
			}
		}
		
		return res;
	}

	private static void individualMutate(Individual currentGene) {
		for(int i = 0; i < currentGene.getGenes().length; i++){
			if(rand.nextDouble()<=Constants.MUTATION_PROBABILITY){
				currentGene.mutate(i);
			}
		}
	}

	private static Individual[] makeChildren(Individual p1, Individual p2) {
		byte[] g1 = p1.getGenes();
		byte[] g2 = p2.getGenes();
		int length = Math.min(g1.length, g2.length);
		byte[] child1 = new byte[length];
		byte[] child2 = new byte[length];
		
		int crossPoint = 1 + rand.nextInt(length-2);
		boolean isFirstParent = rand.nextBoolean();
		
		for(int i = 0; i < crossPoint; i++){
			if(isFirstParent){
				child1[i] = g1[i];
				child2[i] = g2[i];
			}
			else{
				child1[i] = g2[i];
				child2[i] = g1[i];
			}
		}
		
		for(int i = crossPoint; i < length; i++){
			if(!isFirstParent){
				child1[i] = g1[i];
				child2[i] = g2[i];
			}
			else{
				child1[i] = g2[i];
				child2[i] = g1[i];
			}
		}
		
		return new Individual[]{
				new Individual(child1),
				new Individual(child2)
		};
	}
}
