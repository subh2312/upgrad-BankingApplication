import service.BankService;
import service.BankServiceImpl;

import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {
        int choice=0;
        int userChoice = 0;
        Scanner sc = new Scanner(System.in);
        BankService service = new BankServiceImpl();
        System.out.println("1. Already Have an Account");
        System.out.println("2. Open a new Account");
        System.out.println("0. to quit: \n");
        System.out.print("Enter Your Choice : ");
        userChoice=sc.nextInt();
        switch (userChoice){
            case 1:
                int count = 0;
                while(count<3){
                    System.out.println("Enter your account id : ");
                    Long id = sc.nextLong();
                    System.out.println("Enter your password : ");
                    String pass = sc.next();
                    if (validatePassword(service,id,pass)) {
                        while(choice != -1) {
                            printCommandTemplate();
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1://deposit money
                                    System.out.println("Enter amount to deposit : ");
                                    double deposit=sc.nextDouble();
                                    service.depositMoney(id,deposit);
                                    break;
                                case 2://withdraw money
                                    System.out.println("Enter amount to Withdraw : ");
                                    double withdraw=sc.nextDouble();
                                    service.withdrawMoney(id,withdraw);
                                    break;
                                case 3://check balance
                                    service.checkBalance(id);
                                    break;
                                case 4://display acc details
                                    service.displayAccountDetails(id);
                                    break;
                                case 5://display card details
                                    service.getCardDetails(id);
                                    break;
                                case 6://create new card
                                    service.createNewCard(id);
                                    break;
                                case 7://edit profile
                                    boolean editmenuquit = false;
                                    do {
                                        System.out.println("1. Change User Name");
                                        System.out.println("2. Change Account type");
                                        System.out.println("3. Change Card Pin");
                                        System.out.println("0. to quit: \n");

                                        int UserEditMenu = sc.nextInt();
                                        switch (UserEditMenu) {

                                            case 1:
                                                System.out.println("Enter new Name");
                                                String new_name = sc.next();
                                                System.out.println("Name Changed to " + service.update(id,"name",new_name));
                                                break;
                                            case 2:
                                                System.out.println("Enter new Account_type");
                                                String new_acc_type = sc.next();
                                                System.out.println("Account type Changed to " + service.update(id,"type",new_acc_type));
                                                break;
                                            case 3:
                                                boolean flag = true;
                                                do {
                                                    System.out.println("Enter new 4 digit Pin");
                                                    String new_pin = sc.next();
                                                    String regex = "[0-9]{4}";
                                                    if (new_pin.matches(regex)) {
                                                        System.out.println("Pin changed to " + service.update(id, "pin", new_pin));
                                                    } else {
                                                        flag = false;
                                                        System.out.println("Incorrect Pin. Please enter 4 digits between 0-9");
                                                    }
                                                }while(!flag);

                                                break;
                                            case 0:
                                                editmenuquit=true;
                                               break;
                                            default:
                                                System.out.println("Wrong Choice.");
                                                break;

                                        }
                                    } while (!editmenuquit);
                                    break;
                                case 8://View Transaction History
                                    service.viewTransactionHistory(id);
                                    break;
                                case -1:
                                    break;
                                default:
                                    System.out.println("Please enter a valid choice");
                                    break;
                            }
                        }
                        break;
                    }else{
                        System.out.println("incorrect password please try again");
                    }
                }

                break;
            case 2:
                System.out.print("Enter your Name : ");
                String name = sc.next();
                System.out.print("Enter Account Type : ");
                String type = sc.next();
                System.out.println("Enter a password to access your account");
                String password = sc.next();
                createAccount(service,name,type,password);
                break;

        }





    }

    private static boolean validatePassword(BankService service, Long id, String pass) {
        return service.validatePassword(id,pass);
    }


    private static void createAccount(BankService service,String name, String type,String password) {
        service.createAccount(name,type,(long) (Math.random() * 100000000000000L),password);
    }

    private static void printCommandTemplate() {
        System.out.println("1. Deposit money");
        System.out.println("2. Withdraw money");
        System.out.println("3. Check balance");
        System.out.println("4. Display Account Details");
        System.out.println("5. Display Card Details");
        System.out.println("6. Create New Card");
        System.out.println("7. Edit Profile");
        System.out.println("8. View Transaction History");
        System.out.println("0. to quit: \n");
        System.out.print("Enter Your Choice : ");
    }
}
