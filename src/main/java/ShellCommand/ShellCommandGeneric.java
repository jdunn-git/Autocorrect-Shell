package ShellCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ShellCommandGeneric extends BuildableShellCommand {

	String name;
	Process process;
	String dir;
	
	public ShellCommandGeneric() {
	}

	@Override
	public void BuildCommand(String command) {
		builder = new ProcessBuilder();
		
		name = command;

	    builder.command("sh", "-c", command);
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
