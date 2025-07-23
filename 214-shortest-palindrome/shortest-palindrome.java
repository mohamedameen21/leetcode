class Solution {
    public String shortestPalindrome(String s) {
        StringBuilder str = new StringBuilder(s);
        String reverse = str.reverse().toString();
        String st = s + "$" + reverse;

        int[] lps = getLPS(st);
        System.out.println(Arrays.toString(lps));
        int maxLengthMatching = lps[lps.length -1];

        if(maxLengthMatching == s.length()) {
            return s;
        }

        return new StringBuilder(s.substring(maxLengthMatching)).reverse().toString() + s;
    }

    private int[] getLPS(String s) {
        int i = 0;
        int j = 1;
        int[] lms = new int[s.length()];
        lms[0] = 0;

        while (j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                lms[j] = i + 1;
                i++;
                j++;
            } else {
                while (i > 0 && s.charAt(i) != s.charAt(j)) {
                    i = lms[i - 1];
                }

                if (s.charAt(i) == s.charAt(j)) {
                    lms[j] = i + 1;
                    i++;
                    j++;
                } else {
                    lms[j] = 0;
                    j++;
                }
            }
        }

        return lms;
    }
}