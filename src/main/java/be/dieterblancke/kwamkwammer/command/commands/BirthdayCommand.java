package be.dieterblancke.kwamkwammer.command.commands;

import be.dieterblancke.kwamkwammer.command.Command;
import be.dieterblancke.kwamkwammer.other.MessageUtils;
import be.dieterblancke.kwamkwammer.other.Utils;
import com.google.common.collect.Lists;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

@Component
public class BirthdayCommand extends Command
{

    public BirthdayCommand()
    {
        super( "birthday", Lists.newArrayList( "bday" ) );
    }

    @Override
    public void onExecute( final Message message, final String[] args )
    {
        if ( !Utils.hasLottesBirthdayPassedYet() )
        {
            MessageUtils.sendMessage(
                    message.getTextChannel(),
                    "I know you can be a little impatient, but you'll have to wait until your birthday my love<3"
            );
            return;
        }

        // TODO
    }
}
