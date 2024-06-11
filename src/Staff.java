import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.sql.*;
import java.util.Scanner;

public class Staff {
    Scanner input=new Scanner(System.in);
    static String url = "jdbc:mysql://localhost:3306/hotel";
    static String username = "root";
    static String password = "";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    public  void showStaff(String sql){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = sql;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("========Staff Information========");
            System.out.println("----------------------------------------------------------------------------------------|");
            System.out.println("StaffID\t|\t\tName\t\t|\t Gender\t|\tDateOfBirth\t|\tPhone\t\t\t|\tAddress\t|");
            System.out.println("--------|-------------------|-----------|---------------|-------------------|-----------|");
            while (resultSet.next()) {
                int staffID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                Date dob = resultSet.getDate("dob");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                System.out.println("\t"+staffID+"\t|\t\t"+name+"\t\t|\t "+gender+"\t\t|\t"+dob+"\t|\t"+phone+"\t\t|\t"+address+"\t\t|");
                System.out.println("--------|-------------------|-----------|---------------|-------------------|-----------|");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public void addStaff(){
        System.out.println("==========Add Staff==========");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO staff(name,gender,dob,phone,address)VALUES(?, ?, ?, ?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("Enter Name: ");
            String name = input.nextLine();
            System.out.print("Enter Gender: ");
            String gender = input.nextLine();
            System.out.print("Enter Date Of Birth (YYYY-MM-DD): ");
            Date dob = Date.valueOf(input.nextLine());
            System.out.print("Phone: ");
            String phone = input.nextLine();
            System.out.print("Address : ");
            String address = input.nextLine();
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setDate(3, dob);
            statement.setString(4, phone);
            statement.setString(5, address);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("------------successfully------------");
            } else {
                System.out.println("------------Unsuccessfully------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //int userId, String name, String gender, String dob, String phone, String address
    public void updateStaff() {
        System.out.println("==========Update Staff==========");
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            String query = "UPDATE staff SET name=?, gender=?, dob=?, phone=?, address=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.print("Enter StaffID : ");
            int id=input.nextInt();
            input.nextLine();
            System.out.print("Enter New Name : ");
            String name = input.nextLine();
            System.out.print("Enter New Gender : ");
            String gender = input.nextLine();
            System.out.print("Enter New DOB (YYYY-MM-DD) : ");
            Date dob = Date.valueOf(input.nextLine());
            System.out.print("Enter New Phone : ");
            String phone = input.nextLine();
            System.out.print("Enter New Address : ");
            String address = input.nextLine();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setDate(3, dob);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            preparedStatement.setInt(6, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User information updated successfully.");
            } else {
                System.out.println("No user found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteStaff(){
        System.out.println("==========Delete Staff==========");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM staff WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("Enter StaffID : ");
            int id=input.nextInt();
            statement.setInt(1,id );
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("------Staff deleted successfully------");
            } else {
                System.out.println("------Not found with ID " + id+"------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void searchStaff() {
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
                    String query = "SELECT * FROM staff WHERE name LIKE ? ";

                    preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, Integer.parseInt(search));
                    preparedStatement.setString(1, "%" + search + "%");
                    resultSet = preparedStatement.executeQuery();
                    System.out.println("----------------------------------------------------------------------------------------|");
                    System.out.println("StaffID\t|\t\tName\t\t|\t Gender\t|\tDateOfBirth\t|\tPhone\t\t\t|\tAddress\t|");
                    System.out.println("--------|-------------------|-----------|---------------|-------------------|-----------|");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String gender = resultSet.getString("gender");
                        String dob = resultSet.getString("dob");
                        String phone = resultSet.getString("phone");
                        String address = resultSet.getString("address");
                        System.out.println("\t" + id + "\t|\t\t" + name + "\t\t|\t " + gender + "\t\t|\t" + dob + "\t|\t" + phone + "\t\t|\t" + address + "\t\t|");
                        System.out.println("--------|-------------------|-----------|---------------|-------------------|-----------|");

                    }
                    }else if (s==1) {
                    System.out.print("Enter ID to Search : ");
                    String search=input.nextLine();
                    String query = "SELECT * FROM staff WHERE id= ? ";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, Integer.parseInt(search));
                    resultSet = preparedStatement.executeQuery();
                    System.out.println("----------------------------------------------------------------------------------------|");
                    System.out.println("StaffID\t|\t\tName\t\t|\t Gender\t|\tDateOfBirth\t|\tPhone\t\t\t|\tAddress\t|");
                    System.out.println("--------|-------------------|-----------|---------------|-------------------|-----------|");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String gender = resultSet.getString("gender");
                        String dob = resultSet.getString("dob");
                        String phone = resultSet.getString("phone");
                        String address = resultSet.getString("address");
                        System.out.println("\t" + id + "\t|\t\t" + name + "\t\t|\t " + gender + "\t\t|\t" + dob + "\t|\t" + phone + "\t\t|\t" + address + "\t\t|");
                        System.out.println("--------|-------------------|-----------|---------------|-------------------|-----------|");

                    }
                }else {
                    System.out.println("Please Enter correct number!!!");
                }
            }while (s>0);
            System.out.println("Exit");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
