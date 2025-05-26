
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
      int choice = 0;
      int loginResult = 0;
      boolean isValid;
      
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
               int id = Integer.parseInt(employeeID);
               
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
               System.out.println("Employee IDs must be a 5-digit number. Please try again.");
            }   
         } while((!isValid) && !(employeeID.equalsIgnoreCase("quit")) && loginResult != 1 && loginResult != -1);  
         
         if(!(employeeID.equalsIgnoreCase("quit")))
         {  
            System.out.println("Please enter your PIN");
            employeePIN = sc.nextLine();
                        
            // perform checks on the ID and PIN
            // see if login works
            loginResult = login(employeeID, employeePIN);
            System.out.println("Login method result: " + loginResult);
                        
            if (loginResult == 0)
            {
               System.out.println("Login failed. Please try again.");
            } 
            else if (loginResult == 1)
            {
               System.out.println("Login successful (employee).");
               System.out.println("Select a number:\n1. Add Inventory\n2. Update Inventory\n3. Delete Inventory\n4. Check Inventory\n5. List all items\n6. List item\n7. Checkout");
               choice = sc.nextInt();
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
               }*/
            }
            else if (loginResult == -1)
            {
               System.out.println("Login successful (admin).");
               System.out.println("Select a number:\n1. Add Inventory\n2. Update Inventory\n3. Delete Inventory\n4. Check Inventory\n5. List all items\n6. List item\n7. Checkout\n8. Add an employee\n9. Edit an employee's details\n10. Activate/Deactivate an employee\n11. View all transactions\n12. View a transaction by transaction number");
               choice = sc.nextInt();
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
      }
   }  
   /*
   Name: login
   Return Type: int
   Parameters: String employeeID, String PIN
   Description: This method compares the entered employee IDs and PINs to the existing files
   Change:
   */
   
   public static int login(String employeeID, String employeePIN)
   {
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
           if (arrayEmployees[rows][0] != null && arrayEmployees[rows][0].equals(employeeID)) 
           {
               // Check if PIN cell is also not null before comparing
               if (arrayEmployees[rows][1] != null && arrayEmployees[rows][1].equals(employeePIN)) 
               {
                   loginType = 1; // successful login
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
   
   public static void writeFile(String fileName)
   {
      // Variable Declaration
      int numLines;
      try
      {
         BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
         // determine number of lines
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
         // loop through the array
         
      }
      catch(IOException e)
      {
      }      
   }
   
   /*
   Name: readFile
   Return Type: void
   Parameters: String fileName
   Description: Stores files that should be at the start of the main into 2D arrays
   Change:
   */
   public static void readFile(String fileName)
   {  
      // Variable Declaration
      int numLines = 0;
      String line = "";
      int rows = 0;
      int cols = 0;
      // determine number of lines
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
      try
      {
         BufferedReader in = new BufferedReader (new FileReader(fileName));
         while((line = in.readLine())!= null)
         {
            //line = in.readLine();
            // determine which array to store the line read to
               if(fileName.equals(TRANSACTION_HISTORY))
               {
                  arrayTransactions[rows][cols] = line;
               }
               else if(fileName.equals(INVENTORY))
               {
                  arrayInventory[rows][cols] = line;
               }
               else if(fileName.equals(EMPLOYEE))
               {
                  arrayEmployees[rows][cols] = line;
               }
               // determine if all parameters are read or not
            cols++;
            if(cols% numLines == 0)
            {
               // throw next line away
               in.readLine();
               // read the next line and store it in the column corresponding to the specific item/employee/transaction
               rows++;
               cols = 0;
            }            
         }
         in.close();
      }
      catch(IOException e)
      {
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
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it doesn’t match, the user will be prompted to enter the name, price, and quantity if the UPC code does not match anything in the current inventory. This will loop until Q is pressed to return to the main menu.
   Change:
   */
   public static void addInventory(String codeUPC)
   {
   
   }
   
   /*
   Name: updateInventory
   Return Type: void
   Parameters: int specificTransaction
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it matches, then it will prompt the user to enter the name of the item, current price, and current quantity, then prompt for new price and quantity. This will loop until Q is pressed to return to the main menu.
   Change:
   */
   public static void updateInventory(int specificTransaction)
   {
   
   }
   
   /*
   Name: delete
   Return Type: void
   Parameters: String upcCode
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it does match, then the user will be prompted for the name of the item to be deleted, removing it from the system, and then return to the main menu. If it doesn’t match a message that the item does not exist will be printed, then return to the main menu. 
   Change:
   */
   public static void delete(String upcCode)
   {

   }
   
   /*
   Name: checkInv
   Return Type: void
   Parameters: String upcCode
   Description: This method asks for an 8-digit UPC code, then checks and compares the code. If it exists the the name, current price, and current quantity of the item will be printed. If it doesn’t match a message that the item does not exist will be printed. This will loop until Q is pressed to return to the main menu.
   Change:
   */
   public static void checkInv(String upcCode)
   {

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

   }
   
   /*
   Name: checkOut
   Return Type: void
   Parameters: String upc
   Description: This method prompts the user to input the UPC for items to be purchased, keeping a total of the price. If a UPC does not exist, an error is printed. If it does, the name, price, and total items scanned are displayed. If 'Q' is entered, a receipt is printed, outlining the transaction number, item names, and price. Inventory counts are reduced as items are purchased.
   Change:
   */
   public static void checkOut(String upc)
   {

   }
}