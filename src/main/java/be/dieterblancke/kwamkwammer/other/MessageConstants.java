package be.dieterblancke.kwamkwammer.other;

import be.dieterblancke.kwamkwammer.services.DiscordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@AllArgsConstructor
public class MessageConstants
{

    private final DiscordService discordService;

    public String getAvatarUrl()
    {
        return discordService.getJda().getSelfUser().getAvatarUrl();
    }

    public Color getPrimaryMessageColor()
    {
        return new Color( 238, 162, 33 );
    }
}
