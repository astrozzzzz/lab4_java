
class Box<T> {
    private T item;

    public void setItem(T item) {
        if (this.item != null) {
            throw new IllegalStateException("В коробке уже что-то лежит");
        }
        this.item = item;
    }

    public T getItem() {
        T buff = item;
        item = null;
        return buff;
    }

    public boolean isFull() {
        return item != null;
    }

    @Override
    public String toString() {
        T value = this.getItem();
        if (value == null) {
            return "В коробке ничего нет";
        }
        return "Значение в коробке: " + value;
    }
}
