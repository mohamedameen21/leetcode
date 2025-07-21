class Solution {
    public String countAndSay(int n) {
        String s = "1";

        for (int i = 1; i < n; i++) {
            s = getRunLengthEncode(s);
        }

        return s;
    }

    private String getRunLengthEncode(String s) {
        if (s.isEmpty()) {
            return "";
        }

        int consecutiveCount = 1;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                consecutiveCount++;
            } else {
                sb.append(consecutiveCount).append(s.charAt(i - 1));
                consecutiveCount = 1;
            }
        }

        sb.append(consecutiveCount).append(s.charAt(s.length() - 1));

        return sb.toString();
    }
}