package ru.skypro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.exceptions.EmptyListException;
import ru.skypro.exceptions.EmptyParameterException;
import ru.skypro.exceptions.MissingElementException;
import ru.skypro.exceptions.MissingNumberException;

import static ru.skypro.StringListConstans.*;


public class StringListImplTest {
    private final IntListImpl out = new IntListImpl();

    @Test
    public void testAddSmth() {
        Assertions.assertEquals(ONE, out.add(ONE));
        Assertions.assertEquals(1, out.size);
        Assertions.assertEquals(ONE, out.get(0));

        Assertions.assertEquals(TWO, out.add(TWO));
        Assertions.assertEquals(2, out.size);
        Assertions.assertEquals(TWO, out.get(1));

        Assertions.assertEquals(THREE, out.add(THREE));
        Assertions.assertEquals(3, out.size);
        Assertions.assertEquals(THREE, out.get(2));

        Assertions.assertEquals(ZERO, out.add(ZERO));
        Assertions.assertEquals(4, out.size);
        Assertions.assertEquals(ZERO, out.get(3));

        Assertions.assertEquals(MINUSONE, out.add(MINUSONE));
        Assertions.assertEquals(5, out.size);
        Assertions.assertEquals(MINUSONE, out.get(4));
    }

    @Test
    public void testExceptionWhenAddingNullElement() {
        Assertions.assertThrows(EmptyParameterException.class, () -> out.add(null));
    }

    @Test
    public void testAddSmthIndex() {
        Assertions.assertEquals(ONE, out.add(1, ONE));
        Assertions.assertEquals(ONE, out.get(0));
        Assertions.assertEquals(1, out.size);


        Assertions.assertEquals(TWO, out.add(2, TWO));
        Assertions.assertEquals(TWO, out.get(1));
        Assertions.assertEquals(2, out.size);

        Assertions.assertEquals(THREE, out.add(4, THREE));
        Assertions.assertEquals(THREE, out.get(2));
        Assertions.assertEquals(3, out.size);

        Assertions.assertEquals(ZERO, out.add(8, ZERO));
        Assertions.assertEquals(ZERO, out.get(3));
        Assertions.assertEquals(4, out.size);

        Assertions.assertEquals(MINUSONE, out.add(10, MINUSONE));
        Assertions.assertEquals(MINUSONE, out.get(4));
        Assertions.assertEquals(5, out.size);
    }

    @Test
    public void testExceptionWhenAddingNullElementWithIndex() {
        Assertions.assertThrows(EmptyParameterException.class, () -> out.add(4, null));
        Assertions.assertThrows(EmptyParameterException.class, () -> out.add(-1, ONE));
    }

    @Test
    public void testSetSmthInIndex() {
        out.add(ONE);
        out.add(TWO);
        out.add(THREE);
        Assertions.assertEquals(MINUSONE, out.set(0, MINUSONE));
        Assertions.assertEquals(ZERO, out.set(1, ZERO));
        Assertions.assertEquals(MINUSONE, out.get(0));
        Assertions.assertEquals(ZERO, out.get(1));
        Assertions.assertEquals(THREE, out.get(2));
        Assertions.assertEquals(3, out.size);
    }

    @Test
    public void testExceptionWhenSettingNullElementWithIndex() {
        Assertions.assertThrows(EmptyParameterException.class, () -> out.set(1, null));
        Assertions.assertThrows(EmptyParameterException.class, () -> out.set(-1, ONE));
    }

    @Test
    public void testRemoveItem() {
        out.add(ONE);
        Assertions.assertEquals(ONE, out.remove(ONE));
        Assertions.assertEquals(0, out.size);

    }

    @Test
    public void testExceptionWhenRemovingNullElement() {
        Assertions.assertThrows(EmptyParameterException.class, () -> out.remove(null));
    }

    @Test
    public void testExceptionWhenRemovingMissingElement() {
        Assertions.assertThrows(MissingElementException.class, () -> out.remove(ONE));
    }

