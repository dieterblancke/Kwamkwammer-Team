package be.dieterblancke.kwamkwammer.command.commands;

import be.dieterblancke.kwamkwammer.command.Command;
import be.dieterblancke.kwamkwammer.other.MessageUtils;
import com.google.common.collect.Lists;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class MissYouCommand extends Command
{

    public MissYouCommand()
    {
        super( "missu", Lists.newArrayList( "miss" ) );
    }

    @Override
    public void onExecute( final Message message, final String[] args )
    {
        MessageUtils.sendMessage(
                message.getTextChannel(),
                "I miss you too my love<3" +
                        "\nI'm sure we'll talk very soon again and that the missing is only for a short bit, I'm sure I can't wait to talk to you<3" +
                        "\nhttps://tenor.com/view/imiss-you-so-much-imiss-you-missing-you-cony-and-bear-hearts-gif-16098997"
        );
    }
}
