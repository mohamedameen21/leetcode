class Solution {
    public String sortVowels(String s) {
        char[] vowels = new char[s.length()];
        int i = -1;

        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                vowels[++i] = c;
            }
        }

        if (i == -1) {
            return s;
        }

        Arrays.sort(vowels, 0, i +1);
        char[] letters = s.toCharArray();

        for (int j = letters.length-1; j >= 0; j--) {
            if(isVowel(letters[j])) {
                letters[j] = vowels[i--];
            }
        }

        return new String(letters);
    }

    private boolean isVowel(char c) {
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };

        for (char v : vowels) {
            if (c == v)
                return true;
        }

        return false;
    }
}