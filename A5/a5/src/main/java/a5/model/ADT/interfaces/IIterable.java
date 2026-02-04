package a5.model.ADT.interfaces;

import a5.model.ADT.implementations.iterators.QueryIterator;
import a5.model.ADT.interfaces.delegates.*;

public interface IIterable<T>
{
    /**
     * Returns a new, separate instance of the current {@code IIterable<T>} containing the shallow copies of its elements.
     */
    public IIterable<T> Clone();

    public IIterator<T> GetIterator();

    default void ForEach(IndexedIteratorAction<T> action) { GetIterator().ForEach(action); }
    default void ForEach(IteratorAction<T> action) { GetIterator().ForEach(action); }

    default <R> QueryIterator<R> Select(IndexedIteratorSelector<T, R> selector) { return GetIterator().Select(selector); }
    default <R> QueryIterator<R> Select(IteratorSelector<T, R> selector) { return GetIterator().Select(selector); }

    default QueryIterator<T> Where(IndexedIteratorPredicate<T> predicate) { return GetIterator().Where(predicate); }
    default QueryIterator<T> Where(IteratorPredicate<T> predicate) { return GetIterator().Where(predicate); }
}