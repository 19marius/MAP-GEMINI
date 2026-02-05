package a7.model.ADT.interfaces;

public interface IStack<T> extends IIterable<T>
{
    public boolean IsEmpty();

    public void Push(T elem);

    public T Peek();

    public T Pop();

    public int Size();

    public void Clear();
}