import com.company.AccountRecord;
import com.company.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class CustomerTest {
    Customer customer;
    @BeforeEach
    public void makeCustomer(){
        customer = new Customer();
        List<String[]> customerData = Arrays.asList(
                new String[] {"10000","12-01-2021"},
                new String[] {"-7500","01-10-2022"},
                new String[] {"18000","12-22-2021"}
        );
        customerData.forEach(data->{
            AccountRecord record = new AccountRecord();
            record.setChargeDate(data[1]);
            record.setCharge(Integer.parseInt(data[0]));
            customer.getCharges().add(record);
        });
        customer.setName("Customer Name");
        customer.setId(1);
    }


    @Test
    public void shouldReturnCustomerBalance(){
        Assertions.assertEquals(20500, customer.getBalance());
    }

    @Test
    public void shouldReturnCustomerString(){
        int balance = customer.getBalance();
        Assertions.assertEquals(String.format("%d %s %d", 1, "Customer Name", balance), customer.toString());
    }

}
