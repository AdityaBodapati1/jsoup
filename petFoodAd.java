//imports
import java.io.*;
import java.util.*;

public class petFoodAd {
    public static void main(String[] args) {
        String postsFile = "social_media_posts.txt";
        String targetWordsFile = "target_words.txt";
        String outputFile = "targeted_users.txt";

        try {
            // Load data
            List<String> posts = loadFile(postsFile);
            List<String> targetWords = loadFile(targetWordsFile);

            // Find target users and their messages
            List<String[]> targetUsers = findTargetUsers(posts, targetWords);

            // Print usernames and messages
            System.out.println("Potential customers:");
            for (String[] user : targetUsers) {
                System.out.println(user[0] + user[2]);
            }

            // Write to output file
            writeOutput(targetUsers, outputFile);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //reads in file and trims whitespace    
    private static List<String> loadFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
        }
        return lines;
    }

    //code to check if the message contains the target words after splitting username and message
    private static List<String[]> findTargetUsers(List<String> posts, List<String> targetWords) {
        List<String[]> targetUsers = new ArrayList<>();
        for (String post : posts) {
            String[] parts = post.split(":", 2);
            if (parts.length == 2) {
                String username = parts[0].trim();
                String message = parts[1].trim().toLowerCase();
                String currMessage = ", We bet your furry friend would love to smell our pet food!";
                for (String word : targetWords) {
                    if (message.contains(word.toLowerCase())) {
                        if (message.contains(word.toLowerCase())) {
                            if (word.equalsIgnoreCase("dog") || word.equalsIgnoreCase("dogs")) {
                                currMessage = ", Our dog food is packed with protein and rich flavors that will have tails wagging with every bite.";
                            } else if (word.equalsIgnoreCase("cat") || word.equalsIgnoreCase("cats") || word.equalsIgnoreCase("kitty")) {
                                currMessage = ", Your feline friend deserves the best! Our cat food is carefully crafted with savory ingredients to satisfy even the pickiest of eaters.";
                            }
                        targetUsers.add(new String[] { username, parts[1].trim(), currMessage });
                        break;
                    }
                }
            }
        }
    }
        return targetUsers;
    }

    
//Adds potential customers to a new file
    private static void writeOutput(List<String[]> targetUsers, String outputFile) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String[] user : targetUsers) {
                bw.write("Username: " + user[0] + ", Message: " + user[1]);
                bw.newLine();
                bw.write(user[0] + user[2]);
                bw.newLine();
            }
        }
    }
}