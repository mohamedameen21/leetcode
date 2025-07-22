class Solution {
    public String longestPrefix(String s) {
        int[] lps = getLPS(s);
        int index = lps[lps.length -1];

        return s.substring(0, index);
    }

    private int[] getLPS(String s) {
        int i = 0;
        int j = 1;
        int[] lms = new int[s.length()];
        lms[0] = 0;  

        while(j < s.length()) {
            if(s.charAt(i) == s.charAt(j)) {
                lms[j] = i+1;
                i++;
                j++;
            } else {
                while(i > 0 && s.charAt(i) != s.charAt(j)) {
                    i = lms[i-1];
                }

                if(s.charAt(i) == s.charAt(j)) {
                    lms[j] = i +1;
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