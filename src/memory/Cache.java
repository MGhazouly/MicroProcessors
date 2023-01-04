package memory;

public class Cache {
	private int[] cacheArray;
	
	public Cache() {
		this.cacheArray = new int[2048];
		this.setData(12, 100);
		this.setData(13, 100);
		this.setData(14, 100);
	}
	
	public int getData(int decAddress) {
		return this.cacheArray[decAddress];
	}
	
	public void setData(int decAddress,int data) {
		this.cacheArray[decAddress] = data;
	}
	public int getsize() {
		return this.cacheArray.length;

	}
	public void printCache() {
		for (int i = 0; i < this.cacheArray.length; i++) {
			System.out.println("Cache location : " + i + " " + this.cacheArray[i]);
		}
	}
	
}
