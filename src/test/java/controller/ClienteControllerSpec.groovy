package controller

import com.soulrebel.neoris.controller.ClienteController
import com.soulrebel.neoris.entity.Cliente
import com.soulrebel.neoris.service.ClienteService
import org.mockito.Mock
import spock.lang.Specification

class ClienteControllerSpec extends Specification {

    @Mock
    ClienteService clienteService

    @Mock
    ClienteController clienteController

    def setup() {
        clienteController = new ClienteController(clienteService)
    }

    def "Test crearCliente method"() {
        given:
        Cliente cliente = new Cliente(1, 'Carlos')
        when:
        clienteController.crearCliente(cliente)
        then:
        1 * clienteService.crearCliente(_ as Cliente)
    }

    def "Test obtenerClientePorId method"() {
        given:
        Integer id = 1
        Cliente cliente = new Cliente(id, 'JCarlos')
        when:
        clienteController.obtenerClientePorId(id)
        then:
        1 * clienteService.obtenerClientePorId(id) >> Optional.of(cliente)
    }

    def "Test actualizarCliente method"() {
        given:
        Integer id = 1
        Cliente cliente = new Cliente(id, 'Carlos')
        when:
        clienteController.actualizarCliente(cliente, id)
        then:
        1 * clienteService.actualizarCliente(_ as Cliente, id)
    }

    def "Test borrarClientePorId method"() {
        given:
        Integer id = 1
        when:
        clienteController.borrarClientePorId(id)
        then:
        1 * clienteService.borrarClientePorId(id)
    }
}