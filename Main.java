// Name: Rajat Prakash
// AP-LAB-II

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.print.attribute.HashAttributeSet;

// ADT Company is created here with corresponding fields : - 
class Company
{
	private double company_account_balance = 0;
	
	public double getCompany_account_balance() {
		return company_account_balance;
	}

	public void setCompany_account_balance(double company_account_balance) {
		this.company_account_balance = company_account_balance;
	}

}

interface Company_Demands
{
	Scanner sc_m = new Scanner(System.in);
	public void print_item_details();
}
// ADT item is created here with corresponding fields : - 

class item_merchant implements Company_Demands
{
	private int item_code;
	private String item_name;
	private double item_price;
	private int item_quantity;
	private String item_category;
	private String item_offer;
	
	public item_merchant(int item_code, String item_name, double item_price, int item_quantity, String item_category) {
		this.item_code = item_code;
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_quantity = item_quantity;
		this.item_category = item_category;
		this.item_offer = "None";
	}

	public void setItem_code(int item_code) {
		this.item_code = item_code;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getItem_price() {
		return item_price;
	}
	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}
	public int getItem_quantity() {
		return item_quantity;
	}
	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getItem_offer() {
		return item_offer;
	}
	public void setItem_offer(String item_offer) {
		this.item_offer = item_offer;
	}
	public int getItem_code() {
		return item_code;
	}
	// PolyMorphism concept under interface is used :-
	@Override
	public void print_item_details()
	{
		System.out.println(item_code + " " + item_name + " " + item_price + " " + item_quantity + " " + item_offer + " " + item_category );
	}
}

// ADT Merchant is created here with corresponding fields : - 

class Merchant implements Company_Demands
{
	public String getName() {
		return name;
	}
	public int getUser_id() {
		return user_id;
	}
	public String getAddress() {
		return address;
	}
	public double getContribution_to_company() {
		return contribution_to_company;
	}
	
	public void setContribution_to_company(double contribution_to_company) {
		this.contribution_to_company = contribution_to_company;
	}
	public ArrayList<item_merchant> getItem_merchant_all() {
		return item_merchant_all;
	}
	
