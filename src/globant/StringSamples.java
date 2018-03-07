package globant;


public class StringSamples {

	public static void main(String args[]) {
		StringSamples s = new StringSamples();
		System.out.println(s.isRotatedString("waterbottle", "erbottlewat"));
		System.out.println(s.uniqueChars("abcdea"));
		System.out.println(s.reverseString("abcd"));
		System.out.println(s.removeDuplicates("abcbaghc"));
		System.out.println(s.areAnagrams("abccd", "dccba"));
		System.out.println(s.replaceSpaces("a bcds    sde "));
	}

	public boolean uniqueChars(String s) {
		if(s == null || s.trim().length() == 0) return false;
		
		char letters[] = s.toCharArray();
		
		for(int i = 0, j = i + 1; i < letters.length; i++) {
			
			while(j < letters.length) {
				if(letters[i] == letters[j]) return false;
				j++;
			}
			
		}
		return true;
	}
	
	public String reverseString(String s) {
		
		char letters[] = s.toCharArray();
		char reverseLetters[] = new char[letters.length];
		for(int i = letters.length; i > 0; i--) {
			reverseLetters[letters.length - i] = letters[i-1];
		}
		
		return new String(reverseLetters);
	}
	
	public String removeDuplicates(String s) {
		
		char letters[] = s.toCharArray();
		
		for(int i = 0; i < letters.length; i++) {
			int j = i + 1;
			while(j < letters.length) {
				if(letters[i] == letters[j]) {
					int k = j + 1;
					while(k < letters.length) {
						letters[k-1] = letters[k];
						k++;
					}
					letters[k-1] = '\0';
				}
				j++;
			}

		}
		
		return new String(letters);
	}
	
	public boolean areAnagrams(String s1, String s2) {
		
		int count[] = new int[128];
		char letters[] = s1.toCharArray();
		for(int i = 0; i < letters.length; i++){
			count[letters[i] - 32]++;
		}
		
		letters = s2.toCharArray();
		for(int i = 0; i < letters.length; i++){
			if(count[letters[i] - 32] == 0) return false;
			count[letters[i] - 32]--;
		}
		
		return true;
	}
	
	public String replaceSpaces(String s) {
		char letters[] = s.toCharArray();
		int count = 0;
		for(int i = 0; i < letters.length; i++) {
			if(letters[i] == ' ') count++;
		}
		
		char strArray[] = new char[letters.length + count * 3];
		for(int i = 0, j = 0; i < letters.length; i++) {
			if(letters[i] == ' ') {
				strArray[j++] = '%';
				strArray[j++] = '2';
				strArray[j++] = '0';
			} else {
				strArray[j++] = letters[i];
			}
		}
		
		return new String(strArray);
	}	
	
	public boolean isRotatedString(String s1, String s2) {
		if(s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty())
			return false;
			
		if(s1.trim().length() != s2.trim().length())	
			return false;
			
		char first[] = s1.toCharArray();
		char second[] = s2.toCharArray();
		for(int i = 0; i < first.length; i++){
			
			if(first[i] == second[0]) {
				int j = i;
				int k = 0;
				while(j < first.length && first[j] == second[k]){
					j++;
					k++;
				}
				if(j == first.length && i == (j-k)){
					char postchars[] = new char[i];
					char prechars[] = new char[i];
					
					int m = 0;
					while(k < second.length) {
						postchars[m] = second[k++];
						prechars[m] = first[m];
						m++;
					}
					if(String.valueOf(prechars).equals(String.valueOf(postchars)))
						return true;
				}
			}
			
		}
		
		return false;
	}	
	
	
}
