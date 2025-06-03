class Twitter {

    private static int globalTimestamp=0;
    final private static int feedCount=10;

    final private class Tweet {
        int id;
        int timestamp;

        public Tweet(int id, int timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }

    private HashMap<Integer,Set<Integer>> followersMap;
    private HashMap<Integer, List<Tweet>> tweetMap;
    private PriorityQueue<Tweet> maxHeap; 

    public Twitter() {
        followersMap = new HashMap<>();
        tweetMap = new HashMap<>();

        maxHeap = new PriorityQueue<>(new Comparator<Tweet>() {
            public int compare(Tweet a, Tweet b) {
                return b.timestamp - a.timestamp;
            }
        });

    }

    public void postTweet(int userId, int tweetId) {
        this.createUserIfNot(userId);

        List<Tweet> tweetList = tweetMap.get(userId);
        tweetList.addFirst(new Tweet(tweetId, Twitter.globalTimestamp++));
    }

    public List<Integer> getNewsFeed(int userId) {
        createUserIfNot(userId);
        maxHeap.clear();

        List<Integer> latestFeed = new ArrayList<>();
        boolean isTweetIndexIsEmpty = false;
        int index = 0;

        while(maxHeap.size() <= Twitter.feedCount && !isTweetIndexIsEmpty) {
            isTweetIndexIsEmpty = true;

            for(int follwerId : followersMap.get(userId)) {
                List<Tweet> tweetList = tweetMap.get(follwerId);

                if(!tweetList.isEmpty() && index < tweetList.size()) {
                    isTweetIndexIsEmpty = false;
                    maxHeap.add(tweetList.get(index));
                }
            }
            index++;
        }

        while(latestFeed.size() < Twitter.feedCount && !maxHeap.isEmpty()) {
            latestFeed.add(maxHeap.remove().id);
        }

        return latestFeed;
    }

    public void follow(int followerId, int followeeId) {
        this.createUserIfNot(followerId, followeeId);
        
        Set<Integer> followersList = followersMap.get(followerId);
        followersList.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) { // if all calls are valid
        Set<Integer> followersList = followersMap.get(followerId);
        followersList.remove(followeeId);
    }

    private void createUserIfNot(int... userIds) {
        for (int userId : userIds) {
            if (tweetMap.containsKey(userId)) {
                continue;
            }

            tweetMap.put(userId, new ArrayList<>());
            followersMap.put(userId, new HashSet<>());

            Set<Integer> followersSet = followersMap.get(userId);
            followersSet.add(userId); // for iterating on getNewfeed
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */