package controller.user.room.utilities;

import java.util.Random;

public class GenerateCode {

    public static String generateCode() {
        // Generate three random letters
        String letters = generateRandomLetters(3);

        // Generate three random numbers
        String numbers = generateRandomNumbers(3);

        // Combine letters and numbers to form the code
        String generatedCode = letters + numbers;

        return generatedCode;
    }

    private static String generateRandomLetters(int length) {
        StringBuilder randomLetters = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // ASCII code for uppercase letters: 65 to 90
            char randomLetter = (char) (random.nextInt(26) + 65);
            randomLetters.append(randomLetter);
        }

        return randomLetters.toString();
    }

    private static String generateRandomNumbers(int length) {
        StringBuilder randomNumbers = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // Generate a random digit (0 to 9)
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }

        return randomNumbers.toString();
    }
}