	private String name;
	private int user_id;
	private String address;
	private double contribution_to_company;
	private double main_account;
	private int extra_slots = 0;
	private ArrayList<item_merchant> item_merchant_all = new ArrayList<item_merchant>() ;
	public Merchant(String name, int user_id, String address)
	{
		this.address = address;
		this.name = name;
		this.user_id = user_id;
	}
	
//Merchant can add his particular item through this : - 
	public void add_item()
	{
		if ( item_merchant_all.size() >= getContribution_to_company() * 0.01 + 10 )
		{
			System.out.println("Your have no more slot to add item, Please sell more to get more slots.");
		}else{
			System.out.println("Enter item details");
			System.out.println("item name:");
			String item_name = sc_m.next();
			System.out.println("item price:");
			double item_price = sc_m.nextDouble();
			System.out.println("item quantity:");
			int item_quantity = sc_m.nextInt();
			System.out.println("item category:");
			String item_category = sc_m.next();
			if ( !Main.all_category.containsValue(item_category))
			{
				Main.all_category.put(Main.count_category++,item_category);
			}
			item_merchant new_item = new item_merchant(Main.count_item++, item_name, item_price, item_quantity, item_category);
			new_item.print_item_details();
			item_merchant_all.add(new_item);
		}
	}
	
//Merchant can edit his particular item through this : -
	public void edit_item()
	{
		System.out.println("choose item by code");
		for ( item_merchant k : item_merchant_all)
		{
			k.print_item_details();
		}
		int real_time_item = sc_m.nextInt();
		for ( item_merchant k : item_merchant_all)
		{
			if ( k.getItem_code() == real_time_item)
			{
				System.out.println("Enter edit details");
				System.out.println("item price:");
				k.setItem_price(sc_m.nextDouble());
				System.out.println("item quantity:");
				k.setItem_quantity(sc_m.nextInt());
				k.print_item_details();
				break;
			}
		}
	}
//Merchant can add offer on his particular item through this : -
	public void add_offer()
	{
		System.out.println("choose item by code");
		for ( item_merchant k : item_merchant_all)
		{
			k.print_item_details();
		}
		int item_code_for_offer = sc_m.nextInt();
		System.out.println("choose offer\n1) buy one get one \n2) 25% off");
		int offer_chosen = sc_m.nextInt();
		if ( offer_chosen == 1)
		{
			for ( item_merchant k : item_merchant_all )
			{
				if ( k.getItem_code() == item_code_for_offer)
				{
					k.setItem_offer("buy one get one");
					k.print_item_details();
				}
			}
		}
		if ( offer_chosen == 2)
		{
			for ( item_merchant k : item_merchant_all )
			{
				if ( k.getItem_code() == item_code_for_offer)
				{
					k.setItem_offer("25% off");
					k.print_item_details();
				}
			}
		}
		
	}
//Merchant can search item details by category through this : -
	public void search_by_category(HashMap<Integer,String> all_category,Merchant[] all_merchant)
	{
		System.out.println("choose a category");
		Iterator iterator = all_category.entrySet().iterator();
		while( iterator.hasNext())
		{
			Map.Entry mapElement = (Map.Entry)iterator.next();
			System.out.println(mapElement.getKey() + ") " + mapElement.getValue());
		}
		int realtime_category_key = sc_m.nextInt();
		String realtime_category = all_category.get(realtime_category_key);
		for ( Merchant k : all_merchant )
		{
			for ( item_merchant kk : k.getItem_merchant_all() )
			{
				if ( kk.getItem_category().equals(realtime_category))
				{
					kk.print_item_details();
				}
			}
		}
	}
	public double getMain_account() {
		return main_account;
	}
	public void setMain_account(double main_account) {
		this.main_account = main_account;
	}
	// PolyMorphism concept under interface is used :-
	@Override
	public void print_item_details()
	{
		System.out.println( "Name: " + getName() + "\n" + "Address: " +  getAddress() + "\n" + "Contribution to Company: " + getContribution_to_company());
		System.out.println();
	}
}

// ADT Cart is created here with corresponding fields  which help in function of Add_to_cart: - 
class Cart
{
	public Queue<item_merchant> item_queue = new LinkedList<item_merchant>();
	public Queue<Merchant> merchant_queue = new LinkedList<Merchant>();
	public Queue<Integer> quantity_required = new LinkedList<Integer>();
}

//ADT Customer is created here with corresponding fields : - 

