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
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Trasnfer Money";
        final String ACCOUNT_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Drop Existing Account";

        final String ERR_MSG = String.format("\t%s%s%s", COLOR_RED_BOLD,"%S",RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s", COLOR_GREEN_BOLD,"%s",RESET);

        String[][] customerDetails = new String[0][];
        
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

                    do{
                        valid = true;
                        System.out.print("\n\tName: ");
                        name = scanner.nextLine().strip();
                        if(name.isBlank()){
                            System.out.printf(ERR_MSG, "Account name can't be empty!\n");
                            valid = false;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if(!(Character.isLetter(name.charAt(i))||Character.isSpaceChar(name.charAt(i)))){
                                System.out.printf(ERR_MSG, "Invalid user name\n");
                                valid = false;
                                break;
                            }
                        }
                    }while(!valid);

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
                    newCustomerDetails[newCustomerDetails.length - 1][0] = random+"";
                    newCustomerDetails[newCustomerDetails.length - 1][1] = name;
                    newCustomerDetails[newCustomerDetails.length - 1][2] = initialDepo+"";

                    customerDetails = newCustomerDetails;

                    System.out.printf(SUCCESS_MSG, String.format("\n\tID:SDB-%05d, %s has been created sucessfully!\n", random, name));
                    System.out.print("\n\tDo you want to continue(Y/n)? ");
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;
                    

                default: System.exit(0);
            }

        }while(true);


    }
}