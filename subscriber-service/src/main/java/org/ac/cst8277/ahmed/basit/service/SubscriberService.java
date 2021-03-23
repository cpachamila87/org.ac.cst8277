package org.ac.cst8277.ahmed.basit.service;

import org.ac.cst8277.ahmed.basit.util.BaseResponse;

public interface SubscriberService {
    BaseResponse createSubscription(long subId, long pubId);
    BaseResponse confirmSubscription(long subId, long pubId);
    BaseResponse editSubscription();
    BaseResponse removeSubscription(long subId, long pubId);
    BaseResponse listSubscriptions(long subId);
    BaseResponse listMessages(long subId);
}