class Customer implements Company_Demands
{
	private int user_id;
	private String name;
	private String address;
	private double main_account = 100.00;
	private double reward_account = 0;
	private int no_of_purchase = 0;
	public ArrayList<String> all_rewards = new ArrayList<>();
	public ArrayList<String> all_transactions = new ArrayList<>();
	public Cart cart = new Cart();
	public Customer(String name, int user_id, String address)
	{
		this.address = address;
		this.name = name;
		this.user_id = user_id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNo_of_purchase() {
		return no_of_purchase;
	}
	public void setNo_of_purchase(int no_of_purchase) {
		this.no_of_purchase = no_of_purchase;
	}

	public double getMain_account() {
		return main_account;
	}
	public void setMain_account(double main_account) {
		this.main_account = main_account;
	}
	public double getReward_account() {
		return reward_account;
	}
	public void setReward_account(double reward_account) {
		this.reward_account = reward_account;
	}
	public void search_item()
	{
		System.out.println("Choose a category");
		Iterator iterator = Main.all_category.entrySet().iterator();
		while( iterator.hasNext())
		{
			Map.Entry mapElement = (Map.Entry)iterator.next();
			System.out.println(mapElement.getKey() + ") " + mapElement.getValue());
		}
	}

	public void add_to_cart(item_merchant item_merchant ,int item_quanity, Merchant merchant)
	{
		cart.item_queue.add(item_merchant);
		cart.merchant_queue.add(merchant);
		cart.quantity_required.add(item_quanity);
	}
	// PolyMorphism concept under interface is used :-
	
	@Override
	public void print_item_details()
	{
		System.out.println( "Name: " + getName() + "\n" + "Address: " +  getAddress() + "\n" + "Number of orders placed: " + getNo_of_purchase());
		System.out.println();
	}
}


public class Main {
	public static int count_item = 1;
	public static int count_category = 1;
	public static HashMap<Integer,String> all_category = new HashMap<>();
	public static void main(String[] args)
	{
		Company company = new Company();
		Scanner sc = new Scanner(System.in);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Created 5 Customer and 5 Merchant as default user : -
		Merchant jack;Merchant john;Merchant james;Merchant jeff;Merchant joseph;
		Customer ali;Customer nobby;Customer bruno;Customer borat;Customer aladeen;
			jack = new Merchant("jack",1,"jack_address");
			john = new Merchant("john",2, "john_address");
			james = new Merchant("james",3, "james_address");
			jeff = new Merchant("jeff",4, "jeff_address");
			joseph = new Merchant("joseph",5, "joseph_address");
			
			ali = new Customer("ali", 1, "ali_address");
			nobby = new Customer("nobby", 2, "nobby_address");
			bruno = new Customer("bruno", 3, "bruno_address");
			borat = new Customer("borat", 4, "borat_address");
			aladeen = new Customer("aladeen", 5, "aladeen_address");
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		boolean exit = false;
		// An array to maintain all Merchants and Customers
		Merchant[] all_merchant = {jack, john, james, jeff, joseph};
		Customer[] all_customer = {ali, nobby, bruno, borat, aladeen};
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Opening of e-commerce terminal of Mercury Inc.(Can Use facility until EXIT)
		while(!exit)
		{
			System.out.println("Welcome to Mercury\n1) Enter as Merchant\n2) Enter as Customer\n3) See user details\n4) Company account balance\n5) Exit");
			int input_query = sc.nextInt();
			switch(input_query)
			{
			case 1:
				boolean exit_merchant = false;
				// Login as Merchant. ( Can Use facility until EXIT )
				System.out.println("choose merchant");
				for ( int i = 0; i < 5; i++)
				{
					System.out.println(i + 1 + " " + all_merchant[i].getName());
				}
				int merchant_id = sc.nextInt();
				Merchant realtime_merchant = all_merchant[merchant_id - 1];
				
	// All methods for Merchant has been called from here repetitively until EXIT
				while(!exit_merchant)
				{
					
					System.out.println("Welcome " + realtime_merchant.getName());
					System.out.println("Merchant Menu");
					System.out.println("1) Add item\n2) Edit item\n3) Search by category\n4) Add offer\n5) Rewards won\n6) Exit");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					int input_merchant_query = sc.nextInt();
					switch(input_merchant_query)
					{
					case 1:
						realtime_merchant.add_item();  // Add item for sale
						break;
					case 2:
						realtime_merchant.edit_item(); // Edit item for sale
						break;
					case 3:
						realtime_merchant.search_by_category(all_category,all_merchant);  // Search all items by category in market
						break;
					case 4:
						realtime_merchant.add_offer(); // Add offer to selected item for sale
						break;
					case 5:
						System.out.println(realtime_merchant.getContribution_to_company() % 100);  // Print contribution to company
						break;
					case 6:
						exit_merchant = true;   // Log Out of Merchant ID
						break;
					}
				}
				break;
			case 2:
				boolean exit_customer = false;
				// Login as Customer. ( Can Use facility until EXIT )
				System.out.println("choose customer");
				for ( int i = 0; i < 5; i++)
				{
					System.out.println(i + 1 + " " + all_customer[i].getName());
				}
				int customer_id = sc.nextInt();
				Customer realtime_customer = all_customer[customer_id - 1];
				
				// All methods for customer is called for for Buying and Transactions
				
				while(!exit_customer)
				{
					System.out.println("Welcome " + realtime_customer.getName());
					System.out.println("Customer Menu");
					System.out.println("1) Search item\n2) checkout cart\n3) Reward won\n4) print latest orders\n5) Exit");
////////////////////////////////////////////////////////////////////////////////
					int customer_query = sc.nextInt();
					switch(customer_query)
					{
//					Searching: Search using categories. Display all the categories so that the user can
//					choose one. The customer after searching by the category gets a list view of the items
//					under that category. Then the customer can choose the item by its code and quantity.
//					The customer has to choose a method of transaction: 1. a choice of buying an item, 2.
//					adding it to cart, 3. exiting the current option. Print success messages or appropriate
//					error messages for the first 2 options.
					case 1:
						boolean shopping = true;
						while(shopping)
						{
							realtime_customer.search_item();
							int realtime_category_key = sc.nextInt();
							System.out.println("choose item by code");
							String realtime_category = all_category.get(realtime_category_key);
							for ( Merchant k : all_merchant )
							{
								for ( item_merchant kk : k.getItem_merchant_all() )
								{
									if ( kk.getItem_category().equals(realtime_category))
									{
										kk.print_item_details();
									}
								}
							}
							System.out.println("Enter item code");
							int interested_item_code = sc.nextInt();
							System.out.println("Enter item quantity");
							int interested_item_quantity = sc.nextInt();
							System.out.println("1) Buy item\n2) Add item to cart\n3) Exit");
							int buying_query = sc.nextInt();
							if (buying_query == 1)
							{
								boolean item_bought = false;
								for ( Merchant k : all_merchant )
								{
									for ( item_merchant kk : k.getItem_merchant_all() )
									{
										if ( kk.getItem_code() == interested_item_code)
										{
											double amount_to_merchant = kk.getItem_price() * interested_item_quantity;
											double amount_to_merchant_25_off = amount_to_merchant * (75.0/100.0); 
											
											double transaction_amount = amount_to_merchant * ( 100.5/100.0);
											double transaction_amount_25_off = amount_to_merchant_25_off * ( 100.5/100.0);
											
											double amount_to_merchant_bogo = 0;
											double transaction_amount_bogo = 0;
											if ( kk.getItem_offer() == "buy one get one" && interested_item_quantity % 2 == 0 )
											{
												amount_to_merchant_bogo = kk.getItem_price() * interested_item_quantity * (1.0/2.0);
												transaction_amount_bogo = amount_to_merchant_bogo * ( 100.5/100.0);
											}else if ( kk.getItem_offer() == "buy one get one" && interested_item_quantity % 2 != 0 )
											{
												amount_to_merchant_bogo = kk.getItem_price() * (interested_item_quantity * (1/2)) + kk.getItem_price() ;
												transaction_amount_bogo = amount_to_merchant_bogo * ( 100.5/100.0);
											}
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
											if ( kk.getItem_offer() == "None" )
											{
												if ( realtime_customer.getMain_account() + realtime_customer.getReward_account() >= transaction_amount && kk.getItem_quantity() >= interested_item_quantity)
												{
													if ( realtime_customer.getMain_account()  >= transaction_amount )
													{
														realtime_customer.setMain_account(realtime_customer.getMain_account() - transaction_amount);
														k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant * ( 0.5/100.0));
														company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant * ( 1.0/100.0));
														realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
														item_bought = true;
														realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant + " from Merchant " + k.getName());
														System.out.println("Item Successfully bought");
													}else{
														realtime_customer.setReward_account(realtime_customer.getReward_account() - transaction_amount + realtime_customer.getMain_account());
														realtime_customer.setMain_account(0);
														k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant * ( 0.5/100.0));
														company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant * ( 1.0/100.0));
														realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
														item_bought = true;
														realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant + " from Merchant " + k.getName());
														System.out.println("Item Successfully bought");
													}
												}else{
													System.out.println("Item can't be bought.");
												}
												if ( item_bought)
												{
													kk.setItem_quantity(kk.getItem_quantity() - interested_item_quantity);
												}
												break;
											}
							/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				/////
											if ( kk.getItem_offer() == "25% off")
											{
												if ( realtime_customer.getMain_account() + realtime_customer.getReward_account() >= transaction_amount_25_off && kk.getItem_quantity() >= interested_item_quantity)
												{
													if ( realtime_customer.getMain_account()  >= transaction_amount_25_off )
													{
														realtime_customer.setMain_account(realtime_customer.getMain_account() - transaction_amount_25_off);
														k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant_25_off * ( 0.5/100.0));
														company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant_25_off * ( 1.0/100.0));
														realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
														item_bought = true;
														realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant_25_off + " from Merchant " + k.getName());
														System.out.println("Item Successfully bought");
													}else{
														realtime_customer.setReward_account(realtime_customer.getReward_account() - transaction_amount_25_off + realtime_customer.getMain_account());
														realtime_customer.setMain_account(0);
														k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant_25_off * ( 0.5/100.0));
														company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant_25_off * ( 1.0/100.0));
														realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
														item_bought = true;
														realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant_25_off + " from Merchant " + k.getName());
														System.out.println("Item Successfully bought");
													}
												}else{
													System.out.println("Item can't be bought.");
												}
												if ( item_bought)
												{
													kk.setItem_quantity(kk.getItem_quantity() - interested_item_quantity);
												}
												break;
											}
						/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
											if ( kk.getItem_offer() == "buy one get one")
											{
												if ( realtime_customer.getMain_account() + realtime_customer.getReward_account() >= transaction_amount_bogo && kk.getItem_quantity() >= interested_item_quantity)
												{
													if ( realtime_customer.getMain_account()  >= transaction_amount_bogo )
													{
														realtime_customer.setMain_account(realtime_customer.getMain_account() - transaction_amount_bogo);
														k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant_bogo * ( 0.5/100.0));
														company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant_bogo * ( 1.0/100.0));
														realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
														item_bought = true;
														realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant_bogo + " from Merchant " + k.getName());
														System.out.println("Item Successfully bought");
													}else{
														realtime_customer.setReward_account(realtime_customer.getReward_account() - transaction_amount_bogo + realtime_customer.getMain_account());
														realtime_customer.setMain_account(0);
														k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant_bogo * ( 0.5/100.0));
														company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant_bogo * ( 1.0/100.0));
														realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
														item_bought = true;
														realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant_bogo + " from Merchant " + k.getName());
														System.out.println("Item Successfully bought");
													}
												}else{
													System.out.println("Item can't be bought.");
												}
												if ( item_bought)
												{
													kk.setItem_quantity(kk.getItem_quantity() - interested_item_quantity);
												}
												break;
											}
							
						//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
											
										}
									}
								}
								if ( realtime_customer.getNo_of_purchase() % 5 == 0 && item_bought )
								{
									realtime_customer.all_rewards.add("Rupees 10 on Buying " + realtime_category + "from Merchant");
									realtime_customer.setReward_account( realtime_customer.getReward_account() + 10.0 );
									
								}
								if ( item_bought )
								{
									shopping = false;
								}
							}
							if ( buying_query == 2)
							{
								for ( Merchant k : all_merchant )
								{
									for ( item_merchant kk : k.getItem_merchant_all() )
									{
										if ( kk.getItem_code() == interested_item_code)
										{
											realtime_customer.add_to_cart(kk,interested_item_quantity, k);
											System.out.println("Item successfully added into cart");
											shopping = false;
										}
									}
								}
							}
							if ( buying_query == 3)
							{
								shopping = false;
							}
						}
						break;
	///////////////////////////////////////////////////////////////////				
