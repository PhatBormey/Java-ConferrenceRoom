import java.sql.*;
import java.util.Scanner;

public class Customer {
    Scanner input=new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/hotel";
    String username = "root";
    String password = "";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    public void inputCostomer(String sql){
        System.out.println("==========Add Customer==========");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = sql;
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("Enter Name: ");
            String name = input.nextLine();
            System.out.print("Enter Gender: ");
            String gender = input.nextLine();
            System.out.print("Enter Age: ");
            int age = input.nextInt();
            input.nextLine();
            System.out.print("Phone: ");
            String phone = input.nextLine();
            System.out.print("Company : ");
            String companyName = input.nextLine();
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setInt(3, age);
            statement.setString(4, phone);
            statement.setString(5, companyName);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(" ");
            } else {
                System.out.println("Error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int inputCusID( ){
        int customerID = 0;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = " SELECT customerID FROM customer ORDER BY customerID DESC LIMIT 1;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                customerID = resultSet.getInt("customerID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerID;
    }
    public void ShowCustomer(String sql){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = sql;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("========Customer Information========");
            System.out.println("------------------------------------------------------------------------------------|");
            System.out.println("CustomerID\t|\t\tName\t\t|\t Gender\t|\tAge\t|\tPhone\t\t\t|\tCompany\t|");
            System.out.println("------------|-------------------|-----------|-------|-------------------|-----------|");
            while (resultSet.next()) {
                int customerID = resultSet.getInt("customerID");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                String companyName = resultSet.getString("companyName");
                System.out.println("\t"+customerID+"\t\t|\t"+name+"\t\t\t|\t "+gender+"\t\t|\t"+age+"\t|\t"+phone+"\t\t|\t"+companyName+"\t|");
                System.out.println("------------|-------------------|-----------|-------|-------------------|-----------|");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void updateCustomer() {
        System.out.println("==========Update Customer==========");
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            String query = "UPDATE customer SET name=?, gender=?,age=?,phone=?,companyName=? WHERE customerID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.print("Enter CustomerID : ");
            int customerID=input.nextInt();
            input.nextLine();
            System.out.print("Enter New Name : ");
            String name = input.nextLine();
            System.out.print("Enter New Gender : ");
            String gender = input.nextLine();
            System.out.print("Enter New Age : ");
            int age=input.nextInt();
            input.nextLine();
            System.out.print("Enter New Phone : ");
            String phone = input.nextLine();
            System.out.print("Enter New Company : ");
            String companyName = input.nextLine();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, companyName);
            preparedStatement.setInt(6,customerID );
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("============ successfully===========");
            } else {
                System.out.println("Not found with the ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void deleteCustomer(){
        System.out.println("==========Delete Customer==========");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM customer WHERE customerID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("Enter CustomerID : ");
            int customerID=input.nextInt();
            statement.setInt(1,customerID );
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("------Customer deleted successfully------");
            } else {
                System.out.println("------Not found with ID " + customerID+"------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void searchName(String search){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
                    String query = "SELECT * FROM customer WHERE name LIKE ? ";
                    preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, Integer.parseInt(search));
                    preparedStatement.setString(1, "%" + search + "%");
                    resultSet = preparedStatement.executeQuery();
            if (search.length()>0){
                int se;System.out.println("------------------------------------------------------------------------------------|");
                System.out.println("CustomerID\t|\t\tName\t\t|\t Gender\t|\tAge\t|\tPhone\t\t\t|\tCompany\t|");
                System.out.println("------------|-------------------|-----------|-------|-------------------|-----------|");
                while (resultSet.next()) {
                    int costomerID = resultSet.getInt("customerID");
                    String name = resultSet.getString("name");
                    String gender = resultSet.getString("gender");
                    int age = resultSet.getInt("age");
                    String phone = resultSet.getString("phone");
                    String companyName = resultSet.getString("companyName");
                    System.out.println("\t"+costomerID+"\t\t|\t"+name+"\t\t\t|\t "+gender+"\t\t|\t"+age+"\t|\t"+phone+"\t\t|\t"+companyName+"\t|");
                    System.out.println("------------|-------------------|-----------|-------|-------------------|-----------|");
                }
                do {
                    System.out.println("1 Update ");
                    System.out.println("2 Add new ");
                    System.out.print("Enter : ");
                    se=input.nextInt();
                    input.nextLine();
                    if (se==1){
                        updateCustomer();
                        break;
                    }else if (se==2){

                    }else {
                        System.out.print("Please Enter correct number!");
                    }
                }while (se>0);
            }else {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void searchCustomer() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            int s;
            do {
                System.out.println("Enter 1 to Search by ID");
                System.out.println("Enter 2 to Search by Name");
                System.out.println("Enter 0 to Exit");
                System.out.print("Enter : ");
                s=input.nextInt();
                input.nextLine();
                if (s==2){
                    System.out.print("Enter Name to Search : ");
                    String search=input.nextLine();
                    String query = "SELECT * FROM customer WHERE name LIKE ? ";
                    preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, Integer.parseInt(search));
                    preparedStatement.setString(1, "%" + search + "%");
                    resultSet = preparedStatement.executeQuery();
                    System.out.println("------------------------------------------------------------------------------------|");
                    System.out.println("CustomerID\t|\t\tName\t\t|\t Gender\t|\tAge\t|\tPhone\t\t\t|\tCompany\t|");
                    System.out.println("------------|-------------------|-----------|-------|-------------------|-----------|");
                    while (resultSet.next()) {
                        int costomerID = resultSet.getInt("customerID");
                        String name = resultSet.getString("name");
                        String gender = resultSet.getString("gender");
                        int age = resultSet.getInt("age");
                        String phone = resultSet.getString("phone");
                        String companyName = resultSet.getString("companyName");
                        System.out.println("\t"+costomerID+"\t\t|\t"+name+"\t\t\t|\t "+gender+"\t\t|\t"+age+"\t|\t"+phone+"\t\t|\t"+companyName+"\t|");
                        System.out.println("------------|-------------------|-----------|-------|-------------------|-----------|");

                    }
                }else if (s==1) {
                    System.out.print("Enter ID to Search : ");
                    String search=input.nextLine();
                    String query = "SELECT * FROM customer WHERE customerID= ? ";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, Integer.parseInt(search));
                    resultSet = preparedStatement.executeQuery();
                    System.out.println("------------------------------------------------------------------------------------|");
                    System.out.println("CustomerID\t|\t\tName\t\t|\t Gender\t|\tAge\t|\tPhone\t\t\t|\tCompany\t|");
                    System.out.println("------------|-------------------|-----------|-------|-------------------|-----------|");
                    while (resultSet.next()) {
                        int costomerID = resultSet.getInt("customerID");
                        String name = resultSet.getString("name");
                        String gender = resultSet.getString("gender");
                        int age = resultSet.getInt("age");
                        String phone = resultSet.getString("phone");
                        String companyName = resultSet.getString("companyName");
                        System.out.println("\t"+costomerID+"\t\t|\t"+name+"\t\t\t|\t "+gender+"\t\t|\t"+age+"\t|\t"+phone+"\t\t|\t"+companyName+"\t|");
                        System.out.println("------------|-------------------|-----------|-------|-------------------|-----------|");

                    }
                }else {
                    System.out.print("Please Enter correct number!");
                }
            }while (s>0);
            System.out.println("Exit");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
