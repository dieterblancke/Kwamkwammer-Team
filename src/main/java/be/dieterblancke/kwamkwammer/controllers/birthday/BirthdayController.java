package be.dieterblancke.kwamkwammer.controllers.birthday;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping( "/" )
public class BirthdayController
{

    @GetMapping("/birthday2021")
    public ModelAndView getWatchListPage()
    {
        final ModelAndView modelAndView = new ModelAndView( "birthday2021" );

        return modelAndView;
    }
}
