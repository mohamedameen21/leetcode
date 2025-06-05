class Solution {

    private static class Node {
        int listIndex;
        int element;
        int eleIndex;

        public Node(int listIndex, int element, int eleIndex) {
            this.listIndex = listIndex;
            this.element = element;
            this.eleIndex = eleIndex;
        }

        public boolean doesHasNext(List<List<Integer>> nums) {
            List<Integer> list = nums.get(listIndex);

            return eleIndex + 1 < list.size();
        }
    }

        public int[] smallestRange(List<List<Integer>> nums) {
            int k = nums.size();
            PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node a, Node b) {
                    return a.element - b.element;
                }
            });
            Node maxNode = null;

            for (int i = 0; i < nums.size(); i++) {
                int num = nums.get(i).get(0);
                Node node = new Node(i, num, 0);
                minHeap.add(node);

                if (maxNode == null) {
                    maxNode = node;
                } else {
                    if (maxNode.element < node.element) {
                        maxNode = node;
                    }
                }
            }

            Node minNode = minHeap.peek();
            int minRange = Integer.MAX_VALUE;
            int[] result = new int[2];

            while(true) {
                minNode = minHeap.remove();
                
                int range = maxNode.element - minNode.element;
                if(range < minRange) {
                    minRange = range;
                    result[0] = minNode.element;
                    result[1] = maxNode.element;
                }

                if(minNode.doesHasNext(nums)) {
                    int listIndex = minNode.listIndex;
                    int nextEleIndex = minNode.eleIndex + 1;
                    int newNum = nums.get(listIndex).get(nextEleIndex);

                    Node newNode = new Node(listIndex, newNum, nextEleIndex);
                    
                    if(newNode.element > maxNode.element) {
                        maxNode = newNode;
                    }
                    minHeap.add(newNode);
                } else {
                    break;
                }
            }

            return result;
        }
}