package com.brikton.labapps.msusuario.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    void testBajaClienteConPedidosYExiste(){
        fail("es instesteable");
        when(pedidoService.tienePedidos(anyInt())).thenReturn(true);
        // when(ClienteRepository)

    }

    @Test
    void testBajaClienteConPedidosYNoExiste(){
        fail("Not yet implemented");
        when(pedidoService.tienePedidos(anyInt())).thenReturn(true);


    }

    @Test
    void testBajaClienteSinPedidosYExiste(){
        fail("Not yet implemented");
    }
    @Test
    void testBajaClienteSinPedidosYNoExiste(){
        fail("Not yet implemented");
    }

    @Test
    void testListarClientes(){
        Cliente cliente1 = new Cliente(1, "razonSocial", null, null, null, null, usuario);
        Cliente cliente2 = new Cliente(1, "razonSocial", null, null, null, null, usuario);
        Cliente cliente3 = new Cliente(1, "razonSocial", null, null, null, null, usuario);
        cliente3.setFechaBaja(LocalDate.now());
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        when(clienteRepo.findAll()).thenReturn(clientes);
        List<Cliente> resultadoClientes = clienteService.listarClientes();
        assertThat(resultadoClientes.size() == 2);
    }

    @Test
    void testBuscarClientePorId(){
        fail("Not yet implemented");
    }

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
