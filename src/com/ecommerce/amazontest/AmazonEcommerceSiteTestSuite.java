package com.ecommerce.amazontest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@RunWith(Suite.class)
@SuiteClasses({ AmazonSourceURLTest.class, AmazonVerifyTitle.class, AmazonAdressTest.class, AmazonDealsPageTest.class,
		AmazonMobileLinkTest.class, AmazonSigninTest.class, AmazonSearchTest.class, AmazonAddtoCartTest.class,
		AmazonCartTest.class, AmazonSaveLaterInCartTest.class })
public class AmazonEcommerceSiteTestSuite {

}
