package com.brikton.labapps.mspedido.rest;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import com.brikton.labapps.mspedido.MsPedidosTest;
import com.brikton.labapps.mspedido.domain.DetallePedido;
import com.brikton.labapps.mspedido.domain.Obra;
import com.brikton.labapps.mspedido.domain.Pedido;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@SpringBootTest( 
		classes = MsPedidosTest.class,
		webEnvironment = WebEnvironment.RANDOM_PORT)
class PedidoResourceTest {
	
	private RestTemplate restTemplate = new RestTemplate();

    // @Autowired
    // private TestRestTemplate testRestTemplate;
    
	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	@LocalServerPort
	String puerto;
	  
	
	private final String urlServer= "http://localhost";
	private final String apiPedido = "api/pedido";
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
	@Disabled
	void deberiaRechazarPorFaltaDeObra() {
		fail("Not yet implemented");
	}

	@Test
	//@Disabled
	void deberiaRechazarPorFaltaDeItems() {
		String server = urlServer+":"+puerto+"/"+apiPedido;
		System.out.println("SERVER "+server);
		Pedido unPedido = new Pedido();
		unPedido.setObra(new Obra());
		HttpEntity<Pedido> requestPedido = new HttpEntity<>(unPedido);
		Throwable ex = assertThrows( 
			      HttpClientErrorException.class, 
			      () -> {
			    	  	ResponseEntity<String> respuesta = restTemplate.exchange(server, HttpMethod.POST,requestPedido , String.class);
			  			assertTrue(respuesta.getStatusCode().equals(HttpStatus.BAD_REQUEST));
			      	}
			  ); 
		assertTrue(ex.getMessage().startsWith("400"));

	}

	@Test
	//@Disabled
	void deberiaAceptarConObraEItems() {
		String server = urlServer+":"+puerto+"/"+apiPedido;
		System.out.println("SERVER "+server);
		Pedido unPedido = new Pedido();
		unPedido.setObra(new Obra());
		ArrayList<DetallePedido> detalles = new ArrayList<DetallePedido>();
		detalles.add(new DetallePedido());
		unPedido.setDetalle(detalles);
		HttpEntity<Pedido> requestPedido = new HttpEntity<>(unPedido);
		ResponseEntity<String> respuesta = restTemplate.exchange(server, HttpMethod.POST,requestPedido , String.class);
		assertTrue(respuesta.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test
	//@Disabled
	void deberiaRechazarPorFaltaDeItems2() {
		String server = urlServer+":"+puerto+"/"+apiPedido;
		System.out.println("SERVER "+server);
		Pedido unPedido = new Pedido();
		unPedido.setObra(new Obra());
		HttpEntity<Pedido> requestPedido = new HttpEntity<>(unPedido);
		ResponseEntity<String> respuesta = testRestTemplate.exchange(server, HttpMethod.POST,requestPedido , String.class);		
		assertTrue(respuesta.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	void hacerAlgo() {
		assertTrue(true);
	}
	
	

}