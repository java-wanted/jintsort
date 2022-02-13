package src.javawanted.jintsort;

import static java.lang.System.out;
import java.util.Arrays;
import java.util.Comparator;

class Reader {
	final int NUM_MIN = -128;
	final int NUM_MAX = 127;

	final String OP_HELP = "-h";
	final String OP_ASC = "-a";
	final String OP_DESC = "-d";
	final String OP_STDIN = "-";

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
		out.printf("\tWith no NUMBERS, or when NUMBERS is -, read " +
				"read the standard input.\n");

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

	private void read_numbers()
	{
		/* TODO: read NUMBERS from the STDIN stream
		 *
		 * This method will be provided in the following commpits.
		 */
		System.exit(1);
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

		if (first < last && argv[first].equals(OP_STDIN)) {
			first++;

			if (first != last) {
				out.printf("Invalid parameter %d: is %s, but " +
						"no parameters are expected.\n",
						first + 1, argv[first]);
				System.exit(1);
			}
		}

		if (first == last)
			read_numbers();
		else
			parse_numbers(argv, first);
	}
}

class Writer {
	void write(Integer []numbers)
	{
		int last = numbers.length;
		int i = 0;

		if (i == last)
			return;

		out.printf("%d", numbers[i].intValue());
		i++;

		for (; i < last; i++) {
			out.printf(" %d", numbers[i].intValue());
		}

		out.printf("\n");
	}
}

class Sorter {
	private Comparator<Integer> compar;

	Sorter(boolean ascending)
	{
		if (ascending)
			compar = Comparator.naturalOrder();
		else
			compar = Comparator.reverseOrder();
	}

	Integer [] sort(Integer []numbers)
	{
		Integer [] nums = Arrays.copyOf(numbers, numbers.length);

		Arrays.sort(nums, compar);

		return nums;
	};
}

public class Main {
	public static void main(String []argv)
	{
		Reader reader = new Reader();
		Writer writer;
		Sorter sorter;
		Integer []numbers;

		reader.parse(argv);

		sorter = new Sorter(reader.ascending());
		numbers = sorter.sort(reader.numbers());

		writer = new Writer();
		writer.write(numbers);

		System.exit(0);
	}
}
