package ar.com.divisionturbos.sac.webapp.controller;

import ar.com.divisionturbos.sac.core.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by mzanetti on 02/06/17.
 */
@Controller
@RequestMapping("/prueba/")
public class PruebaController {

    @Autowired
    ItemsService itemsService;


    @RequestMapping(value = "/prueba")
    private void prueba (WebRequest webRequest){

        itemsService.getAll();

    }

}
