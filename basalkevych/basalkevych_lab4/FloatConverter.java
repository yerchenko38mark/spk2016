public class FloatConverter {
	
	private static final int MAIN_PART = 6;
	private static final int DOT_PART = 4;
	
	public static float byteArrayToFloat(byte[] arr, float shift){
		float res = 0.0f;
		
		int current = (int) Math.pow(2, MAIN_PART-1);
		
		for(int i = 0; i < MAIN_PART; i++){
			if(arr[i]!=0){
				res+=current;
			}
			current/=2;
		}
		
		float start_dot = 0.5f;
		for(int i = 0; i < DOT_PART; i++){
			if(arr[i+MAIN_PART]!=0){
				res+=start_dot;
			}
			start_dot/=2;
		}
		return res-shift;
	}
	
	public static byte[] floatToByteArray(float f, float shift){
		byte[] res = new byte[MAIN_PART + DOT_PART];
		
		f+=shift;

		int main = (int) f;
		float dot = (f - main);
		
		int current = (int) Math.pow(2, MAIN_PART-1);
		
		for(int i = 0; i < MAIN_PART; i++){
			if(main >= current){
				res[i] = 1;
				main-=current;
			}else{
				res[i] = 0;
			}
			current/=2;
		}
		
		float start_dot = 0.5f;
		for(int i = 0; i < DOT_PART; i++){
			if(dot >= start_dot){
				res[i+MAIN_PART] = 1;
				dot-=start_dot;
			}else{
				res[i+MAIN_PART] = 0;
			}
			start_dot/=2;
		}
		return res;
	}

	public static float byteArrayToFloat(byte[] arr) {
		return byteArrayToFloat(arr, Constants.SHIFT);
	}

	public static byte[] floatToByteArray(float value) {
		return floatToByteArray(value, Constants.SHIFT);
	}
}

