package be.dieterblancke.kwamkwammer.services;

import be.dieterblancke.kwamkwammer.command.Command;
import be.dieterblancke.kwamkwammer.listeners.CommandListener;
import be.dieterblancke.kwamkwammer.other.paginator.Paginator;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Service
public class DiscordService
{

    private final List<Command> commands = Lists.newLinkedList();
    private final JDA jda;

    public DiscordService( @Value( "${discord.token}" ) final String discordToken ) throws LoginException
    {
        this.jda = JDABuilder.createDefault( discordToken ).build();
        this.registerListeners();
    }

    private void registerListeners()
    {
        this.jda.addEventListener( new CommandListener( this ) );
    }

    public void register( final Command command )
    {
        commands.add( command );
        log.info( "Command " + command.getName() + " has been registered!" );
    }

    public void unregister( final Command command )
    {
        commands.remove( command );
    }

    public Optional<Command> search( final String name )
    {
        return commands.stream()
                .filter( command -> command.getName().equalsIgnoreCase( name ) || command.getAliases().contains( name ) )
                .findAny();
    }

    public List<Command> getCommands()
    {
        return Collections.unmodifiableList( commands );
    }

    public void registerPaginator( final Paginator paginator )
    {
        // do nothing for now
    }
}
