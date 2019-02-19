package com.ant.base.baseAdapter.base;

import android.support.v4.util.SparseArrayCompat;


public class ItemViewDelegateManager<T>
{
    SparseArrayCompat<ItemAdapter<T>> delegates = new SparseArrayCompat();

    public int getItemViewDelegateCount()
    {
        return delegates.size();
    }

    public ItemViewDelegateManager<T> addDelegate(ItemAdapter<T> delegate)
    {
        int viewType = delegates.size();
        if (delegate != null)
        {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemAdapter<T> delegate)
    {
        if (delegates.get(viewType) != null)
        {
            throw new IllegalArgumentException(
                    "An ItemAdapter is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemAdapter is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(ItemAdapter<T> delegate)
    {
        if (delegate == null)
        {
            throw new NullPointerException("ItemAdapter is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType)
    {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            ItemAdapter<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType( item, position))
            {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemAdapter added that matches position=" + position + " in data source");
    }

    public void convert(ViewHolder holder, T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            ItemAdapter<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType( item, position))
            {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    public ItemAdapter getItemViewDelegate(int viewType)
    {
        return delegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType)
    {
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemAdapter itemViewDelegate)
    {
        return delegates.indexOfValue(itemViewDelegate);
    }
}
