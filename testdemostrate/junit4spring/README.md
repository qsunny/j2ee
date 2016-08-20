### junit4spring example FAQ ###

1. for xml config file parse

	
	    import org.springframework.test.context.ContextConfiguration;
	    
	    @ContextConfiguration(locations = {
	    "classpath:pathTo/appConfig.xml",
	    "classpath:pathTo/appConfig2.xml"})
	    public class MachineLearningTest {
	    //...
	    }


2. for mutiple config files

    	import org.springframework.test.context.ContextConfiguration;
    
    	@ContextConfiguration(classes = {AppConfig.class, AppConfig2.class})
    	public class MachineLearningTest {
    	//...
    	}

