class Solution {
    public int repeatedStringMatch(String a, String b) {
        if(b.isEmpty()) {
            return 0;
        } 

        if(a == b) {
            return 1;
        }

        StringBuilder str = new StringBuilder(a);
        int numOfTimesRepeated = 1;

        while(str.length() < b.length()) {
            str.append(a);
            numOfTimesRepeated++;
        }

        int i = 0;
        int j = 0;
        int[] lps = getLps(b);
        System.out.println(Arrays.toString(lps));
        boolean isRepeated = false;

        while(i < str.length()) {
            if(str.charAt(i) == b.charAt(j)) {
                i++;
                j++;

                if(j == b.length()) {
                    return numOfTimesRepeated;
                }

            } else {
                if(j != 0) {
                    j = lps[j-1];
                } else {
                    i++;
                }
            }

            if(i == str.length() && !isRepeated) {
                str.append(a);
                numOfTimesRepeated++;
                isRepeated = true;
            }
        }

        return -1;
    }

    private int[] getLps(String s) {
        int[] lps = new int[s.length()];
        int i = 0;
        int j = i+1;
        lps[i] = 0;

        while(j < s.length()) {
            if(s.charAt(i) == s.charAt(j)) {
                lps[j] = i+1;
                i++;
                j++;
            } else {
                if(i == 0) {
                    lps[j] = 0;
                    j++;
                } else {
                    i = lps[i-1];
                }
            }
        }

        return lps;
    }
}