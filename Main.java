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
                if (!calculator.valid_reg(reg)) {
                    System.out.println("Invalid register!");
                    continue;
                }

                // Get and print value
                System.out.println(calculator.get_value(reg));

            } else {
                String reg = commands[0];
                String op = commands[1];
				String val = commands[2];

                // Check register name
				if (!calculator.valid_reg(reg)) {
					System.out.println("Invalid register!");
					continue;
				}

                // Check and parse operand
                Operand operand = Operand.parse(op);
                if (operand == null) {
                    System.out.println("Invalid Operation!");
					continue;
                }
				
				// Check value, either number or register, and create Expression object.
				Expression exp;
				if (calculator.valid_number(val)) {
					exp = new Expression(operand, val, true);
				} else if (calculator.valid_reg(val)) {
					exp = new Expression(operand, val, false);
				} else {
					System.out.println("Invalid value!");
					continue;
				}

                // Add expression for later evaluation
                calculator.add_exp(reg, exp);
            }

        }

        scanner.close();

    }
}