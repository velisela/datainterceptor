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
 * and list of unique customer for a given geozone
 *
 */
public class UniqueCustomerGeozoneQuery implements VisitorQuery {
    private Map<String, Set<String>> geoCustomerMap;

    public void visit(VisitableDataHolder visitable) {
        CustomerDataHolder holder = (CustomerDataHolder) visitable;
        List<CustomerDetails> customers = holder.getAllCustomerDetails();
        geoCustomerMap = new HashMap<String, Set<String>>();
        for(CustomerDetails customer : customers) {
            if(!geoCustomerMap.containsKey(customer.getGeozone())) {
                Set<String> customerSet = new HashSet<String>();
                customerSet.add(customer.getCustomerId());
                geoCustomerMap.put(customer.getGeozone(), customerSet);
            }
            else {
                Set<String> customerSet = geoCustomerMap.get(customer.getGeozone());
                //The set will add the customerId in case of it's non existence.
                //Thus unique customerIds or count will be maintained per geozoneId in the map.
                customerSet.add(customer.getCustomerId());
            }
        }
    }

    /**
     * 
     * @param geozone
     * @return number of customers per geozone
     */
    public int getCustomerCountByGeozone(String geozone) {
        Set<String> customerSet = geoCustomerMap.get(geozone);
        return (customerSet!=null)?customerSet.size():-1;
    }

    /**
     * 	
     * @param geozone
     * @return set of customers for a given geozone
     */
    public Set<String> getUniqueCustomerByGeozone(String geozone) {
        return geoCustomerMap.get(geozone);
    }

}
