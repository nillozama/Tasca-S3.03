package nivell2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

	private static List<Product> stock = new ArrayList<Product>();
	private static List<Ticket> ticketList = new ArrayList<Ticket>();
	private static DataControl dc = new DataControl();
	private static String floristName;
	private static int floristNum;

	public static void main(String[] args) {

		boolean sortirMenu = false;

		floristName = requireString("Amb quina floristeria vols treballar?").toLowerCase();
		floristNum = dc.getShop(floristName);

		if (floristNum == 0) {

			/*
			 * if(requireString("No hi ha cap floristeria a la base de dades amb el nom "
			 * +floristName+". " +
			 * "Vols crear-la? \n1 per crear-la, qualsevol altra lletra per sortir.").equals
			 * ("1")){ floristNum=dc.insertShop(floristName); }else {
			 * 
			 * System.out.println("Gracies per utilitzar l'aplicació. Adeu!!");
			 * sortirMenu=true;
			 * 
			 * }
			 */

			System.out.println("No hi ha cap floristeria a la base de dades amb el nom " + floristName + ". ");
			System.out.println("Gracies per utilitzar l'aplicació. Adeu!!");
			sortirMenu = true;

		} else {

			stock = dc.getStock(floristNum);
			ticketList = dc.getTicket(floristNum);
		}

		while (!sortirMenu) {
			sortirMenu = showMenu(sortirMenu);

		}
	}

	public static boolean showMenu(boolean sortirMenu) {

		String indexSwitch;
		String name;
		Product product;
		float price;
		int itemToDelate;
		float currentStockValue;
		float totalSalesValue;

		System.out.println(
				"\n               __/)  \n" + "            .-(__(=:       " + "FLORISTERIA " + floristName.toUpperCase()
						+ "\n" + "            |    \\)\n" + "     (\\__   |              1 - AFEGIR PRODUCTE\n"
						+ "     :=)__)-|  __/)        2 - RETIRAR PRODUCTE\n"
						+ "      (/    |-(__(=:       3 - IMPRIMIR STOCK DE TOTS ELS PRODUCTES\n"
						+ "    ______  |  _ \\)        4 - IMPRIMIR STOCK AMB QUANTITATS\n"
						+ "   /      \\ | / \\          5 - MOSTRAR COMPRES ANTIGUES\n"
						+ "        ___\\|/___\\         6 - CREAR TIQUET DE VENDA\n"
						+ "       [         ]\\        7 - MOSTRAR VALOR STOCK \n"
						+ "        \\       /  \\       8 - MOSTRAR VALOR TOTES LES VENDES\n"
						+ "         \\     /           0 - SORTIR PROGRAMA" + "\n          \\___/            "
						+ "--------------------------------------------------------------");
		indexSwitch = requireString("\r\nQuina opció del menú vols triar?");

		switch (indexSwitch) {

		case "1":

			name = requireString("Introdueix el nom del producte.");
			price = requireFloatNumber("Introdueix el preu del producte.");
			product = addNewProductMenu(name, price);

			if (product != null) {

				stock.add(product);
				System.out.println("S'ha afegit el producte " + name + " a l'stock.");
			} else {

				System.out.println("No s'ha creat cap producte.");
			}

			break;

		case "2":

			System.out.println("Quin producte vols retirà?");
			printStock();
			itemToDelate = requireIntNumber("Introdueix el número del producte a retirar.") - 1;
			removeItemFromStock(itemToDelate);
			System.out.println("El producta s'ha eliminat!");

			break;

		case "3":

			printStock();

			break;

		case "4":
			printStockByType();
			break;

		case "5":

			showSales();

			break;
		case "6":

			createTicket();

			break;

		case "7":

			currentStockValue = (float) stock.stream().mapToDouble(Product::getPrice).sum();
			System.out.println("El valor de tot l'stock es de " + currentStockValue + " euros.\n");

			break;

		case "8":

			totalSalesValue = (float) ticketList.stream().mapToDouble(t -> t.getTicketPrice()).sum();
			System.out.println("El valor de totes les vendes es de " + totalSalesValue + " euros.\n");

			break;

		case "0":

			System.out.println("Gracies per utilitzar l'aplicació. Adeu!!");
			DatabaseConnection.closeConnect();
			sortirMenu = true;
			break;

		default:

			System.out.println("Agafa un opció del menú. ¡El número ha de ser entre 0 i 8!");
		}

		return sortirMenu;
	}

	public static Product addNewProductMenu(String name, float price) {

		String type;
		String characteristic;
		Product product = null;
		int id;
		String index = requireString("\nIntrodueix el tipus de producte que vols afegir.\n "
				+ " 1-Arbre.\n 2-Flor.\n 3-Decoració.\n 0-Sortir opció afegir producte.");

		switch (index) {

		case "1":

			type = "arbre";
			characteristic = requireString("Introdueix l'alçada de l'arbre.");
			id = dc.insertProduct(name, 1, price, characteristic, floristNum);

			product = new Tree(id, name, type, price, characteristic);

			break;

		case "2":

			type = "flor";
			characteristic = requireString("Introdueix el color de la flor.");
			id = dc.insertProduct(name, 2, price, characteristic, floristNum);
			product = new Flower(id, name, type, price, characteristic);

			break;

		case "3":

			type = "decoracio";
			characteristic = requireString("Introdueix el material.");
			id = dc.insertProduct(name, 3, price, characteristic, floristNum);
			product = new Decor(id, name, type, price, characteristic);

			break;

		case "0":

			break;

		default:

			System.out.println("Agafa un opció del menú. ¡El número ha de ser entre 0 i 3!");
			addNewProductMenu(name, price);
		}

		return product;
	}

	public static void printStock() {

		int i = 0;

		for (Product product : stock) {

			i++;
			System.out.println(i + "- " + product);
		}
		if (stock.size() == 0) {
			System.out.println("No hi ha productes. La botiga està buida!");
		}
	}

	public static void printStockByType() {

		int treeStock = (int) stock.stream().filter(p -> p.getType().equalsIgnoreCase("arbre")).count();

		int flowerStock = (int) stock.stream().filter(p -> p.getType().equalsIgnoreCase("flor")).count();

		int decorStock = (int) stock.stream().filter(p -> p.getType().equalsIgnoreCase("decoracio")).count();

		System.out.println("Stock d'arbres: " + treeStock + " unitats.");
		System.out.println("Stock de flors: " + flowerStock + " unitats.");
		System.out.println("Stock de decoració: " + decorStock + " unitats.");
	}

	public static void removeItemFromStock(int itemToDelate) {

		int id = stock.get(itemToDelate).getId();
		dc.delateFromStock(id);
		stock.remove(itemToDelate);
	}

	public static void removeItemFromStockbyId(Product product) {

		stock.remove(product);
	}

	public static void showSales() {

		for (Ticket ticket : ticketList) {

			System.out.println("\n" + ticket);
			ticket.showProducts();
		}
	}

	public static void createTicket() {

		int ticketId;
		Product productToSale;
		List<Integer> productId = new ArrayList<Integer>();

		if (stock.size() > 0) {

			Ticket ticket = new Ticket(ticketList.size() + 1);
			int valueSelected;

			do {
				printStock();
				System.out.println("\nIntrodueix ID producte per afegir al carro.");
				valueSelected = requireIntNumber("Escriu ID [ 0 - FINALITZAR]: ");

				if (0 < valueSelected && valueSelected <= stock.size()) {

					productToSale = stock.get(valueSelected - 1);
					ticket.addProduct(productToSale);
					removeItemFromStockbyId(productToSale);
					productId.add(productToSale.getId());

				} 
				if (valueSelected == 0 || stock.size() == 0) {

					if (stock.size() == 0) {
						System.out.println("No queden productes per comprar.");
					}
					if (ticket.getSales().size() > 0) {
						System.out.println("Acabant la compra...Registrant el ticket...");
						ticketList.add(ticket);
						ticketId = dc.createTicket(floristNum);
						for (int i : productId) {

							dc.updateFromProduct(i, ticketId);
							// eliminar valor idshop del producto y añadir idticket al producto (tabla
							// producto)
							// update de la tabla ticket con la suma de los precios.
						}

					} else {
						System.out.println("Sortint. No se ha fet cap compra...");
					}
				} else {

					System.out.println("S'ha introduït un ID incorrecte");
				}

			} while (valueSelected != 0 && stock.size() > 0);

			// Print the ticket and updating values
			ticket.showProducts();

		} else
			System.out.println("\nNo n'hi ha productes per vendre. " + "Botiga buida!! \n\n".toUpperCase());
	}

	// Returns value of stock in terms of currency
	public static float currentStockValue() {

		return (float) stock.stream().mapToDouble(Product::getPrice).sum();
	}

	public static float totalSalesValue() {
		return (float) ticketList.stream().mapToDouble(t -> t.getTicketPrice()).sum();

	}

	public static String requireString(String message) {

		Scanner sc = new Scanner(System.in);
		String string;

		System.out.println(message);
		string = sc.nextLine();

		return string;
	}

	public static int requireIntNumber(String missatge) {
		int input=0;
		boolean inputCorrecte = false;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println(missatge);
			try {
				input = sc.nextInt();
				inputCorrecte = true;
			} catch (InputMismatchException e) {
				System.out.println("No s'ha introduït el tipus de dades esperat");
				sc.next();
			}
		} while (!inputCorrecte);
		
		return input;
	}

	public static float requireFloatNumber(String missatge) {
		float input=0;
		boolean inputCorrecte = false;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println(missatge);
			try {
				input=sc.nextFloat();
				inputCorrecte = true;
			} catch (InputMismatchException e) {
				System.out.println("No s'ha introduït el tipus de dades esperat");
				sc.next();
			}
		} while (!inputCorrecte);
		
		return input;
	}
}
