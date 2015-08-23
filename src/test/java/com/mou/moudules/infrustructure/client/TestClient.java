package com.mou.moudules.infrustructure.client;

import java.util.List;

import org.junit.Test;
import org.mou.common.JsonUtil;

import com.mou.modules.infrustructure.domain.Client;
import com.mou.mongodb.base.springdb.dao.CommonDaoMongo;

public class TestClient {

	@Test
	public void testFindAll() {

		CommonDaoMongo cdm = new CommonDaoMongo();
		List<Client> clients = cdm.findAll(Client.class);

		System.out.println(JsonUtil.getPrettyJsonStr(clients));
	}
}
