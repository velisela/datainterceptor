package com.oracle.datainterceptor.databuilders;

import com.oracle.datainterceptor.exceptions.IncorrectDataFormatException;
import com.oracle.datainterceptor.holders.CustomerDataHolder;
import com.oracle.datainterceptor.holders.VisitableDataHolder;
import com.oracle.datainterceptor.model.CustomerDetails;

/**
 * 
 * @author velisela
 * This class builds the  CustomerDataHolder which is a VisitableDataHolder from the raw input data, when invoked from data builder factory 
 */
public class CustomerDataBuilder implements DataBuilder {

    public VisitableDataHolder buildData(String data) throws IncorrectDataFormatException {
        CustomerDataHolder customerDataHolder = new CustomerDataHolder();
        String[] rowSplit = data.split("\r\n");
        for(String row : rowSplit) {
            String[] columnSplit = row.split(",");
            if(columnSplit.length!=6) {
                throw new IncorrectDataFormatException();
            }
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setCustomerId(columnSplit[0]);
            customerDetails.setContractId(columnSplit[1]);
            customerDetails.setGeozone(columnSplit[2]);
            customerDetails.setTeamcode(columnSplit[3]);
            customerDetails.setProjectcode(columnSplit[4]);
            customerDetails.setBuildDuration(columnSplit[5]);
            customerDataHolder.addCustomerDetails(customerDetails);
        }
        return customerDataHolder;
    }

}
