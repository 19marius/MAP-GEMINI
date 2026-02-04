package a5.model.ADT.implementations;

import java.util.LinkedList;

import a5.model.ADT.implementations.iterators.ListIterator;
import a5.model.ADT.interfaces.IIterable;
import a5.model.ADT.interfaces.IIterator;
import a5.model.ADT.interfaces.IList;

public class List<T> implements IList<T>
{
    private final LinkedList<T> list = new LinkedList<>();

    @Override
    public final java.util.List<T> Vanilla() 
    {
        LinkedList<T> copy = new LinkedList<>();
        ForEach(x -> copy.add(x));

        return copy; 
    }

    @Override
    public final void Append(T elem) { list.add(elem); }

    @Override
    public final void Prepend(T elem) { list.addFirst(elem); }

    @Override
    public final T First() { return list.getFirst(); }

    @Override
    public final T Last() { return list.getLast(); }

    @Override
    public final int Length() { return list.size(); }

    @Override
    public final T Get(int index) { return list.get(index); }

    @Override
    public final boolean Remove(T elem) { return list.remove(elem); } 

    @Override
    public final boolean RemoveAt(int index) { return list.remove(index) != null; }

    @Override
    public final boolean Exists(T elem) { return list.contains(elem); }

    @Override
    public final void Clear() { list.clear(); }

    @Override
    public final IIterable<T> Clone()
    {
        List<T> clone = new List<>();
        ForEach(x -> clone.Append(x));

        return clone;
    }

    @Override
    public final IIterator<T> GetIterator() { return new ListIterator<>(this); }
}