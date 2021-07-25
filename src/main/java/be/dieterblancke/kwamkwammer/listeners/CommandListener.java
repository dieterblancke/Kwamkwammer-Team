package be.dieterblancke.kwamkwammer.listeners;

import be.dieterblancke.kwamkwammer.command.Command;
import be.dieterblancke.kwamkwammer.services.DiscordService;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public class CommandListener extends ListenerAdapter
{

    private final DiscordService discordService;

    @Override
    public void onGuildMessageReceived( GuildMessageReceivedEvent event )
    {
        if ( event.getAuthor().isBot()
                || !event.getMessage().getChannelType().equals( ChannelType.TEXT ) )
        {
            return;
        }
        final Message message = event.getMessage();
        final String content = message.getContentRaw();

        if ( message.getContentRaw().startsWith( "!" ) )
        {
            final String[] splitten = content.split( " " );
            final String commandName = splitten[0].substring( "!".length() );
            final Optional<Command> optionalCommand = discordService.search( commandName );

            optionalCommand.ifPresent( command -> command.execute( message, Arrays.copyOfRange( splitten, 1, splitten.length ) ) );
        }
    }
}
