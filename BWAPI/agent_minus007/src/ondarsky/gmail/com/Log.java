package ondarsky.gmail.com;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Log {

	private static PrintStream inf;
	private static PrintStream dbg;
	private static PrintStream err;

	static {
		try {
			inf = new Oblivion("void");
			dbg = new Oblivion("void");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Log instance;

	public static Log getInstance() {
		if (instance == null) {
			instance = new Log();
		}
		return instance;
	}

	public void setup(int level) {
		if(level > 0)
			dbg = System.out;
		if(level > 1)
			inf = System.out;
		err = System.err;
	}

	public void info(String msg) {
		inf.println(msg);
	}

	public void debug(String msg) {
		dbg.println(msg);
	}

	public void error(String msg) {
		err.println(msg);
	}

	private static class Oblivion extends PrintStream {
		public Oblivion(String fileName) throws FileNotFoundException {
			super(fileName);
		}

		@Override
		public void print(String s) {
			// die in oblivion;
		}

		@Override
		public void println(String x) {
			// die in oblivion;
		}


	}

}
