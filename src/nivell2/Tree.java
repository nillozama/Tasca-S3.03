package nivell2;

public class Tree extends Product {

	private String height;
	
	public Tree(int id,String name, String type, float price, String height) {
		super(id, name, type, price);
		this.height=height;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "[nom=" + name + ", tipus=" + type + ", alçada=" + height+", preu=" + price + "]";
	}
}
