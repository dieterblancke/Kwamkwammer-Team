package be.dieterblancke.kwamkwammer.other;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MessageUtils
{

    private static final Logger LOGGER = LoggerFactory.getLogger( MessageUtils.class );

    public static String calculateEstimated( final LocalDateTime from )
    {
        final LocalDateTime now = LocalDateTime.now( ZoneOffset.UTC );
        final Duration duration = Duration.between( from, now );

        return formatToString( duration );
    }

    public static String formatToString( final Duration duration )
    {
        if ( duration.toDaysPart() == 0 )
        {
            return String.format(
                    "%dh %dm %ds",
                    duration.toHoursPart(),
                    duration.toMinutesPart(),
                    duration.toSecondsPart()
            );
        }
        else
        {
            return String.format(
                    "%dd %dh %dm %ds",
                    duration.toDaysPart(),
                    duration.toHoursPart(),
                    duration.toMinutesPart(),
                    duration.toSecondsPart()
            );
        }
    }

    public static String duplicateCharacter( final String toCopy, final int amount )
    {
        return toCopy.repeat( Math.max( 0, amount ) );
    }

    public static String generateColumn( final int maxLength, final String text )
    {
        return generateCharColumn( " ", maxLength, text );
    }

    public static String generateLeftAlignedColumn( final int maxLength, final String text )
    {
        return text + " ".repeat( Math.max( 0, maxLength - text.length() ) );
    }

    public static String generateCharColumn( final String filler, final int maxLength, final String text )
    {
        int spaces = maxLength - text.length();
        StringBuilder result = new StringBuilder();

        if ( spaces % 2 != 0 )
        {
            spaces--;
            result = new StringBuilder( filler );
        }
        final String fillerStr = filler.repeat( Math.max( 0, spaces / 2 ) );
        result.append( fillerStr );
        result.append( text );
        result.append( fillerStr );

        return result.toString();
    }

    public static Message sendSyncMessage( final TextChannel channel, final CharSequence message )
    {
        try
        {
            return channel.sendMessage( message ).complete();
        }
        catch ( InsufficientPermissionException e )
        {
            final String name = e.getPermission().getName();

            LOGGER.error(
                    "The bot does not have the permission " + name + " to send a message in "
                            + channel.getGuild().getName() + "#" + channel.getName() + "."
            );
            return null;
        }
    }

    public static Message sendSyncMessage( final TextChannel channel, final MessageEmbed embed )
    {
        try
        {
            return channel.sendMessage( embed ).complete();
        }
        catch ( InsufficientPermissionException e )
        {
            final String name = e.getPermission().getName();

            LOGGER.error(
                    "The bot does not have the permission " + name + " to send an embed message in "
                            + channel.getGuild().getName() + "#" + channel.getName() + "."
            );
            return null;
        }
    }

    public static void sendMessage( final TextChannel channel, final CharSequence message )
    {
        try
        {
            channel.sendMessage( message ).queue();
        }
        catch ( InsufficientPermissionException e )
        {
            final String name = e.getPermission().getName();

            LOGGER.error(
                    "The bot does not have the permission " + name + " to send a message in "
                            + channel.getGuild().getName() + "#" + channel.getName() + "."
            );
        }
    }

    public static void sendMessage( final TextChannel channel, final MessageEmbed embed )
    {
        try
        {
            channel.sendMessage( embed ).queue();
        }
        catch ( InsufficientPermissionException e )
        {
            final String name = e.getPermission().getName();

            LOGGER.error(
                    "The bot does not have the permission " + name + " to send an embed message in "
                            + channel.getGuild().getName() + "#" + channel.getName() + "."
            );
        }
    }

    public static String checkForPlural( final String word, final int number )
    {
        return word + ( number == 1 ? "" : "s" );
    }
}
