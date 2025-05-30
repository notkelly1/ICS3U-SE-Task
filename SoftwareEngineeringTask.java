/*Lauren's update*/
import java.util.*;
import java.lang.*;
import java.io.*;
public class SoftwareEngineeringTask
{
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
   
   // Variable Decalration
   public static String[][] arrayTransactions = new String[MAX_SIZE][TRANSACTION_PARAMETERS];
   public static String[][] arrayEmployees = new String[MAX_SIZE][EMPLOYEE_PARAMETERS];
   public static String[][] arrayInventory = new String[MAX_SIZE][INVENTORY_PARAMETERS];
   
   // main method
   public static void main(String[] args)
   {
      // Variable Declaration
      String employeeID = "";
      String employeePIN = "";
      String choice = "";
      String userInput = "";
      int loginResult = 0;
      int inputCheckNum;
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
      while(!(employeeID.equalsIgnoreCase("quit")) && loginResult != 1 && loginResult != -1)
      {
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
         
         if(!(employeeID.equalsIgnoreCase("quit")))
         {  
            System.out.println("Please enter your PIN");
            employeePIN = sc.nextLine();
                        
            // perform checks on the ID and PIN
            // DELETE LATER!!!! see if login works 
            loginResult = login(employeeID, employeePIN);
            System.out.println("Login method result: " + loginResult);
                        
            if (loginResult == 0)
            {
               System.out.println("Login failed. Please try again. (Invalid Login Attempt)");
            } 
            else if (loginResult == 1)
            {
               System.out.println("Login successful (employee).");
               
               // MENU START (LOOP UNTIL QUIT)
               
               while(!exitMenuLoop){
                  System.out.println("Select a number:\n1. Add Inventory\n2. Update Inventory\n3. Delete Inventory\n4. Check Inventory\n5. List all items\n6. List item\n7. Checkout");
                  choice = sc.nextLine();
                  
                  // switch case for menu 
                  switch (choice)
                  {
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
                        // UNCOMMENT THIS WHEN I FIGURE OUT HOW TO MAKE CHECKOUT METHOD
                        checkOut(employeeID);
                        exitMenuLoop = true;
                        break;
                  }
               }
            }
            else if (loginResult == -1)
            {
               System.out.println("Login successful (admin).");
               System.out.println("Select a number:\n1. Add Inventory\n2. Update Inventory\n3. Delete Inventory\n4. Check Inventory\n5. List all items\n6. List item\n7. Checkout\n8. Add an employee\n9. Edit an employee's details\n10. Activate/Deactivate an employee\n11. View all transactions\n12. View a transaction by transaction number");
               choice = sc.nextLine();
               switch (choice)
                  {
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
                        // UNCOMMENT THIS WHEN I FIGURE OUT HOW TO MAKE CHECKOUT METHOD
                        checkOut(employeeID);
                        exitMenuLoop = true;
                        break;
                  }

               /*switch (choice)
               {
                  case 1:
                     
                     addInventory(String codeUPC);
                     break;
                     
                  case 2:
                  
                     updateInventory(int specificTransaction);
                     break;
                     
                  case 3:
                  
                     delete(String upcCode);
                     break;
                     
                  case 4:
                  
                     checkInv(String upcCode);
                     break;
                  
                  case 5:
                  
                     listAll();
                     break;
                     
                  case 6:
                  
                     listItem(String itemName);
                     break;
                  
                  case 7:
                  
                     checkOut(String upc);
                     break;
                  
                  case 8:
                  
                     addEmployee(String employeeID);
                     break;
                  
                  case 9:
                  
                     editDetails(String employeeID);
                     break;
                     
                  case 10:
                  
                     changeEmployeeStatus(String employeeID);
                     break;
                     
                  case 11:
                  
                     viewAllTransactions();
                     break;
                     
                  case 12:
                  
                     viewTransactions(int specificTransaction);
                     break;
               }*/
            }                  
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
   // END OF MAIN METHOD
     
   /*
   Name: login
   Return Type: int
   Parameters: String employeeID, String PIN
   Description: This method compares the entered employee IDs and PINs to the existing files
   Change:
   */
   
   public static int login(String employeeID, String employeePIN)
   {
       
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
      if(fileName.equals(TRANSACTION_HISTORY))
      {
         numLines = TRANSACTION_PARAMETERS;
      }
      else if(fileName.equals(INVENTORY))
      {
         numLines = INVENTORY_PARAMETERS;
      }
      else if(fileName.equals(EMPLOYEE))
      {
         numLines = EMPLOYEE_PARAMETERS;
      }
   
      try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName)))  // try-with-resources for safe closing
      {
         for(int rows = 0; rows < MAX_SIZE; rows++)
         {
            for(int cols = 0; cols < numLines; cols++)
            {
               if(fileName.equals(TRANSACTION_HISTORY))
               {
                  String currentValue = arrayTransactions[rows][cols]; //ADDITIONAL CODE
                  if(currentValue == null || currentValue.equalsIgnoreCase("null")) //ADDITIONAL CODE
                  {
                     currentValue = ""; //ADDITIONAL CODE
                  }
                  out.write(currentValue + "\n"); //ADDITIONAL CODE
               }
               else if(fileName.equals(EMPLOYEE))
               {
                  String currentValue = arrayEmployees[rows][cols]; //ADDITIONAL CODE
                  if(currentValue == null || currentValue.equalsIgnoreCase("null")) //ADDITIONAL CODE
                  {
                     currentValue = ""; //ADDITIONAL CODE
                  }
                  out.write(currentValue + "\n"); //ADDITIONAL CODE
               }
               else if(fileName.equals(INVENTORY))
               {
                  String currentValue = arrayInventory[rows][cols]; //ADDITIONAL CODE
                  if(currentValue == null || currentValue.equalsIgnoreCase("null")) //ADDITIONAL CODE
                  {
                     currentValue = ""; //ADDITIONAL CODE
                  }
                  out.write(currentValue + "\n"); //ADDITIONAL CODE
               }
            }
            out.write("***\n");
         }
      }
      catch(IOException e)
      {
         // Intentionally left blank
      }
      System.out.println("written");
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
               //line = line.trim();
   
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
   Parameters: employeeID
   Description: This method will assign the next available Employee ID number and prompt the administrator to provide: 
   the PIN, first name, last name, title and status of the new employee, updating all information to their respective arrays
   Change:
   */
   public static void addEmployee(String employeeID)
   {
   
   }
   /*
   Name: editDetails
   Return Type: void
   Parameters: String employeeID
   Description: This method prompts the administrator for the employee ID to edit and then checks and compares if it exists. If it matches, the system should allow the administrator to change the PIN, First name, Last name, and Title; if not, an error message will be printed.
   Change:
   */
   public static void editDetails(String employeeID)
   {

   }
   
   /*
   Name: changeEmployeeStatus
   Return Type: void
   Parameters: String employeeID
   Description: This method changes the status of an employee in arrayStatus
   Change:
   */
   public static void changeEmployeeStatus(String employeeID)
   {

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
   
   }
   
   /*
   Name: viewTransactions
   Return Type: void
   Parameters: int specificTransaction
   Description: This method asks the user for the transaction they want to view, then reads the specific transaction from the CheckoutTransactionsFile, only printing the specific transaction
   Change:
   */
   public static void viewTransactions(int specificTransaction)
   {

   }
   
   /*
   Name: addInventory
   Return Type: void
   Parameters: String codeUPC
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it doesnt match, the user will be prompted to enter the name, price, and quantity if the UPC code does not match anything in the current inventory. This will loop until Q is pressed to return to the main menu.
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
         if(codeUPC.equals(arrayInventory[rows][UPC_INDEX])){
            // check (delete later)
            System.out.println("UPC code exists in array!");
            // try again in main method
            // once the return is reached, the rest of the method doesn't run.
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
         if(codeUPC.trim().equals(arrayInventory[rows][UPC_INDEX])){
         //if (codeUPC.trim().equals(arrayInventory[rows][UPC_INDEX].trim())){
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
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it does match, then the user will be prompted for the name of the item to be deleted, removing it from the system, and then return to the main menu. If it doesnt match a message that the item does not exist will be printed, then return to the main menu. 
   Change:
   */
   public static void delete(String codeUPC)
   {
      // Constant Declaration
      final int UPC_INDEX = 1;
      final int NAME_INDEX = 0;
      
      // Variable Declaration
      //String itemName = "";
      //boolean itemExists = false;
      
      // Creating Scanner
      Scanner sc = new Scanner(System.in);
      // checks codeUPC against all values in the inventory array
      
      // check the array for codeUPC
      for(int rows = 0; rows < MAX_SIZE; rows++){
         if(codeUPC.equals(arrayInventory[rows][UPC_INDEX])){
            // the item exists
            System.out.println("UPC code exists in array!");
            System.out.print("Please input the name of the item: ");
            
            //itemName = sc.nextLine();
            if(arrayInventory[rows][NAME_INDEX].equals(sc.nextLine())){
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
            //itemExists = true;
            return;            
         }         
      }
      
      
      // if statement to check
      // if it exists: Prompt user for name of item to delete
      /*if(itemExists){
         System.out.print("Please input the name of the item: ");
         itemName = sc.nextLine();
      }
      // if it does not exist: print item does not exist
      else{*/
         System.out.print("Item does not exist.");
      }              
   
   /*
   Name: checkInv
   Return Type: void
   Parameters: String upcCode
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it exists the the name, current price, and current quantity of the item will be printed. If it doesnt match a message that the item does not exist will be printed. This will loop until Q is pressed to return to the main menu.
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
         if(codeUPC.equals(arrayInventory[rows][UPC_INDEX])){
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
      // constant declaration
      final int PRICE_INDEX = 2;
      
      //print the array (INVENTORY)
      for(int rows = 0; rows < MAX_SIZE; rows++){
         for(int cols = 0; cols < PRICE_INDEX; cols++){
            System.out.print(arrayInventory[rows][cols] + " ");
            }
            System.out.println("\n");
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
         if(itemName.equals(arrayInventory[rows][NAME_INDEX])){
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
   public static void checkOut(String employeeID)
   {
      // Constant Declaration
      final int NAME_INDEX = 0;
      final int UPC_INDEX = 1;
      final int PRICE_INDEX = 2;
      final int QTY_INDEX = 3;
      
      // Variable Declaration
      String scannedItems = "The list of items is: ";
      String codeUPC = "";
      double subtotal = 0;  
      int newQty = 0; 
      boolean itemFound = false;
      
      // Creating Scanner
      Scanner sc = new Scanner(System.in);   
      
      do{
      // prompt user for a UPC
      System.out.print("Please Enter a UPC: ");
         codeUPC = sc.nextLine();
         
         // perform check to see if UPC exists
            for(int rows = 0; rows < MAX_SIZE && !itemFound; rows++){
               if(codeUPC.equals(arrayInventory[rows][UPC_INDEX])){
                  
                  // print the name, current price & qty of item
                  System.out.printf("The item name is %s. The current price of the item is $%s\n", arrayInventory[rows][NAME_INDEX], arrayInventory[rows][PRICE_INDEX]);
                  scannedItems = scannedItems + arrayInventory[rows][NAME_INDEX] + ", ";
                  itemFound = true;
                  
                  // check qty
                  if(Integer.parseInt(arrayInventory[rows][QTY_INDEX]) == 0){
                     System.out.println("Inventory count kept at zero.");
                  }
                  else{
                     // reduce inv count by 1
                     try{
                        newQty =  Integer.parseInt(arrayInventory[rows][QTY_INDEX]) - 1;
                        arrayInventory[rows][QTY_INDEX] = ("" + newQty);
                     } 
                     catch (NumberFormatException e) {
                         System.out.println("Invalid quantity format at row " + rows);
                     }
                  }
                  
                  // Calculate transaction
                  try{
                     subtotal += Double.parseDouble(arrayInventory[rows][PRICE_INDEX]);
                     // subtotal check
                     System.out.printf("Current Subtotal is $%.2f.\n", subtotal);
                     
                  }
                  catch (NumberFormatException e) {
                         System.out.println("Invalid price format at row " + rows);
                     }
      
                  // print all items scanned thus far
                  System.out.println(scannedItems);
               }
         } // end of for     
         if (!itemFound){
            System.out.println("The item does not exist. Try again.");
         }
      }
      // loop until 'q' is entered 
      while(!(codeUPC.equalsIgnoreCase("q")));
         
         // if statement
            // error if UPC does not exist
            // if it exists the name and price of the item should be displayed, along with a total of all items that have been scanned thus far.  
                           
               // if statement to check if the qty = 0 
               // reduce inventory count by 1 if qty > 0 (change global array)
               // calculate transaction
               // add the item name to the string 

      // end loop
      // record transactions

   }
}//end of class