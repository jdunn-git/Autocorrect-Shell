package ShellCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ShellCommandDocker extends BuildableShellCommand {

	String name = "cd";
	
	String[] args;

	public ShellCommandDocker() {
	}
	
	@Override
	public void BuildCommand(String command) {
		builder = new ProcessBuilder();
		
		name = "docker " + command;

	    //builder.command("sh", "-c", command);
		
	    builder.command("/usr/local/bin/docker", command);
//	    builder.command("/usr/local/bin/docker", "ps");
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