    @Test
    public void testRemoveIndex() {
        out.add(ONE);
        out.add(TWO);
        out.add(THREE);
        out.add(ZERO);

        Assertions.assertEquals(THREE, out.remove(2));
        Assertions.assertEquals(ZERO, out.get(2));
        Assertions.assertEquals(3, out.size);

    }

    @Test
    public void testExceptionWhenRemovingWrongIndex() {
        out.add(ONE);
        Assertions.assertThrows(EmptyParameterException.class, () -> out.remove(-1));
        Assertions.assertThrows(EmptyParameterException.class, () -> out.remove(1));

    }

    @Test
    public void testContainElement() {
        out.add(ONE);
        Assertions.assertTrue(out.contains(ONE));
        out.add(TWO);
        Assertions.assertTrue(out.contains(TWO));
        out.add(THREE);
        Assertions.assertTrue(out.contains(THREE));
        out.add(ZERO);
        Assertions.assertTrue(out.contains(ZERO));
        out.add(MINUSONE);
        Assertions.assertTrue(out.contains(MINUSONE));

        Assertions.assertFalse(out.contains(-100));


    }

    @Test
    public void testExceptionWhenFindEmptyElement() {
        Assertions.assertThrows(EmptyParameterException.class, () -> out.contains(null));

    }

    @Test
    public void testIndexOf() {
        out.add(ONE);
        out.add(TWO);
        Assertions.assertEquals(0, out.indexOf(ONE));
        Assertions.assertEquals(1, out.indexOf(TWO));
        Assertions.assertEquals(-1, out.indexOf(ZERO));
    }

    @Test
    public void testIndexOfExceptionWrongItem() {
        Assertions.assertThrows(EmptyParameterException.class, () -> out.indexOf(null));

    }

    @Test
    public void testLastIndexOf() {
        out.add(ONE);
        out.add(TWO);
        Assertions.assertEquals(0, out.lastIndexOf(ONE));
        Assertions.assertEquals(1, out.lastIndexOf(TWO));
        Assertions.assertEquals(-1, out.lastIndexOf(ZERO));
    }

    @Test
    public void testLastIndexOfExceptionWrongItem() {
        Assertions.assertThrows(EmptyParameterException.class, () -> out.lastIndexOf(null));

    }

    @Test
    public void testGet() {
        out.add(ONE);
        out.add(TWO);
        Assertions.assertEquals(ONE, out.get(0));
        Assertions.assertEquals(TWO, out.get(1));

    }

    @Test
    public void testGetExceptions() {
        out.add(ONE);
        Assertions.assertThrows(EmptyParameterException.class, () -> out.get(-1));
        Assertions.assertThrows(MissingNumberException.class, () -> out.get(1));

    }

    @Test
    public void testEquals() {
        out.add(ONE);
        out.add(TWO);

        IntList list = new IntListImpl();
        list.add(ONE);
        list.add(TWO);

        IntList list2 = new IntListImpl();
        list2.add(ONE);

        Assertions.assertTrue(out.equals(list));
        Assertions.assertFalse(out.equals(list2));

    }

    @Test
    public void testEqualsExceptions() {
        IntList list1 = new IntListImpl();
        Assertions.assertThrows(EmptyListException.class, () -> out.equals(list1));
    }

    @Test
    public void testSize() {
        Assertions.assertEquals(0, out.size);
        out.add(ONE);
        Assertions.assertEquals(1, out.size);
        out.add(TWO);
        Assertions.assertEquals(2, out.size);
        out.add(THREE);
        Assertions.assertEquals(3, out.size);
    }


    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(out.isEmpty());
        out.add(ONE);
        Assertions.assertFalse(out.isEmpty());
    }

    @Test
    public void testClear() {
        out.add(ONE);
        Assertions.assertFalse(out.isEmpty());
        out.clear();
        Assertions.assertTrue(out.isEmpty());
    }

    @Test
    public void testToArray() {
        out.add(ONE);
        out.add(TWO);
        out.add(THREE);
        Integer[] expected = new Integer[3];
        expected[0]=ONE;
        expected[1]=TWO;
        expected[2]=THREE;
        Assertions.assertEquals(3, out.toArray().length);
        Assertions.assertArrayEquals(expected,out.toArray());

    }


}
