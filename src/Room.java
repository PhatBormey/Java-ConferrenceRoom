import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;


public class Room {
    Scanner input=new Scanner(System.in);
    public  String url = "jdbc:mysql://localhost:3306/hotel";
    public String username = "root";
    public String password = "";
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    public void ShowRoom(String sql){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = sql;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("========Room========");
            System.out.println("--------------------------------------------------------|");
            System.out.println("\tRoomID\t|\t RoomType\t|\tPrice\t|\tRoomDes\t\t|");
            System.out.println("------------|---------------|-----------|---------------|");
            while (resultSet.next()) {
                String roomID = resultSet.getString("room.roomID");
                String roomType = resultSet.getString("room.roomType");
                int price = resultSet.getInt("room.price");
                String roomDes = resultSet.getString("room.roomDes");
                System.out.println("\t"+roomID+"\t|\t"+roomType+"\t\t|\t"+price+"\t\t|\t"+roomDes+"\t\t|" );
                System.out.println("------------|---------------|-----------|---------------|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public void addRoom(){
        System.out.println("==========Add Room==========");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO room(roomID,roomType,price,roomDes)VALUES(?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("Enter RoomID : ");
            String roomID = input.nextLine();
            System.out.print("Enter Room Type : ");
            String roomType = input.nextLine();
            System.out.print("Enter Price: ");
            double price=input.nextDouble();
            input.nextLine();
            System.out.print("Enter RoomDes: ");
            String roomDes = input.nextLine();
            statement.setString(1, roomID);
            statement.setString(2, roomType);
            statement.setDouble(3, price);
            statement.setString(4, roomDes);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("------------successfully------------");
            } else {
                System.out.println("------------Unsuccessfully------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void updateRoomDes(String roomID,String sql) {
//        System.out.println("==========Udate Room==========");
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            String query =sql;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roomID);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println();
            } else {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void updateRoom() {
        System.out.println("==========Udate Room==========");
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            String query = "UPDATE room SET roomType=?, price=?, roomDes=? WHERE roomID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.print("Enter RoomID : ");
            String roomID=input.nextLine();
            System.out.print("Enter New RoomType : ");
            String roomType = input.nextLine();
            System.out.print("Enter New Price : ");
            double price = input.nextDouble();
            input.nextLine();
            System.out.print("Enter New RoomDes : ");
            String roomDes = input.nextLine();
            preparedStatement.setString(1, roomType);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, roomDes);
            preparedStatement.setString(4, roomID);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(" updated successfully.");
            } else {
                System.out.println("updated Unsuccessfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public void deleteRoom(){
        System.out.println("==========Delete Room==========");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM room WHERE roomID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("Enter RoomID : ");
            String roomID=input.nextLine();
            statement.setString(1,roomID );
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("------delete successfully------");
            } else {
                System.out.println("------Not found with ID " + roomID+"------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public  void searchRoom() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
             int s;
            do {
                System.out.println("Enter 1 to Search by Size");
                System.out.println("Enter 2 to Search by RoomID");
                System.out.println("Enter 0 to Exit");
                System.out.print("Enter : ");
                s=input.nextInt();
                input.nextLine();
                if (s==2){
                    System.out.print("Enter RoomID to Search : ");
                    String search=input.nextLine();
                    String query = "SELECT * FROM room WHERE roomID LIKE ? ";

                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, "%" + search + "%");
                    resultSet = preparedStatement.executeQuery();
                    System.out.println("--------------------------------------------------------|");
                    System.out.println("\tRoomID\t|\t RoomType\t|\tPrice\t|\tRoomDes\t\t|");
                    System.out.println("------------|---------------|-----------|---------------|");
                    while (resultSet.next()) {
                        String roomID = resultSet.getString("room.roomID");
                        String roomType = resultSet.getString("room.roomType");
                        int price = resultSet.getInt("room.price");
                        String roomDes = resultSet.getString("room.roomDes");
                        System.out.println("\t"+roomID+"\t|\t"+roomType+"\t\t|\t"+price+"\t\t|\t"+roomDes+"\t\t|" );
                        System.out.println("------------|---------------|-----------|---------------|");
                    }
                }else if (s==1) {
                    System.out.print("Enter Size to Search : ");
                    String search=input.nextLine();
                    String query = "SELECT * FROM room WHERE roomType LIKE ? ";

                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, "%" + search + "%");
                    resultSet = preparedStatement.executeQuery();
                    System.out.println("--------------------------------------------------------|");
                    System.out.println("\tRoomID\t|\t RoomType\t|\tPrice\t|\tRoomDes\t\t|");
                    System.out.println("------------|---------------|-----------|---------------|");
                    while (resultSet.next()) {
                        String roomID = resultSet.getString("room.roomID");
                        String roomType = resultSet.getString("room.roomType");
                        int price = resultSet.getInt("room.price");
                        String roomDes = resultSet.getString("room.roomDes");
                        System.out.println("\t"+roomID+"\t|\t"+roomType+"\t\t|\t"+price+"\t\t|\t"+roomDes+"\t\t|" );
                        System.out.println("------------|---------------|-----------|---------------|");
                    }
                }else {
                    System.out.println("Please Enter correct number!!!");
                }
            }while (s>0);
            System.out.println("Exit");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public void checkDate(){
        LocalDate now= LocalDate.now();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT roomID,bookingDate FROM `booking`;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String roomID = resultSet.getString("roomID");
                Date bookingDate=resultSet.getDate("bookingDate");
                int result=now.compareTo(bookingDate.toLocalDate());
                if (result>0){
                    updateRoomDes(roomID, "UPDATE room SET roomDes=\"Free\" WHERE roomID=?;");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

