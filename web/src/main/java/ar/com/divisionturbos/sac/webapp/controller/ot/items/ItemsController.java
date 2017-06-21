package ar.com.divisionturbos.sac.webapp.controller.ot.items;

import ar.com.divisionturbos.sac.core.model.ItemsEntity;
import ar.com.divisionturbos.sac.core.model.OtEntity;
import ar.com.divisionturbos.sac.core.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mzanetti on 14/06/17.
 */
@Controller
@RequestMapping("/items")
@SessionAttributes("ot")
public class ItemsController {

    @Autowired
    ItemsService itemsService;

    @RequestMapping("/search")
    public ModelAndView search(final ItemsEntity itemsEntity, WebRequest webRequest) throws InterruptedException {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("items", itemsService.findPaginated(itemsEntity, null, null, null, null));
        modelAndView.addObject("search",itemsEntity);
        modelAndView.setViewName("consultaItems");
        return modelAndView;

    }

    @RequestMapping("/consultarItems")
    public ModelAndView consultarItems() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("items", itemsService.getAll());
        modelAndView.setViewName("consultaItems");
        return modelAndView;
    }


}
