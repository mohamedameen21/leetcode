class Solution {
    public boolean checkValidString(String s) {
        // return validateString(s, 0, 0);

        int openCount = 0;
        int closeCount = 0;
        int length = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') openCount++;
            else openCount--;

            if (s.charAt(length - i) == ')' || s.charAt(length - i) == '*') closeCount++;
            else closeCount--;

            if(openCount < 0 || closeCount < 0) return false;
        }

        return true;
    }

    private boolean validateString(String s, int index, int count) {
        if (index == s.length()) {
            return count == 0;
        }

        if (s.charAt(index) == '(') {
            return validateString(s, index + 1, count + 1);
        }

        if (s.charAt(index) == '(') {
            return validateString(s, index + 1, count - 1);
        }

        return validateString(s, index + 1, count + 1)
                || validateString(s, index + 1, count - 1)
                || validateString(s, index + 1, count);
    }
}