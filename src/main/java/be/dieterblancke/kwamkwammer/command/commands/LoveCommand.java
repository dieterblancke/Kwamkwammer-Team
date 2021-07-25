package be.dieterblancke.kwamkwammer.command.commands;

import be.dieterblancke.kwamkwammer.command.Command;
import be.dieterblancke.kwamkwammer.other.MessageUtils;
import com.google.common.collect.Lists;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class LoveCommand extends Command
{

    public LoveCommand()
    {
        super( "love", Lists.newArrayList( "loveu" ) );
    }

    @Override
    public void onExecute( final Message message, final String[] args )
    {
        MessageUtils.sendMessage(
                message.getTextChannel(),
                "https://media1.tenor.com/images/22cf84d244d33fd0db14045d11af7653/tenor.gif?itemid=15980505"
        );
    }
}
