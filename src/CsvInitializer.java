import java.io.*;

public class CsvInitializer {
    String fileName = "productsReceivedDB.csv";

    public void EnsureFile(String fileName) {
        try {
            // Create a File object
            File file = new File(fileName);

            // Check if the file already exists
            if (file.exists()) {
                System.out.println("Database file found/in the right location :)");
            } else {
                // Create the file
                if (file.createNewFile()) {
                    String newData = "0,productName,productBrand,storeBoughtFrom,98.6,01/31/2025\n";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                        // Append new data to the end of the file
                        writer.write(newData);
                        writer.flush(); // Flush the writer
                        System.out.println("Data inserted successfully: " + newData);
                    } catch (IOException e) {
                        System.err.println("Error while inserting data: " + e.getMessage());
                    }
                    System.out.println("File created successfully.");
                } else {
                    System.out.println("Failed to create the file.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public String getLastLine(String fileName) {
        String lastLine = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
}
