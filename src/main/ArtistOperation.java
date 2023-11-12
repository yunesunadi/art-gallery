package main;

import java.util.InputMismatchException;
import java.util.LinkedList;

public class ArtistOperation extends User implements ArtistInterface {
	int artistCount = 5, artworkCount = 7;
	
	static LinkedList<Artist> artists = new LinkedList<>();
	static LinkedList<Artwork> artworks = new LinkedList<>();
	LinkedList<OrderedArtwork> orderedArtworks = CustomerOperation.orderedArtworks;
	
	public ArtistOperation() {		
		Artist a1 = new Artist("A1", "Terry Medhurst", "terry.medhurst@example.com", "1043 N Stelling Rd", "09123456789", "Painter");
		Artist a2 = new Artist("A2", "Jared Grant", "jared.grant@example.com", "9220 Elgin St", "09234567891", "Photographer");
		Artist a3 = new Artist("A3", "Gabe Campbell", "gabe.campbell@example.com", "8494 W Sherman Dr", "09345678912", "Painter");
		Artist a4 = new Artist("A4", "Marion Perez", "marion.perez@example.com", "19067 Plum St", "09456789123", "Photographer");
		Artist a5 = new Artist("A5", "Charlie Collins", "charlie.collins@example.com", "6016 College St", "09567891234", "Painter");
		artists.add(a1);
		artists.add(a2);
		artists.add(a3);
		artists.add(a4);
		artists.add(a5);
		
		Artwork aw1 = new Artwork("AW1", "Musical Rules", "Lorem ipsum dolor sit amet", "1700", "Oil painting", "Painting", "Terry Medhurst", false, false);
		Artwork aw2 = new Artwork("AW2", "A Walking Penguin", "Lorem ipsum dolor sit amet", "1100", "Animal", "Photography", "Jared Grant", true, false);
		Artwork aw3 = new Artwork("AW3", "The Oriental Arthur", "Lorem ipsum dolor sit amet", "700", "Watercolor painting", "Painting", "Gabe Campbell", false, false);
		Artwork aw4 = new Artwork("AW4", "A racing car", "Lorem ipsum dolor sit amet", "900", "Car", "Photography", "Marion Perez", true, false);
		Artwork aw5 = new Artwork("AW5", "Knight Foundation", "Lorem ipsum dolor sit amet", "1500", "Portrait painting", "Painting", "Charlie Collins", false, false);
		Artwork aw6 = new Artwork("AW6", "Waterfall", "Lorem ipsum dolor sit amet", "1000", "Nature", "Photography", "Jared Grant", true, false);
		Artwork aw7 = new Artwork("AW7", "Difficult Nature", "Lorem ipsum dolor sit amet", "750", "Landscape painting", "Painting", "Gabe Campbell", false, true);
		artworks.add(aw1);
		artworks.add(aw2);
		artworks.add(aw3);
		artworks.add(aw4);
		artworks.add(aw5);
		artworks.add(aw6);
		artworks.add(aw7);
	}
	
	@Override
	public void welcome() {		
		System.out.println("\nWelcome to Artist User Site");
		System.out.println("===========================");
		System.out.println("1. Create the artist information");
		System.out.println("2. Update the artist information");
		System.out.println("3. Delete the artist information");
		System.out.println("4. Add the art creation information");
		System.out.println("5. Delete the art creation information");
		System.out.println("6. View the ordered art information by artist name");
		System.out.println("7. Calculate the total sales information by artist name");
		System.out.println("8. Logout");
		
		try {
			System.out.print("\nEnter option: ");
			int option = sc.nextInt();
			
			switch(option) {
				case 1: createArtist(); break;
				case 2: updateArtist(); break;
				case 3: deleteArtist(); break;
				case 4: createArtwork(); break;
				case 5: deleteArtwork(); break;
				case 6: viewOrderedArtwork(); break;
				case 7: calculateTotalSales(); break;
				case 8: logout(); break;
				default: System.out.println("*Input must be between 1 and 8. Please try again.");
			}
			
			welcome();
		} catch(InputMismatchException ex) {
			System.out.println("*Input must be a number. Please try again.");
			sc.nextLine();
			welcome();
		}
	}
	
	public String enterProfession(String profession, boolean professionStatus) {
		String option = "";
		
		while(!professionStatus) {
			boolean optionStatus = false;
			
			try {
				System.out.println("Choose artist profession.");
				System.out.println("1. Painter");
				System.out.println("2. Photographer");
				option = enterNumber(option, optionStatus, "option");
				
				switch(option) {
					case "1": 
					{	
						profession = "Painter";
						professionStatus = true;
					}	
					break;
					case "2":
					{
						profession = "Photographer"; 
						professionStatus = true;
					}	
					break;
					default:
					{
						professionStatus = false;
						System.out.println("*Option must be between 1 and 2.");
					}
				}
			} catch(Exception ex) {
				ex.getMessage();
			}
		}
		return profession;
	}

