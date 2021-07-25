package be.dieterblancke.kwamkwammer.command.commands;

import be.dieterblancke.kwamkwammer.command.Command;
import be.dieterblancke.kwamkwammer.other.MessageUtils;
import com.google.common.collect.Lists;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class HelloCommand extends Command
{

    public HelloCommand()
    {
        super( "hello", Lists.newArrayList( "sup" ) );
    }

    @Override
    public void onExecute( final Message message, final String[] args )
    {
        MessageUtils.sendMessage(
                message.getTextChannel(),
                "Hello my love<3"
        );
    }
}
