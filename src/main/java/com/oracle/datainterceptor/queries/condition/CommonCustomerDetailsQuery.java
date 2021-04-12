package com.oracle.datainterceptor.queries.condition;

import java.util.ArrayList;
import java.util.List;

import com.oracle.datainterceptor.exceptions.DataInterceptorException;
import com.oracle.datainterceptor.holders.VisitableDataHolder;
import com.oracle.datainterceptor.model.Condition;
import com.oracle.datainterceptor.model.CustomerDetails;
import com.oracle.datainterceptor.queries.VisitorQuery;

/**
 * 
 * @author velisela
 * It is a query class to for criteria queries 
 *
 */
public class CommonCustomerDetailsQuery implements VisitorQuery {

    public void visit(VisitableDataHolder visitable) {
        //TODO
    }
    
    public List<String> commonQuery(List<CustomerDetails> data, Condition condition, String field) throws DataInterceptorException {
        List<String> result = new ArrayList<String>();
        for(CustomerDetails details : data) {
            String fieldValue = details.getByField(condition.getField());
            if(fieldValue == null) {
                throw new DataInterceptorException("Not a valid field");
            }
            if(details.getByField(condition.getField()).equals(condition.getValue())) {
                result.add(details.getByField(field));
            }
        }
        return result;
    }

}
