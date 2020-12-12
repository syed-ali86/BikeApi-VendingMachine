package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import javax.rmi.CORBA.Util;


public class Steps {

    @When("I hit the biker Api")
    public void i_hit_the_biker_api() {
        // Write code here that turns the phrase above into concrete actions
        boolean hitResult = Utils.hitURL();
        Assert.assertTrue("Unable to hit Api",hitResult);
    }

    @Then("I should get valid response code as 200")
    public void i_should_get_valid_response_code_as() {
        // Write code here that turns the phrase above into concrete actions
        boolean response = Utils.getResponse()==200;
        Assert.assertTrue("Status is not 200",response);
        //System.out.println("I should get valid response code as "+response);
    }
    @Then("I should get valid response data")
    public void i_should_get_valid_response_data() {
        // Write code here that turns the phrase above into concrete actions
        boolean response = Utils.getResponseData() != null;
        Assert.assertTrue("Invalid_response_data",response);
    }

    @Given("developers pass network id {string}")
    public void developersPassNetworkId(final String networkId) {

        boolean response = Utils.getNetworkInfo(networkId);
        Assert.assertTrue("Invalid_network_id",response);
    }

    @Then("network location corresponded {string} in {string}")
    public void networkLocationCorrespondedIn(final String city, final String country) {

        boolean response = Utils.getLocationInfo(city,country);
        Assert.assertTrue("Invalid_location_info",response);
    }

    @Then("corresponded location {string}, {string} returned")
    public void correspondedLocationReturned(final String lat, final String lon) {

        boolean response = Utils.getLocationPosition(lat,lon);
        Assert.assertTrue("Invalid_location_position",response);
    }


    @Given("developers pass network id {string} to api")
    public void developersPassNetworkIdToApi(String networkId) {
        boolean hitResult = Utils.hitURL(networkId);
        Assert.assertTrue("Unable to hit Api",hitResult);

    }


    @Then("number of stations should be returned")
    public void number_of_stations_should_be_returned() {
        // Write code here that turns the phrase above into concrete actions
        boolean numberOfStations = Utils.getNumberOfStations()!= -1;
        Assert.assertTrue("Unable to find Stations",numberOfStations);

    }

    @Then("validate available bikes, timestamps")
    public void validate_available_bikes_timestamps() {
        // Write code here that turns the phrase above into concrete actions
        boolean validate = Utils.verifyFreeBike()==2;
        Assert.assertTrue("Unable to locate Free bike or timestamp",validate);
    }



}
