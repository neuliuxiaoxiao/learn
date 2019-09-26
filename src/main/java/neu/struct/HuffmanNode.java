package neu.struct;

public class HuffmanNode implements Comparable, Cloneable {
	protected int key;				// 鏉冨��
	protected HuffmanNode left;		// 宸﹀瀛�
	protected HuffmanNode right;	// 鍙冲瀛�
	protected HuffmanNode parent;	// 鐖剁粨鐐�

	protected HuffmanNode(int key, HuffmanNode left, HuffmanNode right, HuffmanNode parent) {
		this.key = key;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	@Override
	public Object clone() {
		Object obj=null;
		
		try {
			obj = (HuffmanNode)super.clone();//Object 涓殑clone()璇嗗埆鍑轰綘瑕佸鍒剁殑鏄摢涓�涓璞°��    
		} catch(CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		
		return obj;    
	}

	@Override
    public int compareTo(Object obj) {
		return this.key - ((HuffmanNode)obj).key;
	}
}
