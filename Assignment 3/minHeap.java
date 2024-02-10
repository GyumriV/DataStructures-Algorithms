// minHeap class to be used in Main
public class minHeap {
	int[] heap;
	int size;
	int max;
	
	private static final int FRONT = 1;
	// Constructor
	public minHeap(int maxSize) {
		this.max = maxSize;
		this.size = 0;
		heap = new int[this.max+1];
		heap[0] = Integer.MIN_VALUE;
	}
	
	private int parent(int curr) {
		return curr/2;
	}
	
	private int leftChild(int curr) {
		return (2*curr);
	}
	
	private int rightChild(int curr) {
		return (2 * curr) + 1;
	}
	
	private boolean isLeaf(int curr) {		
		if(curr >= (size / 2) && curr <= size) {
			return true;
		}
		return false;
	}
	
	private void swap(int num1, int num2) {	
		int temp;
		temp = heap[num1];
		heap[num1] = heap[num2];
		heap[num2] = temp;
	}
	//performs heapify starting the given index
	private void minHeapify(int curr) {	
		if(!isLeaf(curr)) {
			if(heap[curr] > heap[leftChild(curr)]) {
				if(heap[leftChild(curr)] < heap[rightChild(curr)]) {
					swap(curr, leftChild(curr));
					minHeapify(leftChild(curr));
				} else {
					swap(curr, rightChild(curr));
					minHeapify(rightChild(curr));
				}
			}
		}
	}
	//inserts new element in into heap
	public void insert(int e) {
		if(size >= max) {
			return;
		}
		heap[++size] = e;
		int curr = size;
		
		while(heap[curr] < heap[parent(curr)]) {
			swap(curr, parent(curr));
			curr = parent(curr);
		}
	}
	// Removes and returns min element
	public int removeMin() {
		int popped = heap[FRONT];
		heap[FRONT] = heap[size--];
		minHeapify(FRONT);
		return popped;
	}
	
}

