package testhelpers;

public class TestFakeBrowser {
    public String currentUrl = "https://mysite.mycompany.com/this/path/and/resource";
    public String currentSource = "<!DOCTYPE html><html><head></head><body><h1>Hello world</h1></body></html>";
    public String currentTitle = "My fake browser page";

    public void navigate(String url){
        currentUrl = url;
    }
}
