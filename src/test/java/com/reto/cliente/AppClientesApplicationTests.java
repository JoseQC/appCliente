package com.reto.cliente;

import com.reto.cliente.controller.ClienteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AppClientesApplicationTests {

	@Autowired
	private ClienteController clienteController;

	@Test
	void contextLoads() {
		assertThat(clienteController).isNotNull();

	}

}
