import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;


public class CSSMinTest
{

	private static void assertCSSMin(String message, String input, String expectedOutput) throws Exception
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		CSSMin.formatFile(new StringReader(input), baos);
		baos.flush();
		String actualOutput = new String(baos.toByteArray(),"UTF-8");
		System.out.println("");
		System.out.println("# " + message + " => ");
		System.out.println("\texpected:\t" + expectedOutput);
		System.out.println("\tactual:  \t" + actualOutput);
		Assert.assertEquals(message, expectedOutput.trim(), actualOutput.trim());
	}
	
	@Test
	public void testWhitespace() throws Exception
	{
		assertCSSMin(
				"White space in regular selectors",
				// This:
				"          body           { " + 
				"								background-color:											red;				 " + 
				"	}			 "
				// Should be:
			, "body{background-color:red}");
		
		assertCSSMin(
			"White space in nested selectors",
			// This:
			"@-webkit-keyframes configuratorFlyLeft { " + 
			"	0% {left: 0;} " + 
			"	100% {left: -100%;} " + 
			" } " + 
			"  " + 
			" @-webkit-keyframes configuratorFlyRight { " + 
			"	0% {right: 0;} " + 
			"	100% {right: -100%;} " + 
			"} "
			// Should be:
		, "@-webkit-keyframes configuratorFlyLeft{0%{left:0}100%{left:-100%}}@-webkit-keyframes configuratorFlyRight{0%{right:0}100%{right:-100%}}");
	}

}
