import java.util.Iterator;

@SuppressWarnings("unchecked")
class DynamicArray<T> implements Iterable<T> {
    private T[] elems;
    private int capacity;
    private int size;

    public DynamicArray(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Capacity must be positive");
        this.capacity = capacity;
        elems = (T[]) new Object[capacity];
        size = 0;
    };

    public DynamicArray() {
        this(4);
    }

    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        return elems[index];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void set(int index, T elem) {
        if (index >= capacity) {
            T[] temp = (T[]) new Object[capacity * 2];
            for (int i = 0; i < capacity; i++) {
                temp[i] = elems[i];
                System.out.printf("Add %d\n", i);
            }
            capacity *= 2;
            System.out.printf("Increase capacity to %d\n", capacity);
            elems = temp;
        }
        elems[index] = elem;
        size++;
    }

    public void push(T elem) {
        this.set(size, elem);
    }

    public void prepend(T elem) {
        T[] temp;
        if (size + 1 >= capacity)
            capacity *= 2;
        temp = (T[]) new Object[capacity];
        temp[0] = elem;
        for (int i = 0; i < size; i++) {
            temp[i + 1] = elems[i];
        }
        elems = temp;
        size++;
    }

    public void insert(T elem, int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        T[] temp;
        if (size + 1 >= capacity)
            capacity *= 2;
        temp = (T[]) new Object[capacity];

        for (int i = 0; i < index; i++) {
            temp[i] = elems[i];
        }
        temp[index] = elem;
        for (int i = index; i < size; i++) {
            temp[i + 1] = elems[i];
        }
        elems = temp;
        size++;
    }

    public T pop() {
        T pop = elems[size - 1];
        size--;
        return pop;
    }

    public void delete(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= capacity || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < index; i++) {
            temp[i] = elems[i];
        }
        for (int i = index + 1; i < size; i++) {
            temp[i - 1] = elems[i];
        }

        elems = temp;
    }

    public int find(T elem) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elems[i] == elem)
                return i;
        }
        return index;
    }

    public void clear() {
        elems = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            public boolean hasNext() {
                return index < size;
            }

            public T next() {
                return elems[index++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elems[i]);
            if (i != size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

class Main {
    public static void main(String[] args) {
        DynamicArray<Integer> arr = new DynamicArray<Integer>();
        for (int i = 0; i < 10; i++) {
            arr.push(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(arr.get(i));
        }
        System.out.printf("Size is %d\n", arr.getSize());
        System.out.println(arr);

        arr.prepend(2);
        int z = arr.pop();
        System.out.println(z);
        for (int i = 0; i < arr.getSize(); i++) {
            System.out.println(arr.get(i));
        }
        System.out.printf("Size is %d\n", arr.getSize());
        System.out.println(arr);
    }

}