package com.oracle.datainterceptor.factory;

import com.oracle.datainterceptor.factory.service.ParseType;
import com.oracle.datainterceptor.factory.service.impl.FileParser;
import com.oracle.datainterceptor.factory.service.impl.StringParser;
import com.oracle.datainterceptor.model.InputTypes;

/**
 * 
 * @author velisela
 * ParseType factory class to derive which class to instantiate
 */
public class ParserTypeFactory {
    //use getType method to get the obejct of parse type   
    public static ParseType getType(String type){  
        if(type == null){  
            return null;  
        }  
        if(type.equalsIgnoreCase(InputTypes.FILE.name())) {  
            //we can make this object as singleton as well 
            return new FileParser();  
        }   
        else if(type.equalsIgnoreCase(InputTypes.STRING.name())){  
            //we can make this object as singleton as well 
            return new StringParser();  
        }   
        return null;  
    }  
}