//						2) Checkout option: Items have to be checked out in a FIFO manner. If you cannot check
//						out a particular item ( keeping in mind the quantity, account balance, offer ) then display
//						an error message (e.g. out of stock, out of money) and exit current option, else display
//						success messages and exit current option. Also, all the items before that particular item
//						( in case of error ) have to be checked out.
						//////////////////////////////////////////////////////////////////					
						
						
					case 2:
						boolean item_can_be_bought = true; 
						while ( item_can_be_bought && !realtime_customer.cart.item_queue.isEmpty())
						{
							boolean item_bought = false;
							item_merchant kk = realtime_customer.cart.item_queue.peek();
							Merchant k = realtime_customer.cart.merchant_queue.peek();
							int interested_item_quantity = realtime_customer.cart.quantity_required.peek();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
							double amount_to_merchant = kk.getItem_price() * interested_item_quantity;
							double amount_to_merchant_25_off = amount_to_merchant * (75.0/100.0); 
							
							double transaction_amount = amount_to_merchant * ( 100.5/100.0);
							double transaction_amount_25_off = amount_to_merchant_25_off * ( 100.5/100.0);
							
							double amount_to_merchant_bogo = 0;
							double transaction_amount_bogo = 0;
							if ( kk.getItem_offer() == "buy one get one" && interested_item_quantity % 2 == 0 )
							{
								amount_to_merchant_bogo = kk.getItem_price() * interested_item_quantity * (1.0/2.0);
								transaction_amount_bogo = amount_to_merchant_bogo * ( 100.5/100.0);
							}else if ( kk.getItem_offer() == "buy one get one" && interested_item_quantity % 2 != 0 )
							{
								amount_to_merchant_bogo = kk.getItem_price() * (interested_item_quantity * (1/2)) + kk.getItem_price() ;
								transaction_amount_bogo = amount_to_merchant_bogo * ( 100.5/100.0);
							}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							if ( kk.getItem_offer() == "None" )
							{
								if ( realtime_customer.getMain_account() + realtime_customer.getReward_account() >= transaction_amount && kk.getItem_quantity() >= interested_item_quantity)
								{
									if ( realtime_customer.getMain_account()  >= transaction_amount )
									{
										realtime_customer.setMain_account(realtime_customer.getMain_account() - transaction_amount);
										k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant * ( 0.5/100.0));
										company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant * ( 1.0/100.0));
										realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
										item_bought = true;
										realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant + " from Merchant " + k.getName());
										//System.out.println("Item Successfully bought");
									}else{
										realtime_customer.setReward_account(realtime_customer.getReward_account() - transaction_amount + realtime_customer.getMain_account());
										realtime_customer.setMain_account(0);
										k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant * ( 0.5/100.0));
										company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant * ( 1.0/100.0));
										realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
										item_bought = true;
										realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant + " from Merchant " + k.getName());
										//System.out.println("Item Successfully bought");
									}
								}else{
									item_can_be_bought = false;
									System.out.println("Item can't be bought.");
								}
								if ( item_bought)
								{
									kk.setItem_quantity(kk.getItem_quantity() - interested_item_quantity);
								}else{
									System.out.println("Item can't be bought.");
								}
								break;
							}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				/////
							if ( kk.getItem_offer() == "25% off")
							{
								if ( realtime_customer.getMain_account() + realtime_customer.getReward_account() >= transaction_amount_25_off && kk.getItem_quantity() >= interested_item_quantity)
								{
									if ( realtime_customer.getMain_account()  >= transaction_amount_25_off )
									{
										realtime_customer.setMain_account(realtime_customer.getMain_account() - transaction_amount_25_off);
										k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant_25_off * ( 0.5/100.0));
										company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant_25_off * ( 1.0/100.0));
										realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
										item_bought = true;
										realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant_25_off + " from Merchant " + k.getName());
										//System.out.println("Item Successfully bought");
									}else{
										realtime_customer.setReward_account(realtime_customer.getReward_account() - transaction_amount_25_off + realtime_customer.getMain_account());
										realtime_customer.setMain_account(0);
										k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant_25_off * ( 0.5/100.0));
										company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant_25_off * ( 1.0/100.0));
										realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
										item_bought = true;
										realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant_25_off + " from Merchant " + k.getName());
										//System.out.println("Item Successfully bought");
									}
								}else{
									item_can_be_bought = false;
									System.out.println("Item can't be bought.");
								}
								if ( item_bought)
								{
									kk.setItem_quantity(kk.getItem_quantity() - interested_item_quantity);
								}
								break;
							}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
							if ( kk.getItem_offer() == "buy one get one")
							{
								if ( realtime_customer.getMain_account() + realtime_customer.getReward_account() >= transaction_amount_bogo && kk.getItem_quantity() >= interested_item_quantity)
								{
									if ( realtime_customer.getMain_account()  >= transaction_amount_bogo )
									{
										realtime_customer.setMain_account(realtime_customer.getMain_account() - transaction_amount_bogo);
										k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant_bogo * ( 0.5/100.0));
										company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant_bogo * ( 1.0/100.0));
										realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
										item_bought = true;
										realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant_bogo + " from Merchant " + k.getName());
										//System.out.println("Item Successfully bought");
									}else{
										realtime_customer.setReward_account(realtime_customer.getReward_account() - transaction_amount_bogo + realtime_customer.getMain_account());
										realtime_customer.setMain_account(0);
										k.setContribution_to_company(k.getContribution_to_company() + amount_to_merchant_bogo * ( 0.5/100.0));
										company.setCompany_account_balance(company.getCompany_account_balance() + k.getContribution_to_company() + amount_to_merchant_bogo * ( 1.0/100.0));
										realtime_customer.setNo_of_purchase(realtime_customer.getNo_of_purchase() + 1);
										item_bought = true;
										realtime_customer.all_transactions.add("Bought item " + kk.getItem_category() + " quantity: " + interested_item_quantity + " for Rs " + amount_to_merchant_bogo + " from Merchant " + k.getName());
										//System.out.println("Item Successfully bought");
									}
								}else{
									item_can_be_bought = false;
									System.out.println("Item can't be bought.");
								}
								if ( item_bought)
								{
									kk.setItem_quantity(kk.getItem_quantity() - interested_item_quantity);
								}
							
								break;
							}
			
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
							
							if ( realtime_customer.getNo_of_purchase() % 5 == 0 && item_bought )
							{
								realtime_customer.all_rewards.add("Rupees 10 on Buying " + kk.getItem_category() + "from Merchant");
								realtime_customer.setReward_account( realtime_customer.getReward_account() + 10.0 );
								realtime_customer.cart.item_queue.poll();
								realtime_customer.cart.merchant_queue.poll();
								realtime_customer.cart.quantity_required.poll();
								
							}
							
						}	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////						
						
						break;
			//3) Print reward: Option to see how much reward has been won.			
					case 3:
						if ( realtime_customer.all_rewards.size() != 0)
						{
							for ( String k : realtime_customer.all_rewards)
							{
								System.out.println(k);
							}
						}else{
							System.out.println("No reward has been won");
						}
						break;