	@Override
	public void createArtist() {
		char status;
		String id = "",
			name = "", 
			email = "",
			address = "",
			phoneNumber = "",
			profession = "";
		
		do {
			boolean nameStatus = false,
					emailStatus = false,
					phoneNumberStatus = false,
					professionStatus = false;
			artistCount++;
			id = "A" + artistCount;
			
			System.out.println();
			sc.nextLine();
			name = enterName(name, nameStatus, "artist");
			email = enterEmail(email, emailStatus, "artist");
			sc.nextLine();
			address = enterAddress(address, "artist");
			phoneNumber = enterPhoneNumber(phoneNumber, phoneNumberStatus, "artist");
			profession = enterProfession(profession, professionStatus);
			
			Artist a = new Artist(id, name, email, address, phoneNumber, profession);
			artists.add(a);
			System.out.println("Create artist successfully.");
			
			System.out.print("\nDo you want to create another artist information (y, n)? ");
			status = sc.next().charAt(0);
		} while (Character.toLowerCase(status) == 'y');
	}

	@Override
	public void updateArtist() {
		boolean status = false, artistNameStatus = false;
		char doStatus;
		String option = "",
				artistName = "",
				name = "", 
				email = "",
				address = "",
				phoneNumber = "",
				profession = "";
		
		System.out.println();
		sc.nextLine();
		artistName = enterName(artistName, artistNameStatus, "artist");
		
		for(Artist a: artists) {
			if(artistName.equalsIgnoreCase(a.getName())) {
				do {
					boolean updateStatus = false;
					
					while(!updateStatus) {
						boolean nameStatus = false, 
								emailStatus = false, 
								phoneNumberStatus = false,
								professionStatus = false,
								optionStatus = false;
	
						try {
							System.out.println("Which one do you want to update?");
							System.out.println("1. Artist name");
							System.out.println("2. Artist email");
							System.out.println("3. Artist address");
							System.out.println("4. Artist phone number");
							System.out.println("5. Artist profession");
							option = enterNumber(option, optionStatus, "option");
							
							switch(option) {
								case "1": 
								{	
									name = enterName(name, nameStatus, "artist");
									a.setName(name);
									updateStatus = true;
									System.out.println("Update artist name successfully.");
								}	
								break;
								case "2":
								{
									email = enterEmail(email, emailStatus, "artist");
									a.setEmail(email);
									updateStatus = true;
									System.out.println("Update artist email successfully.");
								}	
								break;
								case "3":
								{
									address = enterAddress(address, "artist");
									a.setAddress(address);
									updateStatus = true;
									System.out.println("Update artist address successfully.");
								}	
								break;
								case "4":
								{
									phoneNumber = enterPhoneNumber(phoneNumber, phoneNumberStatus, "artist");
									a.setPhoneNumber(phoneNumber);
									updateStatus = true;
									System.out.println("Update artist phone number successfully.");
								}	
								break;
								case "5":
								{
									profession = enterProfession(profession, professionStatus);
									a.setProfession(profession);
									updateStatus = true;
									System.out.println("Update artist profession successfully.");
								}	
								break;
								default:
								{
									updateStatus = false;
									System.out.println("*Input must be between 1 and 5.");
								}
							}
						} catch(Exception ex) {
							ex.getMessage();
						}
					}
										
					System.out.println("\nDo you want to update another information (y, n)? ");
					doStatus = sc.next().charAt(0);
					sc.nextLine();
				} while (Character.toLowerCase(doStatus) == 'y');
				
				status = true;
				break;
			}
		}
		
		if(!status) {
			System.out.println("*Artist name not found.");
		}
	}

	@Override
	public void deleteArtist() {
		boolean status = false, nameStatus = false;
		String name = "";
		
		System.out.println();
		sc.nextLine();
		name = enterName(name, nameStatus, "artist");

		for(Artist a: artists) {
			if(name.equalsIgnoreCase(a.getName())) {
				for(Artwork aw: artworks) {
					if(a.getName().equalsIgnoreCase(aw.getArtist()) && !aw.isRemoved()) {
						aw.setRemoved(true);
					}
				}
				status = true;
			}
		}
		
		for(Artwork aw: artworks) {
			artists.removeIf((Artist a) -> a.getName().equalsIgnoreCase(aw.getArtist()) && !aw.isRemoved());
		}
		
		if(status) {
			System.out.println("Delete artist successfully.");
		} else {
			System.out.println("*Artist name not found.");
		}
	}
	
	public String enterDescription(String description) {
		System.out.print("Enter artwork description: ");
		description = sc.nextLine();
		return description;
	}
	
