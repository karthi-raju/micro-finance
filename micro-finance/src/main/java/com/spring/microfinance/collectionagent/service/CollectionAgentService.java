package com.spring.microfinance.collectionagent.service;

import java.util.List;

import com.spring.microfinance.collectionagent.entity.CollectionAgent;

public interface CollectionAgentService {

	public CollectionAgent getCollectionAgentByMobile(String collectionAgentMobileNumber);

	public List<CollectionAgent> getAllCollectionAgent();

	public void deleteCollectionAgent(String id);

	public CollectionAgent updateCollectionAgent(CollectionAgent collectionAgent);

	public CollectionAgent createCollectionAgent(CollectionAgent collectionAgent);

	public CollectionAgent getCollectionAgentById(String id);

}
