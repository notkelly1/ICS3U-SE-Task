
/*Lauren's update*/
import java.util.*;
import java.lang.*;
import java.io.*;

public class SoftwareEngineeringTask {
   // Constant Declaration
   public static final String TRANSACTION_HISTORY = "CheckoutTransactionsFile.txt";
   public static final String INVENTORY = "InventoryFile.txt";
   public static final String EMPLOYEE = "EmployeeFile.txt";
   public static final int MAX_SIZE = 5; // maximum number of employees/transactions/inventory
   public static final int TRANSACTION_PARAMETERS = 7;
   public static final int INVENTORY_PARAMETERS = 4;
   public static final int EMPLOYEE_PARAMETERS = 5;
   public static final String ADMIN_PIN = "2468";
   public static final String ADMIN_ID = "00000";

   // Variable Declaration
   public static String[][] arrayTransactions = new String[MAX_SIZE][TRANSACTION_PARAMETERS];
   public static String[][] arrayEmployees = new String[MAX_SIZE][EMPLOYEE_PARAMETERS];
   public static String[][] arrayInventory = new String[MAX_SIZE][INVENTORY_PARAMETERS];
   public static String employeeID = "";

   // main method
   public static void main(String[] args) {
      // Variable Declaration
      String id = "";
      String employeePIN = "";
      String choice = "";
      String userInput = "";
      String pin = "";
      String firstName = "";
      String lastName = "";
      String title = "";
      String status = "";
      String fileName = "";
      int loginResult = 0;
      int inputCheckNum;
      int transactionNumber;
      boolean isValid;
      boolean exitSwitchLoop = false;
      boolean exitMenuLoop = false;

      // Start fileReader
      readFile(TRANSACTION_HISTORY);
      readFile(INVENTORY);
      readFile(EMPLOYEE);

      // test print the array (TRANSACTIONS)
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         for (int cols = 0; cols < TRANSACTION_PARAMETERS; cols++) {
            System.out.print(arrayTransactions[rows][cols] + " ");
         }
         System.out.println("new transaction\n");
      }

