class Solution {
    private static class DisjointSet {
        private final List<Integer> rank = new ArrayList<>();
        private final List<Integer> size = new ArrayList<>();
        private final List<Integer> parent = new ArrayList<>();
        
        public DisjointSet(int n) {
            for(int i = 0; i < n; i++) {
                rank.add(0);
                size.add(1);
                parent.add(i);
            }
        }
        
        public void union(int u, int v) {
            unionByRank(u, v);
            // unionBySize(u, v);
        }
        
        public int findUltimateParent(int node) {
            if(node == parent.get(node)) {
                return node;
            }
            
            int ultimateParent = findUltimateParent(parent.get(node));
            parent.set(node, ultimateParent);
            
            return ultimateParent;
        }
        
        private void unionByRank(int u, int v) {
            int ultimateParentOfU = findUltimateParent(u);
            int ultimateParentOfV = findUltimateParent(v);
            
            if(ultimateParentOfU == ultimateParentOfV) {
                return;
            }
            
            if(rank.get(ultimateParentOfU) < rank.get(ultimateParentOfV)) {
                parent.set(ultimateParentOfU, ultimateParentOfV);
            } else if(rank.get(ultimateParentOfU) > rank.get(ultimateParentOfV)) {
                parent.set(ultimateParentOfV, ultimateParentOfU);
            } else {
                parent.set(ultimateParentOfV, ultimateParentOfU);
                rank.set(ultimateParentOfU, rank.get(ultimateParentOfU) + 1);
            }
        }
        
        private void unionBySize(int u, int v) {
            int ultimateParentOfU = findUltimateParent(u);
            int ultimateParentOfV = findUltimateParent(v);
            
            if(ultimateParentOfU == ultimateParentOfV) {
                return;
            }
            
            if(size.get(ultimateParentOfU) < size.get(ultimateParentOfV)) {
                parent.set(ultimateParentOfU, ultimateParentOfV);
                size.set(ultimateParentOfV, size.get(ultimateParentOfV) + size.get(ultimateParentOfU));
            } else {
                parent.set(ultimateParentOfV, ultimateParentOfU);
                size.set(ultimateParentOfU, size.get(ultimateParentOfU) + size.get(ultimateParentOfV));
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DisjointSet ds = new DisjointSet(accounts.size());
        Map<String, Integer> mailToNodeMap = new HashMap<>();

        for(int i = 0; i < accounts.size(); i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);

                if(!mailToNodeMap.containsKey(email)) {
                    mailToNodeMap.put(email, i);
                } else {
                    ds.union(i, mailToNodeMap.get(email));
                }
            }
        }

        List<List<String>> list = new ArrayList<>();

        Map<Integer, List<String>> nodeToMailMap = new HashMap<>();
        // System.out.println(mailToNodeMap);

        for(String email : mailToNodeMap.keySet()) {
            int node = mailToNodeMap.get(email);
            int ultimateParentNode = ds.findUltimateParent(node);

            if(!nodeToMailMap.containsKey(ultimateParentNode)) {
                nodeToMailMap.put(ultimateParentNode, new ArrayList<>());
            }

            List<String> l = nodeToMailMap.get(ultimateParentNode);
            l.add(email);
        }

        // System.out.println(nodeToMailMap);

        for(int node : nodeToMailMap.keySet()) {
            String name = accounts.get(node).get(0);
            List<String> l = new ArrayList<>();
            l.add(name);

            List<String> emailList = nodeToMailMap.get(node);
            Collections.sort(emailList);
            l.addAll(emailList);
            list.add(l);
        }

        return list;
    }
}