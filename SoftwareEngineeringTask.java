import java.util.*;
import java.lang.*;
import java.io.*;

public class SoftwareEngineeringTask{
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
   public static void main(String[] args){
      // Variable Declaration
      String id = "";
      String employeePIN = "";
      String choice = "";
      String userInput = "";
      String pin = "" ;
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
      for(int rows = 0; rows < MAX_SIZE; rows++){
         for(int cols = 0; cols < TRANSACTION_PARAMETERS; cols++){
            System.out.print(arrayTransactions[rows][cols] + " ");
            }
            System.out.println("new transaction\n");
      }

      // test print the array (INVENTORY)
      for(int rows = 0; rows < MAX_SIZE; rows++){
         for(int cols = 0; cols < INVENTORY_PARAMETERS; cols++){
            System.out.print(arrayInventory[rows][cols] + " ");
            }
            System.out.println("new item\n");
      }

      // test print the array (EMPLOYEES)
      for(int rows = 0; rows < MAX_SIZE; rows++){
         for(int cols = 0; cols < EMPLOYEE_PARAMETERS; cols++){
            System.out.print(arrayEmployees[rows][cols] + " ");
            }
            System.out.println("new employee\n");
      }

      // Creating Scanner
      Scanner sc = new Scanner(System.in);

      // loop the login method if the input is not quit
      while(!(employeeID.equalsIgnoreCase("quit")) && loginResult != 1 && loginResult != -1){
         do
         {
            // prompt user for login
            System.out.println("Please enter your employee ID");
            employeeID = sc.nextLine();
            isValid = false;

            try
            {
               // check input in try catch to verify if it is numerical
               inputCheckNum = Integer.parseInt(employeeID);

               if (employeeID.length() == 5)
               {
                  isValid = true;
               }
               else
               {
                  System.out.println("Employee IDs must be a 5-digits. Please try again.");
               }
            }
            catch(NumberFormatException e)
            {
               if (!(employeeID.equalsIgnoreCase("quit")))
               {
                  System.out.println("Employee IDs must be a 5-digit number. Please try again.");
               }
            }
         }
         while((!isValid) && !(employeeID.equalsIgnoreCase("quit")) && loginResult != 1 && loginResult != -1);

         // reset to use it for other checks
         inputCheckNum = 0;

         if(!(employeeID.equalsIgnoreCase("quit"))){
            System.out.println("Please enter your PIN");
            employeePIN = sc.nextLine();

            // perform checks on the ID and PIN
            loginResult = login(employeeID, employeePIN);
            System.out.println("Login method result: " + loginResult);

            if (loginResult == 0)
            {
               System.out.println("Login failed. Please try again. (Invalid Login Attempt)");
            }
            else if (loginResult == 1){
               System.out.println("Login successful (employee).");

               // MENU START (LOOP UNTIL QUIT)

               while(!exitMenuLoop){
                  System.out.println("Select a number:\n1. Add Inventory\n2. Update Inventory\n3. Delete Inventory\n4. Check Inventory\n5. List all items\n6. List item\n7. Checkout");
                  choice = sc.nextLine();

                  // switch case for menu
                  switch (choice){
                     case "1":

                        do{
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if(!userInput.equalsIgnoreCase("q")){
                              // try catch to determine if the UPC code is all numbers
                              try{
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8){
                                    // run method
                                    addInventory(userInput);
                                 }
                                 else{
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              }
                              catch(NumberFormatException e){
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }
                        // check condition
                        while(!userInput.equalsIgnoreCase("q"));

                        break;

                     case "2":

                        do{
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if(!userInput.equalsIgnoreCase("q")){
                              // try catch to determine if the UPC code is all numbers
                              try{
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8){
                                    // run method
                                    updateInventory(userInput);
                                 }
                                 else{
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              }
                              catch(NumberFormatException e){
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }

                        // check condition
                        while(!userInput.equalsIgnoreCase("q"));
                        // loop until Q is pressed to break the loop

                        break;

                     case "3":

                        System.out.println("Enter an 8 digit UPC Code");
                        delete(sc.nextLine());
                        break;

                     case "4":

                        do{
                           System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                           userInput = sc.nextLine();
                           isValid = false;

                           if(!userInput.equalsIgnoreCase("q")){
                              // try catch to determine if the UPC code is all numbers
                              try{
                                 inputCheckNum = Integer.parseInt(userInput);
                                 if (userInput.length() == 8){
                                    // run method
                                    checkInv(userInput);
                                 }
                                 else{
                                    System.out.println("UPC must be exactly 8 digits. Please try again.");
                                 }
                              }
                              catch(NumberFormatException e){
                                 System.out.println("UPC must be an 8 digit number. Please try again.");
                              }
                           }
                        }

                        // check condition
                        while(!userInput.equalsIgnoreCase("q"));
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
            else if (loginResult == -1){
               System.out.println("Login successful (admin).");
               // start of while
               while(!exitMenuLoop){
                  System.out.println("Select a number:\n1. Add Inventory\n2. Update Inventory\n3. Delete Inventory\n4. Check Inventory\n5. List all items\n6. List item\n7. Checkout\n8. Add an employee\n9. Edit an employee's details\n10. Activate/Deactivate an employee\n11. View all transactions\n12. View a transaction by transaction number");
                  choice = sc.nextLine();
                  switch (choice){
                        case "1":

                           do{
                              System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                              userInput = sc.nextLine();
                              isValid = false;

                              if(!userInput.equalsIgnoreCase("q")){
                                 // try catch to determine if the UPC code is all numbers
                                 try{
                                    inputCheckNum = Integer.parseInt(userInput);
                                    if (userInput.length() == 8){
                                       // run method
                                       addInventory(userInput);
                                    }
                                    else{
                                       System.out.println("UPC must be exactly 8 digits. Please try again.");
                                    }
                                 }
                                 catch(NumberFormatException e){
                                    System.out.println("UPC must be an 8 digit number. Please try again.");
                                 }
                              }
                           }
                           // check condition
                           while(!userInput.equalsIgnoreCase("q"));

                           break;

                        case "2":

                           do{
                              System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                              userInput = sc.nextLine();
                              isValid = false;

                              if(!userInput.equalsIgnoreCase("q")){
                                 // try catch to determine if the UPC code is all numbers
                                 try{
                                    inputCheckNum = Integer.parseInt(userInput);
                                    if (userInput.length() == 8){
                                       // run method
                                       updateInventory(userInput);
                                    }
                                   else{
                                       System.out.println("UPC must be exactly 8 digits. Please try again.");
                                    }
                                 }
                                 catch(NumberFormatException e){
                                    System.out.println("UPC must be an 8 digit number. Please try again.");
                                 }
                              }
                           }

                           // check condition
                           while(!userInput.equalsIgnoreCase("q"));
                           // loop until Q is pressed to break the loop

                           break;

                        case "3":

                           System.out.println("Enter an 8 digit UPC Code");
                           delete(sc.nextLine());
                           break;

                        case "4":

                           do{
                              System.out.println("Enter an 8-digit UPC Code (or Q to quit):");
                              userInput = sc.nextLine();
                              isValid = false;

                              if(!userInput.equalsIgnoreCase("q")){
                                 // try catch to determine if the UPC code is all numbers
                                 try{
                                    inputCheckNum = Integer.parseInt(userInput);
                                    if (userInput.length() == 8){
                                       // run method
                                       checkInv(userInput);
                                    }
                                    else{
                                       System.out.println("UPC must be exactly 8 digits. Please try again.");
                                    }
                                 }
                                 catch(NumberFormatException e){
                                    System.out.println("UPC must be an 8 digit number. Please try again.");
                                 }
                              }
                           }

                           // check condition
                           while(!userInput.equalsIgnoreCase("q"));
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

                        case "8":

                        addEmployee();
                        break;

                     case "9":

                        System.out.print("Enter Employee ID to edit: ");
                        String employeeID = sc.nextLine();
                        editDetails(employeeID); // pass ID to the method
                        break;

                     case "10":

                        System.out.print("Enter Employee ID to change status: ");
                        employeeID = sc.nextLine();
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
               }// end of switch
            }// end of while
         }
      }// ends the loop
      // FileWriter method used to update the file after all changes are made
         writeFile(TRANSACTION_HISTORY);
         writeFile(INVENTORY);
         writeFile(EMPLOYEE);

      // test print the array (TRANSACTIONS)
      for(int rows = 0; rows < MAX_SIZE; rows++){
         for(int cols = 0; cols < TRANSACTION_PARAMETERS; cols++){
            System.out.print(arrayTransactions[rows][cols] + " ");
            }
            System.out.println("new transaction\n");
      }

      // test print the array (INVENTORY)
      for(int rows = 0; rows < MAX_SIZE; rows++){
         for(int cols = 0; cols < INVENTORY_PARAMETERS; cols++){
            System.out.print(arrayInventory[rows][cols] + " ");
            }
            System.out.println("new item\n");
      }

      // test print the array (EMPLOYEES)
      for(int rows = 0; rows < MAX_SIZE; rows++){
         for(int cols = 0; cols < EMPLOYEE_PARAMETERS; cols++){
            System.out.print(arrayEmployees[rows][cols] + " ");
            }
            System.out.println("new employee\n");
         }
      }
   }// END OF MAIN METHOD

   /*
   Name: login
   Return Type: int
   Parameters: String employeeID, String PIN
   Description: This method compares the entered employee IDs and PINs to the existing files
   Change:
   */

   public static int login(String employeeID, String employeePIN){

       // Constant Declaration
       final int ID_INDEX = 0;
       final int PIN_INDEX = 1;
       final int ACTIVITY_INDEX = 4;

       // Variable Declaration
       int rows = 0;
       int loginType = 0;
       boolean endLogic = false;

       // Check for admin login first
       if(employeeID.equalsIgnoreCase(ADMIN_ID) && employeePIN.equalsIgnoreCase(ADMIN_PIN))
       {
           loginType = -1; // admin login
       }

       while (!endLogic && rows < MAX_SIZE)
       {
           // Check if ID cell is not null before comparing
           if (arrayEmployees[rows][ID_INDEX] != null && arrayEmployees[rows][0].equals(employeeID))
           {
               // Check if PIN cell is also not null before comparing
               if (arrayEmployees[rows][PIN_INDEX] != null && arrayEmployees[rows][1].equals(employeePIN))
               {
                   // nested if to check if the user logging in is active or inactive
                  if(arrayEmployees[rows][ACTIVITY_INDEX].equalsIgnoreCase("Active")){
                     loginType = 1; // successful login
                  }
                  // user is inactive
                  else{
                     System.out.println("Access is denied, as the user status is INACTIVE");
                     loginType = 0;
                  }
               }
               else
               {
                   loginType = 0; // wrong PIN
               }
               endLogic = true; // ID found, end checks
           }
           else
           {
               rows++; // move to next row
           }
       }

       return loginType;
   }

   /*
   Name: writeFile
   Return Type: void
   Parameters: String filename
   Description: This method stores final arrays that were changed into the files at the very end of the main
   Change:
   */
   public static void writeFile(String fileName){
       int numLines = 0;
       String[][] targetArray = null;

       if(fileName.equals(TRANSACTION_HISTORY))
       {
           numLines = TRANSACTION_PARAMETERS;
           targetArray = arrayTransactions;
       }
       else if(fileName.equals(INVENTORY))
       {
           numLines = INVENTORY_PARAMETERS;
           targetArray = arrayInventory;
       }
       else if(fileName.equals(EMPLOYEE))
       {
           numLines = EMPLOYEE_PARAMETERS;
           targetArray = arrayEmployees;
       }

       try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName)))  // try-with-resources for safe closing
       {
           for(int rows = 0; rows < MAX_SIZE; rows++)
           {
               for(int cols = 0; cols < numLines; cols++)
               {
                   String currentValue = targetArray[rows][cols];
                   if(currentValue == null || currentValue.equalsIgnoreCase("null"))
                   {
                       currentValue = "";
                   }
                   out.write(currentValue + "\n");
               }
               out.write("***\n");
           }
       }
       catch(IOException e)
       {
       }
       //System.out.println("written");
   }


   /*
   Name: readFile
   Return Type: void
   Parameters: String fileName
   Description: Stores files that should be at the start of the main into 2D arrays
   Change:
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
       }
       else if (fileName.equals(INVENTORY)) {
           numLines = INVENTORY_PARAMETERS;
           array = arrayInventory;
       }
       else if (fileName.equals(EMPLOYEE)) {
           numLines = EMPLOYEE_PARAMETERS;
           array = arrayEmployees;
       }

       try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
           while ((line = in.readLine()) != null) {
               line = line.trim(); // Trim whitespace from read line

               // If it's the record separator or an empty line, skip logic without using continue
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
       }
       catch (IOException e) {
       }
   }

   /*
   Name: addEmployee
   Return Type: void
   Parameters: None   
   Description: This method will assign the next available Employee ID number and prompt the administrator to provide: the PIN, first name, last name, title and status of the new employee, updating all information to the arrayEmployees
   Change:
   */

   // Lauren's Code
   public static void addEmployee() 
   {
       String line = "";
       int employeeIndex = 0;
       int paramIndex = 0;
       int nextIndex = -1;
       int maxID = 0;
       String pin = "";
       String firstName = "";
       String lastName = "";
       String title = "";
       String status = "";
       String employeeID = "";       
       Scanner sc = new Scanner(System.in);
   
       // Load existing employees from file
       try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE))) 
       {
           while ((line = br.readLine()) != null && employeeIndex < MAX_SIZE) 
           {
               if (line.equals("***")) 
               {
                   employeeIndex++;
                   paramIndex = 0;
               } 
               else 
               {
                   if (paramIndex < EMPLOYEE_PARAMETERS) 
                   {
                       arrayEmployees[employeeIndex][paramIndex] = line;
                       paramIndex++;
                   }
               }
           }
       } 
       catch (IOException e) 
       {
           System.out.println("Error reading employee file: " + e.getMessage());
           return;
       }
   
       // Find next free slot and max ID
       for (int i = 0; i < MAX_SIZE; i++) 
       {
           if (arrayEmployees[i][0] == null || arrayEmployees[i][0].length() == 0) 
           {
               if (nextIndex == -1) nextIndex = i;
           } 
           else 
           {
               try 
               {
                   int id = Integer.parseInt(arrayEmployees[i][0]);
                   if (id > maxID) maxID = id;
               } 
               catch (NumberFormatException e) 
               {
                   // ignore malformed IDs
               }
           }
       }
       if (nextIndex == -1) 
       {
           System.out.println("No available employee slots.");
           return;
       }
   
       // Prompt admin for new employee data
       System.out.print("Enter PIN: ");
       pin = sc.nextLine();
   
       System.out.print("Enter First Name: ");
       firstName = sc.nextLine();
   
       System.out.print("Enter Last Name: ");
       lastName = sc.nextLine();
   
       System.out.print("Enter Title: ");
       title = sc.nextLine();
   
       System.out.print("Enter Status: ");
       status = sc.nextLine();
   
       int newID = maxID + 1;
       employeeID = "00000" + newID;
       employeeID = employeeID.substring(employeeID.length() - 5); 
        
       // Update arrayEmployees with new data
       arrayEmployees[nextIndex][0] = employeeID;
       arrayEmployees[nextIndex][1] = pin;
       arrayEmployees[nextIndex][2] = firstName + " " + lastName;
       arrayEmployees[nextIndex][3] = title;
       arrayEmployees[nextIndex][4] = status;
   
       // Write entire array back to file
       try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE))) 
       {
           for (int i = 0; i < MAX_SIZE; i++) 
           {
               if (arrayEmployees[i][0] != null && arrayEmployees[i][0].length() > 0) 
               {
                   for (int j = 0; j < EMPLOYEE_PARAMETERS; j++) 
                   {
                       bw.write(arrayEmployees[i][j]);
                       bw.newLine();
                   }
                   bw.write("***");
                   bw.newLine();
               } 
               else 
               {
                   for (int j = 0; j < EMPLOYEE_PARAMETERS; j++) 
                   {
                       bw.newLine();
                   }
                   bw.write("***");
                   bw.newLine();
               }
           }
       } 
       catch (IOException e) 
       {
           System.out.println("Error writing to file: " + e.getMessage());
           return;
       }
   
       // Print confirmation of new employee added
       System.out.println("\nNew Employee Added:");
       System.out.println("ID:     " + arrayEmployees[nextIndex][0]);
       System.out.println("PIN:    " + arrayEmployees[nextIndex][1]);
       System.out.println("Name:   " + arrayEmployees[nextIndex][2]);
       System.out.println("Title:  " + arrayEmployees[nextIndex][3]);
       System.out.println("Status: " + arrayEmployees[nextIndex][4]);
   }
   
   /*
   Name: editDetails
   Return Type: void
   Parameters: String inputID
   Description: This method prompts the administrator for the employee ID to edit and then checks and compares if it exists. If it matches, the system should allow the administrator to change the PIN, First name, Last name, and Title; if not, an error message will be printed.
   Change:

   */
   public static void editDetails(String inputID) 
   {
       Scanner sc = new Scanner(System.in);
       int employeeIndex = -1;
       String line;
       int row = 0;
       int col = 0;
       String pin;
       String fullName;
       String title;
   
       // Load existing employee data
       try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE))) 
       {
           while ((line = br.readLine()) != null && row < MAX_SIZE) 
           {
               if (line.equals("***")) 
               {
                   row++;
                   col = 0;
               } 
               else if (col < EMPLOYEE_PARAMETERS) 
               {
                   arrayEmployees[row][col] = line;
                   col++;
               }
           }
       } 
       catch (IOException e) 
       {
           System.out.println("Error reading employee file: " + e.getMessage());
           return;
       }
   
       // Find the employee index in arrayEmployees
       for (int i = 0; i < MAX_SIZE; i++) 
       {
           if (arrayEmployees[i][0] != null && arrayEmployees[i][0].equals(inputID)) 
           {
               employeeIndex = i;
               break;
           }
       }
   
       if (employeeIndex == -1) 
       {
           System.out.println("Employee ID not found.");
           return;
       }
   
       System.out.println("\nEditing employee: " + inputID + " (press Enter to keep current value)");
   
       // PIN
       System.out.print("PIN [" + arrayEmployees[employeeIndex][1] + "]: ");
       pin = sc.nextLine();
       if (!pin.equals("")) 
       {
           arrayEmployees[employeeIndex][1] = pin;
       }
   
       // Full Name
       System.out.print("Full Name [" + arrayEmployees[employeeIndex][2] + "]: ");
       fullName = sc.nextLine();
       if (!fullName.equals("")) 
       {
           arrayEmployees[employeeIndex][2] = fullName;
       }
   
       // Title
       System.out.print("Title [" + arrayEmployees[employeeIndex][3] + "]: ");
       title = sc.nextLine();
       if (!title.equals("")) 
       {
           arrayEmployees[employeeIndex][3] = title;
       }
   
       // Status is NOT editable (arrayEmployees[employeeIndex][4])
   
       // Write updated arrayEmployees back to file
       try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE))) 
       {
           for (int i = 0; i < MAX_SIZE; i++) 
           {
               if (arrayEmployees[i][0] != null && !arrayEmployees[i][0].equals("")) 
               {
                   for (int j = 0; j < EMPLOYEE_PARAMETERS; j++) 
                   {
                       bw.write(arrayEmployees[i][j]);
                       bw.newLine();
                   }
               } 
               else 
               {
                   // Write blank lines for empty employee slot
                   for (int j = 0; j < EMPLOYEE_PARAMETERS; j++) 
                   {
                       bw.newLine();
                   }
               }
               bw.write("***");
               bw.newLine();
           }
       } 
       catch (IOException e) 
       {
           System.out.println("Error writing to file: " + e.getMessage());
           return;
       }
   
       System.out.println("\nEmployee " + inputID + " updated successfully (unchanged fields preserved).");
   }
      
   /*
   Name: changeEmployeeStatus
   Return Type: void
   Parameters: String inputID
   Description: This method changes the status of an employee in the arrayEmployees
   Change:
   */
   public static void changeEmployeeStatus(String inputID) 
   {
       Scanner scanner = new Scanner(System.in);
       int employeeIndex = -1;
       String line;
       int row = 0;
       int col = 0;
   
       // Load current data into arrayEmployees
       try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE))) 
       {
           while ((line = br.readLine()) != null && row < MAX_SIZE) 
           {
               if (line.equals("***")) 
               {
                   row++;
                   col = 0;
               } 
               else if (col < EMPLOYEE_PARAMETERS) 
               {
                   arrayEmployees[row][col] = line;
                   col++;
               }
           }
       } 
       catch (IOException e) 
       {
           System.out.println("Error reading employee file: " + e.getMessage());
           return;
       }
   
       // Find employee by ID
       for (int i = 0; i < MAX_SIZE; i++) 
       {
           if (arrayEmployees[i][0] != null && arrayEmployees[i][0].equals(inputID)) 
           {
               employeeIndex = i;
               break;
           }
       }
   
       if (employeeIndex == -1) 
       {
           System.out.println("Employee ID not found.");
           return;
       }
   
       String currentStatus = arrayEmployees[employeeIndex][4]; // Status in column 4
   
       if (currentStatus == null || currentStatus.equals("")) 
       {
           System.out.println("Status info missing for employee.");
           return;
       }
   
       System.out.println("Current status of employee " + inputID + ": " + currentStatus);
   
       if (currentStatus.equalsIgnoreCase("Inactive")) 
       {
           System.out.println("Cannot deactivate employee. Status is already Inactive.");
           return;
       }
   
       System.out.print("Are you sure you want to deactivate this employee? (yes/no): ");
       String confirm = scanner.nextLine();
   
       if (!confirm.equalsIgnoreCase("yes")) 
       {
           System.out.println("Operation cancelled.");
           return;
       }
   
       // Update status
       arrayEmployees[employeeIndex][4] = "Inactive";
   
       // Write changes back to the file
       try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMPLOYEE))) 
       {
           for (int i = 0; i < MAX_SIZE; i++) 
           {
               if (arrayEmployees[i][0] != null && !arrayEmployees[i][0].equals("")) 
               {
                   for (int j = 0; j < EMPLOYEE_PARAMETERS; j++) 
                   {
                       bw.write(arrayEmployees[i][j]);
                       bw.newLine();
                   }
               } 
               else 
               {
                   // Write blank lines for empty slot
                   for (int j = 0; j < EMPLOYEE_PARAMETERS; j++) 
                   {
                       bw.newLine();
                   }
               }
               bw.write("***");
               bw.newLine();
           }
       } 
       catch (IOException e) 
       {
           System.out.println("Error writing to file: " + e.getMessage());
           return;
       }
   
       System.out.println("Employee " + inputID + " status has been changed to Inactive.");
   }
      
   /*
   Name: viewAllTransactions
   Return Type: void
   Parameters: None
   Description: This method reads the CheckoutTransactionsFile.txt, then prints all the transactions from the file
   Change:
   */
   public static void viewAllTransactions()
   {
      // Constant Declaration
      final int TRANSACTION_INDEX = 0;
      final int ID_INDEX = 1;
      final int ITEMS_INDEX = 2;
      final int ITEMPRICE_INDEX = 3;
      final int SUBTOTAL_INDEX = 4;
      final int TAXES_INDEX = 5;
      final int TOTAL_INDEX = 6;

         //System.out.println("Transaction # | Employee ID | Items Sold | Price of Items Sold | Subtotal Cost | Taxes | Total Cost")
         //print the array (TRANSACTIONS)
         for(int rows = 0; rows < MAX_SIZE; rows++)
         {
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
   Name: viewTransactions
   Return Type: void
   Parameters: int specificTransaction
   Description: This method asks the user for the transaction they want to view, then reads the specific transaction from the CheckoutTransactionsFile, only printing the specific transaction
   Change:
   */
   public static void viewTransactions(int specificTransaction){
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

      if (!file.exists())
      {
         System.out.println("No transactions found.");
         return;
      }

      try{
         BufferedReader reader = new BufferedReader(new FileReader(file));

         while (!done)
         {
            line = reader.readLine();

            if (line == null)
            {
               // end of file
               done = true;
            }
            else
            {
               if (inTransaction == false)
               {
                  // try to read the transaction number
                  try
                  {
                     int transactionNum = Integer.parseInt(line);
                     if (transactionNum == specificTransaction)
                     {
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
                  }
                  catch (NumberFormatException e)
                  {
                     // not a number, just ignore
                  }
               }
               else
               {
                  // we're inside the transaction
                  System.out.println(line);
                  if (line.equals("***"))
                  {
                  done = true; // stop after end of this transaction
                  }
               }
            }
         }

         if (found == false)
         {
            System.out.println("Transaction #" + specificTransaction + " not found.");
         }

         reader.close();

      }
      catch (IOException e)
      {
         System.out.println("Error reading file.");
      }
   }

   /*
   Name: addInventory
   Return Type: void
   Parameters: String codeUPC
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it doesn’t match, the user will be prompted to enter the name, price, and quantity if the UPC code does not match anything in the current inventory. This will loop until Q is pressed to return to the main menu.
   Change:
   */
   public static void addInventory(String codeUPC)
   {
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
      for(int rows = 0; rows < MAX_SIZE; rows++){
         // Added null check for arrayInventory[rows][UPC_INDEX]
         if(arrayInventory[rows][UPC_INDEX] != null && codeUPC.equals(arrayInventory[rows][UPC_INDEX])){
            System.out.println("UPC code exists in array!");
            return;
         }
      }
      // determine where the first empty spot in the array is (either null, or empty string)
      while(firstEmptyRow < MAX_SIZE && arrayInventory[firstEmptyRow][UPC_INDEX] != null && !arrayInventory[firstEmptyRow][UPC_INDEX].equals("")){
         firstEmptyRow++;
      }
      if (firstEmptyRow >= MAX_SIZE) {
      System.out.println("Inventory is full. Cannot add new item.");
          return;
      }
      // if we get to this step, it  means that the UPC code does not exist, so we need to add a new value in the array.
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
   Name: updateInventory
   Return Type: void
   Parameters: int specificTransaction
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it matches, then it will prompt the user to enter the name of the item, current price, and current quantity, then prompt for new price and quantity. This will loop until Q is pressed to return to the main menu.
   Change:
   */
   public static void updateInventory(String codeUPC)
   {
      // Constant Declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;

      // Creating Scanner
      Scanner sc = new Scanner(System.in);

      // check the array for codeUPC
      for(int rows = 0; rows < MAX_SIZE; rows++){
         System.out.println("Checking row " + rows + ": " + arrayInventory[rows][UPC_INDEX]);
         // Added null check for arrayInventory[rows][UPC_INDEX]
         if(arrayInventory[rows][UPC_INDEX] != null && codeUPC.trim().equals(arrayInventory[rows][UPC_INDEX])){
            // print the name, current price & qty of item
            System.out.printf("The item name is %s. The current price is $%s, and the current quantity is %s.%n", arrayInventory[rows][NAME_INDEX], arrayInventory[rows][PRICE_INDEX], arrayInventory[rows][QTY_INDEX]);

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
   Name: delete
   Return Type: void
   Parameters: String codeUPC
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it does match, then the user will be prompted for the name of the item to be deleted, removing it from the system, and then return to the main menu. If it doesn’t match a message that the item does not exist will be printed, then return to the main menu.
   Change:
   */
   public static void delete(String codeUPC)
   {
      // Constant Declaration
      final int UPC_INDEX = 1;
      final int NAME_INDEX = 0;

      // Creating Scanner
      Scanner sc = new Scanner(System.in);

      // check the array for codeUPC
      for(int rows = 0; rows < MAX_SIZE; rows++){
         // Added null check for arrayInventory[rows][UPC_INDEX]
         if(arrayInventory[rows][UPC_INDEX] != null && codeUPC.equals(arrayInventory[rows][UPC_INDEX])){
            // the item exists
            System.out.println("UPC code exists in array!");
            System.out.print("Please input the name of the item: ");

            // Added null check for arrayInventory[rows][NAME_INDEX]
            if(arrayInventory[rows][NAME_INDEX] != null && arrayInventory[rows][NAME_INDEX].equals(sc.nextLine())){
               System.out.println("Name Found!");
               // delete item (loop through specific row and make everything ""
               for(int cols = 0;  cols < INVENTORY_PARAMETERS; cols++){
                  arrayInventory[rows][cols] = "";
               }
               System.out.println("Item deleted successfully.");
            }
            else{
               System.out.println("Name does not exist in the Inventory. (Incorrect Item Name)");
            }
            return;
         }
      }
      System.out.print("Item does not exist.");
   }

   /*
   Name: checkInv
   Return Type: void
   Parameters: String upcCode
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it exists the the name, current price, and current quantity of the item will be printed. If it doesn’t match a message that the item does not exist will be printed. This will loop until Q is pressed to return to the main menu.
   Change:
   */
   public static void checkInv(String codeUPC)
   {
      // Constant Declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;

      // determine if item exists in array
      for(int rows = 0; rows < MAX_SIZE; rows++){
         // Added null check for arrayInventory[rows][UPC_INDEX]
         if(arrayInventory[rows][UPC_INDEX] != null && codeUPC.equals(arrayInventory[rows][UPC_INDEX])){
            // print the name, current price & qty of item
            System.out.printf("The item name is %s. The current price is $%s, and the current quantity is %s.%n", arrayInventory[rows][NAME_INDEX], arrayInventory[rows][PRICE_INDEX], arrayInventory[rows][QTY_INDEX]);
            return;
         }
      }
      System.out.println("The item does not exist. Try again.");
      return;
   }

   /*
   Name: listAll
   Return Type: void
   Parameters: None
   Description: This method prints the arrayInventory, then returns to the main menu
   Change:
   */
   public static void listAll()
   {
      // Constant Declaration
      final int UPC_INDEX = 1; // Need UPC_INDEX to check if row is empty

      //print the array (INVENTORY)
      for(int rows = 0; rows < MAX_SIZE; rows++){
          // Only print if the row is not empty (e.g., UPC code exists)
          if (arrayInventory[rows][UPC_INDEX] != null && !arrayInventory[rows][UPC_INDEX].equals("")) {
              for(int cols = 0; cols < INVENTORY_PARAMETERS; cols++){ // Print all inventory parameters
                  System.out.print(arrayInventory[rows][cols] + " ");
              }
              System.out.println("\n");
          }
      }
   }

   /*
   Name: listItem
   Return Type: void
   Parameters: String itemName
   Description: This method asks the user for an item name, then checks and compares the item to the arrayInventory. If it does exist, then the UPC, price and quantity of that item will be printed; if not Item not found will be printed, then return to the main menu.
   Change:
   */
   public static void listItem(String itemName)
   {
      // Constant declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;

      // compare item name with values in inventoryArray
      for(int rows = 0; rows < MAX_SIZE; rows++){
         // Added null check for arrayInventory[rows][NAME_INDEX]
         if(arrayInventory[rows][NAME_INDEX] != null && itemName.equals(arrayInventory[rows][NAME_INDEX])){
            // print the UPC, current price & qty of item
            System.out.printf("The item UPC is %s. The current price is $%s, and the current quantity is %s.%n", arrayInventory[rows][UPC_INDEX], arrayInventory[rows][PRICE_INDEX], arrayInventory[rows][QTY_INDEX]);
            return;
         }
      }
      System.out.println("Item does not exist.");
   }

   /*
    Name: checkOut
    Return Type: void
    Parameters: String employeeID (need to record which employee performed the action)
    Description: This method prompts the user to input the UPC for items to be purchased, keeping a total of the price. If a UPC does not exist, an error is printed. If it does, the name, price, and total items scanned are displayed. If 'Q' is entered, a receipt is printed, outlining the transaction number, item names, and price. Inventory counts are reduced as items are purchased.
    Change:
    */
   public static void checkOut(String employeeID){
       // Constant Declaration 
       final int NAME_INDEX = 0;
       final int UPC_INDEX = 1;
       final int PRICE_INDEX = 2;
       final int QTY_INDEX = 3;
       final double HST = 0.13;
   
       final int TRANSACTION_INDEX = 0;
       final int ID_INDEX = 1;
       final int ITEMS_INDEX = 2;
       final int ITEMPRICE_INDEX = 3;
       final int SUBTOTAL_INDEX = 4;
       final int TAXES_INDEX = 5;
       final int TOTAL_INDEX = 6;
   
       // Variable Declarations 
       String scannedItems = "The list of items is: ";
       String scannedPrice = "";
       String codeUPC = "";
       double subtotal = 0;
       double totalPrice = 0;
       int newQty = 0;
       int newTransaction = 0;
       boolean itemFoundInCurrentScan; // Will be initialized in the loop
       boolean transactionMadeDuringCheckout = false;
       boolean continueScanning = true;
       int itemIndex = 0; // Index of the last found item in arrayInventory
       Scanner sc = new Scanner(System.in); // Scanner initialized here
   
       int rows; // Loop counter
       int currentRow; // Loop counter within the item search
       int maxTransactionNum; // Used for finding max transaction number
       int transactionRowIterator; // Loop counter for finding max transaction number
       int nextTransactionRow; // Used for finding the next empty transaction row
       int i; // Loop counter for finding next empty transaction row
   
       double taxes = 0;
   
       // Loop for scanning UPCs
       while(continueScanning) {
           itemFoundInCurrentScan = false; // Reset for each UPC entry
           System.out.print("Please Enter a UPC (or Q to quit): ");
           codeUPC = sc.nextLine();
   
           if (codeUPC.equalsIgnoreCase("q")) {
               continueScanning = false; // Exit the loop if 'q' is entered
           }
           else {
               // Perform check to see if UPC exists
               currentRow = 0; // Initialize loop counter
               while (currentRow < MAX_SIZE && !itemFoundInCurrentScan) {
                   if(arrayInventory[currentRow][UPC_INDEX] != null && codeUPC.equals(arrayInventory[currentRow][UPC_INDEX])){
   
                       // Check if the item's price and quantity are valid numbers before parsing
                       if (arrayInventory[currentRow][PRICE_INDEX] == null || (arrayInventory[currentRow][PRICE_INDEX].trim()).equals("") ||
                           arrayInventory[currentRow][QTY_INDEX] == null || (arrayInventory[currentRow][QTY_INDEX].trim()).equals("")) {
                           System.out.println("Error: Item data (price or quantity) is incomplete or invalid for UPC " + codeUPC);
                           itemFoundInCurrentScan = true; // Mark as found to prevent "item does not exist" message
                       }
                       else {
                           // print the name, current price & qty of item
                           System.out.printf("The item name is %s. The current price of the item is $%s\n", arrayInventory[currentRow][NAME_INDEX], arrayInventory[currentRow][PRICE_INDEX]);
                           scannedItems = scannedItems + arrayInventory[currentRow][NAME_INDEX] + ", ";
                           scannedPrice = scannedPrice + arrayInventory[currentRow][PRICE_INDEX] + ", ";
                           itemFoundInCurrentScan = true;
                           transactionMadeDuringCheckout = true; // An item was successfully scanned
                           itemIndex = currentRow; // Store the index of the found item
   
                           // check qty
                           try {
                               int currentQty = Integer.parseInt(arrayInventory[currentRow][QTY_INDEX]);
                               if (currentQty == 0){
                                   System.out.println("Inventory count is zero. Cannot sell this item.");
                               }
                               else {
                                   // reduce inv count by 1
                                   newQty = currentQty - 1;
                                   arrayInventory[currentRow][QTY_INDEX] = "" + (newQty); // Convert int back to String
                                   System.out.println("Remaining quantity: " + newQty);
                               }
                           }
                           catch (NumberFormatException e) {
                               System.out.println("Invalid quantity format for item " + arrayInventory[currentRow][NAME_INDEX] + ". Quantity not updated.");
                           }
   
                           // Calculate transaction
                           try{
                               subtotal += Double.parseDouble(arrayInventory[currentRow][PRICE_INDEX]);
                               System.out.printf("Current Subtotal is $%.2f.\n", subtotal);
                           }
                           catch (NumberFormatException e) {
                               System.out.println("Invalid price format for item " + arrayInventory[currentRow][NAME_INDEX] + ". Price not added to subtotal.");
                           }
   
                           // print all items scanned thus far
                           System.out.println(scannedItems);
                       }
                   }
                   currentRow++;
               } // end while (inner loop for finding item)
   
               if (!itemFoundInCurrentScan){ // If no item found after checking all rows for the current UPC
                   System.out.println("The item does not exist. Try again.");
               }
           }
       } // end while (continueScanning loop)
   
       // RUN AFTER SCANNING LOOP ENDS (only if items were scanned)
       if (transactionMadeDuringCheckout) { // Only print receipt and record if something was scanned
           System.out.printf("\nRECEIPT\n");
           // Check for how much HST would be on this
           taxes = subtotal * HST;
           totalPrice = subtotal * (1 + HST);
   
           System.out.printf("Subtotal: $%.2f\n", subtotal);
           System.out.printf("Amount of 13%% HST: $%.2f.\n", taxes);
           System.out.printf("Total price including 13%% HST: $%.2f.\n", totalPrice);
   
           // Safely print scanned items list
           if (scannedItems.length() > "The list of items is: ".length()) {
               System.out.println(scannedItems.substring(0, scannedItems.length() - 2)); // Remove trailing ", "
           }
           else {
               System.out.println("No items were successfully scanned.");
           }
   
           // Find the next available transaction slot and update arrayTransactions
           nextTransactionRow = -1; // Reset before finding
           i = 0; // Initialize loop counter
           while (i < MAX_SIZE && nextTransactionRow == -1) {
               if (arrayTransactions[i][TRANSACTION_INDEX] == null || (arrayTransactions[i][TRANSACTION_INDEX]).equals("")) {
                   nextTransactionRow = i;
               }
               i++;
           }
   
           if (nextTransactionRow != -1) {
               // Calculate new transaction number by finding the maximum existing transaction number
               maxTransactionNum = 0; // Reset before finding max
               transactionRowIterator = 0; // Initialize loop counter
               while (transactionRowIterator < MAX_SIZE) {
                   if (arrayTransactions[transactionRowIterator][TRANSACTION_INDEX] != null && !(arrayTransactions[transactionRowIterator][TRANSACTION_INDEX]).equals("")) {
                       try {
                           int currentTransNum = Integer.parseInt(arrayTransactions[transactionRowIterator][TRANSACTION_INDEX]);
                           if (currentTransNum > maxTransactionNum) {
                               maxTransactionNum = currentTransNum;
                           }
                       }
                       catch (NumberFormatException e) {
                           System.out.println("Warning: Invalid transaction number format found in arrayTransactions at row " + transactionRowIterator + ": " + arrayTransactions[transactionRowIterator][TRANSACTION_INDEX]);
                       }
                   }
                   transactionRowIterator++;
               }
               newTransaction = maxTransactionNum + 1;
   
               arrayTransactions[nextTransactionRow][TRANSACTION_INDEX] = "" + (newTransaction);
               arrayTransactions[nextTransactionRow][ID_INDEX] = employeeID;
   
               // Safely assign scannedItems and scannedPrice for the transaction record
               if (scannedItems.length() > "The list of items is: ".length()) {
                   arrayTransactions[nextTransactionRow][ITEMS_INDEX] = scannedItems.substring(0, scannedItems.length() - 2);
               }
               else {
                   arrayTransactions[nextTransactionRow][ITEMS_INDEX] = "";
               }
   
               if (scannedPrice.length() > 0) {
                   arrayTransactions[nextTransactionRow][ITEMPRICE_INDEX] = scannedPrice.substring(0, scannedPrice.length() - 2);
               }
               else {
                   arrayTransactions[nextTransactionRow][ITEMPRICE_INDEX] = "";
               }
   
               arrayTransactions[nextTransactionRow][SUBTOTAL_INDEX] = "" + (subtotal);
               arrayTransactions[nextTransactionRow][TAXES_INDEX] = "" + (taxes);
               arrayTransactions[nextTransactionRow][TOTAL_INDEX] = "" + (totalPrice);
   
               System.out.println("Transaction recorded successfully with Transaction # " + newTransaction);
           }
           else {
               System.out.println("Could not record transaction: Transaction history is full.");
           }
       }
       else {
           System.out.println("No items scanned for this checkout.");
       }
   }// end checkOut method
}//end of class