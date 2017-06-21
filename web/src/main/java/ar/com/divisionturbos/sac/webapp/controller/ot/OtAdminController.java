package ar.com.divisionturbos.sac.webapp.controller.ot;

import ar.com.divisionturbos.sac.core.model.OtEntity;
import ar.com.divisionturbos.sac.core.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.POST;

/**
 * Created by mzanetti on 06/06/17.
 */
@Controller
@RequestMapping("/ot")
@SessionAttributes("ot")
public class OtAdminController {

    @Autowired
    ClienteService clienteService;


    @RequestMapping("/crearOT")
    public ModelAndView crearOT(@ModelAttribute("ot") OtEntity otEntity,WebRequest webRequest) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ot/cargarDetalleOT");
        return modelAndView;
    }

    @RequestMapping("/addOT")
    public ModelAndView addOT(@ModelAttribute("ot") OtEntity otEntity, WebRequest webRequest,
                              DefaultSessionAttributeStore
                                      defaultSessionAttributeStENTIore) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("nuevaOT");
        modelAndView.addObject("clientes", clienteService.getAll());
        return modelAndView;

    }
}
