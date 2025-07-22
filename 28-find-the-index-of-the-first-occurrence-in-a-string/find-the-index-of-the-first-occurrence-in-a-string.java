class Solution {
    public int strStr(String haystack, String needle) {
        int[] lms = getLPS(needle);
        System.out.println(Arrays.toString(lms));
        int i = 0;
        int j = 0;

        while(i < haystack.length()) {
            if(haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                if(j == needle.length()) { // found the element
                    System.out.println(i);
                    System.out.println(j);
                    return i - needle.length();
                }
            } else {
                while(j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    j = lms[j-1];
                }

                if(haystack.charAt(i) == needle.charAt(j)) {
                    i++;
                    j++;
                } else {
                    i++;
                }
            }
        } 

        return -1;
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
                    // i++;
                }
            }
        }

        return lms;
    }
}