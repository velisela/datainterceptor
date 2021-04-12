package com.oracle.datainterceptor.factory;

import com.oracle.datainterceptor.databuilders.CustomerDataBuilder;
import com.oracle.datainterceptor.databuilders.DataBuilder;
import com.oracle.datainterceptor.databuilders.VendorDataBuilder;
import com.oracle.datainterceptor.model.BuilderDatatype;

/**
 * 
 * @author velisela
 * This class creates instances of DataBuilder based on the type of data
 *  the user wanted to build
 */
public class DataBuilderFactory {

    public static DataBuilder getDataBuilder(String builderDataType) {
        if(builderDataType.equalsIgnoreCase(BuilderDatatype.CUSTOMER_DETAILS.name())) {
            return new CustomerDataBuilder();
        }
        if(builderDataType.equalsIgnoreCase(BuilderDatatype.VENDOR_DETAILS.name())) {
            return new VendorDataBuilder();
        }
        return null;
    }
}
