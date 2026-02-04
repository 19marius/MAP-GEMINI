package a5.model.ADT.implementations.iterators;

import java.util.LinkedList;

import a5.model.ADT.interfaces.delegates.*;
import a5.model.ADT.implementations.List;
import a5.model.ADT.interfaces.IIterable;
import a5.model.ADT.interfaces.IIterator;

interface QueryAction<T, R> { public R run(T element, int index); }

@SuppressWarnings("unchecked")
public abstract class IteratorBase<T> implements IIterator<T>
{
    private class EmptyElement { }

    protected int index;

    @Override
    public void ForEach(IndexedIteratorAction<T> action)
    {
        IteratorBase<?> original = this instanceof QueryIterator ? ((QueryIterator<T>) this).original : this;
        int before = original.index;

        original.First();
        while (original.Valid())
        {
            Object transformed = Transform(original.Current(), original.index);

            if (transformed.getClass() != EmptyElement.class) action.run((T) transformed, original.index);
            original.Next();
        }

        original.index = before;
    }
    @Override
    public void ForEach(IteratorAction<T> action)
    {
        IteratorBase<?> original = this instanceof QueryIterator ? ((QueryIterator<T>) this).original : this;
        int before = original.index;

        original.First();
        while (original.Valid())
        {
            Object transformed = Transform(original.Current(), original.index);

            if (transformed.getClass() != EmptyElement.class) action.run((T) transformed);
            original.Next();
        }

        original.index = before;
    }

    @Override
    public <R> QueryIterator<R> Select(IndexedIteratorSelector<T, R> selector)
    {
        LinkedList<QueryAction<Object, Object>> queue = this instanceof QueryIterator ? ((QueryIterator<T>) this).queue : new LinkedList<>();
        queue.addLast((x, i) -> x.getClass() == EmptyElement.class ? x : selector.run((T) x, i));
        
        return new QueryIterator<>(this instanceof QueryIterator ? ((QueryIterator<T>) this).original : this, queue);
    }
    @Override
    public <R> QueryIterator<R> Select(IteratorSelector<T, R> selector)
    {
        LinkedList<QueryAction<Object, Object>> queue = this instanceof QueryIterator ? ((QueryIterator<T>) this).queue : new LinkedList<>();
        queue.addLast((x, _) -> x.getClass() == EmptyElement.class ? x : selector.run((T) x));
        
        return new QueryIterator<>(this instanceof QueryIterator ? ((QueryIterator<T>) this).original : this, queue);
    }

    @Override
    public QueryIterator<T> Where(IndexedIteratorPredicate<T> predicate)
    {
        LinkedList<QueryAction<Object, Object>> queue = this instanceof QueryIterator ? ((QueryIterator<T>) this).queue : new LinkedList<>();
        queue.addLast((x, i) -> x.getClass() == EmptyElement.class ? x : (predicate.run((T) x, i) ? x : new EmptyElement()));
        
        return new QueryIterator<>(this instanceof QueryIterator ? ((QueryIterator<T>) this).original : this, queue);
    }
    @Override
    public QueryIterator<T> Where(IteratorPredicate<T> predicate)
    {
        LinkedList<QueryAction<Object, Object>> queue = this instanceof QueryIterator ? ((QueryIterator<T>) this).queue : new LinkedList<>();
        queue.addLast((x, _) -> x.getClass() == EmptyElement.class ? x : (predicate.run((T) x) ? x : new EmptyElement()));
        
        return new QueryIterator<>(this instanceof QueryIterator ? ((QueryIterator<T>) this).original : this, queue);
    }

    @Override
    public <R extends IIterable<T>> R Collect(R collection, IndexedIteratorCollector<T, R> collector)
    {
        IteratorBase<?> original = this instanceof QueryIterator ? ((QueryIterator<T>) this).original : this;
        int before = original.index;

        original.First();
        while (original.Valid())
        {
            Object transformed = Transform(original.Current(), original.index);

            if (transformed.getClass() != EmptyElement.class) collector.run(collection, (T) transformed, original.index);
            original.Next();
        }

        original.index = before;
        return collection;
    }
    @Override
    public <R extends IIterable<T>> R Collect(R collection, IteratorCollector<T, R> collector)
    {
        IteratorBase<?> original = this instanceof QueryIterator ? ((QueryIterator<T>) this).original : this;
        int before = original.index;

        original.First();
        while (original.Valid())
        {
            Object transformed = Transform(original.Current(), original.index);

            if (transformed.getClass() != EmptyElement.class) collector.run(collection, (T) transformed);
            original.Next();
        }

        original.index = before;
        return collection;
    }

    @Override
    public List<T> ToList() { return Collect(new List<>(), (c, x) -> c.Append(x)); }

    private Object Transform(Object element, int index)
    {
        if (!(this instanceof QueryIterator)) return element;

        LinkedList<QueryAction<Object, Object>> queue = ((QueryIterator<T>) this).queue;
        for (QueryAction<Object, Object> x : queue) { element = x.run(element, index); }

        return element;
    }
}