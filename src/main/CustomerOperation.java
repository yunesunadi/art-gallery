package main;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class CustomerOperation extends User implements CustomerInterface {
	int customerCount = 0, orderedArtworkCount = 0;
	
	LinkedList<Artist> artists = ArtistOperation.artists;
	LinkedList<Artwork> artworks = ArtistOperation.artworks;
	static LinkedList<OrderedArtwork> orderedArtworks = new LinkedList<>();
	
	@Override
	public void welcome() {	
		System.out.println("\nWelcome to Customer User Site");
		System.out.println("=============================");
		System.out.println("1. View all the sorted art creation information");
		System.out.println("2. Search the art creation information by artist name");
		System.out.println("3. Order the art information");
		System.out.println("4. Cancel the ordered art information");
		System.out.println("5. View the ordered art information by customer name");
		System.out.println("6. Calculate the payment information of ordered art by customer name");
		System.out.println("7. Logout");
			
		try {
			System.out.print("\nEnter option: ");
			int option = sc.nextInt();
			
			switch(option) {
				case 1: viewSortedArtwork(); break;
				case 2: searchArtwork(); break;
				case 3: orderArtwork(); break;
				case 4: cancelArtwork(); break;
				case 5: viewOrderedArtwork(); break;
				case 6: calculatePayment(); break;
				case 7: logout(); break;
				default: {
					System.out.println("*Input must be between 1 and 7. Please try again.");
				}
			}
			
			welcome();
		} catch(InputMismatchException ex) {
			System.out.println("*Input must be a number. Please try again.");
			sc.nextLine();
			welcome();
		}
	}

	@Override
	public void viewSortedArtwork() {
		Comparator<Artwork> comparator = Comparator.comparing(Artwork::getPrice);
		Collections.sort(artworks, comparator);
	
		System.out.println("\nAvailable artworks");
		System.out.println("==================");
		for(Artwork aw: artworks) {
			if(!aw.isRemoved() && !aw.isSoldOut()) {
				displayArtworkInfo(aw);
			}
		}
		System.out.println();
		
		System.out.println("Sold out artworks");
		System.out.println("=================");
		for(Artwork aw: artworks) {			
			if(aw.isSoldOut() && !aw.isRemoved()) {
				displayArtworkInfo(aw);
			} 
		}
		System.out.println();
		
		System.out.println("Removed artworks");
		System.out.println("================");
		for(Artwork aw: artworks) {
			if(aw.isRemoved()) {
				displayArtworkInfo(aw);
			} 
		}
	}

	@Override
	public void searchArtwork() {
		boolean isFound = false, nameStatus = false;
		String name = "";
		
		System.out.println();
		sc.nextLine();
		name = enterName(name, nameStatus, "artist");
		
		for(Artwork aw: artworks) {
			if(name.equalsIgnoreCase(aw.getArtist()) && !aw.isRemoved()) {
				displayArtworkInfo(aw);
				displaySoldOut(aw);
				isFound = true;
			}
		}
		
		if(!isFound) {
			System.out.println("\n*Artist name not found.");
		}
	}

	@Override
	public void orderArtwork() {
		char status;
		String id = "", 
				name = "", 
				email = "",
				address = "",
				phoneNumber = "",
				orderedArtworkId = "",
				orderedArtworkName = "";
		Customer c;
		OrderedArtwork oa;
		Date date = new Date();
			
			do {
				boolean nameStatus = false,
						emailStatus = false,
						phoneNumberStatus = false,
						orderedArtworkNameStatus = false,
						isFound = false;
				
				customerCount++;
				id = "C" + customerCount;
				
				System.out.println();
				sc.nextLine();
				name = enterName(name, nameStatus, "customer");
				email = enterEmail(email, emailStatus, "customer");
				sc.nextLine();
				address = enterAddress(address, "customer");
				phoneNumber = enterPhoneNumber(phoneNumber, phoneNumberStatus, "customer");
				
				orderedArtworkCount++;
				orderedArtworkId = "OA" + orderedArtworkCount;
				
				while(!isFound) {
					orderedArtworkName = enterName(orderedArtworkName, orderedArtworkNameStatus, "artwork");	
				
					for(Artwork aw: artworks) {
						if(orderedArtworkName.equalsIgnoreCase(aw.getName()) && !aw.isSoldOut() && !aw.isRemoved()) {
							c = new Customer(id, name, email, address, phoneNumber);
							aw.setSoldOut(true);
							oa = new OrderedArtwork(orderedArtworkId, c, aw.getName(), date.toString());
							orderedArtworks.add(oa);
							System.out.println("Order the artwork successfully.");
							isFound = true;
							break;
						}
					}
					
					if(!isFound) {
						System.out.println("*Artwork name not found.");
					}
				}				
			
			System.out.print("\nDo you want to order another artwork (y, n)? ");
			status = sc.next().charAt(0);
		} while (Character.toLowerCase(status) == 'y');	
	}

	@Override
	public void cancelArtwork() {
		boolean isCustomerFound = false,
				isArtworkFound = false,
				customerNameStatus = false,
				artworkNameStatus = false;
		String customerName = "", artworkName = "";
		
		System.out.println();
		sc.nextLine();
		customerName = enterName(customerName, customerNameStatus, "customer");
		
		for(OrderedArtwork oa: orderedArtworks) {
			if(customerName.equalsIgnoreCase(oa.getCustomer().getName())) {
				for(Artwork aw: artworks) {
					if(oa.getArtwork().equalsIgnoreCase(aw.getName()) && !aw.isRemoved()) {
						displayOrderedArtworkInfo(oa);
						isCustomerFound = true;
					}
				}
			}
		}
		
		System.out.println();
		
		if(isCustomerFound) {
			artworkName = enterName(artworkName, artworkNameStatus, "artwork");
		
			for(OrderedArtwork oa: orderedArtworks) {
				if(customerName.equalsIgnoreCase(oa.getCustomer().getName()) && artworkName.equalsIgnoreCase(oa.getArtwork())) {
					for(Artwork aw: artworks) {
						if(artworkName.equalsIgnoreCase(aw.getName()) && !aw.isRemoved()) {
							aw.setSoldOut(false);
							System.out.println("Cancel the artwork successfully.");
							isArtworkFound = true;
							break;
						}
					}
				}
			}
			
			if(!isArtworkFound) {
				System.out.println("*Artwork name not found.");
			}
		} else {
			System.out.println("*Customer name not found.");
		}
	}

	@Override
	public void viewOrderedArtwork() {
		boolean isFound = false, nameStatus = false;
		String name = "";
		
		System.out.println();
		sc.nextLine();
		name = enterName(name, nameStatus, "customer");
		
		for(OrderedArtwork oa: orderedArtworks) {
			if(name.equalsIgnoreCase(oa.getCustomer().getName())) {
				for(Artwork aw: artworks) {
					if(oa.getArtwork().equalsIgnoreCase(aw.getName()) && aw.isSoldOut() && !aw.isRemoved()) {
						displayOrderedArtworkInfo(oa);
						isFound = true;
					}
				}
			}
		}
		
		if(!isFound) {
			System.out.println("*Customer name not found.");
		}
	}

	@Override
	public void calculatePayment() {
		int total = 0;
		boolean isFound = false, nameStatus = false;
		String name = "";
		
		System.out.println();
		sc.nextLine();
		name = enterName(name, nameStatus, "customer");
		
		for(OrderedArtwork oa: orderedArtworks) {
			if(name.equalsIgnoreCase(oa.getCustomer().getName())) {
				for(Artwork aw : artworks) {
					if(aw.getName().equalsIgnoreCase(oa.getArtwork()) && aw.isSoldOut() && !aw.isRemoved()) {
						total += Integer.parseInt(aw.getPrice());
						displayOrderedArtworkInfo(oa);
						isFound = true;
					}
				}
			}
		}
		
		if(isFound) {
			System.out.println("\nTotal Price = " + total);
		} else {
			System.out.println("*Customer name not found.");
		}
	}
	
	@Override
	public void logout() {
		ArtGalleryMain.login.welcome();
	}
}
