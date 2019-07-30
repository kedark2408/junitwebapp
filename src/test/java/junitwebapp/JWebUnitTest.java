package junitwebapp;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertLinkPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTestingEngineKey;
 
import org.junit.Before;
import org.junit.Test;
 
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
 
 
public class JWebUnitTest {
    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT); 
        setBaseUrl("http://18.207.206.62:8081/junitwebapp");
    }
 
    @Test
    public void testLoginPage() {
        beginAt("index.jsp"); 
        assertTitleEquals("Home");
        assertLinkPresent("login");
        clickLink("login");
        assertTitleEquals("Home");
    }
     
    @Test
    public void testHomePage() {
        beginAt("home.jsp"); 
        assertTitleEquals("Login");
        assertLinkPresent("home");
        clickLink("home");
        assertTitleEquals("Login");
    }
}
