package autoshell.ShellCommand;

public class WorkingDirectory {

    private static WorkingDirectory single_instance = null;
  
    public String dir;
  
    private WorkingDirectory()
    {
        dir = System.getProperty("user.dir");
    }
  
    public static WorkingDirectory getInstance()
    {
        if (single_instance == null)
            single_instance = new WorkingDirectory();
        
  
        return single_instance;
    }
    
    public String getDirectory() {
    	return dir;
    }
    
    public void setDirectory(String newDir) {
    	dir = newDir;
    }
}
