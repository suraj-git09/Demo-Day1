
import Utility.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DBConnection.getConnection();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Create Order");
                System.out.println("2. Show My Orders");
                System.out.println("3. Exit Order");
                System.out.println("4. Show Profit and Loss");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createOrder(scanner);
                        break;
                    case 2:
                        showMyOrders();
                        break;
                    case 3:
                        exitOrder(scanner);
                        break;
                    case 4:
                        showProfitAndLoss();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createOrder(Scanner scanner) throws SQLException {
        System.out.println("Enter stock name:");
        String stockName = scanner.nextLine();

        System.out.println("Enter buy price:");
        double buyPrice = scanner.nextDouble();

        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();

        int stockId = getStockId(stockName);

        if (stockId == -1) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Stocks (stock_name, remaining_quantity) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, stockName);
            ps.setInt(2, quantity);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                stockId = rs.getInt(1);
            }
        } else {
            PreparedStatement ps = connection.prepareStatement("UPDATE Stocks SET remaining_quantity = remaining_quantity + ? WHERE stock_id = ?");
            ps.setInt(1, quantity);
            ps.setInt(2, stockId);
            ps.executeUpdate();
        }

        PreparedStatement ps = connection.prepareStatement("INSERT INTO Orders (stock_id, buy_price, quantity) VALUES (?, ?, ?)");
        ps.setInt(1, stockId);
        ps.setDouble(2, buyPrice);
        ps.setInt(3, quantity);
        ps.executeUpdate();

        System.out.println("Order placed: " + stockName + ", " + quantity + " at ₹" + buyPrice);
    }

