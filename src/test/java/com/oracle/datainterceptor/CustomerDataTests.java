package com.oracle.datainterceptor;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.oracle.datainterceptor.databuilders.DataBuilder;
import com.oracle.datainterceptor.exceptions.DataInterceptorException;
import com.oracle.datainterceptor.factory.DataBuilderFactory;
import com.oracle.datainterceptor.factory.ParserTypeFactory;
import com.oracle.datainterceptor.factory.service.ParseType;
import com.oracle.datainterceptor.factory.service.impl.FileParser;
import com.oracle.datainterceptor.factory.service.impl.StringParser;
import com.oracle.datainterceptor.holders.CustomerDataHolder;
import com.oracle.datainterceptor.holders.VisitableDataHolder;
import com.oracle.datainterceptor.model.BuilderDatatype;
import com.oracle.datainterceptor.model.Condition;
import com.oracle.datainterceptor.model.InputTypes;
import com.oracle.datainterceptor.queries.AverageDurationGeozoneQuery;
import com.oracle.datainterceptor.queries.UniqueCustomerContractQuery;
import com.oracle.datainterceptor.queries.UniqueCustomerGeozoneQuery;
import com.oracle.datainterceptor.queries.condition.CommonCustomerDetailsQuery;

public class CustomerDataTests {

    @Test
    public void testTypeGeneration() throws DataInterceptorException {
        ParseType type = ParserTypeFactory.getType(InputTypes.STRING.name());
        assertTrue(type instanceof StringParser);
        type = ParserTypeFactory.getType(InputTypes.FILE.name());
        assertTrue(type instanceof FileParser);
    }

    @Test
    public void testCustomerDataQueries() throws DataInterceptorException {
        //Get proper input type
        ParseType type = ParserTypeFactory.getType(InputTypes.STRING.name());
        String rawData =  type.getInput();

        //Make data query ready using databuilders from DataBuilderFactory
        //DataBuilderFactory can give multiple objects for differnt types of data
        //The ENUM BuilderDatatype has an enumeration for different supported data types
        DataBuilder customerDataBuilder = DataBuilderFactory.getDataBuilder(BuilderDatatype.CUSTOMER_DETAILS.name());

        //Once the data is built it will be query ready for multiple queries
        VisitableDataHolder customerDataHolder = customerDataBuilder.buildData(rawData);

        //Each visitor query object which has its own mission will be passed as a query to the built data
        //The dataHolder emulates a table and the visitor objects behaves as a query
        UniqueCustomerContractQuery customerContractQuery = new UniqueCustomerContractQuery();
        customerDataHolder.acceptQuery(customerContractQuery);

        //Once the dataHolder visited by the visitor query we can query the data, by looking for get methods in it
        int customerCountByContract = customerContractQuery.getCustomerCountByContract("2345");
        assertEquals(3, customerCountByContract);

        UniqueCustomerGeozoneQuery customerGeoQuery = new UniqueCustomerGeozoneQuery();
        customerDataHolder.acceptQuery(customerGeoQuery);

        int customerCountByGeozone = customerGeoQuery.getCustomerCountByGeozone("us_west");
        assertEquals(2, customerCountByGeozone);

        customerCountByGeozone = customerGeoQuery.getCustomerCountByGeozone("us_east");
        assertEquals(1, customerCountByGeozone);

        Set<String> customerIds = customerGeoQuery.getUniqueCustomerByGeozone("us_west");
        assertEquals(2, customerIds.size());
        assertEquals(true, customerIds.contains("1233456"));
        assertEquals(false, customerIds.contains("3244332"));

        customerIds = customerGeoQuery.getUniqueCustomerByGeozone("us_east");
        assertEquals(1, customerIds.size());
        assertEquals(true, customerIds.contains("2343225"));
        assertEquals(false, customerIds.contains("3244332"));

        AverageDurationGeozoneQuery averageDurationquery = new AverageDurationGeozoneQuery();
        customerDataHolder.acceptQuery(averageDurationquery);
        assertEquals(4222.0, averageDurationquery.getAverageDurationByGeozone("eu_west"));
        assertEquals(2216.0, averageDurationquery.getAverageDurationByGeozone("us_west"));
    }

    @Test
    public void testCustomerDataBuilding() {
        String rawData = "2343225,us_east,RedTeam,ProjectApple,3445s\r\n" + 
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\r\n" + 
                "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n" + 
                "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\r\n" + 
                "3244132,2346,eu_west,YellowTeam3,ProjectEgg";

        //Make data query ready using databuilders from DataBuilderFactory
        //DataBuilderFactory can give multiple objects for differnt types of data
        //The ENUM BuilderDatatype has an enumeration for different supported data types
        DataBuilder customerDataBuilder = DataBuilderFactory.getDataBuilder(BuilderDatatype.CUSTOMER_DETAILS.name());

        //Once the data is built it will be query ready for multiple queries
        try {
            VisitableDataHolder customerDataHolder = customerDataBuilder.buildData(rawData);
            fail("Should get exception with wrong data");
        } catch (DataInterceptorException e) {
            assertEquals("The parsed data is of incorrect format, verify the input data", e.getMessage());
        }
    }

    @Test
    public void testCustomerDetailsCustomQuery() throws DataInterceptorException {
        ParseType type = ParserTypeFactory.getType(InputTypes.STRING.name());
        assertTrue(type instanceof StringParser);
        String rawData =  type.getInput();
        DataBuilder customerDataBuilder = DataBuilderFactory.getDataBuilder(BuilderDatatype.CUSTOMER_DETAILS.name());
        CustomerDataHolder customerDataHolder = (CustomerDataHolder) customerDataBuilder.buildData(rawData);
        assertEquals(5, customerDataHolder.getAllCustomerDetails().size());
        
        CommonCustomerDetailsQuery customerContractQuery = new CommonCustomerDetailsQuery();
        Condition condition = new Condition();
        condition.setField("contractId");
        condition.setValue("2345");
        List<String> customerIds = customerContractQuery.commonQuery(customerDataHolder.getAllCustomerDetails(), condition, "customerId");
        assertEquals(3, customerIds.size());
        
        condition = new Condition();
        condition.setField("contractId");
        condition.setValue("234544");
        customerIds = customerContractQuery.commonQuery(customerDataHolder.getAllCustomerDetails(), condition, "customerId");
        assertEquals(0, customerIds.size());
        
        condition = new Condition();
        condition.setField("contract-Id");//wrong field
        condition.setValue("2345");
        try {
            customerIds = customerContractQuery.commonQuery(customerDataHolder.getAllCustomerDetails(), condition, "customerId");
            fail("Wrong field value");
        }  catch (DataInterceptorException e) {
            assertEquals("Not a valid field", e.getMessage());
        }
    }

}
