

import java.util.List;
import java.util.Scanner;
import controller.ListItemHelper;
import model.PetItem;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static ListItemHelper helper = new ListItemHelper();

	// add
	private static void addAnItem() {
		System.out.print("Enter owner name: ");
		String owner = in.nextLine();
		System.out.print("Enter pet name: ");
		String name = in.nextLine();
		System.out.println("Enter pet type: ");
		String type = in.nextLine();
		PetItem toAdd = new PetItem(owner, name, type);
		helper.insertItem(toAdd);

	}

	// delete
	private static void deleteAnItem() {
		System.out.print("Enter owner name to delete: ");
		String owner = in.nextLine();
		System.out.print("Enter pet name to delete: ");
		String name = in.nextLine();
		System.out.println("Enter pet type to delete: ");
		String type = in.nextLine();
		PetItem toDelete = new PetItem(owner, name, type);
		helper.deleteItem(toDelete);
	}

	// edit
	private static void editAnItem() {
		System.out.println("How would you like to search? ");
		System.out.println("1. Search by owner");
		System.out.println("2. Search by name");
		System.out.println("3. Search by type");
		
		int searchBy = in.nextInt();
		in.nextLine();
		List<PetItem> foundItems;
		
		if (searchBy == 1) {
			System.out.print("Enter the owner name: ");
			String owner = in.nextLine();
			foundItems = helper.searchForPetByOwner(owner);
		} else if (searchBy == 2) {
			System.out.print("Enter the name: ");
			String name = in.nextLine();
			foundItems = helper.searchForPetByName(name);
		} else {
			System.out.print("Enter the type: ");
			String type = in.nextLine();
			foundItems = helper.searchForPetByType(type);
		}
		if (!foundItems.isEmpty()) {
			System.out.println("Found Results");
			for (PetItem p : foundItems) {
				System.out.println(p.getId() + " : " + p.toString());
			}
			System.out.print("Enter ID to edit: ");
			int idToEdit = in.nextInt();

			PetItem toEdit = helper.searchForPetById(idToEdit);
			System.out.println("Retrieved Name: " + toEdit.getName() + ", Type: " + toEdit.getType() + ", Owner: "
					+ toEdit.getOwner());
			System.out.println("1. Update Owner");
			System.out.println("2. Update Type");
			System.out.println("3. Update Name");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Owner: ");
				String newOwner = in.nextLine();
				toEdit.setOwner(newOwner);
			} else if (update == 2) {
				System.out.print("New Type: ");
				String newType = in.nextLine();
				toEdit.setType(newType);
			} else if (update == 3) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			}

			helper.updatePet(toEdit);
		} else {
			System.out.println("No results");
		}

	}

	// main
	public static void main(String[] args) {
		runMenu();
	}

	// menu
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our awesome Pets list! ---");
		while (goAgain) {
			System.out.println("Select an Item:");
			System.out.println("1. Add a Pet");
			System.out.println("2. Edit a pet");
			System.out.println("3. Delete a pet");
			System.out.println("4. View the list of pets");
			System.out.println("5. Exit");
			int usrInput = in.nextInt();
			in.nextLine();

			if (usrInput == 1) {
				addAnItem();
			} else if (usrInput == 2) {
				editAnItem();
			} else if (usrInput == 3) {
				deleteAnItem();
			} else if (usrInput == 4) {
				viewTheList();
			} else {
				helper.cleanUp();
				System.out.println("Exiting...");
				goAgain = false;
			}

		}

	}

	// print list
	private static void viewTheList() {
		List<PetItem> allItems = helper.showAllItems();
		for (PetItem singleItem : allItems) {
			System.out.println(singleItem.returnItemDetails());
		}

	}

}
