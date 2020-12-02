module QA_SDET_Test_1 {
	exports com.qa.sdet.main;
	exports com.qa.sdet.util.bean;
	exports com.qa.sdet.test;
	requires java.net.http;
	requires jackson.databind;
	requires jackson.core;
	requires org.junit.jupiter.api;
	requires junit;
}
