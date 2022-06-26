public class MaxHeap {

  // array to track heap elements
  private int[] heap;
  // pointer to track last element in heap
  private int end;

  // constructor for heap class
  public MaxHeap() {
    // initialize array of size 10 to store heap
    heap = new int[10];
    // intialize end to -1 (no elements in heap)
    end = -1;
  }

  /* for index i:
   * children at 2i+1 and 2i+2
   * parent at floor((i-1)/2)
   */

  // insert element in heap
  public void insert(int val) {
    // increment end and insert val at end
    end++;
    heap[end] = val;

    // store index of child and calculate index of parent
    int childInd = end;
    int parentInd = (int)Math.floor((end - 1)/2);

    // iterate while the value we want to insert is greater than its parent
    // also ensure we aren't out of the bounds of the array
    while (parentInd >= 0 && val > heap[parentInd]) {
      // swap child with parent element
      swap(heap, parentInd, childInd);

      // store child and recalculate parentInd
      childInd = parentInd;
      parentInd = (int)Math.floor((childInd - 1)/2);
    }

    display(this);
  }

  // helper method to swap elements in array
  private static void swap(int[] arr, int ind1, int ind2) {
    int temp = arr[ind1];
    arr[ind1] = arr[ind2];
    arr[ind2] = temp;
  }

  // helper method to display elements in heap
  private static void display(MaxHeap displayHeap) {
    int[] heapArr = displayHeap.heap;

    System.out.print("[");
    for (int i = 0 ; i < displayHeap.end ; i++) {
      System.out.print(heapArr[i] + ", ");
    }
    System.out.print(heapArr[displayHeap.end]);
    System.out.println("]");
  }

  // helper method to display elements in array
  private static void displayArr(int[] arr) {
    System.out.print("[");
    for (int i = 0 ; i < arr.length - 1 ; i++) {
      System.out.print(arr[i] + ", ");
    }
    System.out.print(arr[arr.length - 1]);
    System.out.println("]");
  }

  // delete element in heap
  public Integer delete() {
    // check that heap is not empty
    if (end != -1) {
      // save value at root
      int rootVal = heap[0];
      // replace value at root with value at end to maintain complete binary tree
      heap[0] = heap[end];
      // decrement end to reflect deleted value
      end--;

      // track current index (start at root)
      int curr = 0;

      // set initial left and right child index
      int leftChildInd = 2*curr + 1;
      int rightChildInd = 2*curr + 2;

      // left and right child
      while (leftChildInd <= end && rightChildInd <= end) {
        // satisfy heap invariant by replacing value at root with max value
        // compare values of children, select larger child and compare with value at curr
        if (heap[leftChildInd] >= heap[rightChildInd] && heap[leftChildInd] > heap[curr]) {
          // swap curr and left child and set curr to left child
          swap(heap, leftChildInd, curr);
          curr = leftChildInd;
        } else if (heap[rightChildInd] >= heap[leftChildInd] && heap[rightChildInd] > heap[curr]) {
          // swap curr and left child and set curr to left child
          swap(heap, rightChildInd, curr);
          curr = rightChildInd;
        } else {
          return rootVal;
        }

        // calculate left and right child index from new curr index
        leftChildInd = 2*curr + 1;
        rightChildInd = 2*curr + 2;
      }

      // check if only left child is remaining
      if (leftChildInd <= end && heap[leftChildInd] > heap[curr]) {
        swap(heap, leftChildInd, curr);
      }

      // no children, or child values are less than curr value
      return rootVal;
    }

    return null;
  }

  // heap sort
  public static int[] heapSort(MaxHeap inputHeap) {
    // reference to heap array in inputHeap
    int[] heapArr = inputHeap.heap;

    // iterate through heap until heap is empty
    while (inputHeap.end > -1) {
      // call heap delete operation and assign to index after end of heap
      int deletedVal = inputHeap.delete();
      heapArr[inputHeap.end + 1] = deletedVal;
    }

    displayArr(heapArr);
    return heapArr;
  }

  // create a heap (using insertion)
  public static MaxHeap createHeap(int[] heapVals) {
    // initialize heap to store values in heapVals
    MaxHeap newHeap = new MaxHeap();

    // iterate through elements in heapVal and call insert method on each element
    for (int i = 0 ; i < heapVals.length ; i++) {
      newHeap.insert(heapVals[i]);
    }

    return newHeap;
  }

  // // create a heap (using heapify procedure)
  // public static MaxHeap heapify(int[] heapVals) {
  //
  // }

  // main method
  public static void main(String[] args) {
    int[] heapVals = {5, 10, 30, 20, 35, 40, 15};
    display(createHeap(heapVals));
  }
}
