/* Kelly Wang ICS3U 2025-05-12

"SE Task"
*/

import java.util.*;
import java.lang.*;
import java.io.*;
public class SoftwareEngineeringTask{
   // Constant Declaration
   final String TRANSACTION_HISTORY = "CheckoutTransactionsFile.txt";
   final String INVENTORY = "InventoryFile.txt";
   final String EMPLOYEE = "EmployeeFile.txt";
   final int MAX_SIZE = 100; // maximum number of employees/transactions/inventory
   
   // Variable Decalration
   public static int[][] arrayTransactions = new int[MAX_SIZE][7];
   public static int[][] arrayEmployees = new int[MAX_SIZE][5];
   public static int[][] arrayInventory = new int[MAX_SIZE][4];
  public static void main(String[] args) {
      //main method
   }
   public static int login(){
   // will output 1/0/-1 for each corresponding thingy
   }
   public static 
   
   //file reader method (reads file and stores in an array)
   public static void readFile(String fileName){  
      String line = "";
      int count = 0;
      int storeCount = 0;
      // determine number of lines
      if(fileName.equals(TRANSACTION_HISTORY)){
         numLines = 7;
      }
      else if(fileName.equals(INVENTORY)){
         numLines = 4;
      }
      else if(fileName.equals(EMPLOYEE)){
         numLines = 5;
      }
      try{
         in = new BufferedReader (new FileReader(fileName));
         while(line != null){
            line = in.readLine();
            // determine which array to store the line read to
               if(fileName.equals(TRANSACTION_HISTORY)){
                  arrayTransactions[storeCount][count] = line;
               }
               else if(fileName.equals(INVENTORY)){
                  arrayEmployees[rows][cols] = line;
               }
               else if(fileName.equals(EMPLOYEE)){
                  arrayInventory[rows][cols] = line;
               }
               // determine if all parameters are read or not
            if(count% numLines == 0){
               // read the next line and store it in the column corresponding to the specific item/employee/transaction
               rows++;
            }
            cols++;
         }
         in.close();
      }
      catch(IOException e){
      }
   }
}

   
 