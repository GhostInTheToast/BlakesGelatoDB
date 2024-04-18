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
                    String newData = "Batch#,Product Name,Prod Brand,Store,Temp,Exp Date";

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
            br.readLine();
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }

    public String appendData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Append new data to the end of the file
            writer.newLine(); // Move to the next line (optional)
            writer.write(data);
            writer.flush(); // Flush the writer
            System.out.println("Data inserted successfully: " + data);
        } catch (IOException e) {
            System.err.println("Error while inserting data: " + e.getMessage());
        }
        return "data";
    }
}
