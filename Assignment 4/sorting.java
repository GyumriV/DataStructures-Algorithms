//Vardan Vardanyan
//CSC 130 Assignment 4

public class sorting {
	public void heapSort(int arr[]) {
		int size = arr.length;	
		for(int i=size/2 - 1; i >= 0;i--) {
			heapify(arr,size, i);
		}
	}
	
	void heapify(int arr[], int size, int i) {
		int max = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		
		if(left < size && arr[left] > arr[max]) {
			max = left;
		}
		if(right < size && arr[right] > arr[max]) {
			max = right;
		}
		if(max != i) {
			int temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
			heapify(arr,size,max);
		}
	}
	
	public void merge(int arrLeft[], int arrRight[], int arr[], int leftSize, int rightSize) {
		int i = 0;
		int left = 0;
		int right = 0;
		
		while(left < leftSize && right < rightSize) {
			if(arrLeft[left] < arrRight[right]) {
				arr[i++] = arrLeft[left++];
			}else {
				arr[i++] = arrRight[right++];
			}
		}
		while(left < leftSize) {
			arr[i++] = arrLeft[left++];
		}
		while(right < rightSize) {
			arr[i++] = arrRight[right++];
		}
	}
	
	public void mergeSort(int arr[], int size) {
		if(size < 2) {
			return;
		}
		int middle = size / 2;
		int leftArr[] = new int[middle];
		int rightArr[] = new int[size - middle];
		int index = 0;
		for(int i=0;i<size;++i) {
			if(i<middle) {
				leftArr[i] = arr[i];
			}else {
				rightArr[index] = arr[i];
				index++;
			}
		}
		mergeSort(leftArr, middle);
		mergeSort(rightArr, size-middle);
		merge(leftArr,rightArr,arr,middle,size-middle);
	}
		
	static void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		
		int i = (low - 1);
		for (int j = low; j <= high - 1;j++) {
			if(arr[j] < pivot) {
				i++;
				swap(arr,i,j);
			}
		}
		swap(arr, i+ 1,high);
		return (i+1);
	} 
	
	static void quickSort(int arr[], int low, int high) {
		if(low < high) {
			int index = partition(arr, low, high);
			
			quickSort(arr, low, index - 1);
			quickSort(arr, index + 1,high);
		}
	}
	
}
