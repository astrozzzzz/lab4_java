class Storage<T> {
    private final T item;

    public Storage(T item) {
        this.item = item;
    }

    public T getItemAlt(T alternative) {
        if (item != null) {
            return item;
        }
        return alternative;
    }

    public T getItem() {
        return item;
    }

    @Override
    public String toString() {
        T value = this.getItem();
        if (value == null) {
            return "В хранилище ничего нет";
        }
        return "Значение в хранилище: " + value;
    }
}
