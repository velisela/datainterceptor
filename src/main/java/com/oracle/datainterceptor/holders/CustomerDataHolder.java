package com.oracle.datainterceptor.holders;

import java.util.ArrayList;
import java.util.List;

import com.oracle.datainterceptor.model.CustomerDetails;
import com.oracle.datainterceptor.queries.VisitorQuery;

/**
 * 
 * @author velisela
 * This class holds the data of customers in a list data structure of a model object
 *
 */
public class CustomerDataHolder implements VisitableDataHolder {

    private List<CustomerDetails> customerDetailHolder;

    public CustomerDataHolder() {
        customerDetailHolder = new ArrayList<CustomerDetails>();
    }

    public void addCustomerDetails(CustomerDetails customerDetail) {
        if(customerDetailHolder==null) {
            customerDetailHolder = new ArrayList<CustomerDetails>();
        }
        else {
            customerDetailHolder.add(customerDetail);
        }
    }

    public List<CustomerDetails> getAllCustomerDetails() {
        return customerDetailHolder;
    }

    public void acceptQuery(VisitorQuery visitor) {
        visitor.visit(this);
    }

}
