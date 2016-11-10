
public class testReverseMultipleNodes {
	public static void main(String[] args){
		/*I used this class to varify that sequences of "append" calls works 
		 * and to verify that the reverse method works on strands with
		 * multiple nodes.
		 * 
		 */
		LinkStrand first = new LinkStrand("first");
		first.append("second");
		first.append("third");
		first.append("fourth");
		System.out.println("LinkStrand normal: " + first.toString());
		
		System.out.println("LinkStrand reversed: " + first.reverse().toString());
		first.cutAndSplice("gat", "ccccc");
	}
}
