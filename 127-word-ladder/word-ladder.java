class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> hashSet = new HashSet<>();
        for (String word : wordList) {
            hashSet.add(word);
        }

        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        hashSet.remove(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.remove();

                for (int j = 0; j < word.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String s = word.substring(0, j) + c + word.substring(j + 1);

                        if (hashSet.contains(s)) {
                            if (s.equals(endWord)) {
                                return level + 1;
                            }

                            queue.add(s);
                            hashSet.remove(s);
                        }
                    }
                }
            }

            level++;
        }

        return 0;
    }
}