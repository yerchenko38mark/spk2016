public class PathCalculator {
	
	public static float findPathLength(Individual individual){
		float res = 0.0f;
		byte[] path = individual.getGenes();
		for(int i = 0; i < path.length-1; i++){
			res+=getDistance(path[i], path[i+1]);
		}
		
		res+=getDistance(path[path.length-1], path[0]);

		return res;
	}
	
	private static float getDistance(int first, int second){
		Point firstPoint = CityNet.getInstance().getPoints()[first];
		Point secondPoint = CityNet.getInstance().getPoints()[second];
		
		float res = 0.0f;
		res = (float) Math.sqrt((firstPoint.x-secondPoint.x) * (firstPoint.x-secondPoint.x) 
				+ (firstPoint.y-secondPoint.y) * (firstPoint.y-secondPoint.y));
		
		return res;
	}
}