      // test print the array (INVENTORY)
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         for (int cols = 0; cols < INVENTORY_PARAMETERS; cols++) {
            System.out.print(arrayInventory[rows][cols] + " ");
         }
         System.out.println("new item\n");
      }

      // test print the array (EMPLOYEES)
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         for (int cols = 0; cols < EMPLOYEE_PARAMETERS; cols++) {
            System.out.print(arrayEmployees[rows][cols] + " ");
         }
         System.out.println("new employee\n");
      }

      // Creating Scanner
      Scanner sc = new Scanner(System.in);

      // loop the login method if the input is not quit
      while (!(employeeID.equalsIgnoreCase("quit")) && loginResult != 1 && loginResult != -1) {
         do {
            // prompt user for login
            System.out.println("Please enter your employee ID");
            employeeID = sc.nextLine();
            isValid = false;

            try {
               // check input in try catch to verify if it is numerical
               inputCheckNum = Integer.parseInt(employeeID);

               if (employeeID.length() == 5) {
                  isValid = true;
               } else {
                  System.out.println("Employee IDs must be a 5-digits. Please try again.");
               }
            } catch (NumberFormatException e) {
               if (!(employeeID.equalsIgnoreCase("quit"))) {
                  System.out.println("Employee IDs must be a 5-digit number. Please try again.");
               }
            }
         } while ((!isValid) && !(employeeID.equalsIgnoreCase("quit")) && loginResult != 1 && loginResult != -1);

         // reset to use it for other checks
         inputCheckNum = 0;

         if (!(employeeID.equalsIgnoreCase("quit"))) {
            System.out.println("Please enter your PIN");
            employeePIN = sc.nextLine();

            // perform checks on the ID and PIN
            // DELETE LATER!!!! see if login works
            loginResult = login(employeeID, employeePIN);
            System.out.println("Login method result: " + loginResult);

            if (loginResult == 0) {
               System.out.println("Login failed. Please try again. (Invalid Login Attempt)");
            } else if (loginResult == 1) {
               System.out.println("Login successful (employee).");

               // MENU START (LOOP UNTIL QUIT)

               while (!exitMenuLoop) {
                  System.out.println(
                        "Select a number:\n1. Add Inventory\n2. Update Inventory\n3. Delete Inventory\n4. Check Inventory\n5. List all items\n6. List item\n7. Checkout");
                  choice = sc.nextLine();

                  // switch case for menu
                  switch (choice) {
                     case "1":

                        do {
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if (!userInput.equalsIgnoreCase("q")) {
                              // try catch to determine if the UPC code is all numbers
                              try {
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8) {
                                    // run method
                                    addInventory(userInput);
                                 } else {
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              } catch (NumberFormatException e) {
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }
                        // check condition
                        while (!userInput.equalsIgnoreCase("q"));

                        break;

                     case "2":

                        do {
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if (!userInput.equalsIgnoreCase("q")) {
                              // try catch to determine if the UPC code is all numbers
                              try {
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8) {
                                    // run method
                                    updateInventory(userInput);
                                 } else {
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              } catch (NumberFormatException e) {
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }

                        // check condition
                        while (!userInput.equalsIgnoreCase("q"));
                        // loop until Q is pressed to break the loop

                        break;

                     case "3":

                        System.out.println("Enter an 8 digit UPC Code");
                        delete(sc.nextLine());
                        break;

                     case "4":

                        do {
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if (!userInput.equalsIgnoreCase("q")) {
                              // try catch to determine if the UPC code is all numbers
                              try {
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8) {
                                    // run method
                                    checkInv(userInput);
                                 } else {
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              } catch (NumberFormatException e) {
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }

                        // check condition
                        while (!userInput.equalsIgnoreCase("q"));
                        break;

                     case "5":

                        listAll();
                        break;

                     case "6":
                        System.out.println("Enter an item name");
                        listItem(sc.nextLine());
                        break;

                     case "7":
                        checkOut(employeeID);
                        exitMenuLoop = true;
                        break;
                  } // end of switch
               } // end of while
            } // end of if
            else if (loginResult == -1) {
               System.out.println("Login successful (admin).");
               // start of while
               while (!exitMenuLoop) {
                  System.out.println(
                        "Select a number:\n1. Add Inventory\n2. Update Inventory\n3. Delete Inventory\n4. Check Inventory\n5. List all items\n6. List item\n7. Checkout\n8. Add an employee\n9. Edit an employee's details\n10. Activate/Deactivate an employee\n11. View all transactions\n12. View a transaction by transaction number");
                  choice = sc.nextLine();
                  switch (choice) {
                     case "1":

                        do {
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if (!userInput.equalsIgnoreCase("q")) {
                              // try catch to determine if the UPC code is all numbers
                              try {
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8) {
                                    // run method
                                    addInventory(userInput);
                                 } else {
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              } catch (NumberFormatException e) {
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }
                        // check condition
                        while (!userInput.equalsIgnoreCase("q"));

                        break;

                     case "2":

                        do {
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if (!userInput.equalsIgnoreCase("q")) {
                              // try catch to determine if the UPC code is all numbers
                              try {
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8) {
                                    // run method
                                    updateInventory(userInput);
                                 } else {
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              } catch (NumberFormatException e) {
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }

                        // check condition
                        while (!userInput.equalsIgnoreCase("q"));
                        // loop until Q is pressed to break the loop

                        break;

                     case "3":

                        System.out.println("Enter an 8 digit UPC Code");
                        delete(sc.nextLine());
                        break;

                     case "4":

                        do {
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if (!userInput.equalsIgnoreCase("q")) {
                              // try catch to determine if the UPC code is all numbers
                              try {
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8) {
                                    // run method
                                    checkInv(userInput);
                                 } else {
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              } catch (NumberFormatException e) {
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }

                        // check condition
                        while (!userInput.equalsIgnoreCase("q"));
                        break;

                     case "5":

                        listAll();
                        break;

                     case "6":
                        System.out.println("Enter an item name");
                        listItem(sc.nextLine());
                        break;

                     case "7":

                        checkOut(employeeID);
                        exitMenuLoop = true;
                        break;

                     // UNCOMMENT THIS WHEN I FIGURE OUT HOW TO IMPLEMENT THE REST OF THE METHODS
                     case "8":

                        System.out.print("Enter PIN: ");
                        pin = sc.nextLine();

                        System.out.print("Enter First Name: ");
                        firstName = sc.nextLine();

                        System.out.print("Enter Last Name: ");
                        lastName = sc.nextLine();

                        System.out.print("Enter Title: ");
                        title = sc.nextLine();

                        System.out.print("Enter Status (Active or Inactive): ");
                        status = sc.nextLine();

                        addEmployee(pin, firstName, lastName, title, status);
                        break;

                     case "9":

                        System.out.print("Enter Employee ID to edit: ");
                        id = sc.nextLine();
                        editDetails(id);
                        break;

                     case "10":

                        // Ask the user for the employee ID
                        System.out.print("Enter the Employee ID to change status: ");
                        employeeID = sc.nextLine();

                        // Call the method to change the status
                        changeEmployeeStatus(employeeID);
                        break;

                     case "11":

                        viewAllTransactions();
                        break;

                     case "12":

                        // Ask user which transaction to view
                        System.out.print("Enter the transaction number you want to view: ");
                        transactionNumber = sc.nextInt();

                        // Call the method with the given transaction number
                        viewTransactions(transactionNumber);
                        break;
                     /*
                      * case "8":
                      * 
                      * System.out.print("Enter new employee ID, : ");
                      * addEmployee(sc.nextLine(), );
                      * break;
                      * 
                      * case "9":
                      * 
                      * System.out.print("Enter employee ID: ");
                      * editDetails(sc.nextLine());
                      * break;
                      * 
                      * case "10":
                      * 
                      * System.out.print("Enter employee ID: ");
                      * changeEmployeeStatus(sc.nextLine());
                      * break;
                      * 
                      * case "11":
                      * 
                      * viewAllTransactions();
                      * break;
                      * 
                      * case "12":
                      * 
                      * try{
                      * System.out.print("Enter the Transaction Number: ");
                      * viewTransactions(sc.nextInt());
                      * }
                      * catch(InputMismatchException e){
                      * System.out.print("Bad Input (not a number)");
                      * }
                      * break;
                      */
                  }// end of switch
               } // end of while
            }
         } // ends the loop
           // FileWriter method used to update the file after all changes are made
         writeFile(TRANSACTION_HISTORY);
         writeFile(INVENTORY);
         writeFile(EMPLOYEE);

         // test print the array (TRANSACTIONS)
         for (int rows = 0; rows < MAX_SIZE; rows++) {
            for (int cols = 0; cols < TRANSACTION_PARAMETERS; cols++) {
               System.out.print(arrayTransactions[rows][cols] + " ");
            }
            System.out.println("new transaction\n");
         }

         // test print the array (INVENTORY)
         for (int rows = 0; rows < MAX_SIZE; rows++) {
            for (int cols = 0; cols < INVENTORY_PARAMETERS; cols++) {
               System.out.print(arrayInventory[rows][cols] + " ");
            }
            System.out.println("new item\n");
         }

         // test print the array (EMPLOYEES)
         for (int rows = 0; rows < MAX_SIZE; rows++) {
            for (int cols = 0; cols < EMPLOYEE_PARAMETERS; cols++) {
               System.out.print(arrayEmployees[rows][cols] + " ");
            }
            System.out.println("new employee\n");
         }
      }
   }// END OF MAIN METHOD

   /*
    * Name: login
    * Return Type: int
    * Parameters: String employeeID, String PIN
    * Description: This method compares the entered employee IDs and PINs to the
    * existing files
    * Change:
    */

   public static int login(String employeeID, String employeePIN) {

      // Constant Declaration
      final int ID_INDEX = 0;
      final int PIN_INDEX = 1;
      final int ACTIVITY_INDEX = 4;

      // Variable Declaration
      int rows = 0;
      int loginType = 0;
      boolean endLogic = false;

      // Check for admin login first
      if (employeeID.equalsIgnoreCase(ADMIN_ID) && employeePIN.equalsIgnoreCase(ADMIN_PIN)) {
         loginType = -1; // admin login
      }

      while (!endLogic && rows < MAX_SIZE) {
         // Check if ID cell is not null before comparing
         if (arrayEmployees[rows][ID_INDEX] != null && arrayEmployees[rows][0].equals(employeeID)) {
            // Check if PIN cell is also not null before comparing
            if (arrayEmployees[rows][PIN_INDEX] != null && arrayEmployees[rows][1].equals(employeePIN)) {
               // nested if to check if the user logging in is active or inactive
               if (arrayEmployees[rows][ACTIVITY_INDEX].equalsIgnoreCase("Active")) {
                  loginType = 1; // successful login
               }
               // user is inactive
               else {
                  System.out.println("Access is denied, as the user status is INACTIVE");
                  loginType = 0;
               }
            } else {
               loginType = 0; // wrong PIN
            }
            endLogic = true; // ID found, end checks
         } else {
            rows++; // move to next row
         }
      }

      return loginType;
   }

   /*
    * Name: writeFile
    * Return Type: void
    * Parameters: String filename
    * Description: This method stores final arrays that were changed into the files
    * at the very end of the main
    * Change:
    */
   public static void writeFile(String fileName) {
      int numLines = 0;
      String[][] targetArray = null;

      if (fileName.equals(TRANSACTION_HISTORY)) {
         numLines = TRANSACTION_PARAMETERS;
         targetArray = arrayTransactions;
      } else if (fileName.equals(INVENTORY)) {
         numLines = INVENTORY_PARAMETERS;
         targetArray = arrayInventory;
      } else if (fileName.equals(EMPLOYEE)) {
         numLines = EMPLOYEE_PARAMETERS;
         targetArray = arrayEmployees;
      }

      try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) // try-with-resources for safe closing
      {
         for (int rows = 0; rows < MAX_SIZE; rows++) {
            for (int cols = 0; cols < numLines; cols++) {
               String currentValue = targetArray[rows][cols];
               if (currentValue == null || currentValue.equalsIgnoreCase("null")) {
                  currentValue = "";
               }
               out.write(currentValue + "\n");
            }
            out.write("***\n");
         }
      } catch (IOException e) {
      }
      // System.out.println("written");
   }

   /*
    * Name: readFile
    * Return Type: void
    * Parameters: String fileName
    * Description: Stores files that should be at the start of the main into 2D
    * arrays
    * Change:
    */
   public static void readFile(String fileName) {
      int numLines = 0;
      String line = "";
      int rows = 0;
      int cols = 0;
      String[][] array = null;

      // Set correct array and number of fields
      if (fileName.equals(TRANSACTION_HISTORY)) {
         numLines = TRANSACTION_PARAMETERS;
         array = arrayTransactions;
      } else if (fileName.equals(INVENTORY)) {
         numLines = INVENTORY_PARAMETERS;
         array = arrayInventory;
      } else if (fileName.equals(EMPLOYEE)) {
         numLines = EMPLOYEE_PARAMETERS;
         array = arrayEmployees;
      }

      try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
         while ((line = in.readLine()) != null) {
            // line = line.trim();

            // If it's the record separator or an empty line, skip logic without using
            // continue
            if (!line.equals("***") && !line.equals("")) {
               if (rows < array.length && cols < numLines) {
                  array[rows][cols] = line.equalsIgnoreCase("null") ? "" : line;
                  cols++;
               }

               // When a full record is read, move to next row
               if (cols == numLines) {
                  rows++;
                  cols = 0;
               }
            }
         }
      } catch (IOException e) {
      }
   }

   /*
    * Name: addEmployee
    * Return Type: void
    * Parameters: String pin, String firstName, String lastName, String title,
    * String status
    * Description: This method will assign the next available Employee ID number
    * and prompt the administrator to provide:
    * the PIN, first name, last name, title and status of the new employee,
    * updating all information to their respective arrays
    * Change:
    */

   // Lauren's Code
   public static void addEmployee(String pin, String firstName, String lastName, String title, String status) {
      String[] lines = new String[1000];
      int totalLines = 0;
      String line;
      int start = -1;
      int maxID = 0;
      int newID = 0;
      String employeeID = "";
      boolean isNumber;

      // Step 1: Read the file into the lines array
      try {
         BufferedReader in = new BufferedReader(new FileReader("EmployeeFile.txt"));
         while ((line = in.readLine()) != null) {
            lines[totalLines] = line;
            totalLines++;
         }
         in.close();
      } catch (IOException e) {
         System.out.println("Error reading file.");
         return;
      }

      // Step 2: Find the first empty slot pattern: ***, "", ***
      for (int j = 0; j < totalLines - 5; j++) {
         if (lines[j].equals("") && lines[j + 1].equals("") && lines[j + 2].equals("") && lines[j + 3].equals("")
               && lines[j + 4].equals("") && lines[j + 5].equals("***")) {
            start = j;
         }
      }

      if (start == -1) {
         System.out.println("No available employee slot.");
         return;
      }

      System.out.println("Found slot at line: " + start);

      // Step 3: Find the highest existing employee ID (5-digit number)
      for (int i = 0; i < totalLines; i++) {
         line = lines[i];
         if (line != null && line.length() == 5) {
            isNumber = true;
            for (int k = 0; k < 5; k++) {
               char c = line.charAt(k);
               if (c < '0' || c > '9') {
                  isNumber = false;
               }
            }
            if (isNumber) {
               int id = Integer.parseInt(line);
               if (id > maxID) {
                  maxID = id;
               }
            }
         }
      }

      // Step 4: Generate new employee ID with leading zeros
      newID = maxID + 1;
      if (newID < 10) {
         employeeID = "0000" + newID;
      } else if (newID < 100) {
         employeeID = "000" + newID;
      } else if (newID < 1000) {
         employeeID = "00" + newID;
      } else if (newID < 10000) {
         employeeID = "0" + newID;
      } else {
         employeeID = "" + newID;
      }

      System.out.println("Writing employee ID: " + employeeID);

      // Step 5: Create new employee info array
      String[] newEmployeeInfo = new String[5];
      newEmployeeInfo[0] = employeeID;
      newEmployeeInfo[1] = pin;
      newEmployeeInfo[2] = firstName + " " + lastName;
      newEmployeeInfo[3] = title;
      newEmployeeInfo[4] = status;

      // Step 6: Copy new employee info into lines array using a loop
      int idx = 0;
      for (int i = start; i < start + 5; i++) {
         lines[i] = newEmployeeInfo[idx];
         idx++;
      }
      // lines[start + 5] should remain "***" (leave untouched)

      // Step 7: Write the updated lines back to the file
      try {
         BufferedWriter bw = new BufferedWriter(new FileWriter("EmployeeFile.txt"));
         for (int i = 0; i < totalLines; i++) {
            if (lines[i] != null) {
               bw.write(lines[i]);
            }
            bw.newLine();
         }
         bw.close();
         System.out.println("Employee added successfully with ID: " + employeeID);
      } catch (IOException e) {
         System.out.println("Error writing to file.");
      }
   }

   /*
    * Name: editDetails
    * Return Type: void
    * Parameters: String employeeID
    * Description: This method prompts the administrator for the employee ID to
    * edit and then checks and compares if it exists. If it matches, the system
    * should allow the administrator to change the PIN, First name, Last name, and
    * Title; if not, an error message will be printed.
    * Change:
    */
   public static void editDetails(String employeeID) {
      File inputFile = new File("EmployeeFile.txt");
      File tempFile = new File("employees_temp.txt");

      try {
         BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
         Scanner sc = new Scanner(System.in);

         String line;
         boolean found = false;
         String currentPIN;
         String newPin;
         String currentName;
         String newName;
         String currentTitle;
         String newTitle;
         String currentStatus;
         String boundary;

         while ((line = reader.readLine()) != null) {
            if (line.equals(employeeID)) {
               found = true;

               writer.write(line); // write employeeID
               writer.newLine();

               // PIN
               currentPIN = reader.readLine();
               System.out.println("Current PIN: " + currentPIN);
               System.out.print("Enter new PIN (or Enter to keep): ");
               newPin = sc.nextLine();
               if (newPin.equals("")) {
                  newPin = currentPIN;
               }
               writer.write(newPin);
               writer.newLine();

               // Name
               currentName = reader.readLine();
               System.out.println("Current Name: " + currentName);
               System.out.print("Enter new Name (or Enter to keep): ");
               newName = sc.nextLine();
               if (newName.equals("")) {
                  newName = currentName;
               }
               writer.write(newName);
               writer.newLine();

               // Title
               currentTitle = reader.readLine();
               System.out.println("Current Title: " + currentTitle);
               System.out.print("Enter new Title (or Enter to keep): ");
               newTitle = sc.nextLine();
               if (newTitle.equals("")) {
                  newTitle = currentTitle;
               }
               writer.write(newTitle);
               writer.newLine();

               // Status (do not change)
               currentStatus = reader.readLine();
               writer.write(currentStatus);
               writer.newLine();

               // Write the boundary line ***
               boundary = reader.readLine();
               writer.write(boundary);
               writer.newLine();
            } else {
               // just copy line to output
               writer.write(line);
               writer.newLine();
            }
         }

         if (!found) {
            System.out.println("Employee ID not found!");
         } else {
            System.out.println("Employee details updated.");
         }
      } catch (IOException e) {
         System.out.println("Error processing the file.");
         e.printStackTrace();
      }

      // Replace original file with updated temp file
      if (tempFile.exists()) {
         inputFile.delete();
         tempFile.renameTo(inputFile);
      }
   }

   /*
    * Name: changeEmployeeStatus
    * Return Type: void
    * Parameters: String employeeID
    * Description: This method changes the status of an employee in arrayStatus
    * Change:
    */
   public static void changeEmployeeStatus(String employeeID) {
      File originalFile = new File("EmployeeFile.txt");
      File updatedFile = new File("TempEmployeeFile.txt");

      // Declare variables before the loop
      String id = "";
      String pin = "";
      String name = "";
      String jobTitle = "";
      String status = "";
      String separator = "";

      boolean found = false;

      try {
         BufferedReader reader = new BufferedReader(new FileReader(originalFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(updatedFile));
         String line;

         while ((line = reader.readLine()) != null) {
            // Use the first line as ID
            id = line;
            pin = reader.readLine();
            name = reader.readLine();
            jobTitle = reader.readLine();
            status = reader.readLine();
            separator = reader.readLine(); // should be ***

            // If it's the employee we're updating
            if (id.equals(employeeID)) {
               System.out.println("Found employee: " + name);
               System.out.println("Old status: " + status);

               // Change the status
               if (status.equalsIgnoreCase("Active")) {
                  status = "Inactive";
               } else {
                  status = "Active";
               }
               System.out.println("New status: " + status);
               found = true;
            }
            // Write the (possibly updated) employee info to the new file
            writer.write(id);
            writer.newLine();
            writer.write(pin);
            writer.newLine();
            writer.write(name);
            writer.newLine();
            writer.write(jobTitle);
            writer.newLine();
            writer.write(status);
            writer.newLine();
            writer.write("***");
            writer.newLine();
         }
         reader.close();
         writer.close();

         // Replace original file with updated one
         if (originalFile.delete()) {
            if (updatedFile.renameTo(originalFile)) {
               System.out.println("Employee file updated successfully.");
            } else {
               System.out.println("Could not rename the updated file.");
            }
         } else {
            System.out.println("Could not delete the original file.");
         }

         if (!found) {
            System.out.println("Employee ID " + employeeID + " not found.");
         }

      } catch (IOException e) {
         System.out.println("Something went wrong: " + e.getMessage());
      }
   }

   /*
    * Name: viewAllTransactions
    * Return Type: void
    * Parameters: None
    * Description: This method reads the CheckoutTransactionsFile.txt, then prints
    * all the transactions from the file
    * Change:
    */
   public static void viewAllTransactions() {
      // Constant Declaration
      final int TRANSACTION_INDEX = 0;
      final int ID_INDEX = 1;
      final int ITEMS_INDEX = 2;
      final int ITEMPRICE_INDEX = 3;
      final int SUBTOTAL_INDEX = 4;
      final int TAXES_INDEX = 5;
      final int TOTAL_INDEX = 6;

      // System.out.println("Transaction # | Employee ID | Items Sold | Price of Items
      // Sold | Subtotal Cost | Taxes | Total Cost")
      // print the array (TRANSACTIONS)
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         System.out.printf("Transaction #: %s\n", arrayTransactions[rows][TRANSACTION_INDEX]);
         System.out.printf("Employee ID: %s\n", arrayTransactions[rows][ID_INDEX]);
         System.out.printf("Items Sold: %s\n", arrayTransactions[rows][ITEMS_INDEX]);
         System.out.printf("Price of Items Sold: %s\n", arrayTransactions[rows][ITEMPRICE_INDEX]);
         System.out.printf("Subtotal Cost: %s\n", arrayTransactions[rows][SUBTOTAL_INDEX]);
         System.out.printf("Taxes: %s\n", arrayTransactions[rows][TAXES_INDEX]);
         System.out.printf("Total Cost: %s\n", arrayTransactions[rows][TOTAL_INDEX]);

         System.out.println("\n");
      }
   }

   /*
    * {
    * File file = new File("CheckoutTransactionsFile.txt");
    * 
    * if (!file.exists())
    * {
    * System.out.println("No transactions found.");
    * return;
    * }
    * 
    * try
    * {
    * BufferedReader reader = new BufferedReader(new FileReader(file));
    * String line;
    * 
    * System.out.println("=== All Transactions ===");
    * 
    * while ((line = reader.readLine()) != null)
    * {
    * System.out.println(line);
    * }
    * 
    * reader.close();
    * }
    * catch (IOException e)
    * {
    * System.out.println(e + "  Problem reading " + file);
    * }
    */

   /*
    * Name: viewTransactions
    * Return Type: void
    * Parameters: int specificTransaction
    * Description: This method asks the user for the transaction they want to view,
    * then reads the specific transaction from the CheckoutTransactionsFile, only
    * printing the specific transaction
    * Change:
    */
   public static void viewTransactions(int specificTransaction) {
      // Constant Declaration
      final int TRANSACTION_INDEX = 0;
      final int ID_INDEX = 1;
      final int ITEMS_INDEX = 2;
      final int ITEMPRICE_INDEX = 3;
      final int SUBTOTAL_INDEX = 4;
      final int TAXES_INDEX = 5;
      final int TOTAL_INDEX = 6;

      // Variable Declaration
      int rows = 0;
      String line;
      boolean inTransaction = false;
      boolean found = false;
      boolean done = false;

      File file = new File("CheckoutTransactionsFile.txt");

      if (!file.exists()) {
         System.out.println("No transactions found.");
         return;
      }

      try {
         BufferedReader reader = new BufferedReader(new FileReader(file));

         while (!done) {
            line = reader.readLine();

            if (line == null) {
               // end of file
               done = true;
            } else {
               if (inTransaction == false) {
                  // try to read the transaction number
                  try {
                     int transactionNum = Integer.parseInt(line);
                     if (transactionNum == specificTransaction) {
                        inTransaction = true;
                        found = true;
                        System.out.println(line); // print transaction number

                        // prints the corresponding line in the array
                        rows = transactionNum - 1;
                        System.out.printf("Transaction #: %s\n", arrayTransactions[rows][TRANSACTION_INDEX]);
                        System.out.printf("Employee ID: %s\n", arrayTransactions[rows][ID_INDEX]);
                        System.out.printf("Items Sold: %s\n", arrayTransactions[rows][ITEMS_INDEX]);
                        System.out.printf("Price of Items Sold: %s\n", arrayTransactions[rows][ITEMPRICE_INDEX]);
                        System.out.printf("Subtotal Cost: %s\n", arrayTransactions[rows][SUBTOTAL_INDEX]);
                        System.out.printf("Taxes: %s\n", arrayTransactions[rows][TAXES_INDEX]);
                        System.out.printf("Total Cost: %s\n", arrayTransactions[rows][TOTAL_INDEX]);

                     }
                  } catch (NumberFormatException e) {
                     // not a number, just ignore
                  }
               } else {
                  // we're inside the transaction
                  System.out.println(line);
                  if (line.equals("***")) {
                     done = true; // stop after end of this transaction
                  }
               }
            }
         }

         if (found == false) {
            System.out.println("Transaction #" + specificTransaction + " not found.");
         }

         reader.close();

      } catch (IOException e) {
         System.out.println("Error reading file.");
      }
   }

   /*
    * Name: addInventory
    * Return Type: void
    * Parameters: String codeUPC
    * Description: This method asks for an 8-digit UPC code, then checks and
    * compares the code. If it doesnt match, the user will be prompted to enter
    * the name, price, and quantity if the UPC code does not match anything in the
    * current inventory. This will loop until Q is pressed to return to the main
    * menu.
    * Change:
    */
   public static void addInventory(String codeUPC) {
      // Constant Declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;

      // Variable Declaration
      int firstEmptyRow = 0;

      // Creating Scanner
      Scanner sc = new Scanner(System.in);

      // check the array for codeUPC
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         if (codeUPC.equals(arrayInventory[rows][UPC_INDEX])) {
            // check (delete later)
            System.out.println("UPC code exists in array!");
            // try again in main method
            // once the return is reached, the rest of the method doesn't run.
            return;
         }
      }
      // determine where the first empty spot in the array is (either null, or empty
      // string)
      while (firstEmptyRow < MAX_SIZE && arrayInventory[firstEmptyRow][UPC_INDEX] != null
            && !arrayInventory[firstEmptyRow][UPC_INDEX].equals("")) {
         firstEmptyRow++;
      }
      if (firstEmptyRow >= MAX_SIZE) {
         System.out.println("Inventory is full. Cannot add new item.");
         return;
      }
      // if we get to this step, it means that the UPC code does not exist, so we need
      // to add a new value in the array.
      arrayInventory[firstEmptyRow][UPC_INDEX] = codeUPC;
      // prompt user for name, price, and quantity of the item.

      // prompt user for name of the item
      System.out.print("What is the name of the item?: ");
      // update the appropriate array index with the next keyboard input
      arrayInventory[firstEmptyRow][NAME_INDEX] = sc.nextLine();

      // prompt user for price of the item
      System.out.print("What is the price of the item?: ");
      // update the appropriate array index with the next keyboard input
      arrayInventory[firstEmptyRow][PRICE_INDEX] = sc.nextLine();

      // prompt user for quantity of the item
      System.out.print("What is the quantity of the item?: ");
      // update the appropriate array location with the next keyboard input
      arrayInventory[firstEmptyRow][QTY_INDEX] = sc.nextLine();

      return; // Exit after successful update
   }

   /*
    * Name: updateInventory
    * Return Type: void
    * Parameters: int specificTransaction
    * Description: This method asks for an 8-digit UPC code, then checks and
    * compares the code. If it matches, then it will prompt the user to enter the
    * name of the item, current price, and current quantity, then prompt for new
    * price and quantity. This will loop until Q is pressed to return to the main
    * menu.
    * Change:
    */
   public static void updateInventory(String codeUPC) {
      // Constant Declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;

      // Creating Scanner
      Scanner sc = new Scanner(System.in);

      // check the array for codeUPC
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         System.out.println("Checking row " + rows + ": " + arrayInventory[rows][UPC_INDEX]);
         if (codeUPC.trim().equals(arrayInventory[rows][UPC_INDEX])) {
            // if (codeUPC.trim().equals(arrayInventory[rows][UPC_INDEX].trim())){
            // print the name, current price & qty of item
            System.out.printf("The item name is %s. The current price is $%s, and the current quantity is %s.%n",
                  arrayInventory[rows][NAME_INDEX], arrayInventory[rows][PRICE_INDEX], arrayInventory[rows][QTY_INDEX]);

            // prompt user for new price of the item
            System.out.print("What is the new price of the item?: ");
            // update the appropriate array indexwith the next keyboard input
            arrayInventory[rows][PRICE_INDEX] = sc.nextLine();

            // prompt user for new quantity of the item
            System.out.print("What is the new quantity of the item?: ");
            // update the appropriate array location with the next keyboard input
            arrayInventory[rows][QTY_INDEX] = sc.nextLine();

            return; // Exit after successful update
         }
      }
      System.out.println("Item does not exist, try again"); // loop in the main method
      return; // Exit
   }

   /*
    * Name: delete
    * Return Type: void
    * Parameters: String codeUPC
    * Description: This method asks for an 8-digit UPC code, then checks and
    * compares the code. If it does match, then the user will be prompted for the
    * name of the item to be deleted, removing it from the system, and then return
    * to the main menu. If it doesnt match a message that the item does not exist
    * will be printed, then return to the main menu.
    * Change:
    */
   public static void delete(String codeUPC) {
      // Constant Declaration
      final int UPC_INDEX = 1;
      final int NAME_INDEX = 0;

      // Variable Declaration
      // String itemName = "";
      // boolean itemExists = false;

      // Creating Scanner
      Scanner sc = new Scanner(System.in);
      // checks codeUPC against all values in the inventory array

      // check the array for codeUPC
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         if (codeUPC.equals(arrayInventory[rows][UPC_INDEX])) {
            // the item exists
            System.out.println("UPC code exists in array!");
            System.out.print("Please input the name of the item: ");

            // itemName = sc.nextLine();
            if (arrayInventory[rows][NAME_INDEX].equals(sc.nextLine())) {
               System.out.println("Name Found!");
               // delete item (loop through specific row and make everything ""
               for (int cols = 0; cols < INVENTORY_PARAMETERS; cols++) {
                  arrayInventory[rows][cols] = "";
               }
               System.out.println("Item deleted successfully.");
            } else {
               System.out.println("Name does not exist in the Inventory. (Incorrect Item Name)");
            }
            // itemExists = true;
            return;
         }
      }

      // if statement to check
      // if it exists: Prompt user for name of item to delete
      /*
       * if(itemExists){
       * System.out.print("Please input the name of the item: ");
       * itemName = sc.nextLine();
       * }
       * // if it does not exist: print item does not exist
       * else{
       */
      System.out.print("Item does not exist.");
   }

   /*
    * Name: checkInv
    * Return Type: void
    * Parameters: String upcCode
    * Description: This method asks for an 8-digit UPC code, then checks and
    * compares the code. If it exists the the name, current price, and current
    * quantity of the item will be printed. If it doesnt match a message that the
    * item does not exist will be printed. This will loop until Q is pressed to
    * return to the main menu.
    * Change:
    */
   public static void checkInv(String codeUPC) {
      // Constant Declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;

      // determine if item exists in array
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         if (codeUPC.equals(arrayInventory[rows][UPC_INDEX])) {
            // print the name, current price & qty of item
            System.out.printf("The item name is %s. The current price is $%s, and the current quantity is %s.%n",
                  arrayInventory[rows][NAME_INDEX], arrayInventory[rows][PRICE_INDEX], arrayInventory[rows][QTY_INDEX]);
            return;
         }
      }
      System.out.println("The item does not exist. Try again.");
      return;
   }

   /*
    * Name: listAll
    * Return Type: void
    * Parameters: None
    * Description: This method prints the arrayInventory, then returns to the main
    * menu
    * Change:
    */
   public static void listAll() {
      // constant declaration
      final int PRICE_INDEX = 2;

      // print the array (INVENTORY)
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         for (int cols = 0; cols < PRICE_INDEX; cols++) {
            System.out.print(arrayInventory[rows][cols] + " ");
         }
         System.out.println("\n");
      }
   }

   /*
    * Name: listItem
    * Return Type: void
    * Parameters: String itemName
    * Description: This method asks the user for an item name, then checks and
    * compares the item to the arrayInventory. If it does exist, then the UPC,
    * price and quantity of that item will be printed; if not Item not found will
    * be printed, then return to the main menu.
    * Change:
    */
   public static void listItem(String itemName) {
      // Constant declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;

      // compare item name with values in inventoryArray
      for (int rows = 0; rows < MAX_SIZE; rows++) {
         if (itemName.equals(arrayInventory[rows][NAME_INDEX])) {
            // print the UPC, current price & qty of item
            System.out.printf("The item UPC is %s. The current price is $%s, and the current quantity is %s.%n",
                  arrayInventory[rows][UPC_INDEX], arrayInventory[rows][PRICE_INDEX], arrayInventory[rows][QTY_INDEX]);
            return;
         }
      }
      System.out.println("Item does not exist.");
   }

   /*
    * Name: checkOut
    * Return Type: void
    * Parameters: String employeeID (need to record which employee performed the
    * action)
    * Description: This method prompts the user to input the UPC for items to be
    * purchased, keeping a total of the price. If a UPC does not exist, an error is
    * printed. If it does, the name, price, and total items scanned are displayed.
    * If 'Q' is entered, a receipt is printed, outlining the transaction number,
    * item names, and price. Inventory counts are reduced as items are purchased.
    * Change:
    */
   public static void checkOut(String employeeID) {
      // Constant Declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;
      final double HST = 0.13;

      // transaction array constants
      final int TRANSACTION_INDEX = 0;
      final int ID_INDEX = 1;
      final int ITEMS_INDEX = 2;
      final int ITEMPRICE_INDEX = 3;
      final int SUBTOTAL_INDEX = 4;
      final int TAXES_INDEX = 5;
      final int TOTAL_INDEX = 6;

      // Variable Declaration
      String scannedItems = "The list of items is: ";
      String scannedPrice = "";
      String codeUPC = "";
      double subtotal = 0;
      double totalPrice = 0;
      int newQty = 0;
      int newTransaction = 0;
      boolean itemFound = false;

      int itemIndex = 0;
      boolean itemUpdated = false;

      // STUPID CHECK DELETE LATER
      // System.out.println(employeeID);

      // Creating Scanner
      Scanner sc = new Scanner(System.in);
      
      // SET ITEMFOUND TO FALSE
      itemFound = false;
         
      do {
         
         // prompt user for a UPC
         System.out.print("Please Enter a UPC: ");
         codeUPC = sc.nextLine();

         // perform check to see if UPC exists
         for (int rows = 0; rows < MAX_SIZE && !itemFound; rows++) {
            if (codeUPC.equals(arrayInventory[rows][UPC_INDEX])) {
               /// *PRINT*/ System.out.println("ENTERED IF STATEMENT");

               // print the name, current price & qty of item
               System.out.printf("The item name is %s. The current price of the item is $%s\n",
                     arrayInventory[rows][NAME_INDEX], arrayInventory[rows][PRICE_INDEX]);
               scannedItems = scannedItems + arrayInventory[rows][NAME_INDEX] + ", ";
               scannedPrice = scannedPrice + arrayInventory[rows][PRICE_INDEX] + ", ";
               System.out.println("ITEM FOUND SET TO TRUE");
               itemFound = true;
               itemIndex = rows;

               // check qty
               if (Integer.parseInt(arrayInventory[rows][QTY_INDEX]) == 0) {
                  System.out.println("Inventory count kept at zero.");
               } else {
                  // reduce inv count by 1
                  try {
                     newQty = Integer.parseInt(arrayInventory[rows][QTY_INDEX]) - 1;
                     arrayInventory[rows][QTY_INDEX] = ("" + newQty);
                  } catch (NumberFormatException e) {
                     System.out.println("Invalid quantity format at row " + rows);
                  }
               }

               // Calculate transaction
               try {
                  subtotal += Double.parseDouble(arrayInventory[rows][PRICE_INDEX]);
                  // subtotal check
                  System.out.printf("Current Subtotal is $%.2f.\n", subtotal);

                  /*
                   * // Check for how much HST would be on this
                   * System.out.printf("How much 13%% HST would be on the subtotal $%.2f.\n",
                   * (subtotal * HST));
                   * 
                   * // total price including 13% HST
                   * System.out.printf("Total price including 13%% HST: $%.2f.\n", (subtotal * (1
                   * + HST)));
                   */

               } catch (NumberFormatException e) {
                  System.out.println("Invalid price format at row " + rows);
               }

               // print all items scanned thus far
               System.out.println(scannedItems);
            }
         } // end of for
         
         if (!itemFound && !(codeUPC.equalsIgnoreCase("q"))) {
            itemFound = false;
            System.out.println("The item does not exist. Try again.");
         }
         /// *PRINT*/ System.out.println("LOOP BACK TO BEGINNING");
      }
      // loop until 'q' is entered
      while (!(codeUPC.equalsIgnoreCase("q")));

      // RUN AFTER WHILE LOOP ENDS
      System.out.printf("\nRECEIPT\n");
      // Check for how much HST would be on this
      System.out.printf("Amount of 13%% HST on the subtotal $%.2f.\n", (subtotal * HST));

      // total price including 13% HST
      System.out.printf("Total price including 13%% HST: $%.2f.\n", (subtotal * (1 + HST)));

      // save the price/HST arrayTransactions

      System.out.println("DEBUG: itemFound is " + itemFound);
      if (itemFound) {
         System.out.println("why tf isnt this running");
         // UPC/item exists: print out the name, priceof item, and total of all items
         // that have been scanned thus far
         System.out.printf("Item name: %s\n", arrayInventory[itemIndex][NAME_INDEX]);
         System.out.printf("1 of %s costs $%.2f.\n", arrayTransactions[itemIndex][ITEMPRICE_INDEX]);
         System.out.printf("So far, %d items have been scanned so far.\n", arrayTransactions[itemIndex][ITEMS_INDEX]);

         // update arrays here (?)
         for (int i = 0; i < MAX_SIZE; i++) {
            if (arrayTransactions[i][TRANSACTION_INDEX] == null && !itemUpdated) {
               newTransaction = Integer.parseInt(arrayTransactions[i - 1][TRANSACTION_INDEX]) + 1;
               arrayTransactions[i][ID_INDEX] = employeeID;
               arrayTransactions[i][ITEMS_INDEX] = scannedItems;
               arrayTransactions[i][PRICE_INDEX] = scannedPrice;
               itemUpdated = true;
            }
         }
      } else {
         if (!(codeUPC.equalsIgnoreCase("q"))) {
            System.out.println("Error: item not found... :fearful:");
            itemFound = false;
         }
         System.out.println("else?????");
      }

      // if statement
      // error if UPC does not exist
      // if it exists the name and price of the item should be displayed, along with a
      // total of all items that have been scanned thus far.

      // if statement to check if the qty = 0
      // reduce inventory count by 1 if qty > 0 (change global array)
      // calculate transaction
      // add the item name to the string

      // end loop
      // record transactions

   }// end checkOut method
}// end of class