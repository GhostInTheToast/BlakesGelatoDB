import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String csvFile = "productsReceivedDB.csv";

        LogoDisplayer.display(1500);

        int menuSelection;
        do {
            MenuDisplayer.displayMenu();

            CsvInitializer csv = new CsvInitializer();
            csv.EnsureFile(csvFile);

            // open csv and get last line
            String lastLine = csv.getLastLine(csvFile);

            int orderBatch = Integer.parseInt(lastLine.split(",")[0]);
            // System.out.println("the order batch is " + orderBatch);

            String productName = "N/A";
            String productBrand = "N/A";
            String storeBoughtFrom = "N/A";
            float temperature;
            String date = "N/A";

            Scanner scan = new Scanner(System.in);
            menuSelection = scan.nextInt();
            scan.nextLine();

            switch (menuSelection) {
                case 1:
                    orderBatch += 1;
                    do {
                        System.out.println("Enter the PRODUCT NAME (or 'q to quit to main menu'): ");
                        productName = scan.nextLine();
                        if (productName.equalsIgnoreCase("q"))
                            break;
                        System.out.println("Enter the PRODUCT BRAND: ");
                        productBrand = scan.nextLine();
                        System.out.println("Enter the STORE/SUPPLIER: ");
                        storeBoughtFrom = scan.nextLine();
                        // error handling needed on temp
                        System.out.println("Enter the PRODUCT TEMPERATURE: ");
                        temperature = scan.nextFloat();
                        scan.nextLine();
                        System.out.println("Enter the PURCHASE DATE: ");
                        date = scan.nextLine();

                        String newData = (orderBatch) + "," + productName + "," + productBrand + "," + storeBoughtFrom +
                                "," + temperature + "," + date;
                        csvFile = "productsReceivedDB.csv";

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
                            // Append new data to the end of the file
                            writer.newLine(); // Move to the next line (optional)
                            writer.write(newData);
                            writer.flush(); // Flush the writer
                            System.out.println("Data inserted successfully: " + newData);
                        } catch (IOException e) {
                            System.err.println("Error while inserting data: " + e.getMessage());
                        }
                    } while (!productName.equalsIgnoreCase("q"));
                    break;
                case 2:
                    csvFile = "productsReceivedDB.csv";
                    String line = "";


                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        // Read and print headers
                        // Read the header line
                        String head = br.readLine();
                        String[] headers = head.split(",");

                        printHeader(headers);



                        line = br.readLine();
//                        System.out.printf("| %-6s | %-16s | %-10s | %-10s | %-6s | %-10s |\n",
//                                "Batch#", "Product Name", "Prod Brand", "Store", "Temp", "Exp Date");

                        printSeparator();

                        // Read and print data
                        while ((line = br.readLine()) != null) {
                            String[] data = line.split(",");
                            System.out.printf("| %-6s | %-16s | %-10s | %-10s | %-6s | %-10s |\n",
                                    data[0], truncateString(data[1], 16), truncateString(data[2], 10),
                                    truncateString(data[3], 10), data[4], truncateString(data[5], 10));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Enter the PRODUCT NAME: ");
                    productName = scan.nextLine();
                    break;
                case 4:
                    System.out.println("Enter the PRODUCT NAME: ");
                    productName = scan.nextLine();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.exit(0);

            };
        } while (menuSelection != 5);
    }
    // Method to print separator line
    private static void printSeparator() {
        System.out.println("+--------+------------------+------------+------------+--------+------------+");
    }

    // Method to truncate string to specified length
    private static String truncateString(String str, int maxLength) {
        return (str.length() <= maxLength) ? str : str.substring(0, maxLength);
    }

    // Method to print column headers dynamically
    private static void printHeader(String[] headers) {
        StringBuilder headerFormat = new StringBuilder("|");
//        for (String header : headers) {
//            headerFormat.append(" %-").append(getColumnWidth(header)).append("s |");
//        }
        headerFormat.append(" %-").append(6).append("s |");
        headerFormat.append(" %-").append(16).append("s |");
        headerFormat.append(" %-").append(10).append("s |");
        headerFormat.append(" %-").append(10).append("s |");
        headerFormat.append(" %-").append(6).append("s |");
        headerFormat.append(" %-").append(10).append("s |");

        System.out.printf(headerFormat.toString() + "\n", (Object[]) headers);
    }

    // Method to calculate column width based on the longest string in the column
    private static int getColumnWidth(String header) {
        return Math.max(header.length(), 6); // Minimum width is 6 for the ID column
    }




}



