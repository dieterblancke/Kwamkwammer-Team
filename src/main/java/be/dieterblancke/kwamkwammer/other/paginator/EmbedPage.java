package be.dieterblancke.kwamkwammer.other.paginator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.temporal.TemporalAccessor;

@Data
@EqualsAndHashCode( callSuper = false )
public class EmbedPage extends EmbedBuilder implements Page
{

    private final int page;
    private String message = "";

    public EmbedPage( final int page )
    {
        this.page = page;
    }

    public EmbedPage( final int page, final EmbedBuilder builder )
    {
        super( builder );
        this.page = page;
    }

    @Override
    public EmbedBuilder clear()
    {
        super.clear();
        return this;
    }

    @Override
    public EmbedBuilder setTitle( String title )
    {
        super.setTitle( title );
        return this;
    }

    @Override
    public EmbedBuilder setTitle( String title, String url )
    {
        super.setTitle( title, url );
        return this;
    }

    @Override
    public EmbedBuilder appendDescription( CharSequence description )
    {
        super.appendDescription( description );
        return this;
    }

    @Override
    public EmbedBuilder setTimestamp( TemporalAccessor temporal )
    {
        super.setTimestamp( temporal );
        return this;
    }

    @Override
    public EmbedBuilder setColor( Color color )
    {
        super.setColor( color );
        return this;
    }

    @Override
    public EmbedBuilder setColor( int color )
    {
        super.setColor( color );
        return this;
    }

    @Override
    public EmbedBuilder setThumbnail( String url )
    {
        super.setThumbnail( url );
        return this;
    }

    @Override
    public EmbedBuilder setImage( String url )
    {
        super.setImage( url );
        return this;
    }

    @Override
    public EmbedBuilder setAuthor( String name )
    {
        super.setAuthor( name );
        return this;
    }

    @Override
    public EmbedBuilder setAuthor( String name, String url )
    {
        super.setAuthor( name, url );
        return this;
    }

    @Override
    public EmbedBuilder setAuthor( String name, String url, String iconUrl )
    {
        super.setAuthor( name, url, iconUrl );
        return this;
    }

    @Override
    public EmbedBuilder setFooter( String text, String iconUrl )
    {
        super.setFooter( text, iconUrl );
        return this;
    }

    @Override
    public EmbedBuilder addField( MessageEmbed.Field field )
    {
        super.addField( field );
        return this;
    }

    @Override
    public EmbedBuilder addField( String name, String value, boolean inline )
    {
        super.addField( name, value, inline );
        return this;
    }

    @Override
    public EmbedBuilder addBlankField( boolean inline )
    {
        super.addBlankField( inline );
        return this;
    }

    @Override
    public EmbedBuilder clearFields()
    {
        super.clearFields();
        return this;
    }

    public EmbedPage copyForPage( final int page )
    {
        return new EmbedPage( page, this );
    }
}
