package com.hdu;

import javax.swing.table.TableRowSorter;
import java.util.List;

/**
 * @Author: ssf
 * @Date: 2020/3/5 3:37 下午
 */
public class TestChainList<T extends Comparable> {

    private T value;
    private TestChainList<T> next;
    private TestChainList<T> latest;


    public TestChainList(T value) {
        this.value = value;
    }

    public void add(T t){
        TestChainList  tmpChainList = new TestChainList(t);
        if (t.compareTo(value) == -1 && (t.compareTo(latest.value) == 1) || t.compareTo(latest.value) == 0) {
            tmpChainList.latest = this.latest;
            tmpChainList.next = this;
        }else {
            next.add(t);
        }
    }


    public void delete(T t){
        if (t.equals(value)){
            latest.next = this.latest;
            latest.latest = this.next;
        }
        if (next == null)
            return;
        next.delete(t);

    }


}
