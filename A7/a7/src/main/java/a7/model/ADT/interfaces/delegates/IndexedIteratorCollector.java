package a7.model.ADT.interfaces.delegates;

import a7.model.ADT.interfaces.IIterable;

public interface IndexedIteratorCollector<T, R extends IIterable<T>> { public void run(R collection, T element, int index); }