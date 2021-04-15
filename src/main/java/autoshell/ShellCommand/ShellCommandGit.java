package autoshell.ShellCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ShellCommandGit extends BuildableShellCommand {

	String name;
	ProcessBuilder builder;
	Process process;
	String dir;
	
	public ShellCommandGit() {
	}

	@Override
	public void BuildCommand(String command) {
		builder = new ProcessBuilder();
		
		String[] commandArgs = command.split(" ");
		
		int quoteIndex1 = -1, quoteIndex2 = -1;
		name = command;
		for (int i = 0; i < commandArgs.length; i++) {
//			System.out.print(commandArgs[i] + " ");

			if (commandArgs[i].startsWith("\"")) {
				quoteIndex1 = i;
			}
			if (commandArgs[i].endsWith("\"")) {
				quoteIndex2 = i;
			}
			
		}
		
		// Truncate the args if there's a string inside of them.
		String[] truncatedArgs = commandArgs;
		if (quoteIndex1 != -1 && quoteIndex2 != -1) {
			truncatedArgs = new String[quoteIndex1+1];
			for (int i = 0; i < quoteIndex1; i++) {
				truncatedArgs[i] = commandArgs[i];
			}
			String combinedString = "";
			for (int i = quoteIndex1; i <= quoteIndex2; i++) {
				combinedString += commandArgs[i] + " ";
			}
			truncatedArgs[quoteIndex1] = combinedString;

		}
		
		for (int i = 0; i < truncatedArgs.length; i++) {
			System.out.println(truncatedArgs[i] + " ");
		}

		System.out.println();

	    //builder.command("sh", "-c", command);
	    builder.command(truncatedArgs);
		builder.directory(new File(WorkingDirectory.getInstance().getDirectory()));
	}
	
	@Override
	public void execute() {
		try {
			
			process = builder.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		    
		    String line = "";
		    while ((line = errorReader.readLine()) != null) {
		        System.out.println(line);
		    }
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		    
			int exitCode = process.waitFor();
			assert exitCode == 0;
		    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}