package com.brikton.labapps.msusuario.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.domain.TipoUsuario;
import com.brikton.labapps.msusuario.domain.Usuario;
import com.brikton.labapps.msusuario.exception.RiesgoException;
import com.brikton.labapps.msusuario.repository.ClienteRepository;
import com.brikton.labapps.msusuario.service.ClienteService;
import com.brikton.labapps.msusuario.service.PedidoService;
import com.brikton.labapps.msusuario.service.RiesgoBCRAService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ClienteServiceImplUnitTest {
    
    @Autowired
    ClienteService clienteService;

    @MockBean
    ClienteRepository clienteRepo;

    @MockBean
    PedidoService pedidoService;

    @MockBean
    RiesgoBCRAService riesgoService;

    Cliente cliente;
    Usuario usuario;

    @BeforeEach
	void setUp() throws Exception {
		// unPedido = new Pedido();
		// Obra obra = new Obra();
		// DetallePedido d1 = new DetallePedido(new Material(),5,40.0);
		// DetallePedido d2 = new DetallePedido(new Material(),10,80.0);
		// DetallePedido d3 = new DetallePedido(new Material(),2,450.0);
		// unPedido.setDetalle(new ArrayList<DetallePedido>());
		// unPedido.getDetalle().add(d1);
		// unPedido.getDetalle().add(d2);
		// unPedido.getDetalle().add(d3);
		// unPedido.setObra(obra);
        usuario = new Usuario(1, "user", "password", new TipoUsuario("CLIENTE"));
        cliente = new Cliente(1, "razonSocial", null, null, null, null, usuario);
        cliente.setCuit("20245461568");
        cliente.setFechaBaja(null);
        cliente.setId(1);
        cliente.setMail("algunmail@algunmail.com");
        cliente.setMaxCuentaCorriente(1000000.00);
        cliente.setRazonSocial("razonSocial");
	}

    @Test
    void testGuardarClienteConIdNullYRiesgoMenor(){
        cliente.setId(null);
        when(riesgoService.getRiesgo(any(String.class))).thenReturn(1);
        when(clienteRepo.save(any(Cliente.class))).thenReturn(cliente);
        try{
            Cliente clienteResultado = clienteService.guardarCliente(cliente);
            assertThat(clienteResultado.getId().equals(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGuardarClienteConIdNullYRiesgoMayor(){
        cliente.setId(null);
        when(riesgoService.getRiesgo(any(String.class))).thenReturn(3);
        when(clienteRepo.save(any(Cliente.class))).thenReturn(cliente);
            
        assertThrows(RiesgoException.class, () -> clienteService.guardarCliente(cliente), "Riesgo Crediticio > 2");

    }

    @Test
    void testGuardarClienteConIdMayorACero(){
        cliente.setId(3);
        when(riesgoService.getRiesgo(any(String.class))).thenReturn(1);
        when(clienteRepo.save(any(Cliente.class))).thenReturn(cliente);
        try{
            Cliente clienteResultado = clienteService.guardarCliente(cliente);
            assertThat(clienteResultado.getId().equals(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test
    // void testGuardarClienteConIdMayorACero(){
    //     cliente.setId(3);
    //     when(riesgoService.getRiesgo(any(String.class))).thenReturn(1);
    //     when(clienteRepo.save(any(Cliente.class))).thenReturn(cliente);
    //     try{
    //         Cliente clienteResultado = clienteService.guardarCliente(cliente);
    //         assertThat(clienteResultado.getId().equals(1));

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    @AfterEach
	void tearDown() throws Exception {
	}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
}
