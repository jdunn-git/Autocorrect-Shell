package autoshell.ShellCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
//import java.util.concurrent.Executor;

public abstract class BuildableShellCommand {

	ProcessBuilder builder;
	Process process;
	BufferedReader reader;
	//String dir;

	public void BuildCommand(String command) {
		builder = new ProcessBuilder();
		
	    builder.command("sh", "-c", command);
		builder.directory(new File(WorkingDirectory.getInstance().getDirectory()));
	}
	
	public void execute() {
		try {
			process = builder.start();
			
		    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    
		    String line = "";
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
