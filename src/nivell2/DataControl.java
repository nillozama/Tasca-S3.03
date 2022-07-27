package nivell2;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

public class DataControl {
	
	private Connection connect;
	
	public DataControl() {
		
		connect=DatabaseConnection.getConnect();
		
		
	}
	
	public int createTicket (int shop_idshop) {
		
		String sql;
		int m=0;
		Statement s;
		int result=0;
	      
	    sql = "insert into ticket(date, shop_idshop) "
	            		+ "values(now(),"+shop_idshop+")";
	    try {
			
			s=connect.createStatement();
			m = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		    ResultSet rs= s.getGeneratedKeys();
		    if (rs.next()) {
		        result= rs.getInt(1);
		     }
			if (m == 1) {
		    	
		        System.out.println("S'ha realitzat correctament la inserció : "+sql);
		    }else {
		    	
		        System.out.println("Error en la inserció.");
	        	connect.close();
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return result;
	}
	
	public int insertProduct(String name, int idtype, float price, String characteristic, int shop_idshop) {

		String sql;
		int m=0;
		Statement s;
		int result=0;
	            
	    sql = "insert into product(name, type_idtype, price, characteristic, shop_idshop) "
	            		+ "values('"+name+"',"+idtype+","+price+",'"+characteristic+"',"+shop_idshop+")";
	    try {
	    	s=connect.createStatement();
			m = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		    ResultSet rs= s.getGeneratedKeys();
		    if (rs.next()) {
		        result= rs.getInt(1);
		     }
		    
			if (m == 1) {
		    	
		        System.out.println("S'ha realitzat correctament la inserció : "+sql);
		    }else {
		    	
		        System.out.println("Error en la inserció.");
	        	connect.close();
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return result;
	}
	
	/*public int insertShop(String name){

		String sql;
		int m=0;
		Statement s;
		int result=0;
	            
	    sql = "insert into product(name) value('"+name+"')";
    
	    try {
	    	s=connect.createStatement();
			m = s.executeUpdate(sql);
			ResultSet rs= s.getGeneratedKeys();
			while (rs.next()) {
				result= rs.getInt(1);
			}

			if (m == 1) {
		    	
		        System.out.println("S'ha realitzat correctamente la inserció : "+sql);
		    }else {
		    	
		        System.out.println("Error en la inserció.");
	        	connect.close();
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return result;
	}*/
	
	public List<Product> getStock(int floristNum) {
		
        List<Product> stock=new ArrayList<Product>();
		ResultSet rs;
		String sql;
		int id;
		String name;
		int type;
		float price;
		String characteristic;
     
	    sql = "select * from product where shop_idshop="+floristNum;
	    try {
			rs = connect.createStatement().executeQuery(sql);
		    while (rs.next()) {
		        id=rs.getInt(1);
		        name=rs.getString(2);
		        type=rs.getInt(3);
		        price=rs.getFloat(4);
		        characteristic=rs.getString(5);
		        
		        if(type==1) {
		        	
		        	stock.add(new Tree(id, name, "arbre", price, characteristic));
		        }else if (type==2){
		        	
		        	stock.add(new Flower(id, name, "flor", price, characteristic));
		        }
		        else if(type==3){
		        	
		        	stock.add(new Decor(id, name, "decoracio", price, characteristic));
		        }
		     }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return stock;
	}
	
	public List<Ticket> getTicket(int floristNum){
		
        List<Ticket> tickets=new ArrayList<Ticket>();
    	List<Product> productSalesList;
    	Ticket ticket;
        ResultSet rs;
		String sql;
		int id;
		//Date date;
		//float totalPrice;
     
	    sql = "select idticket from ticket where shop_idshop="+floristNum;
	    try {
			rs = connect.createStatement().executeQuery(sql);
		    while (rs.next()) {
		        id=rs.getInt(1);
		        //date=rs.getDate(2);
		        //totalPrice=rs.getFloat(3);
		        
		        ticket=new Ticket(id);
		        tickets.add(ticket);
		        productSalesList=getProductsFromTicket(id);
		        for(Product product:productSalesList) {
		        	
		        	ticket.addProduct(product);
		        }
		     }

		} catch (SQLException e) {
			
			System.out.println("La consulta ha fallat amb el següent missatge:");
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return tickets;
		
	}
	
	public List<Product> getProductsFromTicket(int idTicket) {
		
    	List<Product> salesList=new ArrayList<Product>();
		ResultSet rs;
		String sql;
		int id;
		String name;
		int type;
		float price;
		String characteristic;
     
	    sql = "select idproduct, name, type_idtype, price, characteristic from product where ticket_idticket="+idTicket;
	    try {
			rs = connect.createStatement().executeQuery(sql);
		    while (rs.next()) {
		        id=rs.getInt(1);
		        name=rs.getString(2);
		        type=rs.getInt(3);
		        price=rs.getFloat(4);
		        characteristic=rs.getString(5);
		        
		        if(type==1) {
		        	
		        	salesList.add(new Tree(id, name, "arbre", price, characteristic));
		        }else if (type==2){
		        	
		        	salesList.add(new Flower(id, name, "flor", price, characteristic));
		        }
		        else if(type==3){
		        	
		        	salesList.add(new Decor(id, name, "decoracio", price, characteristic));
		        }
		     }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return salesList;
	}
	
	public int getShop(String name) {
		
		ResultSet rs;
		String sql;
		int result=0;
     
	    sql = "select idshop from shop where name='"+name+"'";
	    try {
			rs = connect.createStatement().executeQuery(sql);
		    if (rs.next()) {
		        result= rs.getInt(1);
		     }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return result;
	}
	
	public void delateFromStock(int itemToDelate) {
		
		String sql;
		int m=0;
	            
	    sql = "delete from product where idproduct="+itemToDelate;
    
	    try {

			m = connect.createStatement().executeUpdate(sql);
			if (m == 1) {
		    	
		        System.out.println("S'ha eliminat el producte amb ID : "+itemToDelate);
		    }else {
		    	
		        System.out.println("Error en la eliminació.");
	        	connect.close();
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateFromProduct(int idProduct, int idTicket) {
		
		String sql;
		int m=0;
	            
	    sql = "update product set shop_idshop=null, ticket_idticket="+idTicket+" where idproduct="+idProduct;
	    try {

			m = connect.createStatement().executeUpdate(sql);

		    
			if (m == 1) {
		    	
		        System.out.println("S'ha realitzat correctament la inserció : "+sql);
		    }else {
		    	
		        System.out.println("Error en la inserció.");
	        	connect.close();
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
