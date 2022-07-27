package nivell2;

public class Flower extends Product {

	//private static int stock;
	private String color;
	//private int id;
	
	public Flower(int id, String name, String type, float price, String color) {
		
		super(id, name, type, price);
		this.color=color;
		//id=Product.getId();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "[nom=" + name + ", tipus=" + type + ", color=" + color + ", preu=" + price + "]";
	}
}
