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
 * It is a visitorQuery that queries the data holder for average duration that takes 
 * for a given geozone
 *
 */
public class AverageDurationGeozoneQuery implements VisitorQuery {
    private Map<String, Set<Integer>> geoDurationMap;

    public void visit(VisitableDataHolder visitable) {
        CustomerDataHolder holder = (CustomerDataHolder) visitable;
        List<CustomerDetails> customers = holder.getAllCustomerDetails();
        geoDurationMap = new HashMap<String, Set<Integer>>();
        for(CustomerDetails customer : customers) {
            if(!geoDurationMap.containsKey(customer.getGeozone())) {
                Set<Integer> durationSet = new HashSet<Integer>();
                durationSet.add(getDurationByText(customer.getBuildDuration()));
                geoDurationMap.put(customer.getGeozone(), durationSet);
            }
            else {
                Set<Integer> durationSet = geoDurationMap.get(customer.getGeozone());
                durationSet.add(getDurationByText(customer.getBuildDuration()));
            }
        }
    }

    private Integer getDurationByText(String durationText) {
        durationText = durationText.substring(0, durationText.length()-1); //to omit units 'seconds' from the string
        return Integer.parseInt(durationText);
    }

    /**
     * 
     * @param geozone
     * @return the average build duration taken by a geozone
     */
    public double getAverageDurationByGeozone(String geozone) {
        Set<Integer> durationSet = geoDurationMap.get(geozone);
        double sum = 0;
        if(durationSet!=null) {
            for(Integer value : durationSet) {
                sum+=value;
            }
            return sum/durationSet.size();
        }
        return -1;

    }

}
