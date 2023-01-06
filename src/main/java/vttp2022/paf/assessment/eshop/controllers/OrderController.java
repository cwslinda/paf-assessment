package vttp2022.paf.assessment.eshop.controllers;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@RestController
@RequestMapping(path = "/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private CustomerRepository custRepo;

	@GetMapping(path="/name",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchRSVPByName(@RequestParam(value="q", required=true) String name) {
        Optional<Customer> customer = custRepo.findCustomerByName(name);

        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Customer %s not found".formatted(name));
        }

       

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
            .body("Customer exists");
    }


	@PostMapping(path = "/order", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postCheckout(@RequestBody JsonObject json) throws StreamReadException, DatabindException, IOException{

		System.out.println("name >>> " + json);

		byte[] jsonData = json.toString().getBytes();

        ObjectMapper mapper = new ObjectMapper();
        Customer customer = mapper.readValue(jsonData, Customer.class);

        System.out.print(customer.getName());
		// Optional<Customer> cusOptional = custRepo.findCustomerByName(name);
		// System.out.println("customer's name >>> " + cusOptional);

        // if (cusOptional.isEmpty()) {
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Customer %s not found".formatted(name));
        // }


		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
            .body("Customer exists");
	}
}