package ch.heigvd.dai;

import static ch.heigvd.dai.terminal.Color.*;
import ch.heigvd.dai.commands.Root;
import java.io.File;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        // Define command name - source: https://stackoverflow.com/a/11159435
        String jarFilename =
                new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath())
                        .getName();

        // Create root command
        Root root = new Root();

        // Calculate execution time for root command and its subcommands
        Long start = System.nanoTime();
        int exitCode =
                new CommandLine(root)
                        .setCommandName(jarFilename)
                        .setCaseInsensitiveEnumValuesAllowed(true)
                        .execute(args);
        Long end = System.nanoTime();

        if (exitCode == 0) {
            if (root.getChoiceColor()){
                System.out.println(BLUE + "Execution time in " + (end - start) / (1000 * 1000) + " [ms]" + RESET);
            } else {
                System.out.println("Execution time in " + (end - start) / (1000 * 1000) + " [ms]");
            }
        }
        System.exit(exitCode);
    }
}