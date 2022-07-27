package nivell2;

import java.sql.Date;

public class TicketTableModel {
	
	private int idticket;
	private Date date;
	private float totalPrice;
	
	
	public int getIdticket() {
		return idticket;
	}
	public void setIdticket(int idticket) {
		this.idticket = idticket;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
