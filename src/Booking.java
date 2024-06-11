import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Booking {
    Scanner input=new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/hotel";
    String username = "root";
    String password = "";
    public void ShowBooking(){

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT booking.bookingID, room.roomID, room.roomType, customer.name, customer.companyName, customer.phone,\n" +
                    "       booking.date_submit, booking.bookingDate, booking.prepaid, staff.id\n" +
                    "FROM booking\n" +
                    "INNER JOIN staff ON booking.staffID = staff.id\n" +
                    "INNER JOIN room ON room.roomID = booking.roomID\n" +
                    "INNER JOIN customer ON customer.customerID = booking.customerID\n" +
                    "ORDER BY booking.bookingID ASC, room.roomID, room.roomType, customer.name, customer.companyName, customer.phone,\n" +
                    "         booking.date_submit, booking.bookingDate, booking.prepaid, staff.id\n" +
                    "LIMIT 0, 25;"; // Replace with your desired query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("========Booked Room========");
            System.out.println("--------------------------------------------------------------------------------------------------------------" +
                    "-----------------------------------------------");
            System.out.println("BookingID\t|\tRoomID\t|\t RoomType\t|\tCustomerName\t|\tCompany\t\t|\tCustomerPhone" +
                    "\t|\tPrePaid\t\t|\tBookingDate\t|\tStaffID\t|\tDate\t\t|");
            System.out.println("------------|-----------|---------------|-------------------|---------------|-------------------|--------------" +
                    "-|---------------|-----------|---------------|");
            while (resultSet.next()) {
                int bookingID = resultSet.getInt("booking.bookingID");
                String roomID = resultSet.getString("room.roomID");
                String roomType = resultSet.getString("room.roomType");
                String customerName = resultSet.getString("customer.name");
                String companyName = resultSet.getString("customer.companyName");
                String customerPhone = resultSet.getString("customer.phone");
                String prePaid = resultSet.getString("booking.prepaid");
                String bookingDate = resultSet.getString("booking.bookingDate");
                int staffID = resultSet.getInt("staff.id");
                String sumbitDate = resultSet.getString("booking.date_submit");
                System.out.println("\t"+bookingID+"\t\t|\t"+roomID+"\t|\t"+roomType+"\t\t|\t"+customerName+"\t\t\t|\t"+companyName+"\t\t|\t"+customerPhone+
                        "\t\t|\t"+prePaid+"\t\t\t|\t"+bookingDate+"\t|\t"+staffID+"\t\t|\t"+sumbitDate+"\t|" );
                System.out.println("------------|-----------|---------------|-------------------|---------------|-------------------|--------------" +
                        "-|---------------|-----------|---------------|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    public void InputBooking(){
        Room obj=new Room();
        Customer cus=new Customer();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query ="INSERT INTO booking(staffID,customerID,roomID,bookingDate,date_submit,prepaid) VALUES(?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("Enter StaffID: ");
            int staffID = input.nextInt();
//            cus.inputCusID();
            input.nextLine();
            System.out.print("Enter RoomID: ");
            String roomID = input.nextLine();
            obj.updateRoomDes(roomID, "UPDATE room SET roomDes=\"Rented\" WHERE roomID=?;");
            System.out.print("Booking Date (YYYY-MM-DD): ");
            Date bDate = Date.valueOf(input.nextLine());
            LocalDate sDate = LocalDate.now();
            System.out.print("Enter Prepaid: ");
            int prepaid = input.nextInt();
            input.nextLine();
            statement.setInt(1, staffID);
            statement.setInt(2, cus.inputCusID());
            statement.setString(3, roomID);
            statement.setDate(4, bDate);
            statement.setDate(5, Date.valueOf(sDate));
            statement.setInt(6, prepaid);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(" Insert successfully!.");
            } else {
                System.out.println("Insert Unsuccessfully!.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

}
