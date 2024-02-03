import java.util.*;

public class LinkedListImplementation {

    static class CustomLinkedList <T> {

        Node head;
        private int size;

        public CustomLinkedList() {
            this.size = 0;
        }

        private class Node {
            T data;
            Node next;

            public Node(T data, Node next)
            {
                this.data = data;
                this.next = next;
                size++; //Whenever a new node is created, size of linked list will be incremented.
            }
        }

        //Add First: adding a node at the first position (0th index) of the linked list
        public void addFirst(T data)
        {
            Node newNode = new Node(data, null);
            if(head == null) //If linked list is empty, set the newly created node as the head node.
            {
                head = newNode;
                return;
            }
            //Else if ll is not empty, then set the next pointer of the newly created node to the existing head node.
            newNode.next = head;
            head = newNode; //And then set the newly created node as the head node.
        }

        //Add Last: adding a node to the end of the linked list
        public void addLast(T data)
        {
            Node newNode = new Node(data, null);
            if(head == null) //If linked list is empty, set the newly created node as the head node.
            {
                head = newNode;
                return;
            }
            //Else if ll is not empty, traverse the ll till the last node is found and then append the new node to the last node.
            Node currNode = head;
            while(currNode.next != null)
            {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }

        //Delete First: deleting a node at the beginning of a Linked List
        public void deleteFirst()
        {
            if(head == null)
            {
                System.out.println("List is already empty");
                return;
            }
            size--;
            Node currNode = head;
            head = currNode.next;
        }

        //Delete Last: deleting a node at the end of a Linked List
        public void deleteLast()
        {
            if(head == null) { //LL has zero nodes
                System.out.println("List is already empty");
                return;
            }
            size--;
            if(head.next == null) { //LL has only 1 node
                head = null;
                return;
            }
            else { //LL has 2 or more nodes
                Node currNode = head.next;
                Node currNodeMinus1 = head;
                while (currNode.next != null) {
                    currNodeMinus1 = currNode;
                    currNode = currNode.next;
                }
                currNodeMinus1.next = null;
            }
        }


        //Print List: printing the linked list in the order that nodes were added.
        public void printList()
        {
            if(head == null) //If linked list is empty, set the newly created node as the head node.
            {
                System.out.println("Empty List");
                return;
            }
            Node currNode = head;
            while(currNode != null)
            {
                System.out.print(currNode.data.toString() + " -> ");
                currNode = currNode.next;
            }
            System.out.println("null");
        }

        public int getSize()
        {
            return this.size;
        }

    }

    public static void main(String args[])
    {
        CustomLinkedList<String> newLinkedList = new CustomLinkedList<>();
        newLinkedList.addFirst("is");
        newLinkedList.addLast("a");
        newLinkedList.addFirst("This");
        newLinkedList.addLast("Linked List");
        newLinkedList.printList();

        System.out.println("Size: "+newLinkedList.getSize());

        newLinkedList.deleteFirst();
        newLinkedList.deleteLast();
        newLinkedList.printList();

        System.out.println("Size: "+newLinkedList.getSize());

        newLinkedList.deleteLast();
        newLinkedList.printList();

        System.out.println("Size: "+newLinkedList.getSize());

        newLinkedList.deleteLast();
        newLinkedList.printList();

        System.out.println("Size: "+newLinkedList.getSize());
    }
}
