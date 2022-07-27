package nivell2;

public class Product {
	
	protected String name;
	protected String type;
	protected float price;
	protected int id;
	
	public Product (int id, String name, String type, float price) {
		
		this.name=name;
		this.type=type;
		this.price=price;
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
}
