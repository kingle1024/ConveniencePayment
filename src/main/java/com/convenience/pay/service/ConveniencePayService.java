package com.convenience.pay.service;

import com.convenience.pay.dto.PayMethodType;
import com.convenience.pay.dto.PayRequest;
import com.convenience.pay.dto.PayResponse;
import com.convenience.pay.type.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Component
public class ConveniencePayService {
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap =
            new HashMap<>();
    private final DiscountInterface discountInterface = new DiscountByPayMethod();
//    private final DiscountInterface discountInterface;
    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet,
                                 DiscountInterface discountByConvenience){
        paymentInterfaceSet.forEach( // 정보를 읽어와서 각각 저장해준다.
                paymentInterface -> paymentInterfaceMap.put(
                        paymentInterface.getPayMethodType(),
                        paymentInterface
                )
        );
//        this.discountInterface = discountByConvenience;
    }

    public PayResponse pay(PayRequest payRequest){
        PaymentInterface paymentInterface =
                paymentInterfaceMap.get(payRequest.getPayMethodType());

        Integer disCountedAmount =
                discountInterface.getDiscountedAmount(payRequest); // 할인 처리
        PaymentResult payment
                = paymentInterface.payment(disCountedAmount);

        // fail test
        if(payment == PaymentResult.PAYMENT_FAIL){
            return new PayResponse(PayResult.FAIL, 0);
        }

        // success case
        return new PayResponse(PayResult.SUCCESS, disCountedAmount);
    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest){
        PaymentInterface paymentInterface =
                paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

        CancelPaymentResult cancelPaymentResult =
                paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());

        if(cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL){
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,
                payCancelRequest.getPayCancelAmount());
    }
}
