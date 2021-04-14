package CheckStrategies;

import CheckableCommand.Checkable;

public class Context {
	   private CheckStrategy strategy;

	   public Context(CheckStrategy strategy){
	      this.strategy = strategy;
	   }

	   public void executeStrategy(Checkable com){
	      strategy.generateVariants(com);
	   }
}
