package co.com.sofka.stepdefnitions.soap.information.countryflag;

import co.com.sofka.stepdefnitions.soap.information.SetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static co.com.sofka.questions.ReturnStringValue.systemValue;
import static co.com.sofka.tasks.information.DoPost.doPost;
import static co.com.sofka.util.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

public class CountryFlagWithCucumberStepDefinition extends SetUp {

    private static final String COUNTRY_FLAG_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\information\\countryflag.xml";
    private static final String STRING_COUNTRY = "[stringCountry]";
    private static final String MSN_INIT = "<m:CountryFlagResult>http://www.oorsprong.org/WebSamples.CountryInfo/Flags/";
    private static final String MSN_FIN = ".jpg</m:CountryFlagResult>";
    private static final String ETIQUETA_INI = "<m:CountryFlagResult>";
    private static final String ETIQUETA_FIN = "</m:CountryFlagResult>";


    //========================================================================================//
    //========================================================================================//
    @Given("que el usuario de la  app quiere conocer la bandera del pais a donde desea viajar")
    public void queElUsuarioDeLaAppQuiereConocerLaBanderaDelPaisADondeDeseaViajar() {
        setUp();
    }


    @When("el usuario quiere conocer la bandera del EEUU e ingresa las iniciales {string}")
    public void elUsuarioQuiereConocerLaBanderaDelEEUUEIngresaLasIniciales(String country) {
        bodyRequest = defineBodyRequest(country);
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );
    }

    @Then("el ususario deberia obtener la bandera con el nombre {string}")
    public void elUsusarioDeberiaObtenerLaBanderaConElNombre(String countryInicial) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El resultado de la suma debe ser: ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString(MSN_INIT + countryInicial + MSN_FIN)
                )
        );
    }

    //========================================================================================//
    ///===================================FALLO===============================================//
    //=======================================================================================//

    @When("el usuario quiere conocer la bandera del Colombia e ingresa las iniciales {string}")
    public void elUsuarioQuiereConocerLaBanderaDelColombiaEIngresaLasIniciales(String initError) {
        bodyRequest = defineBodyRequest(initError);
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );
    }


    @Then("el ususario deberia obtener la respuesta  {string}")
    public void elUsusarioDeberiaObtenerLaRespuesta(String countryError) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El nombre del pais debe ser : ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString(ETIQUETA_INI + countryError + ETIQUETA_FIN)

                )
        );
    }


    private String defineBodyRequest(String nameCountry){
        return readFile(COUNTRY_FLAG_XML).replace(STRING_COUNTRY, nameCountry);
    }

}
