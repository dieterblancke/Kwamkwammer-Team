package be.dieterblancke.kwamkwammer.controllers.flipit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping( "/flipit" )
public class FlipItController
{

    @GetMapping
    public ModelAndView getFlipItPage()
    {
        return new ModelAndView( "flipit" );
    }
}
