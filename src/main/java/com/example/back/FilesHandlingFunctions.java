package com.example.back;


import com.example.front.Statistics;

import java.io.*;
import java.util.ArrayList;

public class FilesHandlingFunctions implements Serializable {
    private static final long serialVersionUID = 1L;

    public static ArrayList<String> getEmailsList(String filePath) {
        ArrayList<String> emailsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                if (lineNumber % 2 == 0) {
                    emailsList.add(line);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emailsList;
    }

    public static boolean passwordEquals(String filePath, String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Assuming emails and passwords are on alternating lines
                if (email.equals(line.trim())) {
                    String passwordLine = br.readLine();
                    if (passwordLine != null && password.equals(passwordLine.trim())) {
                        return true;
                    }
                    return false;  // If email matches but password doesn't
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // In case of an exception, return false
        }
        return false;  // If no matching email is found
    }

    public static boolean doesUserExist (String filePath , String email) {
        if (getEmailsList(filePath).contains(email)) {
            return true;
        }
        return false;
    }

    public static boolean createFileIfNotExists(String filePath) {
        File file = new File(filePath);

        try {
            if (file.exists()) {
                return false ;
            } else {
                if (file.createNewFile()) {
                    return true;
                } else  {
                    return false ;
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public static void writeLinesToFile(String filePath, ArrayList<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();  // Adds a new line after each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to read lines from a file
    public static ArrayList<String> readLinesFromFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static boolean createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);

        // Check if the folder exists
        if (!folder.exists()) {
            // Attempt to create the folder
            boolean created = folder.mkdirs();
            if (created) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static Statistics readStatisticsFromFile(String filePath) {
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            String line3 = reader.readLine();

            if (line1 != null && !line1.trim().isEmpty()) {
                index1 = Integer.parseInt(line1.trim());
            }

            if (line2 != null && !line2.trim().isEmpty()) {
                index2 = Integer.parseInt(line2.trim());
            }

            if (line3 != null && !line3.trim().isEmpty()) {
                index3 = Integer.parseInt(line3.trim());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return new Statistics(index1, index2, index3);
    }
}
