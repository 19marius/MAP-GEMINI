package a5.model.ADT.interfaces;

import a5.model.ADT.implementations.iterators.QueryIterator;
import a5.model.ADT.interfaces.delegates.*;
import a5.model.ADT.implementations.List;

public interface IIterator<T>
{
    public boolean Valid();

    public void First();

    public void Next();

    public T Current();

    public void ForEach(IndexedIteratorAction<T> action);
    public void ForEach(IteratorAction<T> action);

    public <R> QueryIterator<R> Select(IndexedIteratorSelector<T, R> selector);
    public <R> QueryIterator<R> Select(IteratorSelector<T, R> selector);

    public QueryIterator<T> Where(IndexedIteratorPredicate<T> predicate);
    public QueryIterator<T> Where(IteratorPredicate<T> predicate);

    public <R extends IIterable<T>> R Collect(R collection, IndexedIteratorCollector<T, R> collector);
    public <R extends IIterable<T>> R Collect(R collection, IteratorCollector<T, R> collector);

    public List<T> ToList();
}