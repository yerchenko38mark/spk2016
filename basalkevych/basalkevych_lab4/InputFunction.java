
public class InputFunction {
	//f(x) = A + Bx + Cx^2 + Dx^3
	
	public static float calc(float x){
		float res = -(Constants.A +
				Constants.B*x + 
				Constants.C*x*x+
				Constants.D*x*x*x);
		return res;
	}
	
}

