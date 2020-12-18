package ID_205665862_ID_206841496;


public class Set<T> {
	private final int ENLARGE_FACTOR = 2;
	private T[] arr;
	private int currentSize;

	@SuppressWarnings("unchecked")
	public Set() {
		arr = (T[]) new Object[ENLARGE_FACTOR];
		currentSize = 0;
	}

	@Override
	public String toString() {
		String str = "[";
		for (int i = 0; i < currentSize; i++)
			str += arr[i] + " ";
		str += "]";
		return str;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public T get(int index) {
		return arr[index];
	}

	public int capacity() {
		return arr.length;
	}

	public void add(T newValue) {
		if (currentSize == arr.length)
			enlargeArray();
		if (!isAlreadyExists(newValue)) {
			arr[currentSize] = newValue;
			currentSize++;
		}
	}

	
	private boolean isAlreadyExists(T newValue) {
		for (int i = 0; i < currentSize; i++) {
			if (newValue.equals(arr[i]))
				return true;
		}
		return false;
	}

	private void enlargeArray() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[arr.length * ENLARGE_FACTOR];
		for (int i = 0; i < arr.length; i++)
			temp[i] = arr[i];

		arr = temp;
	}

	public void remove(T t) {
		int nullIndex = -1;
		for (int i = 0; i < currentSize; i++) {
			if (t.equals(arr[i])) {
				arr[i] = null;
				nullIndex = i;
			}
		}
		for (int i = nullIndex; i < currentSize-1; i++) {
			arr[i] = arr[i+1];
		}
		currentSize--;
	}

}
