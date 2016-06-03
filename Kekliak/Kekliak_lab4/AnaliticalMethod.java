public class AnaliticalMethod {
	private static float DELTA = 0.001f;
	
	public float bestX;
	public float bestY;
	
	public AnaliticalMethod(){}
	
	public void solve(float left, float right){
		bestX = left;
		bestY = InputFunction.calc(left);
		for(float f = left + DELTA; f <= right; f+= DELTA)
		{
			float newY = InputFunction.calc(f);
			if(newY < bestY)
			{
				bestX = f;
				bestY = newY;
			}
		}
	}
	
	public static void main(String[] args){
		AnaliticalMethod analiticalMethod = new AnaliticalMethod();
		analiticalMethod.solve(-10f, 54f);
		System.out.println("Analitical method\n" +
				"Found x="+analiticalMethod.bestX+"; y="+analiticalMethod.bestY);
	}
}

