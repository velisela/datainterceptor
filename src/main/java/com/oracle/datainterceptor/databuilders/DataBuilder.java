package com.oracle.datainterceptor.databuilders;

import com.oracle.datainterceptor.exceptions.DataInterceptorException;
import com.oracle.datainterceptor.holders.VisitableDataHolder;

/**
 * 
 * @author velisela
 * An interface that defines how data builders should be
 *
 */
public interface DataBuilder {
    public VisitableDataHolder buildData(String data) throws DataInterceptorException;
}
