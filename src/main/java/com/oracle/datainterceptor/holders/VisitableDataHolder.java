package com.oracle.datainterceptor.holders;

import com.oracle.datainterceptor.queries.VisitorQuery;
/**
 * 
 * @author velisela
 * This is interface for dataholder
 *
 */
public interface VisitableDataHolder {
    public void acceptQuery(VisitorQuery visitor);
}
