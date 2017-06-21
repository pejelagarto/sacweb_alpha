package ar.com.divisionturbos.sac.webapp.controller.ot;

import ar.com.divisionturbos.sac.core.enums.EstadoEnum;
import ar.com.divisionturbos.sac.core.model.OtEntity;
import ar.com.divisionturbos.sac.core.service.ClienteService;
import ar.com.divisionturbos.sac.core.service.OtService;
import ar.com.divisionturbos.sac.webapp.controller.BaseFormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mzanetti on 05/06/17.
 */
@Controller
@RequestMapping("/ot")
@SessionAttributes("ot")
public class OtController extends BaseFormController {


    @Autowired
    OtService otService;

    @Autowired
    ClienteService clienteService;

    @ModelAttribute("ot")
    public OtEntity populateForm() {
        return new OtEntity();
    }


    @RequestMapping("/iniciarOT")
    public ModelAndView iniciarOt(WebRequest webRequest, DefaultSessionAttributeStore defaultSessionAttributeStore,
                                  SessionStatus sessionStatus) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/ot/addOT");
        return modelAndView;

    }

    @RequestMapping("/consultarOT")
    public ModelAndView consultarOT(WebRequest webRequest, DefaultSessionAttributeStore defaultSessionAttributeStore,
                                    SessionStatus sessionStatus) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ots", otService.getAll());
        modelAndView.addObject("clientes", clienteService.getAll());
        modelAndView.addObject("estados", EstadoEnum.values());
        modelAndView.setViewName("consultarOTs");
        return modelAndView;

    }

    @RequestMapping(value = "/searchOT", method = RequestMethod.GET)
    public ModelAndView searchOt(@RequestParam(required = false, value = "q") String query) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("ots", otService.search(query));
        modelAndView.addObject("clientes", clienteService.getAll());
        modelAndView.addObject("estados", EstadoEnum.values());
        modelAndView.setViewName("consultarOTs");
        return modelAndView;

    }

    @RequestMapping("/search")
    public ModelAndView search(final OtEntity otEntity, WebRequest webRequest) throws InterruptedException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ots", otService.findFilter(otEntity));
        modelAndView.addObject("clientes", clienteService.getAll());
        modelAndView.addObject("search", otEntity);
        modelAndView.addObject("estados", EstadoEnum.values());
        modelAndView.setViewName("consultarOTs");
        return modelAndView;

    }

    @RequestMapping(value = "/darBaja", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView darBaja(Long id,final HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        OtEntity otEntity = otService.get(id);
        otEntity.setEstado(EstadoEnum.INACTIVO.getCodigo());
        otEntity.setfCierre(new Date());
        otService.save(otEntity);

        final Locale locale = request.getLocale();

        saveMessage(request, getText("ot.baja", String.valueOf(otEntity.getNrot()), locale));
        modelAndView.setViewName("redirect:/ot/consultarOT");

        return modelAndView;
    }

    @RequestMapping(value = "/darAlta", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView darAlta(Long id,final HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        OtEntity otEntity = otService.get(id);
        otEntity.setEstado(EstadoEnum.ACTIVO.getCodigo());
        otEntity.setfAlta(new Date());
        otEntity.setfCierre(null);
        otService.save(otEntity);

        final Locale locale = request.getLocale();

        saveMessage(request, getText("ot.alta", String.valueOf(otEntity.getNrot()), locale));
        modelAndView.setViewName("redirect:/ot/consultarOT");

        return modelAndView;
    }


}
