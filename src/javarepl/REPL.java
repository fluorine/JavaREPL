package javarepl;

import java.util.Scanner;

/**
 * This class implements some static methods that provide a safe REPL to any
 * command line application.
 * 
 * @author fluorine@github.com
 * @version 0.1
 */
public class REPL {
	static Scanner keyboard = new Scanner(System.in);

	private REPL() {
	}

	// Configuration for indentation //
	static String indent = "  ";

	/**
	 * @return Get current indentation.
	 */
	public static String getIndent() {
		return indent;
	}

	/**
	 * Set indent to append before instructions and error messages.
	 * 
	 * @param newindent
	 *            New indentation string
	 */
	public static void setIndent(String newindent) {
		indent = newindent;
	}

	// Methods //

	/**
	 * Get String line from Console.
	 * 
	 * @param propt
	 *            Instructions and prompt for the user.
	 * @return Raw String from user.
	 */
	public static String getString(String prompt) {
		// Display instructions and prompt
		while (true) {
			System.out.print(indent + prompt);
			String input = keyboard.nextLine();

			// Avoiding blank lines
			if (input.trim().isEmpty()) {
				System.out.println(indent + "Input must not be blank.\n");
				continue;
			}

			return input;
		}
	}

	/**
	 * Get tokens delimited by given delimiters, from an input line provided by
	 * the user.
	 * 
	 * @param prompt
	 *            Instructions and prompt for the user.
	 * @param delimiters
	 *            Delimiting regex
	 * @return An array of Strings.
	 */
	public static String[] getTokens(String prompt, String delimiters) {
		return getString(prompt).split(delimiters);
	}

	/**
	 * Get tokens delimited by space, from an input line provided by the user.
	 * 
	 * @param prompt
	 *            Instructions and prompt for the user.
	 * @return An array of Strings.
	 */
	public static String[] getTokens(String prompt) {
		return getTokens(prompt, " ");
	}

	/**
	 * This method ask the user for an integer.
	 * 
	 * @param prompt
	 *            Instructions and prompt for the user.
	 * @return Integer value provided by the user.
	 */
	public static int getInt(String prompt) {
		while (true) {
			// Get value and parse it.
			int value;
			String input = getString(prompt);

			try {
				value = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println(indent + "Input is not a valid Integer.\n");
				continue;
			}

			return value;
		}
	}

	/**
	 * Get some integers from user, delimited by given delimiters.
	 * 
	 * @param prompt
	 *            Instructions and prompt for the user.
	 * @return Array of integers
	 */
	public static Integer[] getIntArray(String prompt, String delimiters) {
		while (true) {
			String[] tokens = getTokens(prompt, delimiters);
			Integer[] numbers = new Integer[tokens.length];

			// Parse all values
			boolean failure = false;
			for (int i = 0; i < tokens.length; i++) {
				try {
					numbers[i] = Integer.parseInt(tokens[i]);
				} catch (NumberFormatException e) {
					System.out.println(indent + "There are invalid values. "
							+ "All values must be Integers.\n");
					failure = true;
					break;
				}
			}

			// Keep loop if failure happened during parsing.
			if (failure) {
				continue;
			} else {
				return numbers;
			}
		}
	}

	/**
	 * Get some parsed integers from user, delimited by spaces.
	 * 
	 * @param prompt
	 *            Instructions and prompt for the user.
	 * @return Array of integers
	 */
	public static Integer[] getIntArray(String prompt) {
		return getIntArray(prompt, " ");
	}

	/**
	 * Main method, for testing REPL.
	 */
	public static void main(String[] params) {
		// REPL's configuration
		// REPL.setIndent(" ");

		// Asking for name
		String name = REPL.getString("Write your name: ");
		System.out.println("   Your name is '" + name + "'\n");

		// Asking for two numbers, add them and finally show total
		System.out.println(" Write two integer numbers.");
		int a = REPL.getInt("a: ");
		int b = REPL.getInt("b: ");
		int total = a + b;
		System.out.println("   " + a + " + " + b + " = " + total);
		System.out.println("   " + a + " * " + b + " = " + (a * b));

		// Get some tokens
		String[] tokens = REPL.getTokens(
				"Write some tokens delimited by comma\n    >", ",");
		for (String i : tokens) {
			System.out.println(" - " + i.trim());
		}

		System.out.println();

		// Total of a list of integers
		Integer[] numbers = REPL
				.getIntArray("Write some integers,\n  separated by space: ");
		total = 0;
		for (int i : numbers) {
			total += i;
		}

		System.out.println("  Total, after adding previous list of integers: "
				+ total);
	}
}
