package beng.learner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import beng.io.ObjectDeserializer;
import beng.jumperB.UtilityScorer;
import weka.core.Instances;

public class Learner {
	
	public static final int MIN_NARGS = 2;
	public static final int MAX_NARGS = 3;

	public static void main(String[] args){
		if(args.length < MIN_NARGS || args.length > MAX_NARGS){
			usage();
			System.exit(0);
			return;
		}
		Instances instances = parseData(args[0]);
		UtilityScorer utilityScorer = refineUtilityFunction(instances);
		exportUtilityScorer(args[1], utilityScorer);
		// If log file specified
		if(args.length == MAX_NARGS){
			String evaluation = evaluateUtilityFunction(utilityScorer);
			logEvaluation(args[2], evaluation);
		}
		System.out.println("Utility function trained and saved.");
		return;
		
	}
	private static void usage(){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement thisStackFrame = stacktrace[1];
		String methodName = thisStackFrame.getMethodName();
		System.out.println("Usage: " + methodName + " <game-data-pathname> <util-funtion-output-pathname> [log-file-pathname]");
	}
	private static Instances parseData(String dataFile){
		return null;
	}
	private static UtilityScorer refineUtilityFunction(Instances instances){
		return null;
	}
	private static void exportUtilityScorer(String outputPathname, UtilityScorer utilityScorer){
		ObjectDeserializer<UtilityScorer> exporter = new ObjectDeserializer<>();
		exporter.deserialize(utilityScorer, outputPathname);
		return;
	}
	private static String evaluateUtilityFunction(UtilityScorer utilityScorer){
		return null;
	}
	private static void logEvaluation(String logFilePathname, String evaluation){
		File file = new File(logFilePathname);
		boolean append = true;
		FileWriter fw;
		try {
			fw = new FileWriter(file, append);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
			return;
		}
		try {
			fw.write(evaluation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
