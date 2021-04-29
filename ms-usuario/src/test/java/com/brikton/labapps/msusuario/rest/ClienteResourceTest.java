package com.brikton.labapps.msusuario.rest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.brikton.labapps.msusuario.MsUsuarioTest;
import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest( 
		classes = MsUsuarioTest.class,
		webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClienteResourceTest {

    private RestTemplate restTemplate = new RestTemplate();

    // @Autowired
    // private TestRestTemplate testRestTemplate;
    
	// private TestRestTemplate testRestTemplate = new TestRestTemplate();
	
    @LocalServerPort
	String puerto;
	  
	
	private final String urlServer= "http://localhost";
	private final String apiCliente = "api/cliente";
    int randomServerPort;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void hacerAlgo() {
		assertTrue(true);
	}
	
    @Test
    void crearCliente(){
        String server = urlServer+":"+puerto+"/"+apiCliente;
		System.out.println("SERVER "+server);
		Cliente unCliente = new Cliente(0, "Pedro", "04123654", "unmail@mail.com", 2000.0, null, null);
		HttpEntity<Cliente> requestCliente = new HttpEntity<>(unCliente);
		ResponseEntity<String> respuesta = restTemplate.exchange(server, HttpMethod.POST,requestCliente , String.class);
		assertTrue(respuesta.getStatusCode().equals(HttpStatus.OK));
        ObjectMapper objectMapper = new ObjectMapper();
        Cliente rta;
        try {
            rta = objectMapper.readValue(respuesta.getBody(), Cliente.class);
            assertTrue(rta.getId().equals(1));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
