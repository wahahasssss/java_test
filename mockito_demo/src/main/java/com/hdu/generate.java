package com.hdu;

import org.junit.Test;

import java.util.List;
import static org.mockito.Mockito.*;
/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/15
 * @Time 下午2:57
 */
public class generate {

    @Test
    public void test(){
        List listMock = mock(List.class);
        listMock.add("one");
        listMock.clear();




        verify(listMock).add("one");
        verify(listMock).clear();

        when(listMock.get(0)).thenReturn("z");


        assert listMock.get(0) == "one";
    }


}