//						4) List recent orders: Display the last 10 transactions in the following manner " Bought
//						item “item name” quantity: “item quantity” for Rs “item price” from Merchant: “Merchant
//						Name”.
					case 4:
						for ( int i = realtime_customer.all_transactions.size() - 1; i >= 0; i-- )
						{
							if ( realtime_customer.all_transactions.size() - i > 10)
							{
								break;
							}
							System.out.println(realtime_customer.all_transactions.get(i));
							
						}
						
						break;
						
		// Logging out as Customer
					case 5:
						exit_customer = true;
						break;
					}
				}
				break;
/////////////////////////////////////////////////////////////////////////////////////
				//All details of Merchants and Customers can be printed here
			case 3:
				
	
				
				System.out.println("All Merchants : ");
				for ( Merchant k : all_merchant )
				{
					//System.out.println( "Name: " + k.getName() + "\n" + "Address: " +  k.getAddress() + "\n" + "Contribution to Company: " + k.getContribution_to_company());
					//System.out.println();
					k.print_item_details();
				}
				for ( Customer k : all_customer )
				{
					//System.out.println( "Name: " + k.getName() + "\n" + "Address: " +  k.getAddress() + "\n" + "Number of orders placed: " + k.getNo_of_purchase());
					//System.out.println();
					k.print_item_details();
				}
				break;
				
	// Case for which Company Revenue is printed
			case 4:
				
				System.out.println("Company account balance is :  " + company.getCompany_account_balance() + "/- ");
				break;
	// Closing the application
			case 5:
				exit = true;
				break;
			}
		}		
	}
}
