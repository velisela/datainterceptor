package com.oracle.datainterceptor.queries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oracle.datainterceptor.holders.CustomerDataHolder;
import com.oracle.datainterceptor.holders.VisitableDataHolder;
import com.oracle.datainterceptor.model.CustomerDetails;

/**
 * 
 * @author velisela
 * It is a visitorQuery that queries the data holder for number of unique customers
 * for a given contractId
 *
 */
public class UniqueCustomerContractQuery implements VisitorQuery {
    private Map<String, Set<String>> contractCustomerMap;

    public void visit(VisitableDataHolder visitable) {
        CustomerDataHolder holder = (CustomerDataHolder) visitable;
        List<CustomerDetails> customers = holder.getAllCustomerDetails();
        contractCustomerMap = new HashMap<String, Set<String>>();
        for(CustomerDetails customer : customers) {
            if(!contractCustomerMap.containsKey(customer.getContractId())) {
                Set<String> customerSet = new HashSet<String>();
                customerSet.add(customer.getCustomerId());
                contractCustomerMap.put(customer.getContractId(), customerSet);
            }
            else {
                Set<String> customerSet = contractCustomerMap.get(customer.getContractId());
                //The set will add the customerId in case of it's non existence.
                //Thus unique customerIds or count will be maintained per contractId in the map.
                customerSet.add(customer.getCustomerId());
            }
        }
    }

    /**
     * 
     * @param contractId
     * @return number of customer for a given contractId
     */
    public int getCustomerCountByContract(String contractId) {
        Set<String> customerSet = contractCustomerMap.get(contractId);
        return (customerSet!=null)? customerSet.size(): -1;
    }

}
