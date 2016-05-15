import java.util.Random;

public class CityNet {
	// Singleton
	
	private static CityNet instance;
	
	public static CityNet getInstance(){
		if(instance != null) return instance;
		else return null;
	}
	
	public static void Create(Point[] coordinates){
		instance = new CityNet(coordinates);
	}
	
	private CityNet(Point[] coordinates) {
		this.citysCoordinates = coordinates;
	}
	
	private Point[] citysCoordinates;
	
	public Point[] getPoints(){
		return citysCoordinates;
	}
	
	private static Random rand = new Random();
	
	public static Point[] generateMap(){
		int point = Constants.CITY_COUNT;
		
		Point[] res = new Point[point];
		
		int x,y;
		for(int i = 0; i < point; i++){
			x = rand.nextInt(Constants.MAX_X);
			y = rand.nextInt(Constants.MAX_Y);
			
			res[i] = new Point(x, y);
			System.out.println("City x:" + x +"; y:" + y);
		}
		
		return res;
	}
}
