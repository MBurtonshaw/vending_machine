package com.techelevator;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Utility {
    public static final List<String> KEYS = Arrays.asList("$20","$10","$5","$1","Quarter","Dime","Nickel","Penny");
    public static final String KABOOM = "\n" +
            " _   __  ___  ______  _____  _____ ___  ___\n" +
            "| | / / / _ \\ | ___ \\|  _  ||  _  ||  \\/  |\n" +
            "| |/ / / /_\\ \\| |_/ /| | | || | | || .  . |\n" +
            "|    \\ |  _  || ___ \\| | | || | | || |\\/| |\n" +
            "| |\\  \\| | | || |_/ /\\ \\_/ /\\ \\_/ /| |  | |\n" +
            "\\_| \\_/\\_| |_/\\____/  \\___/  \\___/ \\_|  |_/\n" +
            "                                           \n" +
            "                                           \n";
    public static final String welcomeCuteCo = " __    __        _                                              \n" +
                    "/ / /\\ \\ \\  ___ | |  ___   ___   _ __ ___    ___                \n" +
                    "\\ \\/  \\/ / / _ \\| | / __| / _ \\ | '_ ` _ \\  / _ \\               \n" +
                    " \\  /\\  / |  __/| || (__ | (_) || | | | | ||  __/               \n" +
                    "  \\/  \\/   \\___||_| \\___| \\___/ |_| |_| |_| \\___|               \n" +
                    "   ___         _            ___              _____              \n" +
                    "  / __\\ _   _ | |_   ___   / __\\  ___        \\_   \\ _ __    ___ \n" +
                    " / /   | | | || __| / _ \\ / /    / _ \\        / /\\/| '_ \\  / __|\n" +
                    "/ /___ | |_| || |_ |  __// /___ | (_) | _  /\\/ /_  | | | || (__ \n" +
                    "\\____/  \\__,_| \\__| \\___|\\____/  \\___/ ( ) \\____/  |_| |_| \\___|\n" +
                    "                                       |/                       \n";
    public static void logToFile(String description) {
        final String LOG_FILE = "Log.txt";
        LocalDateTime localDate = LocalDateTime.now();
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true);
             PrintWriter writer = new PrintWriter(fileWriter, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedDate = localDate.format(formatter);
            writer.println(formattedDate + " " + description);
        } catch (IOException e) {
            System.out.println("Fail to log data: " + e.getMessage());
        }
    }
    public static void playSound(String path) {
        try {
            File soundFile = new File("src/main/resources/sound/" + path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
    //M//C:\Users\Student\workspace\java-minicapstonemodule1-team0\src\test\resources\sound\
    //K//C:\Users\Student\workspace\Capstone\Module-1\java-minicapstonemodule1-team0\src\test\resources\sound\
}
