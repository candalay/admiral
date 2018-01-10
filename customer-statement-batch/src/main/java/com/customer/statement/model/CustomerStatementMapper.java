package com.customer.statement.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CustomerStatementMapper  implements FieldSetMapper<CustomerStatement>{
 
    @Override
    public CustomerStatement mapFieldSet(FieldSet fieldSet) throws BindException {
    	CustomerStatement result = new CustomerStatement();
        result.setReference(new BigInteger(fieldSet.readString(0)));
        result.setAccountNumber(fieldSet.readString(1));
        result.setDescription(fieldSet.readString(2));
        result.setStartBalance(new BigDecimal(fieldSet.readString(3)));
        result.setMutation(fieldSet.readString(4));
        result.setEndBalance(new BigDecimal(fieldSet.readString(5)));
        return result;
    }
}
