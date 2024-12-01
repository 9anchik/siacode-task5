package Various1;

public interface Queue <T> {
    void add (T value, int priority);
    Node<T> poll ();
}
