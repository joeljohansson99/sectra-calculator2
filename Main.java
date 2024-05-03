import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String args[]) {

        Scanner scanner;

        // Check for invalid arguments
        if (args.length > 1) {
            System.out.println("usage: java Calculator <file>");
            return;
        }
        
        // Check for file or console
        if (args.length == 1) {
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (Exception e){
                System.out.println("File " + args[0] + " does not exist");
                return;
            }
        } else {
            scanner = new Scanner(System.in);
        }

        Calculator calculator = new Calculator();

        // Start reading the lines
        while (scanner.hasNextLine()) {
            String instr = scanner.nextLine().toLowerCase();

            // Break if user wants to quit
            if (instr.equals("quit")) {
                break;
            }
            
            // Split instruction into commands
            String[] commands = instr.split(" ");

            if (commands.length < 2 || commands.length > 3) {
                System.out.println("Invalid instruction!");
                continue;
            }

            if (commands[0].equals("print")) {
                // Get and check register name
                String reg = commands[1];
                if (!reg.matches("[A-Za-z0-9]+")) {
                    System.out.println("Invalid register name!");
                    continue;
                }

                // Get and print value
                System.out.println(calculator.get_value(reg));

            } else {
                // Get and check register name
                String reg = commands[0];
                if (!reg.matches("[A-Za-z0-9]+")) {
                    System.out.println("Invalid register name!");
                    continue;
                }

                // Get and check value
                int value;
                try {
                    value = Integer.parseInt(commands[2]);
                } catch (Exception e) {
                    System.out.println("Invalid value!");
                    continue;
                }

                // Get and check operand
                Operand op = Operand.parse(commands[1], value);
                if (op == null) {
                    System.out.println("Invalid Operation!");
                }

                // Add operation for later evaluation
                calculator.add_op(reg, op);
            }

        }

        scanner.close();

    }
}