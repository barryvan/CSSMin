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
	
	@Test
	public void testPseudos() throws Exception
	{
		assertCSSMin(
			"'Before' pseudo",
			// This:
			".myclass::before { " + 
			"	content: 'foo'; " + 
			"} "
			// Should be:
			, ".myclass::before{content:'foo'}");
		
		assertCSSMin(
			"Pseudo class with interesting properties",
			// This:
			".myclass:hover { " + 
			"	color: red; " + 
			"	margin: 16px; " + 
			"	background:    url(http://www.image.com/mobile-sprite.png); " + 
			"} "
			// Should be:
			, ".myclass:hover{background:url(http://www.image.com/mobile-sprite.png);color:red;margin:16px}");
		
		assertCSSMin(
			"'Not' pseudo",
			// This:
			".myclass:not(:first-child) { " + 
			"	color: 'blue' " + 
			"} "
			// Should be:
			, ".myclass:not(:first-child){color:'blue'}");
		
		assertCSSMin(
			"Multiple pseudos in combination",
			// This:
			".myclass:not(:first-child)::before:hover { " + 
			"	color: red; " + 
			"	background:    url(http://www.image.com/mobile-sprite.png); " +
			"} "
			// Should be:
			, ".myclass:not(:first-child)::before:hover{background:url(http://www.image.com/mobile-sprite.png);color:red}");
	}
	
	@Test
	public void testURL() throws Exception
	{
		assertCSSMin(
			"Unquoted absolute URL",
			// This:
			".someClass{ " + 
			"	background: url(http://www.image.com/mobile-sprite.png); " + 
			"} "
			// Should be:
			, ".someClass{background:url(http://www.image.com/mobile-sprite.png)}");
		
		assertCSSMin(
			"Single quoted absolute URL",
			// This:
			".someClass{ " + 
			"	background-color: green; " +
			"	background-size: 100% 100%;" +
			"	background-image: url('http://www.image.com/mobile-sprite.png'); " + 
			"} "
			// Should be:
			, ".someClass{background-color:green;background-image:url(http://www.image.com/mobile-sprite.png);background-size:100% 100%}");
		
		assertCSSMin(
			"Double quoted absolute URL",
			// This:
			".someClass{ " + 
			"	background-color: green; " +
			"	background-size: 100% 100%;" +
			"	background-image: url(\"http://www.image.com/mobile-sprite.png\"); " + 
			"} "
			// Should be:
			, ".someClass{background-color:green;background-image:url(http://www.image.com/mobile-sprite.png);background-size:100% 100%}");
		
		assertCSSMin(
			"Unquoted SVG data URL",
			// This:
			".decorator { " + 
			"	background-size: cover; " + 
			"	background-position: center; " + 
			"	filter: url(data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' version='1.1'><defs><filter id='blur'><feGaussianBlur stdDeviation='8'/></filter></defs></svg>#blur); " + 
			"	filter: blur(8px); " + 
			"} "
			// Should be:
			, ".decorator{background-position:center;background-size:cover;filter:url(data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' version='1.1'><defs><filter id='blur'><feGaussianBlur stdDeviation='8'/></filter></defs></svg>#blur);filter:blur(8px)}");
		
		assertCSSMin(
			"Double quoted SVG data URL",
			// This:
			".decorator { " + 
			"	background-size: cover; " + 
			"	background-position: center; " + 
			"	filter: url(\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' version='1.1'><defs><filter id='blur'><feGaussianBlur stdDeviation='8'/></filter></defs></svg>#blur\"); " + 
			"	filter: blur(8px); " + 
			"} "
			// Should be:
			, ".decorator{background-position:center;background-size:cover;filter:url(\"data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' version='1.1'><defs><filter id='blur'><feGaussianBlur stdDeviation='8'/></filter></defs></svg>#blur\");filter:blur(8px)}");
	}
	
	@Test
	public void testParameterReduction() throws Exception
	{
		assertCSSMin(
			"Repeated margin width - reduced",
			// This:
			".someClass{ " + 
			"	margin: 4px 4px; " + 
			"} "
			// Should be:
			, ".someClass{margin:4px}");
		
		assertCSSMin(
			"Repeated background size - not reduced",
			// This:
			".someClass{ " + 
			"	background-size: 100% 100%; " + 
			"} "
			// Should be:
			, ".someClass{background-size:100% 100%}");
	}

}
