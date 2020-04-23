package com.spring.microfinance.collectionagent.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microfinance.borrower.entity.Borrower;
import com.spring.microfinance.collectionagent.entity.CollectionAgent;
import com.spring.microfinance.collectionagent.service.CollectionAgentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/collectionagent")
public class CollectionAgentController {

	@Autowired
	private CollectionAgentService collectionAgentServiceImpl;

	@ApiOperation(value = "Creates a Collection")
	@PostMapping
	public ResponseEntity<CollectionAgent> createCollectionAgent(@RequestBody CollectionAgent collectionAgent) {
		return new ResponseEntity<CollectionAgent>(collectionAgentServiceImpl.createCollectionAgent(collectionAgent),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves active Collection Agent detail, based on given mobile number")
	@GetMapping(value = "/{mobileNumber}")
	public ResponseEntity<CollectionAgent> getBorrower(@PathVariable String mobileNumber) {
		return new ResponseEntity<CollectionAgent>(collectionAgentServiceImpl.getCollectionAgentByMobile(mobileNumber),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves all active Collection Agent details")
	@GetMapping(value = "/all")
	public ResponseEntity<List<CollectionAgent>> getAllActiveBorrowers() {
		return new ResponseEntity<List<CollectionAgent>>(collectionAgentServiceImpl.getAllCollectionAgent(),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Updates Collection Agent details")
	@PutMapping
	public ResponseEntity<CollectionAgent> updateBorrower(@RequestBody CollectionAgent collectionAgent) {
		return new ResponseEntity<CollectionAgent>(collectionAgentServiceImpl.updateCollectionAgent(collectionAgent),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Makes Collection Agent visibilty to deleted using id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Borrower> deleteBorrower(@PathVariable String id) {
		collectionAgentServiceImpl.deleteCollectionAgent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
