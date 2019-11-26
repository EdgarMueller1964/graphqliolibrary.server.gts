package com.thinkenterprise.graphqlio.server.gts.tracking;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thinkenterprise.graphqlio.server.gts.tracking.GtsRecord;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsRecord.GtsArityType;
import com.thinkenterprise.graphqlio.server.gts.tracking.GtsRecord.GtsOperationType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGraphQLIORecordStringifyRegExp {

	
	final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger();

	
//	// optional parameters 
//	private String srcType = null; 
//	private String srcId = null; 
//	private String srcAttr = null; 
//
//	/// required Parameters
//	private String op = ""; 	
//	private String arity = "";
//	private String dstType = "";
//	private String dstIds[] = null;
//	private String dstAttrs[] = null;
	
	@Test
	public void testStringify() {

		GtsRecord record = GtsRecord.builder().op(GtsOperationType.READ)
				.arity(GtsArityType.ONE)
				.dstType("dstType")
				.dstIds(new String[] {"dstId1", "dstId2"})
				.dstAttrs(new String[] {"dstAttr1", "dstAttr2"})
				.build();
		
		String strRecord = record.stringify();
//		log.info("strRecord =" + strRecord);
//		
//		boolean bMatchesRegExp = GraphQLIORecord.matchesPredefinedPattern(strRecord);
//		Assert.assertTrue(bMatchesRegExp);
		
		GtsRecord recordFromString = GtsRecord.builder().stringified(strRecord).build();
		
		Assert.assertTrue(recordFromString != null && record != null && recordFromString.equals(record));
		
		
	}
	
	
}
