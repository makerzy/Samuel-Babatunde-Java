package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Main {

    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        //Update this
        List<Customer> customers = new ArrayList<>();
        ListIterator<String[]> iterator = customerData.listIterator();
        while (iterator.hasNext()){
            String[] cData = iterator.next();
//            convert the id to int type
            int id = Integer.parseInt(cData[0]);
            String name = cData[1];
            int charge = Integer.parseInt(cData[2]);
            String date = cData[3];
            AccountRecord accountRecord = new AccountRecord();
            accountRecord.setCharge(charge);
            accountRecord.setChargeDate(date);
            Customer customer;
//            Check if we have a record for the customer ID
            if(customers.size() >=id){
//                get the customer at the index (id) -1
                customer = customers.get(id-1);
            }else{
//                create a new customer and add to the customer list
                customer = new Customer();
                customers.add(customer);
            }
            customer.setId(id);
            customer.setName(name);
            customer.getCharges().add(accountRecord);
        }
//        List<Customer> posCustomers = customers.stream().filter(customer -> customer.getBalance()>=0)
//                .collect(Collectors.toList());
//        List<Customer> negCustomers= customers.stream().filter(customer -> customer.getBalance() < 0)
//                .collect(Collectors.toList());

        System.out.println("Positive accounts:");
        customers.stream().filter(customer -> customer.getBalance()>=0)
                .forEach(System.out::println);
        System.out.println("Negative accounts:");
        customers.stream().filter(customer -> customer.getBalance() < 0)
                .forEach(System.out::println);
    }
}
