import java.util.Scanner;

public class BankingApp {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String RESET = "\033[0m";
        
        final String DASHBOARD = "💰 Welcome to Smart Banking App"; 
        final String NEW_ACCOUNT = "➕ Open New Account";
        final String DEPOSIT_MONEY = "📥 Deposit Money";
        final String WITHDRAW_MONEY = "📤 Withdraw Money";
        final String TRANSFER_MONEY = "💸 Trasnfer Money";
        final String ACCOUNT_BALANCE = "🖨 Check Account Balance";
        final String DELETE_ACCOUNT = "❌ Drop Existing Account";

        final String ERR_MSG = String.format("\t%s%s%s", COLOR_RED_BOLD,"%s",RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s", COLOR_GREEN_BOLD,"%s",RESET);

        // String[][] customerDetails = new String[0][];
        String[][] customerDetails = {{"01234","lakith","25000"},
                                       {"02237","rathnayake","2540"},
                                    {"12345","rathnayake","4500"} };
        
        String screen = DASHBOARD;

        do{
            System.out.println(CLEAR);
            final String APP_TITLE = String.format("%s%s%s", COLOR_BLUE_BOLD, screen, RESET);
            
            System.out.println("\t" + APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD:
                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Trasfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Drop Existing Account");
                    System.out.println("\t[7]. Exit");
                    System.out.println();
                    System.out.print("\tEnter an option to continue: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch(option){
                        case 1: screen = NEW_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = ACCOUNT_BALANCE; break;
                        case 6: screen = DELETE_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;
                
                case NEW_ACCOUNT:

                    boolean valid;
                    String name;
                    int random = 0;
                    double initialDepo;
                    random = (int)Math.floor(Math.random()*10000);
                    System.out.printf("\tID:   SDB-%05d\n",random);

                    name = getUserInput("name");

                    do{
                        valid = true;
                        System.out.print("\n\tInitial Deposit: Rs. ");
                        initialDepo = scanner.nextDouble();
                        scanner.nextLine();
                        if(initialDepo<5000) {
                            System.out.printf(ERR_MSG, "\n\tInitial deposit should be greater than Rs.5000.00\n");
                            System.out.print("\n\tDo you wish to continue(Y/n)? ");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) {valid = false;continue;}
                            screen = DASHBOARD;
                            break;
                        }
                    }while(!valid);

                    String[][] newCustomerDetails = new String[customerDetails.length + 1][3];

                    for (int i = 0; i < customerDetails.length; i++) {
                        newCustomerDetails[i] = customerDetails[i];
                    }
                    newCustomerDetails[newCustomerDetails.length - 1][0] = (random+"").length() == 5 ? (random+"") : "0".repeat(5-(random+"").length())+random;
                    newCustomerDetails[newCustomerDetails.length - 1][1] = name;
                    newCustomerDetails[newCustomerDetails.length - 1][2] = initialDepo+"";

                    customerDetails = newCustomerDetails;

                    System.out.printf(SUCCESS_MSG, String.format("\n\tID:SDB-%05d, %s has been created sucessfully!\n", random, name));
                    System.out.print("\n\tDo you want to continue(Y/n)? ");
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                case DEPOSIT_MONEY:
                    
                    boolean valid2;
                    double depoAmount;
                    int value = checkAccountNumber(customerDetails,"value");

                    System.out.printf("\tCurrent account balance: Rs: %s\n", customerDetails[value][2]);
                    do{
                        valid2 = true;
                        System.out.print("\n\tDeposit amount: Rs. ");
                        depoAmount = scanner.nextDouble();
                        scanner.nextLine();
                        if(depoAmount<500) {
                            System.out.printf(ERR_MSG, "\n\tDeposit amount should be greater than Rs.500.00\n");
                            System.out.print("\n\tDo you wish to continue(Y/n)? ");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) {valid2 = false;continue;}
                            screen = DASHBOARD;
                            break;
                        }
                    }while(!valid2);

                    customerDetails[value][2] = (depoAmount + Double.parseDouble(customerDetails[value][2]))+"";

                    System.out.printf("\tNew account balance: Rs: %s\n", customerDetails[value][2]);
                    System.out.print("\n\tDo you want to continue(Y/n)? ");
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                case WITHDRAW_MONEY:
                    boolean valid3;
                    double withdraw;
                    int withIndex = checkAccountNumber(customerDetails,"value");
                    System.out.printf("\tCurrent account balance: Rs: %s\n", customerDetails[withIndex][2]);
                    do{
                        valid3 = true;
                        System.out.print("\n\tWithdraw amount: Rs. ");
                        withdraw = scanner.nextDouble();
                        scanner.nextLine();
                        if(withdraw<100) {
                            System.out.printf(ERR_MSG, "\n\tWithdraw amount can't be less than Rs.100.00\n");
                            System.out.print("\n\tDo you wish to continue(Y/n)? ");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) {valid3 = false;continue;}
                            screen = DASHBOARD;
                            break;
                        } else if(Double.parseDouble(customerDetails[withIndex][2]) - withdraw < 500){
                            System.out.printf(ERR_MSG, "\n\tShould maintain minimum ammount of Rs.500.00 at the account\n");
                            System.out.print("\n\tDo you wish to continue(Y/n)? ");
                            if(scanner.nextLine().strip().toUpperCase().equals("Y")) {valid3 = false;continue;}
                            screen = DASHBOARD;
                            break;
                        }

                    }while(!valid3);
                    customerDetails[withIndex][2] = (Double.parseDouble(customerDetails[withIndex][2]) - withdraw)+"";
                    System.out.printf("\tNew account balance: Rs: %s\n", customerDetails[withIndex][2]);
                    System.out.print("\n\tDo you want to continue(Y/n)? ");
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;


                default: System.exit(0);
            }

        }while(true);


    }

    public static String getUserInput(String input) {
        boolean valid;
        String value;

        do{
            valid = true;
            System.out.print("\n\tName: ");
            value = scanner.nextLine().strip();
            if(value.isBlank()){
                System.out.printf(returnStatus("ERR_MSG"), "Account name can't be empty!\n");
                valid = false;
            }
            for (int i = 0; i < value.length(); i++) {
                if(!(Character.isLetter(value.charAt(i))||Character.isSpaceChar(value.charAt(i)))){
                    System.out.printf(returnStatus("ERR_MSG"), "Invalid user name\n");
                    valid = false;
                    break;
                }
            }
            
        }while(!valid);

        return value;
    } 

    public static String returnStatus(String input){

        String value = "";
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String RESET = "\033[0m";

        final String ERR_MSG = String.format("\t%s%s%s", COLOR_RED_BOLD,"%S",RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s", COLOR_GREEN_BOLD,"%s",RESET);

        if(input.equals("ERR_MSG")) value = ERR_MSG;
        else if(input.equals("SUCCESS_MSG")) value =  SUCCESS_MSG;
        return value;
    }

    public static int checkAccountNumber(String[][] array,String input) {
        boolean valid;
        String value;
        int j;

        loop:
        do{
            j = 0;
            valid = true;
            System.out.print("\n\tEnter A/C No: ");
            value = scanner.nextLine().strip();
            if(value.isBlank()){
                System.out.printf(returnStatus("ERR_MSG"), "A/C No can't be empty!\n");
                valid = false;
            }
            else if(!(value.startsWith("SDB-") && value.length() == 9)){
                System.out.printf(returnStatus("ERR_MSG"),"Invalid format\n");
                valid = false;
            }
            else if(valid==true){
                for (int i = j; i < array.length; i++) {
                    if(array[i][0].equals(value.substring(4))){
                        // System.out.println("Found");
                        valid = true;
                        j = i;
                        break loop;
                    }
                }
                System.out.printf(returnStatus("ERR_MSG"), "A/C No is not found!\n");
                valid = false;
            }
            

        }while(!valid);

        return j;
    }

   
}