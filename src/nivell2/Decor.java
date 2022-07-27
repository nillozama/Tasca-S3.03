package nivell2;

public class Decor extends Product {

	String material;
	
	public Decor(int id, String name, String type, float price, String material) {
		
		super(id, name, type, price);
		this.material=material;
	}
	
	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}
	
	@Override
	public String toString() {
		return "[nom=" + name + ", tipus=" + type + ", material=" + material + ", preu=" + price + "]";
	}
}