//    private static void showMyOrders() throws SQLException {
//        PreparedStatement ps = connection.prepareStatement(
//                "SELECT o.order_id, s.stock_name, o.buy_price, o.quantity - o.sell_quantity AS remaining_quantity, " +
//                        "o.buy_price * (o.quantity - o.sell_quantity) AS total_investment " +
//                        "FROM Orders o JOIN Stocks s ON o.stock_id = s.stock_id WHERE o.sell_quantity < o.quantity"
//        );
//
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            int orderId = rs.getInt("order_id");
//            String stockName = rs.getString("stock_name");
//            double buyPrice = rs.getDouble("buy_price");
//            int remainingQuantity = rs.getInt("remaining_quantity");
//            double totalInvestment = rs.getDouble("total_investment");
//
//            System.out.println("Order ID: " + orderId + ", Stock Name: " + stockName + ", Buy Price: ₹" + buyPrice + ", Remaining Quantity: " + remainingQuantity + ", Total Investment: ₹" + totalInvestment);
//        }
//    }
private static void showMyOrders() throws SQLException {
    System.out.println("Fetching your orders...");

    PreparedStatement ps = connection.prepareStatement(
            "SELECT o.order_id, s.stock_name, o.buy_price, o.quantity - o.sell_quantity AS remaining_quantity, " +
                    "o.buy_price * (o.quantity - o.sell_quantity) AS total_investment " +
                    "FROM Orders o JOIN Stocks s ON o.stock_id = s.stock_id WHERE o.sell_quantity < o.quantity"
    );

    ResultSet rs = ps.executeQuery();

    boolean hasData = false;

    System.out.println("Order ID | Stock Name | Buy Price | Remaining Quantity | Total Investment");
    System.out.println("--------------------------------------------------------------------------");

    while (rs.next()) {
        hasData = true;
        int orderId = rs.getInt("order_id");
        String stockName = rs.getString("stock_name");
        double buyPrice = rs.getDouble("buy_price");
        int remainingQuantity = rs.getInt("remaining_quantity");
        double totalInvestment = rs.getDouble("total_investment");

        System.out.printf(" %8d | %10s | ₹%8.2f | %17d | ₹%14.2f\n",
                orderId, stockName, buyPrice, remainingQuantity, totalInvestment);

    }
    System.out.println("--------------------------------------------------------------------------");

    if (!hasData) {
        System.out.println("No orders found.");

    }
}


    private static void exitOrder(Scanner scanner) throws SQLException {
        System.out.println("Enter order ID or stock name:");
        String input = scanner.nextLine();

        System.out.println("Enter sell price:");
        double sellPrice = scanner.nextDouble();

        System.out.println("Enter quantity to exit:");
        int exitQuantity = scanner.nextInt();

        int orderId = -1;
        int stockId = -1;

        try {
            orderId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            stockId = getStockId(input);
            if (stockId != -1) {
                // Fetch the corresponding order_id if only stock_id is provided
                PreparedStatement ps = connection.prepareStatement("SELECT order_id FROM Orders WHERE stock_id = ? LIMIT 1");
                ps.setInt(1, stockId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    orderId = rs.getInt("order_id");
                }
            }
        }

        if (orderId != -1) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Orders SET sell_price = ?, sell_quantity = sell_quantity + ? " +
                            "WHERE order_id = ? AND (quantity - sell_quantity) >= ?"
            );
            ps.setDouble(1, sellPrice);
            ps.setInt(2, exitQuantity);
            ps.setInt(3, orderId);
            ps.setInt(4, exitQuantity);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Exited " + exitQuantity + " shares at ₹" + sellPrice);

                // Update remaining_quantity in Stocks table
                ps = connection.prepareStatement("UPDATE Stocks SET remaining_quantity = remaining_quantity - ? WHERE stock_id = ?");
                ps.setInt(1, exitQuantity);
                ps.setInt(2, stockId);
                ps.executeUpdate();

                // Insert into ProfitLoss table
                updateProfitLoss(orderId, exitQuantity, sellPrice);

                // Set remaining_quantity to negative if all shares are exited
                ps = connection.prepareStatement("SELECT remaining_quantity FROM Stocks WHERE stock_id = ?");
                ps.setInt(1, stockId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int remainingQuantity = rs.getInt("remaining_quantity");
                    if (remainingQuantity == 0) {
                        ps = connection.prepareStatement("UPDATE Stocks SET remaining_quantity = -quantity WHERE stock_id = ?");
                        ps.setInt(1, stockId);
                        ps.executeUpdate();
                    }
                }
            } else {
                System.out.println("Failed to exit shares. Please check the order ID/stock name and quantity.");
            }
        } else {
            System.out.println("Invalid order ID or stock name.");
        }
    }

    private static void updateProfitLoss(int orderId, int exitQuantity, double sellPrice) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "SELECT buy_price, stock_id FROM Orders WHERE order_id = ? LIMIT 1"
        );
        ps.setInt(1, orderId);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            double buyPrice = rs.getDouble("buy_price");
            int stockId = rs.getInt("stock_id");
            double netProfitLoss = (sellPrice - buyPrice) * exitQuantity;
            double netProfitLossPercentage = ((sellPrice - buyPrice) / buyPrice) * 100;

            // Format netProfitLossPercentage to 2 decimal places
            netProfitLossPercentage = Math.round(netProfitLossPercentage * 100.0) / 100.0;

            ps = connection.prepareStatement(
                    "INSERT INTO ProfitLoss (order_id, stock_id, buy_price, sell_price, quantity, net_profit_loss, net_profit_loss_percentage) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, orderId);
            ps.setInt(2, stockId);
            ps.setDouble(3, buyPrice);
            ps.setDouble(4, sellPrice);
            ps.setInt(5, exitQuantity);
            ps.setDouble(6, netProfitLoss);
            ps.setDouble(7, netProfitLossPercentage);
            ps.executeUpdate();
        }
    }

    private static int getStockId(String stockName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT stock_id FROM Stocks WHERE stock_name = ?");
        ps.setString(1, stockName);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("stock_id");
        } else {
            return -1;
        }
    }

    private static void showProfitAndLoss() throws SQLException {
        System.out.println("Fetching profit and loss data...");

        PreparedStatement ps = connection.prepareStatement(
                "SELECT p.profit_loss_id, o.order_id, s.stock_name, p.buy_price, p.sell_price, p.quantity, " +
                        "p.net_profit_loss, p.net_profit_loss_percentage " +
                        "FROM ProfitLoss p " +
                        "JOIN Orders o ON p.order_id = o.order_id " +
                        "JOIN Stocks s ON p.stock_id = s.stock_id"
        );

        ResultSet rs = ps.executeQuery();

        double totalInvestment = 0;
        double totalProfitLoss = 0;
        boolean hasData = false;

        System.out.println("Order ID | Stock Name | Buy Price | Sell Price | Quantity | Net Profit/Loss | Net Profit/Loss Percentage");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        while (rs.next()) {
            hasData = true;
            int profitLossId = rs.getInt("profit_loss_id");
            int orderId = rs.getInt("order_id");
            String stockName = rs.getString("stock_name");
            double buyPrice = rs.getDouble("buy_price");
            double sellPrice = rs.getDouble("sell_price");
            int quantity = rs.getInt("quantity");
            double netProfitLoss = rs.getDouble("net_profit_loss");
            double netProfitLossPercentage = rs.getDouble("net_profit_loss_percentage");

            double investment = buyPrice * quantity;
            totalInvestment += investment;
            totalProfitLoss += netProfitLoss;

            System.out.printf(" %8d | %10s | ₹%8.2f | ₹%9.2f | %8d | ₹%13.2f | %21.2f%%\n",
                    orderId, stockName, buyPrice, sellPrice, quantity, netProfitLoss, netProfitLossPercentage);
        }

        if (hasData) {
            double totalProfitLossPercentage = (totalProfitLoss / totalInvestment) * 100;
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.printf("Total Investment: ₹%.2f\n", totalInvestment);
            System.out.printf("Total Profit/Loss: ₹%.2f\n", totalProfitLoss);
            System.out.printf("Total Profit/Loss Percentage: %.2f%%\n", totalProfitLossPercentage);
        } else {
            System.out.println("No profit and loss data available.");
        }
    }
}

