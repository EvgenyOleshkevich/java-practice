package com.example.demo.controllers;

import com.example.demo.CodeResponse;
import com.example.demo.models.*;
import com.example.demo.repositories.CustomerRepo;
import com.example.demo.repositories.ItemRepo;
import com.example.demo.repositories.SellerRepo;
import com.example.demo.repositories.VariableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private SellerRepo sellers;
    @Autowired
    private CustomerRepo customers;
    @Autowired
    private ItemRepo items;
    @Autowired
    private VariableRepo orders;

    @GetMapping("/customerSignIn/{login}/{password}")
    public ResponseEntity<Response> customerSignIn(@PathVariable("login") String login,
                                                   @PathVariable("password") String password) {
        Optional<Customer> customer = customers.findById(login);
        if (customer.isPresent()) {
            if (customer.get().getPassword().equals(password)) {
                return ResponseEntity.ok(new Response(customer.get().getAmount(),
                        CodeResponse.Ok));
            } else {
                return ResponseEntity.ok(new Response(0,
                        CodeResponse.InvalidPassword));
            }
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.UserLoginDontExist));
        }
    }

    @GetMapping("/customerSignUp/{login}/{password}")
    public ResponseEntity<Response> customerSignUp(@PathVariable("login") String login,
                                                   @PathVariable("password") String password) {
        Optional<Customer> customer = customers.findById(login);
        if (customer.isPresent()) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.UserLoginAlredyExist));
        } else {
            customers.save(new Customer(login, password));
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.Ok));
        }

    }

    @GetMapping("/deleteCustomer/{login}/{password}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable("login") String login,
                                                   @PathVariable("password") String password) {
        Optional<Customer> customer = customers.findById(login);
        if (customer.isPresent() && customer.get().getPassword().equals(password)) {
            customers.delete(customer.get());
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.Ok));
    }

    @GetMapping("/customer/addAmount/{login}/{value}")
    public ResponseEntity<Response> addAmountCustomer(@PathVariable("login") String login,
                                                   @PathVariable("value") int value) {
        Optional<Customer> customer = customers.findById(login);
        if (customer.isPresent()) {
            customer.get().setAmount(customer.get().getAmount() + value);
            customers.save(customer.get());
            return ResponseEntity.ok(new Response(customer.get().getAmount(),
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.UserLoginDontExist));
        }

    }

    @GetMapping("/sellerSignIn/{login}/{password}")
    public ResponseEntity<Response> sellerSignIn(@PathVariable("login") String login,
                                                   @PathVariable("password") String password) {
        Optional<Seller> seller = sellers.findById(login);
        if (seller.isPresent()) {
            if (seller.get().getPassword().equals(password)) {
                return ResponseEntity.ok(new Response(seller.get().getAmount(),
                        CodeResponse.Ok));
            } else {
                return ResponseEntity.ok(new Response(0,
                        CodeResponse.InvalidPassword));
            }
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.UserLoginDontExist));
        }
    }

    @GetMapping("/sellerSignUp/{login}/{password}")
    public ResponseEntity<Response> sellerSignUp(@PathVariable("login") String login,
                                                   @PathVariable("password") String password) {
        Optional<Seller> seller = sellers.findById(login);
        if (seller.isPresent()) {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.UserLoginAlredyExist));
        } else {
            sellers.save(new Seller(login, password, "withot description"));
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.Ok));
        }
    }

    @GetMapping("/deleteSeller/{login}/{password}")
    public ResponseEntity<Response> deleteSeller(@PathVariable("login") String login,
                                                 @PathVariable("password") String password) {
        Optional<Seller> seller = sellers.findById(login);
        Iterable<Item> itemIterable = items.findAll();
        Iterable<Variable> orderIterable = orders.findAll();
        if (seller.isPresent() && seller.get().getPassword().equals(password)) {
            for (Variable order: orderIterable) {
                if (order.getSeller().equals(login)){
                    orders.delete(order);
                }
            }
            for (Item item: itemIterable) {
                if (item.getSeller().equals(login)){
                    items.delete(item);
                }
            }
            sellers.delete(seller.get());
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.Ok));
    }

    @GetMapping("/buying/{loginCustomer}/{loginSeller}/{idItem}/{address}")
    public ResponseEntity<Response> buying(@PathVariable("loginCustomer") String loginCustomer,
                                           @PathVariable("loginSeller") String loginSeller,
                                           @PathVariable("idItem") long idItem,
                                           @PathVariable("address") String address) {
        Optional<Customer> customer = customers.findById(loginCustomer);
        Optional<Seller> seller = sellers.findById(loginSeller);
        Optional<Item> item = items.findById(idItem);
        if (customer.isPresent() && seller.isPresent() && item.isPresent()) {
            if (customer.get().getAmount() - item.get().getCost() < 0) {
                return ResponseEntity.ok(new Response(-1,
                        CodeResponse.NotEnoughmoneyToBuy));
            }
            customer.get().setAmount(customer.get().getAmount() - item.get().getCost());
            seller.get().setAmount(seller.get().getAmount() + item.get().getCost());
            Variable order = new Variable();
            order.setItem(item.get().getID());
            order.setSeller(seller.get().getLogin());
            order.setAddress(address);
            order.setName(item.get().getName() + "_" + (new Date()).hashCode());
            orders.save(order);
            customers.save(customer.get());
            sellers.save(seller.get());
            return ResponseEntity.ok(new Response(customer.get().getAmount(),
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.UserLoginDontExist));
        }

    }

    @GetMapping("/seller/setDescription/{login}/{password}/{description}")
    public ResponseEntity<Response> sellerSetDescription(@PathVariable("login") String login,
                                                         @PathVariable("password") String password,
                                                         @PathVariable("description") String description) {
        Optional<Seller> seller = sellers.findById(login);
        if (seller.isPresent() && seller.get().getPassword().equals(password)) {
            seller.get().setDescription(description);
            sellers.save(seller.get());
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.UserLoginDontExist));
        }

    }

    @GetMapping("/getSellers")
    public @ResponseBody List<SellerRequest> getSellers() {
        Iterable<Seller> sellerIterable = sellers.findAll();
        List<SellerRequest> sellerList = new ArrayList<SellerRequest>();

        for (Seller seller : sellerIterable) {
            sellerList.add(new SellerRequest(seller.getLogin(), seller.getDescription()));
        }
        return sellerList;
    }

    @GetMapping("/seller/addItem/{login}/{name}/{cost}/{description}")
    public ResponseEntity<Response> addItem(@PathVariable("login") String login,
                                            @PathVariable("name") String name,
                                            @PathVariable("cost") int cost,
                                            @PathVariable("description") String description) {
        Optional<Seller> seller = sellers.findById(login);
        if (seller.isPresent()) {
            Item item = new Item();
            item.setSeller(seller.get().getLogin());
            item.setName(name);
            item.setCost(cost);
            item.setDescription(description);
            items.save(item);
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.Ok));
        } else {
            return ResponseEntity.ok(new Response(0,
                    CodeResponse.UserLoginDontExist));
        }
    }

    @GetMapping("/seller/deleteItem/{login}/{password}/{id}")
    public ResponseEntity<Response> deleteItem(@PathVariable("login") String login,
                                               @PathVariable("password") String password,
                                               @PathVariable("id") long idItem) {
        Optional<Seller> seller = sellers.findById(login);
        Optional<Item> item = items.findById(idItem);
        if (seller.isPresent() && seller.get().getPassword().equals(password)
        && item.isPresent()) {
            items.delete(item.get());
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.Ok));
    }

    @GetMapping("/getItems/{loginSeller}")
    public @ResponseBody List<Item> getItemBySeller(@PathVariable("loginSeller") String loginSeller) {
        Optional<Seller> seller = sellers.findById(loginSeller);
        if (seller.isPresent()) {
            Iterable<Item> itemIterable = items.findAll();
            List<Item> itemBySeller = new ArrayList<Item>();

            for (Item item :
                    itemIterable) {
                if (item.getSeller().equals(seller.get().getLogin()))
                    itemBySeller.add(item);
            }
            return itemBySeller;
        } else {
            return new ArrayList<Item>();
        }
    }

    @GetMapping("/getOrders/{loginSeller}")
    public @ResponseBody List<Order> getOrderBySeller(@PathVariable("loginSeller") String loginSeller) {
        Optional<Seller> seller = sellers.findById(loginSeller);
        if (seller.isPresent()) {
            Iterable<Variable> orderIterable = orders.findAll();
            List<Order> orderBySeller = new ArrayList<Order>();

            for (Variable order :
                    orderIterable) {
                if (order.getSeller().equals(seller.get().getLogin()))
                    orderBySeller.add(new Order(order));
            }
            return orderBySeller;
        } else {
            return new ArrayList<Order>();
        }
    }

    @GetMapping("/seller/deleteOrder/{login}/{password}/{id}")
    public ResponseEntity<Response> deleteOrder(@PathVariable("login") String login,
                                               @PathVariable("password") String password,
                                               @PathVariable("id") long idOrder) {
        Optional<Seller> seller = sellers.findById(login);
        Optional<Variable> order = orders.findById(idOrder);
        if (seller.isPresent() && seller.get().getPassword().equals(password)
                && order.isPresent() && order.get().getSeller().equals(login)) {
            orders.delete(order.get());
        }
        return ResponseEntity.ok(new Response(0,
                CodeResponse.Ok));
    }

    @GetMapping("/users")
    public @ResponseBody List<Customer> users() {
        Iterable<Customer> customersIterable = customers.findAll();
        List<Customer> activities = new ArrayList<Customer>();

        for (Customer c:
                customersIterable) {
            activities.add(c);
        }

        return activities;
    }
}
