import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String driverClass = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverClass);
        Staff sta=new Staff();
        Room room=new Room();
        Customer cus=new Customer();
        Booking book=new Booking();
        Scanner input=new Scanner(System.in);
        room.checkDate();
        int choose ;
        do {
            System.out.print(" 1  Booking.\n");
            System.out.print(" 2  Room .\n");
            System.out.print(" 3  Customer.\n");
            System.out.print(" 4  Staff.\n");
            System.out.print(" 0  Exit.\n");
            System.out.print(" Enter : ");
            choose=input.nextInt();
            if (choose==1){
                int b;
                do {
                    System.out.println(" 1  Booking Room.");
                    System.out.println(" 2  Show List Booking.");
                    System.out.print(" 0  Exit.\n");
                    System.out.print("Enter : ");
                    b=input.nextInt();
                    if(b==1){
                        cus.inputCostomer("INSERT INTO customer(name,gender,age,phone,companyName)VALUES(?, ?, ?, ?,?);");
//                        cus.ShowCustomer("SELECT * FROM customer ORDER BY customerID DESC LIMIT 1;");
                        book.InputBooking();
                    }else if (b==2){

                        book.ShowBooking();
                    }
                }while (b>0);

            }else if(choose==2) {
                int ro;
                do {
                    System.out.print(" 1  Add new room.\n");
                    System.out.print(" 2  Update room.\n");
                    System.out.print(" 3  Delete room.\n");
                    System.out.print(" 4  Search room.\n");
                    System.out.print(" 5  Show room.\n");
                    System.out.print(" 0  Exit.\n");
                    System.out.print(" Enter : ");
                    ro=input.nextInt();
                    if (ro==1){
                        room.addRoom();
                    }else if (ro==2){
                        room.updateRoom();
                    }else if (ro==3){
                        room.deleteRoom();
                    }else if (ro==4){
                        room.searchRoom();
                    }else if (ro==5){
                        int r;
                        do {
                            System.out.println(" 1  show room available.");
                            System.out.println(" 2  show room booked.");
                            System.out.println(" 3  show all room .");
                            System.out.print(" 0  Exit.\n");
                            System.out.print("Enter : ");
                            r=input.nextInt();
                            switch (r){
                                case 1:
                                    room.ShowRoom("SELECT * FROM `room` WHERE roomDes LIKE \"Free\";");
                                    int a;
                                    do {
                                        System.out.println(" 1 Small Room.");
                                        System.out.println(" 2 Medium Room.");
                                        System.out.println(" 3 Large Room.");
                                        System.out.print(" 0  Exit.\n");
                                        System.out.print("Enter :");
                                        a=input.nextInt();
                                        if (a==1){
                                            room.ShowRoom("SELECT * FROM `room` WHERE roomType LIKE \"small\" AND roomDes LIKE \"Free\";");
                                        }else if (a==2){
                                            room.ShowRoom("SELECT * FROM `room` WHERE roomType LIKE \"medium\" AND roomDes LIKE \"Free\";");
                                        }else if (a==3){
                                            room.ShowRoom("SELECT * FROM `room` WHERE roomType LIKE \"large\" AND roomDes LIKE \"Free\";");
                                        }else if (a>3){
                                            System.out.println("Please Enter correct number!!!");
                                        }
                                    }while (a>0);
                                    break;
                                case 2:
                                    room.ShowRoom("SELECT * FROM `room` WHERE roomDes LIKE \"Rented\";");
                                    int z;
                                    do {
                                        System.out.println("Enter 1 Small Room.");
                                        System.out.println("Enter 2 Medium Room.");
                                        System.out.println("Enter 3 Large Room.");
                                        System.out.print(" 0  Exit.\n");
                                        System.out.print("Enter :");
                                        z=input.nextInt();
                                        if (z==1){
                                            room.ShowRoom("SELECT * FROM `room` WHERE roomType LIKE \"small\" AND roomDes LIKE \"Rented\";");
                                        }else if (z==2){
                                            room.ShowRoom("SELECT * FROM `room` WHERE roomType LIKE \"medium\" AND roomDes LIKE \"Rented\";");
                                        }else if (z==3){
                                            room.ShowRoom("SELECT * FROM `room` WHERE roomType LIKE \"large\" AND roomDes LIKE \"Rented\";");
                                        }else {
                                            System.out.println("Please Enter correct number!!!");
                                        }
                                    }while (z>0);
                                    break;
                                case 3:
                                    room.ShowRoom("SELECT * FROM `room`;");
                                case 0:
                                    break;
                            }

                        }while (r>0);
                    }else if (ro>5){
                        System.out.println("Please Enter correct number!!!");
                    }
                }while (ro>0);
            }else if (choose==3){
                int cu;
                do {
                    System.out.print(" 1  Update Customer.\n");
                    System.out.print(" 2  Search Customer.\n");
                    System.out.print(" 3  Show Customer.\n");
                    System.out.print(" 0  Exit.\n");
                    System.out.print(" Enter : ");
                    cu=input.nextInt();
                    if (cu==1){
                        cus.updateCustomer();
                    }else if (cu==2){
                        cus.searchCustomer();
                    }else if (cu==3){
                        cus.ShowCustomer("SELECT * FROM customer;");
                    }
                    else {
                        System.out.println("Please Enter correct number!!!");
                    }
                }while (cu>0);
            }else if (choose==4){
                int i;
                do {
                    System.out.print(" 1  Add new staff.\n");
                    System.out.print(" 2  Update staff.\n");
                    System.out.print(" 3  Delete staff.\n");
                    System.out.print(" 4  Search staff.\n");
                    System.out.print(" 5  Show staff.\n");
                    System.out.print(" 0  Exit.\n");
                    System.out.print(" Enter : ");
                    i=input.nextInt();
                    if (i==1){
                        sta.addStaff();
                    }else if(i==2){
                        sta.updateStaff();
                    }else if (i==3){

                        sta.deleteStaff();
                    }else if(i==4){
                        sta.searchStaff();
                    }else if (i==5){
                        sta.showStaff("SELECT * FROM staff;");
                    }else {
                        System.out.println("Please Enter correct number!!!");
                    }

                }while (i>0);
                System.out.println("=====================================");
                continue;
            }
            else {
                System.out.println("Please Enter correct number!!!");
            }
        }while (choose>=0);
        System.out.print("Thank You!");
    }
}