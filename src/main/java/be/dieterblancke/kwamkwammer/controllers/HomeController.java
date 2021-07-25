package be.dieterblancke.kwamkwammer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping( "/" )
public class HomeController
{

    @GetMapping
    public ModelAndView getHomePage()
    {
        return new ModelAndView( "home" );
    }
}
