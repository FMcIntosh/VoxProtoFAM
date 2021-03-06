package app.process;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class Festival {


	//Says aloud the given word
	public static void sayWord(String word) throws IOException, InterruptedException{
		Task<Void> task = createProcess(createTempScript(word));
		task.run();
	}

	//Says aloud the given word
	public static void spellWord(String word) throws IOException, InterruptedException{
		word = word.replaceAll(".(?!$)", "$0 ");
		Task<Void> task = createProcess(createTempScript(word));
		task.run();
	}

	//Uses temporary script to build the bash command process
	private static Task<Void> createProcess(File script) throws IOException, InterruptedException{

		return (new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try
				{
					ProcessBuilder pb = new ProcessBuilder("bash", script.toString());
					pb.inheritIO();
					Process process = pb.start();
					process.waitFor();
				}

				finally	{
					script.delete();
					return null;
				}
			}
		});

	}

	//Write temporary script with bash festival commands, to speak aloud the given text
	private static File createTempScript(String text) throws IOException {
		File tempScript = File.createTempFile("script", null);

		Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
		PrintWriter printWriter = new PrintWriter(streamWriter);

		printWriter.println("#!/bin/bash");
		printWriter.println("echo \"" + text + "\" | festival --tts");

		printWriter.close();

		return tempScript;
	}
}
