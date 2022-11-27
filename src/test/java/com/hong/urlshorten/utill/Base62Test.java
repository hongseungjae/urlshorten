package com.hong.urlshorten.utill;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Base62Test {

    Base62 base62 = new Base62();

    @Test
    public void test(){

        //given
        long index = Long.MAX_VALUE;
        String encoding = base62.encoding(index);
        System.out.println("encoding = " + encoding);

        //when
        long decoding = base62.decoding(encoding);
        System.out.println("decoding = " + decoding);

        //then
        Assertions.assertEquals(decoding,index);

    }


}