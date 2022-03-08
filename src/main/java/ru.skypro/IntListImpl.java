package ru.skypro;

import ru.skypro.exceptions.EmptyListException;
import ru.skypro.exceptions.EmptyParameterException;
import ru.skypro.exceptions.MissingElementException;
import ru.skypro.exceptions.MissingNumberException;

public class IntListImpl implements IntList {
    public int size;
    public Integer[] arr;
    public int startLength = 4;

    public IntListImpl() {
        this.arr = new Integer[startLength];
    }

    public IntListImpl(int length) {
        this.arr = new Integer[length];
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new EmptyParameterException("Объект не задан");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = item;
                size++;
                return item;
            }
        }

        grow();
        arr[size] = item;
        size++;
        return item;
    }

    private void grow() {
        Integer[] newArr = new Integer[(int) ( size * 1.5)];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null || index < 0 || index > size-1) {
            throw new EmptyParameterException("Объект не задан или задан не верно");
        }

        if (index+1 >= arr.length-1) {
            grow();
        }

        for (int j = size; j > index; j--) {
            arr[j] = arr[j-1];
        }

        arr[index] = item;
        size++;
        return item;
    }


    @Override
    public Integer set(int index, Integer item) {
        if (item == null || index < 0) {
            throw new EmptyParameterException("Объект не задан или задан не верно");
        }
        if (index - 1 >= size) {
            throw new MissingNumberException("Нет элемента с таким номером");
        }
        arr[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null) {
            throw new EmptyParameterException("Объект не задан");
        }
        int index;
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                found = true;
                arr[i] = null;
                size--;
                index = i;
                for (int j = index; j < size; j++) {
                    arr[j] = arr[j + 1];
                }
            }
        }
        if (!found) {
            throw new MissingElementException("Данная строка не найдена");
        }
        return item;


    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new EmptyParameterException("Объект задан не верно");
        }
        if (index - 1 > size) {
            throw new MissingNumberException("Нет элемента с таким номером");
        }
        Integer deletedElement = arr[index];
        arr[index] = null;
        for (int i = 0; i < size; i++) {
            if (arr[i] == null) {
                arr[i] = arr[i + 1];
                arr[i + 1] = null;
                size--;
            }
        }
        return deletedElement;
    }

    @Override
    public boolean contains(Integer item) {
        if (arr[0] == null) {
            throw new EmptyParameterException("Лист пустой");
        }
        if (item == null) {
            throw new EmptyParameterException("Объект не задан");
        }
        if (size >= 2) {
            Integer[] sortingArr = new Integer[arr.length];
            System.arraycopy(arr, 0, sortingArr, 0, arr.length);
           sort(sortingArr,0,size-1);
            return containsElement(sortingArr, item);
        }
        if (arr[0].equals(item))
            return true;
        else return false;

    }

    private boolean containsElement(Integer[] arr, Integer item) {
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arr[mid])) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;

            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) {
            throw new EmptyParameterException("Объект не задан");
        }
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) {
            throw new EmptyParameterException("Объект не задан");
        }
        for (int i = size - 1; i > -1; i--) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0) {
            throw new EmptyParameterException("Объект задан не верно");
        }
        if (index > size - 1) {
            throw new MissingNumberException("Нет элемента с таким номером");
        }
        return arr[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if (otherList.isEmpty()) {
            throw new EmptyListException("Сравнение с пустным листом");
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!otherList.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return arr[0] == null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[size];
        System.arraycopy(arr, 0, result, 0, size);
        return result;
    }


    private void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public void sort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            sort(arr, begin, partitionIndex - 1);
            sort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }


}