	public String enterPrice(String price, boolean priceStatus) {
		while(!priceStatus) {
			try {
				System.out.print("Enter artwork price: ");
				price = sc.nextLine();
				String val = Character.toUpperCase("price".charAt(0)) + "price".substring(1);
				
				if(Utilities.checkNumber(price, val)) {
					priceStatus = true;
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return price;
	}
	
	public String enterType(String type, boolean typeStatus) {
		while(!typeStatus) {
			try {
				System.out.print("Enter artwork type: ");
				type = sc.nextLine();
				
				if (Utilities.checkString(type, "Artwork type")) {
					typeStatus = true;
				}
			} catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return type;
	}
	
	public String enterCategory(String category, boolean categoryStatus) {
		String option = "";
		
		while(!categoryStatus) {
			boolean optionStatus = false;
			
			try {
				System.out.println("Choose artwork category.");
				System.out.println("1. Painting");
				System.out.println("2. Photography");
				option = enterNumber(option, optionStatus, "option");
				
				switch(option) {
					case "1": 
					{	
						category = "Painting";
						categoryStatus = true;
					}	
					break;
					case "2":
					{
						category = "Photography"; 
						categoryStatus = true;
					}	
					break;
					default:
					{
						categoryStatus = false;
						System.out.println("*Input must be between 1 and 2.");
					}
				}
			} catch(Exception ex) {
				ex.getMessage();
			}
		}
				
		return category;
	}

	@Override
	public void createArtwork() {
		char status;
		String id = "",
			name = "", 
			description = "",
			type = "",
			category = "",
			artistName = "",
			price = "";
		boolean isSoldOut = false, isRemoved = false;
		
		do {
			boolean nameStatus = false,
					priceStatus = false,
					typeStatus = false,
					categoryStatus = false,
					artistNameStatus = false,
					isFound = false;
			artworkCount++;
			id = "AW" + artworkCount;
			
			System.out.println();
			sc.nextLine();
			name = enterName(name, nameStatus, "artwork");
			description = enterDescription(description);
			price = enterPrice(price, priceStatus);
			type = enterType(type, typeStatus);
			category = enterCategory(category, categoryStatus);
			
			while(!isFound) {
				artistName = enterName(artistName, artistNameStatus, "artist");
			
				for(Artist a : artists) {
					if(artistName.equalsIgnoreCase(a.getName())) {
						isFound = true;
						break;
					}
				}
				
				if(!isFound) {
					System.out.println("*Artist name not found.");
				}
			}
			
			Artwork aw = new Artwork(id, name, description, price, type, category, artistName, isSoldOut, isRemoved);
			artworks.add(aw);
			System.out.println("Create artwork successfully.");
			
			System.out.println("\nDo you want to create another artwork information (y, n)? ");
			status = sc.next().charAt(0);
		} while (Character.toLowerCase(status) == 'y');
	}

	@Override
	public void deleteArtwork() {
		String artistName = "", artworkName = "";
		boolean artistNameStatus = false,
				artworkNameStatus = false,
				isArtistFound = false,
				isArtworkFound = false;
		
		sc.nextLine();
		
		while(!isArtistFound) {
			System.out.println();
			artistName = enterName(artistName, artistNameStatus, "artist");
			
			for(Artwork aw: artworks) {
				if(artistName.equalsIgnoreCase(aw.getArtist()) && !aw.isRemoved()) {
					displayArtworkInfo(aw);
					displaySoldOut(aw);
					isArtistFound = true;
				}
			}
			
			if(!isArtistFound) {
				System.out.println("*Artist name not found.");
			}
		}
		
		System.out.println();
		
		while(!isArtworkFound) {
			artworkName = enterName(artworkName, artworkNameStatus, "artwork");
			
			for(Artwork aw: artworks) {
				if(artworkName.equalsIgnoreCase(aw.getName()) && artistName.equalsIgnoreCase(aw.getArtist()) && !aw.isRemoved()) {
					aw.setRemoved(true);
					System.out.println("Delete artwork successfully.");
					isArtworkFound = true;
					break;
				}
			}
			
			if(!isArtworkFound) {
				System.out.println("*Artwork name not found.");
			}
		}
	}

	@Override
	public void viewOrderedArtwork() {
		boolean isFound = false, nameStatus = false;
		String name = "";
		
		System.out.println();
		sc.nextLine();
		name = enterName(name, nameStatus, "artist");
		
		for(OrderedArtwork oa: orderedArtworks) {
			for(Artwork aw: artworks) {
				if(aw.getName().equalsIgnoreCase(oa.getArtwork())) {
					if(name.equalsIgnoreCase(aw.getArtist()) && aw.isSoldOut() && !aw.isRemoved()) {
						displayOrderedArtworkInfo(oa);
						isFound = true;
					}
				}
			}
		}
		
		if(!isFound) {
			System.out.println("*Artist name not found.");
		}
	}

	@Override
	public void calculateTotalSales() {
		int total = 0;
		boolean isFound = false, nameStatus = false;
		String name = "";
		
		System.out.println();
		sc.nextLine();
		name = enterName(name, nameStatus, "artist");
		
		for(Artwork aw: artworks) {
			if(name.equalsIgnoreCase(aw.getArtist())) {
				for(OrderedArtwork oa : orderedArtworks) {
					if(aw.getName().equalsIgnoreCase(oa.getArtwork()) && aw.isSoldOut() && !aw.isRemoved()) {
						total += Integer.parseInt(aw.getPrice());
						displayOrderedArtworkInfo(oa);
						isFound = true;
					}
				}
			}
		}
		
		if(isFound) {
			System.out.println("\nTotal Price: " + "$" + total);
		} else {
			System.out.println("\n*Artist name not found.");
		}
	}
	
	@Override
	public void logout() {
		ArtGalleryMain.login.welcome();
	}
}
