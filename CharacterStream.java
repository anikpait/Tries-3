// Time Complexity : O(1) since I can have max 26 characters in the stringbuilder
// Space Complexity : O(n) for trie nodes where n is the number of words 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Suffix Tree
/* Your code here along with comments explaining your approach: STart storing the characters in the trie from the last character of the word. It
will act as suffix tree. Since we are moving backwards always, and checking if the previous character is End becomes true then return true else
if it is null return false. We are parsing over the word list once to build the tree.
*/
class StreamChecker {
    class TrieNode{
        boolean IsEnd;
        TrieNode[] children;
        TrieNode(){
            this.children = new TrieNode[26];                                                               // Trie Nodes
            this.IsEnd = false;
        }
    }
    TrieNode root;
    StringBuilder sb;
    public StreamChecker(String[] words) {
        this.root = new TrieNode();
        this.sb = new StringBuilder();
        for(String word : words){                                                               // Build the Tree
            buildTree(word);
        }
    }
    
    public void buildTree(String word){
        TrieNode node = root;
        for(int i = word.length()-1; i>=0; i--){                                                                    // Word start from the back
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null)
                node.children[c-'a'] = new TrieNode();
            node = node.children[c-'a'];
        }
        node.IsEnd = true;                                                                                      // K >=1, mark all the words true
    }
    
    public boolean query(char letter) {
        sb.append(letter);                                                                                  // Keep building your letters one by one
        TrieNode node = root;
        for(int k = sb.length()-1; k >=0; k--){                                                                     // Traverse backwards
            char c = sb.charAt(k);
            if(node.children[c-'a'] == null) return false;                                          // If children is null, return false
             if(node.children[c-'a'].IsEnd == true) return true;                                                // If previous character has isend as true reutrn true
            node = node.children[c - 'a'];
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */