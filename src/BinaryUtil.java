
public class BinaryUtil {
	public static String toBinaryString (int n){
        StringBuffer sb = new StringBuffer ();
        while ( n > 0 ){
            int bit = n%2;
            sb.insert(0, bit);
            n=n/2;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println (toBinaryString(25));
    }

}
