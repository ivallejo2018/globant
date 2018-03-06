

public class StringSamples {

	public static void main(String args[]) {
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
		for(int i = letters.length; i >= 0; i--) {
			reverseLetters[letters.length - i] = letters[i];
		}
		
		return new String(reverseLetters);
	}
	
	public String removeDuplicates(String s) {
		
		char letters[] = s.toCharArray();
		
		for(int i = 0, j = i + 1; i < letters.length; i++) {
			
			while(j < letters.length) {
				if(letters[i] == letters[j]) {
					int k = j;
					while(k < letters.length) {
						letters[k] = letters[k+1];
						k++;
					}
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
		
		for(int i = 0; i < letters.length; i++) {
//			if(letters[i] == ' ') letters[i] = '';
		}
		
		return new String(letters);
	}	
}
