import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Provider {
	private static Random rand = new Random();
	
	public static Individual[] provide(Individual[] parents){
		
		Individual[] res = new Individual[Constants.SELECTION_SIZE];
		
		
		float totalPath = 0.0f;
		for(int i = 0; i < parents.length; i++){
			totalPath+=parents[i].getPath();
		}
		
		float sumDivided = 0.0f;
		for(int i = 0; i < parents.length; i++){
			sumDivided+=totalPath/parents[i].getPath();
		}
		
		float[] probability = new float[parents.length+1];
		for(int i = 0;i < parents.length; i++){
			if(i == 0){
				probability[i] = totalPath / parents[i].getPath() / sumDivided;
			}else{
				probability[i] = probability[i-1] +
						totalPath / parents[i].getPath() / sumDivided;
			}
		}
		probability[parents.length] = 1f;
		
		int current = 0;
		while(current<Constants.SELECTION_SIZE){
			
			int p1 = 0, p2 = 0;
			float randomValue = rand.nextFloat();
			while(p1 < parents.length && probability[p1] < randomValue){
				p1++;
			}
			randomValue = rand.nextFloat();
			while(p2<parents.length && probability[p2]<randomValue){
				p2++;
			}
			
			if(p1 != p2){
				Individual[] currentGenes = makeChildren(parents[p1], parents[p2]);
				for(int z = 0;z < currentGenes.length; z++){
					addMutation(currentGenes[z]);
					res[current+z] = currentGenes[z];
				}
				current += currentGenes.length;
			}
		}
		
		
		return res;
	}
	
	private static void addMutation(Individual currentGene) {
		for(int i = 0; i < currentGene.getGenes().length; i++){
			if(rand.nextDouble()<=Constants.MUTATION_PROBABILITY){
				
				int p1 = rand.nextInt(Constants.CITY_COUNT);
				int p2 = rand.nextInt(Constants.CITY_COUNT);
				
				byte v1 = currentGene.getGenes()[p1];
				byte v2 = currentGene.getGenes()[p2];
				
				currentGene.setValue(p2, v1);
				currentGene.setValue(p1, v2);
				
			}
		}
	}

	private static Individual[] makeChildren(Individual p1, Individual p2) {
		byte[] g1 = p1.getGenes();
		byte[] g2 = p2.getGenes();
		int length = Math.min(g1.length, g2.length);
		byte[] child1 = new byte[length];
		byte[] child2 = new byte[length];
		
		int current = 0;
		int previous = -1;
		for(int i = 0; i < length; i++){
			current = i;
			if(i == 0){
				child1[0] = g1[0];
			}else{
				if(i%2 == 0){
					previous = child1[current-1];
					int sameItemPosition = -1;
					for(int z = 0; z < length; z++) 
						if(g1[z] == previous){
						sameItemPosition = z;
					}
					
					byte next = -1;
					if(sameItemPosition < length-1){
						next = g1[sameItemPosition + 1];
					}else{
						next = g1[0];
					}
					
					boolean childHasItem = false;
					for(int z = 0; z < current; z++)
						if(child1[z] == next) 
							childHasItem = true;
					if(childHasItem){
						break;
					}
					else{
						child1[current] = next;
					}
				}else{
					previous = child1[current - 1];
					int sameItemPosition = -1;
					for(int z = 0; z < length; z++) 
						if(g2[z] == previous){
						sameItemPosition = z;
					}
					
					byte next = -1;
					if(sameItemPosition < length - 1){
						next = g2[sameItemPosition + 1];
					}else{
						next = g2[0];
					}
					
					boolean childHasItem = false;
					for(int z = 0; z < current; z++)
						if(child1[z] == next) 
							childHasItem = true;
					if(childHasItem){
						break;
					}
					else{
						child1[current] = next;
					}
				}
			}
		}
		
		List<Byte> list = new ArrayList<Byte>();
		
		list.clear();
		for(int i = 0; i < Constants.CITY_COUNT; i++){
			boolean has = false;
			for(int j = 0; j < current; j++){
				if(child1[j] == i) has = true;
			}
			if(!has)
				list.add((byte) i);
		}
		
		for(int i = current; i < length; i++){
			if(!list.isEmpty()){
				int pos = rand.nextInt(list.size());
				child1[current] = (byte) list.get(pos);
				list.remove(pos);
			}
			current++;
		}
		
		current = 0;
		previous = -1;
		for(int i = 0;i < length; i++){
			current = i;
			if(i == 0){
				child2[0] = g2[0];
			}else{
				if(i%2 == 0){
					previous = child2[current-1];
					int sameItemPosition = -1;
					for(int z = 0; z < length; z++) 
						if(g2[z]==previous){
							sameItemPosition = z;
					}
					
					byte next = -1;
					if(sameItemPosition < length-1){
						next = g2[sameItemPosition + 1];
					}else{
						next = g2[0];
					}
					
					boolean childHasItem = false;
					for(int z = 0; z < current; z++)
						if(child2[z] == next) 
							childHasItem = true;
					if(childHasItem){
						break;
					}
					else{
						child2[current] = next;
					}
				}else{
					previous = child2[current - 1];
					int sameItemPosition = -1;
					for(int z = 0; z < length; z++)
						if(g1[z]==previous){
							sameItemPosition = z;
					}
					
					byte next = -1;
					if(sameItemPosition < length - 1){
						next = g1[sameItemPosition + 1];
					}else{
						next = g1[0];
					}
					
					boolean childHasItem = false;
					for(int z = 0; z < current; z++)
						if(child2[z] == next) 
							childHasItem = true;
					if(childHasItem){
						break;
					}
					else{
						child2[current] = next;
					}
				}
			}
		}
		
		list.clear();
		for(int i = 0; i < Constants.CITY_COUNT; i++){
			boolean has = false;
			for(int j = 0; j < current; j++){
				if(child2[j] == i) has = true;
			}
			if(!has)
				list.add((byte) i);
		}
		
		for(int i = current; i < length; i++){
			if(!list.isEmpty()){
				int pos = rand.nextInt(list.size());
				child2[current] = (byte) list.get(pos);
				list.remove(pos);
			}
			current++;
		}
		
		return new Individual[]{
				new Individual(child1),
				new Individual(child2)
				};
	}
	
}
