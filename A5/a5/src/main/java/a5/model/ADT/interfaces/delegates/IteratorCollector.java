package a5.model.ADT.interfaces.delegates;

import a5.model.ADT.interfaces.IIterable;

public interface IteratorCollector<T, R extends IIterable<T>> { public void run(R collection, T element); }