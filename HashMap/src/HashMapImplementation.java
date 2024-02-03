import java.util.*;

public class HashMapImplementation
{

    static class CustomHashMap<K, V>
    { //generics
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }

        }

        private int n; //nodes
        private int N; //buckets
        //This is actually the HashMap
        private LinkedList<Node> buckets[];
        //This is an array of linked lists meaning that each index is a linked list.

        //K is the threshold value for lambda
        private double k = 2.0;

        @SuppressWarnings("unchecked")
        public CustomHashMap() //Constructor for CustomHashMap
        {
            this.N =4;
            this.buckets = new LinkedList[4]; //Here we initialise the size of the array
            for(int i = 0; i<N; i++)
            {
                buckets[i] = new LinkedList<>(); //Initialising each index of the array as an empty linked list.
            }
        }


        /**
         * This is the hashing function. It takes a key as input and returns the bucket index.
         * @param key
         * @return
         */
        private int hashFunction(K key)
        {
            int bucketIndex = key.hashCode();
            return Math.abs(bucketIndex % N); // % N ensures that our bucket index is between 0 and N-1 (inclusive)
        }


        /**
         * This function is called when the loading factor (lambda) exceeds the threshold value 'k'.
         */
        private void rehash() {
            LinkedList<Node> oldBucket[] = buckets; //Stored the old data into an array 'oldBucket'
            buckets = new LinkedList[N*2];//Created a new linked list of double size than the original list.
            this.N= N*2; //Resetting the value of N
            this.n=0; //Resetting the value of n

            for(int i=0; i<buckets.length; i++)
            {
                buckets[i] = new LinkedList<>(); //Initialising each index of new bucket array with an empty linked list.
            }

            for(int i=0; i<oldBucket.length; i++) //Iterate through every index of old bucket i.e. iterate through all linked lists
            {
                LinkedList<Node> linkedList = oldBucket[i];
                for(int j=0; j<linkedList.size(); j++) //Iterate through every node in the linked list
                {
                    Node node = linkedList.get(j);
                    put(node.key, node.value); //Push the node into the new bucket of larger size
                }
            }
        }

        /**
         * Function to put a key value pair inside the HashMap
         * T.C : O(lambda) on average, O(n) in worst case
         * @param key
         * @param value
         */
        public void put(K key, V value)
        {
            int bucketIndex = hashFunction(key);
            int dataIndex = searchForKeyInLinkedList(key,bucketIndex);
            Node element = new Node(key,value);
            if(dataIndex == -1) //If key doesn't exist at the bucket index, create a new node containing the key
            {
                buckets[bucketIndex].add(element);
                n++;
            }
            else //Update the Node at the data index containing the existing key with the new value
            {
                Node existingData = buckets[bucketIndex].get(dataIndex);
                existingData.value = value;
            }

            double lambda = (double)n/N;

            if(lambda > k) //Rehash if the loading factor exceeds k.
            {

                rehash();
            }

        }

        /**
         * Returns the value stored at a given key. If key doesn't exist, throws an exception
         * @param key
         * @return
         * @throws KeyDoesNotExistException
         */
        public V get(K key) throws KeyDoesNotExistException
        {
            int bucketIndex = hashFunction(key);
            int dataIndex = searchForKeyInLinkedList(key, bucketIndex);
            if(dataIndex != -1)
            {
                Node data = buckets[bucketIndex].get(dataIndex);
                return data.value;
            }
            else
            {
                throw new KeyDoesNotExistException(key);
            }

        }


        /**
         * Checks if the HashMap contains a given key. Returns true if key exists else returns false
         * @param key
         * @return
         */
        public boolean containsKey(K key)
        {
            int bucketIndex = hashFunction(key);
            int dataIndex = searchForKeyInLinkedList(key, bucketIndex);
            return dataIndex != -1;
        }


        /**
         * Removes the key-value pair corresponding to given key.
         * @param key
         * @return
         * @throws KeyDoesNotExistException
         */
        public V remove(K key) throws KeyDoesNotExistException {
            int bucketIndex = hashFunction(key);
            int dataIndex = searchForKeyInLinkedList(key, bucketIndex);
            if(dataIndex == -1)
            {
                throw new KeyDoesNotExistException(key);
            }
            else
            {
                Node removedNode = buckets[bucketIndex].remove(dataIndex);
                n--;
                return removedNode.value;
            }
        }


        /**
         * Checks whether the HashMap is empty or not.
         * Returns true if map is empty, else returns false.
         * @return
         */
        public boolean isEmpty() {
            return n == 0;
        }

        /**
         * Returns all the keys in the HashMap. If HashMap contains no keys, returns empty array list.
         * @return
         */
        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();
            for(int i=0; i<buckets.length; i++)
            {
                LinkedList<Node> linkedList = buckets[i];
                for(int j=0; j<linkedList.size(); j++)
                {
                    keys.add(linkedList.get(j).key);
                }
            }
            return keys;
        }



        /**
         * This function takes a bucket index and searches whether the particular key exists at that bucket index by searching through the linked list.
         * If the key does exist, it returns the position of the node where the key is stored, within the linked list. Let's call this the data index.
         * Returns -1 if the key doesn't exist at any node in the linked list.
         * @param key
         * @param bucketIndex
         * @return
         */
        private int searchForKeyInLinkedList(K key, int bucketIndex)
        {
            LinkedList<Node> linkedListAtBucketIndex = buckets[bucketIndex];
            int dataIndex = 0;
            for(Node node : linkedListAtBucketIndex)
            {
                if(node.key.equals(key))
                {
                    return dataIndex;
                }
                dataIndex++;
            }
            return -1;
        }
    }

    public static void main(String args[])
    {
        CustomHashMap<Integer, Integer> map = new CustomHashMap<>();
        map.put(1, 50);
        map.put(2, 30);
        map.put(3, 100);
        map.put(4, 150);
        map.put(5, 80);
        map.put(6, 200);
        map.put(1, 1000);
        map.put(7, 15);
        map.put(8, 90);
        map.put(9, 1);
        map.put(10, 2);
        try {
            System.out.println("map.get(1): " + map.get(1)); // key exists
        } catch (KeyDoesNotExistException e) {
            System.out.println("KeyDoesNotExistException occurred: " + e.getMessage());
        }

        try {
            System.out.println("map.get(100): " + map.get(100)); // key doesn't exist
        } catch (KeyDoesNotExistException e) {
            System.out.println("KeyDoesNotExistException occurred: " + e.getMessage());
        }

        try {
            System.out.println("map.containsKey(1): " + map.containsKey(1)); // key exists
        } catch (Exception e) {
            System.out.println("KeyDoesNotExistException occurred: " + e.getMessage());
        }

        try {
            System.out.println("map.containsKey(100): " + map.containsKey(100)); // key doesn't exist
        } catch (Exception e) {
            System.out.println("KeyDoesNotExistException occurred: " + e.getMessage());
        }

        try {
            System.out.println("map.isEmpty(): " + map.isEmpty());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

        try {
            System.out.println("map.remove(1): " + map.remove(1));
        } catch (KeyDoesNotExistException e) {
            System.out.println("KeyDoesNotExistException occurred: " + e.getMessage());
        }

        try {
            System.out.println("map.remove(100): " + map.remove(100));
        } catch (KeyDoesNotExistException e) {
            System.out.println("KeyDoesNotExistException occurred: " + e.getMessage());
        }

        System.out.println("map.keySet(): " + map.keySet());

    }

    public static class KeyDoesNotExistException extends Exception{
        public KeyDoesNotExistException()
        {
            super();
        }

        public KeyDoesNotExistException(Object customData)
        {
            super("Key: "+customData.toString()+" does not exist");
        }
    }

}



