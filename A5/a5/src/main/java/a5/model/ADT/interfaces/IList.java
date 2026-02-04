package a5.model.ADT.interfaces;

import java.util.List;

public interface IList<T> extends IIterable<T>
{
    public List<T> Vanilla();

    public void Append(T elem);

    public void Prepend(T elem);

    public boolean Remove(T elem);

    public boolean RemoveAt(int index);

    public boolean Exists(T elem);

    public void Clear();

    public T First();

    public T Last();

    public int Length();

    public T Get(int index);
}