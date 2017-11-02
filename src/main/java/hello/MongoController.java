package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "mongo")
public class MongoController {

	@Autowired
	private CustomerRepository repository;

	@ApiOperation(value = "Save a customer to the mongo db")
	@RequestMapping(value = "/mongo", method = RequestMethod.POST)
	public void saveCustomer(@RequestBody Customer customer) {
		repository.save(customer);
	}

	@ApiOperation(value = "Retrieve a customer from the mongo db")
	@RequestMapping(value = "/mongo", method = RequestMethod.GET)
	public List<Customer> getCustomer(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		
		List<Customer> matches = new ArrayList<Customer>();
		matches.addAll(repository.findByFirstName(firstName));
		matches.addAll(repository.findByLastName(lastName));

		return matches;
	}
}
