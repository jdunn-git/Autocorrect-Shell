package autoshell.CheckableCommand;

import java.util.List;

public interface Checkable {
	
	public String getName();
	public void setVariants(List<String> variants);
	public boolean checkIfMatch(String str);
}
