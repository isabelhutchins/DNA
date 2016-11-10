import java.util.HashMap;
import java.util.Stack;

public class LinkStrand implements IDnaStrand{
	private Node myFirst;
	private Node myLast;
	private long mySize;
	private int myAppends;
	private Node currentNode;
	private int currentDex;
	private long currentCharCount;
	
	private class Node {
		String info;
		Node next;
		public Node(String s) {
			info = s;
			next = null;
		}
	}
	
	public LinkStrand(String info){
		initialize(info);
	}
	
	public LinkStrand(){
		this("");
	}
	
	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast=myFirst;
		mySize=source.length();
		myAppends=0;
		currentNode=myFirst;
		currentDex=0;
		currentCharCount=0;
	}
	
	@Override
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		int pos = 0;
		int start = 0;
		String search = myFirst.info;
		boolean first = true;
		IDnaStrand ret = null;
		
		if (myFirst.next!=null){
			throw new UnsupportedOperationException("Unsupported Operation Exception");
		}
		
		while ((pos = search.indexOf(enzyme, start)) >= 0) {
			if (first) {
				ret = getInstance(search.substring(start, pos));
				first = false;
			} else {
				ret.append(search.substring(start, pos));
			}
			start = pos + enzyme.length();
			ret.append(splicee);
			pos++;
		}
		if (start < search.length()) {
			// NOTE: This is an important special case! If the enzyme
			// is never found, return an empty String.
			if (ret == null) {
				ret = getInstance("");
			} else {
				ret.append(search.substring(start));
			}
		}
		return ret;
	}
	
	
	@Override
	public long size() {
		return mySize;
	}


	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		Node toAppend = new Node(dna);
		myLast.next=toAppend;
		myLast=myLast.next;
		myLast.next=null;
		myAppends++;
		mySize+=dna.length();
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		Node temp = myFirst;
		//if only one node, you can avoid making a map and a stack, etc. 
		if (temp.next==null){
		StringBuilder toReverse = new StringBuilder(temp.info);
		LinkStrand reversedStrand = new LinkStrand(toReverse.reverse().toString());
		return reversedStrand;
		}
		
		HashMap<StringBuilder, StringBuilder>reverses=new HashMap<>();
		Stack <Node> reverseListStack = new Stack<>();
		
		while (temp!=null){
			StringBuilder toReverse = new StringBuilder(temp.info);
			if (reverses.containsKey(toReverse)){
				reverseListStack.push(new Node(reverses.get(toReverse).toString()));
			}else{
				reverses.put(toReverse, toReverse.reverse());
				reverseListStack.push(new Node(toReverse.toString()));
			}
			temp = temp.next;
		}
		
		temp = reverseListStack.pop();
		LinkStrand reversedStrand = new LinkStrand(temp.info);
		temp = reversedStrand.myFirst;
		
		while(reverseListStack.empty()==false){
			temp.next=reverseListStack.pop();
			temp=temp.next;	
		}
		return reversedStrand;
	}

	@Override
	public String getStats() {
		return String.format("# appends = %d", myAppends);
	}

	@Override
	public char charAt(int index) {
		if (index>size())
			throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
			
			long count = 0;
			int dex = 0;
			Node list = myFirst;
			
			if (index-currentCharCount==1){
				 count = currentCharCount;
				 dex = currentDex;
				 list = currentNode;
			}
			while (count != index) {
				count++;
				dex++;
				if (dex >= list.info.length()) {
					dex = 0;
					list = list.next;
				}
			}
			currentCharCount=count;
			currentDex=dex;
			currentNode=list;
	        return list.info.charAt(dex);	         
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node temp = myFirst;
		
		while (temp!=null){
			sb.append(temp.info);
			temp = temp.next;
		}
		return sb.toString();
	}
	
	





}
