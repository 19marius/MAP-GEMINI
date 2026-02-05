package a7.model.ADT.implementations.iterators;

import java.util.ArrayList;

import a7.model.ADT.implementations.Stack;
import a7.model.exceptions.InvalidIteratorException;

public class StackIterator<T> extends IteratorBase<T>
{
    private final ArrayList<T> stack = new ArrayList<>();

    public StackIterator(Stack<T> stack)
    {
        while (!stack.IsEmpty()) this.stack.add(stack.Pop());
        for (int i = this.stack.size() - 1; i >= 0; i--) stack.Push(this.stack.get(i));  
    }

    @Override
    public boolean Valid() { return index >= 0 && index < stack.size(); }

    @Override
    public void First() { index = 0; }

    @Override
    public void Next() { index++; }

    @Override
    public T Current() 
    {
        if (!Valid()) throw new InvalidIteratorException();
        return stack.get(index); 
    }
}