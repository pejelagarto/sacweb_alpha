package ar.com.divisionturbos.sac.webapp.controller.ot;

import ar.com.divisionturbos.sac.core.enums.EstadoEnum;
import ar.com.divisionturbos.sac.core.model.ItemsEntity;
import ar.com.divisionturbos.sac.core.model.OtEntity;
import ar.com.divisionturbos.sac.core.service.ClienteService;
import ar.com.divisionturbos.sac.core.service.ItemsService;
import ar.com.divisionturbos.sac.core.service.OtService;
import ar.com.divisionturbos.sac.webapp.component.IndiceComponent;
import ar.com.divisionturbos.sac.webapp.controller.BaseFormController;
import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mzanetti on 06/06/17.
 */
@Controller
@RequestMapping("/ot")
@SessionAttributes("ot")
public class OtDetalleController extends BaseFormController{

    @Autowired
    ClienteService clienteService;

    @Autowired
    ItemsService itemsService;

    @Autowired
    IndiceComponent indiceComponent;

    @Autowired
    OtService otService;

    @RequestMapping("/cargarDetalleOT")
    public ModelAndView cargarDetalleOt(@ModelAttribute("ot") OtEntity otEntity) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientes", clienteService.getAll());
        modelAndView.setViewName("cargarDetalleOt");
        return modelAndView;
    }

    @RequestMapping(value = {"/addDetalle"}, method = {RequestMethod.GET, RequestMethod.POST}, produces =
            "application/json;charset=UTF-8")
    public @ResponseBody
    OtEntity
    addDetalle(final ItemsEntity detalleOtEntity, @ModelAttribute("ot") OtEntity otEntity, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        detalleOtEntity.setItm(indiceComponent.getItm());

        otEntity.getDetalleOtEntityList().add(detalleOtEntity);

        return otEntity;
    }


    @RequestMapping("/guardarOT")
    public ModelAndView guardarOt(@ModelAttribute("ot") OtEntity otEntity,final HttpServletRequest request,SessionStatus status) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        otEntity.setUsuarioCreacion(user.getUsername());
        otEntity.setFechaCreacion(new Date());
        otEntity.setEstado(EstadoEnum.ACTIVO.getCodigo());
        otEntity.setfAlta(new Date());

        /*Esto tendria que estar relacionado*/
        for(ItemsEntity itemsEntity : otEntity.getDetalleOtEntityList()){
            itemsEntity.setNrot(otEntity.getNrot());
        }
        otService.save(otEntity);

        status.setComplete();

        final Locale locale = request.getLocale();

        saveMessage(request, getText("ot.guardada", String.valueOf(otEntity.getNrot()), locale));

        modelAndView.setViewName("redirect:/ot/consultarOT");
        return modelAndView;
    }

    @RequestMapping(value = {"/deleteItem"}, method = {RequestMethod.GET, RequestMethod.POST}, produces =
            "application/json;charset=UTF-8")
    public @ResponseBody
    OtEntity
    deleteItem(final ItemsEntity detalleOtEntity, @ModelAttribute("ot") OtEntity otEntity, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        otEntity.getDetalleOtEntityList().remove(detalleOtEntity);

        return otEntity;
    }
}
