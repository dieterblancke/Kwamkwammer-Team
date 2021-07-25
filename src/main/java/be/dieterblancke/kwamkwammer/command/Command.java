package be.dieterblancke.kwamkwammer.command;

import be.dieterblancke.kwamkwammer.other.MessageUtils;
import be.dieterblancke.kwamkwammer.services.DiscordService;
import com.google.common.collect.Lists;
import lombok.Data;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
@Component
public abstract class Command
{

    private String name;
    private List<String> aliases;
    private List<Command> subCommands;
    private boolean limitable = false;

    public Command()
    {
        this.name = this.getClass().getSimpleName().toLowerCase()
                .replace( "subcommand", "" )
                .replace( "command", "" );
        this.aliases = new ArrayList<>();
        this.subCommands = new ArrayList<>();
    }

    public Command( final String name )
    {
        this( name, Lists.newArrayList() );
    }

    public Command( final String name, final List<String> aliases )
    {
        this.name = name;
        this.aliases = aliases;
        this.subCommands = Lists.newArrayList();
    }

    @Autowired
    public final void setDiscordService( final DiscordService discordService )
    {
        discordService.register( this );
    }

    public void execute( final Message message, final String[] args )
    {
        if ( args.length >= 1 )
        {
            final Optional<Command> subCommand = subCommands.stream()
                    .filter( command -> command.getName().equalsIgnoreCase( args[0] ) || command.getAliases().contains( args[0] ) )
                    .findFirst();

            if ( subCommand.isPresent() )
            {
                subCommand.get().execute( message, Arrays.copyOfRange( args, 1, args.length ) );
                return;
            }
        }
        try
        {
            onExecute( message, args );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            MessageUtils.sendMessage(
                    message.getTextChannel(),
                    "Something went wrong while executing this command. Please contact <@183656254659362817> to resolve this issue (with timestamp + timezone included)."
            );
        }
    }

    public boolean canUse( final Member member )
    {
        return true;
    }

    protected void registerSubCommand( final Command command )
    {
        subCommands.add( command );
    }

    public abstract void onExecute( Message message, String[] args );

    protected void queueNoPermMessage( final Message message )
    {
        message.getTextChannel().sendMessage(
                "You cannot use this command! Please ask an administrator to give you permissions!"
        ).queue();
    }
}
