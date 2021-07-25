package be.dieterblancke.kwamkwammer.other.paginator;

import be.dieterblancke.kwamkwammer.SpringContext;
import be.dieterblancke.kwamkwammer.other.MessageUtils;
import be.dieterblancke.kwamkwammer.services.DiscordService;
import lombok.Data;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

@Data
public class Paginator
{

    private final int max;
    private final String userId;
    private long lastUpdate = System.currentTimeMillis();
    private Message message;
    private PageGenerator generator;

    private Page currentPage;

    public Paginator( final int max, final String userId, final PageGenerator generator )
    {
        this.max = max;
        this.userId = userId;
        this.generator = generator;

        setCurrentPage( 1 );
        SpringContext.getBean( DiscordService.class ).registerPaginator( this );
    }

    public void setCurrentPage( final int page )
    {
        if ( page <= 0 || page > max )
        {
            return;
        }
        lastUpdate = System.currentTimeMillis();
        currentPage = generator.generatePage( page );

        if ( message != null )
        {
            if ( currentPage instanceof EmbedPage )
            {
                final EmbedPage embedPage = ( (EmbedPage) currentPage );

                message = message.editMessage( embedPage.build() ).complete();
            }
            else
            {
                message = message.editMessage( currentPage.toString() ).complete();
            }
        }
    }

    public void sendMessage( final TextChannel channel )
    {
        if ( message != null )
        {
            return;
        }
        if ( currentPage instanceof EmbedPage )
        {
            final EmbedPage embedPage = ( (EmbedPage) currentPage );

            message = MessageUtils.sendSyncMessage( channel, embedPage.build() );
        }
        else
        {
            message = MessageUtils.sendSyncMessage( channel, currentPage.toString() );
        }

        if ( max >= 5 )
        {
            message.addReaction( "⏮" ).complete();
        }
        message.addReaction( "⬅" ).complete();
        message.addReaction( "➡" ).complete();
        if ( max >= 5 )
        {
            message.addReaction( "⏭" ).complete();
        }
        message.addReaction( "\uD83C\uDDFD" ).complete();
    }
}
