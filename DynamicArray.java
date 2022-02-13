class DynamicArray<E> {
    private Object[] elems;
    private int capacity;
    private int size;

    public DynamicArray(int capacity) {
        this.capacity = capacity;
        elems = new Object[capacity];
        size = 0;
    };

    public DynamicArray() {
        this(4);
    }

    public E get(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        @SuppressWarnings("unchecked")
        E elem = (E) elems[index];
        return elem;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void set(int index, E elem) {
        if (index >= capacity) {
            Object[] temp = new Object[capacity * 2];
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

    public void push(E elem) {
        this.set(size, elem);
    }

    public void prepend(E elem) {
        Object[] temp;
        if (size + 1 >= capacity)
            capacity *= 2;
        temp = new Object[capacity];
        temp[0] = elem;
        for (int i = 0; i < size; i++) {
            temp[i + 1] = elems[i];
        }
        elems = temp;
        size++;
    }

    public void insert(E elem, int index) throws ArrayIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        Object[] temp;
        if (size + 1 >= capacity)
            capacity *= 2;
        temp = new Object[capacity];

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

    public E pop() {
        @SuppressWarnings("unchecked")
        E pop = (E) elems[size - 1];
        size--;
        return pop;
    }

    public void delete(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= capacity || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        Object[] temp = new Object[capacity];
        for (int i = 0; i < index; i++) {
            temp[i] = elems[i];
        }
        for (int i = index + 1; i < size; i++) {
            temp[i - 1] = elems[i];
        }

        elems = temp;
    }

    public int find(E elem) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elems[i] == elem)
                return i;
        }
        return index;
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

        arr.prepend(2);
        int z = arr.pop();
        System.out.println(z);
        for (int i = 0; i < arr.getSize(); i++) {
            System.out.println(arr.get(i));
        }
        System.out.printf("Size is %d\n", arr.getSize());
    }
}