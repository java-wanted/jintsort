package src.javawanted.jintsort;

import static java.lang.System.out;

class Reader {
	final int NUM_MIN = -128;
	final int NUM_MAX = 127;

	final String OP_HELP = "-h";

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

	void parse(String []argv)
	{
		int last = argv.length;
		int first = 0;

		if (first < last && argv[first].equals(OP_HELP)) {
			usage();
		}
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
