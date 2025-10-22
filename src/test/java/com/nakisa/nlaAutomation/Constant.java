package com.nakisa.nlaAutomation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.nakisa.nlaAutomation.utils.DriverFactory;

public class Constant extends DriverFactory {

	public final static boolean runHeadless = false;

	public static String getTodaysDate() {
		String outputDateTime;
		Date todayDate = new Date();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MMM");
		outputDateTime = dateFormatter.format(todayDate);
		return outputDateTime;
	}

	public static int getAARowNumber() {return 23;}

	public static int getAAColNumber() {
		return 25;
	}

	public static int getPPSRRowNumber() {return 21;}

	public static int getCFColNumber() { return 17;}

	public static int getCFRowNumber() {
		return 9;
	}
	public static int getBSColNumber() { return 17;}

	public static int getBSRowNumber() {
		return 9;
	}
	public static int getISRowNumber() {
		return 9;
	}

	public static int getISColNumber() { return 17;}
	public static int getDQIAGColNumber() { return 0;}
	public static int getDQIAGRowNumber() {
		return 6;
	}

	public static int getCEColNumber() {
		return 14;
	}
	public static int getCERowNumber() {
		return 9;
	}

	public static int getPPSRColNumber() {
		return 11;
	}

	public static int getCTRRowNumber() {
		return 26;
	}

	public static int getCTRColNumber() {
		return 38;
	}

	public static int getGLRowNumber() {
		return 30;
	}

	public static int getGLColNumber() {
		return 0;
	}

	public static int getDRRowNumber() {return 27;}

	public static int getDRColNumber() {return 14;}
	public static List<Integer> getSkipColumnsForDQIReports() {
		return Arrays.asList(0, 1, 2, 3, 5, 6, 20, 21, 24, 25, 26, 27, 65, 80);
	}
	public static List<Integer> getSkipRowsForDQIReports() {
		return Arrays.asList();
	}

}
