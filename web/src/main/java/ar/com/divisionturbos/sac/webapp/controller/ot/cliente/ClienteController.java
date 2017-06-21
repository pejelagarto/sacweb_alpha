package ar.com.divisionturbos.sac.webapp.controller.ot.cliente;

import ar.com.divisionturbos.sac.core.model.ClienteEntity;
import ar.com.divisionturbos.sac.core.service.ClienteService;
import ar.com.divisionturbos.sac.webapp.controller.BaseFormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by mzanetti on 05/06/17.
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController extends BaseFormController {

    @RequestMapping("/addCliente")
    public ModelAndView addCliente(WebRequest webRequest) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("nuevoCliente");
        modelAndView.addObject("cliente", new ClienteEntity());
        return modelAndView;

    }

    @Autowired
    ClienteService clienteService;

    @RequestMapping("/nuevoCliente")
    public ModelAndView nuevoCliente(final ClienteEntity clienteEntity, WebRequest webRequest,final HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        clienteService.save(clienteEntity);

        final Locale locale = request.getLocale();
        saveMessage(request, getText("cliente.guardado", String.valueOf(clienteEntity.getRazonSocial()), locale));

        modelAndView.setViewName("redirect:/cliente/consultar");
        return modelAndView;
    }


    @RequestMapping("/consultar")
    public ModelAndView consultarClientes() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientes", clienteService.getAll());
        modelAndView.setViewName("consultarClientes");
        return modelAndView;
    }
}
