package a7.model.ADT.interfaces;

import a7.model.ADT.implementations.Dictionary;
import a7.model.ADT.implementations.iterators.QueryIterator;
import a7.model.ADT.interfaces.delegates.*;
import a7.model.ADT.implementations.List;

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

    public <TKey, TValue> Dictionary<TKey, TValue> ToDictionary(IndexedIteratorSelector<T, TKey> key_selector, IndexedIteratorSelector<T, TValue> value_selector);
    public <TKey, TValue> Dictionary<TKey, TValue> ToDictionary(IteratorSelector<T, TKey> key_selector, IteratorSelector<T, TValue> value_selector);
    public <TKey, TValue> Dictionary<TKey, TValue> ToDictionary(IndexedIteratorSelector<T, TKey> key_selector);
    public <TKey, TValue> Dictionary<TKey, TValue> ToDictionary(IteratorSelector<T, TKey> key_selector);
    
}