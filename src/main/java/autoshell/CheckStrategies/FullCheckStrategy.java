package autoshell.CheckStrategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import autoshell.CheckableCommand.Checkable;

public class FullCheckStrategy implements CheckStrategy {

	Map<String,List<String>> qwertyMisclicks = new HashMap<String,List<String>>(){{
		put("q",Arrays.asList("w", "a"));
		put("w",Arrays.asList("q", "s", "e"));
		put("e",Arrays.asList("w", "d", "r"));
		put("r",Arrays.asList("e", "f", "t"));
		put("t",Arrays.asList("r", "g", "y"));
		put("y",Arrays.asList("t", "h", "u"));
		put("u",Arrays.asList("y", "j", "i"));
		put("i",Arrays.asList("u", "k", "o"));
		put("o",Arrays.asList("i", "l", "p"));
		put("p",Arrays.asList("o", ";", "["));
		put("a",Arrays.asList("q", "s", "z"));
		put("s",Arrays.asList("a", "w", "d", "x"));
		put("d",Arrays.asList("s", "e", "f", "c"));
		put("f",Arrays.asList("d", "r", "g", "v"));
		put("g",Arrays.asList("f", "t", "h", "b"));
		put("h",Arrays.asList("g", "y", "j", "n"));
		put("j",Arrays.asList("h", "u", "k", "m"));
		put("k",Arrays.asList("j", "i", "l", ","));
		put("l",Arrays.asList("k", "o", ";", "."));
		put("z",Arrays.asList("a", "x"));
		put("x",Arrays.asList("z", "s", "c"));
		put("c",Arrays.asList("x", "d", "v"));
		put("v",Arrays.asList("c", "f", "b"));
		put("b",Arrays.asList("v", "g", "n"));
		put("n",Arrays.asList("b", "h", "m"));
		put("m",Arrays.asList("n", "j", ","));
		put(" ",Arrays.asList("c", "v", "b", "n", "m"));
	}};

	@Override
	public void generateVariants(Checkable com) {
		com.setVariants(generateAllVariants(com.getName()));
	}

	private List<String> generateAllVariants(String name) {
        List<String> combinedList = Stream.of(nameList(name), swappedLetterVariants(name), replacedLetterVariants(name), missingLetterVariants(name), extraLetterVariants(name))
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        return combinedList;
	}
	
	private List<String> nameList(String name) {
		List<String> tempList = new ArrayList<String>();
		tempList.add(name);
//		System.out.println(tempList);
		return tempList;
	}
	
	private List<String> swappedLetterVariants(String name) {
		List<String> tempList = new ArrayList<String>();
		
		for (int i = 0; i < name.length(); i++) {
			char[] newVariant = name.toCharArray();
			if (i != 0) {
				char tmp = newVariant[i];
				newVariant[i] = newVariant[i-1];
				newVariant[i-1] = tmp;
				tempList.add(new String(newVariant));
			}
		}
//		System.out.print("swappedLetterVariants: ");
//		System.out.println(tempList);

		return tempList;
	}
	
	private List<String> replacedLetterVariants(String name) {
		List<String> tempList = new ArrayList<String>();
		
		for (int i = 0; i < name.length(); i++) {
			for (String c : qwertyMisclicks.get(String.valueOf(name.charAt(i)))) {
				String newVariant = "";
				if (i > 0 && i < name.length() - 1) {
					newVariant = name.substring(0,i) + c + name.substring(i+1);
				}
				else if (i == 0) {
					newVariant = c + name.substring(i+1);
				}
				else if (i == name.length() - 1) {
					newVariant = name.substring(0,i) + c;
				}
					
				if (newVariant != "") 
					tempList.add(newVariant);
			}
		}
//		System.out.print("replacedLetterVariants: ");
//		System.out.println(tempList);

		return tempList;
	}

	private List<String> missingLetterVariants(String name) {
		List<String> tempList = new ArrayList<String>();
		
		for (int i = 0; i < name.length(); i++) {
			String newVariant = "";
			if (i > 0 && i < name.length() - 1) {
				newVariant = name.substring(0,i) + name.substring(i+1);
			}
			else if (i == 0) {
				newVariant = name.substring(i+1);
			}
			else if (i == name.length() - 1) {
				newVariant = name.substring(0,i);
			}
				
			if (newVariant != "") 
				tempList.add(newVariant);
			
		}
//		System.out.print("missingLetterVariants: ");
//		System.out.println(tempList);

		return tempList;
	}
	
	private List<String> extraLetterVariants(String name) {
		List<String> tempList = new ArrayList<String>();
		
		for (int i = 0; i < name.length(); i++) {
			for (String c : qwertyMisclicks.get(String.valueOf(name.charAt(i)))){
				String newVariant1 = "", newVariant2 = "";
				if (i > 0 && i < name.length() - 1) {
					newVariant1 = name.substring(0,i) + c + name.substring(i);
					newVariant2 = name.substring(0,i+1) + c + name.substring(i+1);
				}
				else if (i == 0) {
					newVariant1 = c + name;
					newVariant2 = name.substring(0,1) + c + name.substring(1);
				}
				else if (i == name.length() - 1) {
					newVariant1 = name.substring(0,i) + c + name.substring(i);
					newVariant2 = name + c;
				}
				
				if (newVariant1 != "") 
					tempList.add(newVariant1);
				if (newVariant2 != "") 
					tempList.add(newVariant2);
			}
		}
//		System.out.print("extraLetterVariants: ");
//		System.out.println(tempList);

		return tempList;
	}
	
}
