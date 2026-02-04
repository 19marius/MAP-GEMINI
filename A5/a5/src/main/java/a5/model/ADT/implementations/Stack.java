package a5.model.ADT.implementations;

import a5.model.ADT.implementations.iterators.StackIterator;
import a5.model.ADT.interfaces.IIterable;
import a5.model.ADT.interfaces.IIterator;
import a5.model.ADT.interfaces.IStack;
import a5.model.exceptions.EmptyStackException;

public class Stack<T> implements IStack<T>
{
    private final java.util.Stack<T> stack = new java.util.Stack<>();

    @Override
    public final void Clear() { stack.clear(); }

    @Override
    public final boolean IsEmpty() { return stack.isEmpty(); }

    @Override
    public final void Push(T elem) { stack.push(elem); }

    @Override
    public final T Peek()
    {
        if (IsEmpty()) throw new EmptyStackException("Cannot peek from an empty stack."); 
        return stack.peek(); 
    }

    @Override
    public final T Pop()
    {
        if (IsEmpty()) throw new EmptyStackException("Cannot pop from an empty stack.");
        return stack.pop(); 
    }

    @Override
    public final int Size() { return stack.size(); }

    @Override
    public final IIterable<T> Clone()
    {
        Stack<T> clone = new Stack<>();
        GetIterator().ForEach((x, i) -> clone.Push(x));

        return clone;
    }

    @Override
    public final IIterator<T> GetIterator() { return new StackIterator<>(this); }
}