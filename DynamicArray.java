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

    public E get(int index) {
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

    public static void main(String[] args) {
        DynamicArray<Integer> arr = new DynamicArray<Integer>();
        for (int i = 0; i < 10; i++) {
            arr.push(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(arr.get(i));
        }
        System.out.printf("Size is %d\n", arr.getSize());
    }
}