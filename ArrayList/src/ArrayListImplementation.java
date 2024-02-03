import java.util.*;

public class ArrayListImplementation {

    //We declare this as static because we want every array list object that is created to have the same properties.
    static class CustomArrayList <T>
    {
            private static final int DEFAULT_CAPACITY = 10; //Default initial capacity of the ArrayList
            private static final Object[] EMPTY_ARRAYLIST = {}; //Empty ArrayList initialisation
            private static final Object[] EMPTY_ARRAYLIST_WITH_DEFAULT_CAPACITY = {};
            Object[] arr;
            private int size;

            //If initial capacity is not specified, create an array of size = 10 (default capacity)
            public CustomArrayList() {
                arr = new Object[DEFAULT_CAPACITY];
            }

            //If initial capacity is specified, create an array of the given capacity
            public CustomArrayList(int initialCapacity) throws IllegalCapacityException {
                if(initialCapacity > 0) {
                    arr = new Object[initialCapacity];
                }
                else if(initialCapacity == 0) {
                    arr = EMPTY_ARRAYLIST;
                }
                else {
                    throw new IllegalCapacityException(initialCapacity);
                }
            }

            //Function to add a new element to the ArrayList
            public boolean add(T t) {
                ensureArrayHasSufficientSize(size + 1); //Check if the size of the array is sufficient or not
                arr[size++] = t;
                return true;
            }

            //Function to remove an element from the ArrayList
            @SuppressWarnings("unchecked")
            public T remove(int index) throws IllegalIndexException
            {
                checkIndex(index);
                T oldValue = (T) arr[index];
                int numMoved = size - index - 1;
                if (numMoved > 0)
                    System.arraycopy(arr, index+1, arr, index,
                            numMoved);
                arr[--size] = null;

                return oldValue;
            }

            //Function to get an element at a particular position in the ArrayList
            @SuppressWarnings("unchecked")
            public T get(int index) throws IllegalIndexException
            {
                checkIndex(index);
                return (T) arr[index];
            }

            //Function to set an element at a particular position in the ArrayList
            public boolean set(int index, T t) throws IllegalIndexException
            {
                checkIndex(index);
                arr[index] = t;
                return true;
            }

            //Function to check if ArrayList is empty
            public boolean isEmpty() {
                return (size == 0);
            }

            public void printList(){
                System.out.print("{");
                for(int i=0; i<size; i++)
                {
                    if(i == size-1) {
                        System.out.print(arr[i].toString());
                    }
                    else{
                        System.out.print(arr[i].toString() + ", ");
                    }
                }
                System.out.print("}");
                System.out.println();
            }


            private void checkIndex(int index) throws IllegalIndexException{
                if(index<0 || index>=size)
                {
                    throw new IllegalIndexException(index);
                }
            }

            private void ensureArrayHasSufficientSize(int newSize) {
                if(newSize > arr.length) {
                    growArray();
                }
            }

            private void growArray()
            {
                arr = Arrays.copyOf(arr, arr.length + arr.length >> 1);
            }

    }

    public static void main(String args[]) {

        CustomArrayList<Integer> arrayList = new CustomArrayList<>();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.printList();

        System.out.println(arrayList.isEmpty());

//        arrayList.remove(1);
//        arrayList.remove(100);
//        arrayList.get(1);
//        arrayList.get(100);

        CustomArrayList<Object> newList= new CustomArrayList<>();
        newList.add(arrayList);
        newList.add("Hello World");
        newList.printList();


    }


    public static class IllegalCapacityException extends RuntimeException{
        public IllegalCapacityException(){
            super();
        }

        public IllegalCapacityException(int capacityValue)
        {
            System.out.println(capacityValue + " is an invalid capacity value. Please enter a valid capacity value.");
        }
    }

    public static class IllegalIndexException extends RuntimeException{
        public IllegalIndexException(){
            super();
        }

        public IllegalIndexException(int indexValue)
        {
            System.out.println(indexValue + " is an invalid index value. Please enter a valid index value.");
        }
    }
}
