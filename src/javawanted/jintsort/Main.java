package src.javawanted.jintsort;

import static java.lang.System.out;
import java.util.Arrays;

class Reader {
	final int NUM_MIN = -128;
	final int NUM_MAX = 127;

	final String OP_HELP = "-h";
	final String OP_ASC = "-a";
	final String OP_DESC = "-d";

	private boolean ascending = true;
	private Integer []numbers = new Integer [0];

	void usage()
	{
		out.printf("Read integral numbers and write them in an " +
				"order\n");
		out.printf("\n");
		out.printf("Parameters: [-h] [-a|-d] [NUMBERS]\n");
		out.printf("\n");
		out.printf("\th\tPrint this message.\n");
		out.printf("\ta\tWrite numbers in the ascending order " +
				"(default)\n");
		out.printf("\td\tWrite numbers in the descending order\n");
		out.printf("\n");
		out.printf("\tNUMBERS A space separated list of integers " +
				"in the range [%d, %d].\n", NUM_MIN, NUM_MAX);

		System.exit(1);
	}

	boolean ascending()
	{
		return ascending;
	}

	Integer []numbers()
	{
		return Arrays.copyOf(numbers, numbers.length);
	}

	private void parse_numbers(String []argv, int first)
	{
		int last = argv.length;
		int i = first;
		int num = 0;

		numbers = new Integer[last - first];

		for (; i < last; i++) {
			try {
				num = Integer.parseInt(argv[i]);
			} catch (NumberFormatException exc) {
				break;
			}

			if (num < NUM_MIN || num > NUM_MAX) {
				break;
			}

			numbers[i - first] = num;
		}

		if (i < last) {
			out.printf("Invalid parameter %d: is %s, but MUST BE " +
					"an integer in the range [%d, %d].\n",
					i + 1, argv[i], NUM_MIN, NUM_MAX);
			System.exit(1);
		}
	}

	void parse(String []argv)
	{
		int last = argv.length;
		int first = 0;

		if (first < last && argv[first].equals(OP_HELP)) {
			usage();
		}

		if (first < last && argv[first].equals(OP_ASC)) {
			first++;
		} else if (first < last && argv[first].equals(OP_DESC)) {
			ascending = false;
			first++;
		}

		parse_numbers(argv, first);
	}
}

public class Main {
	public static void main(String []argv)
	{
		Reader reader = new Reader();

		reader.parse(argv);

		System.exit(0);
	}
}
