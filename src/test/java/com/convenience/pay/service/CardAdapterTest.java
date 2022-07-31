package com.convenience.pay.service;

import com.convenience.pay.type.CardUseCancelResult;
import com.convenience.pay.type.CardUseResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardAdapterTest {
    CardAdapter cardAdapter = new CardAdapter();
    @Test
    void capture_success(){
        //given
        Integer payAmount = 100;
        //when
        CardUseResult cardUseResult = cardAdapter.capture(payAmount);
        //then
        assertEquals(CardUseResult.USE_SUCCESS, cardUseResult);
    }
    @Test
    void capture_fail(){
        //given
        Integer payAmount = 101;
        //when
        CardUseResult cardUseResult = cardAdapter.capture(payAmount);
        //then
        assertEquals(CardUseResult.USE_FAIL, cardUseResult);
    }
    @Test
    void cancelCapture_success(){
        //given
        Integer cancelAmount = 1000;
        //when
        CardUseCancelResult cardUseCancelResult = cardAdapter.cancelCapture(cancelAmount);
        //then
        assertEquals(CardUseCancelResult.USE_CANCEL_SUCCESS, cardUseCancelResult);
    }
    @Test
    void cancelCapture_fail(){
        //given
        Integer cancelAmount = 999;
        //when
        CardUseCancelResult cardUseCancelResult = cardAdapter.cancelCapture(cancelAmount);
        //then
        assertEquals(CardUseCancelResult.USE_CANCEL_FAIL, cardUseCancelResult);
    }
}