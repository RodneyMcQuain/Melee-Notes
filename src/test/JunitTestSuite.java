package test;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ 
	StatTest.class,
	MoneyReportTest.class 
})

public class JunitTestSuite {
    
	@BeforeClass
    public static void init() {
		SQLiteTestData.deleteDatabaseFile();
		SQLiteTestData.buildEntireDatabase();
	}
}
